CREATE OR REPLACE VIEW CMS_DOC_REL_V AS 
--1ͼ���
SELECT b.book_name title,
       b.last_update_date,
       d.lib_code,
       d.status,
       d.doc_id,
       drt.parent_doc_id,
       drt.rel_type
  FROM cms_doc_t d, cms_doc_rel_t drt, cms_book_t b
 WHERE d.doc_id = drt.doc_id
   AND d.doc_id = b.doc_id
UNION ALL
--2���������
SELECT e.elec_prod_name title,
       e.last_update_date,
       d.lib_code,
       d.status,
       d.doc_id,
       drt.parent_doc_id,
       drt.rel_type
  FROM cms_doc_t d, cms_doc_rel_t drt, cms_elec_prod_t e
 WHERE d.doc_id = drt.doc_id
   AND d.doc_id = e.doc_id
UNION ALL
--3�ڿ���
SELECT (pcv.periodical_category_name || '(' || ps.YEAR || '���' ||
       ps.period_num || '��)') title,
       p.last_update_date,
       d.lib_code,
       d.status,
       d.doc_id,
       drt.parent_doc_id,
       drt.rel_type
  FROM cms_doc_t                 d,
       cms_doc_rel_t             drt,
       cms_periodical_t          p,
       cms_periodical_category_v pcv,
       cms_period_setup_t        ps
 WHERE d.doc_id = drt.doc_id
   AND d.doc_id = p.doc_id
   AND p.periodical_category_id = pcv.periodical_category_id
   AND p.period_id = ps.setup_id(+)
UNION ALL
--4��ֽ��
SELECT (ncv.newspaper_category_name || '(' || ns.YEAR || '���' ||
       ns.news_num || '��)') title,
       n.last_update_date,
       d.lib_code,
       d.status,
       d.doc_id,
       drt.parent_doc_id,
       drt.rel_type
  FROM cms_doc_t                 d,
       cms_doc_rel_t             drt,
       cms_newspaper_t           n,
       cms_newspaper_category_v  ncv,
       cms_news_setup_t          ns
 WHERE d.doc_id = drt.doc_id
   AND d.doc_id = n.doc_id
   AND n.newspaper_category_id = ncv.newspaper_category_id
   AND n.news_id = ns.setup_id(+)
UNION ALL
--5��Ƶ��
SELECT a.file_name title,
       a.last_update_date,
       d.lib_code,
       d.status,
       d.doc_id,
       drt.parent_doc_id,
       drt.rel_type
  FROM cms_doc_t d, cms_doc_rel_t drt, cms_audio_t a
 WHERE d.doc_id = drt.doc_id
   AND d.doc_id = a.doc_id
UNION ALL
--6��Ƶ��
SELECT v.file_name title,
       v.last_update_date,
       d.lib_code,
       d.status,
       d.doc_id,
       drt.parent_doc_id,
       drt.rel_type
  FROM cms_doc_t d, cms_doc_rel_t drt, cms_video_t v
 WHERE d.doc_id = drt.doc_id
   AND d.doc_id = v.doc_id
UNION ALL
--7�̰���
SELECT tpt.teaching_plan_name title,
       tpt.last_update_date,
       d.lib_code,
       d.status,
       d.doc_id,
       drt.parent_doc_id,
       drt.rel_type
  FROM cms_doc_t d, cms_doc_rel_t drt, cms_teaching_plan_t tpt
 WHERE d.doc_id = drt.doc_id
   AND d.doc_id = tpt.doc_id
UNION ALL
--8�μ���
SELECT c.courseware_name title,
       c.last_update_date,
       d.lib_code,
       d.status,
       d.doc_id,
       drt.parent_doc_id,
       drt.rel_type
  FROM cms_doc_t d, cms_doc_rel_t drt, cms_courseware_t c
 WHERE d.doc_id = drt.doc_id
   AND d.doc_id = c.doc_id
UNION ALL
--9�ز�ͼ��
SELECT m.figure_name title,
       m.last_update_date,
       d.lib_code,
       d.status,
       d.doc_id,
       drt.parent_doc_id,
       drt.rel_type
  FROM cms_doc_t d, cms_doc_rel_t drt, cms_material_figure_t m
 WHERE d.doc_id = drt.doc_id
   AND d.doc_id = m.doc_id
UNION ALL
--10��Ӱͼ��
SELECT p.figure_name title,
       p.last_update_date,
       d.lib_code,
       d.status,
       d.doc_id,
       drt.parent_doc_id,
       drt.rel_type
  FROM cms_doc_t d, cms_doc_rel_t drt, cms_photography_figure_t p
 WHERE d.doc_id = drt.doc_id
   AND d.doc_id = p.doc_id
UNION ALL
--11��ͼ��
SELECT i.illustration_name title,
       i.last_update_date,
       d.lib_code,
       d.status,
       d.doc_id,
       drt.parent_doc_id,
       drt.rel_type
  FROM cms_doc_t d, cms_doc_rel_t drt, cms_illustration_t i
 WHERE d.doc_id = drt.doc_id
   AND d.doc_id = i.doc_id
UNION ALL
--12������Ŀ��
SELECT we.title title,
       we.last_update_date,
       d.lib_code,
       d.status,
       d.doc_id,
       drt.parent_doc_id,
       drt.rel_type
  FROM cms_doc_t d, cms_doc_rel_t drt, cms_works_entry_t we
 WHERE d.doc_id = drt.doc_id
   AND d.doc_id = we.doc_id
UNION ALL
--13�ٿ���Ŀ��
SELECT ee.entry_header title,
       ee.last_update_date,
       d.lib_code,
       d.status,
       d.doc_id,
       drt.parent_doc_id,
       drt.rel_type
  FROM cms_doc_t d, cms_doc_rel_t drt, cms_encyclopedias_entry_t ee
 WHERE d.doc_id = drt.doc_id
   AND d.doc_id = ee.doc_id;
