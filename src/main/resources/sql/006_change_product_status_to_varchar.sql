ALTER TABLE products
ALTER COLUMN status DROP DEFAULT;

ALTER TABLE products
ALTER COLUMN status TYPE VARCHAR(20)
USING status::text;

ALTER TABLE products
ALTER COLUMN status SET DEFAULT 'ACTIVE';

ALTER TABLE products
ADD CONSTRAINT chk_products_status
CHECK (status IN ('ACTIVE', 'INACTIVE'));

DROP TYPE product_status;