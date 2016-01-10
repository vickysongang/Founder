CREATE OR REPLACE VIEW CMS_APPLY_DETAIL_INFO_V AS
SELECT t.apply_id,
       t.apply_name,
       t.reason,
       t.remarks,
       t.status,
       u.diplay_name,
       'DOWNLOAD' apply_type
  FROM cms_res_download_apply_h_t t, cms_user_t u
 WHERE t.user_id = u.user_id
UNION ALL
SELECT t.apply_id,
       t.apply_name,
       lookup.meaning reason,
       t.remarks,
       t.status,
       u.diplay_name,
       'EXP' apply_type
  FROM cms_res_exp_apply_h_t t, cms_user_t u, cms_lookup_value_t lookup
 WHERE t.user_id = u.user_id
   AND t.attribute1 = lookup.lookup_value_code(+)
   AND lookup.lookup_type_code(+) = 'EXPORT_REASON';
