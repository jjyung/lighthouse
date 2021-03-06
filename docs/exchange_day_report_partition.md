```sql
CREATE OR REPLACE FUNCTION exchange_day_report_insert_function()
RETURNS TRIGGER AS $$
DECLARE
        partition_date TEXT;
        partition_name TEXT;
        start_of_year TEXT;
        end_of_next_year TEXT;
BEGIN
        partition_date := to_char(NEW.date, 'YYYY');
        partition_name := 'exchange_day_report_' || partition_date;
        start_of_year := to_char((NEW.date), 'YYYY') || '-01-01';
        end_of_next_year := to_char((NEW.date + interval '1 year'), 'YYYY') || '-01-01';
IF NOT EXISTS
        (SELECT 1
         FROM information_schema.tables
         WHERE table_name = partition_name)
THEN
        EXECUTE format(E'CREATE TABLE %I (CHECK ( date_trunc(\'month\', date) >= ''%s'' AND date_trunc(\'month\', date) < ''%s'')) INHERITS (public.exchange_day_report)', partition_name, start_of_year, end_of_next_year);
        RAISE NOTICE 'A partition has been created %', partition_name;
        EXECUTE format('CREATE INDEX %I ON %I (date)', partition_name || '_date', partition_name);
        RAISE NOTICE 'A partition index has been created %', partition_name || '_date';
END IF;
EXECUTE format('INSERT INTO %I SELECT ($1).*', partition_name) USING NEW;
RETURN NEW;
END
$$
LANGUAGE plpgsql;

CREATE TRIGGER insert_exchange_day_report_trigger
BEFORE INSERT ON public.exchange_day_report
FOR EACH ROW EXECUTE PROCEDURE public.exchange_day_report_insert_function();

CREATE OR REPLACE FUNCTION partitions_master_table_cleanup_trigger()
RETURNS trigger AS
$BODY$BEGIN
DELETE FROM ONLY public.exchange_day_report;
RETURN NULL;
END;$BODY$
LANGUAGE plpgsql VOLATILE
COST 100;

CREATE TRIGGER clean_partitions_master_table
AFTER INSERT
ON public.exchange_day_report
FOR EACH ROW
EXECUTE PROCEDURE partitions_master_table_cleanup_trigger();
```