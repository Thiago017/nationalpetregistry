-- V2__Add_validation_to_identification_documents.sql
-- Update users table with CPF validation
ALTER TABLE
  users
ADD
  CONSTRAINT chk_cpf_format CHECK (
    identification_document ~ '^\d{3}\.\d{3}\.\d{3}-\d{2}$'
  );

-- Update pets table with RG validation
ALTER TABLE
  pets
ADD
  CONSTRAINT chk_rg_format CHECK (identification_document ~ '^\d{1,9}$');