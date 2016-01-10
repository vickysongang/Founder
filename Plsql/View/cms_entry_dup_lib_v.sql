CREATE OR REPLACE VIEW CMS_ENTRY_DUP_LIB_V AS 
SELECT lib.lib_id, lib.lib_code, lib.lib_name, lib.seq
  FROM cms_lib_v lib
 WHERE lib.lib_code IN ('WORKS_ENTRY', 'ENCYCLOPEDIAS_ENTRY');
