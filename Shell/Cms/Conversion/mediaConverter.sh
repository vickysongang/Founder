#!/bin/bash
path="/home/oracle/cms/temp/media/"
#ת��ΪFLV
`/usr/local/ffmpeg/bin/ffmpeg -i $path$1 -ab 128 -ar 22050 -r 29.97 -s 450x270 -qscale 6 -t 1000 -y -f flv $path$2`
#���ɷ���
`/usr/local/ffmpeg/bin/ffmpeg -i $path$2 -y -f image2 -ss 1 -vframes 1 -s 120*72 $path$3`
#��ȡʱ��
`/usr/local/ffmpeg/bin/ffmpeg -i $path$1 2>&1 | grep 'Duration' | cut -d ' ' -f 4 | sed s/,// > $path$4`