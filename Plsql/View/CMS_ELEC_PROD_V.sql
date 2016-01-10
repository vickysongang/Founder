CREATE OR REPLACE VIEW CMS_ELEC_PROD_V AS
SELECT ep.doc_id,
       ep.elec_prod_name,
       ep.isbn,
       ep.item_code,
       ep.publishing_house,
       ep.assort_book,
       decode(dcr.category_id, -1, 'Œ¥∑÷¿‡', c.category_name) category_name,
       ep.lang,
       lang_lookup.meaning lang_meaning,
       ep.carrier_type,
       carrier_type_lookup.meaning carrier_type_meaning,
       ep.time_length,
       ep.amount,
       ep.disk_design,
       ep.producer,
       ep.text_editor,
       ep.editor,
       ep.pub_time,
       ep.keyword,
       ep.content_desc,
       ep.remarks
  FROM cms_elec_prod_t        ep,
       cms_category_t         c,
       cms_doc_category_rel_t dcr,
       cms_lookup_value_v     lang_lookup,
       cms_lookup_value_v     carrier_type_lookup
 WHERE ep.doc_id = dcr.doc_id
   AND dcr.category_id = c.category_id(+)
   AND ep.lang = lang_lookup.lookup_value_code(+)
   AND lang_lookup.lookup_type_code(+) = 'LANG'
   AND ep.carrier_type = carrier_type_lookup.lookup_value_code(+)
   AND carrier_type_lookup.lookup_type_code(+) = ' CARRIER_TYPE'
