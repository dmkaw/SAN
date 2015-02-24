DROP DATABASE IF EXISTS "KEMOD";
DROP SCHEMA IF EXISTS kemod;
DROP ROLE IF EXISTS kemod;
CREATE DATABASE "KEMOD" ENCODING 'utf-8';
\c "KEMOD"
CREATE ROLE kemod ENCRYPTED PASSWORD 'md57a7bf3385a28ddc48e7a0875a1e03101' NOSUPERUSER NOCREATEDB NOCREATEROLE INHERIT LOGIN;
CREATE SCHEMA kemod AUTHORIZATION kemod;
