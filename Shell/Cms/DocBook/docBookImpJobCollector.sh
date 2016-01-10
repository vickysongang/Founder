#!/bin/bash
last_collect_time_file="/home/oracle/cms/bin/last_collect_time.txt"
if [ ! -f "$last_collect_time_file" ]; then
  echo 19700101000000 > $last_collect_time_file
fi
last_process_time=`head -1 "$last_collect_time_file"`
echo `date +%Y%m%d%H%M%S` > $last_collect_time_file

path_prefix="/home/ftpsite/"
path_suffix="/图书库/DocBook"
press_array=("大象出版社" "文心出版社")
for data in ${press_array[@]}  
do  
                docbooks=""
docbook_path=$path_prefix${data}$path_suffix
                for file in `ls $docbook_path`
                do 
                 if [ -d "$path_prefix${data}$path_suffix/$file" ]; then 
                   file_last_update_date=`stat $docbook_path/$file | grep Modify | awk '{print $2}'`
                   file_last_update_time=`stat $docbook_path/$file | grep Modify | awk '{split($3,var,".");print var[1]}'`
                   file_last_update_fulltime=`date -d "$file_last_update_date $file_last_update_time" +%Y%m%d%H%M%S`
                   if [ "$file_last_update_fulltime" -gt "$last_process_time" ]; then
                        docbooks="$docbooks$file/"
                   fi
                 fi
                done
                docbooks_gb18030=`echo $docbooks | iconv -f gb18030 -t utf8`
                pressName_gb18030=`echo ${data} | iconv -f gb18030 -t utf8`
 
    `curl -d "docBookNames=$docbooks_gb18030&pressName=$pressName_gb18030" http://ucm.hold.founder.com:16200/CmsUcmSyncWS/resources/docbook/collectImpJob`
    
    #`curl -d "docBookNames=`echo "9787806627662贵州油菜" | iconv -f gb18030 -t utf8`&pressName=`echo "大象出版社" | iconv -f gb18030 -t utf8`" http://ucm.hold.founder.com:7001/CmsUcmSyncWS/resources/docbook/collectImpJob`
done