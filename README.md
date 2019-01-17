web-server


sudo docker run --name some-mysql -e MYSQL_ROOT_PASSWORD=my-secret-pw -d mysql
sudo docker run -ti --name some-app --link some-mysql:mysql -d node bash
sudo docker attach ec7f6a63c0a8

sudo docker run -ti --name some-app -v /home/mathieu/web-server/nodejs-app:/app --link some-mysql:mysql -d node bash



sudo docker run -it --link some-mysql:mysql --rm mysql sh -c 'exec mysql -h"$MYSQL_PORT_3306_TCP_ADDR" -P"$MYSQL_PORT_3306_TCP_PORT" -uroot -p"$MYSQL_ENV_MYSQL_ROOT_PASSWORD"'

ALTER USER 'root'@'localhost' IDENTIFIED WITH mysql_native_password BY 'my-secret-pw';
ALTER USER 'root'@'%' IDENTIFIED WITH mysql_native_password BY 'my-secret-pw';
CREATE USER 'foo'@'%' IDENTIFIED WITH mysql_native_password BY 'my-secret-pw';
