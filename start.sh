#!/usr/bin/env bash
git pull;
mvnw clean compile package spring-boot::stop;
mvnw clean compile package spring-boot::start;