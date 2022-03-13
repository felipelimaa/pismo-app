CREATE TABLE transactions
(
    transaction_id bigint NOT NULL,
    account_id bigint NOT NULL,
    operationtype_id bigint NOT NULL,
    amount numeric(19,2) NOT NULL,
    eventdate timestamp without time zone,
    CONSTRAINT transactions_pk PRIMARY KEY (transaction_id),
    CONSTRAINT account_fk FOREIGN KEY (account_id)
            REFERENCES accounts (account_id),
    CONSTRAINT operationtype_fk FOREIGN KEY (operationtype_id)
            REFERENCES operational_types (operationtype_id)
);

