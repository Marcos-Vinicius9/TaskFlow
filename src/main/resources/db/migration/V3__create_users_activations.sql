CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE user_activations (
    activation_token UUID PRIMARY KEY,
    user_id UUID REFERENCES users(user_id) ON DELETE CASCADE,
    expiration_date TIMESTAMP NOT NULL
);
