UPDATE products
SET
    created_at = v.created_at,
    updated_at = v.updated_at
FROM (
    VALUES
        (1,  '2026-01-05 09:12:15+08'::timestamptz, '2026-01-05 09:12:15+08'::timestamptz),
        (2,  '2026-01-07 14:25:33+08'::timestamptz, '2026-01-07 14:25:33+08'::timestamptz),
        (3,  '2026-01-10 20:41:08+08'::timestamptz, '2026-01-10 20:41:08+08'::timestamptz),
        (4,  '2026-01-13 11:03:57+08'::timestamptz, '2026-01-13 11:03:57+08'::timestamptz),
        (5,  '2026-01-16 16:48:21+08'::timestamptz, '2026-01-16 16:48:21+08'::timestamptz),
        (6,  '2026-01-19 08:19:44+08'::timestamptz, '2026-01-19 08:19:44+08'::timestamptz),
        (7,  '2026-01-22 13:37:12+08'::timestamptz, '2026-01-22 13:37:12+08'::timestamptz),
        (8,  '2026-01-25 19:56:05+08'::timestamptz, '2026-01-25 19:56:05+08'::timestamptz),
        (9,  '2026-01-28 10:27:49+08'::timestamptz, '2026-01-28 10:27:49+08'::timestamptz),
        (10, '2026-02-01 15:14:26+08'::timestamptz, '2026-02-01 15:14:26+08'::timestamptz),
        (11, '2026-02-04 21:32:18+08'::timestamptz, '2026-02-04 21:32:18+08'::timestamptz),
        (12, '2026-02-08 09:45:51+08'::timestamptz, '2026-02-08 09:45:51+08'::timestamptz),
        (13, '2026-02-11 17:08:39+08'::timestamptz, '2026-02-11 17:08:39+08'::timestamptz),
        (14, '2026-02-15 12:22:04+08'::timestamptz, '2026-02-15 12:22:04+08'::timestamptz),
        (15, '2026-02-18 18:11:47+08'::timestamptz, '2026-02-18 18:11:47+08'::timestamptz),
        (16, '2026-02-22 07:54:30+08'::timestamptz, '2026-02-22 07:54:30+08'::timestamptz),
        (17, '2026-02-25 14:39:16+08'::timestamptz, '2026-02-25 14:39:16+08'::timestamptz),
        (18, '2026-03-01 20:05:42+08'::timestamptz, '2026-03-01 20:05:42+08'::timestamptz),
        (19, '2026-03-05 11:28:09+08'::timestamptz, '2026-03-05 11:28:09+08'::timestamptz),
        (20, '2026-03-09 16:53:55+08'::timestamptz, '2026-03-09 16:53:55+08'::timestamptz)
) AS v(product_id, created_at, updated_at)
WHERE products.product_id = v.product_id;