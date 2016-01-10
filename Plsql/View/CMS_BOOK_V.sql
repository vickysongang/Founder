CREATE OR REPLACE VIEW CMS_BOOK_V AS
SELECT b.book_id,
       b.book_name,
       b.isbn,
       b.item_code,
       b.series,
       b.publishing_house,
       decode(dcr.category_id, -1, 'Œ¥∑÷¿‡', c.category_name) category_name,
       b.lang,
       lang_lookup.meaning lang_meaning,
       b.editor,
       b.author,
       b.cover_design,
       b.format_design,
       b.chief_editor,
       b.impression,
       b.edition,
       b.translator,
       b.pub_time,
       b.pagination,
       b.price,
       b.suggestion,
       b.reader_group,
       reader_group_lookup.meaning reader_group_meaning,
       b.book_size,
       book_size_lookup.meaning book_size_meaning,
       b.prod_size,
       prod_size_lookup.meaning prod_size_meaning,
       b.color_num,
       color_num_lookup.meaning color_num_meaning,
       b.format_comp,
       b.assort_prod,
       assort_prod_lookup.meaning assort_prod_meaning,
       b.keyword,
       b.remarks,
       b.main_page,
       main_page_lookup.meaning main_page_meaning,
       b.main_typeset,
       main_typeset_lookup.meaning main_typeset_meaning,
       b.cover_typeset,
       cover_typeset_lookup.meaning cover_typeset_meaning,
       b.doc_id
  FROM cms_book_t             b,
       cms_category_t         c,
       cms_doc_category_rel_t dcr,
       cms_lookup_value_v     lang_lookup,
       cms_lookup_value_v     reader_group_lookup,
       cms_lookup_value_v     book_size_lookup,
       cms_lookup_value_v     prod_size_lookup,
       cms_lookup_value_v     color_num_lookup,
       cms_lookup_value_v     assort_prod_lookup,
       cms_lookup_value_v     cover_typeset_lookup,
       cms_lookup_value_v     main_typeset_lookup,
       cms_lookup_value_v     main_page_lookup
 WHERE b.doc_id = dcr.doc_id
   AND dcr.category_id = c.category_id(+)
   AND b.lang = lang_lookup.lookup_value_code(+)
   AND lang_lookup.lookup_type_code(+) = 'LANG'
   AND b.reader_group = reader_group_lookup.lookup_value_code(+)
   AND reader_group_lookup.lookup_type_code(+) = 'READER_GROUP'
   AND b.book_size = book_size_lookup.lookup_value_code(+)
   AND book_size_lookup.lookup_type_code(+) = 'BOOK_SIZE'
   AND b.prod_size = prod_size_lookup.lookup_value_code(+)
   AND prod_size_lookup.lookup_type_code(+) = 'PROD_SIZE'
   AND b.color_num = color_num_lookup.lookup_value_code(+)
   AND color_num_lookup.lookup_type_code(+) = 'COLOR_NUM'
   AND b.assort_prod = assort_prod_lookup.lookup_value_code(+)
   AND assort_prod_lookup.lookup_type_code(+) = 'ASSORT_PROD'
   AND b.cover_typeset = cover_typeset_lookup.lookup_value_code(+)
   AND cover_typeset_lookup.lookup_type_code(+) = 'TYPESET'
   AND b.main_typeset = main_typeset_lookup.lookup_value_code(+)
   AND main_typeset_lookup.lookup_type_code(+) = 'TYPESET'
   AND b.main_page = main_page_lookup.lookup_value_code(+)
   AND main_page_lookup.lookup_type_code(+) = 'MAIN_PAGE';
