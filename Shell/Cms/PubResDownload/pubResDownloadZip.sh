#!/bin/bash
cd $1
str=$3
str=${str//@#^/" "}
temp=`echo $str|grep '###'|wc -l`
param=""
while(($temp>0))
do
  param=$param${str%%##*}" "
  str=`echo ${str#*###}`
  temp=`echo $str|grep '###'|wc -l`
done
#zip -r $2 $param
tar -cvf $2 $param