CREATE OR REPLACE VIEW CMS_RES_TEMP_SHELF_PUB_V AS
SELECT h.doc_id,
       NULL           rel_id,
       rci.res_name   attachment_name,
       NULL           res_name,
       h.storage_type,
       h.user_id,
       h.comp_code
  FROM cms_res_temp_shelf_pub_h_t h, cms_res_common_info_v rci
 WHERE h.storage_type = 'FOLDER'
   AND h.doc_id = rci.doc_id
UNION ALL
SELECT h.doc_id,
       l.rel_id,
       dur.file_name attachment_name,
       er.res_name,
       h.storage_type,
       h.user_id,
       h.comp_code
  FROM cms_res_temp_shelf_pub_h_t h,
       cms_res_temp_shelf_pub_l_t l,
       cms_doc_ucm_rel_t          dur,
       cms_exp_res_v              er,
       cms_doc_t                  d
 WHERE h.shelf_header_id = l.shelf_header_id
   AND l.rel_id = dur.rel_id
   AND h.storage_type = 'FILE'
   AND er.attribute1 = dur.ftp_path
   AND dur.doc_id = d.doc_id
   AND d.lib_code = er.lib_code
