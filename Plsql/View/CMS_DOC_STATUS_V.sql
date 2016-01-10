CREATE OR REPLACE VIEW CMS_DOC_STATUS_V AS
SELECT 'TO_GATHER_BOOK' status_code,
       '���ɼ�' status_name,
       'BOOK' lib_code,
       10 seq
  FROM dual
UNION ALL
SELECT 'TO_SYNC_BOOK' status_code,
       '��ͬ��' status_name,
       'BOOK' lib_code,
       20 seq
  FROM dual
UNION ALL
SELECT 'TO_INDEX_BOOK' status_code,
       '������' status_name,
       'BOOK' lib_code,
       30 seq
  FROM dual
UNION ALL
SELECT 'TO_APPROVE_BOOK' status_code,
       '������' status_name,
       'BOOK' lib_code,
       40 seq
  FROM dual
UNION ALL
SELECT 'PUBED_BOOK' status_code,
       '�ѷ���' status_name,
       'BOOK' lib_code,
       50 seq
  FROM dual
UNION ALL
SELECT 'TO_GATHER_ELEC_PROD' status_code,
       '���ɼ�' status_name,
       'ELEC_PROD' lib_code,
       10 seq
  FROM dual
UNION ALL
SELECT 'TO_SYNC_ELEC_PROD' status_code,
       '��ͬ��' status_name,
       'ELEC_PROD' lib_code,
       20 seq
  FROM dual
UNION ALL
SELECT 'TO_INDEX_ELEC_PROD' status_code,
       '������' status_name,
       'ELEC_PROD' lib_code,
       30 seq
  FROM dual
UNION ALL
SELECT 'TO_APPROVE_ELEC_PROD' status_code,
       '������' status_name,
       'ELEC_PROD' lib_code,
       40 seq
  FROM dual
UNION ALL
SELECT 'PUBED_ELEC_PROD' status_code,
       '�ѷ���' status_name,
       'ELEC_PROD' lib_code,
       50 seq
  FROM dual
UNION ALL
SELECT 'TO_INDEX_VIDEO' status_code,
       '������' status_name,
       'VIDEO' lib_code,
       10 seq
  FROM dual
UNION ALL
SELECT 'TO_APPROVE_VIDEO' status_code,
       '������' status_name,
       'VIDEO' lib_code,
       20 seq
  FROM dual
UNION ALL
SELECT 'PUBED_VIDEO' status_code,
       '�ѷ���' status_name,
       'VIDEO' lib_code,
       30 seq
  FROM dual
UNION ALL
SELECT 'TO_INDEX_AUDIO' status_code,
       '������' status_name,
       'AUDIO' lib_code,
       10 seq
  FROM dual
UNION ALL
SELECT 'TO_APPROVE_AUDIO' status_code,
       '������' status_name,
       'AUDIO' lib_code,
       20 seq
  FROM dual
UNION ALL
SELECT 'PUBED_AUDIO' status_code,
       '�ѷ���' status_name,
       'AUDIO' lib_code,
       30 seq
         FROM dual
UNION ALL
SELECT 'TO_GATHER_ACTIVITY' status_code,
       '���ɼ�' status_name,
       'ACTIVITY' lib_code,
       10 seq
         FROM dual
UNION ALL
SELECT 'TO_SYNC_ACTIVITY' status_code,
       '��ͬ��' status_name,
       'ACTIVITY' lib_code,
       20 seq
         FROM dual
UNION ALL
SELECT 'TO_INDEX_ACTIVITY' status_code,
       '������' status_name,
       'ACTIVITY' lib_code,
       30 seq
         FROM dual
UNION ALL
SELECT 'TO_APPROVE_ACTIVITY' status_code,
       '������' status_name,
       'ACTIVITY' lib_code,
       40 seq
  FROM dual
UNION ALL
SELECT 'PUBED_ACTIVITY' status_code,
       '�ѷ���' status_name,
       'ACTIVITY' lib_code,
       50 seq
  FROM dual
UNION ALL
SELECT 'TO_GATHER_TEACHING_PLAN' status_code,
       '���ɼ�' status_name,
       'TEACHING_PLAN' lib_code,
       10 seq
  FROM dual
  UNION ALL
SELECT 'TO_SYNC_TEACHING_PLAN' status_code,
       '��ͬ��' status_name,
       'TEACHING_PLAN' lib_code,
       20 seq
  FROM dual
UNION ALL
SELECT 'TO_INDEX_TEACHING_PLAN' status_code,
       '������' status_name,
       'TEACHING_PLAN' lib_code,
       30 seq
         FROM dual
UNION ALL
SELECT 'TO_APPROVE_TEACHING_PLAN' status_code,
       '������' status_name,
       'TEACHING_PLAN' lib_code,
       40 seq
  FROM dual
