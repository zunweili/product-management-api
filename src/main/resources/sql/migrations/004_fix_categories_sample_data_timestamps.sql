UPDATE categories
SET
    created_at = CASE category_name
        WHEN '文學小說' THEN '2025-08-12 10:18:25+08'::timestamptz
        WHEN '商業理財' THEN '2025-09-27 14:36:42+08'::timestamptz
        WHEN '程式設計' THEN '2025-11-15 19:24:08+08'::timestamptz
    END,
    updated_at = CASE category_name
        WHEN '文學小說' THEN '2025-09-03 16:45:11+08'::timestamptz
        WHEN '商業理財' THEN '2025-10-21 09:12:57+08'::timestamptz
        WHEN '程式設計' THEN '2025-12-04 21:08:33+08'::timestamptz
    END
WHERE category_name IN (
    '文學小說',
    '商業理財',
    '程式設計'
);