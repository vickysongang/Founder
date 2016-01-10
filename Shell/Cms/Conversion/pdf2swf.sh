#!/bin/bash
str=$2
str=${str//@#^/" "}
/usr/local/pdf2swf/bin/pdf2swf -o "$1" -f -s flashversion=9 "$str"