UNION ALL
SELECT 'PUBED_TEACHING_PLAN' status_code,
       '�ѷ���' status_name,
       'TEACHING_PLAN' lib_code,
       50 seq
  FROM dual
UNION ALL
SELECT 'TO_GATHER_COURSEWARE' status_code,
       '���ɼ�' status_name,
       'COURSEWARE' lib_code,
       10 seq
  FROM dual
  UNION ALL
SELECT 'TO_SYNC_COURSEWARE' status_code,
       '��ͬ��' status_name,
       'COURSEWARE' lib_code,
       20 seq
  FROM dual
UNION ALL
SELECT 'TO_INDEX_COURSEWARE' status_code,
       '������' status_name,
       'COURSEWARE' lib_code,
       30 seq
         FROM dual
UNION ALL
SELECT 'TO_APPROVE_COURSEWARE' status_code,
       '������' status_name,
       'COURSEWARE' lib_code,
       40 seq
  FROM dual
UNION ALL
SELECT 'PUBED_COURSEWARE' status_code,
       '�ѷ���' status_name,
       'COURSEWARE' lib_code,
       50 seq
  FROM dual
  UNION ALL
SELECT 'TO_GATHER_PERIODICAL' status_code,
       '���ɼ�' status_name,
       'PERIODICAL' lib_code,
       10 seq
  FROM dual
  UNION ALL
SELECT 'TO_SYNC_PERIODICAL' status_code,
       '��ͬ��' status_name,
       'PERIODICAL' lib_code,
       20 seq
  FROM dual
  UNION ALL
SELECT 'TO_INDEX_PERIODICAL' status_code,
       '������' status_name,
       'PERIODICAL' lib_code,
       30 seq
         FROM dual
UNION ALL
SELECT 'TO_APPROVE_PERIODICAL' status_code,
       '������' status_name,
       'PERIODICAL' lib_code,
       40 seq
  FROM dual
UNION ALL
SELECT 'PUBED_PERIODICAL' status_code,
       '�ѷ���' status_name,
       'PERIODICAL' lib_code,
       50 seq
  FROM dual
UNION ALL
SELECT 'TO_GATHER_NEWSPAPER' status_code,
       '���ɼ�' status_name,
       'NEWSPAPER' lib_code,
       10 seq
  FROM dual
  UNION ALL
SELECT 'TO_SYNC_NEWSPAPER' status_code,
       '��ͬ��' status_name,
       'NEWSPAPER' lib_code,
       20 seq
  FROM dual
UNION ALL
SELECT 'TO_INDEX_NEWSPAPER' status_code,
       '������' status_name,
       'NEWSPAPER' lib_code,
       30 seq
         FROM dual
UNION ALL
SELECT 'TO_APPROVE_NEWSPAPER' status_code,
       '������' status_name,
       'NEWSPAPER' lib_code,
       40 seq
  FROM dual
UNION ALL
SELECT 'PUBED_NEWSPAPER' status_code,
       '�ѷ���' status_name,
       'NEWSPAPER' lib_code,
       50 seq
  FROM dual
UNION ALL
SELECT 'TO_INDEX_MATERIAL_FIGURE' status_code,
       '������' status_name,
       'MATERIAL_FIGURE' lib_code,
       10 seq
         FROM dual
UNION ALL
SELECT 'TO_APPROVE_MATERIAL_FIGURE' status_code,
       '������' status_name,
       'MATERIAL_FIGURE' lib_code,
       20 seq
  FROM dual
UNION ALL
SELECT 'PUBED_MATERIAL_FIGURE' status_code,
       '�ѷ���' status_name,
       'MATERIAL_FIGURE' lib_code,
       30 seq
  FROM dual
UNION ALL
SELECT 'TO_INDEX_PHOTOGRAPHY_FIGURE' status_code,
       '������' status_name,
       'PHOTOGRAPHY_FIGURE' lib_code,
       10 seq
         FROM dual
UNION ALL
SELECT 'TO_APPROVE_PHOTOGRAPHY_FIGURE' status_code,
       '������' status_name,
       'PHOTOGRAPHY_FIGURE' lib_code,
       20 seq
  FROM dual
UNION ALL
SELECT 'PUBED_PHOTOGRAPHY_FIGURE' status_code,
       '�ѷ���' status_name,
       'PHOTOGRAPHY_FIGURE' lib_code,
       30 seq
  FROM dual
UNION ALL
SELECT 'TO_IMPORT_DOCBOOK' status_code,
       '�����' status_name,
       'DOCBOOK' lib_code,
       10 seq
  FROM dual
UNION ALL
SELECT 'IMPORTING_DOCBOOK' status_code,
       '�����' status_name,
       'DOCBOOK' lib_code,
       20 seq
  FROM dual
