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
    users_message_id bigint NOT NULL PRIMARY KEY,
    sent_message character varying(255),
    received_message character varying(255),
    users_chat_id bigint NOT NULL,
    FOREIGN KEY (users_chat_id) REFERENCES users (chat_id)
);