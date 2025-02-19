CREATE TABLE orders (
                          id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          product_id BIGINT NOT NULL,
                          quantity BIGINT NOT NULL,
                          status VARCHAR(50) NOT NULL DEFAULT 'ORDERED',
                          created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
                          updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);