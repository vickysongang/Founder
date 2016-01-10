#mainPath=`echo $1 | iconv -f gb18030 -t utf8`
#cd mainPath
cd $1
`xmllint -noout -xinclude -schema /u02/Oracle/Middleware/user_projects/domains/ecm_domain/ucm_restful_config/docbook-mini.xsd Main.xml`
