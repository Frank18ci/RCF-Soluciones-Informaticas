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

