DROP DATABASE IF EXISTS RCF_SOLUCIONES_INFORMATICAS_USERS_SERVICE;
CREATE DATABASE RCF_SOLUCIONES_INFORMATICAS_USERS_SERVICE;
use RCF_SOLUCIONES_INFORMATICAS_USERS_SERVICE;

CREATE TABLE roles (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  code VARCHAR(30) NOT NULL UNIQUE,
  name VARCHAR(60) NOT NULL
);

INSERT INTO roles(code, name) VALUES ('CLIENT','Cliente'),('TECH','Técnico'),('ADMIN','Administrador');

CREATE TABLE users (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  email VARCHAR(100) NOT NULL UNIQUE,
  password_hash VARCHAR(255) NOT NULL,
  full_name VARCHAR(120) NOT NULL,
  role_id BIGINT NOT NULL,
  phone VARCHAR(30) NULL,
  is_active BOOLEAN NOT NULL DEFAULT TRUE,
  created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  deleted_at DATETIME NULL,
  CONSTRAINT fk_users_role FOREIGN KEY (role_id) REFERENCES roles(id)
);

CREATE INDEX idx_users_role ON users(role_id);
CREATE INDEX idx_users_active ON users(is_active);

-- Insertar usuarios (rol ya estaba insertado en tu script)
INSERT INTO users (email, password_hash, full_name, role_id, phone)
VALUES
('juan.perez@example.com', 'hashed_pass_123', 'Juan Pérez', 1, '+51987654321'), -- Cliente
('maria.garcia@example.com', 'hashed_pass_456', 'María García', 2, '+51981234567'), -- Técnico
('admin@example.com', 'hashed_pass_789', 'Admin Root', 3, NULL); -- Admin

CREATE TABLE countries (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  code CHAR(2) NOT NULL UNIQUE,
  name VARCHAR(100) NOT NULL UNIQUE
);

INSERT INTO countries (code, name) VALUES
('PE', 'Perú'),
('US', 'Estados Unidos');

CREATE TABLE states_regions (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(100) NOT NULL,
  country_id BIGINT NOT NULL,
  CONSTRAINT fk_states_regions_country FOREIGN KEY (country_id) REFERENCES countries(id)
);

INSERT INTO states_regions (name, country_id) VALUES
('Lima', 1),
('Cusco', 1),
('California', 2),
('New York', 2);

CREATE TABLE cities (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(100) NOT NULL,
  state_region_id BIGINT NOT NULL,
  CONSTRAINT fk_cities_state FOREIGN KEY (state_region_id) REFERENCES states_regions(id)
);

INSERT INTO cities (name, state_region_id) VALUES
('Lima Metropolitana', 1),
('Miraflores', 1),
('Cusco', 2),
('Los Angeles', 3),
('San Francisco', 3),
('New York City', 4);

CREATE TABLE user_addresses (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  user_id BIGINT NOT NULL,
  address VARCHAR(150) NOT NULL,
  reference_address VARCHAR(150),
  city_id BIGINT NOT NULL,
  postal_code VARCHAR(30),
  created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  CONSTRAINT fk_user_addresses_user FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
  CONSTRAINT fk_user_addresses_city FOREIGN KEY (city_id) REFERENCES cities(id)
);

CREATE INDEX idx_user_addresses_user ON user_addresses(user_id);

-- Insertar direcciones de usuario
INSERT INTO user_addresses (user_id, address, reference_address, city_id, postal_code)
VALUES
(1, 'Av. Arequipa 1234', 'Cerca al parque Kennedy', 2, '15074'),
(1, 'Calle Los Pinos 567', 'Frente a la bodega Azul', 1, '15073'),
(2, 'Jr. Saphy 345', 'A espaldas de la Plaza de Armas', 3, '08002'),
(3, '123 Main St', 'Near Central Park', 6, '10001');

-- ------------------------------------------

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

INSERT INTO products (sku, name, base_price_cents, purchase_price_cents, sale_price_cents, tax_rate, stock, category_id, discount_id)
VALUES ('CAM-001', 'Cámara HD', 150000, 100000, 180000, 18.00, 20, 1, 1);

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

-- ------------------------------------------

DROP DATABASE IF EXISTS RCF_SOLUCIONES_INFORMATICAS_SERVICES_SERVICE;
CREATE DATABASE RCF_SOLUCIONES_INFORMATICAS_SERVICES_SERVICE;
use RCF_SOLUCIONES_INFORMATICAS_SERVICES_SERVICE;


