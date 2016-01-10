CREATE OR REPLACE VIEW CMS_USER_PLATFORM_REL_V AS
SELECT DISTINCT r.role_type,
                lv.meaning role_type_name,
                u.user_name,
                u.user_id,
                lv.seq
  FROM cms_role_user_rel_t rur,
       cms_user_t          u,
       cms_role_t          r,
       cms_lookup_value_v  lv
 WHERE rur.user_id = u.user_id
   AND rur.role_id = r.role_id
   AND r.enable_flag = 'Y'
   AND u.enable_flag = 'Y'
   AND lv.lookup_type_code = 'PLATFORM_TYPE'
   AND lv.lookup_value_code = r.role_type
   AND u.user_name <> 'SYSADMIN'
UNION ALL
SELECT 'ADMIN_PLATFORM' role_type,
       lv.meaning role_type_name,
       u.user_name,
       u.user_id,
       lv.seq
  FROM cms_user_t u, cms_lookup_value_v lv
 WHERE u.user_name = 'SYSADMIN'
   AND lv.lookup_type_code = 'PLATFORM_TYPE'
   AND lv.lookup_value_code = 'ADMIN_PLATFORM';
