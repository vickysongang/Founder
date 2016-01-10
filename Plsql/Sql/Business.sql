--获取指定用户所属的平台角色,用于登录系统的平台选择.
SELECT 'ADMIN_PLATFORM' role_code, '管理平台' role_name
  FROM cms_role_t r, cms_role_user_rel_t rur
 WHERE r.role_type = 'ADMIN_PLATFORM'
   AND r.role_code = rur.role_code
   AND r.enable_flag = 'Y'
   AND rur.user_name = 'LIHENG'
   AND rownum = 1
UNION ALL
SELECT 'WORK_PLATFORM' role_code, '工作平台' role_name
  FROM cms_role_t r, cms_role_user_rel_t rur
 WHERE r.role_type = 'WORK_PLATFORM'
   AND r.role_code = rur.role_code
   AND r.enable_flag = 'Y'
   AND rur.user_name = 'LIHENG'
   AND rownum = 1
UNION ALL
SELECT 'PUB_PLATFORM' role_code, '发布平台' role_name
  FROM cms_role_t r, cms_role_user_rel_t rur
 WHERE r.role_type = 'PUB_PLATFORM'
   AND r.role_code = rur.role_code
   AND r.enable_flag = 'Y'
   AND rur.user_name = 'LIHENG';

--获取指定出版社下的用户信息,包括用户的角色信息.
SELECT u.user_id,
       u.user_name,
       u.diplay_name,
       u.enable_flag,
       u.comp_code,
       cms_common_pkg.is_comp_admin(u.user_name, u.comp_code) is_comp_admin,
       cms_common_pkg.get_role_str(u.user_name, 'ADMIN_PLATFORM', 'NAME') admin_platform_role,
       cms_common_pkg.get_role_str(u.user_name, 'WORK_PLATFORM', 'NAME') work_platform_role,
       cms_common_pkg.get_role_str(u.user_name, 'PUB_PLATFORM', 'NAME') pub_platform_role
  FROM cms_user_t u
 WHERE u.comp_code = 'DAXIANG';

--管理平台角色进行系统一次性获取必要的角色权限等信息.
SELECT u.user_id,
       u.user_name,
       u.diplay_name,
       u.enable_flag,
       cms_common_pkg.get_admin_comp_str(u.user_name) comp_str
  FROM cms_user_t u
 WHERE u.user_name = 'LIHENG';

--获取指定出版社下的工作角色
SELECT r.role_id, r.role_code, r.role_name, r.comp_code
  FROM cms_role_t r
 WHERE r.role_type = 'WORK_PLATFORM'
   AND r.enable_flag = 'Y'
   AND r.comp_code = 'DAXIANG';

--根据管理员所管辖的出版社查询对应的出版社
SELECT lv.lookup_value_id,
       lv.lookup_value_code comp_code,
       lv.meaning           comp_name,
       lv.seq
  FROM cms_lookup_value_v lv
 WHERE lv.lookup_type_code = 'COMP'
   AND ('all' = 'ALL' OR
       lv.lookup_value_code IN
       (SELECT *
           FROM TABLE(CAST(cms_common_pkg.parse_string('asdf,asfd') AS
                           cms_table_type))))
 ORDER BY seq;

--查询某角色下的用户
SELECT u.user_id,
       u.user_name,
       u.diplay_name,
       u.enable_flag,
       r.role_code,
       r.role_type,
       r.comp_code
  FROM cms_user_t u, cms_role_user_rel_t rur, cms_role_t r
 WHERE rur.role_code = r.role_code
   AND rur.role_type = r.role_type
   AND rur.comp_code = r.comp_code
   AND rur.user_name = u.user_name

--获取没有指定角色的用户信息
  SELECT u.user_id,
         u.diplay_name || '(' || u.user_name || ')' display_name,
         u.comp_code
          FROM cms_user_t u
         WHERE u.user_name NOT IN
               (SELECT 'SYSADMIN'
                  FROM dual
                UNION ALL
                SELECT rur.user_name
                  FROM cms_role_user_rel_t rur, cms_role_t r
                 WHERE rur.role_code = r.role_code
                   AND rur.comp_code = r.comp_code
                   AND rur.role_type = r.role_type
                   AND r.role_id = :proleid)
           AND u.enable_flag = 'Y';


--获取指定出版社的第一层库信息
SELECT DISTINCT lib_code, lib_name, comp_code, seq
  FROM (SELECT clr.lib_type_code   lib_code,
               lt.lookup_type_name lib_name,
               clr.comp_code,
               lt.seq
          FROM cms_comp_lib_rel_t clr, cms_lookup_type_t lt
         WHERE clr.lib_type_code = lt.lookup_type_code)
 ORDER BY seq;

