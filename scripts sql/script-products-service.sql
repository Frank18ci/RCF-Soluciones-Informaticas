
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
