#!/usr/bin/env bash
git pull;
./mvnw spring-boot::stop;
./mvnw clean compile package spring-boot::start;