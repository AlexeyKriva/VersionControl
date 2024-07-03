CREATE TYPE access AS ENUM ('PUBLIC', 'PRIVATE');

CREATE TABLE IF NOT EXISTS public.projects
(
    id serial NOT NULL,
    name character varying(255) NOT NULL,
    description character varying(1000) NOT NULL,
    user_id BIGINT NOT NULL,
    added_date TIMESTAMP NOT NULL,
    with_access access NOT NULL,
    CONSTRAINT projects_pkey PRIMARY KEY (id),
    CONSTRAINT projects_name_key UNIQUE (name),
    CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES users(id)
)