INSERT INTO users(username,password,enabled)
VALUES ('admin','password', true);
INSERT INTO users(username,password,enabled)
VALUES ('user','password', true);
INSERT INTO users(username,password,enabled)
VALUES ('scott','password', true);
 
INSERT INTO user_roles (username, role)
VALUES ('user', 'ROLE_USER');
INSERT INTO user_roles (username, role)
VALUES ('admin', 'ROLE_ADMIN');
INSERT INTO user_roles (username, role)
VALUES ('admin', 'ROLE_USER');
INSERT INTO user_roles (username, role)
VALUES ('scott', 'ROLE_USER');