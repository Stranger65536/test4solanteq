CREATE USER c##prod IDENTIFIED BY Riephiepaex4;
 
GRANT CREATE SESSION,
UNLIMITED TABLESPACE
TO c##prod;
 
CREATE TABLE c##prod.positions (
  position_id RAW(32) DEFAULT SYS_GUID() NOT NULL,
  name        NVARCHAR2(255)             NOT NULL,
  CONSTRAINT position_pk PRIMARY KEY (position_id)
);
 
CREATE TABLE c##prod.users (
  user_id     RAW(32) DEFAULT SYS_GUID() NOT NULL,
  first_name  NVARCHAR2(255)             NOT NULL,
  last_name   NVARCHAR2(255)             NOT NULL,
  middle_name NVARCHAR2(255)             NULL,
  birth_date  DATE                       NOT NULL,
  position_id RAW(32) DEFAULT SYS_GUID() NOT NULL,
  CONSTRAINT user_pk PRIMARY KEY (user_id),
  CONSTRAINT user_position FOREIGN KEY (position_id) REFERENCES c##prod.positions (position_id)
);
 
CREATE INDEX c##prod.index_users ON c##prod.users (
  first_name,
  last_name,
  middle_name,
  birth_date,
  position_id
);
 
GRANT SELECT ON c##prod.users TO c##prod;
 
COMMIT;