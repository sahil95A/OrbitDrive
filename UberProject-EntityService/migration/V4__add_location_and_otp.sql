

CREATE TABLE exact_location
(
    id         BIGINT AUTO_INCREMENT NOT NULL,
    created_at datetime NOT NULL,
    updated_at datetime NOT NULL,
    latitude DOUBLE NOT NULL,
    longitude DOUBLE NOT NULL,
    CONSTRAINT pk_exactlocation PRIMARY KEY (id)
);

CREATE TABLE named_location
(
    id                BIGINT AUTO_INCREMENT NOT NULL,
    created_at        datetime NOT NULL,
    updated_at        datetime NOT NULL,
    exact_location_id BIGINT NULL,
    namel             VARCHAR(255) NULL,
    zip_code          VARCHAR(255) NULL,
    city              VARCHAR(255) NULL,
    country           VARCHAR(255) NULL,
    state             VARCHAR(255) NULL,
    CONSTRAINT pk_namedlocation PRIMARY KEY (id)
);

CREATE TABLE otp
(
    id             BIGINT AUTO_INCREMENT NOT NULL,
    created_at     datetime NOT NULL,
    updated_at     datetime NOT NULL,
    code           VARCHAR(255) NULL,
    sent_to_number VARCHAR(255) NULL,
    CONSTRAINT pk_otp PRIMARY KEY (id)
);

CREATE TABLE revchanges
(
    rev        BIGINT NOT NULL,
    entityname VARCHAR(255) NULL
);

CREATE TABLE revinfo
(
    rev      BIGINT NOT NULL,
    revtstmp BIGINT NULL,
    CONSTRAINT pk_revinfo PRIMARY KEY (rev)
);

ALTER TABLE car
    ADD CONSTRAINT uc_car_platenumber UNIQUE (plate_number);

ALTER TABLE named_location
    ADD CONSTRAINT FK_NAMEDLOCATION_ON_EXACTLOCATION FOREIGN KEY (exact_location_id) REFERENCES exact_location (id);

ALTER TABLE revchanges
    ADD CONSTRAINT fk_revchanges_on_default_tracking_modified_entities_changelog FOREIGN KEY (rev) REFERENCES revinfo (rev);

