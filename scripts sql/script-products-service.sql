DROP DATABASE IF EXISTS RCF_SOLUCIONES_INFORMATICAS_PRODUCTS_SERVICE;
CREATE DATABASE RCF_SOLUCIONES_INFORMATICAS_PRODUCTS_SERVICE;
use RCF_SOLUCIONES_INFORMATICAS_PRODUCTS_SERVICE;

CREATE TABLE categories (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  parent_id BIGINT NULL,
  name VARCHAR(120) NOT NULL UNIQUE,
  slug VARCHAR(140) NOT NULL UNIQUE,
  created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  CONSTRAINT fk_categories_parent FOREIGN KEY (parent_id) REFERENCES categories(id) ON DELETE SET NULL
);
CREATE INDEX idx_categories_parent ON categories(parent_id);

INSERT INTO categories (name, slug) 
VALUES ('Cámaras', 'camaras'),
('Accesorios', 'accesorios'),
('Video', 'video'),
('Audio', 'audio'),
('Computadoras', 'computadoras'),
('Monitores', 'monitores');

CREATE TABLE discounts_types(
	id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL UNIQUE
);

INSERT INTO discounts_types(name) 
VALUES ('PERCENTAGE'), ('FIXED');

CREATE TABLE discounts (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  code VARCHAR(50) NOT NULL UNIQUE,
  description VARCHAR(150),
  discount_type_id BIGINT NOT NULL,   
  value DECIMAL(15,2) NOT NULL,         -- porcentaje o monto fijo
  active BOOLEAN NOT NULL DEFAULT TRUE,
  start_date DATETIME NULL,
  end_date DATETIME NULL,
  created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  CONSTRAINT fk_discounts_discount_type FOREIGN KEY (discount_type_id) REFERENCES discounts_types(id)
);
INSERT INTO discounts (code, description, discount_type_id, value)
VALUES ('PROMO10', 'Descuento 10%', 1, 10.00);


CREATE TABLE product_attributes (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  code VARCHAR(100) NOT NULL UNIQUE, -- ej. 'COLOR','MATERIAL'
  name VARCHAR(150) NOT NULL
);

INSERT INTO product_attributes (code, name) VALUES 
('COLOR', 'Color'),
('MATERIAL', 'Material');

CREATE TABLE products (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  sku VARCHAR(100) NOT NULL UNIQUE,
  name VARCHAR(150) NOT NULL,
  short_description VARCHAR(255) NULL,
  img_url varchar(255) null,
  description TEXT NULL,
  base_price_cents BIGINT NOT NULL,      -- precio base (ej. sin descuento ni impuestos)
  purchase_price_cents BIGINT NOT NULL,  -- precio de compra (costo)
  sale_price_cents BIGINT NOT NULL,      -- precio de venta sugerido
  tax_rate DECIMAL(5,2) NOT NULL DEFAULT 0,
  stock INT NOT NULL DEFAULT 0,
  category_id BIGINT NULL,
  discount_id BIGINT NULL,
  active BOOLEAN NOT NULL DEFAULT TRUE,
  created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  deleted_at DATETIME NULL,
  CONSTRAINT fk_products_category FOREIGN KEY (category_id) REFERENCES categories(id) ON DELETE SET NULL,
  CONSTRAINT fk_products_discount FOREIGN KEY (discount_id) REFERENCES discounts(id) ON DELETE SET NULL
);
CREATE INDEX idx_products_category ON products(category_id);
CREATE INDEX idx_products_active ON products(active);

INSERT INTO products (sku, name, base_price_cents, purchase_price_cents, sale_price_cents, tax_rate, stock, category_id, discount_id, short_description, description)
VALUES ('CAM-001', 'Cámara HD', 150000, 100000, 180000, 18.00, 20, 1, 1, 'Cámara de alta definición', 'Cámara HD con resolución 1080p, ideal para grabaciones profesionales.'),
('LEN-001', 'Lente Zoom', 150000, 120000, 200000, 18.00, 15, 1, NULL, 'Lente con zoom óptico 10x', 'Lente zoom compatible con cámaras DSLR, ofrece una calidad de imagen superior.'),
('ACC-001', 'Trípode', 50000, 30000, 70000, 18.00, 30, 2, NULL, 'Trípode ajustable', 'Trípode ligero y ajustable, perfecto para fotografía y video.'),
('MON-001', 'Monitor 4K', 800000, 600000, 950000, 18.00, 10, 6, NULL, 'Monitor con resolución 4K', 'Monitor profesional con resolución 4K, ideal para edición de video y fotografía.'),
('AUD-001', 'Micrófono Profesional', 200000, 150000, 250000, 18.00, 25, 4, NULL, 'Micrófono de alta calidad', 'Micrófono profesional con cancelación de ruido, ideal para grabaciones de estudio.');