--根据指定出版社及第一层库信息获取第二层库信息
SELECT DISTINCT lib_code, lib_name, comp_code, lib_type_code, seq
  FROM (SELECT clr.lib_code,
               lv.meaning lib_name,
               clr.comp_code,
               clr.lib_type_code,
               lv.seq
          FROM cms_comp_lib_rel_t clr, cms_lookup_value_v lv
         WHERE clr.lib_code = lv.lookup_value_code
           AND clr.lib_type_code = lv.lookup_type_code)
 ORDER BY seq;

--根据库获取分类信息
SELECT c.category_id,
       c.category_code,
       c.category_name,
       c.comp_code,
       c.seq,
       lcgr.lib_code,
       lcgr.lib_type_code,
       lcgr.primary_flag
  FROM cms_category_t c, cms_lib_category_g_rel_t lcgr
 WHERE c.parent_node_code = lcgr.category_group_code
   AND c.comp_code = lcgr.comp_code
   AND lcgr.primary_flag = p_primary_flag
   AND lcgr.lib_code = p_lib_code
   AND lcgr.lib_type_code = p_lib_type_code
   AND lcgr.comp_code = p_comp_code
 ORDER BY seq;

--获取指定角色对第一层库的可见信息.
SELECT fll.lib_code,
       fll.lib_name,
       fll.comp_code,
       nvl((SELECT 'Y'
             FROM cms_role_lib_rel_t rlr, cms_second_level_lib_v sll
            WHERE rlr.lib_code = sll.lib_code
              AND rlr.lib_type_code = sll.lib_type_code
              AND sll.lib_type_code = fll.lib_code
              AND fll.comp_code = sll.comp_code
              AND rlr.role_id = 137
              AND rownum = 1),
           'N') visual_flag
  FROM cms_first_level_lib_v fll
 WHERE fll.comp_code = 'DAXIANG';

--获取指定角色对第二层库的可见信息.
SELECT sll.lib_code,
       sll.lib_name,
       sll.comp_code,
       sll.lib_type_code,
       nvl((SELECT 'Y'
             FROM cms_role_lib_rel_t rlr
            WHERE rlr.lib_code = sll.lib_code
              AND rlr.lib_type_code = sll.lib_type_code
              AND rlr.role_id = 137),
           'N') visual_flag
  FROM cms_second_level_lib_v sll
 WHERE sll.comp_code = 'DAXIANG';

--工作平台登录后显示第一层库信息
SELECT fll.lib_code,
       fll.lib_name,
       fll.comp_code,
       sll.lib_code lib_code1,
       u.user_name,
       r.role_type,
       r.role_id
  FROM cms_first_level_lib_v  fll,
       cms_second_level_lib_v sll,
       cms_role_user_rel_t    rur,
       cms_role_lib_rel_t     rlr,
       cms_role_t             r,
       cms_user_t             u
 WHERE fll.lib_code = sll.lib_type_code
   AND fll.comp_code = sll.comp_code
   AND fll.comp_code = r.comp_code
   AND rlr.role_id = r.role_id
   AND rur.role_id = r.role_id
   AND r.comp_code = sll.comp_code
   AND rur.user_id = u.user_id
   AND u.user_name = 'JIANGMINGLEI'
   AND rlr.lib_code = sll.lib_code
   AND r.role_type = 'WORK_PLATFORM'
   AND rlr.lib_type_code = sll.lib_type_code
   AND fll.comp_code = 'DAXIANG'

--工作平台登录后根据第一层库信息显示第二层库信息  
  SELECT sll.lib_code, sll.lib_type_code, sll.lib_name, sll.comp_code
          FROM cms_second_level_lib_v sll,
         cms_role_user_rel_t    rur,
         cms_role_lib_rel_t     rlr,
         cms_role_t             r,
         cms_user_t             u
         WHERE sll.comp_code = r.comp_code
           AND r.role_id = rur.role_id
           AND r.role_type = 'WORK_PLATFORM'
           AND rur.user_id = u.user_id
           AND u.user_name = 'JIANGMINGLEI'
           AND rlr.role_id = r.role_id
           AND rlr.lib_code = sll.lib_code
           AND rlr.lib_type_code = sll.lib_type_code
           AND sll.lib_type_code = 'END_PROD_LIB'
           AND sll.comp_code = 'DAXIANG'
        
        --获取指定用户指定类型角色对应的出版社信息.
          SELECT DISTINCT r.comp_code, c.comp_name
                  FROM cms_role_user_rel_t rur, cms_role_t r, cms_comp_v c
                 WHERE r.role_id = rur.role_id
                   AND r.comp_code = c.comp_code
                   AND r.role_type = 'WORK_PLATFORM'
                   AND r.enable_flag = 'Y'
                   AND rur.user_id = 5
                   
--获取指定DOC在UCM上对应的记录
SELECT d.doc_id, dur.ucm_doc_name, dur.file_name
  FROM cms_doc_t d, cms_doc_ucm_rel_t dur
 WHERE d.doc_id = dur.doc_id
   AND d.doc_id IN (SELECT *
                      FROM TABLE(CAST(cms_common_pkg.parse_string(:bvdocidstr) AS
                                      cms_table_type)))
