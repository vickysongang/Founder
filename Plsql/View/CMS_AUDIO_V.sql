CREATE OR REPLACE VIEW CMS_AUDIO_V AS
SELECT a.doc_id,
       a.audio_id,
       a.file_name,
       a.file_time,
       a.file_size,
       a.isbn,
       a.item_code,
       a.series_name,
       decode(dcr.category_id, -1, 'Œ¥∑÷¿‡', c.category_name) category_name,
       a.lang,
       lang_lookup.meaning lang_meaning,
       a.text_editor,
       a.editor,
       a.carrier_type,
       a.pub_time,
       a.record_user,
       carrier_type_lookup.meaning carrier_type_meaning,
       a.assort_book,
       a.keyword,
       a.remarks,
       a.content_desc,
       a.preview_url,
       a.file_format
  FROM cms_audio_t            a,
       cms_category_t         c,
       cms_doc_category_rel_t dcr,
       cms_lookup_value_v     lang_lookup,
       cms_lookup_value_v     carrier_type_lookup
 WHERE a.doc_id = dcr.doc_id
   AND dcr.category_id = c.category_id(+)
   AND a.lang = lang_lookup.lookup_value_code(+)
   AND lang_lookup.lookup_type_code(+) = 'LANG'
   AND a.carrier_type = carrier_type_lookup.lookup_value_code(+)
   AND carrier_type_lookup.lookup_type_code(+) = 'CARRIER_TYPE'

