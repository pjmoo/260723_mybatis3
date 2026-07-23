CREATE TABLE IF NOT EXISTS STUDENTS (
                                        id   BIGINT AUTO_INCREMENT PRIMARY KEY,
                                        name VARCHAR(50) NOT NULL
    );

CREATE TABLE IF NOT EXISTS COURSES (
                                       id   BIGINT AUTO_INCREMENT PRIMARY KEY,
                                       name VARCHAR(100) NOT NULL
    );