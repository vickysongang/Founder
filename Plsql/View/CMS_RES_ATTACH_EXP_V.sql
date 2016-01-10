CREATE OR REPLACE VIEW CMS_RES_ATTACH_EXP_V AS
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
       er.attribute1 file_path,
       rts.shelf_id,
       rts.doc_id,
       rts.res_id,
       dur.ucm_did,
       dur.ucm_docname,
       dur.file_name,
       dur.attribute15--存储UCM系统与数据库断裂了关系的文件
  FROM cms_res_exp_apply_h_t  reah,
       cms_res_exp_apply_l_t  REAL,
       cms_res_template_t     rt,
       cms_res_template_rel_t rtr,
       cms_exp_res_v          er,
       cms_res_temp_shelf_v   rts,
       cms_doc_ucm_rel_t      dur
 WHERE reah.apply_id = real.head_id
   AND reah.template_id = rt.template_id
   AND rt.template_id = rtr.template_id
   AND reah.batch_code = rtr.batch_code
   AND rtr.res_code = er.res_code
   AND rtr.res_type_code = er.res_type_code
   AND real.shelf_id = rts.shelf_id
   AND rts.doc_id = dur.doc_id
   AND er.attribute1 = dur.ftp_path
   AND substr(er.res_type_code, 1, instr(er.res_type_code, '#') - 1) =
       'ATTACH_TYPE'
