CREATE OR REPLACE VIEW CMS_RES_TEMP_SHELF_V AS
SELECT rts.shelf_id,
       rts.doc_id,
       rci.lib_code category_code,
       rts.user_id,
       nvl(rts.delete_flag, 'N') delete_flag,
       rts.creation_date,
       nvl(rts.apply_submit_flag, 'N') apply_submit_flag,
       rci.res_name,
       rci.keyword,
       rci.author,
       rci.res_id
  FROM cms_res_temp_shelf_t rts, cms_res_common_info_v rci
 WHERE rts.doc_id = rci.doc_id;
