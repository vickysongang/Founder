CREATE OR REPLACE VIEW CMS_NEWSPAPER_V AS
SELECT n.newspaper_id,
       n.doc_id,
       n.newspaper_category_id,
       nc.newspaper_category_name,
       nc.cn,
       nc.issn,
       ns.year,
       ns.news_num,
       ns.year_meaning || '-' || ns.news_num year_num,
       ns.year_meaning,
       n.keyword,
       n.editor,
       nc.chief_editor,
       nc.lang,
       nc.lang_meaning,
       n.cover_design,
       n.format_design,
       n.pub_time,
       n.pagination,
       nc.price,
       n.suggestion,
       nc.book_size,
       nc.book_size_meaning,
       nc.prod_size,
       nc.prod_size_meaning,
       n.color_num,
       color_num_lookup.meaning color_num_meaning,
       n.assort_prod,
       assort_prod_lookup.meaning assort_prod_meaning,
       n.remarks,
       n.main_page,
       main_page_lookup.meaning main_page_meaning,
       n.main_typeset,
       main_typeset_lookup.meaning main_typeset_meaning,
       n.cover_typeset,
       cover_typeset_lookup.meaning cover_typeset_meaning,
       nc.newspaper_type,
       nc.newspaper_type_meaning,
       nc.description,
       n.total_num,
       nc.reader_group_meaning,
       n.typeset_comp,
       nc.publishing
  FROM cms_newspaper_t          n,
       cms_newspaper_category_v nc,
       cms_news_setup_v          ns,
       cms_lookup_value_v       color_num_lookup,
       cms_lookup_value_v       assort_prod_lookup,
       cms_lookup_value_v       cover_typeset_lookup,
       cms_lookup_value_v       main_typeset_lookup,
       cms_lookup_value_v       main_page_lookup
 WHERE n.newspaper_category_id = nc.newspaper_category_id
   AND n.news_id = ns.setup_id
   AND n.color_num = color_num_lookup.lookup_value_code(+)
   AND color_num_lookup.lookup_type_code(+) = 'COLOR_NUM'
   AND n.assort_prod = assort_prod_lookup.lookup_value_code(+)
   AND assort_prod_lookup.lookup_type_code(+) = 'ASSORT_PROD'
   AND n.cover_typeset = cover_typeset_lookup.lookup_value_code(+)
   AND cover_typeset_lookup.lookup_type_code(+) = 'COVER_TYPESET'
   AND n.main_typeset = main_typeset_lookup.lookup_value_code(+)
   AND main_typeset_lookup.lookup_type_code(+) = 'MAIN_TYPESET'
   AND n.main_page = main_page_lookup.lookup_value_code(+)
   AND main_page_lookup.lookup_type_code(+) = 'MAIN_PAGE';
