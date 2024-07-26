CREATE EXTENSION IF NOT EXISTS "uuid-ossp";


CREATE TABLE tasks (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    user_id UUID NOT NULL,
    title VARCHAR(255) NOT NULL,
    description TEXT,
    status VARCHAR(20),
    priority VARCHAR(20),
    creation_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    start_date DATE,
    estimated_end_date DATE,
    FOREIGN KEY (user_id) REFERENCES users(user_id)
);
