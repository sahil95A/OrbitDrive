ALTER TABLE booking
DROP
FOREIGN KEY FK_BOOKING_ON_PASSENGER;

ALTER TABLE booking
DROP
FOREIGN KEY FK_BOOKING_ON_REVIEW;

ALTER TABLE passenger_review
DROP
FOREIGN KEY FK_PASSENGERREVIEW_ON_ID;

CREATE TABLE car
(
    id           BIGINT AUTO_INCREMENT NOT NULL,
    created_at   datetime NOT NULL,
    updated_at   datetime NOT NULL,
    plate_number VARCHAR(255) NULL,
    color_id     BIGINT NULL,
    brand        VARCHAR(255) NULL,
    model        VARCHAR(255) NULL,
    car_type     VARCHAR(255) NULL,
    driver_id    BIGINT NULL,
    CONSTRAINT pk_car PRIMARY KEY (id)
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
    ADD CONSTRAINT FK_CAR_ON_COLOR FOREIGN KEY (color_id) REFERENCES color (id);

ALTER TABLE car
    ADD CONSTRAINT FK_CAR_ON_DRIVER FOREIGN KEY (driver_id) REFERENCES driver (id);

ALTER TABLE revchanges
    ADD CONSTRAINT fk_revchanges_on_default_tracking_modified_entities_changelog FOREIGN KEY (rev) REFERENCES revinfo (rev);

DROP TABLE booking_review;

DROP TABLE passenger_review;

ALTER TABLE booking
DROP
COLUMN end_time;

ALTER TABLE booking
DROP
COLUMN passenger_id;

ALTER TABLE booking
DROP
COLUMN review_id;

ALTER TABLE booking
DROP
COLUMN booking_status;

ALTER TABLE booking
    ADD booking_status VARCHAR(255) NULL;

ALTER TABLE passenger
    MODIFY name VARCHAR (255) NOT NULL;