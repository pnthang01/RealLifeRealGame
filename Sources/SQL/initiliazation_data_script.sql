USE `data`;

INSERT INTO `category` VALUES
	(1, '5k23e7zB', 1, 1, 'Power'),
	(2, 'HUesJMM6', 2, 1, 'Intelligent'),
	(3, 'WpxxS9ZS', 3, 1, 'Communication'),
	(4, 'wYaviD74', 4, 1, 'Career'),
	(5, '5nrGZSco', 5, 1, 'Art');

INSERT INTO `language` VALUES
	(1, 'vn','VN', 'vn_VN'),
	(2, 'en','US', 'en_US');

INSERT INTO `category_language` VALUES
	(1, 1, 1, N'Thể lực', N'Các hoạt động rèn luyện thể lực'),
	(2, 2, 1, N'Tri thức', N'Các hoạt động về học tập, đọc sách v.v'),
	(3, 3, 1, N'Nghệ thuật', N'Các hoạt động về nghệ thuật: học đàn, hát hò v.v'),
	(4, 4, 1, N'Quan hệ - Giao tiếp', N'Các hoạt động xã hội, giao tiếp bên ngoài'),
	(5, 5, 1, N'Nghề nghiệp', N'Các hoạt động chú tâm về nghề nghiệp');

INSERT INTO `badge` VALUES 
	(1, 1, 'test.jpg','{"Login":"3"}'),
	(2, 1, 'test.jpg','{"Login":"7"}'),
	(3, 1, 'test.jpg','{"Login":"15"}'),
	(4, 1, 'test.jpg','{"Login":"30"}'),
	(5, 1, 'test.jpg','{"Login":"60"}'),
	(6, 1, 'test.jpg','{"CompleteTask_Communication":"3"}'),
	(7, 1, 'test.jpg','{"CompleteTask_Communication":"7"}'),
	(8, 1, 'test.jpg','{"CompleteTask_Communication":"16"}'),
	(9, 1, 'test.jpg','{"CompleteTask_Communication":"36"}'),
	(10, 1, 'test.jpg','{"CompleteTask_Communication":"81"}'),
	(11, 1, 'test.jpg','{"CompleteTask_Power":"3"}'),
	(12, 1, 'test.jpg','{"CompleteTask_Power":"7"}'),
	(13, 1, 'test.jpg','{"CompleteTask_Power":"16"}'),
	(14, 1, 'test.jpg','{"CompleteTask_Power":"36"}'),
	(15, 1, 'test.jpg','{"CompleteTask_Power":"81"}'),
	(16, 1, 'test.jpg','{"CompleteTask_Intelligent":"2"}'),
	(17, 1, 'test.jpg','{"CompleteTask_Intelligent":"4"}'),
	(18, 1, 'test.jpg','{"CompleteTask_Intelligent":"8"}'),
	(19, 1, 'test.jpg','{"CompleteTask_Intelligent":"16"}'),
	(20, 1, 'test.jpg','{"CompleteTask_Intelligent":"32"}'),
	(21, 1, 'test.jpg','{"CompleteTask_Career":"2"}'),
	(22, 1, 'test.jpg','{"CompleteTask_Career":"4"}'),
	(23, 1, 'test.jpg','{"CompleteTask_Career":"8"}'),
	(24, 1, 'test.jpg','{"CompleteTask_Career":"16"}'),
	(25, 1, 'test.jpg','{"CompleteTask_Career":"32"}'),
	(26, 1, 'test.jpg','{"CompleteTask_Art":"2"}'),
	(27, 1, 'test.jpg','{"CompleteTask_Art":"4"}'),
	(28, 1, 'test.jpg','{"CompleteTask_Art":"8"}'),
	(29, 1, 'test.jpg','{"CompleteTask_Art":"16"}'),
	(30, 1, 'test.jpg','{"CompleteTask_Art":"32"}');

INSERT INTO `badge_language` VALUES
	(1, 1, 1, N'Người năng động', N'Bạn rất năng động, tặng bạn huy hiệu này vì đã đăng nhập 5 lần!'),
	(2, 2, 1, N'Sức khỏe là trên hết', N'Bạn đã tạo 3 nhiệm vụ tăng thể lực, chúc mừng bạn!');

INSERT INTO `role` VALUES
	(1, 'Basic'),
	(2, 'Premium');

INSERT INTO `user` VALUES
	(1, 1, 'testacc','e10adc3949ba59abbe56e057f20f883e','testacc@gmail.com','FirstName',null,'2014-07-22',null,0,0,'CONFIRM',null);

INSERT INTO `config` VALUES
	('RESOURCES_URI', 'http://localhost:8080/resource/');
