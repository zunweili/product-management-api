INSERT INTO categories (category_name, created_at, updated_at) VALUES
('文學小說', '2025-08-12 10:18:25+08'::timestamptz, '2025-09-03 16:45:11+08'::timestamptz),
('商業理財', '2025-09-27 14:36:42+08'::timestamptz, '2025-10-21 09:12:57+08'::timestamptz),
('程式設計', '2025-11-15 19:24:08+08'::timestamptz, '2025-12-04 21:08:33+08'::timestamptz),
('歷史人文', '2025-11-03 09:15:22+08'::timestamptz, '2025-11-21 18:40:11+08'::timestamptz),
('心理勵志', '2025-12-18 14:27:35+08'::timestamptz, '2026-01-05 09:12:44+08'::timestamptz),
('語言學習', '2025-10-26 20:08:14+08'::timestamptz, '2025-11-30 15:26:03+08'::timestamptz),
('童書繪本', '2026-01-07 11:42:09+08'::timestamptz, '2026-02-02 21:14:55+08'::timestamptz),
('藝術設計', '2025-09-14 16:33:48+08'::timestamptz, '2025-10-19 12:48:26+08'::timestamptz),
('旅遊生活', '2025-08-29 08:21:56+08'::timestamptz, '2025-09-16 07:33:10+08'::timestamptz),
('飲食文化', '2025-12-02 19:17:41+08'::timestamptz, '2025-12-27 20:05:49+08'::timestamptz),
('漫畫輕小說', '2026-02-11 13:05:27+08'::timestamptz, '2026-03-01 16:22:31+08'::timestamptz),
('社會科學', '2025-10-08 10:54:33+08'::timestamptz, '2025-11-14 11:09:58+08'::timestamptz),
('醫療保健', '2026-01-23 17:39:12+08'::timestamptz, '2026-02-09 22:47:06+08'::timestamptz);

INSERT INTO products (
    product_name,
    category_id,
    price,
    image_url,
    description,
    status,
    stock,
    created_at,
    updated_at
) VALUES
('解憂雜貨店', 1, 320, 'https://example.com/images/namiya.jpg', '一家神祕雜貨店，串起不同人生的溫暖故事。', 'ACTIVE', 12, '2026-01-05 09:12:15+08'::timestamptz, '2026-01-05 09:12:15+08'::timestamptz),
('嫌疑犯X的獻身', 1, 340, 'https://example.com/images/suspect_x.jpg', '充滿數學思維與情感張力的推理小說。', 'ACTIVE', 8, '2026-01-07 14:25:33+08'::timestamptz, '2026-01-07 14:25:33+08'::timestamptz),
('挪威的森林', 1, 360, 'https://example.com/images/norwegian_wood.jpg', '描寫青春、孤獨與愛情的經典文學作品。', 'ACTIVE', 10, '2026-01-10 20:41:08+08'::timestamptz, '2026-01-10 20:41:08+08'::timestamptz),
('人間失格', 1, 300, 'https://example.com/images/no_longer_human.jpg', '探討自我認同與人生疏離感的日本名作。', 'ACTIVE', 6, '2026-01-13 11:03:57+08'::timestamptz, '2026-01-13 11:03:57+08'::timestamptz),
('小王子', 1, 250, 'https://example.com/images/little_prince.jpg', '以童話形式呈現人生與愛的重要寓意。', 'ACTIVE', 15, '2026-01-16 16:48:21+08'::timestamptz, '2026-01-16 16:48:21+08'::timestamptz),
('追風箏的孩子', 1, 380, 'https://example.com/images/kite_runner.jpg', '關於友情、背叛與救贖的感人小說。', 'INACTIVE', 3, '2026-01-19 08:19:44+08'::timestamptz, '2026-01-19 08:19:44+08'::timestamptz),

