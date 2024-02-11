DROP TABLE IF EXISTS Scan;

DROP TABLE IF EXISTS File;

DROP TABLE IF EXISTS FileScanAssoc;

-- Table Scan
CREATE TABLE Scan (
    id SERIAL PRIMARY KEY,
    file_system_type VARCHAR(255),
    scan_date VARCHAR,
    file_name_filter VARCHAR(255),
    extension_filter VARCHAR(255),
    root_directory_name VARCHAR(255),
    execution_time INTEGER,
    size INTEGER,
    max_files INTEGER,
    max_depth INTEGER
);

-- Table File
CREATE TABLE File (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255),
    parent_folder_name VARCHAR(255),
    file_extension VARCHAR(10),
    modification_date VARCHAR,
    size BIGINT,
    depth INTEGER
);

-- Table FileScanAssoc
CREATE TABLE FileScanAssoc (
    id SERIAL PRIMARY KEY,
    scan_id INTEGER,
    file_id INTEGER,
    FOREIGN KEY (scan_id) REFERENCES Scan(id),
    FOREIGN KEY (file_id) REFERENCES File(id)
);