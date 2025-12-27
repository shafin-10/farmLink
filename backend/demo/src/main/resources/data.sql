INSERT INTO products (
    name, description, price, stock, category,
    harvest_date, status, created_at, created_by
) VALUES
('Fresh Tomato',
 'Organic red tomatoes directly from farm',
 45.50,
 120,
 'Vegetable',
 '2025-06-10',
 'ACTIVE',
 NOW(),
 'farmer_1'),

('Basmati Rice',
 'Premium long grain basmati rice',
 95.00,
 300,
 'Grain',
 '2025-05-20',
 'ACTIVE',
 NOW(),
 'farmer_2');

INSERT INTO products (
    name, description, price, stock, category,
    harvest_date, status, created_at, created_by
) VALUES
('Potato',
 'Fresh farm potatoes',
 30.00,
 0,
 'Vegetable',
 '2025-04-15',
 'OUT_OF_STOCK',
 NOW(),
 'farmer_1');


INSERT INTO products (
    name, description, price, stock, category,
    harvest_date, status, created_at, created_by
) VALUES
('Old Wheat Batch',
 'Previous season harvested wheat',
 60.00,
 0,
 'Grain',
 '2024-11-10',
 'ARCHIVED',
 NOW(),
 'farmer_2');


 INSERT INTO roles (role_name, created_at, created_by)
 VALUES ('USER', NOW(), 'DBA');

 INSERT INTO roles (role_name, created_at, created_by)
 VALUES ('ADMIN', NOW(), 'DBA');

 INSERT INTO roles (role_name, created_at, created_by)
 VALUES ('FARMER', NOW(), 'DBA');