('被討厭的勇氣', 2, 330, 'https://example.com/images/courage_to_be_disliked.jpg', '用對話方式介紹阿德勒心理學的核心觀念。', 'ACTIVE', 14, '2026-01-22 13:37:12+08'::timestamptz, '2026-01-22 13:37:12+08'::timestamptz),
('原子習慣', 2, 420, 'https://example.com/images/atomic_habits.jpg', '從微小習慣開始，逐步打造長期改變。', 'ACTIVE', 20, '2026-01-25 19:56:05+08'::timestamptz, '2026-01-25 19:56:05+08'::timestamptz),
('富爸爸窮爸爸', 2, 350, 'https://example.com/images/rich_dad_poor_dad.jpg', '用不同金錢觀點談理財與資產累積。', 'ACTIVE', 9, '2026-01-28 10:27:49+08'::timestamptz, '2026-01-28 10:27:49+08'::timestamptz),
('習慣致富', 2, 310, 'https://example.com/images/wealth_habits.jpg', '整理成功人士常見的金錢與生活習慣。', 'ACTIVE', 7, '2026-02-01 15:14:26+08'::timestamptz, '2026-02-01 15:14:26+08'::timestamptz),
('一如既往', 2, 390, 'https://example.com/images/same_as_ever.jpg', '從人性與行為模式理解財富與決策。', 'ACTIVE', 11, '2026-02-04 21:32:18+08'::timestamptz, '2026-02-04 21:32:18+08'::timestamptz),
('灰階思考', 2, 340, 'https://example.com/images/gray_thinking.jpg', '在複雜世界中培養更有彈性的判斷方式。', 'INACTIVE', 4, '2026-02-08 09:45:51+08'::timestamptz, '2026-02-08 09:45:51+08'::timestamptz),
('Deep Work 深度工作力', 2, 400, 'https://example.com/images/deep_work.jpg', '探討如何在分心時代培養高價值專注力。', 'ACTIVE', 5, '2026-02-11 17:08:39+08'::timestamptz, '2026-02-11 17:08:39+08'::timestamptz),

('Java SE 17 技術手冊', 3, 680, 'https://example.com/images/java17.jpg', '涵蓋 Java SE 17 核心語法與重要觀念。', 'ACTIVE', 13, '2026-02-15 12:22:04+08'::timestamptz, '2026-02-15 12:22:04+08'::timestamptz),
('Java 核心技術 I', 3, 720, 'https://example.com/images/core_java_1.jpg', '學習 Java 基礎與物件導向設計的經典教材。', 'ACTIVE', 4, '2026-02-18 18:11:47+08'::timestamptz, '2026-02-18 18:11:47+08'::timestamptz),
('Spring Boot 實戰入門', 3, 560, 'https://example.com/images/spring_boot_intro.jpg', '快速建立 REST API 與後端應用的入門書。', 'ACTIVE', 16, '2026-02-22 07:54:30+08'::timestamptz, '2026-02-22 07:54:30+08'::timestamptz),
('SQL 必知必會', 3, 420, 'https://example.com/images/sql_fundamentals.jpg', '用清楚範例掌握 SQL 常用查詢與資料操作。', 'ACTIVE', 18, '2026-02-25 14:39:16+08'::timestamptz, '2026-02-25 14:39:16+08'::timestamptz),
('演算法圖鑑', 3, 500, 'https://example.com/images/algorithm_visual.jpg', '用圖解方式理解常見演算法與資料結構。', 'ACTIVE', 6, '2026-03-01 20:05:42+08'::timestamptz, '2026-03-01 20:05:42+08'::timestamptz),
('Docker 入門實戰', 3, 450, 'https://example.com/images/docker_practice.jpg', '學習容器化觀念與基本操作流程。', 'INACTIVE', 2, '2026-03-05 11:28:09+08'::timestamptz, '2026-03-05 11:28:09+08'::timestamptz),
('Clean Code 無瑕的程式碼', 3, 620, 'https://example.com/images/clean_code.jpg', '介紹提升程式可讀性與可維護性的實務原則。', 'ACTIVE', 9, '2026-03-09 16:53:55+08'::timestamptz, '2026-03-09 16:53:55+08'::timestamptz);