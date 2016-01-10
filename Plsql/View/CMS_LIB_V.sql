CREATE OR REPLACE VIEW CMS_LIB_V AS
SELECT DISTINCT lib_id,lib_code, lib_name,retrival_flag,has_category_flag,seq
  FROM (SELECT clr.lib_code, lv.meaning lib_name,lv.attribute2 retrival_flag,lv.attribute3 has_category_flag, lv.seq,lv.lookup_value_id lib_id
          FROM cms_comp_lib_rel_t clr, cms_lookup_value_v lv
         WHERE clr.lib_code = lv.lookup_value_code
           AND clr.lib_type_code = lv.lookup_type_code);
