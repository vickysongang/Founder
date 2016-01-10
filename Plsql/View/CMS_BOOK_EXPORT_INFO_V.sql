CREATE OR REPLACE VIEW CMS_BOOK_EXPORT_INFO_V AS
SELECT b.BOOK_NAME,
       b.ISBN,
       b.IMPRESSION,
       b.EDITION,
       b.ITEM_CODE,
       b.SERIES,
       b.PUBLISHING_HOUSE,
       ct.CATEGORY_NAME,
       lang.meaning LANG_MEANING,
       b.EDITOR,
       b.AUTHOR,
       b.COVER_DESIGN,
       b.FORMAT_DESIGN,
       b.CHIEF_EDITOR,
       b.TRANSLATOR,
       b.PUB_TIME,
       b.PAGINATION,
       b.PRICE,
       b.SUGGESTION,
       readergroup.meaning READER_GROUP_MEANING,
       booksize.meaning BOOK_SIZE_MEANING,
       prodsize.meaning PROD_SIZE_MEANING,
       colornum.meaning COLOR_NUM_MEANING,
       b.FORMAT_COMP,
       assortprod.meaning ASSORT_PROD_MEANING,
       b.KEYWORD,
       b.REMARKS,
       mainpage.meaning MAIN_PAGE_MEANING,
       maintypeset.meaning MAIN_TYPESET_MEANING,
       covertypeset.meaning COVER_TYPESET_MEANING,
       docstatus.status_name,
       d.comp_code,
       d.status
  FROM cms_book_t             b,
       cms_doc_t              d,
       cms_doc_category_rel_t crt,
       cms_category_t         ct,
       cms_lookup_value_v     lang,
       cms_lookup_value_v     readergroup,
       cms_lookup_value_v     booksize,
       cms_lookup_value_v     prodsize,
       cms_lookup_value_v     colornum,
       cms_lookup_value_v     assortprod,
       cms_lookup_value_v     mainpage,
       cms_lookup_value_v     maintypeset,
       cms_lookup_value_v     covertypeset,
       cms_doc_status_v       docstatus
 WHERE b.doc_id = crt.doc_id(+)
   AND b.doc_id = d.doc_id
   AND crt.category_id = ct.category_id(+)
   AND b.lang = lang.lookup_value_code(+)
   AND lang.lookup_type_code(+) = 'LANG'
   AND b.reader_group = readergroup.lookup_value_code(+)
   AND readergroup.lookup_type_code(+) = 'READER_GROUP'
   AND b.book_size = booksize.lookup_value_code(+)
   AND booksize.lookup_type_code(+) = 'BOOK_SIZE'
   AND b.prod_size = prodsize.lookup_value_code(+)
   AND prodsize.lookup_type_code(+) = 'PROD_SIZE'
   AND b.color_num = colornum.lookup_value_code(+)
   AND colornum.lookup_type_code(+) = 'COLOR_NUM'
   AND b.assort_prod = assortprod.lookup_value_code(+)
   AND assortprod.lookup_type_code(+) = 'ASSORT_PROD'
   AND b.main_page = mainpage.lookup_value_code(+)
   AND mainpage.lookup_type_code(+) = 'MAIN_PAGE'
   AND b.main_typeset = maintypeset.lookup_value_code(+)
   AND maintypeset.lookup_type_code(+) = 'TYPESET'
   AND b.cover_typeset = covertypeset.lookup_value_code(+)
   AND covertypeset.lookup_type_code(+) = 'TYPESET'
   AND d.status = docstatus.status_code
