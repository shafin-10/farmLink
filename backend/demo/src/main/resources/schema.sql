CREATE TABLE IF NOT EXISTS users (
  user_id SERIAL PRIMARY KEY,
  name VARCHAR(100) NOT NULL,
  email VARCHAR(50) NOT NULL,
  mobile_number VARCHAR(20) NOT NULL,
  pwd VARCHAR(200) NOT NULL,
  role_id INT NOT NULL,
  address_id INT NULL,
  created_at TIMESTAMP NOT NULL,
  created_by VARCHAR(50) NOT NULL,
  updated_at TIMESTAMP DEFAULT NULL,
  updated_by VARCHAR(50) DEFAULT NULL,
  FOREIGN KEY (role_id) REFERENCES roles(role_id),
);

CREATE TABLE IF NOT EXISTS roles (
  role_id SERIAL PRIMARY KEY,
  role_name VARCHAR(50) NOT NULL,
  created_at TIMESTAMP NOT NULL,
  created_by VARCHAR(50) NOT NULL,
  updated_at TIMESTAMP DEFAULT NULL,
  updated_by VARCHAR(50) DEFAULT NULL
);

ALTER TABLE products
ADD COLUMN farmer_id INT;

ALTER TABLE products
ADD CONSTRAINT fk_products_farmer
FOREIGN KEY (farmer_id)
REFERENCES users(user_id);



