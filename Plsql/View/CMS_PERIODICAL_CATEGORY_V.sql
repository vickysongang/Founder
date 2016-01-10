CREATE OR REPLACE VIEW CMS_PERIODICAL_CATEGORY_V AS
SELECT c.category_id                  periodical_category_id,
       c.category_name                periodical_category_name,
       ps.periodical_type,
       periodical_type_lookup.meaning periodical_type_meaning,
       ps.paper_type,
       ps.chief_editor,
       ps.price,
       ps.book_size,
       ps.lang,
       lang_lookup.meaning            lang_meaning,
       book_size_lookup.meaning       book_size_meaning,
       ps.cn,
       ps.issn,
       ps.prod_size,
       prod_size_lookup.meaning       prod_size_meaning,
       ps.reader_group,
       reader_group_lookup.meaning    reader_group_meaning,
       c.seq,
       ps.total,
       ps.publishing,
       ps.description
  FROM cms_category_t           c,
       cms_lib_category_g_rel_t lcgr,
       cms_periodical_setup_t   ps,
       cms_lookup_value_v       book_size_lookup,
       cms_lookup_value_v       prod_size_lookup,
       cms_lookup_value_v       reader_group_lookup,
       cms_lookup_value_v       periodical_type_lookup,
       cms_lookup_value_v       lang_lookup
 WHERE c.parent_node_type = 'CATEGORY_GROUP'
   AND abs(c.parent_node_id) = lcgr.rel_id
   AND lcgr.lib_code = 'PERIODICAL'
   AND lcgr.lib_type_code = 'END_PROD_LIB'
   AND ps.periodical_category_id = c.category_id
   AND ps.book_size = book_size_lookup.lookup_value_code
   AND book_size_lookup.lookup_type_code = 'BOOK_SIZE'
   AND ps.prod_size = prod_size_lookup.lookup_value_code
   AND prod_size_lookup.lookup_type_code = 'PROD_SIZE'
   AND ps.reader_group = reader_group_lookup.lookup_value_code
   AND reader_group_lookup.lookup_type_code = 'READER_GROUP'
   AND ps.periodical_type = periodical_type_lookup.lookup_value_code
   AND periodical_type_lookup.lookup_type_code = 'PERIODICAL_TYPE'
   AND ps.lang = lang_lookup.lookup_value_code
   AND lang_lookup.lookup_type_code = 'LANG'
