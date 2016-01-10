CREATE OR REPLACE VIEW CMS_RES_CATEGORY_V AS
SELECT c.category_id,
       c.category_name,
       c.parent_node_type,
       c.parent_node_id,
       lcgr.comp_code,
       lcgr.lib_code,
       lcgr.lib_type_code,
       lcgr.primary_flag,
       lcgr.category_group_code,
       c.seq,
       c.attribute1,
       c.attribute2,
       c.attribute3,
       to_char(c.last_update_date, 'yyyy-MM-dd') last_update_date
  FROM cms_category_t           c,
       cms_lib_category_g_rel_t lcgr,
       cms_lookup_value_t       lib
 WHERE cms_common_pkg.get_category_top_node_id(c.category_id) = lcgr.rel_id
   AND lcgr.lib_code = lib.lookup_value_code
   AND lcgr.lib_type_code = lib.lookup_type_code
   AND lib.attribute3 = 'Y'
UNION ALL
SELECT (-t.rel_id) category_id,
       lib.meaning category_name,
       'CATEGORY_GROUP' parent_node_type,
       0 parent_node_id,
       t.comp_code,
       t.lib_code,
       t.lib_type_code,
       t.primary_flag,
       t.category_group_code,
       lib.seq,
       NULL attribute1,
       NULL attribute2,
       NULL attribute3,
       to_char(t.last_update_date,'yyyy-MM-dd') last_update_date
  FROM cms_lib_category_g_rel_t t,  cms_lookup_value_t    lib
 WHERE t.lib_code = lib.lookup_value_code
   AND t.lib_type_code = lib.lookup_type_code
   AND lib.attribute3 = 'Y'
UNION ALL
SELECT 0 category_id,
       '分类管理' category_name,
       'CATEGORY_GROUP' parent_node_type,
       0 parent_node_id,
       NULL comp_code,
       NULL lib_code,
       NULL lib_type_code,
       'Y' primary_flag,
       NULL category_group_code,
       0 seq,
       NULL attribute1,
       NULL attribute2,
       NULL attribute3,
       to_char(SYSDATE,'yyyy-MM-dd') last_update_date
  FROM dual
