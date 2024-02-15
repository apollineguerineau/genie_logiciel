DROP TABLE IF EXISTS Scan;

DROP TABLE IF EXISTS File;

DROP TABLE IF EXISTS FileScanAssoc;

-- Table Scan
CREATE TABLE Scan (
    scan_id SERIAL PRIMARY KEY,
    file_system_type VARCHAR(255),
    scan_date VARCHAR,
    file_name_filter VARCHAR(255),
    extension_filter VARCHAR(255),
    root_directory_name VARCHAR(255),
    execution_time FLOAT,
    size INTEGER,
    max_files INTEGER,
    max_depth INTEGER
);

-- Table File
CREATE TABLE File (
    file_id SERIAL PRIMARY KEY,
    name VARCHAR(255),
    parent_folder_name VARCHAR(255),
    file_extension VARCHAR(10),
    modification_date VARCHAR,
    size BIGINT,
    depth INTEGER
);

CREATE TABLE FileScanAssoc (
  `scan_id` int NOT NULL,
  `file_id` int NOT NULL,
  PRIMARY KEY (`scan_id`,`file_id`),
  KEY `produitid_idx` (`produitid`),
  CONSTRAINT `scan_id` FOREIGN KEY (`scan_id`) REFERENCES `Scan` (`scan_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `file_id` FOREIGN KEY (`file_id`) REFERENCES `File` (`file_id`) ON DELETE CASCADE ON UPDATE CASCADE
);
