CREATE OR REPLACE VIEW CMS_RES_COMMON_INFO_V AS
SELECT b.book_id res_id,
       b.doc_id,
       b.book_name res_name,
       (b.book_name || ',' || b.isbn || ',' || b.edition || ',' ||
       b.impression ) unique_res_name,
       b.keyword,
       b.author,
       'BOOK' lib_code,
       b.last_update_date,
       b.item_code,
       b.isbn,
       b.editor,
       b.pub_time,
       NULL res_source
  FROM cms_book_t b
UNION ALL
SELECT a.audio_id res_id,
       a.doc_id,
       a.file_name res_name,
       a.file_name unique_res_name,
       a.keyword,
       a.record_user author,
       'AUDIO' lib_code,
       a.last_update_date,
       a.item_code,
       a.isbn,
       a.editor,
       a.pub_time,
       NULL res_source
  FROM cms_audio_t a
UNION ALL
SELECT v.video_id res_id,
       v.doc_id,
       v.file_name res_name,
       v.file_name unique_res_name,
       v.keyword,
       v.producer author,
       'VIDEO' lib_code,
       v.last_update_date,
       v.item_code,
       v.isbn,
       v.editor,
       v.pub_time,
       NULL res_source
  FROM cms_video_t v
UNION ALL
SELECT p.periodical_id res_id,
       p.doc_id,
       pc.periodical_category_name || '(' || ps.year || '-' ||
       ps.period_num || ')' res_name,
       pc.periodical_category_name || '(' || ps.year || '-' ||
       ps.period_num || ')' unique_res_name,
       p.keyword,
       p.editor author,
       'PERIODICAL' lib_code,
       p.last_update_date,
       NULL item_code,
       pc.issn isbn,
       p.editor,
       p.pub_time,
       NULL res_source
  FROM cms_periodical_t          p,
       cms_periodical_category_v pc,
       cms_period_setup_t        ps
 WHERE p.periodical_category_id = pc.periodical_category_id
   AND p.period_id = ps.setup_id
UNION ALL
SELECT i.illustration_id res_id,
       i.doc_id,
       i.illustration_name res_name,
       i.illustration_name unique_res_name,
       i.keyword,
       i.illustration_author author,
       'ILLUSTRATION' lib_code,
       i.last_update_date,
       NULL item_code,
       NULL isbn,
       NULL editor,
       i.figure_store_time pub_time,
       i.illustration_source res_source
  FROM cms_illustration_t i
UNION ALL
SELECT pf.figure_id res_id,
       pf.doc_id,
       pf.figure_name res_name,
       pf.figure_name unique_res_name,
       pf.keyword,
       pf.photographer author,
       'PHOTOGRAPHY_FIGURE' lib_code,
       pf.last_update_date,
       NULL item_code,
       NULL isbn,
       NULL editor,
       pf.photography_time pub_time,
       NULL res_source
  FROM cms_photography_figure_t pf
UNION ALL
SELECT mf.figure_id res_id,
       mf.doc_id,
       mf.figure_name res_name,
       mf.figure_name unique_res_name,
       mf.keyword,
       mf.figure_author author,
       'MATERIAL_FIGURE' lib_code,
       mf.last_update_date,
       NULL item_code,
       NULL isbn,
       NULL editor,
       mf.figure_store_time pub_time,
       NULL res_source
  FROM cms_material_figure_t mf
UNION ALL
SELECT ep.elec_prod_id res_id,
       ep.doc_id,
       ep.elec_prod_name res_name,
       ep.elec_prod_name unique_res_name,
       ep.keyword,
       ep.editor author,
       'ELEC_PROD' lib_code,
       ep.last_update_date,
       ep.item_code,
       ep.isbn,
       ep.editor,
       ep.pub_time,
       NULL res_source
  FROM cms_elec_prod_t ep
UNION ALL
SELECT n.newspaper_id res_id,
       n.doc_id,
       nc.newspaper_category_name || '(' || ns.year || '-' || ns.news_num || ')' res_name,
       nc.newspaper_category_name || '(' || ns.year || '-' || ns.news_num || ')' unique_res_name,
       n.keyword,
       n.editor author,
       'NEWSPAPER' lib_code,
       n.last_update_date,
       NULL item_code,
       nc.issn isbn,
       n.editor,
       n.pub_time,
       NULL res_source
  FROM cms_newspaper_t n, cms_newspaper_category_v nc, cms_news_setup_t ns
 WHERE n.newspaper_category_id = nc.newspaper_category_id
   AND n.news_id = ns.setup_id
UNION ALL
SELECT c.courseware_id res_id,
       c.doc_id,
       c.courseware_name res_name,
       c.courseware_name unique_res_name,
       c.keyword,
       c.teaching_material_author author,
       'COURSEWARE' lib_code,
       c.last_update_date,
       NULL item_code,
       c.teaching_material_isbn isbn,
       NULL editor,
       NULL pub_time,
       NULL res_source
  FROM cms_courseware_t c
UNION ALL
SELECT tp.teaching_plan_id res_id,
       tp.doc_id,
       tp.teaching_plan_name res_name,
       tp.teaching_plan_name unique_res_name,
       tp.keyword,
       tp.editor author,
       'TEACHING_PLAN' lib_code,
       tp.last_update_date,
       NULL item_code,
       tp.source_book_isbn ibsn,
       tp.editor,
       NULL pub_time,
       NULL res_source
  FROM cms_teaching_plan_t tp
UNION ALL
SELECT ac.activity_id res_id,
       ac.doc_id,
       ac.activity_theme res_name,
       ac.activity_theme unique_res_name,
       NULL keyword,
       NULL author,
       'ACTIVITY' lib_code,
       ac.last_update_date,
       NULL item_code,
       NULL ibsn,
       NULL editor,
       NULL pub_time,
       NULL res_source
  FROM cms_activity_t ac
UNION ALL
SELECT we.entry_id res_id,
       we.doc_id,
       we.title res_name,
       we.title unique_res_name,
       we.keyword,
       we.author,
       'WORKS_ENTRY' lib_code,
       we.last_update_date,
       NULL item_code,
       NULL ibsn,
       NULL editor,
       we.store_time pub_time,
       NULL res_source
  FROM cms_works_entry_t we
UNION ALL
SELECT ee.entry_id res_id,
       ee.doc_id,
       ee.entry_header res_name,
       ee.entry_header unique_res_name,
       ee.keyword,
       NULL author,
       'ENCYCLOPEDIAS_ENTRY' lib_code,
       ee.last_update_date,
       NULL item_code,
       NULL ibsn,
       NULL editor,
       ee.store_time pub_time,
       NULL res_source
  FROM cms_encyclopedias_entry_t ee
