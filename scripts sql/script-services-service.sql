
DROP DATABASE IF EXISTS RCF_SOLUCIONES_INFORMATICAS_SERVICES_SERVICE;
CREATE DATABASE RCF_SOLUCIONES_INFORMATICAS_SERVICES_SERVICE;
use RCF_SOLUCIONES_INFORMATICAS_SERVICES_SERVICE;


CREATE TABLE services (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  code VARCHAR(80) NOT NULL UNIQUE,
  name VARCHAR(150) NOT NULL UNIQUE,
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
