CREATE OR REPLACE VIEW CMS_ENTRY_DUP_RESULT_V AS
SELECT DISTINCT et.entry_id,
       et.title entry_name,
       et.SOURCE,
       et.keyword,
       et.doc_id,
       r.this_fill_time,
       r.rate,
       d.lib_code,
       d.comp_code
  FROM dup_result_1 r, cms_works_entry_t et, cms_doc_t d
 WHERE r.this_item_id = et.entry_id
   AND et.doc_id = d.doc_id
 UNION ALL 
 SELECT DISTINCT et.entry_id,
       et.entry_header entry_name,
       et.SOURCE,
       et.keyword,
       et.doc_id,
       r.this_fill_time,
       r.rate,
       d.lib_code,
       d.comp_code
  FROM dup_result_2 r, cms_encyclopedias_entry_t et, cms_doc_t d
 WHERE r.this_item_id = et.entry_id
   AND et.doc_id = d.doc_id
