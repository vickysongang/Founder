#!/bin/bash 
str=$1
str=${str//@#^/" "}
mkdir "$str"
