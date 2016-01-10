#!/bin/bash 
path_prefix="/home/ftpsite/" 
str=$1
str=${str//@#^/" "}
temp=`echo $str|grep '###'|wc -l`
while(($temp>0))
do
  rm -fr "$path_prefix${str%%###*}"
  str=`echo ${str#*###}`
  temp=`echo $str|grep '###'|wc -l`
done
