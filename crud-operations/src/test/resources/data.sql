
INSERT INTO product (id,name,quantity,price) VALUES (RANDOM_UUID(),'prod1',120,1000.0);

INSERT INTO user_table (id,user_id,email,password) VALUES (RANDOM_UUID(),'admin','admin@gmail.com','$2y$12$.5Lew0H.zNDsk9a7FfrfHeSQkX11WbqLxp6XvlnkLDlrz6w3iYtci');
INSERT INTO role_table (id,name) values (RANDOM_UUID(),'ROLE_ADMIN');
INSERT INTO users_roles (user_id,role_id)
VALUES (
  (SELECT id FROM user_table WHERE user_id = 'admin'),
  (SELECT id FROM role_table WHERE name = 'ROLE_ADMIN'));

