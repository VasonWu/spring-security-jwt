CREATE TABLE `user` (
`id` int(10) PRIMARY KEY,
`user_name` VARCHAR(50) NOT NULL,
`password` VARCHAR(50) NOT NULL,
`enabled` BIT DEFAULT (1));

CREATE TABLE `user_role` (
`id` int(10) PRIMARY KEY,
`user_id` int(10) NOT NULL,
`role` VARCHAR(50) NOT NULL);