#!/bin/bash

chmod a+x /backend/book.jar
chmod a+x /backend/publishing_house.jar
chmod a+x /backend/gateway.jar
java -jar /backend/book.jar &
sleep 5
java -jar /backend/publishing_house.jar &
sleep 5
java -jar /backend/gateway.jar 
sleep infinity 