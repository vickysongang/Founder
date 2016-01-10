CREATE OR REPLACE VIEW CMS_FIRST_LEVEL_LIB_V AS
SELECT DISTINCT lib_code, lib_name, comp_code, seq
  FROM (SELECT clr.lib_type_code   lib_code,
               lt.lookup_type_name lib_name,
               clr.comp_code,
               lt.seq
          FROM cms_comp_lib_rel_t clr, cms_lookup_type_t lt
         WHERE clr.lib_type_code = lt.lookup_type_code
        )
 ORDER BY seq;
