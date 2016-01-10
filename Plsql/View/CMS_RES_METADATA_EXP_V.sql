CREATE OR REPLACE VIEW CMS_RES_METADATA_EXP_V AS
SELECT reah.apply_id,
       reah.apply_name,
       real.line_id,
       rt.template_id,
       rt.template_name,
       er.res_code,
       er.res_name,
       er.res_type_code,
       er.res_type_name,
       substr(er.res_type_code, 1, instr(er.res_type_code, '#') - 1) res_type_simple_code,
       er.attribute1 column_name,
       er.attribute2 table_name,
       rts.shelf_id,
       rts.doc_id,
       rts.res_id
  FROM cms_res_exp_apply_h_t  reah,
       cms_res_exp_apply_l_t  REAL,
       cms_res_template_t     rt,
       cms_res_template_rel_t rtr,
       cms_exp_res_v          er,
       cms_res_temp_shelf_v   rts
 WHERE reah.apply_id = real.head_id
   AND reah.template_id = rt.template_id
   AND rt.template_id = rtr.template_id
   and reah.batch_code = rtr.batch_code
   AND rtr.res_code = er.res_code
   AND rtr.res_type_code = er.res_type_code
   AND real.shelf_id = rts.shelf_id
   AND substr(er.res_type_code, 1, instr(er.res_type_code, '#') - 1) <>
       'ATTACH_TYPE'
                               
