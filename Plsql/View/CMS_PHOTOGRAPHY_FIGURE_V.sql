CREATE OR REPLACE VIEW CMS_PHOTOGRAPHY_FIGURE_V AS
SELECT pf.doc_id,
       figure_name,
       series_name,
       decode(dcr.category_id, -1, 'Œ¥∑÷¿‡', c.category_name) category_name,
       photographer,
       photography_time,
       figure_format,
       photography_location,
       keyword,
       figure_desc,
       remarks,
       pf.figure_x_resolution,
       pf.figure_y_resolution,
       pf.figure_resolution,
       pf.figure_size,
       pf.figure_width,
       pf.figure_height
  FROM cms_photography_figure_t pf,
       cms_category_t           c,
       cms_doc_category_rel_t   dcr
 WHERE pf.doc_id = dcr.doc_id
   AND dcr.category_id = c.category_id(+)

