ALTER TABLE public.accounts
ADD COLUMN credit_limit numeric(19,2) not null default 0;