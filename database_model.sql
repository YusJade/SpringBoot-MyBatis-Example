USE demo;

CREATE TABLE `tb_admin` (
  `id` bigint PRIMARY KEY AUTO_INCREMENT,
  `username` varchar(50) UNIQUE NOT NULL,
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
  `category_id` bigint,
  `publisher` varchar(50),
  `quantity` int,
  `created_at` datetime DEFAULT (now())
);

CREATE TABLE `tb_borrow` (
  `id` bigint PRIMARY KEY AUTO_INCREMENT,
  `user_id` bigint,
  `book_id` bigint,
  `borrow_date` datetime DEFAULT (now()),
  `return_date` datetime,
  `really_return_date` datetime
);

CREATE TABLE `tb_user` (
  `id` bigint PRIMARY KEY AUTO_INCREMENT,
  `username` varchar(50) UNIQUE NOT NULL,
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

# 有借阅记录完成时，更新被借阅书籍的数目
CREATE TRIGGER trg_tb_borrow_upd_book_quantity_u
AFTER UPDATE ON tb_borrow FOR EACH ROW
BEGIN
    IF NEW.return_date IS NOT NULL AND OLD.return_date IS NULL THEN
        UPDATE tb_book SET quantity = quantity + 1 WHERE id = NEW.book_id;
    END IF;
    IF NEW.return_date IS NULL AND OLD.return_date IS NOT NULL THEN
        UPDATE tb_book SET quantity = quantity - 1 WHERE id = NEW.book_id;
    END IF;
end;

# 有借阅记录插入时，更新被借阅书籍的数目
CREATE TRIGGER trg_tb_borrow_upd_book_quantity_i
AFTER INSERT ON tb_borrow FOR EACH ROW
BEGIN
    IF NEW.return_date IS NULL THEN
        UPDATE tb_book SET quantity = quantity - 1 WHERE id = NEW.book_id;
    END IF;
end;

# 当某一图书数量更新时，对应的图书类别中的图书总数需要更新
CREATE TRIGGER trg_tb_book_upd_category_quantity_u
AFTER UPDATE on tb_book FOR EACH ROW
BEGIN
    UPDATE tb_book_category SET quantity = quantity - OLD.quantity + NEW.quantity
    WHERE  tb_book_category.id = NEW.category_id;
end;

# 当有图书信息插入时，对应的图书类别中的图书总数需要更新
CREATE TRIGGER trg_tb_book_upd_category_quantity_i
AFTER INSERT on tb_book FOR EACH ROW
BEGIN
    UPDATE tb_book_category SET quantity = quantity + NEW.quantity
    WHERE  tb_book_category.id = NEW.category_id;
end;

# 当某一图书信息被删除时，对应的图书类别中的图书总数需要更新
CREATE TRIGGER trg_tb_book_upd_category_quantity_d
AFTER DELETE on tb_book FOR EACH ROW
BEGIN
    UPDATE tb_book_category SET quantity = quantity - OLD.quantity
    WHERE  tb_book_category.id = OLD.category_id;
end;