CREATE OR REPLACE VIEW CMS_DOC_RETRIEVAL_V AS
SELECT b.book_name title, b.creation_date,b.isbn, b.keyword,b.pub_time, d.lib_code, b.author, d.doc_id
  FROM cms_book_t b, cms_doc_t d
 WHERE b.doc_id = d.doc_id
UNION ALL
--电子音像库
SELECT e.elec_prod_name title,
       e.creation_date,
       e.isbn,
       e.keyword,
       e.pub_time,
       d.lib_code,
       NULL author,
       d.doc_id
  FROM cms_elec_prod_t e, cms_doc_t d
 WHERE e.doc_id = d.doc_id
--期刊库
UNION ALL
SELECT (pcv.periodical_category_name || '(' || ps.YEAR || '年第' ||
       ps.period_num || '期)') title,
       p.creation_date,
       pcv.issn isbn,
       p.keyword,
       p.pub_time,
       d.lib_code,
       NULL author,
       d.doc_id
  FROM cms_periodical_t          p,
       cms_doc_t                 d,
       cms_periodical_category_v pcv,
       cms_period_setup_t        ps
 WHERE p.doc_id = d.doc_id
   AND p.periodical_category_id = pcv.periodical_category_id
   AND p.period_id = ps.setup_id(+)
UNION ALL
--报纸库
SELECT (ncv.newspaper_category_name || '(' || ns.YEAR || '年第' ||
       ns.news_num || '期)') title,
       n.creation_date,
       ncv.issn isbn,
       n.keyword,
       n.pub_time,
       d.lib_code,
       NULL author,
       d.doc_id
  FROM cms_newspaper_t           n,
       cms_doc_t                 d,
       cms_newspaper_category_v  ncv,
       cms_news_setup_t        ns
 WHERE n.doc_id = d.doc_id
   AND n.newspaper_category_id = ncv.newspaper_category_id
   AND n.news_id = ns.setup_id(+)
UNION ALL
--音频库
SELECT a.file_name title,
       a.creation_date,
       a.isbn,
       a.keyword,
       a.pub_time,
       d.lib_code,
       NULL author,
       d.doc_id
  FROM cms_audio_t a, cms_doc_t d
 WHERE a.doc_id = d.doc_id
UNION ALL
--视频库
SELECT v.file_name title,
       v.creation_date,
       v.isbn,
       v.keyword,
       v.pub_time,
       d.lib_code,
       NULL author,
       d.doc_id
  FROM cms_video_t v, cms_doc_t d
 WHERE v.doc_id = d.doc_id
UNION ALL
--素材图库
SELECT mf.figure_name title,
       mf.creation_date,
       NULL isbn,
       mf.keyword,
       mf.creation_date pub_time,
       d.lib_code,
       NULL author,
       d.doc_id
  FROM cms_material_figure_t mf, cms_doc_t d
 WHERE mf.doc_id = d.doc_id
UNION ALL
--摄影图库
SELECT pf.figure_name title,
       pf.creation_date,
       NULL isbn,
       pf.keyword,
       pf.creation_date pub_time,
       d.lib_code,
       NULL author,
       d.doc_id
  FROM cms_photography_figure_t pf, cms_doc_t d
 WHERE pf.doc_id = d.doc_id
UNION ALL
--插图库
SELECT i.illustration_name title,
       i.creation_date,
       NULL isbn,
       i.keyword,
       i.creation_date pub_time,
       d.lib_code,
       NULL author,
       d.doc_id
  FROM cms_illustration_t i, cms_doc_t d
 WHERE i.doc_id = d.doc_id
UNION ALL
--教案库
SELECT tp.teaching_plan_name title,
       tp.creation_date,
       NULL isbn,
       tp.keyword,
       tp.creation_date pub_time,
       d.lib_code,
       NULL author,
       d.doc_id
  FROM cms_teaching_plan_t tp, cms_doc_t d
 WHERE tp.doc_id = d.doc_id
UNION ALL
--课件库
SELECT c.courseware_name title,
       c.creation_date,
       NULL isbn,
       c.keyword,
       c.creation_date pub_time,
       d.lib_code,
       NULL author,
       d.doc_id
  FROM cms_courseware_t c, cms_doc_t d
 WHERE c.doc_id = d.doc_id
 UNION ALL 
 --论著条目库
SELECT et.title title,
       et.creation_date,
       NULL isbn,
       et.keyword,
       et.creation_date pub_time,
       d.lib_code,
       NULL author,
       d.doc_id
  FROM cms_works_entry_t et, cms_doc_t d
 WHERE et.doc_id = d.doc_id
UNION ALL
 --百科条目库
SELECT eet.entry_header title,
       eet.creation_date,
       NULL isbn,
       eet.keyword,
       eet.creation_date pub_time,
       d.lib_code,
       NULL author,
       d.doc_id
  FROM cms_encyclopedias_entry_t eet, cms_doc_t d
 WHERE eet.doc_id = d.doc_id;
