CREATE OR REPLACE VIEW CMS_RES_EXP_STATUS_ROLE_V AS
SELECT 'TO_FIRST_TRIAL_APPLY' status,
       '������' status_name,
       '����' action,
       'FIRST_TRIAL' role_code,
       10 seq
  FROM dual
UNION ALL
SELECT 'TO_TRIAL_APPLY' status,
       '������' status_name,
       '����' action,
       'TRIAL' role_code,
       20 seq
  FROM dual
UNION ALL
SELECT 'TO_FINAL_JUDGMENT_APPLY' status,
       '������' status_name,
       '����' action,
       'FINAL_JUDGMENT' role_code,
       30 seq
  FROM dual
UNION ALL
SELECT 'APPROVED_APPLY' status,
       '������' status_name,
       NULL action,
       NULL role_code,
       40 seq
  FROM dual
UNION ALL
SELECT 'REJECTED_APPLY' status,
       '�Ѿܾ�' status_name,
       NULL action,
       NULL role_code,
       40 seq
  FROM dual
UNION ALL
SELECT 'TO_SUBMIT_APPLY' status,
       '���ύ' status_name,
       NULL action,
       NULL role_code,
       50 seq
  FROM dual
