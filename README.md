# Implementation

## Requirements
* Gradle
* Mongodb

## Compile
```
gradle build
```
## Run
```
unzip build/distributions/playerRank-0.0.1.zip -d <location>
/<location>/playerRank-0.0.1/bin/playerRank
```
starts web server with api: http://localhost:8080/

## Run test
```
gradle test --tests "re.chasam.models.impl.TournamentImplTest"
gradle test --tests "re.chasam.models.impl.PlayerTest"
```

## Mongo connexion
Its access follows the format:
```
scheme://host:port
```
to access to a db and use collection.
Defaults parameters can be overriden using environments variables:
```
SCHEME
HOST
PORT
NAME
COLLECTION
```

## Docker

```
cd Docker
docker compose up
```

## API
### GET /players  
lists all players with name score and rank
### GET /player/{id}
get a player score and its rank
### POST /players {"name":"a name"}
add a new player, its score and rank are set to 0
### PATCH /players/{id} {"score":42}
set the score of a player
### DELETE /players
remove all players


