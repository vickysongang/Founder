CREATE OR REPLACE VIEW CMS_COURSEWARE_V AS
SELECT courseware_id,
       c.doc_id,
       teaching_material_name,
       teaching_material_isbn,
       teaching_material_author,
       teaching_material_editor,
       teaching_material_series_name,
       teaching_material_type,
       comp_code,
       courseware_type,
       courseware_type_lookup.meaning courseware_type_meaning,
       courseware_name,
       copyright_flag,
       keyword,
       remarks,
       upload_time,
       source_book_doc_id,
       crt.category_id,
       nvl(ct.category_name,'Œ¥∑÷¿‡') category_name
  FROM cms_courseware_t       c,
       cms_lookup_value_v     courseware_type_lookup,
       cms_doc_category_rel_t crt,
       cms_category_t ct
 WHERE c.courseware_type = courseware_type_lookup.lookup_value_code(+)
   AND courseware_type_lookup.lookup_type_code(+) = 'COURSEWARE_TYPE'
   AND c.doc_id = crt.doc_id
   AND crt.category_id = ct.category_id(+);
