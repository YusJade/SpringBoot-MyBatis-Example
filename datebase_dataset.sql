USE demo;

INSERT INTO `tb_book_category` (`name`, `quantity`)
VALUES
('Classic Literature', 0),
('Dystopian', 0),
('Historical Fiction', 0),
('Modern Classics', 0),
('Fantasy', 0),
('Science Fiction', 0),
('Russian Literature', 0),
('British Literature', 0),
('American Literature', 0);


INSERT INTO `tb_book` (`title`, `author`, `category_id`, `publisher`, `quantity`)
VALUES
('Pride and Prejudice', 'Jane Austen', 1, 'Publisher A', 10),
('1984', 'George Orwell', 2, 'Publisher B', 15),
('To Kill a Mockingbird', 'Harper Lee', 3, 'Publisher C', 20),
('The Great Gatsby', 'F. Scott Fitzgerald', 4, 'Publisher D', 25),
('Moby Dick', 'Herman Melville', 1, 'Publisher E', 30),
('War and Peace', 'Leo Tolstoy', 7, 'Publisher F', 35),
('Ulysses', 'James Joyce', 4, 'Publisher G', 40),
('The Odyssey', 'Homer', 1, 'Publisher H', 45),
('Madame Bovary', 'Gustave Flaubert', 1, 'Publisher I', 50),
('The Divine Comedy', 'Dante Alighieri', 1, 'Publisher J', 55),
('Crime and Punishment', 'Fyodor Dostoevsky', 7, 'Publisher K', 60),
('Wuthering Heights', 'Emily Bronte', 8, 'Publisher L', 65),
('The Brothers Karamazov', 'Fyodor Dostoevsky', 7, 'Publisher M', 70),
('Jane Eyre', 'Charlotte Bronte', 8, 'Publisher N', 75),
('Brave New World', 'Aldous Huxley', 2, 'Publisher O', 80),
('The Catcher in the Rye', 'J.D. Salinger', 4, 'Publisher P', 85),
('The Hobbit', 'J.R.R. Tolkien', 5, 'Publisher Q', 90),
('Fahrenheit 451', 'Ray Bradbury', 2, 'Publisher R', 95),
('Anna Karenina', 'Leo Tolstoy', 7, 'Publisher S', 100),
('Don Quixote', 'Miguel de Cervantes', 1, 'Publisher T', 105),
('One Hundred Years of Solitude', 'Gabriel Garcia Marquez', 3, 'Publisher U', 110),
('The Sound and the Fury', 'William Faulkner', 4, 'Publisher V', 115),
('Catch-22', 'Joseph Heller', 4, 'Publisher W', 120),
('The Grapes of Wrath', 'John Steinbeck', 9, 'Publisher X', 125),
('Lord of the Flies', 'William Golding', 4, 'Publisher Y', 130),
('Slaughterhouse-Five', 'Kurt Vonnegut', 6, 'Publisher Z', 135),
('Great Expectations', 'Charles Dickens', 8, 'Publisher A', 140),
('The Old Man and the Sea', 'Ernest Hemingway', 9, 'Publisher B', 145),
('Lolita', 'Vladimir Nabokov', 4, 'Publisher C', 150),
('The Scarlet Letter', 'Nathaniel Hawthorne', 9, 'Publisher D', 155);

