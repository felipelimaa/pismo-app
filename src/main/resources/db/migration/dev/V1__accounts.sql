CREATE TABLE public.accounts
(
    account_id bigint NOT NULL,
    document_number character varying(255) NOT NULL,
    CONSTRAINT accounts_pk PRIMARY KEY (account_id)
);

CREATE SEQUENCE public.hibernate_sequence
    INCREMENT 1
    START 1;