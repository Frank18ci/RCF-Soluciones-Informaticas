
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
