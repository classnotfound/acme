INSERT INTO users(username,password,enabled,accountNonLocked)
VALUES ('admin','password', true, true);
INSERT INTO users(username,password,enabled,accountNonLocked)
VALUES ('user','password', true, true);
INSERT INTO users(username,password,enabled,accountNonLocked)
VALUES ('scott','tiger', true, true);
 
INSERT INTO user_roles (username, role)
VALUES ('user', 'ROLE_USER');
INSERT INTO user_roles (username, role)
VALUES ('admin', 'ROLE_ADMIN');
INSERT INTO user_roles (username, role)
VALUES ('admin', 'ROLE_USER');
INSERT INTO user_roles (username, role)
VALUES ('scott', 'ROLE_USER');