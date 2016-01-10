#!/bin/bash
last_process_time_file="last_process_time.txt"
if [ ! -f "$last_process_time_file" ]; then
  echo 19700101000000 > $last_process_time_file
fi
last_process_time=`head -1 last_process_time.txt`
echo `date +%Y%m%d%H%M%S` > $last_process_time_file

docbooks=""

docbook_path="/home/ftpsite/DocBook"
for file in `ls $docbook_path`
do 
 if [ -d "$docbook_path/$file" ]; then 
   file_last_update_date=`stat $docbook_path/$file | grep Modify | awk '{print $2}'`
   file_last_update_time=`stat $docbook_path/$file | grep Modify | awk '{split($3,var,".");print var[1]}'`
   file_last_update_fulltime=`date -d "$file_last_update_date $file_last_update_time" +%Y%m%d%H%M%S`
   lok=`ls $docbook_path/$file/book.lok`
   #echo "$lok"
   if [ "$file_last_update_fulltime" -gt "$last_process_time" -a -z "$lok" ]; then
     echo > $docbook_path/$file/book.lok
     busyFlag="N"
     for f in `find $docbook_path/$file`
     do
        if [ -f "$f" ]; then
           lsof=`/usr/sbin/lsof $f`
           echo "$f   $lsof"  
           if [ -n "$lsof" ]; then
             busyFlag="Y"
             break
           fi
        fi
     done
     if [ "$busyFlag" = "N" ]; then
        docbooks="$file/"
     else
        `rm $docbook_path/$file/book.lok`
     fi
   fi
 fi
done
echo "$docbooks"

`curl -d param="$docbooks" http://ucm.hold.founder.com:7001/CmsUcmSyncWS/resources/docbook/processDocBook`

