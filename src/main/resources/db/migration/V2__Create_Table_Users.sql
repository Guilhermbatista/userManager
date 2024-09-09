CREATE TABLE users (
    Id INT AUTO_INCREMENT PRIMARY KEY,
    login TEXT NOT NULL,
    email TEXT NOT NULL,
    password TEXT NOT NULL,
    role TEXT NOT NULL
);
