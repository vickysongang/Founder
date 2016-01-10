CREATE OR REPLACE VIEW CMS_DOC_DELETE_STATUS_V AS
SELECT 'DELETED_BOOK' status_code, '��ɾ��' status_name, 'BOOK' lib_code
  FROM dual
UNION ALL
SELECT 'DELETED_ELEC_PROD' status_code,
       '��ɾ��' status_name,
       'ELEC_PROD' lib_code
  FROM dual
UNION ALL
SELECT 'DELETED_PERIODICAL' status_code,
       '��ɾ��' status_name,
       'PERIODICAL' lib_code
  FROM dual
UNION ALL
SELECT 'DELETED_NEWSPAPER' status_code,
       '��ɾ��' status_name,
       'NEWSPAPER' lib_code
  FROM dual
UNION ALL
SELECT 'DELETED_ACTIVITY' status_code,
       '��ɾ��' status_name,
       'ACTIVITY' lib_code
  FROM dual
UNION ALL
SELECT 'DELETED_TEACHING_PLAN' status_code,
       '��ɾ��' status_name,
       'TEACHING_PLAN' lib_code
  FROM dual
UNION ALL
SELECT 'DELETED_COURSEWARE' status_code,
       '��ɾ��' status_name,
       'COURSEWARE' lib_code
  FROM dual
UNION ALL
SELECT 'DELETED_PHOTOGRAPHY' status_code,
       '��ɾ��' status_name,
       'PHOTOGRAPHY_FIGURE' lib_code
  FROM dual
UNION ALL
SELECT 'DELETED_MATERIAL' status_code,
       '��ɾ��' status_name,
       'MATERIAL_FIGURE' lib_code
  FROM dual
UNION ALL
SELECT 'DELETED_AUDIO' status_code, '��ɾ��' status_name, 'AUDIO' lib_code
  FROM dual
UNION ALL
SELECT 'DELETED_VIDEO' status_code, '��ɾ��' status_name, 'VIDEO' lib_code
  FROM dual
UNION ALL
SELECT 'DELETED_WORKS_ENTRY' status_code, '��ɾ��' status_name, 'WORKS_ENTRY' lib_code
  FROM dual
UNION ALL
SELECT 'DELETED_ENCYCLOPEDIAS_ENTRY' status_code, '��ɾ��' status_name, 'ENCYCLOPEDIAS_ENTRY' lib_code
  FROM dual
UNION ALL
  SELECT 'DELETED_TEMP_SHELF' status_code,
         '��ɾ��' status_name,
         'TEMP_SHELF' lib_code
    FROM dual;
  
