#!/usr/bin/env bash
git pull;
./mvnw spring-boot::stop;
./mvnw clean spring-boot::start;