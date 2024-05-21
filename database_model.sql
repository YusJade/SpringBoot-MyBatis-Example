CREATE TABLE `tb_admin` (
  `id` bigint PRIMARY KEY AUTO_INCREMENT,
  `username` varchar(50) UNIQUE,
  `password` varchar(50) NOT NULL,
  `name` varchar(50) NOT NULL,
  `phone` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `created_at` datetime DEFAULT (now())
);

CREATE TABLE `tb_book_category` (
  `id` bigint PRIMARY KEY AUTO_INCREMENT,
  `name` varchar(50) UNIQUE,
  `quantity` int,
  `created_at` datetime DEFAULT (now())
);

CREATE TABLE `tb_book` (
  `id` bigint PRIMARY KEY AUTO_INCREMENT,
  `title` varchar(50),
  `author` varchar(50),
  `category_id` int,
  `publisher` varchar(50),
  `quantity` int,
  `created_at` datetime DEFAULT (now())
);

CREATE TABLE `tb_borrow` (
  `id` bigint PRIMARY KEY AUTO_INCREMENT,
  `user_id` int,
  `book_id` int,
  `borrow_date` datetime DEFAULT (now()),
  `return_date` datetime,
  `really_return_date` datetime
);

CREATE TABLE `tb_user` (
  `id` bigint PRIMARY KEY AUTO_INCREMENT,
  `username` varchar(50) UNIQUE,
  `password` varchar(50) NOT NULL,
  `name` varchar(50) NOT NULL,
  `gender` varchar(50) DEFAULT '男',
  `phone` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `max_borrow_days` int DEFAULT 30,
  `max_borrow_books` int DEFAULT 5,
  `created_at` datetime DEFAULT (now())
);

ALTER TABLE `tb_book` ADD FOREIGN KEY (`category_id`) REFERENCES `tb_book_category` (`id`);

ALTER TABLE `tb_borrow` ADD FOREIGN KEY (`user_id`) REFERENCES `tb_user` (`id`);

ALTER TABLE `tb_borrow` ADD FOREIGN KEY (`book_id`) REFERENCES `tb_book` (`id`);