CREATE TABLE services (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  code VARCHAR(80) NOT NULL UNIQUE,
  name VARCHAR(150) NOT NULL,
  description TEXT NULL,
  base_price_cents BIGINT NOT NULL,         -- precio base en centavos
  tax_rate DECIMAL(5,2) NOT NULL DEFAULT 0, -- impuesto
  duration_minutes INT NOT NULL,            -- duración estimada
  requires_on_site BOOLEAN NOT NULL DEFAULT FALSE,
  active BOOLEAN NOT NULL DEFAULT TRUE,
  created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);
CREATE INDEX idx_services_active ON services(active);

INSERT INTO services (code, name, description, base_price_cents, tax_rate, duration_minutes, requires_on_site)
VALUES 
('INSTALL_CAM', 'Instalación de cámara', 'Instalación profesional de cámaras de seguridad', 5000, 18.00, 60, TRUE),
('MAINT_CAM', 'Mantenimiento de cámara', 'Limpieza y revisión técnica de la cámara', 3000, 18.00, 45, TRUE),
('CONFIG_SYS', 'Configuración remota', 'Configuración vía acceso remoto', 2000, 0.00, 30, FALSE);


CREATE TABLE product_services (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  service_id BIGINT NOT NULL,
  product_id BIGINT NOT NULL,
  price_override_cents BIGINT NULL, -- precio específico para este producto
  UNIQUE KEY uq_service_product (service_id, product_id)
);

INSERT INTO product_services (service_id, product_id, price_override_cents)
VALUES
(1, 1, NULL),        -- Instalación para Cámara HD (usa precio base del servicio)
(2, 1, 2500),        -- Mantenimiento para Cámara HD (precio reducido)
(1, 2, 6000),        -- Instalación para Lente Zoom (precio especial)
(3, 1, NULL);        -- Configuración remota para Cámara HD

-- ----------------------------------------------------

DROP DATABASE IF EXISTS RCF_SOLUCIONES_INFORMATICAS_ORDERS_SERVICE;
CREATE DATABASE RCF_SOLUCIONES_INFORMATICAS_ORDERS_SERVICE;
use RCF_SOLUCIONES_INFORMATICAS_ORDERS_SERVICE;

CREATE TABLE order_statuses (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  code VARCHAR(20) NOT NULL UNIQUE,   -- 'PENDING','PAID','CANCELLED','COMPLETED'
  sort_order INT NOT NULL
);

INSERT INTO order_statuses(code, sort_order)
VALUES 
('PENDING', 1),
('PAID', 2),
('COMPLETED', 3),
('CANCELLED', 99);

-- Órdenes
CREATE TABLE orders (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  user_id BIGINT NOT NULL,               -- referencia lógica a usuario
  currency_code CHAR(3) NOT NULL,
  status_id BIGINT NOT NULL,                -- FK a order_statuses
  payment_method_code VARCHAR(40) NULL,
  subtotal_cents BIGINT NOT NULL DEFAULT 0,
  tax_total_cents BIGINT NOT NULL DEFAULT 0,
  total_cents BIGINT NOT NULL DEFAULT 0,
  discount_cents BIGINT NOT NULL DEFAULT 0,
  notes TEXT NULL,
  created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  CONSTRAINT fk_orders_status FOREIGN KEY (status_id) REFERENCES order_statuses(id)
);

CREATE INDEX idx_orders_user ON orders(user_id);
CREATE INDEX idx_orders_status ON orders(status_id);
CREATE INDEX idx_orders_created ON orders(created_at);

INSERT INTO orders (user_id, currency_code, status_id, payment_method_code, subtotal_cents, tax_total_cents, total_cents, discount_cents, notes)
VALUES
(101, 'USD', 1, 'CARD', 150000, 27000, 177000, 0, 'Primera orden de prueba'),
(102, 'USD', 2, 'PAYPAL', 300000, 54000, 342000, 20000, 'Segunda orden con descuento'),
(103, 'USD', 1, 'CASH', 50000, 9000, 59000, 0, 'Orden rápida');

-- Items de orden
CREATE TABLE order_items (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  order_id BIGINT NOT NULL,
  product_id BIGINT NULL,                 -- referencia lógica a producto
  service_id BIGINT NULL,                 -- referencia lógica a servicio
  description VARCHAR(255) NULL,
  qty INT NOT NULL DEFAULT 1,
  unit_price_cents BIGINT NOT NULL,
  tax_rate DECIMAL(5,2) NOT NULL DEFAULT 0,
  tax_amount_cents BIGINT NOT NULL DEFAULT 0,
  total_line_cents BIGINT NOT NULL,
  discount_line_cents BIGINT NOT NULL DEFAULT 0,
  CONSTRAINT fk_order_items_order FOREIGN KEY (order_id) REFERENCES orders(id) ON DELETE CASCADE,
  CHECK (
    (product_id IS NOT NULL AND service_id IS NULL)
    OR (product_id IS NULL AND service_id IS NOT NULL)
  )
);

