CREATE OR REPLACE VIEW CMS_MATERIAL_FIGURE_V AS
SELECT mf.doc_id,
       figure_name,
       series_name,
       decode(dcr.category_id, -1, 'Œ¥∑÷¿‡', c.category_name) category_name,
       figure_author,
       figure_style,
       figurestylelv.meaning figure_style_meaing,
       figure_format,
       keyword,
       figure_desc,
       remarks,
       figure_code,
       figure_width,
       figure_height,
       figure_x_resolution,
       figure_y_resolution,
       figure_resolution,
       figure_size,
       mf.cover_design
  FROM cms_material_figure_t  mf,
       cms_category_t         c,
       cms_doc_category_rel_t dcr,
       cms_lookup_value_t figurestylelv
 WHERE mf.doc_id = dcr.doc_id
   AND dcr.category_id = c.category_id(+)
   AND mf.figure_style = figurestylelv.lookup_value_code
   AND figurestylelv.lookup_type_code = 'FIGURE_STYLE';
