# protoquill sandbox project

## Prerequirements

- Docker
- sbt

## Setup

Run docker compose to set up mysql server. Then, mysql server starts at 13306.

```sh
docker compose up -d
# or
docker-compose up -d
```

```sh
mysql -u admin -p -h 127.0.0.1 -P 13306
# password: admin

# or
docker compose exec mysql bash
mysql -u admin -p
# password: admin
```
