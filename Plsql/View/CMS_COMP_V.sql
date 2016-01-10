CREATE OR REPLACE VIEW CMS_COMP_V AS 
SELECT lv.lookup_value_id   comp_id,
       lv.lookup_value_code comp_code,
       lv.meaning           comp_name,
       lv.lookup_value_desc comp_desc,
       lv.seq,
       lv.lookup_value_enable_flag,
       lv.attribute4 group_flag--集团用户标识
  FROM cms_lookup_value_v lv
 WHERE lv.lookup_type_code = 'COMP'
