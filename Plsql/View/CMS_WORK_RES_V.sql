CREATE OR REPLACE VIEW CMS_WORK_RES_V AS 
SELECT lv.lookup_value_id   res_id,
       lv.lookup_value_code res_code,
       lv.meaning           res_name,
       lv.lookup_type_code  res_type_code,
       lv.lookup_type_name res_type_name,
       lv.module_code       lib_code,
       lv.seq,
       lv.process_flag
  FROM cms_lookup_value_v lv
 WHERE lv.module_type = 'WORK_RES'
