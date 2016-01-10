CREATE OR REPLACE VIEW CMS_ENTRY_DUP_SIMILAR_V AS
SELECT r.this_item_id,
       r.this_doc_id,
       r.that_item_id,
       r.that_doc_id,
       r.that_fill_time,
       et.title entry_name,
       et.SOURCE,
       r.rate,
       et.entry_content
  FROM dup_result_1 r, cms_works_entry_t et
 WHERE r.that_item_id = et.entry_id
UNION ALL
SELECT r.this_item_id,
       r.this_doc_id,
       r.that_item_id,
       r.that_doc_id,
       r.that_fill_time,
       et.entry_header entry_name,
       et.SOURCE,
       r.rate,
       et.entry_content
  FROM dup_result_1 r, cms_encyclopedias_entry_t et
 WHERE r.that_item_id = et.entry_id;
