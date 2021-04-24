CREATE TABLE place (
                       id integer PRIMARY KEY,
                       number integer,
                       name varchar(25),
                       square NUMERIC(4,1),
                       floor integer,
                       type varchar(50)
);


CREATE TABLE meter (
                       id integer PRIMARY KEY,
                       meter_number integer,
                       place_id integer NOT NULL REFERENCES place (id)
);


CREATE TABLE meter_data (
                            id integer PRIMARY KEY,
                            meter_id integer NOT NULL REFERENCES meter(id),
                            data integer NOT NULL,
                            data_date date
);


CREATE TABLE renter (
                        id integer PRIMARY KEY,
                        name varchar(50),
                        ogrn varchar(25),
                        inn varchar(25),
                        registr_date date,
                        address varchar(500),
                        director_name varchar(200),
                        contact_name varchar(200),
                        phone varchar(25)
) ;


CREATE TABLE contract (
                          id integer PRIMARY KEY,
                          number varchar(25),
                          contract_date date,
                          fare integer,
                          start_date date,
                          finish_date date,
                          payment_day integer,
                          place_id integer NOT NULL REFERENCES place (id),
                          renter_id integer NOT NULL REFERENCES renter (id)
);


CREATE TABLE account (
                         id integer PRIMARY KEY,
                         account_number varchar(25),
                         contract_id integer NOT NULL REFERENCES contract (id)
);


CREATE TABLE account_data (
                              id integer PRIMARY KEY,
                              account_id integer NOT NULL REFERENCES account (id),
                              payment integer,
                              payment_date date,
                              payment_purpose varchar(1000)
);