CREATE TABLE users (
    id numeric,
    email varchar,
    firstname varchar,
    lastname varchar,
    password varchar,
    role varchar,
    tms timestamp,
    start_validity timestamp,
    end_validity timestamp,
    CONSTRAINT user_pkey PRIMARY KEY (id)
);

CREATE SEQUENCE IF NOT EXISTS user_seq
    START WITH 2
    INCREMENT BY 1;