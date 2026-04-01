UPDATE products
SET image_url = 'https://example.com/images/' || image_url
WHERE image_url IS NOT NULL;