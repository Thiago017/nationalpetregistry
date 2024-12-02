-- V1__Create_users_and_pets_tables.sql

-- Create table users
CREATE TABLE users (
  id UUID PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  gender VARCHAR(1) CHECK (gender IN ('M', 'F')),
  date_of_birth DATE NOT NULL,
  phone VARCHAR(20),
  address TEXT,
  identification_document VARCHAR(14) NOT NULL UNIQUE CHECK (identification_document ~ '^\d{3}\.\d{3}\.\d{3}-\d{2}$')
);

-- Create table pets
CREATE TABLE pets (
  id UUID PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  breed VARCHAR(255),
  gender VARCHAR(1) CHECK (gender IN ('M', 'F')),
  date_of_birth DATE NOT NULL,
  identification_document VARCHAR(15) NOT NULL UNIQUE CHECK (identification_document ~ '^\d{2}\.\d{3}\.\d{3}-\d{3}$'),
  owner_id UUID NOT NULL,
  FOREIGN KEY (owner_id) REFERENCES users (id) ON DELETE CASCADE
);