CREATE INDEX idx_order_items_order ON order_items(order_id);
CREATE INDEX idx_order_items_product ON order_items(product_id);
CREATE INDEX idx_order_items_service ON order_items(service_id);

INSERT INTO order_items (order_id, product_id, service_id, description, qty, unit_price_cents, tax_rate, tax_amount_cents, total_line_cents, discount_line_cents)
VALUES
-- Orden 1
(1, 1, NULL, 'Cámara HD', 1, 150000, 18.00, 27000, 177000, 0),
(1, NULL, 1, 'Instalación de cámara', 1, 5000, 18.00, 900, 5900, 0),

-- Orden 2
(2, 2, NULL, 'Lente Zoom', 2, 150000, 18.00, 54000, 342000, 20000),
(2, NULL, 3, 'Configuración remota', 1, 2000, 0.00, 0, 2000, 0),

-- Orden 3
(3, 3, NULL, 'Trípode', 1, 50000, 18.00, 9000, 59000, 0);

-- ----------------------------

DROP DATABASE IF EXISTS RCF_SOLUCIONES_INFORMATICAS_SCHEDULES_SERVICE;
CREATE DATABASE RCF_SOLUCIONES_INFORMATICAS_SCHEDULES_SERVICE;
use RCF_SOLUCIONES_INFORMATICAS_SCHEDULES_SERVICE;

-- Estados de reservas
CREATE TABLE booking_statuses (
  id bigint AUTO_INCREMENT PRIMARY KEY,
  code VARCHAR(20) NOT NULL UNIQUE,       -- 'SCHEDULED','ASSIGNED','IN_PROGRESS','COMPLETED','CANCELLED'
  sort_order INT NOT NULL
);

INSERT INTO booking_statuses(code, sort_order)
VALUES 
('SCHEDULED', 1),
('ASSIGNED', 2),
('IN_PROGRESS', 3),
('COMPLETED', 4),
('CANCELLED', 99);

-- Reservas de servicios
CREATE TABLE service_bookings (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  order_item_id BIGINT NOT NULL,           -- referencia lógica a order_items
  user_id BIGINT NOT NULL,                 -- cliente
  service_id BIGINT NOT NULL,              -- referencia lógica a service
  product_id BIGINT NULL,                  -- si aplica
  technician_user_id BIGINT NULL,          -- técnico asignado
  status_id bigint NOT NULL,                  -- FK a booking_statuses
  scheduled_start DATETIME NOT NULL,
  scheduled_end DATETIME NOT NULL,
  address_snapshot VARCHAR(250) NULL,      -- snapshot de dirección
  notes TEXT NULL,
  created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  CONSTRAINT fk_bookings_status FOREIGN KEY (status_id) REFERENCES booking_statuses(id),
  CHECK (scheduled_end > scheduled_start)
);
CREATE INDEX idx_bookings_service ON service_bookings(service_id);
CREATE INDEX idx_bookings_technician_time ON service_bookings(technician_user_id, scheduled_start);
CREATE INDEX idx_bookings_user ON service_bookings(user_id);

INSERT INTO service_bookings 
(order_item_id, user_id, service_id, product_id, technician_user_id, status_id, scheduled_start, scheduled_end, address_snapshot, notes)
VALUES
-- Reserva 1: Cliente 101, Servicio 1, Producto 1, Técnico 201
(1, 101, 1, 1, 201, 1, '2025-09-27 09:00:00', '2025-09-27 10:00:00', 'Av. Siempre Viva 123, Lima', 'Instalación de cámara HD'),

-- Reserva 2: Cliente 102, Servicio 3, sin producto, Técnico 202
(4, 102, 3, NULL, 202, 2, '2025-09-27 11:00:00', '2025-09-27 11:45:00', 'Calle Falsa 456, Lima', 'Configuración remota'),

-- Reserva 3: Cliente 103, Servicio 1, Producto 3, Técnico 203
(5, 103, 1, 3, 203, 1, '2025-09-28 14:00:00', '2025-09-28 15:00:00', 'Jr. Los Pinos 789, Lima', 'Instalación de trípode');