CREATE TABLE product_attribute_values (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  product_id BIGINT NOT NULL,
  attribute_id BIGINT NOT NULL,
  value VARCHAR(255) NOT NULL,
  UNIQUE KEY uq_prod_attr (product_id, attribute_id),
  CONSTRAINT fk_pav_product FOREIGN KEY (product_id) REFERENCES products(id) ON DELETE CASCADE,
  CONSTRAINT fk_pav_attribute FOREIGN KEY (attribute_id) REFERENCES product_attributes(id) ON DELETE CASCADE
);
INSERT INTO product_attribute_values (product_id, attribute_id, value) VALUES
(1, 1, 'Negro'),
(1, 2, 'Plástico');



-- Inserts para productos de la marca Dahua
INSERT INTO products (sku, name, short_description, img_url, base_price_cents, purchase_price_cents, sale_price_cents, tax_rate, stock, category_id, description)
VALUES 
('DAH-001', 'Cámara de Vigilancia Domo Dahua DH-ABC123', 'Domo Dahua DH-ABC123, una cámara de seguridad compacta y eficiente.', 'DAH-001.jpg', 31500, 28000, 35000, 18.00, 50, 1, 'Domo Dahua DH-ABC123, una cámara de seguridad compacta y eficiente.'),
('DAH-002', 'Cámara de Seguridad Dahua Technology', 'Domo Dahua Technology, una cámara de seguridad avanzada.', 'DAH-002.jpg', 22500, 20000, 25000, 18.00, 50, 1, 'Domo Dahua Technology, una cámara de seguridad avanzada.'),
('DAH-003', 'Cámara de Seguridad Dahua Doble Lente', 'Domo Dahua Doble Lente, una cámara de seguridad con visión avanzada.', 'DAH-003.jpg', 22500, 20000, 25000, 18.00, 40, 1, 'Domo Dahua Doble Lente, una cámara de seguridad con visión avanzada.'),
('DAH-004', 'Cámara de Seguridad Rectangular Dahua', 'Dahua Rectangular, una cámara de seguridad moderna y compacta.', 'DAH-004.jpg', 31500, 28000, 35000, 18.00, 60, 1, 'Dahua Rectangular, una cámara de seguridad moderna y compacta.'),
('DAH-005', 'Cámara de Seguridad Domo Dahua con Luces', 'Domo Dahua con Luces, una cámara de seguridad con iluminación adicional.', 'DAH-005.jpg', 22500, 20000, 25000, 18.00, 45, 1, 'Domo Dahua con Luces, una cámara de seguridad con iluminación adicional.'),
('DAH-006', 'Cámara de Seguridad Domo Dahua Clásica', 'Domo Dahua Clásica, una cámara de seguridad confiable y duradera.', 'DAH-006.jpg', 9000, 8000, 10000, 18.00, 100, 1, 'Domo Dahua Clásica, una cámara de seguridad confiable y duradera.'),
('DAH-007', 'Cámara de Seguridad Domo Dahua Inclinada', 'Domo Dahua Inclinada, una cámara de seguridad con lente ajustable.', 'DAH-007.jpg', 23400, 20800, 26000, 18.00, 30, 1, 'Domo Dahua Inclinada, una cámara de seguridad con lente ajustable.'),
('DAH-008', 'Cámara de Seguridad Domo Dahua Básica', 'Domo Dahua Básica, una cámara de seguridad esencial y fácil de usar.', 'DAH-008.jpg', 33210, 29520, 36900, 18.00, 70, 1, 'Domo Dahua Básica, una cámara de seguridad esencial y fácil de usar.'),
('DAH-009', 'Cámara de Seguridad Dahua con Luces Multicolor', 'Dahua con Luces Multicolor, una cámara de seguridad con iluminación adicional y versátil.', 'DAH-009.jpg', 27000, 24000, 30000, 18.00, 35, 1, 'Dahua con Luces Multicolor, una cámara de seguridad con iluminación adicional y versátil.'),
('DAH-010', 'Cámara de Seguridad Domo Dahua Negra', 'Domo Dahua Negra, una cámara de seguridad elegante con luces de colores.', 'DAH-010.jpg', 36990, 32880, 41100, 18.00, 25, 1, 'Domo Dahua Negra, una cámara de seguridad elegante con luces de colores.'),
('DAH-011', 'Cámara de Seguridad Dahua con Franja Negra', 'Dahua con Franja Negra, una cámara de seguridad moderna y versátil.', 'DAH-011.jpg', 44100, 39200, 49000, 18.00, 20, 1, 'Dahua con Franja Negra, una cámara de seguridad moderna y versátil.');

