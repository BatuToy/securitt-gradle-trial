#!/usr/bin/env bash
set -e
# Go to base root dir.
cd ../../
open -n /Applications/Docker.app
sleep 6
docker-compose --file ./compose.yaml down -v || { printf "An Error Occurred While Downing Compose file!"; exit 1;}
sleep 3
docker-compose --file ./compose.yaml up -d || { printf "An Error Occurred While Upping Compose file!"; exit 1;}