CREATE OR REPLACE VIEW CMS_THEME_RESOURCE_ORIGIN_V AS
SELECT b.book_name title,
       b.creation_date,
       b.isbn,
       b.keyword,
       b.pub_time,
       d.lib_code,
       b.author,
       d.doc_id
  FROM cms_book_t b, cms_doc_t d
 WHERE b.doc_id = d.doc_id
UNION ALL
--“Ù∆µø‚
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
-- ”∆µø‚
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
--Àÿ≤ƒÕºø‚
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
--…„”∞Õºø‚
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
--≤ÂÕºø‚
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
--ΩÃ∞∏ø‚
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
--øŒº˛ø‚
SELECT eet.entry_header title,
       eet.creation_date,
       NULL isbn,
       eet.keyword,
       eet.creation_date pub_time,
       d.lib_code,
       NULL author,
       eet.doc_id
  FROM cms_encyclopedias_entry_t eet, cms_doc_t d
 WHERE eet.doc_id = d.doc_id;
