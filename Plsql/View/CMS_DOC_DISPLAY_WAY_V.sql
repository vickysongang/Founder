CREATE OR REPLACE VIEW CMS_DOC_DISPLAY_WAY_V AS
SELECT 'COVER_LIST' status_code, '封面视图' status_name, 'BOOK' lib_code
  FROM dual
UNION ALL
SELECT 'BOOK_LIST' status_code, '列表视图' status_name, 'BOOK' lib_code
  FROM dual
UNION ALL
SELECT 'ELEC_PROD_COVER_LIST' status_code,
       '封面视图' status_name,
       'ELEC_PROD' lib_code
  FROM dual
UNION ALL
SELECT 'ELEC_PROD_LIB_LIST' status_code,
       '列表视图' status_name,
       'ELEC_PROD' lib_code
  FROM dual
UNION ALL
SELECT 'VIDEO_PHOTO_ALBUM_LIST' status_code,
       '封面视图' status_name,
       'VIDEO' lib_code
  FROM dual
  UNION ALL
SELECT 'VIDEO_LIB_LIST' status_code,
       '列表视图' status_name,
       'VIDEO' lib_code
  FROM dual
UNION ALL

SELECT 'COVER_LIST' status_code, '封面视图' status_name, 'PERIODICAL' lib_code
  FROM dual
UNION ALL
SELECT 'PERIODICAL_LIST' status_code, '列表视图' status_name, 'PERIODICAL' lib_code
  FROM dual
  UNION ALL
SELECT 'COVER_LIST' status_code, '封面视图' status_name, 'NEWSPAPER' lib_code
  FROM dual
UNION ALL
SELECT 'NEWSPAPER_LIST' status_code, '列表视图' status_name, 'NEWSPAPER' lib_code
  FROM dual
  UNION ALL
SELECT 'COVER_LIST' status_code, '图片视图' status_name, 'MATERIAL_FIGURE' lib_code
  FROM dual
UNION ALL
SELECT 'MATERIAL_LIST' status_code, '列表视图' status_name, 'MATERIAL_FIGURE' lib_code
  FROM dual
  UNION ALL
SELECT 'COVER_LIST' status_code, '图片视图' status_name, 'PHOTOGRAPHY_FIGURE' lib_code
  FROM dual
UNION ALL
SELECT 'PHOTOGRAPHY_LIST' status_code, '列表视图' status_name, 'PHOTOGRAPHY_FIGURE' lib_code
  FROM dual;
