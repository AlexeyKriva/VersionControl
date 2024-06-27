CREATE DATABASE version_control_db;

CREATE TABLE IF NOT EXISTS public.users
(
    id serial NOT NULL,
    username character varying(255) NOT NULL,
    email character varying(255) NOT NULL,
    password character varying(255) NOT NULL,
    is_enabled boolean NOT NULL,
    role character varying(255) NOT NULL,
    CONSTRAINT users_pkey PRIMARY KEY (id),
    CONSTRAINT users_email_key UNIQUE (email),
    CONSTRAINT users_nickname_key UNIQUE (username)
)