INSERT INTO `tb_user` (`username`, `password`, `name`, `gender`, `phone`, `email`, `max_borrow_days`, `max_borrow_books`)
VALUES
('alice1', 'password123', 'Alice Johnson', '女', '555-1234', 'alice.johnson@example.com', 30, 5),
('bob1', 'password123', 'Bob Smith', '男', '555-1235', 'bob.smith@example.com', 30, 5),
('charlie1', 'password123', 'Charlie Brown', '男', '555-1236', 'charlie.brown@example.com', 30, 5),
('david1', 'password123', 'David Wilson', '男', '555-1237', 'david.wilson@example.com', 30, 5),
('eve1', 'password123', 'Eve Davis', '女', '555-1238', 'eve.davis@example.com', 30, 5),
('frank1', 'password123', 'Frank Miller', '男', '555-1239', 'frank.miller@example.com', 30, 5),
('grace1', 'password123', 'Grace Lee', '女', '555-1240', 'grace.lee@example.com', 30, 5),
('hannah1', 'password123', 'Hannah Martin', '女', '555-1241', 'hannah.martin@example.com', 30, 5),
('isaac1', 'password123', 'Isaac Clark', '男', '555-1242', 'isaac.clark@example.com', 30, 5),
('julia1', 'password123', 'Julia Lewis', '女', '555-1243', 'julia.lewis@example.com', 30, 5),
('kevin1', 'password123', 'Kevin Walker', '男', '555-1244', 'kevin.walker@example.com', 30, 5),
('laura1', 'password123', 'Laura Hall', '女', '555-1245', 'laura.hall@example.com', 30, 5),
('mike1', 'password123', 'Mike Young', '男', '555-1246', 'mike.young@example.com', 30, 5),
('nina1', 'password123', 'Nina King', '女', '555-1247', 'nina.king@example.com', 30, 5),
('oliver1', 'password123', 'Oliver Wright', '男', '555-1248', 'oliver.wright@example.com', 30, 5),
('paula1', 'password123', 'Paula Scott', '女', '555-1249', 'paula.scott@example.com', 30, 5),
('quentin1', 'password123', 'Quentin Green', '男', '555-1250', 'quentin.green@example.com', 30, 5),
('rachel1', 'password123', 'Rachel Adams', '女', '555-1251', 'rachel.adams@example.com', 30, 5),
('steven1', 'password123', 'Steven Baker', '男', '555-1252', 'steven.baker@example.com', 30, 5),
('tina1', 'password123', 'Tina Nelson', '女', '555-1253', 'tina.nelson@example.com', 30, 5),
('ursula1', 'password123', 'Ursula Carter', '女', '555-1254', 'ursula.carter@example.com', 30, 5),
('victor1', 'password123', 'Victor Mitchell', '男', '555-1255', 'victor.mitchell@example.com', 30, 5),
('wendy1', 'password123', 'Wendy Perez', '女', '555-1256', 'wendy.perez@example.com', 30, 5),
('xander1', 'password123', 'Xander Roberts', '男', '555-1257', 'xander.roberts@example.com', 30, 5),
('yvonne1', 'password123', 'Yvonne Turner', '女', '555-1258', 'yvonne.turner@example.com', 30, 5),
('zach1', 'password123', 'Zachary Collins', '男', '555-1259', 'zachary.collins@example.com', 30, 5),
('amber1', 'password123', 'Amber Rogers', '女', '555-1260', 'amber.rogers@example.com', 30, 5),
('brandon1', 'password123', 'Brandon Russell', '男', '555-1261', 'brandon.russell@example.com', 30, 5),
('cynthia1', 'password123', 'Cynthia Griffin', '女', '555-1262', 'cynthia.griffin@example.com', 30, 5),
('daniel1', 'password123', 'Daniel Diaz', '男', '555-1263', 'daniel.diaz@example.com', 30, 5),
('elizabeth1', 'password123', 'Elizabeth Hayes', '女', '555-1264', 'elizabeth.hayes@example.com', 30, 5),
('fiona1', 'password123', 'Fiona Butler', '女', '555-1265', 'fiona.butler@example.com', 30, 5),
('george1', 'password123', 'George Barnes', '男', '555-1266', 'george.barnes@example.com', 30, 5),
('harper1', 'password123', 'Harper Fisher', '女', '555-1267', 'harper.fisher@example.com', 30, 5),
('ian1', 'password123', 'Ian Powell', '男', '555-1268', 'ian.powell@example.com', 30, 5),
('jasmine1', 'password123', 'Jasmine Patterson', '女', '555-1269', 'jasmine.patterson@example.com', 30, 5),
('kyle1', 'password123', 'Kyle Hughes', '男', '555-1270', 'kyle.hughes@example.com', 30, 5),
('lily1', 'password123', 'Lily Simmons', '女', '555-1271', 'lily.simmons@example.com', 30, 5),
('matt1', 'password123', 'Matt Foster', '男', '555-1272', 'matt.foster@example.com', 30, 5),
('nicole1', 'password123', 'Nicole Henderson', '女', '555-1273', 'nicole.henderson@example.com', 30, 5),
('oscar1', 'password123', 'Oscar Alexander', '男', '555-1274', 'oscar.alexander@example.com', 30, 5),
('pamela1', 'password123', 'Pamela Reynolds', '女', '555-1275', 'pamela.reynolds@example.com', 30, 5),
('quentin2', 'password123', 'Quentin Hamilton', '男', '555-1276', 'quentin.hamilton@example.com', 30, 5),
('rebecca1', 'password123', 'Rebecca Graham', '女', '555-1277', 'rebecca.graham@example.com', 30, 5),
('sam1', 'password123', 'Sam Wallace', '男', '555-1278', 'sam.wallace@example.com', 30, 5),
('taylor1', 'password123', 'Taylor Woods', '女', '555-1279', 'taylor.woods@example.com', 30, 5),
('uma1', 'password123', 'Uma Cole', '女', '555-1280', 'uma.cole@example.com', 30, 5),
('vincent1', 'password123', 'Vincent West', '男', '555-1281', 'vincent.west@example.com', 30, 5),
('willow1', 'password123', 'Willow Bennett', '女', '555-1282', 'willow.bennett@example.com', 30, 5),
('xavier1', 'password123', 'Xavier Bell', '男', '555-1283', 'xavier.bell@example.com', 30, 5),
('yolanda1', 'password123', 'Yolanda Reed', '女', '555-1284', 'yolanda.reed@example.com', 30, 5),
('zane1', 'password123', 'Zane Cook', '男', '555-1285', 'zane.cook@example.com', 30, 5);



