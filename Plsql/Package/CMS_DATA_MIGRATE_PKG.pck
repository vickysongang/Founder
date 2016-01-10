CREATE OR REPLACE PACKAGE CMS_DATA_MIGRATE_PKG IS

  -- Author  : VICKY
  -- Created : 2015/8/23 16:30:11
  -- Purpose : 数据迁移
  PROCEDURE insert_doc_info(p_comp_code  IN VARCHAR2,
                            x_doc_id     OUT NUMBER,
                            x_return_msg OUT VARCHAR2);

  PROCEDURE insert_doc_ucm_rel(p_doc_id       IN NUMBER,
                               p_ucm_did      IN NUMBER,
                               p_ucm_ddocname IN VARCHAR2,
                               p_file_name    IN VARCHAR2,
                               p_ftp_path     IN VARCHAR2,
                               p_doc_type     IN VARCHAR2);

  PROCEDURE insert_book_info(p_comp_code        IN VARCHAR2,
                             p_source_book_id   IN NUMBER,
                             p_book_name        IN VARCHAR2,
                             p_isbn             IN VARCHAR2,
                             p_series           IN VARCHAR2,
                             p_item_code        IN VARCHAR2,
                             p_lang             IN VARCHAR2,
                             p_editor           IN VARCHAR2,
                             p_format_design    IN VARCHAR2,
                             p_cover_design     IN VARCHAR2,
                             p_author           IN VARCHAR2,
                             p_chief_editor     IN VARCHAR2,
                             p_translator       IN VARCHAR2,
                             p_pub_time         IN DATE,
                             p_publishing_house IN VARCHAR2,
                             p_impression       IN NUMBER,
                             p_edition          IN NUMBER,
                             p_pagination       IN NUMBER,
                             p_price            IN NUMBER,
                             p_keyword          IN VARCHAR2,
                             p_reader_group     IN VARCHAR2,
                             p_suggestion       IN VARCHAR2,
                             p_prod_size        IN VARCHAR2,
                             p_color_num        IN VARCHAR2,
                             p_cover_typeset    IN VARCHAR2,
                             p_assort_prod      IN VARCHAR2,
                             p_remarks          IN VARCHAR2,
                             p_book_size        IN VARCHAR2,
                             p_main_page        IN VARCHAR2,
                             p_main_typeset     IN VARCHAR2,
                             p_format_comp      IN VARCHAR2,
                             p_clccat           IN VARCHAR);

  PROCEDURE begin_migrate_data;

  PROCEDURE fetch_datas_from_other_db;

  PROCEDURE update_record_cover_status(p_doc_id IN NUMBER);

  PROCEDURE insert_attach_sync_result(p_attach_id IN NUMBER,
                                      p_book_id   IN NUMBER,
                                      p_doc_id    IN NUMBER,
                                      p_result    IN VARCHAR2);
