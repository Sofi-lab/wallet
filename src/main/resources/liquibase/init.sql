--liquibase formatted sql

--changeset KirikovaSofya:1
DROP TABLE IF EXISTS wallets;

CREATE TABLE wallets (
id uuid PRIMARY KEY,
amount numeric
);

INSERT INTO WALLETS (id, amount) VALUES (gen_random_uuid(), 1000);
INSERT INTO WALLETS (id, amount) VALUES (gen_random_uuid(), 5000);
INSERT INTO WALLETS (id, amount) VALUES (gen_random_uuid(), 3000000);
INSERT INTO WALLETS (id, amount) VALUES (gen_random_uuid(), 74637000);
INSERT INTO WALLETS (id, amount) VALUES (gen_random_uuid(), 366000);
INSERT INTO WALLETS (id, amount) VALUES (gen_random_uuid(), 500);
INSERT INTO WALLETS (id, amount) VALUES (gen_random_uuid(), 30);
INSERT INTO WALLETS (id, amount) VALUES (gen_random_uuid(), 1000000);
INSERT INTO WALLETS (id, amount) VALUES (gen_random_uuid(), 66477000);
INSERT INTO WALLETS (id, amount) VALUES (gen_random_uuid(), 700);