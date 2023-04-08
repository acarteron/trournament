#!/bin/bash

curl -X POST -d '{"name":"Player 1","score":0}' -H "Content-Type: application/json" http://127.0.0.1:8080/players

curl -X POST -d '{"name":"Player 2","score":0}' -H "Content-Type: application/json" http://127.0.0.1:8080/players

curl -X POST -d '{"name":"Player 3","score":0}' -H "Content-Type: application/json" http://127.0.0.1:8080/players

curl -X POST -d '{"name":"Player 4","score":0}' -H "Content-Type: application/json" http://127.0.0.1:8080/players

curl -X PATCH -d '{"score":42}' -H "Content-Type: application/json" "http://127.0.0.1:8080/players/Player%201"

curl -X GET "http://127.0.0.1:8080/players/Player%201"

curl -X GET "http://127.0.0.1:8080/players/Player%202"

curl -X DELETE -H "Content-Type: application/json" http://127.0.0.1:8080/players