-- Inserts para productos de la marca EZVIZ
INSERT INTO products (sku, name, short_description, img_url, base_price_cents, purchase_price_cents, sale_price_cents, tax_rate, stock, category_id, description)
VALUES
('EZV-001', 'Cámara de Seguridad Hikvision Esférica', 'Hikvision Esférica, una cámara de seguridad compacta y eficiente.', 'EZV-001.jpg', 36000, 32000, 40000, 18.00, 40, 1, 'Hikvision Esférica, una cámara de seguridad compacta y eficiente.'),
('EZV-002', 'Cámara de Seguridad H.View con Antenas', 'H.View con Antenas, una cámara de seguridad inalámbrica y eficiente.', 'EZV-002.jpg', 36900, 32800, 41000, 18.00, 35, 1, 'H.View con Antenas, una cámara de seguridad inalámbrica y eficiente.'),
('EZV-003', 'Cámara de Seguridad con Anillo Azul', 'Cámara con Anillo Azul, una cámara de seguridad moderna con antenas.', 'EZV-003.jpg', 42660, 37920, 47400, 18.00, 30, 1, 'Cámara con Anillo Azul, una cámara de seguridad moderna con antenas.'),
('EZV-004', 'Cámara de Seguridad EZVIZ Esférica', 'EZVIZ Esférica, una cámara de seguridad compacta y moderna.', 'EZV-004.jpg', 41310, 36720, 45900, 18.00, 25, 1, 'EZVIZ Esférica, una cámara de seguridad compacta y moderna.'),
('EZV-005', 'Cámara de Seguridad EZVIZ Cuadrada', 'Es una cámara cuadrada de color negro con bordes redondeados.', 'EZV-005.jpg', 40950, 36400, 45500, 18.00, 28, 1, 'Es una cámara cuadrada de color negro con bordes redondeados.'),
('EZV-006', 'Cámara de Seguridad EZVIZ Cilíndrica', 'EZVIZ Cilíndrica, una cámara de seguridad moderna y compacta.', 'EZV-006.jpg', 40860, 36320, 45400, 18.00, 33, 1, 'EZVIZ Cilíndrica, una cámara de seguridad moderna y compacta.'),
('EZV-007', 'Cámara de Seguridad EZVIZ con Antenas', 'EZVIZ con Antenas, una cámara de seguridad versátil con detección de movimiento.', 'EZV-007.jpg', 31500, 28000, 35000, 18.00, 50, 1, 'EZVIZ con Antenas, una cámara de seguridad versátil con detección de movimiento.'),
('EZV-008', 'Cámara de Seguridad EZVIZ Domo', 'EZVIZ Domo, una cámara de seguridad compacta y duradera.', 'EZV-008.jpg', 22500, 20000, 25000, 18.00, 60, 1, 'EZVIZ Domo, una cámara de seguridad compacta y duradera.'),
('EZV-009', 'Cámara de Seguridad EZVIZ Esférica', 'EZVIZ Esférica, una cámara de seguridad compacta y moderna.', 'EZV-009.jpg', 13500, 12000, 15000, 18.00, 80, 1, 'EZVIZ Esférica, una cámara de seguridad compacta y moderna.'),
('EZV-010', 'Cámara de Seguridad EZVIZ Redonda', 'EZVIZ Redonda, una cámara de seguridad compacta y estilizada.', 'EZV-010.jpg', 20700, 18400, 23000, 18.00, 70, 1, 'EZVIZ Redonda, una cámara de seguridad compacta y estilizada.'),
('EZV-011', 'Cámara de Seguridad EZVIZ Ovalada', 'EZVIZ Ovalada, una cámara de seguridad moderna con luz azul.', 'EZV-011.jpg', 22500, 20000, 25000, 18.00, 45, 1, 'EZVIZ Ovalada, una cámara de seguridad moderna con luz azul.');

-- Inserts para productos de la marca HIKVISION
INSERT INTO products (sku, name, short_description, img_url, base_price_cents, purchase_price_cents, sale_price_cents, tax_rate, stock, category_id, description)
VALUES
('HIK-001', 'Cámara de Seguridad HIKVISION Modelo 2023', 'Cámara de seguridad negra y blanca de la marca HIKVISION.', 'HIK-001.jpg', 38700, 34400, 43000, 18.00, 40, 1, 'Cámara de seguridad negra y blanca de la marca HIKVISION.'),
('HIK-002', 'Camara esferica HIKVISION 204-AF', 'Camara esferica con lente protector.', 'HIK-002.jpg', 44100, 39200, 49000, 18.00, 35, 1, 'Camara esferica con lente protector.'),
('HIK-003', 'Camara esferica HIKVISION 174-AF', 'Camara esferica con lente protector.', 'HIK-003.jpg', 41400, 36800, 46000, 18.00, 30, 1, 'Camara esferica con lente protector.'),
('HIK-004', 'Camara esferica HIKVISION 154-AF', 'Camara esperica de lente cuadrado de buena calidad.', 'HIK-004.jpg', 36000, 32000, 40000, 18.00, 25, 1, 'Camara esperica de lente cuadrado de buena calidad.'),
('HIK-005', 'Cámara de Seguridad Hikvision circular', 'Camara que detecta el movimiento y tiene buena resolucion.', 'HIK-005.jpg', 53910, 47920, 59900, 18.00, 20, 1, 'Camara que detecta el movimiento y tiene buena resolucion.'),
('HIK-006', 'Cámara de Seguridad HIKVISION con más alto', 'HIKVISION, camara con soporte de grande.', 'HIK-006.jpg', 31500, 28000, 35000, 18.00, 50, 1, 'HIKVISION, camara con soporte de grande.'),
('HIK-007', 'Cámara de Seguridad HIKVISION rectangular', 'HIKVISION, camara con colores blanco y negro.', 'HIK-007.jpg', 42750, 38000, 47500, 18.00, 45, 1, 'HIKVISION, camara con colores blanco y negro.');