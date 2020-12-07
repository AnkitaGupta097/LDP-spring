INSERT INTO user (user_id,email,password) VALUES ('admin','admin@gmail.com','$2y$12$.5Lew0H.zNDsk9a7FfrfHeSQkX11WbqLxp6XvlnkLDlrz6w3iYtci');
INSERT INTO role (name) value ('ROLE_ADMIN')
INSERT INTO users_roles (user_id,role_id)
VALUES (
  (SELECT id FROM user WHERE user_id = 'admin'),
  (SELECT id FROM role WHERE name = 'ROLE_ADMIN'))


  INSERT INTO product (name,quantity,price) VALUES ('prod1',120,1000.0);



