--liquibase formatted sql

--changeset workoutBot:1
CREATE TABLE workout_user
(
    id   SERIAL primary key,
    telegram_id TEXT NOT NULL UNIQUE,
    user_name TEXT NOT NULL,
    first_name TEXT
);

--changeset workoutBot:2
CREATE TABLE video
(
    id          SERIAL primary key,
    file_unique_id TEXT NOT NULL UNIQUE,
    created_at  TIMESTAMP,
    user_id   SERIAL REFERENCES workout_user (id)
);





