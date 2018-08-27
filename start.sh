#!/usr/bin/env bash
git pull;
systemctl stop rzzl2;
mvn clean compile package;
systemctl start rzzl2;