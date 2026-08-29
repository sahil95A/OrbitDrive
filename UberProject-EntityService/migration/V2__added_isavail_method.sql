

ALTER TABLE driver
    ADD is_available BIT(1) NULL;

ALTER TABLE driver
    MODIFY is_available BIT (1) NOT NULL;

select * from flyway_schema_history;