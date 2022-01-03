#!/bin/bash

if [ "$#" -lt 1 ]; then
    echo "$# is Illegal number of parameters."
    echo "Usage: 1st param [docker image version]"
	exit 1
fi

docker build -t cheesedev4/cheese:$1 --build-arg ENVIRONMENT=dev .

docker push cheesedev4/cheese:$1