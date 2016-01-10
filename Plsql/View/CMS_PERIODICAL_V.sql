CREATE OR REPLACE VIEW CMS_PERIODICAL_V AS
SELECT p.periodical_id,
       p.doc_id,
       p.periodical_category_id,
       pc.periodical_category_name,
       pc.cn,
       pc.issn,
       ps.year,
       ps.period_num,
       ps.year_meaning || '-' || ps.period_num year_num,
       ps.year_meaning,
       p.keyword,
       p.editor,
       pc.chief_editor,
       pc.lang,
       pc.lang_meaning,
       p.cover_design,
       p.format_design,
       p.pub_time,
       p.pagination,
       pc.price,
       p.suggestion,
       pc.book_size,
       pc.book_size_meaning,
       pc.prod_size,
       pc.prod_size_meaning,
       p.color_num,
       color_num_lookup.meaning color_num_meaning,
       p.assort_prod,
       assort_prod_lookup.meaning assort_prod_meaning,
       p.remarks,
       p.main_page,
       main_page_lookup.meaning main_page_meaning,
       p.main_typeset,
       main_typeset_lookup.meaning main_typeset_meaning,
       p.cover_typeset,
       cover_typeset_lookup.meaning cover_typeset_meaning,
       pc.periodical_type,
       pc.periodical_type_meaning,
       pc.description,
       p.total_num,
       pc.reader_group_meaning,
       p.typeset_comp,
       pc.publishing
  FROM cms_periodical_t          p,
       cms_periodical_category_v pc,
       cms_period_setup_v        ps,
       cms_lookup_value_v        color_num_lookup,
       cms_lookup_value_v        assort_prod_lookup,
       cms_lookup_value_v        cover_typeset_lookup,
       cms_lookup_value_v        main_typeset_lookup,
       cms_lookup_value_v        main_page_lookup
 WHERE p.periodical_category_id = pc.periodical_category_id
   AND p.period_id = ps.setup_id
   AND p.color_num = color_num_lookup.lookup_value_code(+)
   AND color_num_lookup.lookup_type_code(+) = 'COLOR_NUM'
   AND p.assort_prod = assort_prod_lookup.lookup_value_code(+)
   AND assort_prod_lookup.lookup_type_code(+) = 'ASSORT_PROD'
   AND p.cover_typeset = cover_typeset_lookup.lookup_value_code(+)
   AND cover_typeset_lookup.lookup_type_code(+) = 'COVER_TYPESET'
   AND p.main_typeset = main_typeset_lookup.lookup_value_code(+)
   AND main_typeset_lookup.lookup_type_code(+) = 'MAIN_TYPESET'
   AND p.main_page = main_page_lookup.lookup_value_code(+)
   AND main_page_lookup.lookup_type_code(+) = 'MAIN_PAGE';
