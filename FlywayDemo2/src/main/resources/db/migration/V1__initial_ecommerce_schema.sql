CREATE TYPE product_category AS ENUM (
    'ELECTRONICS',
    'CLOTHING',
    'HOME_AND_KITCHEN',
    'BEAUTY_AND_PERSONAL_CARE',
    'BOOKS_AND_STATIONERY',
    'SPORTS_AND_OUTDOORS',
    'HEALTH_AND_WELLNESS',
    'TOYS_AND_GAMES',
    'GROCERIES_AND_FOOD',
    'JEWELRY_AND_ACCESSORIES'
    );

CREATE TYPE payment_status AS ENUM (
    'PENDING',
    'SUCCESS',
    'FAILED',
    'REFUNDED'
    );


CREATE TABLE customers (
                           id            SERIAL PRIMARY KEY,
                           name          VARCHAR(100) NOT NULL,
                           email         VARCHAR(150) NOT NULL,
                           password_hash VARCHAR(255) NOT NULL,
                           CONSTRAINT uq_customers_email UNIQUE (email)
);

CREATE TABLE products (
                          product_id     SERIAL PRIMARY KEY,
                          name           VARCHAR(150) NOT NULL,
                          price          NUMERIC(10,2) NOT NULL,
                          stock_quantity INT NOT NULL DEFAULT 0,
                          category       product_category NOT NULL DEFAULT 'ELECTRONICS',
                          CONSTRAINT chk_products_price CHECK (price >= 0),
                          CONSTRAINT chk_products_stock CHECK (stock_quantity >= 0)
);

CREATE TABLE orders (
                        id          SERIAL PRIMARY KEY,
                        customer_id INT NOT NULL,
                        status      VARCHAR(20) NOT NULL DEFAULT 'PENDING',
                        created_at  TIMESTAMP NOT NULL DEFAULT NOW(),
                        CONSTRAINT fk_orders_customer
                            FOREIGN KEY (customer_id) REFERENCES customers (id) ON DELETE RESTRICT,
                        CONSTRAINT chk_orders_status
                            CHECK (status IN ('PENDING', 'PROCESSING', 'COMPLETED', 'CANCELLED'))
);

CREATE TABLE order_items (
                             id                SERIAL PRIMARY KEY,
                             order_id          INT NOT NULL,
                             product_id        INT NOT NULL,
                             quantity          INT NOT NULL,
                             price_at_purchase NUMERIC(10,2) NOT NULL,
                             CONSTRAINT fk_order_items_order
                                 FOREIGN KEY (order_id) REFERENCES orders (id) ON DELETE CASCADE,
                             CONSTRAINT fk_order_items_product
                                 FOREIGN KEY (product_id) REFERENCES products (product_id) ON DELETE RESTRICT,
                             CONSTRAINT chk_order_items_quantity CHECK (quantity > 0),
                             CONSTRAINT chk_order_items_price CHECK (price_at_purchase >= 0)
);

CREATE TABLE payments (
                          id             SERIAL PRIMARY KEY,
                          order_id       INT NOT NULL,
                          amount         NUMERIC(10,2) NOT NULL,
                          payment_status payment_status NOT NULL DEFAULT 'PENDING',
                          CONSTRAINT fk_payments_order
                              FOREIGN KEY (order_id) REFERENCES orders (id) ON DELETE RESTRICT,
                          CONSTRAINT chk_payments_amount CHECK (amount >= 0)
);


CREATE INDEX idx_orders_customer_id ON orders (customer_id);
CREATE INDEX idx_order_items_order_id ON order_items (order_id);
CREATE INDEX idx_order_items_product_id ON order_items (product_id);
CREATE INDEX idx_payments_order_id ON payments (order_id);
CREATE INDEX idx_products_category ON products (category);