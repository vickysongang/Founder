CREATE OR REPLACE VIEW CMS_ILLUSTRATION_V AS
SELECT i.doc_id,
       illustration_name,
       illustration_source,
       decode(dcr.category_id, -1, 'Œ¥∑÷¿‡', c.category_name) category_name,
       illustration_author,
       map_approve_num,
       page_number,
       keyword,
       illustration_desc,
       figure_format,
       remarks,
       i.figure_width,
       i.figure_height,
       i.figure_x_resolution,
       i.figure_y_resolution,
       i.copyright_flag,
       i.figure_resolution,
       i.cover_design,
       i.figure_size,
       i.figure_dimension
  FROM cms_illustration_t i, cms_category_t c, cms_doc_category_rel_t dcr
 WHERE i.doc_id = dcr.doc_id(+)
   AND dcr.category_id = c.category_id(+)
