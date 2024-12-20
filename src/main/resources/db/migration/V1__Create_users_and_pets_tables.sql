-- V1__Create_users_and_pets_tables.sql

-- Create table users
CREATE TABLE users (
  id UUID PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  gender VARCHAR(1) CHECK (gender IN ('M', 'F')),
  date_of_birth DATE NOT NULL,
  phone VARCHAR(20) NOT NULL UNIQUE,
  address TEXT,
  identification_document VARCHAR(14) NOT NULL UNIQUE
);

-- Create table pets
CREATE TABLE pets (
  id UUID PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  breed VARCHAR(255),
  gender VARCHAR(1) CHECK (gender IN ('M', 'F')),
  date_of_birth DATE NOT NULL,
  identification_document VARCHAR(15) NOT NULL UNIQUE,
  owner_id UUID NOT NULL,
  FOREIGN KEY (owner_id) REFERENCES users (id) ON DELETE CASCADE
);