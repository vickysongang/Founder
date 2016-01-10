CREATE OR REPLACE VIEW CMS_TEACHING_PLAN_V AS
SELECT teaching_plan_id,
       tp.doc_id,
       source_book_name,
       source_book_isbn,
       publishing_house,
       source_book_type,
       comp_code,
       editor,
       teaching_plan_name,
       producer,
       teaching_plan_type,
       teaching_plan_type_lookup.meaning teaching_plan_type_meaning,
       copyright_flag,
       keyword,
       remarks,
       teaching_plan_store_time,
       source_book_doc_id,
       crt.category_id,
       nvl(ct.category_name,'Œ¥∑÷¿‡') category_name
  FROM cms_teaching_plan_t    tp,
       cms_lookup_value_v     teaching_plan_type_lookup,
       cms_doc_category_rel_t crt,
       cms_category_t         ct
 WHERE tp.teaching_plan_type =
       teaching_plan_type_lookup.lookup_value_code(+)
   AND teaching_plan_type_lookup.lookup_type_code(+) = 'TEACHING_PLAN_TYPE'
   AND tp.doc_id = crt.doc_id
   AND crt.category_id = ct.category_id(+);