UNION ALL
SELECT 'IMPORTED_DOCBOOK' status_code,
       '�����' status_name,
       'DOCBOOK' lib_code,
       30 seq
  FROM dual
UNION ALL
SELECT 'FAIL_DOCBOOK' status_code,
       '��ʧ��' status_name,
       'DOCBOOK' lib_code,
       40 seq
  FROM dual
UNION ALL
SELECT 'TO_INDEX_ILLUSTRATION' status_code,
       '������' status_name,
       'ILLUSTRATION' lib_code,
       10 seq
  FROM dual
UNION ALL
SELECT 'PUBED_ILLUSTRATION' status_code,
       '�ѷ���' status_name,
       'ILLUSTRATION' lib_code,
       20 seq
  FROM dual
  UNION ALL
SELECT 'TO_INDEX_AUTHOR' status_code,
       '������' status_name,
       'AUTHOR' lib_code,
       10 seq
  FROM dual
UNION ALL
SELECT 'TO_APPROVE_AUTHOR' status_code,
       '�����' status_name,
       'AUTHOR' lib_code,
       20 seq
  FROM dual
UNION ALL
SELECT 'PUBED_AUTHOR' status_code,
       '��ͨ��' status_name,
       'AUTHOR' lib_code,
       30 seq
  FROM dual
UNION ALL
SELECT 'TO_INDEX_THIRD' status_code,
       '������' status_name,
       'THIRD' lib_code,
       10 seq
  FROM dual
UNION ALL
SELECT 'TO_APPROVE_THIRD' status_code,
       '�����' status_name,
       'THIRD' lib_code,
       20 seq
  FROM dual
UNION ALL
SELECT 'PUBED_THIRD' status_code,
       '��ͨ��' status_name,
       'THIRD' lib_code,
       30 seq
  FROM dual
UNION ALL
SELECT 'TO_APPROVE_APPLY' status_code,
       '������' status_name,
       'APPLY' lib_code,
       10 seq
  FROM dual
UNION ALL
SELECT 'APPROVED_APPLY' status_code,
       '������' status_name,
       'APPLY' lib_code,
       20 seq
  FROM dual
UNION ALL
SELECT 'TO_INDEX_WORKS_ENTRY' status_code,
       '������' status_name,
       'WORKS_ENTRY' lib_code,
       10 seq
  FROM dual
UNION ALL
SELECT 'TO_APPROVE_WORKS_ENTRY' status_code,
       '�����' status_name,
       'WORKS_ENTRY' lib_code,
       20 seq
  FROM dual
UNION ALL
SELECT 'PUBED_WORKS_ENTRY' status_code,
       '�ѷ���' status_name,
       'WORKS_ENTRY' lib_code,
       30 seq
  FROM dual
  UNION ALL
SELECT 'TO_INDEX_ENCYCLOPEDIAS_ENTRY' status_code,
       '������' status_name,
       'ENCYCLOPEDIAS_ENTRY' lib_code,
       10 seq
  FROM dual
UNION ALL
SELECT 'TO_APPROVE_ENCYCLOPEDIAS_ENTRY' status_code,
       '�����' status_name,
       'ENCYCLOPEDIAS_ENTRY' lib_code,
       20 seq
  FROM dual
UNION ALL
SELECT 'PUBED_ENCYCLOPEDIAS_ENTRY' status_code,
       '�ѷ���' status_name,
       'ENCYCLOPEDIAS_ENTRY' lib_code,
       30 seq
  FROM dual
UNION ALL
SELECT 'IMPORTING_OFFLINE_ENTRY_PROCESS' status_code,
       '�����' status_name,
       'OFFLINE_ENTRY_PROCESS' lib_code,
       10 seq
  FROM dual
UNION ALL
SELECT 'IMPORTED_OFFLINE_ENTRY_PROCESS' status_code,
       '�����' status_name,
       'OFFLINE_ENTRY_PROCESS' lib_code,
       20 seq
  FROM dual
UNION ALL
SELECT 'FAIL_OFFLINE_ENTRY_PROCESS' status_code,
       '��ʧ��' status_name,
       'OFFLINE_ENTRY_PROCESS' lib_code,
       30 seq
  FROM dual
UNION ALL
SELECT 'TO_INDEX_THEME' status_code,
       '������' status_name,
       'THEME' lib_code,
       10 seq
  FROM dual
UNION ALL
SELECT 'TO_APPROVE_THEME' status_code,
       '�����' status_name,
       'THEME' lib_code,
       20 seq
  FROM dual
UNION ALL
SELECT 'PUBED_THEME' status_code,
       '�ѷ���' status_name,
       'THEME' lib_code,
       30 seq
  FROM dual;
