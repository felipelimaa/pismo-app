CREATE TABLE public.transactions
(
    transaction_id bigint NOT NULL,
    account_id bigint NOT NULL,
    operationtype_id bigint NOT NULL,
    amount numeric(19,2) NOT NULL,
    eventdate timestamp without time zone,
    CONSTRAINT transactions_pk PRIMARY KEY (transaction_id),
    CONSTRAINT account_fk FOREIGN KEY (account_id)
            REFERENCES public.accounts (account_id) MATCH SIMPLE
            ON UPDATE NO ACTION
            ON DELETE NO ACTION,
    CONSTRAINT operationtype_fk FOREIGN KEY (operationtype_id)
                REFERENCES public.operational_types (operationtype_id) MATCH SIMPLE
                ON UPDATE NO ACTION
                ON DELETE NO ACTION
);

