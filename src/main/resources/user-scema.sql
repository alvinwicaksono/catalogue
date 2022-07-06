DROP TABLE IF EXISTS ms_user;

CREATE TABLE ms_user(
    id SERIAL PRIMARY KEY NOT NULL,
    fullname VARCHAR(50) NOT NULL,
    role_id VARCHAR(5) NOT NULL,
    call_number VARCHAR(15),
    created_date TIMESTAMP NOT NULL,
    creator_id INT NOT NULL,
    updated_date TIMESTAMP NULL,
    updater_id INT NULL,
    deleted_date TIMESTAMP NULL,
    deleter_id INT NULL,
    rec_status VARCHAR(1) NULL DEFAULT 'N'::VARCHAR
    ) WITH (OIDS=FALSE);

    SELECT * FROM ms_user;
