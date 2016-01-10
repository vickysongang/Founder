#!/bin/bash 
str=$2
str=${str//@#^/" "}
`/usr/local/ImageMagick/bin/convert -colorspace RGB -sample "$1" -layers flatten "$str" "$3"`
