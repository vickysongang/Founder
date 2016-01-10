CREATE OR REPLACE VIEW CMS_RES_EXP_STATUS_ROLE_V AS
SELECT 'TO_FIRST_TRIAL_APPLY' status,
       '¥˝≥ı…Û' status_name,
       '≥ı…Û' action,
       'FIRST_TRIAL' role_code,
       10 seq
  FROM dual
UNION ALL
SELECT 'TO_TRIAL_APPLY' status,
       '¥˝∏¥…Û' status_name,
       '∏¥…Û' action,
       'TRIAL' role_code,
       20 seq
  FROM dual
UNION ALL
SELECT 'TO_FINAL_JUDGMENT_APPLY' status,
       '¥˝÷’…Û' status_name,
       '÷’…Û' action,
       'FINAL_JUDGMENT' role_code,
       30 seq
  FROM dual
UNION ALL
SELECT 'APPROVED_APPLY' status,
       '“—…Û≈˙' status_name,
       NULL action,
       NULL role_code,
       40 seq
  FROM dual
UNION ALL
SELECT 'REJECTED_APPLY' status,
       '“—æ‹æ¯' status_name,
       NULL action,
       NULL role_code,
       40 seq
  FROM dual
UNION ALL
SELECT 'TO_SUBMIT_APPLY' status,
       '¥˝Ã·Ωª' status_name,
       NULL action,
       NULL role_code,
       50 seq
  FROM dual
