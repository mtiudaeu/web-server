node-couchbase

sudo docker run -d --name db -p 8091-8094:8091-8094 -p 11210:11210 couchbase
sudo docker ps
sudo docker kill 456856be562e
sudo docker rm 456856be562e


# Didn't test that and server.js yet.
npm install couchbase
