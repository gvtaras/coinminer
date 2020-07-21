
rm -r ./default/images/*

docker save coinminer-backend:0.0.1-SNAPSHOT > ./default/images/coinminer-backend:0.0.1-SNAPSHOT.tar

scp -r ./default/* root@135.181.42.187:/opt/coinminer/

ssh root@135.181.42.187 "chmod 777 /opt/coinminer/elasticsearch/logs/"
ssh root@135.181.42.187 "chmod 777 /opt/coinminer/elasticsearch/data/"

ssh root@135.181.42.187 "docker load < /opt/coinminer/images/coinminer-backend:0.0.1-SNAPSHOT.tar; docker tag coinminer-backend:0.0.1-SNAPSHOT coinminer-backend:latest"
