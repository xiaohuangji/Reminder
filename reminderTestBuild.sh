#!/bin/sh

cd /Users/wills/workspace/Reminder/reminder-webapp
mvn clean package 
scp -r /Users/wills/workspace/Reminder/reminder-webapp/target/reminder-webapp/* root@112.126.64.240:/data/reminder-webapp/ROOT
