docker run -d --name mysql-container -e MYSQL_ROOT_PASSWORD=password -p 3306:3306 mysql:8.0.44-debian
# https://hub.docker.com/_/mysql


docker exec -it mysql-container mysql -u springstudent -p


