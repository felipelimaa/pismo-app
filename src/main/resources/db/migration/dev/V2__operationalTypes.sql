CREATE TABLE public.operational_types
(
    operationtype_id bigint NOT NULL,
    description character varying(255),
    operation_decrease character varying(255) not null,
    CONSTRAINT operat_types_pk PRIMARY KEY (operationtype_id)
);

INSERT INTO public.operational_types(operationtype_id, description, operation_decrease) VALUES (1, 'COMPRA A VISTA', 'true');
INSERT INTO public.operational_types(operationtype_id, description, operation_decrease) VALUES (2, 'COMPRA PARCELADA', 'true');
INSERT INTO public.operational_types(operationtype_id, description, operation_decrease) VALUES (3, 'SAQUE', 'true');
INSERT INTO public.operational_types(operationtype_id, description, operation_decrease) VALUES (4, 'PAGAMENTO', 'false');