END CMS_DATA_MIGRATE_PKG;
/
CREATE OR REPLACE PACKAGE BODY CMS_DATA_MIGRATE_PKG IS
  PROCEDURE insert_doc_info(p_comp_code  IN VARCHAR2,
                            x_doc_id     OUT NUMBER,
                            x_return_msg OUT VARCHAR2) IS
    l_doc_row cms_doc_t%ROWTYPE;
    l_doc_id  NUMBER;
  BEGIN
    --插入cms_doc_t数据
    SELECT cms_doc_s.NEXTVAL INTO l_doc_id FROM dual;
    l_doc_row.doc_id                := l_doc_id;
    l_doc_row.lib_code              := 'BOOK';
    l_doc_row.lib_type_code         := 'END_PROD_LIB';
    l_doc_row.comp_code             := p_comp_code;
    l_doc_row.status                := 'PUBED_BOOK';
    l_doc_row.object_version_number := 1;
    l_doc_row.created_by            := -1;
    l_doc_row.creation_date         := SYSDATE;
    l_doc_row.last_updated_by       := -1;
    l_doc_row.last_update_date      := SYSDATE;
    l_doc_row.attribute1            := 'SUCCESS';
    INSERT INTO cms_doc_t VALUES l_doc_row;
    x_doc_id := l_doc_id;
  EXCEPTION
    WHEN OTHERS THEN
      x_doc_id     := NULL;
      x_return_msg := SQLCODE || SQLERRM;
  END insert_doc_info;

  --插入图书分类
  PROCEDURE insert_book_category(p_doc_id      IN NUMBER,
                                 p_category_id NUMBER,
                                 x_return_msg  OUT VARCHAR2) IS
    cms_doc_category_rel_row cms_doc_category_rel_t%ROWTYPE;
    l_rel_id                 NUMBER;
  BEGIN
    SELECT cms_doc_category_rel_s.NEXTVAL INTO l_rel_id FROM dual;
    cms_doc_category_rel_row.rel_id                := l_rel_id;
    cms_doc_category_rel_row.doc_id                := p_doc_id;
    cms_doc_category_rel_row.category_id           := p_category_id;
    cms_doc_category_rel_row.category_group_type   := 'BY_CATEGORY';
    cms_doc_category_rel_row.object_version_number := 1;
    cms_doc_category_rel_row.created_by            := -1;
    cms_doc_category_rel_row.creation_date         := SYSDATE;
    cms_doc_category_rel_row.last_updated_by       := -1;
    cms_doc_category_rel_row.last_update_date      := SYSDATE;
    INSERT INTO cms_doc_category_rel_t VALUES cms_doc_category_rel_row;
    x_return_msg := NULL;
  EXCEPTION
    WHEN OTHERS THEN
      x_return_msg := SQLCODE || SQLERRM;
  END insert_book_category;

  --插入迁移记录
  PROCEDURE insert_data_migrate_record(p_source_book_id IN NUMBER,
                                       p_doc_id         IN NUMBER,
                                       p_result         VARCHAR2,
                                       p_comment        VARCHAR2) IS
    l_data_migrade_record_row cms_data_migrade_record_t%ROWTYPE;
    l_record_id               NUMBER;
    l_success_count           NUMBER;
    l_fail_count              NUMBER;
  BEGIN
    SELECT COUNT(*)
      INTO l_success_count
      FROM cms_data_migrade_record_t t
     WHERE t.source_book_id = p_source_book_id
       AND t.migrate_result = 'S';
    SELECT COUNT(*)
      INTO l_fail_count
      FROM cms_data_migrade_record_t t
     WHERE t.source_book_id = p_source_book_id
       AND t.migrate_result = 'F';
    --如果存在成功记录，则不再做处理
    IF l_success_count > 0 THEN
      NULL;
    ELSIF l_fail_count > 0 THEN
      --如果存在失败记录，则更新失败记录
      UPDATE cms_data_migrade_record_t rt
         SET rt.migrate_result  = p_result,
             rt.migrate_comment = p_comment,
             rt.migrate_time    = SYSDATE,
             rt.doc_id          = p_doc_id
       WHERE rt.source_book_id = p_source_book_id;
    ELSE
      --插入迁移记录
      SELECT cms_data_migrade_record_s.NEXTVAL INTO l_record_id FROM dual;
      l_data_migrade_record_row.record_id       := l_record_id;
      l_data_migrade_record_row.source_book_id  := p_source_book_id;
      l_data_migrade_record_row.migrate_time    := SYSDATE;
      l_data_migrade_record_row.migrate_result  := p_result;
      l_data_migrade_record_row.migrate_comment := p_comment;
      l_data_migrade_record_row.doc_id          := p_doc_id;
      INSERT INTO cms_data_migrade_record_t
      VALUES l_data_migrade_record_row;
    END IF;
  EXCEPTION
    WHEN OTHERS THEN
      NULL;
  END insert_data_migrate_record;

  --插入图书和UCM的对应关系数据
  PROCEDURE insert_doc_ucm_rel(p_doc_id       IN NUMBER,
                               p_ucm_did      IN NUMBER,
                               p_ucm_ddocname IN VARCHAR2,
                               p_file_name    IN VARCHAR2,
                               p_ftp_path     IN VARCHAR2,
                               p_doc_type     IN VARCHAR2) IS
    l_doc_ucm_rel_row cms_doc_ucm_rel_t%ROWTYPE;
    l_rel_id          NUMBER;
  BEGIN
    SELECT cms_doc_ucm_rel_s.NEXTVAL INTO l_rel_id FROM dual;
    l_doc_ucm_rel_row.rel_id                := l_rel_id;
    l_doc_ucm_rel_row.doc_id                := p_doc_id;
    l_doc_ucm_rel_row.ucm_did               := p_ucm_did;
    l_doc_ucm_rel_row.ucm_docname           := p_ucm_ddocname;
    l_doc_ucm_rel_row.file_name             := p_file_name;
    l_doc_ucm_rel_row.ftp_path              := p_ftp_path;
    l_doc_ucm_rel_row.ftp_upload_date       := SYSDATE;
    l_doc_ucm_rel_row.doc_type              := p_doc_type;
    l_doc_ucm_rel_row.object_version_number := 1;
    l_doc_ucm_rel_row.created_by            := -1;
    l_doc_ucm_rel_row.creation_date         := SYSDATE;
    l_doc_ucm_rel_row.last_updated_by       := -1;
    l_doc_ucm_rel_row.last_update_date      := SYSDATE;
    INSERT INTO cms_doc_ucm_rel_t VALUES l_doc_ucm_rel_row;
  EXCEPTION
    WHEN OTHERS THEN
      NULL;
  END insert_doc_ucm_rel;

  --插入图书数据
  PROCEDURE insert_book_info(p_comp_code        IN VARCHAR2,
                             p_source_book_id   IN NUMBER,
                             p_book_name        IN VARCHAR2,
                             p_isbn             IN VARCHAR2,
                             p_series           IN VARCHAR2,
                             p_item_code        IN VARCHAR2,
                             p_lang             IN VARCHAR2,
                             p_editor           IN VARCHAR2,
                             p_format_design    IN VARCHAR2,
                             p_cover_design     IN VARCHAR2,
                             p_author           IN VARCHAR2,
                             p_chief_editor     IN VARCHAR2,
                             p_translator       IN VARCHAR2,
                             p_pub_time         IN DATE,
                             p_publishing_house IN VARCHAR2,
                             p_impression       IN NUMBER,
                             p_edition          IN NUMBER,
                             p_pagination       IN NUMBER,
                             p_price            IN NUMBER,
                             p_keyword          IN VARCHAR2,
                             p_reader_group     IN VARCHAR2,
                             p_suggestion       IN VARCHAR2,
                             p_prod_size        IN VARCHAR2,
                             p_color_num        IN VARCHAR2,
                             p_cover_typeset    IN VARCHAR2,
                             p_assort_prod      IN VARCHAR2,
                             p_remarks          IN VARCHAR2,
                             p_book_size        IN VARCHAR2,
                             p_main_page        IN VARCHAR2,
                             p_main_typeset     IN VARCHAR2,
                             p_format_comp      IN VARCHAR2,
                             p_clccat           IN VARCHAR) IS
    l_book_row     cms_book_t%ROWTYPE;
    l_doc_id       NUMBER;
    l_book_id      NUMBER;
    l_category_id  NUMBER;
    l_lang_code    VARCHAR2(50); --LANG
    l_prod_size    VARCHAR2(50); --PROD_SIZE
    l_color_num    VARCHAR2(50); --COLOR_NUM
    l_typeset      VARCHAR2(50); --TYPESET
    l_assort_prod  VARCHAR2(50); --ASSORT_PROD
    l_reader_group VARCHAR2(50); --READER_GROUP  
    l_book_size    VARCHAR2(50); --BOOK_SIZE
    l_main_page    VARCHAR2(50); --MAIN_PAGE
    l_book_count   NUMBER := 0;
    x_return_msg   VARCHAR2(1000);
  BEGIN
    --如果图书已存在 则中止
    SELECT COUNT(*)
      INTO l_book_count
      FROM cms_book_t b
     WHERE b.book_name = p_book_name
       AND b.isbn = p_isbn
       AND b.impression = p_impression
       AND b.edition = p_edition;
    IF l_book_count > 0 THEN
      insert_data_migrate_record(p_source_book_id,
                                 NULL,
                                 'F',
                                 '该图书已存在--->' || p_book_name || ',' ||
                                 p_isbn || ',' || p_impression || ',' ||
                                 p_edition);
      RETURN;
    END IF;
  
    insert_doc_info(p_comp_code, l_doc_id, x_return_msg);
    IF l_doc_id IS NULL THEN
      insert_data_migrate_record(p_source_book_id,
                                 l_doc_id,
                                 'F',
                                 x_return_msg);
      RETURN;
    END IF;
  
    --获取分类ID
    BEGIN
      SELECT ct.category_id
        INTO l_category_id
        FROM cms_category_t ct, cms_lib_category_g_rel_t lcgr
       WHERE cms_common_pkg.get_category_top_node_id(ct.category_id) =
             lcgr.rel_id
         AND ct.category_name = p_clccat
         AND lcgr.lib_code = 'BOOK'
         AND lcgr.comp_code = p_comp_code;
    EXCEPTION
      WHEN OTHERS THEN
        l_category_id := -1;
    END;
    insert_book_category(l_doc_id, l_category_id, x_return_msg);
    IF x_return_msg IS NOT NULL THEN
      insert_data_migrate_record(p_source_book_id,
                                 l_doc_id,
                                 'F',
                                 x_return_msg);
      RETURN;
    END IF;
    --获取语种CODE
    BEGIN
      SELECT t.lookup_value_code
        INTO l_lang_code
        FROM cms_lookup_value_t t
       WHERE t.meaning = p_lang
         AND t.lookup_type_code = 'LANG';
    EXCEPTION
      WHEN OTHERS THEN
        l_lang_code := NULL;
    END;
  
    --获取成品尺寸
    BEGIN
      SELECT t.lookup_value_code
        INTO l_prod_size
        FROM cms_lookup_value_t t
       WHERE t.meaning = p_lang
         AND t.lookup_type_code = 'PROD_SIZE';
    EXCEPTION
      WHEN OTHERS THEN
        l_prod_size := NULL;
    END;
  
    --获取正文色数
    BEGIN
      SELECT t.lookup_value_code
        INTO l_color_num
        FROM cms_lookup_value_t t
       WHERE t.meaning = p_lang
         AND t.lookup_type_code = 'COLOR_NUM';
    EXCEPTION
      WHEN OTHERS THEN
        l_color_num := NULL;
    END;
  
    --获取排版软件
    BEGIN
      SELECT t.lookup_value_code
        INTO l_typeset
        FROM cms_lookup_value_t t
       WHERE t.meaning = p_lang
         AND t.lookup_type_code = 'TYPESET';
    EXCEPTION
      WHEN OTHERS THEN
        l_typeset := NULL;
    END;
  
    --获取配套产品
    BEGIN
      SELECT t.lookup_value_code
        INTO l_assort_prod
        FROM cms_lookup_value_t t
       WHERE t.meaning = p_lang
         AND t.lookup_type_code = 'ASSORT_PROD';
    EXCEPTION
      WHEN OTHERS THEN
        l_assort_prod := NULL;
    END;
  
    --获取读者对象
    BEGIN
      SELECT t.lookup_value_code
        INTO l_reader_group
        FROM cms_lookup_value_t t
       WHERE t.meaning = p_lang
         AND t.lookup_type_code = 'READER_GROUP';
    EXCEPTION
      WHEN OTHERS THEN
        l_reader_group := NULL;
    END;
  
    --获取开本
    BEGIN
      SELECT t.lookup_value_code
        INTO l_book_size
        FROM cms_lookup_value_t t
       WHERE t.meaning = p_lang
         AND t.lookup_type_code = 'BOOK_SIZE';
    EXCEPTION
      WHEN OTHERS THEN
        l_book_size := NULL;
    END;
  
    --获取正文用纸
    BEGIN
      SELECT t.lookup_value_code
        INTO l_main_page
        FROM cms_lookup_value_t t
       WHERE t.meaning = p_lang
         AND t.lookup_type_code = 'MAIN_PAGE';
    EXCEPTION
      WHEN OTHERS THEN
        l_main_page := NULL;
    END;
  
    SELECT cms_book_s.NEXTVAL INTO l_book_id FROM dual;
    l_book_row.book_id               := l_book_id;
    l_book_row.doc_id                := l_doc_id;
    l_book_row.book_name             := p_book_name;
    l_book_row.isbn                  := p_isbn;
    l_book_row.series                := p_series;
    l_book_row.item_code             := p_item_code;
    l_book_row.lang                  := l_lang_code;
    l_book_row.editor                := p_editor;
    l_book_row.format_design         := p_format_design;
    l_book_row.cover_design          := p_cover_design;
    l_book_row.author                := p_author;
    l_book_row.chief_editor          := p_chief_editor;
    l_book_row.translator            := p_translator;
    l_book_row.pub_time              := p_pub_time;
    l_book_row.publishing_house      := p_publishing_house;
    l_book_row.impression            := p_impression;
    l_book_row.edition               := p_edition;
    l_book_row.pagination            := p_pagination;
    l_book_row.price                 := p_price;
    l_book_row.keyword               := p_keyword;
    l_book_row.reader_group          := l_reader_group;
    l_book_row.suggestion            := p_suggestion;
    l_book_row.prod_size             := l_prod_size;
    l_book_row.color_num             := l_color_num;
    l_book_row.cover_typeset         := l_typeset;
    l_book_row.assort_prod           := l_assort_prod;
    l_book_row.remarks               := p_remarks;
    l_book_row.book_size             := l_book_size;
    l_book_row.main_page             := l_main_page;
    l_book_row.main_typeset          := l_typeset;
    l_book_row.format_comp           := p_format_comp;
    l_book_row.object_version_number := 1;
    l_book_row.created_by            := -1;
    l_book_row.creation_date         := SYSDATE;
    l_book_row.last_updated_by       := -1;
    l_book_row.last_update_date      := SYSDATE;
    INSERT INTO cms_book_t VALUES l_book_row;
    insert_data_migrate_record(p_source_book_id,
                               l_doc_id,
                               'S',
                               p_book_name || ',' || p_isbn || ',' ||
                               p_impression || ',' || p_edition);
    COMMIT;
  EXCEPTION
    WHEN OTHERS THEN
      ROLLBACK;
      insert_data_migrate_record(p_source_book_id,
                                 NULL,
                                 'F',
                                 SQLCODE || SQLERRM);
      COMMIT;
  END insert_book_info;

  PROCEDURE begin_migrate_data IS
    l_comp_code VARCHAR2(100) := 'HENANKEXUEJISHU';
  BEGIN
    FOR b IN (SELECT *
                FROM book_info_t t
               WHERE t.bookid NOT IN
                     (SELECT source_book_id
                        FROM cms_data_migrade_record_t rt
                       WHERE rt.migrate_result = 'S')) LOOP
      insert_book_info(p_comp_code        => l_comp_code,
                       p_source_book_id   => b.bookid,
                       p_book_name        => b.bookname,
                       p_isbn             => b.isbn,
                       p_series           => b.seriesname,
                       p_item_code        => b.materialid,
                       p_lang             => b.LANGUAGE,
                       p_editor           => b.editor,
                       p_format_design    => b.coverdesigner,
                       p_cover_design     => b.coverdesigner,
                       p_author           => b.author,
                       p_chief_editor     => b.majoreditor,
                       p_translator       => b.translator,
                       p_pub_time         => b.pubdate,
                       p_publishing_house => b.pressname,
                       p_impression       => b.printversion,
                       p_edition          => b.bookversion,
                       p_pagination       => b.textnum,
                       p_price            => b.price,
                       p_keyword          => b.keywords,
                       p_reader_group     => b.readerobject,
                       p_suggestion       => b.shelfadvice,
                       p_prod_size        => b.productsize,
                       p_color_num        => b.textcolor,
                       p_cover_typeset    => b.coversoft,
                       p_assort_prod      => b.supportproduct,
                       p_remarks          => b.remark,
                       p_book_size        => b.format,
                       p_main_page        => b.texttype,
                       p_main_typeset     => b.textsoft,
                       p_format_comp      => b.flatplate,
                       p_clccat           => b.clccat);
    END LOOP;
  END begin_migrate_data;

  --通过DBLINK的方式从另一个数据库将数据迁移到当前数据库
  PROCEDURE fetch_datas_from_other_db AS
  BEGIN
    NULL;
    INSERT INTO book_info_t
      SELECT *
        FROM book_info_v@cms_kj_dblk v
       WHERE v.BOOKID NOT IN (SELECT BOOKID FROM book_info_t);
    INSERT INTO attach_t
      SELECT *
        FROM attach@cms_kj_dblk a
       WHERE a.id NOT IN (SELECT id FROM attach_t);
  END fetch_datas_from_other_db;

  PROCEDURE update_record_cover_status(p_doc_id IN NUMBER) AS
  BEGIN
    UPDATE cms_data_migrade_record_t t
       SET t.cover_sync_result = 'S'
     WHERE t.doc_id = p_doc_id;
    COMMIT;
  EXCEPTION
    WHEN OTHERS THEN
      ROLLBACK;
  END update_record_cover_status;

  PROCEDURE insert_attach_sync_result(p_attach_id IN NUMBER,
                                      p_book_id   IN NUMBER,
                                      p_doc_id    IN NUMBER,
                                      p_result    IN VARCHAR2) AS
    l_attach_sync_result_row cms_attach_sync_result_t%ROWTYPE;
    l_result_id              NUMBER;
    l_count                  NUMBER;
  BEGIN
    SELECT COUNT(*)
      INTO l_count
      FROM cms_attach_sync_result_t t
     WHERE t.attach_id = p_attach_id;
    IF l_count = 0 THEN
      SELECT cms_attach_sync_result_s.NEXTVAL INTO l_result_id FROM dual;
      l_attach_sync_result_row.result_id   := l_result_id;
      l_attach_sync_result_row.attach_id   := p_attach_id;
      l_attach_sync_result_row.book_id     := p_book_id;
      l_attach_sync_result_row.doc_id      := p_doc_id;
      l_attach_sync_result_row.sync_result := p_result;
      l_attach_sync_result_row.sync_time   := SYSDATE;
      INSERT INTO cms_attach_sync_result_t VALUES l_attach_sync_result_row;
    ELSE
      UPDATE cms_attach_sync_result_t t
         SET t.sync_result = p_result, t.sync_time = SYSDATE
       WHERE t.attach_id = p_attach_id;
    END IF;
    COMMIT;
  EXCEPTION
    WHEN OTHERS THEN
      ROLLBACK;
  END insert_attach_sync_result;

END CMS_DATA_MIGRATE_PKG;
/
