CREATE OR REPLACE VIEW CMS_ACTIVITY_V AS
SELECT activity_id,
       a.doc_id,
       activity_theme,
       activity_start_time,
       activity_end_time,
       activity_location,
       activity_type,
       comp_code,
       activity_store_time,
       activity_desc,
       remarks,
       crt.category_id,
       nvl(ct.category_name,'Œ¥∑÷¿‡') category_name
  FROM cms_activity_t a, cms_doc_category_rel_t crt, cms_category_t ct
 WHERE a.doc_id = crt.doc_id
   AND crt.category_id = ct.category_id(+);
