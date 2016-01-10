CREATE OR REPLACE VIEW CMS_VIDEO_V AS
SELECT v.doc_id,
       v.video_id,
       v.file_name,
       v.file_time,
       v.file_size,
       v.isbn,
       v.item_code,
       v.series_name,
       decode(dcr.category_id, -1, 'Œ¥∑÷¿‡', c.category_name) category_name,
       v.lang,
       lang_lookup.meaning lang_meaning,
       v.text_editor,
       v.editor,
       v.carrier_type,
       v.pub_time,
       v.producer,
       carrier_type_lookup.meaning carrier_type_meaning,
       v.assort_book,
       v.keyword,
       v.remarks,
       v.content_desc,
       v.preview_url,
       v.file_format
  FROM cms_video_t            v,
       cms_category_t         c,
       cms_doc_category_rel_t dcr,
       cms_lookup_value_v     lang_lookup,
       cms_lookup_value_v     carrier_type_lookup
 WHERE v.doc_id = dcr.doc_id
   AND dcr.category_id = c.category_id(+)
   AND v.lang = lang_lookup.lookup_value_code(+)
   AND lang_lookup.lookup_type_code(+) = 'LANG'
   AND v.carrier_type = carrier_type_lookup.lookup_value_code(+)
   AND carrier_type_lookup.lookup_type_code(+) = 'CARRIER_TYPE'

