CREATE OR REPLACE VIEW CMS_NEWSPAPER_CATEGORY_V AS
SELECT c.category_id                 newspaper_category_id,
       c.category_name               newspaper_category_name,
       ns.newspaper_type,
       newspaper_type_lookup.meaning newspaper_type_meaning,
       ns.paper_type,
       ns.chief_editor,
       ns.price,
       ns.book_size,
       ns.lang,
       lang_lookup.meaning           lang_meaning,
       book_size_lookup.meaning      book_size_meaning,
       ns.cn,
       ns.issn,
       ns.prod_size,
       prod_size_lookup.meaning      prod_size_meaning,
       ns.reader_group,
       reader_group_lookup.meaning   reader_group_meaning,
       c.seq,
       ns.total,
       ns.publishing,
       ns.description
  FROM cms_category_t           c,
       cms_lib_category_g_rel_t lcgr,
       cms_newspaper_setup_t    ns,
       cms_lookup_value_v       book_size_lookup,
       cms_lookup_value_v       prod_size_lookup,
       cms_lookup_value_v       reader_group_lookup,
       cms_lookup_value_v       newspaper_type_lookup,
       cms_lookup_value_v       lang_lookup
 WHERE c.parent_node_type = 'CATEGORY_GROUP'
   AND abs(c.parent_node_id) = lcgr.rel_id
   AND lcgr.lib_code = 'NEWSPAPER'
   AND lcgr.lib_type_code = 'END_PROD_LIB'
   AND ns.newspaper_category_id = c.category_id
   AND ns.book_size = book_size_lookup.lookup_value_code
   AND book_size_lookup.lookup_type_code = 'BOOK_SIZE'
   AND ns.prod_size = prod_size_lookup.lookup_value_code
   AND prod_size_lookup.lookup_type_code = 'PROD_SIZE'
   AND ns.reader_group = reader_group_lookup.lookup_value_code
   AND reader_group_lookup.lookup_type_code = 'READER_GROUP'
   AND ns.newspaper_type = newspaper_type_lookup.lookup_value_code
   AND newspaper_type_lookup.lookup_type_code = 'PERIODICAL_TYPE'
   AND ns.lang = lang_lookup.lookup_value_code
   AND lang_lookup.lookup_type_code = 'LANG'
