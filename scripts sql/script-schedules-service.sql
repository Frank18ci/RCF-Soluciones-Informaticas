
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
