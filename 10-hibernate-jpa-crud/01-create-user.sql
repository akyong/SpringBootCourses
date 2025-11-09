DROP USER if exists 'springstudent'@'localhost';

CREATE USER 'springstudent'@'localhost' IDENTIFIED BY 'springstudent';

GRANT ALL PRIVILEGES ON * . * TO 'springstudent'@'localhost';

# DROP USER IF EXISTS 'springstudent'@'localhost';
# CREATE USER 'springstudent'@'%' IDENTIFIED BY 'springstudent';
# GRANT ALL PRIVILEGES ON *.* TO 'springstudent'@'%' WITH GRANT OPTION;
# FLUSH PRIVILEGES;
