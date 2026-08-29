

CREATE TABLE bookingreview
(
    id         BIGINT AUTO_INCREMENT NOT NULL,
    created_at datetime     NOT NULL,
    updated_at datetime     NOT NULL,
    content    VARCHAR(255) NOT NULL,
    rating DOUBLE NULL,
    booking_id BIGINT       NOT NULL,
    CONSTRAINT pk_bookingreview PRIMARY KEY (id)
);

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

CREATE TABLE passanger_review
(
    id                       BIGINT       NOT NULL,
    passanger_review_comment VARCHAR(255) NOT NULL,
    passanger_rating         VARCHAR(255) NOT NULL,
    CONSTRAINT pk_passangerreview PRIMARY KEY (id)
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

ALTER TABLE driver
    ADD aadhar_card VARCHAR(255) NULL;

ALTER TABLE driver
    ADD active_city VARCHAR(255) NULL;

ALTER TABLE driver
    ADD driver_approval_status ENUM('APPROVED', 'PENDING', 'DENIED') NULL;

ALTER TABLE driver
    ADD home_id BIGINT NULL;

ALTER TABLE driver
    ADD last_known_location_id BIGINT NULL;

ALTER TABLE driver
    ADD rating DOUBLE NULL;

ALTER TABLE driver
    MODIFY rating DOUBLE NOT NULL;

ALTER TABLE bookingreview
    ADD CONSTRAINT uc_bookingreview_booking UNIQUE (booking_id);

ALTER TABLE car
    ADD CONSTRAINT uc_car_platenumber UNIQUE (plate_number);

ALTER TABLE bookingreview
    ADD CONSTRAINT FK_BOOKINGREVIEW_ON_BOOKING FOREIGN KEY (booking_id) REFERENCES booking (id);

ALTER TABLE driver
    ADD CONSTRAINT FK_DRIVER_ON_HOME FOREIGN KEY (home_id) REFERENCES exact_location (id);

ALTER TABLE driver
    ADD CONSTRAINT FK_DRIVER_ON_LASTKNOWNLOCATION FOREIGN KEY (last_known_location_id) REFERENCES exact_location (id);

ALTER TABLE named_location
    ADD CONSTRAINT FK_NAMEDLOCATION_ON_EXACTLOCATION FOREIGN KEY (exact_location_id) REFERENCES exact_location (id);

ALTER TABLE passanger_review
    ADD CONSTRAINT FK_PASSANGERREVIEW_ON_ID FOREIGN KEY (id) REFERENCES bookingreview (id);

ALTER TABLE revchanges
    ADD CONSTRAINT fk_revchanges_on_default_tracking_modified_entities_changelog FOREIGN KEY (rev) REFERENCES revinfo (rev);


ALTER TABLE passenger
    MODIFY name VARCHAR (255) NOT NULL;

ALTER TABLE car
    MODIFY plate_number VARCHAR (255) NOT NULL;

ALTER TABLE  driver
    ADD CONSTRAINT  check_rating CHECK ( driver.rating >=0 AND driver.rating <=5 );