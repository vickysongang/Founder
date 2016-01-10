#!/bin/bash 
batchLoadFile=/home/oracle/cms/hda/toBatchLoadFileList_$1.hda
if [ -f "$batchLoadFile" ]; then
`/u02/Oracle/Middleware/user_projects/domains/ecm_domain/ucm/cs/bin/IdcCommand -f $batchLoadFile -u sysadmin -l /home/oracle/cms/log/batchLoad_$1.log -c server`
fi
`curl -d "docIds=$2" http://ucm.hold.founder.com:16200/CmsUcmSyncWS/resources/batchLoadCallback/removeFtpDirs`
