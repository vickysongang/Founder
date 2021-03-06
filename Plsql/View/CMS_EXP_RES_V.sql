CREATE OR REPLACE VIEW CMS_EXP_RES_V AS 
SELECT lv.lookup_value_id res_id,
       lv.lookup_value_code res_code,
       lv.meaning res_name,
       lv.lookup_type_code res_type_code,
       substr(lv.lookup_type_code, 0, instr(lv.lookup_type_code, '#') - 1) res_type_simple_code,
       lv.lookup_type_name res_type_name,
       lv.module_code lib_code,
       lv.attribute1,
       lv.attribute2,
       nvl(lv.attribute5,'Y') export_flag,
       lv.seq,
       lv.seq2
  FROM cms_lookup_value_v lv
 WHERE lv.module_type = 'RES_EXP'
