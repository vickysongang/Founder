CREATE OR REPLACE VIEW CMS_CHOICE_CATEGORY_V AS
SELECT 0 category_id,
       '下拉列表分类管理' category_name,
       NULL category_code,
       0 parent_node_id,
       'N' is_leaf_node,
       to_char(SYSDATE,'yyyy-MM-dd') last_update_date,
       NULL category_description,
       NULL category_type
  FROM dual
UNION ALL
SELECT -100000000 category_id,
       '图书下拉列表' category_name,
       NULL category_code,
       0 parent_node_id,
       'N' is_leaf_node,
       to_char(SYSDATE,'yyyy-MM-dd') last_update_date,
       NULL category_description,
       'BOOK' category_type
  FROM dual
UNION ALL
SELECT -100000001 category_id,
       '多媒体下拉列表' category_name,
       NULL category_code,
       0 parent_node_id,
       'N' is_leaf_node,
       to_char(SYSDATE,'yyyy-MM-dd') last_update_date,
       NULL category_description,
       'MEDIA' category_type
  FROM dual
UNION ALL
SELECT -100000002 category_id,
       '公共下拉列表' category_name,
       NULL category_code,
       0 parent_node_id,
       'N' is_leaf_node,
       to_char(SYSDATE,'yyyy-MM-dd') last_update_date,
       NULL category_description,
       'COMMON' category_type
  FROM dual
UNION ALL
SELECT -100000003 category_id,
       '图片下拉列表' category_name,
       NULL category_code,
       0 parent_node_id,
       'N' is_leaf_node,
       to_char(SYSDATE,'yyyy-MM-dd') last_update_date,
       NULL category_description,
       'FIGURE' category_type
  FROM dual
UNION ALL
SELECT -100000004 category_id,
       '期刊下拉列表' category_name,
       NULL category_code,
       0 parent_node_id,
       'N' is_leaf_node,
       to_char(SYSDATE,'yyyy-MM-dd') last_update_date,
       NULL category_description,
       'PERIODICAL' category_type
  FROM dual
UNION ALL
SELECT -100000005 category_id,
       '版权下拉列表' category_name,
       NULL category_code,
       0 parent_node_id,
       'N' is_leaf_node,
       to_char(SYSDATE,'yyyy-MM-dd') last_update_date,
       NULL category_description,
       'COPYRIGHT' category_type
  FROM dual
UNION ALL
SELECT -ltt.lookup_type_id category_id,
       ltt.lookup_type_name category_name,
       ltt.lookup_type_code category_code,
       decode(ltt.attribute2,
              'BOOK',
              -100000000,
              'COMMON',
              -100000002,
              'FIGURE',
              -100000003,
              'MEDIA',
              -100000001,
              'PERIODICAL',
              -100000004,
              'COPYRIGHT',
              -100000005) parent_node_id,
       'N' is_leaf_node,
       to_char(ltt.last_update_date,'yyyy-MM-dd') last_update_date,
       ltt.lookup_type_desc category_description,
       ltt.attribute2 category_type
  FROM cms_lookup_type_t ltt
 WHERE ltt.attribute2 IS NOT NULL
UNION ALL
SELECT lvt.lookup_value_id category_id,
       lvt.meaning category_name,
       lvt.lookup_value_code category_code,
       -ltt.lookup_type_id parent_node_id,
       'Y' is_leaf_node,
       to_char(lvt.last_update_date,'yyyy-MM-dd') last_update_date,
       lvt.lookup_value_desc category_description,
       ltt.attribute2 category_type
  FROM cms_lookup_value_t lvt, cms_lookup_type_t ltt
 WHERE lvt.lookup_type_code = ltt.lookup_type_code
   AND ltt.attribute2 IS NOT NULL;
