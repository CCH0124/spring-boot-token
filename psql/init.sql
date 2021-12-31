CREATE USER dev WITH ENCRYPTED PASSWORD '123456';
ALTER USER dev WITH CREATEDB;
CREATE DATABASE jwt WITH
        OWNER = dev
        ENCODING = 'UTF8'
        TABLESPACE = pg_default
        CONNECTION LIMIT = -1;
GRANT ALL PRIVILEGES ON DATABASE jwt TO dev;

\c jwt
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";