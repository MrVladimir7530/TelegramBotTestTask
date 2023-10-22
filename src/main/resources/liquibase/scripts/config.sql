--liquibase formatted sql

--changeset volkov:create_table_users
CREATE TABLE IF NOT EXISTS users(
    chat_id bigint NOT NULL PRIMARY KEY,
    first_name character varying(255),
    last_name character varying(255),
    username character varying(255),
    registered_at timestamp without time zone,
    last_message_at timestamp without time zone
);

--changeset volkov:create_table_users_message
CREATE TABLE IF NOT EXISTS users_message(
    users_message_id bigserial NOT NULL PRIMARY KEY,
    sent_message character varying(255),
    received_message character varying(255),
    users_chat_id bigint NOT NULL,
    FOREIGN KEY (users_chat_id) REFERENCES users (chat_id)
);

--changeset volkov:create_table_daily_domains
CREATE TABLE IF NOT EXISTS daily_domains(
    daily_domains_id bigserial NOT NULL PRIMARY KEY,
    domainname character varying(255),
    hotness int,
    price int,
    x_value int,
    yandex_tic int,
    links int,
    visitors int,
    registrar character varying(255),
    old int,
    delete_date date,
    rkn boolean,
    judicial boolean,
    block boolean
);