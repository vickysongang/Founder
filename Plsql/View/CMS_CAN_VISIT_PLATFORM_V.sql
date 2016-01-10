CREATE OR REPLACE VIEW CMS_CAN_VISIT_PLATFORM_V AS
SELECT v.role_type, v.role_type_name, v.user_name,v.seq
  FROM cms_user_platform_rel_v v
UNION
SELECT 'WORK_PLATFORM' role_type, '工作平台' role_type_name, u.user_name,10 seq
  FROM cms_user_t u, cms_comp_v c
 WHERE u.comp_code = c.comp_code
   AND nvl(c.group_flag, 'N') = 'Y'
UNION
SELECT 'PUB_PLATFORM' role_type, '发布平台' role_type_name, u.user_name,20 seq
  FROM cms_user_t u, cms_comp_v c
 WHERE u.comp_code = c.comp_code
   AND nvl(c.group_flag, 'N') = 'Y';
