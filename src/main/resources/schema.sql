DROP TABLE IF EXISTS Scan;

DROP TABLE IF EXISTS FileInfo;

CREATE TABLE Scan (
    scan_id SERIAL PRIMARY KEY,
    max_number_of_files INTEGER,
    max_depth INTEGER,
    file_name_filter VARCHAR(255),
    file_type_filter VARCHAR(255),
    scan_date TIMESTAMP,
    execution_time BIGINT
);

CREATE TABLE FileInfo (
    file_info_id SERIAL PRIMARY KEY,
    scan_id INTEGER REFERENCES Scan(scan_id),
    file_name VARCHAR(255),
    modification_date TIMESTAMP,
    file_size BIGINT,
    file_type VARCHAR(50),
    containing_directory VARCHAR(255)
);