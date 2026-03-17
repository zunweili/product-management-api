INSERT INTO categories (category_name) VALUES
('文學小說'),
('商業理財'),
('程式設計');

INSERT INTO products (product_name, category_id, price, image_url, description, status, stock) VALUES
('解憂雜貨店', 1, 320, 'namiya.jpg', '一家神祕雜貨店，串起不同人生的溫暖故事。', 'ACTIVE', 12),
('嫌疑犯X的獻身', 1, 340, 'suspect_x.jpg', '充滿數學思維與情感張力的推理小說。', 'ACTIVE', 8),
('挪威的森林', 1, 360, 'norwegian_wood.jpg', '描寫青春、孤獨與愛情的經典文學作品。', 'ACTIVE', 10),
('人間失格', 1, 300, 'no_longer_human.jpg', '探討自我認同與人生疏離感的日本名作。', 'ACTIVE', 6),
('小王子', 1, 250, 'little_prince.jpg', '以童話形式呈現人生與愛的重要寓意。', 'ACTIVE', 15),
('追風箏的孩子', 1, 380, 'kite_runner.jpg', '關於友情、背叛與救贖的感人小說。', 'INACTIVE', 3),

('被討厭的勇氣', 2, 330, 'courage_to_be_disliked.jpg', '用對話方式介紹阿德勒心理學的核心觀念。', 'ACTIVE', 14),
('原子習慣', 2, 420, 'atomic_habits.jpg', '從微小習慣開始，逐步打造長期改變。', 'ACTIVE', 20),
('富爸爸窮爸爸', 2, 350, 'rich_dad_poor_dad.jpg', '用不同金錢觀點談理財與資產累積。', 'ACTIVE', 9),
('習慣致富', 2, 310, 'wealth_habits.jpg', '整理成功人士常見的金錢與生活習慣。', 'ACTIVE', 7),
('一如既往', 2, 390, 'same_as_ever.jpg', '從人性與行為模式理解財富與決策。', 'ACTIVE', 11),
('灰階思考', 2, 340, 'gray_thinking.jpg', '在複雜世界中培養更有彈性的判斷方式。', 'INACTIVE', 4),
('Deep Work 深度工作力', 2, 400, 'deep_work.jpg', '探討如何在分心時代培養高價值專注力。', 'ACTIVE', 5),

('Java SE 17 技術手冊', 3, 680, 'java17.jpg', '涵蓋 Java SE 17 核心語法與重要觀念。', 'ACTIVE', 13),
('Java 核心技術 I', 3, 720, 'core_java_1.jpg', '學習 Java 基礎與物件導向設計的經典教材。', 'ACTIVE', 4),
('Spring Boot 實戰入門', 3, 560, 'spring_boot_intro.jpg', '快速建立 REST API 與後端應用的入門書。', 'ACTIVE', 16),
('SQL 必知必會', 3, 420, 'sql_fundamentals.jpg', '用清楚範例掌握 SQL 常用查詢與資料操作。', 'ACTIVE', 18),
('演算法圖鑑', 3, 500, 'algorithm_visual.jpg', '用圖解方式理解常見演算法與資料結構。', 'ACTIVE', 6),
('Docker 入門實戰', 3, 450, 'docker_practice.jpg', '學習容器化觀念與基本操作流程。', 'INACTIVE', 2),
('Clean Code 無瑕的程式碼', 3, 620, 'clean_code.jpg', '介紹提升程式可讀性與可維護性的實務原則。', 'ACTIVE', 9);