#!/bin/bash
str=$1
str=${str@#^ }
unzip $str -d $2

