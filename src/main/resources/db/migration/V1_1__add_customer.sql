CREATE TABLE t_customer
(
        id binary(16) NOT NULL,
        name VARCHAR(200),
        detail VARCHAR(2000),
        ver int,
        sts_cd varchar(1),
        created_dt timestamp,
        created_by VARCHAR(200),
        updated_dt timestamp,
        updated_by VARCHAR(200),
        PRIMARY KEY (ID)
)