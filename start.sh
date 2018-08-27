#!/usr/bin/env bash
git pull;
systemctl stop rzzl2;
mvnw clean compile package;
systemctl start rzzl2;