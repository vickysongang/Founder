CREATE OR REPLACE VIEW CMS_SECOND_LEVEL_LIB_V AS
SELECT DISTINCT lib_code, lib_name, comp_code, lib_type_code, seq, lib_flag
  FROM (SELECT clr.lib_code,
               lv.meaning        lib_name,
               clr.comp_code,
               clr.lib_type_code,
               lv.seq,
               lv.attribute1     lib_flag
          FROM cms_comp_lib_rel_t clr, cms_lookup_value_v lv
         WHERE clr.lib_code = lv.lookup_value_code
           AND clr.lib_type_code = lv.lookup_type_code)
 ORDER BY seq;
