CREATE OR REPLACE VIEW CMS_ATTACH_TYPE_V AS 
SELECT lv.lookup_value_id   attach_type_id,
       lv.lookup_value_code attach_type_code,
       lv.meaning           attach_type_name,
       lv.module_code       lib_code,
       lv.seq
  FROM cms_lookup_value_v lv
 WHERE lv.module_type = 'ATTACH_TYPE'
