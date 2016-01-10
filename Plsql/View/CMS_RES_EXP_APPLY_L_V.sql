CREATE OR REPLACE VIEW CMS_RES_EXP_APPLY_L_V AS
SELECT real.head_id,
       real.line_id,
       rts.shelf_id,
       rts.doc_id,
       rts.res_name,
       rts.category_code lib_code,
       rts.user_id,
       rts.delete_flag,
       rts.apply_submit_flag,
       rts.keyword,
       rts.author,
       rts.res_id
  FROM cms_res_exp_apply_l_t REAL, cms_res_temp_shelf_v rts
 WHERE real.shelf_id = rts.shelf_id
 
 
                               
