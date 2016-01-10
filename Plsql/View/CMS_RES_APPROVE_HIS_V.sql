CREATE OR REPLACE VIEW CMS_RES_APPROVE_HIS_V AS
SELECT rdah.apply_id,
       u.diplay_name,
       '���ύ' from_position,
       '������' to_position,
       to_char(rdah.creation_date, 'yyyy-MM-dd hh24:mi:ss') start_time,
       decode(rdah.status,'TO_SUBMIT_APPLY','�½�','�ύ') operation,
       'DOWNLOAD' apply_type
  FROM cms_res_download_apply_h_t rdah, cms_user_t u
 WHERE rdah.created_by = u.user_id
UNION ALL
 SELECT rdah.apply_id,
       u.diplay_name,
       '���ύ' from_position,
       '������' to_position,
       to_char(rdah.creation_date, 'yyyy-MM-dd hh24:mi:ss') start_time,
       decode(rdah.status,'TO_SUBMIT_APPLY','�½�','�ύ') operation,
       'EXP' apply_type
  FROM cms_res_exp_apply_h_t rdah, cms_user_t u
 WHERE rdah.created_by = u.user_id
UNION ALL
SELECT reah.apply_id,
       u.diplay_name,
       decode(reah.action,
              '����',
              '������',
              '����',
              '������',
              '����',
              '������') from_position,
       cms_pub_platform_pkg.get_approve_to_position(reah.his_id) to_position,
       to_char(reah.creation_date, 'yyyy-MM-dd hh24:mi:ss') start_time,
       reah.action operation,
       reah.apply_type
  FROM cms_res_exp_approval_his_t reah, cms_user_t u
 WHERE reah.approver_id = u.user_id(+)
 ORDER BY start_time;
