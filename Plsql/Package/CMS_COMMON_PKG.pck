CREATE OR REPLACE PACKAGE cms_common_pkg IS

  /*FUNCTION get_role_str(p_user_name IN VARCHAR2,
  p_role_type IN VARCHAR2,
  p_type      IN VARCHAR2) RETURN VARCHAR2;*/

  FUNCTION get_admin_comp_str(p_user_id IN NUMBER) RETURN VARCHAR2;

  FUNCTION parse_string(str IN VARCHAR2) RETURN cms_table_type;

  FUNCTION parse_string_with_separator(str       IN VARCHAR2,
                                       separator IN VARCHAR2)
    RETURN cms_table_type;

  /*FUNCTION get_admin_role_code(p_user_name IN VARCHAR2,
  p_comp_code IN VARCHAR2) RETURN VARCHAR2;*/

  PROCEDURE ucm_checkin_callback(p_doc_id          IN NUMBER,
                                 p_ucm_did         IN NUMBER,
                                 p_ucm_docname     IN VARCHAR2,
                                 p_file_name       IN VARCHAR2,
                                 p_ftp_path        IN VARCHAR2,
                                 p_thumbnail_url   IN VARCHAR2,
                                 p_swf_url         IN VARCHAR2,
                                 p_ftp_upload_date IN VARCHAR2,
                                 p_doc_type        IN VARCHAR2,
                                 p_ref_doc_id      IN VARCHAR2,
                                 p_attribute1      IN VARCHAR2,
                                 p_attribute2      IN VARCHAR2,
                                 p_attribute3      IN VARCHAR2,
                                 p_attribute4      IN VARCHAR2,
                                 p_attribute5      IN VARCHAR2,
                                 p_attribute6      IN VARCHAR2,
                                 p_attribute7      IN VARCHAR2,
                                 p_attribute8      IN VARCHAR2,
                                 p_attribute9      IN VARCHAR2,
                                 p_attribute10     IN VARCHAR2,
                                 x_return_code     OUT VARCHAR2,
                                 x_return_msg      OUT VARCHAR2);

  PROCEDURE ucm_delete_callback(p_ucm_docname IN VARCHAR2,
                                p_attribute1  IN VARCHAR2,
                                p_attribute2  IN VARCHAR2,
                                p_attribute3  IN VARCHAR2,
                                p_attribute4  IN VARCHAR2,
                                p_attribute5  IN VARCHAR2,
                                p_attribute6  IN VARCHAR2,
                                p_attribute7  IN VARCHAR2,
                                p_attribute8  IN VARCHAR2,
                                p_attribute9  IN VARCHAR2,
                                p_attribute10 IN VARCHAR2,
                                x_return_code OUT VARCHAR2,
                                x_return_msg  OUT VARCHAR2);

  PROCEDURE start_docbook_monitor(p_job_id      IN NUMBER,
                                  p_status      IN VARCHAR2,
                                  x_return_code OUT VARCHAR2,
                                  x_return_msg  OUT VARCHAR2);

  PROCEDURE end_docbook_monitor(p_monitor_id  IN NUMBER,
                                p_status      IN VARCHAR2,
                                p_result      IN VARCHAR2,
                                p_remarks     IN VARCHAR2,
                                x_return_code OUT VARCHAR2,
                                x_return_msg  OUT VARCHAR2);

  PROCEDURE imp_book_metadata(p_comp_code     IN VARCHAR2,
                              p_book_name     IN VARCHAR2,
                              p_isbn          IN VARCHAR2,
                              p_impression    IN VARCHAR2,
                              p_edition       IN VARCHAR2,
                              p_chief_editor  IN VARCHAR2,
                              p_editor        IN VARCHAR2,
                              p_price         IN VARCHAR2,
                              p_keyword       IN VARCHAR2,
                              p_pub_time      IN VARCHAR2,
                              p_publish_house IN VARCHAR2,
                              p_lang          IN VARCHAR2,
                              p_author        IN VARCHAR2,
                              p_series_name   IN VARCHAR2,
                              p_book_size     IN VARCHAR2,
                              p_format_design IN VARCHAR2,
                              p_cover_design  IN VARCHAR2,
                              p_pagenum       IN VARCHAR2,
                              p_remarks       IN VARCHAR2,
                              p_category_name IN VARCHAR2,
                              p_reader_group  IN VARCHAR2,
                              p_suggestion    IN VARCHAR2,
                              p_traslator     IN VARCHAR2,
                              p_prod_size     IN VARCHAR2,
                              p_color_num     IN VARCHAR2,
                              p_cover_typeset IN VARCHAR2,
                              p_assort_prod   IN VARCHAR2,
                              p_main_page     IN VARCHAR2,
                              p_main_typeset  IN VARCHAR2,
                              p_format_comp   IN VARCHAR2,
                              p_item_code     IN VARCHAR2,
                              x_return_code   OUT VARCHAR2,
                              x_return_msg    OUT VARCHAR2);

  PROCEDURE update_assort_prod_status(p_doc_id      IN VARCHAR2,
                                      x_return_code OUT VARCHAR2,
                                      x_return_msg  OUT VARCHAR2);

  PROCEDURE imp_chapter(p_doc_id       IN NUMBER,
                        p_chapter_code IN VARCHAR2,
                        p_chapter_name IN VARCHAR2,
                        p_content      IN CLOB,
                        p_seq          IN NUMBER,
                        x_return_code  OUT VARCHAR2,
                        x_return_msg   OUT VARCHAR2);

  PROCEDURE init_docbook_job(p_job_name    IN VARCHAR2,
                             p_comp_name   IN VARCHAR2,
                             p_isbn        IN VARCHAR2,
                             x_return_code OUT VARCHAR2,
                             x_return_msg  OUT VARCHAR2);

  PROCEDURE imp_jb_info(p_doc_id              IN NUMBER,
                        p_brief_introduction  IN CLOB,
                        p_catalog             IN CLOB,
                        p_foreword            IN CLOB,
                        p_postscript          IN CLOB,
                        p_highlights          IN CLOB,
                        p_preface             IN CLOB,
                        p_glossary            IN CLOB,
                        p_references          IN CLOB,
                        p_author_introduction IN CLOB,
                        x_return_code         OUT VARCHAR2,
                        x_return_msg          OUT VARCHAR2);

  PROCEDURE update_media_preview_info(p_doc_id      IN NUMBER,
                                      p_media_type  IN VARCHAR2,
                                      p_preview_url IN VARCHAR2,
                                      p_cover_url   IN VARCHAR2,
                                      p_duration    IN VARCHAR2,
                                      x_return_code OUT VARCHAR2,
                                      x_return_msg  OUT VARCHAR2);

  FUNCTION get_category_top_node_id(p_category_id IN NUMBER) RETURN NUMBER;

  PROCEDURE get_remove_ftp_dirs(p_doc_ids     IN VARCHAR2,
                                x_return_code OUT VARCHAR2,
                                x_return_msg  OUT VARCHAR2);

  FUNCTION update_doc_status_when_sync(p_doc_id  IN VARCHAR2,
                                       p_user_id IN VARCHAR2) RETURN VARCHAR2;

  PROCEDURE start_sync(p_doc_id IN VARCHAR2);

  PROCEDURE end_sync(p_doc_id IN VARCHAR2);

  FUNCTION cancel_sync(p_doc_id IN VARCHAR2) RETURN VARCHAR2;

  PROCEDURE file_manage(p_doc_id IN NUMBER, p_sync_flag VARCHAR2);

  FUNCTION get_sync_flag(p_doc_id IN NUMBER) RETURN VARCHAR2;

  PROCEDURE get_sync_result(p_doc_ids     IN VARCHAR2,
                            p_user_id     IN VARCHAR2,
                            x_return_code OUT VARCHAR2,
                            x_return_msg  OUT VARCHAR2);

  PROCEDURE clear_sync_log(p_user_name   IN VARCHAR2,
                           p_comp_code   IN VARCHAR2,
                           p_lib_code    IN VARCHAR2,
                           x_return_code OUT VARCHAR2,
                           x_return_msg  OUT VARCHAR2);

  FUNCTION delete_gather_monitor_record(p_doc_id IN NUMBER) RETURN VARCHAR2;

  FUNCTION get_sync_batch_num(p_doc_id IN NUMBER) RETURN VARCHAR2;

  PROCEDURE sync_log_processor(p_user_id          IN VARCHAR2,
                               p_user_name        IN VARCHAR2,
                               p_comp_code        IN VARCHAR2,
                               p_lib_code         IN VARCHAR2,
                               p_doc_id           IN NUMBER,
                               p_file_name        IN VARCHAR2,
                               p_file_path        IN VARCHAR2,
                               p_operation        IN VARCHAR2,
                               p_result           IN VARCHAR,
                               p_thumbnail_result IN VARCHAR2,
                               p_remarks          IN VARCHAR2,
                               batch_num          IN VARCHAR2,
                               x_return_code      OUT VARCHAR2,
                               x_return_msg       OUT VARCHAR2);

  PROCEDURE res_exp_download_apply_submit(p_apply_id    IN NUMBER,
                                          p_proposer_id IN NUMBER,
                                          p_apply_type  IN VARCHAR2,
                                          x_return_code OUT VARCHAR2,
                                          x_return_msg  OUT VARCHAR2);

  PROCEDURE res_exp_download_approval(p_apply_id        IN NUMBER,
                                      p_apply_type      IN VARCHAR2,
                                      p_approver_id     IN NUMBER,
                                      p_result          IN VARCHAR2,
                                      p_rollback_reason IN VARCHAR2,
                                      x_return_code     OUT VARCHAR2,
                                      x_return_msg      OUT VARCHAR2);

  PROCEDURE insert_approve_history(p_apply_id    IN NUMBER,
                                   p_apply_type  IN VARCHAR2,
                                   p_approver_id IN NUMBER,
                                   p_action      IN VARCHAR2,
                                   p_result      IN VARCHAR2);

  FUNCTION get_approve_user_count(p_role_type IN VARCHAR2,
                                  p_status    IN VARCHAR2,
                                  p_comp_code IN VARCHAR2) RETURN NUMBER;

  PROCEDURE update_apply_status(p_apply_id     IN NUMBER,
                                p_apply_type   IN VARCHAR2,
                                p_apply_status IN VARCHAR2);

  FUNCTION get_ftp_path_last_section(p_ftp_path IN VARCHAR2) RETURN VARCHAR2;

  PROCEDURE gather_monitor_processor(p_doc_id           IN NUMBER,
                                     p_file_name        IN VARCHAR2,
                                     p_ftp_path         IN VARCHAR2,
                                     p_thumbnail_result IN VARCHAR2,
                                     p_doc_type         IN VARCHAR2 DEFAULT NULL,
                                     p_comp_code        IN VARCHAR2 DEFAULT NULL,
                                     p_result           IN VARCHAR2,
                                     p_remarks          IN VARCHAR2);

  PROCEDURE works_entry_processor(p_doc_id           IN VARCHAR2,
                                  p_lib_code         IN VARCHAR2,
                                  p_task_id          IN VARCHAR2,
                                  p_user_id          IN VARCHAR2,
                                  p_comp_code        IN VARCHAR2,
                                  p_file_name        IN VARCHAR2,
                                  p_ucm_did          IN VARCHAR2,
                                  p_ucm_doc_name     IN VARCHAR2,
                                  p_title            IN VARCHAR2,
                                  p_subtitle         IN VARCHAR2,
                                  p_english_title    IN VARCHAR2,
                                  p_english_subtitle IN VARCHAR2,
                                  p_source           IN VARCHAR2,
                                  p_author           IN VARCHAR2,
                                  p_traslator        IN VARCHAR2,
                                  p_lang             IN VARCHAR2,
                                  p_word_count       IN VARCHAR2,
                                  p_writing_time     IN VARCHAR2,
                                  p_keyword          IN VARCHAR2,
                                  p_category_name    IN VARCHAR2,
                                  p_abstract_desc    IN VARCHAR2,
                                  p_result_file_path IN VARCHAR2,
                                  p_entry_content    IN CLOB,
                                  x_return_code      OUT VARCHAR2,
                                  x_return_msg       OUT VARCHAR2);

  PROCEDURE encyclopedias_entry_processor(p_doc_id           IN VARCHAR2,
                                          p_lib_code         IN VARCHAR2,
                                          p_task_id          IN VARCHAR2,
                                          p_user_id          IN VARCHAR2,
                                          p_comp_code        IN VARCHAR2,
                                          p_file_name        IN VARCHAR2,
                                          p_ucm_did          IN VARCHAR2,
                                          p_ucm_doc_name     IN VARCHAR2,
                                          p_entry_header     IN VARCHAR2,
                                          p_source           IN VARCHAR2,
                                          p_knowledge_point  IN VARCHAR2,
                                          p_keyword          IN VARCHAR2,
                                          p_category_name    IN VARCHAR2,
                                          p_result_file_path IN VARCHAR2,
                                          p_entry_content    IN CLOB,
                                          x_return_code      OUT VARCHAR2,
                                          x_return_msg       OUT VARCHAR2);

  PROCEDURE update_task_when_finish(p_task_id     IN VARCHAR2,
                                    x_return_code OUT VARCHAR2,
                                    x_return_msg  OUT VARCHAR2);

  PROCEDURE task_processor(p_task_id      IN VARCHAR2,
                           p_user_id      IN VARCHAR2,
                           p_file_name    IN VARCHAR2,
                           p_ucm_did      IN VARCHAR2,
                           p_ucm_doc_name IN VARCHAR2,
                           x_return_code  OUT VARCHAR2,
                           x_return_msg   OUT VARCHAR2);

  PROCEDURE task_monitor_log_processor(p_task_id      IN VARCHAR2,
                                       p_process_info IN VARCHAR2,
                                       p_operator     IN VARCHAR2,
                                       p_log_type     IN VARCHAR2,
                                       p_user_id      IN VARCHAR2,
                                       p_seq          IN NUMBER,
                                       x_return_code  OUT VARCHAR2,
                                       x_return_msg   OUT VARCHAR2);

  FUNCTION delete_task(p_doc_id IN NUMBER) RETURN VARCHAR;

  FUNCTION get_lib_code(p_lib_id IN VARCHAR2) RETURN VARCHAR2;

  FUNCTION delete_entry(p_doc_id IN NUMBER) RETURN VARCHAR2;

  PROCEDURE get_gather_monitor_count(p_doc_id           IN NUMBER,
                                     x_success_count    OUT VARCHAR2,
                                     x_fail_count       OUT VARCHAR2,
                                     x_processing_count OUT VARCHAR2);

  PROCEDURE get_batch_gather_monitor_count(p_comp_code        IN VARCHAR2,
                                           p_lib_code         IN VARCHAR2,
                                           p_user_name        IN VARCHAR2,
                                           x_success_count    OUT VARCHAR2,
                                           x_fail_count       OUT VARCHAR2,
                                           x_processing_count OUT VARCHAR2);

  FUNCTION get_doc_type(p_ftp_path IN VARCHAR2, p_lib_code IN VARCHAR2)
    RETURN VARCHAR2;

  FUNCTION get_doc_type_name(p_ftp_path IN VARCHAR2, p_doc_id IN NUMBER)
    RETURN VARCHAR2;

  FUNCTION get_entry_title(p_doc_id IN NUMBER) RETURN VARCHAR2;

  FUNCTION get_file_manage_directory(p_doc_id IN NUMBER) RETURN VARCHAR2;

  --修改图书的分类的时候同时修改插图的分类
  PROCEDURE update_other_category_for_book(p_doc_id      IN NUMBER,
                                           p_category_id IN NUMBER);

  --校验图书是否存在
  FUNCTION validate_book_exsit(p_book_name  IN VARCHAR2,
                               p_isbn       VARCHAR2,
                               p_edition    IN VARCHAR2,
                               p_impression IN VARCHAR2) RETURN VARCHAR2;

  FUNCTION get_thumbnail_url(p_doc_id IN NUMBER) RETURN VARCHAR2;

  FUNCTION get_preview_url(p_doc_id IN NUMBER) RETURN VARCHAR2;
END cms_common_pkg;
/
CREATE OR REPLACE PACKAGE BODY cms_common_pkg IS

  --获取用户的平台角色信息串,逗号隔开,如liheng为大象出版社管理员,文心出版社管理.
  /*FUNCTION get_role_str(p_user_name IN VARCHAR2,
                        p_role_type IN VARCHAR2,
                        p_type      IN VARCHAR2) RETURN VARCHAR2 IS
    l_role_str VARCHAR2(4000);
  BEGIN
    FOR a_res IN (SELECT r.role_id, r.role_code, r.role_name
                    FROM cms_role_user_rel_t rur, cms_user_t u, cms_role_t r
                   WHERE r.role_type = p_role_type
                     AND rur.role_code = r.role_code
                     AND r.enable_flag = 'Y'
                     AND rur.user_name = u.user_name
                     AND rur.comp_code = r.comp_code
                     AND u.user_name = p_user_name) LOOP
      IF upper(p_type) = 'NAME' THEN
        l_role_str := l_role_str || a_res.role_name || ',';
      ELSIF upper(p_type) = 'CODE' THEN
        l_role_str := l_role_str || a_res.role_code || ',';
      END IF;
    END LOOP;
    IF l_role_str IS NOT NULL THEN
      l_role_str := substr(l_role_str, 1, length(l_role_str) - 1);
    END IF;
    RETURN l_role_str;
  END get_role_str;*/

  --获取出版社管理员所管辖的出版社代码串,逗号隔开.
  FUNCTION get_admin_comp_str(p_user_id IN NUMBER) RETURN VARCHAR2 IS
    l_comp_str  VARCHAR2(240);
    l_user_name VARCHAR2(40);
  BEGIN
    SELECT u.user_name
      INTO l_user_name
      FROM cms_user_t u
     WHERE u.user_id = p_user_id
       AND u.enable_flag = 'Y';
    IF l_user_name = 'SYSADMIN' THEN
      RETURN 'ALL';
    END IF;
    FOR a_res IN (SELECT r.comp_code
                    FROM cms_role_user_rel_t rur, cms_role_t r
                   WHERE r.role_type = 'ADMIN_PLATFORM'
                     AND rur.role_id = r.role_id
                     AND r.enable_flag = 'Y'
                     AND rur.user_id = p_user_id) LOOP
      l_comp_str := l_comp_str || a_res.comp_code || ',';
    END LOOP;
    IF l_comp_str IS NOT NULL THEN
      l_comp_str := substr(l_comp_str, 1, length(l_comp_str) - 1);
    END IF;
    RETURN l_comp_str;
  EXCEPTION
    WHEN OTHERS THEN
      RETURN NULL;
  END get_admin_comp_str;

  FUNCTION parse_string(str IN VARCHAR2) RETURN cms_table_type IS
    len      NUMBER; --分割的数组元素个数
    i        NUMBER; --position位置
    res      VARCHAR2(100);
    up_len   NUMBER; --上一个位置
    down_len NUMBER; --下一个位置
    cstr     cms_table_type := cms_table_type(); --声明集合
  BEGIN
    len := (length(str) - length(REPLACE(str, ','))) / length(',');
    /*len := length(TRIM(translate(str, REPLACE(str, ','), ' ')));*/
    IF len = 0 THEN
      cstr.EXTEND(1);
      cstr(1) := str;
    ELSE
      FOR j IN 1 .. len + 1 LOOP
        --j是集合元素下标
        IF j = 1 THEN
          i   := instr(str, ',', 1, j);
          res := substr(str, 1, i - 1);
          cstr.EXTEND(1);
          cstr(j) := res;
          up_len := i;
          down_len := i;
        ELSIF j < len + 1 THEN
          i        := instr(str, ',', 1, j);
          down_len := i;
          res      := substr(str, up_len + 1, down_len - up_len - 1);
          cstr.EXTEND(1);
          cstr(j) := res;
          up_len := i;
        ELSE
          res := substr(str, down_len + 1);
          cstr.EXTEND(1);
          cstr(j) := res;
        END IF;
      END LOOP;
    END IF;
    RETURN cstr;
  EXCEPTION
    WHEN OTHERS THEN
      RETURN NULL;
  END parse_string;

  FUNCTION parse_string_with_separator(str       IN VARCHAR2,
                                       separator IN VARCHAR2)
    RETURN cms_table_type IS
    len      NUMBER; --分割的数组元素个数
    i        NUMBER; --position位置
    res      VARCHAR2(100);
    up_len   NUMBER; --上一个位置
    down_len NUMBER; --下一个位置
    cstr     cms_table_type := cms_table_type(); --声明集合
  BEGIN
    len := (length(str) - length(REPLACE(str, separator))) /
           length(separator);
    /*len := length(TRIM(translate(str, REPLACE(str, ','), ' ')));*/
    IF len = 0 THEN
      cstr.EXTEND(1);
      cstr(1) := str;
    ELSE
      FOR j IN 1 .. len + 1 LOOP
        --j是集合元素下标
        IF j = 1 THEN
          i   := instr(str, separator, 1, j);
          res := substr(str, 1, i - 1);
          cstr.EXTEND(1);
          cstr(j) := res;
          up_len := i;
          down_len := i;
        ELSIF j < len + 1 THEN
          i        := instr(str, separator, 1, j);
          down_len := i;
          res      := substr(str, up_len + 1, down_len - up_len - 1);
          cstr.EXTEND(1);
          cstr(j) := res;
          up_len := i;
        ELSE
          res := substr(str, down_len + 1);
          cstr.EXTEND(1);
          cstr(j) := res;
        END IF;
      END LOOP;
    END IF;
    RETURN cstr;
  EXCEPTION
    WHEN OTHERS THEN
      RETURN NULL;
  END parse_string_with_separator;

  /*FUNCTION get_admin_role_code(p_user_name IN VARCHAR2,
                               p_comp_code IN VARCHAR2) RETURN VARCHAR2 IS
    l_role_code VARCHAR2(40);
  BEGIN
    SELECT r.role_code
      INTO l_role_code
      FROM cms_role_user_rel_t rur, cms_user_t u, cms_role_t r
     WHERE r.role_type = 'ADMIN_PLATFORM'
       AND rur.role_code = r.role_code
       AND r.enable_flag = 'Y'
       AND rur.user_name = u.user_name
       AND rur.comp_code = r.comp_code
          --and r.comp_code = u.comp_code
       AND u.user_name = p_user_name
       AND r.comp_code = p_comp_code;
    RETURN l_role_code;
  EXCEPTION
    WHEN OTHERS THEN
      RETURN NULL;
  END get_admin_role_code;*/

  PROCEDURE ucm_filter_callback_common(p_doc_id IN NUMBER) IS
    l_source_status     VARCHAR2(40);
    l_source_status_seq NUMBER;
    l_lib_code          VARCHAR2(40);
  BEGIN
    SELECT d.source_status, ds.seq, d.lib_code
      INTO l_source_status, l_source_status_seq, l_lib_code
      FROM cms_doc_t d, cms_doc_status_v ds
     WHERE d.lib_code = ds.lib_code
       AND d.status = ds.status_code
       AND d.doc_id = p_doc_id;
    IF instr(l_source_status, 'TO_GATHER') > 0 THEN
      UPDATE cms_doc_t d
         SET d.status = (SELECT ds.status_code
                           FROM cms_doc_status_v ds
                          WHERE ds.lib_code = l_lib_code
                            AND ds.seq = l_source_status_seq + 10)
       WHERE d.doc_id = p_doc_id;
    ELSE
      UPDATE cms_doc_t d
         SET d.status = d.source_status, d.source_status = NULL
       WHERE d.doc_id = p_doc_id;
    END IF;
    COMMIT;
  EXCEPTION
    WHEN OTHERS THEN
      ROLLBACK;
  END ucm_filter_callback_common;

  PROCEDURE doc_processor(p_doc_id        IN NUMBER,
                          p_lib_code      IN VARCHAR2,
                          p_lib_type_code IN VARCHAR2,
                          p_comp_code     IN VARCHAR2,
                          p_status        IN VARCHAR2,
                          p_thumbnail_url IN VARCHAR2,
                          p_ucm_docname   IN VARCHAR2,
                          p_category_id   IN NUMBER DEFAULT NULL,
                          x_return_code   OUT VARCHAR2,
                          x_return_msg    OUT VARCHAR2) IS
    l_doc_row              cms_doc_t%ROWTYPE;
    l_doc_category_rel_row cms_doc_category_rel_t%ROWTYPE;
  BEGIN
    l_doc_row.doc_id := p_doc_id;
    IF p_doc_id IS NULL THEN
      SELECT cms_doc_s.NEXTVAL INTO l_doc_row.doc_id FROM dual;
      l_doc_row.lib_code              := p_lib_code;
      l_doc_row.lib_type_code         := p_lib_type_code;
      l_doc_row.comp_code             := p_comp_code;
      l_doc_row.object_version_number := 1;
      l_doc_row.created_by            := -1;
      l_doc_row.creation_date         := SYSDATE;
      l_doc_row.last_updated_by       := -1;
      l_doc_row.last_update_date      := SYSDATE;
      l_doc_row.status                := p_status;
      l_doc_row.thumbnail_url         := p_thumbnail_url;
      INSERT INTO cms_doc_t VALUES l_doc_row;
    
      UPDATE cms_doc_ucm_rel_t dur
         SET dur.ref_doc_id       = l_doc_row.doc_id,
             dur.last_update_date = SYSDATE
       WHERE upper(dur.ucm_docname) = upper(p_ucm_docname);
    
      SELECT cms_doc_category_rel_s.NEXTVAL
        INTO l_doc_category_rel_row.rel_id
        FROM dual;
      l_doc_category_rel_row.doc_id                := l_doc_row.doc_id;
      l_doc_category_rel_row.category_id           := nvl(p_category_id, -1);
      l_doc_category_rel_row.object_version_number := 1;
      l_doc_category_rel_row.created_by            := -1;
      l_doc_category_rel_row.creation_date         := SYSDATE;
      l_doc_category_rel_row.last_updated_by       := -1;
      l_doc_category_rel_row.last_update_date      := SYSDATE;
      l_doc_category_rel_row.category_group_type   := 'BY_CATEGORY';
      INSERT INTO cms_doc_category_rel_t VALUES l_doc_category_rel_row;
    ELSE
      UPDATE cms_doc_t d
         SET d.thumbnail_url    = p_thumbnail_url,
             d.last_update_date = SYSDATE
       WHERE d.doc_id = p_doc_id;
    END IF;
    x_return_code := 'S';
    x_return_msg  := to_char(l_doc_row.doc_id);
  EXCEPTION
    WHEN OTHERS THEN
      x_return_code := 'E';
      x_return_msg  := SQLCODE || SQLERRM;
  END doc_processor;

  PROCEDURE doc_ucm_rel_processor(p_doc_id          IN NUMBER,
                                  p_ucm_did         IN NUMBER,
                                  p_ucm_docname     IN VARCHAR2,
                                  p_file_name       IN VARCHAR2,
                                  p_ftp_path        IN VARCHAR2,
                                  p_thumbnail_url   IN VARCHAR2,
                                  p_swf_url         IN VARCHAR2,
                                  p_ftp_upload_date IN VARCHAR2,
                                  p_doc_type        IN VARCHAR2,
                                  p_ref_doc_id      IN VARCHAR2,
                                  x_return_code     OUT VARCHAR2,
                                  x_return_msg      OUT VARCHAR2) IS
    l_cms_doc_ucm_rel     cms_doc_ucm_rel_t%ROWTYPE;
    l_counter             NUMBER := 0;
    l_swf_url1            VARCHAR2(500);
    l_swf_url2            VARCHAR2(500);
    l_special_cover_count NUMBER := 0; --是否存在文件名为‘封面缩略图.jpg’的文件
    l_cover_name          VARCHAR2(50) := upper('封面缩略图.jpg');
  BEGIN
    SELECT COUNT(1)
      INTO l_counter
      FROM cms_doc_ucm_rel_t dur
     WHERE upper(dur.ucm_docname) = upper(p_ucm_docname)
       AND dur.doc_id = p_doc_id;
    IF l_counter = 0 THEN
      SELECT cms_doc_ucm_rel_s.NEXTVAL
        INTO l_cms_doc_ucm_rel.rel_id
        FROM dual;
      l_cms_doc_ucm_rel.doc_id          := p_doc_id;
      l_cms_doc_ucm_rel.ucm_did         := p_ucm_did;
      l_cms_doc_ucm_rel.ucm_docname     := upper(p_ucm_docname);
      l_cms_doc_ucm_rel.file_name       := p_file_name;
      l_cms_doc_ucm_rel.ftp_path        := p_ftp_path;
      l_cms_doc_ucm_rel.ftp_upload_date := nvl(p_ftp_upload_date,
                                               to_char(SYSDATE,
                                                       'yyyymmddhhmiss'));
      --如果p_doc_id和p_ref_doc_id相等，就说明这个文件不和其他文件关联，就不需要设置ref_doc_id的值
      IF p_doc_id <> to_number(p_ref_doc_id) THEN
        l_cms_doc_ucm_rel.ref_doc_id := to_number(p_ref_doc_id);
      END IF;
      l_cms_doc_ucm_rel.doc_type              := p_doc_type;
      l_cms_doc_ucm_rel.object_version_number := 1;
      l_cms_doc_ucm_rel.created_by            := -1;
      l_cms_doc_ucm_rel.creation_date         := SYSDATE;
      l_cms_doc_ucm_rel.last_updated_by       := -1;
      l_cms_doc_ucm_rel.last_update_date      := SYSDATE;
      IF p_thumbnail_url IS NOT NULL THEN
        l_cms_doc_ucm_rel.has_thumbnail_flag := 'Y';
      END IF;
      IF p_swf_url IS NOT NULL THEN
        IF instr(p_swf_url, ',') > 0 THEN
          l_swf_url1                   := substr(p_swf_url,
                                                 0,
                                                 instr(p_swf_url, ',') - 1);
          l_swf_url2                   := substr(p_swf_url,
                                                 instr(p_swf_url, ',') + 1);
          l_cms_doc_ucm_rel.attribute1 := l_swf_url1;
          l_cms_doc_ucm_rel.attribute2 := l_swf_url2;
        ELSE
          l_cms_doc_ucm_rel.attribute1 := p_swf_url;
          l_cms_doc_ucm_rel.attribute2 := p_swf_url;
        END IF;
      
      END IF;
      INSERT INTO cms_doc_ucm_rel_t VALUES l_cms_doc_ucm_rel;
    ELSE
      UPDATE cms_doc_ucm_rel_t dur
         SET dur.ucm_did = p_ucm_did, dur.last_update_date = SYSDATE
       WHERE upper(dur.ucm_docname) = upper(p_ucm_docname);
    END IF;
    --是否存在 封面缩略图.jpg
    SELECT COUNT(*)
      INTO l_special_cover_count
      FROM cms_doc_ucm_rel_t r
     WHERE r.doc_id = p_doc_id
       AND r.doc_type = 'COVER'
       AND upper(r.file_name) = l_cover_name;
    IF p_thumbnail_url IS NOT NULL AND p_doc_type = 'COVER' AND
       ((l_special_cover_count = 1 AND upper(p_file_name) = l_cover_name) OR
       l_special_cover_count = 0) THEN
      UPDATE cms_doc_t d
         SET d.thumbnail_url    = p_thumbnail_url,
             d.last_update_date = SYSDATE
       WHERE d.doc_id = p_doc_id;
    END IF;
    x_return_code := 'S';
  EXCEPTION
    WHEN OTHERS THEN
      x_return_code := 'E';
      x_return_msg  := SQLCODE || SQLERRM;
  END doc_ucm_rel_processor;

  PROCEDURE doc_rel_processor(p_doc_id       IN NUMBER,
                              p_ref_doc_id   IN NUMBER,
                              p_rel_type     IN VARCHAR2,
                              p_ref_rel_type IN VARCHAR2,
                              x_return_code  OUT VARCHAR2,
                              x_return_msg   OUT VARCHAR2) IS
    l_doc_rel_row cms_doc_rel_t%ROWTYPE;
  BEGIN
    IF nvl(p_doc_id, -99) <> -99 AND nvl(p_ref_doc_id, -99) <> -99 THEN
      SELECT cms_doc_rel_s.NEXTVAL INTO l_doc_rel_row.rel_id FROM dual;
      l_doc_rel_row.doc_id                := p_doc_id;
      l_doc_rel_row.parent_doc_id         := p_ref_doc_id;
      l_doc_rel_row.rel_type              := p_ref_rel_type;
      l_doc_rel_row.object_version_number := 1;
      l_doc_rel_row.created_by            := -1;
      l_doc_rel_row.creation_date         := SYSDATE;
      l_doc_rel_row.last_updated_by       := -1;
      l_doc_rel_row.last_update_date      := SYSDATE;
      INSERT INTO cms_doc_rel_t VALUES l_doc_rel_row;
    
      SELECT cms_doc_rel_s.NEXTVAL INTO l_doc_rel_row.rel_id FROM dual;
      l_doc_rel_row.doc_id                := p_ref_doc_id;
      l_doc_rel_row.parent_doc_id         := p_doc_id;
      l_doc_rel_row.rel_type              := p_rel_type;
      l_doc_rel_row.object_version_number := 1;
      l_doc_rel_row.created_by            := -1;
      l_doc_rel_row.creation_date         := SYSDATE;
      l_doc_rel_row.last_updated_by       := -1;
      l_doc_rel_row.last_update_date      := SYSDATE;
      INSERT INTO cms_doc_rel_t VALUES l_doc_rel_row;
    END IF;
    x_return_code := 'S';
  EXCEPTION
    WHEN OTHERS THEN
      x_return_code := 'E';
      x_return_msg  := SQLCODE || SQLERRM;
  END doc_rel_processor;

  PROCEDURE gather_monitor_processor(p_doc_id           IN NUMBER,
                                     p_file_name        IN VARCHAR2,
                                     p_ftp_path         IN VARCHAR2,
                                     p_thumbnail_result IN VARCHAR2,
                                     p_doc_type         IN VARCHAR2 DEFAULT NULL,
                                     p_comp_code        IN VARCHAR2 DEFAULT NULL,
                                     p_result           IN VARCHAR2,
                                     p_remarks          IN VARCHAR2) IS
    PRAGMA AUTONOMOUS_TRANSACTION;
    l_user_name VARCHAR2(80);
  BEGIN
    --回写采集状态
    IF p_doc_id = -99 THEN
      SELECT substr(p_ftp_path,
                    instr(p_ftp_path, '/', 1, 3) + 1,
                    instr(p_ftp_path, '/', 1, 4) -
                    instr(p_ftp_path, '/', 1, 3) - 1)
        INTO l_user_name
        FROM dual;
    END IF;
  
    UPDATE cms_gather_monitor_t gm
       SET gm.RESULT           = p_result,
           gm.thumbnail_result = p_thumbnail_result,
           gm.remarks          = p_remarks,
           gm.last_update_date = SYSDATE
     WHERE gm.doc_id = decode(p_doc_id, -99, gm.doc_id, p_doc_id)
       AND gm.file_name = p_file_name
       AND gm.file_path = p_ftp_path
       AND nvl(gm.attribute2, '-99') =
           nvl(p_comp_code, nvl(gm.attribute2, '-99'))
          /*AND nvl(gm.attribute3, '-99') =nvl(p_doc_type, nvl(gm.attribute3, '-99'))*/
       AND nvl(gm.attribute1, '-99') =
           decode(p_doc_id, -99, l_user_name, nvl(gm.attribute1, '-99'));
    COMMIT;
    IF nvl(p_doc_id, -99) <> -99 THEN
      end_sync(p_doc_id); --当所有文件同步完成后结束同步
    END IF;
  EXCEPTION
    WHEN OTHERS THEN
      ROLLBACK;
  END gather_monitor_processor;

  PROCEDURE assort_processor(p_doc_id             IN NUMBER,
                             p_ref_doc_id         IN NUMBER,
                             p_thumbnail_url      IN VARCHAR2,
                             p_swf_url            IN VARCHAR2,
                             p_file_name          IN VARCHAR2,
                             p_ucm_docname        IN VARCHAR2,
                             p_ucm_did            IN NUMBER,
                             p_ftp_upload_date    IN VARCHAR2,
                             p_doc_type           IN VARCHAR2,
                             p_comp_code          IN VARCHAR2 DEFAULT NULL,
                             p_category_id        IN NUMBER DEFAULT NULL,
                             p_file_size          IN VARCHAR2 DEFAULT NULL,
                             p_format             IN VARCHAR2 DEFAULT NULL,
                             p_figure_resolution  IN VARCHAR2 DEFAULT NULL,
                             p_figure_xresolution IN VARCHAR2 DEFAULT NULL,
                             p_figure_yresolution IN VARCHAR2 DEFAULT NULL,
                             p_figure_width       IN VARCHAR2 DEFAULT NULL,
                             p_figure_height      IN VARCHAR2 DEFAULT NULL,
                             x_return_code        OUT VARCHAR2,
                             x_return_msg         OUT VARCHAR2) IS
    l_res_name               VARCHAR2(1000);
    l_doc_row                cms_doc_t%ROWTYPE;
    l_ref_doc_id             NUMBER;
    l_illustration_row       cms_illustration_t%ROWTYPE;
    l_audio_row              cms_audio_t%ROWTYPE;
    l_video_row              cms_video_t%ROWTYPE;
    l_material_figure_row    cms_material_figure_t%ROWTYPE;
    l_photography_figure_row cms_photography_figure_t%ROWTYPE;
    l_exp EXCEPTION;
    l_lib_code      VARCHAR2(100);
    l_lib_type_code VARCHAR2(100) := 'MATERIAL_LIB';
    l_status_prefix VARCHAR2(100) := 'TO_INDEX_';
    l_comp_code     VARCHAR2(100) := p_comp_code;
  BEGIN
    IF nvl(p_doc_id, -99) <> -99 THEN
      SELECT * INTO l_doc_row FROM cms_doc_t d WHERE d.doc_id = p_doc_id;
      l_comp_code := l_doc_row.comp_code;
    END IF;
    IF p_doc_type = 'ILLUSTRATION' THEN
      l_lib_code := 'ILLUSTRATION';
    ELSIF p_doc_type = 'AUDIO' THEN
      l_lib_code := 'AUDIO';
    ELSIF p_doc_type = 'VIDEO' THEN
      l_lib_code := 'VIDEO';
    ELSIF p_doc_type = 'MATERIAL_FIGURE' THEN
      l_lib_code := 'MATERIAL_FIGURE';
    ELSIF p_doc_type = 'PHOTOGRAPHY_FIGURE' THEN
      l_lib_code := 'PHOTOGRAPHY_FIGURE';
    END IF;
  
    doc_processor(p_doc_id        => p_ref_doc_id,
                  p_lib_code      => l_lib_code,
                  p_lib_type_code => l_lib_type_code,
                  p_comp_code     => l_comp_code,
                  p_status        => l_status_prefix || l_lib_code,
                  p_thumbnail_url => p_thumbnail_url,
                  p_ucm_docname   => p_ucm_docname,
                  p_category_id   => p_category_id,
                  x_return_code   => x_return_code,
                  x_return_msg    => x_return_msg);
    IF x_return_code = 'S' THEN
      l_ref_doc_id := to_number(x_return_msg);
    ELSE
      RAISE l_exp;
    END IF;
  
    doc_ucm_rel_processor(p_doc_id          => l_ref_doc_id,
                          p_ucm_did         => p_ucm_did,
                          p_ucm_docname     => p_ucm_docname,
                          p_file_name       => p_file_name,
                          p_ftp_path        => '原始文件',
                          p_thumbnail_url   => p_thumbnail_url,
                          p_swf_url         => p_swf_url,
                          p_ftp_upload_date => p_ftp_upload_date,
                          p_doc_type        => p_doc_type,
                          p_ref_doc_id      => NULL,
                          x_return_code     => x_return_code,
                          x_return_msg      => x_return_msg);
    IF x_return_code <> 'S' THEN
      RAISE l_exp;
    END IF;
  
    IF p_doc_type = 'ILLUSTRATION' THEN
      IF p_ref_doc_id IS NULL THEN
        SELECT rci.res_name
          INTO l_res_name
          FROM cms_res_common_info_v rci
         WHERE rci.doc_id = p_doc_id;
      
        SELECT cms_illustration_s.NEXTVAL
          INTO l_illustration_row.illustration_id
          FROM dual;
        l_illustration_row.doc_id              := l_ref_doc_id;
        l_illustration_row.illustration_name   := p_file_name;
        l_illustration_row.illustration_source := l_res_name;
        l_illustration_row.figure_size         := p_file_size;
        l_illustration_row.figure_format       := p_format;
        l_illustration_row.figure_store_time   := SYSDATE;
        IF p_figure_width IS NOT NULL THEN
          l_illustration_row.figure_dimension := p_figure_width || 'x' ||
                                                 p_figure_height;
        END IF;
        l_illustration_row.figure_resolution     := p_figure_resolution;
        l_illustration_row.figure_x_resolution   := p_figure_xresolution;
        l_illustration_row.figure_y_resolution   := p_figure_yresolution;
        l_illustration_row.figure_width          := p_figure_width;
        l_illustration_row.figure_height         := p_figure_height;
        l_illustration_row.object_version_number := 1;
        l_illustration_row.created_by            := -1;
        l_illustration_row.creation_date         := SYSDATE;
        l_illustration_row.last_updated_by       := -1;
        l_illustration_row.last_update_date      := SYSDATE;
        INSERT INTO cms_illustration_t VALUES l_illustration_row;
      
        doc_rel_processor(p_doc_id       => p_doc_id,
                          p_ref_doc_id   => l_ref_doc_id,
                          p_rel_type     => l_doc_row.lib_code,
                          p_ref_rel_type => 'ILLUSTRATION',
                          x_return_code  => x_return_code,
                          x_return_msg   => x_return_msg);
        IF x_return_code <> 'S' THEN
          RAISE l_exp;
        END IF;
      ELSE
        UPDATE cms_illustration_t i
           SET i.illustration_name   = p_file_name,
               i.illustration_source = l_res_name,
               i.last_update_date    = SYSDATE
         WHERE i.doc_id = p_ref_doc_id;
      END IF;
    ELSIF p_doc_type = 'AUDIO' THEN
      IF p_ref_doc_id IS NULL THEN
        SELECT cms_audio_s.NEXTVAL INTO l_audio_row.audio_id FROM dual;
        l_audio_row.doc_id                := l_ref_doc_id;
        l_audio_row.file_name             := p_file_name;
        l_audio_row.file_size             := p_file_size;
        l_audio_row.file_format           := p_format;
        l_audio_row.file_store_time       := SYSDATE;
        l_audio_row.object_version_number := 1;
        l_audio_row.created_by            := -1;
        l_audio_row.creation_date         := SYSDATE;
        l_audio_row.last_updated_by       := -1;
        l_audio_row.last_update_date      := SYSDATE;
        INSERT INTO cms_audio_t VALUES l_audio_row;
      
        doc_rel_processor(p_doc_id       => p_doc_id,
                          p_ref_doc_id   => l_ref_doc_id,
                          p_rel_type     => l_doc_row.lib_code,
                          p_ref_rel_type => 'AUDIO',
                          x_return_code  => x_return_code,
                          x_return_msg   => x_return_msg);
        IF x_return_code <> 'S' THEN
          RAISE l_exp;
        END IF;
      ELSE
        UPDATE cms_audio_t a
           SET a.file_name = p_file_name, a.last_update_date = SYSDATE
         WHERE a.doc_id = p_ref_doc_id;
      END IF;
    ELSIF p_doc_type = 'VIDEO' THEN
      IF p_ref_doc_id IS NULL THEN
        SELECT cms_video_s.NEXTVAL INTO l_video_row.video_id FROM dual;
        l_video_row.doc_id                := l_ref_doc_id;
        l_video_row.file_name             := p_file_name;
        l_video_row.file_size             := p_file_size;
        l_video_row.file_format           := p_format;
        l_video_row.file_store_time       := SYSDATE;
        l_video_row.object_version_number := 1;
        l_video_row.created_by            := -1;
        l_video_row.creation_date         := SYSDATE;
        l_video_row.last_updated_by       := -1;
        l_video_row.last_update_date      := SYSDATE;
        INSERT INTO cms_video_t VALUES l_video_row;
      
        doc_rel_processor(p_doc_id       => p_doc_id,
                          p_ref_doc_id   => l_ref_doc_id,
                          p_rel_type     => l_doc_row.lib_code,
                          p_ref_rel_type => 'VIDEO',
                          x_return_code  => x_return_code,
                          x_return_msg   => x_return_msg);
        IF x_return_code <> 'S' THEN
          RAISE l_exp;
        END IF;
      ELSE
        UPDATE cms_video_t v
           SET v.file_name = p_file_name, v.last_update_date = SYSDATE
         WHERE v.doc_id = p_ref_doc_id;
      END IF;
    ELSIF p_doc_type = 'MATERIAL_FIGURE' THEN
      IF p_ref_doc_id IS NULL THEN
        SELECT cms_material_figure_s.NEXTVAL
          INTO l_material_figure_row.figure_id
          FROM dual;
        l_material_figure_row.doc_id      := l_ref_doc_id;
        l_material_figure_row.figure_name := p_file_name;
        IF p_figure_width IS NOT NULL THEN
          l_material_figure_row.figure_dimension := p_figure_width || 'x' ||
                                                    p_figure_height;
        END IF;
        l_material_figure_row.figure_resolution     := p_figure_resolution;
        l_material_figure_row.figure_x_resolution   := p_figure_xresolution;
        l_material_figure_row.figure_y_resolution   := p_figure_yresolution;
        l_material_figure_row.figure_width          := p_figure_width;
        l_material_figure_row.figure_height         := p_figure_height;
        l_material_figure_row.figure_store_time     := SYSDATE;
        l_material_figure_row.object_version_number := 1;
        l_material_figure_row.created_by            := -1;
        l_material_figure_row.creation_date         := SYSDATE;
        l_material_figure_row.last_updated_by       := -1;
        l_material_figure_row.last_update_date      := SYSDATE;
        INSERT INTO cms_material_figure_t VALUES l_material_figure_row;
      
        doc_rel_processor(p_doc_id       => p_doc_id,
                          p_ref_doc_id   => l_ref_doc_id,
                          p_rel_type     => l_doc_row.lib_code,
                          p_ref_rel_type => 'MATERIAL_FIGURE',
                          x_return_code  => x_return_code,
                          x_return_msg   => x_return_msg);
        IF x_return_code <> 'S' THEN
          RAISE l_exp;
        END IF;
      ELSE
        NULL;
        /*UPDATE cms_material_figure_t mf
          SET mf.figure_name = p_file_name, mf.last_update_date = SYSDATE
        WHERE mf.doc_id = p_ref_doc_id;*/
      END IF;
    ELSIF p_doc_type = 'PHOTOGRAPHY_FIGURE' THEN
      IF p_ref_doc_id IS NULL THEN
        SELECT cms_photography_figure_s.NEXTVAL
          INTO l_photography_figure_row.figure_id
          FROM dual;
        l_photography_figure_row.doc_id      := l_ref_doc_id;
        l_photography_figure_row.figure_name := p_file_name;
        IF p_figure_width IS NOT NULL THEN
          l_photography_figure_row.figure_dimension := p_figure_width || 'x' ||
                                                       p_figure_height;
        END IF;
        l_photography_figure_row.figure_resolution     := p_figure_resolution;
        l_photography_figure_row.figure_x_resolution   := p_figure_xresolution;
        l_photography_figure_row.figure_y_resolution   := p_figure_yresolution;
        l_photography_figure_row.figure_width          := p_figure_width;
        l_photography_figure_row.figure_height         := p_figure_height;
        l_photography_figure_row.object_version_number := 1;
        l_photography_figure_row.created_by            := -1;
        l_photography_figure_row.creation_date         := SYSDATE;
        l_photography_figure_row.last_updated_by       := -1;
        l_photography_figure_row.last_update_date      := SYSDATE;
        INSERT INTO cms_photography_figure_t
        VALUES l_photography_figure_row;
      
        doc_rel_processor(p_doc_id       => p_doc_id,
                          p_ref_doc_id   => l_ref_doc_id,
                          p_rel_type     => l_doc_row.lib_code,
                          p_ref_rel_type => 'PHOTOGRAPHY_FIGURE',
                          x_return_code  => x_return_code,
                          x_return_msg   => x_return_msg);
        IF x_return_code <> 'S' THEN
          RAISE l_exp;
        END IF;
      ELSE
        NULL;
        /*UPDATE cms_photography_figure_t pf
          SET pf.figure_name = p_file_name, pf.last_update_date = SYSDATE
        WHERE pf.doc_id = p_ref_doc_id;*/
      END IF;
    END IF;
    x_return_code := 'S';
    x_return_msg  := to_char(l_ref_doc_id);
  EXCEPTION
    WHEN l_exp THEN
      NULL;
    WHEN OTHERS THEN
      x_return_code := 'E';
      x_return_msg  := SQLCODE || SQLERRM;
  END assort_processor;

  --UCM Checkin Filter回写CMS信息.
  PROCEDURE ucm_checkin_callback(p_doc_id          IN NUMBER,
                                 p_ucm_did         IN NUMBER,
                                 p_ucm_docname     IN VARCHAR2,
                                 p_file_name       IN VARCHAR2,
                                 p_ftp_path        IN VARCHAR2,
                                 p_thumbnail_url   IN VARCHAR2,
                                 p_swf_url         IN VARCHAR2,
                                 p_ftp_upload_date IN VARCHAR2,
                                 p_doc_type        IN VARCHAR2,
                                 p_ref_doc_id      IN VARCHAR2,
                                 p_attribute1      IN VARCHAR2,
                                 p_attribute2      IN VARCHAR2,
                                 p_attribute3      IN VARCHAR2,
                                 p_attribute4      IN VARCHAR2,
                                 p_attribute5      IN VARCHAR2,
                                 p_attribute6      IN VARCHAR2,
                                 p_attribute7      IN VARCHAR2,
                                 p_attribute8      IN VARCHAR2,
                                 p_attribute9      IN VARCHAR2,
                                 p_attribute10     IN VARCHAR2,
                                 x_return_code     OUT VARCHAR2,
                                 x_return_msg      OUT VARCHAR2) IS
    l_exp EXCEPTION;
    l_thumbnail_result      VARCHAR2(20);
    l_ref_doc_id            NUMBER;
    l_counter               NUMBER;
    l_doc_id                NUMBER := p_doc_id;
    l_catogory_id           NUMBER;
    l_ftp_path_last_section VARCHAR2(500);
  BEGIN
    --如果是正常上传
    IF nvl(l_doc_id, -99) <> -99 THEN
      doc_ucm_rel_processor(p_doc_id          => l_doc_id,
                            p_ucm_did         => p_ucm_did,
                            p_ucm_docname     => p_ucm_docname,
                            p_file_name       => p_file_name,
                            p_ftp_path        => p_ftp_path,
                            p_thumbnail_url   => p_thumbnail_url,
                            p_swf_url         => p_swf_url,
                            p_ftp_upload_date => p_ftp_upload_date,
                            p_doc_type        => p_doc_type,
                            p_ref_doc_id      => p_ref_doc_id,
                            x_return_code     => x_return_code,
                            x_return_msg      => x_return_msg);
      IF x_return_code <> 'S' THEN
        RAISE l_exp;
      END IF;
    END IF;
  
    SELECT COUNT(1)
      INTO l_counter
      FROM cms_doc_t d
     WHERE d.doc_id = l_doc_id;
    --如果上传的文件类型为插图、音频、视频、素材图、摄影图，则生成配套的记录
    --l_counter > 0代表文件是属于正常上传
    --l_doc_id为-99代表是文件是属于批量上传
    IF p_doc_type IN ('ILLUSTRATION', 'AUDIO', 'VIDEO', 'MATERIAL_FIGURE',
        'PHOTOGRAPHY_FIGURE') AND (l_counter > 0 OR l_doc_id = -99) THEN
      --如果是插图，则分类与图书的分类保持一致
      IF p_doc_type = 'ILLUSTRATION' THEN
        BEGIN
          SELECT r.category_id
            INTO l_catogory_id
            FROM cms_doc_category_rel_t r, cms_doc_t d
           WHERE r.doc_id = d.doc_id
             AND d.doc_id = p_doc_id;
        EXCEPTION
          WHEN OTHERS THEN
            l_catogory_id := -1;
        END;
      ELSE
        --如果文件是属于批量上传的，则取ftp上分类文件夹上_后所带的分类id
        IF instr(p_ftp_path, '_', -1) > 0 THEN
          BEGIN
            l_ftp_path_last_section := get_ftp_path_last_section(p_ftp_path);
            l_catogory_id           := to_number(substr(l_ftp_path_last_section,
                                                        instr(l_ftp_path_last_section,
                                                              '_',
                                                              -1) + 1));
          EXCEPTION
            WHEN OTHERS THEN
              l_catogory_id := -1;
          END;
        END IF;
      END IF;
      assort_processor(p_doc_id             => l_doc_id,
                       p_ref_doc_id         => to_number(p_ref_doc_id),
                       p_thumbnail_url      => p_thumbnail_url,
                       p_swf_url            => NULL,
                       p_file_name          => p_file_name,
                       p_ucm_docname        => p_ucm_docname,
                       p_ucm_did            => p_ucm_did,
                       p_ftp_upload_date    => p_ftp_upload_date,
                       p_doc_type           => p_doc_type,
                       p_comp_code          => p_attribute1,
                       p_category_id        => l_catogory_id,
                       p_file_size          => p_attribute2,
                       p_format             => p_attribute3,
                       p_figure_resolution  => p_attribute4,
                       p_figure_xresolution => p_attribute5,
                       p_figure_yresolution => p_attribute6,
                       p_figure_width       => p_attribute7,
                       p_figure_height      => p_attribute8,
                       x_return_code        => x_return_code,
                       x_return_msg         => x_return_msg);
      IF x_return_code = 'S' THEN
        l_ref_doc_id := to_number(x_return_msg);
      ELSE
        RAISE l_exp;
      END IF;
    END IF;
    --如果是插图、封面、素材图、摄影图等需要设置缩略图采集结果
    IF p_doc_type IN
       ('ILLUSTRATION', 'COVER', 'MATERIAL_FIGURE', 'PHOTOGRAPHY_FIGURE') THEN
      SELECT decode(p_thumbnail_url, NULL, 'FAIL', 'SUCCESS')
        INTO l_thumbnail_result
        FROM dual;
    ELSE
      l_thumbnail_result := '-';
    END IF;
  
    COMMIT;
    x_return_code := 'S';
    x_return_msg  := to_char(l_ref_doc_id);
  
    gather_monitor_processor(p_doc_id           => l_doc_id,
                             p_file_name        => p_file_name,
                             p_ftp_path         => p_ftp_path,
                             p_thumbnail_result => l_thumbnail_result,
                             p_doc_type         => p_doc_type,
                             p_comp_code        => p_attribute1,
                             p_result           => 'SUCCESS',
                             p_remarks          => NULL);
  EXCEPTION
    WHEN l_exp THEN
      ROLLBACK;
      gather_monitor_processor(p_doc_id           => l_doc_id,
                               p_file_name        => p_file_name,
                               p_ftp_path         => p_ftp_path,
                               p_thumbnail_result => l_thumbnail_result,
                               p_doc_type         => p_doc_type,
                               p_comp_code        => p_attribute1,
                               p_result           => 'FAIL',
                               p_remarks          => x_return_msg);
    WHEN OTHERS THEN
      ROLLBACK;
      x_return_code := 'E';
      x_return_msg  := SQLCODE || SQLERRM;
      gather_monitor_processor(p_doc_id           => l_doc_id,
                               p_file_name        => p_file_name,
                               p_ftp_path         => p_ftp_path,
                               p_thumbnail_result => l_thumbnail_result,
                               p_doc_type         => p_doc_type,
                               p_comp_code        => p_attribute1,
                               p_result           => 'FAIL',
                               p_remarks          => x_return_msg);
  END ucm_checkin_callback;

  --UCM Delete Filter回写CMS信息.
  PROCEDURE ucm_delete_callback(p_ucm_docname IN VARCHAR2,
                                p_attribute1  IN VARCHAR2,
                                p_attribute2  IN VARCHAR2,
                                p_attribute3  IN VARCHAR2,
                                p_attribute4  IN VARCHAR2,
                                p_attribute5  IN VARCHAR2,
                                p_attribute6  IN VARCHAR2,
                                p_attribute7  IN VARCHAR2,
                                p_attribute8  IN VARCHAR2,
                                p_attribute9  IN VARCHAR2,
                                p_attribute10 IN VARCHAR2,
                                x_return_code OUT VARCHAR2,
                                x_return_msg  OUT VARCHAR2) IS
    l_doc_id    NUMBER;
    l_file_name VARCHAR2(80);
    l_file_path VARCHAR2(240);
  BEGIN
    FOR a_res IN (SELECT dur.has_thumbnail_flag,
                         dur.doc_id,
                         dur.ref_doc_id,
                         dur.file_name,
                         dur.ftp_path,
                         dur.doc_type
                    FROM cms_doc_ucm_rel_t dur
                   WHERE dur.ucm_docname = p_ucm_docname) LOOP
      l_doc_id    := a_res.doc_id;
      l_file_name := a_res.file_name;
      l_file_path := a_res.ftp_path;
      IF a_res.has_thumbnail_flag = 'Y' AND a_res.ref_doc_id IS NULL THEN
        UPDATE cms_doc_t d
           SET d.thumbnail_url = NULL, d.last_update_date = SYSDATE
         WHERE d.doc_id = a_res.doc_id;
      END IF;
    
      IF a_res.ref_doc_id IS NOT NULL THEN
      
        DELETE cms_doc_t d WHERE d.doc_id = a_res.ref_doc_id;
      
        DELETE cms_doc_category_rel_t dcr
         WHERE dcr.doc_id = a_res.ref_doc_id;
      
        IF a_res.doc_type = 'ILLUSTRATION' THEN
          DELETE cms_illustration_t i WHERE i.doc_id = a_res.ref_doc_id;
        ELSIF a_res.doc_type = 'VIDEO' THEN
          DELETE cms_video_t v WHERE v.doc_id = a_res.ref_doc_id;
        ELSIF a_res.doc_type = 'AUDIO' THEN
          DELETE cms_audio_t a WHERE a.doc_id = a_res.ref_doc_id;
        END IF;
      
      END IF;
    
      --删除关联
      DELETE FROM cms_doc_rel_t dr
       WHERE (dr.doc_id = a_res.doc_id AND
             dr.parent_doc_id = a_res.ref_doc_id)
          OR (dr.doc_id = a_res.ref_doc_id AND
             dr.parent_doc_id = a_res.doc_id);
      COMMIT;
    
      gather_monitor_processor(p_doc_id           => l_doc_id,
                               p_file_name        => l_file_name,
                               p_ftp_path         => l_file_path,
                               p_thumbnail_result => '-',
                               p_result           => 'SUCCESS',
                               p_remarks          => NULL);
    END LOOP;
  
    DELETE FROM cms_doc_ucm_rel_t dur
     WHERE upper(dur.ucm_docname) = upper(p_ucm_docname);
  
    x_return_code := 'S';
  
  EXCEPTION
    WHEN OTHERS THEN
      ROLLBACK;
      x_return_code := 'E';
      x_return_msg  := SQLCODE || SQLERRM;
    
      gather_monitor_processor(p_doc_id           => l_doc_id,
                               p_file_name        => l_file_name,
                               p_ftp_path         => l_file_path,
                               p_thumbnail_result => '-',
                               p_result           => 'FAIL',
                               p_remarks          => x_return_msg);
  END ucm_delete_callback;

  PROCEDURE start_docbook_monitor(p_job_id      IN NUMBER,
                                  p_status      IN VARCHAR2,
                                  x_return_code OUT VARCHAR2,
                                  x_return_msg  OUT VARCHAR2) AS
    l_docbook_monitor cms_docbook_monitor_t%ROWTYPE;
  BEGIN
    IF p_status = 'start_varifyStage' THEN
      DELETE FROM cms_docbook_monitor_t dm WHERE dm.job_id = p_job_id;
    END IF;
    SELECT cms_docbook_monitor_s.NEXTVAL
      INTO l_docbook_monitor.monitor_id
      FROM dual;
    l_docbook_monitor.job_id     := p_job_id;
    l_docbook_monitor.start_date := SYSDATE;
    l_docbook_monitor.status     := p_status;
    l_docbook_monitor.RESULT     := 'ONGOING';
    INSERT INTO cms_docbook_monitor_t VALUES l_docbook_monitor;
    COMMIT;
    x_return_code := 'S';
    x_return_msg  := l_docbook_monitor.monitor_id;
  EXCEPTION
    WHEN OTHERS THEN
      ROLLBACK;
      x_return_code := 'E';
      x_return_msg  := SQLCODE || SQLERRM;
  END start_docbook_monitor;

  PROCEDURE end_docbook_monitor(p_monitor_id  IN NUMBER,
                                p_status      IN VARCHAR2,
                                p_result      IN VARCHAR2,
                                p_remarks     IN VARCHAR2,
                                x_return_code OUT VARCHAR2,
                                x_return_msg  OUT VARCHAR2) AS
  BEGIN
    UPDATE cms_docbook_monitor_t dm
       SET dm.start_date = SYSDATE,
           dm.end_date   = SYSDATE,
           dm.status     = p_status,
           dm.RESULT     = p_result,
           dm.remarks    = p_remarks
     WHERE dm.monitor_id = p_monitor_id;
    IF p_result = 'FAIL' THEN
      UPDATE cms_docbook_job_t dj
         SET dj.status = 'FAIL_DOCBOOK'
       WHERE dj.job_id = (SELECT dm.job_id
                            FROM cms_docbook_monitor_t dm
                           WHERE dm.monitor_id = p_monitor_id);
    ELSIF p_result = 'SUCCESS' AND p_status = 'jingbianMaintainStage' THEN
      UPDATE cms_docbook_job_t dj
         SET dj.status = 'IMPORTED_DOCBOOK'
       WHERE dj.job_id = (SELECT dm.job_id
                            FROM cms_docbook_monitor_t dm
                           WHERE dm.monitor_id = p_monitor_id);
    END IF;
    COMMIT;
    x_return_code := 'S';
  EXCEPTION
    WHEN OTHERS THEN
      ROLLBACK;
      x_return_code := 'E';
      x_return_msg  := SQLCODE || SQLERRM;
  END end_docbook_monitor;

  PROCEDURE imp_book_metadata(p_comp_code     IN VARCHAR2,
                              p_book_name     IN VARCHAR2,
                              p_isbn          IN VARCHAR2,
                              p_impression    IN VARCHAR2,
                              p_edition       IN VARCHAR2,
                              p_chief_editor  IN VARCHAR2,
                              p_editor        IN VARCHAR2,
                              p_price         IN VARCHAR2,
                              p_keyword       IN VARCHAR2,
                              p_pub_time      IN VARCHAR2,
                              p_publish_house IN VARCHAR2,
                              p_lang          IN VARCHAR2,
                              p_author        IN VARCHAR2,
                              p_series_name   IN VARCHAR2,
                              p_book_size     IN VARCHAR2,
                              p_format_design IN VARCHAR2,
                              p_cover_design  IN VARCHAR2,
                              p_pagenum       IN VARCHAR2,
                              p_remarks       IN VARCHAR2,
                              p_category_name IN VARCHAR2,
                              p_reader_group  IN VARCHAR2,
                              p_suggestion    IN VARCHAR2,
                              p_traslator     IN VARCHAR2,
                              p_prod_size     IN VARCHAR2,
                              p_color_num     IN VARCHAR2,
                              p_cover_typeset IN VARCHAR2,
                              p_assort_prod   IN VARCHAR2,
                              p_main_page     IN VARCHAR2,
                              p_main_typeset  IN VARCHAR2,
                              p_format_comp   IN VARCHAR2,
                              p_item_code     IN VARCHAR2,
                              x_return_code   OUT VARCHAR2,
                              x_return_msg    OUT VARCHAR2) AS
    l_doc              cms_doc_t%ROWTYPE;
    l_book             cms_book_t%ROWTYPE;
    l_doc_category_rel cms_doc_category_rel_t%ROWTYPE;
    l_book_id          NUMBER;
    l_doc_id           NUMBER;
    l_lang             VARCHAR2(40) := NULL;
    l_price            NUMBER := NULL;
    l_book_size        VARCHAR2(40) := NULL;
    l_pagenum          NUMBER := NULL;
    l_category_id      NUMBER := -1;
    l_reader_group     VARCHAR2(40) := NULL;
    l_prod_size        VARCHAR2(40) := NULL;
    l_color_num        VARCHAR2(40) := NULL;
    l_cover_typeset    VARCHAR2(40) := NULL;
    l_assort_prod      VARCHAR2(40) := NULL;
    l_main_page        VARCHAR2(40) := NULL;
    l_main_typeset     VARCHAR2(40) := NULL;
  BEGIN
    --语种
    BEGIN
      SELECT lv.lookup_value_code
        INTO l_lang
        FROM cms_lookup_value_v lv
       WHERE lv.lookup_type_code = 'LANG'
         AND lv.meaning = p_lang;
    EXCEPTION
      WHEN OTHERS THEN
        NULL;
    END;
    --开本
    BEGIN
      SELECT lv.lookup_value_code
        INTO l_book_size
        FROM cms_lookup_value_v lv
       WHERE lv.lookup_type_code = 'BOOK_SIZE'
         AND lv.meaning = p_book_size;
    EXCEPTION
      WHEN OTHERS THEN
        NULL;
    END;
    --价格
    BEGIN
      l_price := to_number(p_price);
    EXCEPTION
      WHEN OTHERS THEN
        NULL;
    END;
    --页数
    BEGIN
      l_pagenum := to_number(p_pagenum);
    EXCEPTION
      WHEN OTHERS THEN
        NULL;
    END;
    --读者对象
    BEGIN
      SELECT lv.lookup_value_code
        INTO l_reader_group
        FROM cms_lookup_value_v lv
       WHERE lv.lookup_type_code = 'READER_GROUP'
         AND lv.meaning = p_reader_group;
    EXCEPTION
      WHEN OTHERS THEN
        NULL;
    END;
    --成品尺寸
    BEGIN
      SELECT lv.lookup_value_code
        INTO l_prod_size
        FROM cms_lookup_value_v lv
       WHERE lv.lookup_type_code = 'PROD_SIZE'
         AND lv.meaning = p_prod_size;
    EXCEPTION
      WHEN OTHERS THEN
        NULL;
    END;
    --正文色数
    BEGIN
      SELECT lv.lookup_value_code
        INTO l_color_num
        FROM cms_lookup_value_v lv
       WHERE lv.lookup_type_code = 'COLOR_NUM'
         AND lv.meaning = p_color_num;
    EXCEPTION
      WHEN OTHERS THEN
        NULL;
    END;
    --封面排版软件
    BEGIN
      SELECT lv.lookup_value_code
        INTO l_cover_typeset
        FROM cms_lookup_value_v lv
       WHERE lv.lookup_type_code = 'TYPESET'
         AND lv.meaning = p_cover_typeset;
    EXCEPTION
      WHEN OTHERS THEN
        NULL;
    END;
    --配套产品
    BEGIN
      SELECT lv.lookup_value_code
        INTO l_assort_prod
        FROM cms_lookup_value_v lv
       WHERE lv.lookup_type_code = 'ASSORT_PROD'
         AND lv.meaning = p_assort_prod;
    EXCEPTION
      WHEN OTHERS THEN
        NULL;
    END;
    --正文用纸
    BEGIN
      SELECT lv.lookup_value_code
        INTO l_main_page
        FROM cms_lookup_value_v lv
       WHERE lv.lookup_type_code = 'MAIN_PAGE'
         AND lv.meaning = p_main_page;
    EXCEPTION
      WHEN OTHERS THEN
        NULL;
    END;
    --正文排版软件
    BEGIN
      SELECT lv.lookup_value_code
        INTO l_main_typeset
        FROM cms_lookup_value_v lv
       WHERE lv.lookup_type_code = 'TYPESET'
         AND lv.meaning = p_main_typeset;
    EXCEPTION
      WHEN OTHERS THEN
        NULL;
    END;
    BEGIN
      SELECT b.book_id, b.doc_id
        INTO l_book_id, l_doc_id
        FROM cms_book_t b
       WHERE b.book_name = p_book_name
         AND b.isbn = p_isbn
         AND b.impression = p_impression
         AND b.edition = p_edition;
    
      UPDATE cms_doc_t d
         SET d.comp_code = p_comp_code
       WHERE d.doc_id = l_doc_id;
    
      UPDATE cms_book_t b
         SET b.lang             = l_lang,
             b.price            = l_price,
             b.pub_time         = to_date(p_pub_time, 'dd-mm-yyyy'),
             b.chief_editor     = p_chief_editor,
             b.editor           = p_editor,
             b.keyword          = p_keyword,
             b.publishing_house = p_publish_house,
             b.author           = p_author,
             b.series           = p_series_name,
             b.book_size        = l_book_size,
             b.format_design    = p_format_design,
             b.cover_design     = p_cover_design,
             b.pagination       = l_pagenum,
             b.remarks          = p_remarks,
             b.reader_group     = l_reader_group,
             b.suggestion       = p_suggestion,
             b.translator       = p_traslator,
             b.prod_size        = l_prod_size,
             b.color_num        = l_color_num,
             b.cover_typeset    = l_cover_typeset,
             b.assort_prod      = l_assort_prod,
             b.main_page        = l_main_page,
             b.main_typeset     = l_main_typeset,
             b.format_comp      = p_format_comp,
             b.item_code        = p_item_code,
             b.last_updated_by  = -1,
             b.last_update_date = SYSDATE
       WHERE b.book_id = l_book_id;
    EXCEPTION
      WHEN no_data_found THEN
        SELECT cms_doc_s.NEXTVAL INTO l_doc_id FROM dual;
        l_doc.doc_id                := l_doc_id;
        l_doc.lib_code              := 'BOOK';
        l_doc.lib_type_code         := 'END_PROD_LIB';
        l_doc.comp_code             := p_comp_code;
        l_doc.status                := 'PUBED_BOOK';
        l_doc.object_version_number := 1;
        l_doc.created_by            := -1;
        l_doc.creation_date         := SYSDATE;
        l_doc.last_updated_by       := -1;
        l_doc.last_update_date      := SYSDATE;
        INSERT INTO cms_doc_t VALUES l_doc;
      
        SELECT cms_book_s.NEXTVAL INTO l_book.book_id FROM dual;
        l_book.doc_id                := l_doc_id;
        l_book.book_name             := p_book_name;
        l_book.isbn                  := p_isbn;
        l_book.impression            := p_impression;
        l_book.edition               := p_edition;
        l_book.lang                  := l_lang;
        l_book.price                 := l_price;
        l_book.chief_editor          := p_chief_editor;
        l_book.editor                := p_editor;
        l_book.keyword               := p_keyword;
        l_book.pub_time              := to_date(p_pub_time, 'dd-mm-yyyy');
        l_book.publishing_house      := p_publish_house;
        l_book.author                := p_author;
        l_book.series                := p_series_name;
        l_book.book_size             := l_book_size;
        l_book.format_design         := p_format_design;
        l_book.cover_design          := p_cover_design;
        l_book.pagination            := l_pagenum;
        l_book.remarks               := p_remarks;
        l_book.reader_group          := l_reader_group;
        l_book.suggestion            := p_suggestion;
        l_book.translator            := p_traslator;
        l_book.prod_size             := l_prod_size;
        l_book.color_num             := l_color_num;
        l_book.cover_typeset         := l_cover_typeset;
        l_book.assort_prod           := l_assort_prod;
        l_book.main_page             := l_main_page;
        l_book.main_typeset          := l_main_typeset;
        l_book.format_comp           := p_format_comp;
        l_book.item_code             := p_item_code;
        l_book.object_version_number := 1;
        l_book.created_by            := -1;
        l_book.creation_date         := SYSDATE;
        l_book.last_updated_by       := -1;
        l_book.last_update_date      := SYSDATE;
        INSERT INTO cms_book_t VALUES l_book;
        SELECT cms_doc_category_rel_s.NEXTVAL
          INTO l_doc_category_rel.rel_id
          FROM dual;
        l_doc_category_rel.doc_id := l_doc_id;
        BEGIN
          SELECT c.category_id
            INTO l_category_id
            FROM cms_category_t c, cms_lib_category_g_rel_t lcgr
           WHERE cms_common_pkg.get_category_top_node_id(c.category_id) =
                 lcgr.rel_id
             AND lcgr.lib_code = 'BOOK'
             AND c.category_name = p_category_name
             AND lcgr.comp_code = p_comp_code;
        EXCEPTION
          WHEN OTHERS THEN
            l_category_id := -1;
        END;
        l_doc_category_rel.category_id           := l_category_id;
        l_doc_category_rel.object_version_number := 1;
        l_doc_category_rel.created_by            := -1;
        l_doc_category_rel.creation_date         := SYSDATE;
        l_doc_category_rel.last_updated_by       := -1;
        l_doc_category_rel.last_update_date      := SYSDATE;
        l_doc_category_rel.category_group_type   := 'BY_CATEGORY';
        INSERT INTO cms_doc_category_rel_t VALUES l_doc_category_rel;
    END;
  
    DELETE FROM cms_chapter_t c WHERE c.doc_id = l_doc_id;
    UPDATE cms_doc_t d
       SET d.chapter_flag = NULL, d.last_update_date = SYSDATE
     WHERE chapter_flag = 'Y'
       AND d.doc_id = l_doc_id;
    COMMIT;
    x_return_code := 'S';
    x_return_msg  := l_doc_id;
  EXCEPTION
    WHEN OTHERS THEN
      ROLLBACK;
      x_return_code := 'E';
      x_return_msg  := SQLCODE || SQLERRM;
  END imp_book_metadata;

  PROCEDURE update_assort_prod_status(p_doc_id      IN VARCHAR2,
                                      x_return_code OUT VARCHAR2,
                                      x_return_msg  OUT VARCHAR2) IS
  BEGIN
    FOR c IN (SELECT t.ref_doc_id
                FROM cms_doc_ucm_rel_t t
               WHERE t.doc_id = to_number(p_doc_id)
                 AND t.ref_doc_id IS NOT NULL) LOOP
      UPDATE cms_doc_t d
         SET d.status = 'PUBED_' || d.lib_code
       WHERE d.doc_id = c.ref_doc_id;
    END LOOP;
    COMMIT;
    x_return_code := 'S';
  EXCEPTION
    WHEN OTHERS THEN
      ROLLBACK;
      x_return_code := 'E';
      x_return_msg  := SQLCODE || SQLERRM;
  END update_assort_prod_status;

  PROCEDURE imp_chapter(p_doc_id       IN NUMBER,
                        p_chapter_code IN VARCHAR2,
                        p_chapter_name IN VARCHAR2,
                        p_content      IN CLOB,
                        p_seq          IN NUMBER,
                        x_return_code  OUT VARCHAR2,
                        x_return_msg   OUT VARCHAR2) IS
    l_chapter cms_chapter_t%ROWTYPE;
  BEGIN
    UPDATE cms_doc_t d
       SET d.chapter_flag = 'Y', d.last_update_date = SYSDATE
     WHERE nvl(chapter_flag, 'N') = 'N'
       AND d.doc_id = p_doc_id;
    /*    SELECT COUNT(1) + 1
     INTO l_seq
     FROM cms_chapter_t c
    WHERE c.doc_id = p_doc_id;*/
    SELECT cms_chapter_s.NEXTVAL INTO l_chapter.chapter_id FROM dual;
    l_chapter.doc_id                := p_doc_id;
    l_chapter.chapter_name          := p_chapter_name;
    l_chapter.chapter_code          := p_chapter_code;
    l_chapter.content               := p_content;
    l_chapter.seq                   := p_seq;
    l_chapter.object_version_number := 1;
    l_chapter.created_by            := -1;
    l_chapter.creation_date         := SYSDATE;
    l_chapter.last_updated_by       := -1;
    l_chapter.last_update_date      := SYSDATE;
    INSERT INTO cms_chapter_t VALUES l_chapter;
    COMMIT;
    x_return_code := 'S';
  EXCEPTION
    WHEN OTHERS THEN
      ROLLBACK;
      x_return_code := 'E';
      x_return_msg  := SQLCODE || SQLERRM;
  END imp_chapter;

  PROCEDURE init_docbook_job(p_job_name    IN VARCHAR2,
                             p_comp_name   IN VARCHAR2,
                             p_isbn        IN VARCHAR2,
                             x_return_code OUT VARCHAR2,
                             x_return_msg  OUT VARCHAR2) IS
    l_docbook_job cms_docbook_job_t%ROWTYPE;
    l_comp_code   VARCHAR2(40);
  BEGIN
    SELECT c.comp_code
      INTO l_comp_code
      FROM cms_comp_v c
     WHERE c.comp_name = p_comp_name;
    BEGIN
      SELECT dj.*
        INTO l_docbook_job
        FROM cms_docbook_job_t dj
       WHERE dj.job_name = p_job_name
         AND dj.comp_code = l_comp_code
         AND dj.status = 'TO_IMPORT_DOCBOOK';
    EXCEPTION
      WHEN no_data_found THEN
        SELECT cms_docbook_job_s.NEXTVAL
          INTO l_docbook_job.job_id
          FROM dual;
        l_docbook_job.job_name              := p_job_name;
        l_docbook_job.comp_code             := l_comp_code;
        l_docbook_job.isbn                  := p_isbn;
        l_docbook_job.status                := 'TO_IMPORT_DOCBOOK';
        l_docbook_job.object_version_number := 1;
        l_docbook_job.created_by            := -1;
        l_docbook_job.creation_date         := SYSDATE;
        l_docbook_job.last_updated_by       := -1;
        l_docbook_job.last_update_date      := SYSDATE;
        INSERT INTO cms_docbook_job_t VALUES l_docbook_job;
        COMMIT;
    END;
    x_return_code := 'S';
  EXCEPTION
    WHEN OTHERS THEN
      ROLLBACK;
      x_return_code := 'E';
      x_return_msg  := SQLCODE || SQLERRM;
  END init_docbook_job;

  PROCEDURE imp_jb_info(p_doc_id              IN NUMBER,
                        p_brief_introduction  IN CLOB,
                        p_catalog             IN CLOB,
                        p_foreword            IN CLOB,
                        p_postscript          IN CLOB,
                        p_highlights          IN CLOB,
                        p_preface             IN CLOB,
                        p_glossary            IN CLOB,
                        p_references          IN CLOB,
                        p_author_introduction IN CLOB,
                        x_return_code         OUT VARCHAR2,
                        x_return_msg          OUT VARCHAR2) IS
    l_doc_id  NUMBER;
    l_jb_info cms_jb_info_t%ROWTYPE;
  BEGIN
    BEGIN
      SELECT ji.doc_id
        INTO l_doc_id
        FROM cms_jb_info_t ji
       WHERE ji.doc_id = p_doc_id;
      UPDATE cms_jb_info_t ji
         SET ji.brief_introduction  = nvl(p_brief_introduction,
                                          brief_introduction),
             ji.catalog             = nvl(p_catalog, catalog),
             ji.foreword            = nvl(p_foreword, foreword),
             ji.postscript          = nvl(p_postscript, postscript),
             ji.highlights          = nvl(p_highlights, highlights),
             ji.preface             = nvl(p_preface, preface),
             ji.glossary            = nvl(p_glossary, glossary),
             ji.references          = nvl(p_references, references),
             ji.author_introduction = nvl(p_author_introduction,
                                          author_introduction),
             ji.last_updated_by     = -1,
             ji.last_update_date    = SYSDATE
       WHERE ji.doc_id = l_doc_id;
    EXCEPTION
      WHEN no_data_found THEN
        SELECT cms_jb_info_s.NEXTVAL INTO l_jb_info.jb_info_id FROM dual;
        l_jb_info.doc_id                := p_doc_id;
        l_jb_info.brief_introduction    := p_brief_introduction;
        l_jb_info.catalog               := p_catalog;
        l_jb_info.foreword              := p_foreword;
        l_jb_info.postscript            := p_postscript;
        l_jb_info.highlights            := p_highlights;
        l_jb_info.preface               := p_preface;
        l_jb_info.glossary              := p_glossary;
        l_jb_info.references            := p_references;
        l_jb_info.author_introduction   := p_author_introduction;
        l_jb_info.object_version_number := 1;
        l_jb_info.created_by            := -1;
        l_jb_info.creation_date         := SYSDATE;
        l_jb_info.last_updated_by       := -1;
        l_jb_info.last_update_date      := SYSDATE;
        INSERT INTO cms_jb_info_t VALUES l_jb_info;
    END;
    COMMIT;
    x_return_code := 'S';
  EXCEPTION
    WHEN OTHERS THEN
      ROLLBACK;
      x_return_code := 'E';
      x_return_msg  := SQLCODE || SQLERRM;
  END imp_jb_info;

  PROCEDURE update_media_preview_info(p_doc_id      IN NUMBER,
                                      p_media_type  IN VARCHAR2,
                                      p_preview_url IN VARCHAR2,
                                      p_cover_url   IN VARCHAR2,
                                      p_duration    IN VARCHAR2,
                                      x_return_code OUT VARCHAR2,
                                      x_return_msg  OUT VARCHAR2) IS
  BEGIN
    IF p_media_type = 'VIDEO' THEN
      UPDATE cms_video_t v
         SET v.file_time        = p_duration,
             v.preview_url      = p_preview_url,
             v.last_update_date = SYSDATE
       WHERE v.doc_id = p_doc_id;
      UPDATE cms_doc_t d
         SET d.thumbnail_url = p_cover_url
       WHERE d.doc_id = p_doc_id;
    
    ELSIF p_media_type = 'AUDIO' THEN
      UPDATE cms_audio_t a
         SET a.file_time        = p_duration,
             a.preview_url      = p_preview_url,
             a.last_update_date = SYSDATE
       WHERE a.doc_id = p_doc_id;
    END IF;
    COMMIT;
    x_return_code := 'S';
  EXCEPTION
    WHEN OTHERS THEN
      ROLLBACK;
      x_return_code := 'E';
      x_return_msg  := SQLCODE || SQLERRM;
  END update_media_preview_info;

  FUNCTION get_category_top_node_id(p_category_id IN NUMBER) RETURN NUMBER IS
    l_top_node_id NUMBER;
  BEGIN
    FOR a_res IN (SELECT a.parent_node_id
                    FROM (SELECT c.category_id,
                                 c.parent_node_type,
                                 c.parent_node_id
                            FROM cms_category_t c) a
                   START WITH a.category_id = p_category_id
                  CONNECT BY PRIOR a.parent_node_id = a.category_id) LOOP
      l_top_node_id := a_res.parent_node_id;
    END LOOP;
    RETURN abs(l_top_node_id);
  END get_category_top_node_id;

  PROCEDURE get_remove_ftp_dirs(p_doc_ids     IN VARCHAR2,
                                x_return_code OUT VARCHAR2,
                                x_return_msg  OUT VARCHAR2) IS
    str                VARCHAR2(4000) := p_doc_ids;
    l_doc_id           NUMBER;
    l_ftp_dirs         VARCHAR2(4000);
    l_ftp_dir          VARCHAR2(480);
    l_user_name        VARCHAR2(80);
    l_lib_code         VARCHAR2(80);
    l_lib_name         VARCHAR2(200);
    l_comp_code        VARCHAR2(80);
    l_comp_name        VARCHAR2(200);
    l_processing_count NUMBER := 0;
    l_count            NUMBER := 0;
  BEGIN
    IF p_doc_ids IS NULL OR p_doc_ids = '' THEN
      x_return_code := 'S';
      x_return_msg  := NULL;
      RETURN;
    END IF;
    IF instr(p_doc_ids, '/') = 0 THEN
      /*UPDATE cms_gather_monitor_t gm
        SET gm.RESULT           = nvl(gm.RESULT, 'FAIL'),
            gm.thumbnail_result = nvl(gm.thumbnail_result, 'FAIL')
      WHERE gm.doc_id IN (SELECT *
                            FROM TABLE(CAST(cms_common_pkg.parse_string(substr(p_doc_ids,
                                                                               0,
                                                                               length(p_doc_ids) - 1)) AS
                                            cms_table_type)));*/
      WHILE instr(str, ',') > 0 LOOP
        l_doc_id := to_number(substr(str, 1, instr(str, ',') - 1));
        str      := substr(str, instr(str, ',') + 1);
        SELECT COUNT(*)
          INTO l_processing_count
          FROM cms_gather_monitor_t gmt
         WHERE gmt.doc_id = l_doc_id
           AND gmt.RESULT IS NULL;
        IF l_processing_count = 0 THEN
          BEGIN
            SELECT gm.doc_id
              INTO l_doc_id
              FROM cms_gather_monitor_t gm
             WHERE gm.doc_id = l_doc_id
               AND (gm.RESULT = 'FAIL' OR gm.thumbnail_result = 'FAIL');
          EXCEPTION
            WHEN no_data_found THEN
              SELECT c.comp_name || '/' || sll.lib_name || '/' ||
                     rci.unique_res_name
                INTO l_ftp_dir
                FROM cms_doc_t              d,
                     cms_res_common_info_v  rci,
                     cms_comp_v             c,
                     cms_second_level_lib_v sll
               WHERE d.doc_id = l_doc_id
                 AND d.comp_code = c.comp_code(+)
                 AND d.lib_code = sll.lib_code(+)
                 AND d.comp_code = sll.comp_code(+)
                 AND d.doc_id = rci.doc_id;
              l_ftp_dirs := l_ftp_dirs || l_ftp_dir || '###';
            WHEN OTHERS THEN
              NULL;
          END;
        END IF;
      END LOOP;
      x_return_msg := l_ftp_dirs;
    ELSE
      /*SELECT substr(p_doc_ids,
                  instr(p_doc_ids, '/', -1) + 1,
                  instr(p_doc_ids, '###') - instr(p_doc_ids, '/', -1) - 1)
      INTO l_user_name
      FROM dual;*/
      l_count := 0;
      FOR c IN (SELECT *
                  FROM TABLE(CAST(cms_common_pkg.parse_string_with_separator(p_doc_ids,
                                                                             '/') AS
                                  cms_table_type))) LOOP
        IF l_count = 0 THEN
          l_comp_code := c.column_value;
        ELSIF l_count = 1 THEN
          l_lib_code := c.column_value;
        ELSIF l_count = 2 THEN
          l_user_name := c.column_value;
        END IF;
        l_count := l_count + 1;
      END LOOP;
      SELECT COUNT(*)
        INTO l_processing_count
        FROM cms_gather_monitor_t gmt
       WHERE gmt.attribute1 = l_user_name
         AND gmt.RESULT IS NULL;
      /*UPDATE cms_gather_monitor_t gm
        SET gm.RESULT           = nvl(gm.RESULT, 'FAIL'),
            gm.thumbnail_result = nvl(gm.thumbnail_result, 'FAIL')
      WHERE gm.attribute1 = l_user_name;*/
      IF l_processing_count = 0 THEN
        BEGIN
          SELECT comp_name
            INTO l_comp_name
            FROM cms_comp_v comp
           WHERE comp.comp_code = l_comp_code;
        EXCEPTION
          WHEN OTHERS THEN
            l_comp_name := l_comp_code;
        END;
        BEGIN
          SELECT lib.lib_name
            INTO l_lib_name
            FROM cms_lib_v lib
           WHERE lib.lib_code = l_lib_code;
        EXCEPTION
          WHEN OTHERS THEN
            l_lib_name := l_lib_code;
        END;
        x_return_msg := l_comp_name || '/' || l_lib_name || '/' ||
                        l_user_name || '###';
      ELSE
        x_return_msg := NULL;
      END IF;
    END IF;
    COMMIT;
    x_return_code := 'S';
  EXCEPTION
    WHEN OTHERS THEN
      ROLLBACK;
      x_return_code := 'E';
      x_return_msg  := SQLCODE || SQLERRM;
  END get_remove_ftp_dirs;

  FUNCTION update_doc_status_when_sync(p_doc_id  IN VARCHAR2,
                                       p_user_id IN VARCHAR2) RETURN VARCHAR2 IS
    PRAGMA AUTONOMOUS_TRANSACTION;
    l_curr_status VARCHAR2(100);
    l_next_status VARCHAR2(100);
    l_lib_code    VARCHAR2(100);
    l_seq         NUMBER;
    x_return_code VARCHAR2(100);
    x_return_msg  VARCHAR2(1000);
  BEGIN
    SELECT t.status, t.lib_code
      INTO l_curr_status, l_lib_code
      FROM cms_doc_t t
     WHERE t.doc_id = p_doc_id;
    SELECT v.seq
      INTO l_seq
      FROM cms_doc_status_v v
     WHERE v.lib_code = l_lib_code
       AND v.status_code = l_curr_status;
    SELECT v.status_code
      INTO l_next_status
      FROM cms_doc_status_v v
     WHERE v.lib_code = l_lib_code
       AND v.seq = (l_seq + 10);
    UPDATE cms_doc_t t
       SET t.status = l_next_status
     WHERE t.doc_id = p_doc_id;
    cms_work_platform_pkg.insert_process_log(p_doc_id,
                                             '同步',
                                             p_user_id,
                                             l_next_status,
                                             l_lib_code,
                                             x_return_code,
                                             x_return_msg);
    COMMIT;
    RETURN 'S';
  EXCEPTION
    WHEN OTHERS THEN
      ROLLBACK;
      RETURN SQLCODE || SQLERRM;
  END update_doc_status_when_sync;

  --开始同步操作
  PROCEDURE start_sync(p_doc_id IN VARCHAR2) IS
    PRAGMA AUTONOMOUS_TRANSACTION;
    l_sync_flag VARCHAR2(20);
  BEGIN
    SELECT t.attribute1
      INTO l_sync_flag
      FROM cms_doc_t t
     WHERE t.doc_id = p_doc_id;
    IF l_sync_flag IS NULL OR l_sync_flag = 'FAIL' THEN
      UPDATE cms_doc_t t
         SET t.attribute1 = 'SYNCING', t.last_update_date = SYSDATE
       WHERE t.doc_id = p_doc_id;
      COMMIT;
    END IF;
  EXCEPTION
    WHEN OTHERS THEN
      ROLLBACK;
  END start_sync;

  PROCEDURE file_manage(p_doc_id IN NUMBER, p_sync_flag VARCHAR2) IS
    PRAGMA AUTONOMOUS_TRANSACTION;
  BEGIN
    UPDATE cms_doc_t t
       SET t.attribute1 = p_sync_flag
     WHERE t.doc_id = p_doc_id;
    COMMIT;
  EXCEPTION
    WHEN OTHERS THEN
      ROLLBACK;
  END file_manage;

  --获取同步操作临时标识
  FUNCTION get_sync_flag(p_doc_id IN NUMBER) RETURN VARCHAR2 IS
    l_sync_flag VARCHAR2(20);
  BEGIN
    SELECT t.attribute1
      INTO l_sync_flag
      FROM cms_doc_t t
     WHERE t.doc_id = p_doc_id;
    RETURN l_sync_flag;
  EXCEPTION
    WHEN OTHERS THEN
      RETURN NULL;
  END get_sync_flag;

  --结束同步操作
  PROCEDURE end_sync(p_doc_id IN VARCHAR2) IS
    PRAGMA AUTONOMOUS_TRANSACTION;
    l_fail_count       NUMBER := 0;
    l_processing_count NUMBER := 0; --进行中的数量
    l_update_result    VARCHAR2(100);
    l_attribute1       VARCHAR2(100);
  BEGIN
    BEGIN
      SELECT t.attribute1
        INTO l_attribute1
        FROM cms_doc_t t
       WHERE t.doc_id = p_doc_id;
    EXCEPTION
      WHEN OTHERS THEN
        l_attribute1 := NULL;
    END;
    SELECT COUNT(*)
      INTO l_processing_count
      FROM cms_gather_monitor_t t
     WHERE t.doc_id = p_doc_id
       AND t.RESULT IS NULL;
    --当没有进行中的文件时，代表同步已经结束
    IF l_attribute1 = 'SYNCING' AND l_processing_count = 0 THEN
      FOR c IN (SELECT t.RESULT, t.remarks
                  FROM cms_gather_monitor_t t
                 WHERE t.doc_id = p_doc_id) LOOP
        IF c.RESULT <> 'SUCCESS' AND c.remarks IS NOT NULL THEN
          l_fail_count := l_fail_count + 1;
          EXIT;
        END IF;
      END LOOP;
      IF l_fail_count = 0 THEN
        l_update_result := update_doc_status_when_sync(p_doc_id, -1);
        UPDATE cms_doc_t t
           SET t.attribute1 = 'SUCCESS'
         WHERE t.doc_id = p_doc_id;
      ELSE
        UPDATE cms_doc_t t
           SET t.attribute1 = 'FAIL'
         WHERE t.doc_id = p_doc_id;
      END IF;
      COMMIT;
    END IF;
  EXCEPTION
    WHEN OTHERS THEN
      ROLLBACK;
  END end_sync;

  --取消同步操作
  FUNCTION cancel_sync(p_doc_id IN VARCHAR2) RETURN VARCHAR2 IS
    PRAGMA AUTONOMOUS_TRANSACTION;
  BEGIN
    UPDATE cms_doc_t t SET t.attribute1 = NULL WHERE t.doc_id = p_doc_id;
    COMMIT;
    RETURN 'S';
  EXCEPTION
    WHEN OTHERS THEN
      ROLLBACK;
      RETURN SQLERRM;
  END cancel_sync;

  PROCEDURE get_sync_result(p_doc_ids     IN VARCHAR2,
                            p_user_id     IN VARCHAR2,
                            x_return_code OUT VARCHAR2,
                            x_return_msg  OUT VARCHAR2) IS
    str                VARCHAR2(4000) := p_doc_ids;
    l_doc_id           NUMBER;
    l_file_name        VARCHAR2(100);
    l_lib_code         VARCHAR2(100);
    l_user_name        VARCHAR2(80);
    l_res_display_name VARCHAR2(1000);
    l_fail_count       NUMBER := 0;
    l_processing_count NUMBER := 0;
    l_update_result    VARCHAR2(200);
  BEGIN
    --当不是素材的批量采集时
    IF instr(p_doc_ids, '/') = 0 THEN
      WHILE instr(str, ',') > 0 LOOP
        l_fail_count := 0;
        l_doc_id     := to_number(substr(str, 1, instr(str, ',') - 1));
        str          := substr(str, instr(str, ',') + 1);
        SELECT t.lib_code
          INTO l_lib_code
          FROM cms_doc_t t
         WHERE t.doc_id = l_doc_id;
        IF l_lib_code = 'BOOK' THEN
          BEGIN
            SELECT b.book_name || ',' || b.isbn || ',' || b.edition || ',' ||
                   b.impression
              INTO l_res_display_name
              FROM cms_book_t b
             WHERE b.doc_id = l_doc_id;
          EXCEPTION
            WHEN OTHERS THEN
              l_fail_count := l_fail_count + 1;
          END;
        ELSIF l_lib_code = 'ELEC_PROD' THEN
          BEGIN
            SELECT e.elec_prod_name || '_' || e.isbn
              INTO l_res_display_name
              FROM cms_elec_prod_t e
             WHERE e.doc_id = l_doc_id;
          EXCEPTION
            WHEN OTHERS THEN
              l_fail_count := l_fail_count + 1;
          END;
        ELSIF l_lib_code = 'PERIODICAL' THEN
          BEGIN
            SELECT v.periodical_category_name || '[' || ps.YEAR || '年第' ||
                   ps.period_num || '期]'
              INTO l_res_display_name
              FROM cms_periodical_t          p,
                   cms_doc_t                 d,
                   cms_doc_category_rel_t    dcr,
                   cms_period_setup_t        ps,
                   cms_periodical_category_v v
             WHERE p.doc_id = d.doc_id
               AND d.doc_id = dcr.doc_id
               AND p.period_id = ps.setup_id(+)
               AND p.periodical_category_id = v.periodical_category_id(+)
               AND p.doc_id = l_doc_id;
          EXCEPTION
            WHEN OTHERS THEN
              l_fail_count := l_fail_count + 1;
          END;
        ELSIF l_lib_code = 'NEWSPAPER' THEN
          BEGIN
            SELECT v.newspaper_category_name || '[' || ns.YEAR || '年第' ||
                   ns.news_num || '期]'
              INTO l_res_display_name
              FROM cms_newspaper_t          n,
                   cms_doc_t                d,
                   cms_doc_category_rel_t   dcr,
                   cms_news_setup_t         ns,
                   cms_newspaper_category_v v
             WHERE n.doc_id = d.doc_id
               AND d.doc_id = dcr.doc_id
               AND n.news_id = ns.setup_id(+)
               AND n.newspaper_category_id = v.newspaper_category_id(+)
               AND n.doc_id = l_doc_id;
          EXCEPTION
            WHEN OTHERS THEN
              l_fail_count := l_fail_count + 1;
          END;
        ELSIF l_lib_code = 'TEACHING_PLAN' THEN
          BEGIN
            SELECT tp.teaching_plan_name
              INTO l_res_display_name
              FROM cms_teaching_plan_t tp
             WHERE tp.doc_id = l_doc_id;
          EXCEPTION
            WHEN OTHERS THEN
              l_fail_count := l_fail_count + 1;
          END;
        ELSIF l_lib_code = 'COURSEWARE' THEN
          BEGIN
            SELECT ct.courseware_name
              INTO l_res_display_name
              FROM cms_courseware_t ct
             WHERE ct.doc_id = l_doc_id;
          EXCEPTION
            WHEN OTHERS THEN
              l_fail_count := l_fail_count + 1;
          END;
        ELSIF l_lib_code = 'ACTIVITY' THEN
          BEGIN
            SELECT a.activity_theme
              INTO l_res_display_name
              FROM cms_activity_t a
             WHERE a.doc_id = l_doc_id;
          EXCEPTION
            WHEN OTHERS THEN
              l_fail_count := l_fail_count + 1;
          END;
        END IF;
        --如果该资源中有文件同步失败，则提示有文件同步失败，否则提示资源同步成功
        SELECT COUNT(*)
          INTO l_processing_count
          FROM cms_gather_monitor_t t
         WHERE t.doc_id = l_doc_id
           AND t.RESULT IS NULL;
        IF l_processing_count = 0 THEN
          FOR c IN (SELECT t.RESULT, t.remarks
                      FROM cms_gather_monitor_t t
                     WHERE t.doc_id = l_doc_id) LOOP
            IF c.RESULT <> 'SUCCESS' AND c.remarks IS NOT NULL THEN
              l_fail_count := l_fail_count + 1;
              EXIT;
            END IF;
          END LOOP;
          IF l_fail_count = 0 THEN
            x_return_code := 'S';
            x_return_msg  := x_return_msg || l_res_display_name || '同步成功.' ||
                             chr(10);
          ELSE
            x_return_code := 'E';
            x_return_msg  := x_return_msg || l_res_display_name ||
                             '存在同步失败的文件，请查看同步记录.' || chr(10);
          END IF;
        ELSE
          x_return_code := NULL;
          x_return_msg  := NULL;
        END IF;
        IF l_lib_code NOT IN ('BOOK', 'ELEC_PROD', 'PERIODICAL', 'NEWSPAPER',
            'TEACHING_PLAN', 'COURSEWARE', 'ACTIVITY') THEN
          x_return_code := 'S';
          x_return_msg  := '同步成功';
        END IF;
      END LOOP;
    ELSE
      --批量采集素材
      x_return_code := 'S';
      x_return_msg  := '批量同步成功';
    END IF;
  EXCEPTION
    WHEN OTHERS THEN
      x_return_code := 'E';
      x_return_msg  := '存在同步失败的记录，详细原因请查看同步日志' || chr(10);
  END get_sync_result;

  PROCEDURE clear_sync_log(p_user_name   IN VARCHAR2,
                           p_comp_code   IN VARCHAR2,
                           p_lib_code    IN VARCHAR2,
                           x_return_code OUT VARCHAR2,
                           x_return_msg  OUT VARCHAR2) IS
    PRAGMA AUTONOMOUS_TRANSACTION;
  BEGIN
    UPDATE cms_gather_monitor_t t
       SET t.attribute1 = NULL
     WHERE t.attribute1 = p_user_name
       AND t.attribute2 = p_comp_code
       AND t.attribute3 = p_lib_code;
    COMMIT;
    x_return_code := 'S';
  EXCEPTION
    WHEN OTHERS THEN
      ROLLBACK;
      x_return_code := 'E';
      x_return_msg  := SQLCODE || SQLERRM;
  END clear_sync_log;

  FUNCTION delete_gather_monitor_record(p_doc_id IN NUMBER) RETURN VARCHAR2 IS
    PRAGMA AUTONOMOUS_TRANSACTION;
  BEGIN
    DELETE FROM cms_gather_monitor_t t
     WHERE t.doc_id = p_doc_id
       AND (t.RESULT = 'FAIL' OR t.thumbnail_result = 'FAIL');
    COMMIT;
    RETURN 'S';
  EXCEPTION
    WHEN OTHERS THEN
      ROLLBACK;
      RETURN SQLERRM;
  END delete_gather_monitor_record;

  FUNCTION get_sync_batch_num(p_doc_id IN NUMBER) RETURN VARCHAR2 IS
    l_batch_num VARCHAR2(100);
  BEGIN
    SELECT to_char((nvl(MAX(to_number(t.attribute5)), 0) + 1))
      INTO l_batch_num
      FROM cms_gather_monitor_t t
     WHERE t.doc_id = p_doc_id;
    RETURN l_batch_num;
  EXCEPTION
    WHEN OTHERS THEN
      RETURN '1';
  END get_sync_batch_num;

  PROCEDURE sync_log_processor(p_user_id          IN VARCHAR2,
                               p_user_name        IN VARCHAR2,
                               p_comp_code        IN VARCHAR2,
                               p_lib_code         IN VARCHAR2,
                               p_doc_id           IN NUMBER,
                               p_file_name        IN VARCHAR2,
                               p_file_path        IN VARCHAR2,
                               p_operation        IN VARCHAR2,
                               p_result           IN VARCHAR,
                               p_thumbnail_result IN VARCHAR2,
                               p_remarks          IN VARCHAR2,
                               batch_num          IN VARCHAR2,
                               x_return_code      OUT VARCHAR2,
                               x_return_msg       OUT VARCHAR2) IS
    PRAGMA AUTONOMOUS_TRANSACTION;
    l_monitor_row cms_gather_monitor_t%ROWTYPE;
    l_count       NUMBER;
  BEGIN
    SELECT COUNT(*)
      INTO l_count
      FROM cms_gather_monitor_t t
     WHERE t.doc_id = p_doc_id
       AND t.file_name = p_file_name;
    IF l_count > 0 THEN
      UPDATE cms_gather_monitor_t t
         SET t.file_path        = p_file_path,
             t.RESULT           = p_result,
             t.thumbnail_result = p_thumbnail_result,
             t.remarks          = p_remarks,
             t.created_by       = p_user_id,
             t.creation_date    = SYSDATE,
             t.last_updated_by  = p_user_id,
             t.last_update_date = SYSDATE,
             t.attribute1       = p_user_name,
             t.attribute2       = p_comp_code,
             t.attribute3       = p_lib_code,
             t.attribute4       = p_operation,
             t.attribute5       = batch_num
       WHERE t.doc_id = p_doc_id
         AND t.file_name = p_file_name;
    ELSE
      SELECT cms_gather_monitor_s.NEXTVAL
        INTO l_monitor_row.monitor_id
        FROM dual;
      l_monitor_row.doc_id                := p_doc_id;
      l_monitor_row.file_name             := p_file_name;
      l_monitor_row.file_path             := p_file_path;
      l_monitor_row.RESULT                := p_result;
      l_monitor_row.thumbnail_result      := p_thumbnail_result;
      l_monitor_row.remarks               := p_remarks;
      l_monitor_row.created_by            := p_user_id;
      l_monitor_row.creation_date         := SYSDATE;
      l_monitor_row.last_updated_by       := p_user_id;
      l_monitor_row.last_update_date      := SYSDATE;
      l_monitor_row.object_version_number := 1;
      l_monitor_row.attribute1            := p_user_name;
      l_monitor_row.attribute2            := p_comp_code;
      l_monitor_row.attribute3            := p_lib_code;
      l_monitor_row.attribute4            := p_operation;
      l_monitor_row.attribute5            := batch_num;
      INSERT INTO cms_gather_monitor_t VALUES l_monitor_row;
    END IF;
    x_return_code := 'S';
    COMMIT;
  EXCEPTION
    WHEN OTHERS THEN
      ROLLBACK;
      x_return_code := 'E';
      x_return_msg  := SQLCODE || SQLERRM;
  END sync_log_processor;

  FUNCTION get_approve_user_count(p_role_type IN VARCHAR2,
                                  p_status    IN VARCHAR2,
                                  p_comp_code IN VARCHAR2) RETURN NUMBER IS
    l_user_count NUMBER;
  BEGIN
    SELECT COUNT(*)
      INTO l_user_count
      FROM cms_role_t role, cms_role_user_rel_t rel, cms_user_t u
     WHERE role.role_id = rel.role_id
       AND rel.user_id = u.user_id
       AND role.attribute1 = p_status
       AND role.enable_flag = 'Y'
       AND role.comp_code = p_comp_code
       AND role.role_type = p_role_type;
    RETURN l_user_count;
  EXCEPTION
    WHEN OTHERS THEN
      RETURN 0;
  END get_approve_user_count;

  PROCEDURE update_apply_status(p_apply_id     IN NUMBER,
                                p_apply_type   IN VARCHAR2,
                                p_apply_status IN VARCHAR2) IS
  BEGIN
    IF p_apply_type = 'EXP' THEN
      UPDATE cms_res_exp_apply_h_t reah
         SET reah.status = p_apply_status, reah.last_update_date = SYSDATE
       WHERE reah.apply_id = p_apply_id;
    ELSE
      UPDATE cms_res_download_apply_h_t rdah
         SET rdah.status = p_apply_status, rdah.last_update_date = SYSDATE
       WHERE rdah.apply_id = p_apply_id;
    END IF;
  END update_apply_status;

  PROCEDURE res_exp_download_apply_submit(p_apply_id    IN NUMBER,
                                          p_proposer_id IN NUMBER,
                                          p_apply_type  IN VARCHAR2,
                                          x_return_code OUT VARCHAR2,
                                          x_return_msg  OUT VARCHAR2) IS
    l_counter                    NUMBER;
    l_role_type                  VARCHAR2(40);
    l_first_trial_user_count     NUMBER;
    l_trial_user_count           NUMBER;
    l_final_judgement_user_count NUMBER;
    l_comp_code                  VARCHAR2(100);
  BEGIN
    x_return_code := 'S';
    IF p_apply_type = 'EXP' THEN
      l_role_type := 'WORK_PLATFORM';
      SELECT daht.comp_code
        INTO l_comp_code
        FROM cms_res_exp_apply_h_t daht
       WHERE daht.apply_id = p_apply_id;
      UPDATE cms_res_exp_apply_h_t daht
         SET daht.attribute1 = NULL
       WHERE daht.apply_id = p_apply_id;
    ELSIF p_apply_type = 'DOWNLOAD' THEN
      SELECT daht.comp_code
        INTO l_comp_code
        FROM cms_res_download_apply_h_t daht
       WHERE daht.apply_id = p_apply_id;
      UPDATE cms_res_download_apply_h_t rdah
         SET rdah.status = 'TO_FIRST_TRIAL_APPLY', rdah.attribute1 = NULL
       WHERE rdah.apply_id = p_apply_id;
      l_role_type := 'PUB_PLATFORM';
    END IF;
    --初审下维护的人员数量
    l_first_trial_user_count := get_approve_user_count(l_role_type,
                                                       'FIRST_TRIAL',
                                                       l_comp_code);
    --复审下维护的人员数量
    l_trial_user_count := get_approve_user_count(l_role_type,
                                                 'TRIAL',
                                                 l_comp_code);
    --终审下维护的人员数量
    l_final_judgement_user_count := get_approve_user_count(l_role_type,
                                                           'FINAL_JUDGMENT',
                                                           l_comp_code);
    --初审、复审、终审都没有维护人
    IF l_first_trial_user_count = 0 AND l_trial_user_count = 0 AND
       l_final_judgement_user_count = 0 THEN
      update_apply_status(p_apply_id, p_apply_type, 'APPROVED_APPLY');
    ELSIF l_first_trial_user_count = 0 AND l_trial_user_count = 0 AND
          l_final_judgement_user_count > 0 THEN
      --初审和复审没人，直接进入终审
      update_apply_status(p_apply_id,
                          p_apply_type,
                          'TO_FINAL_JUDGMENT_APPLY');
    ELSIF l_first_trial_user_count = 0 AND l_trial_user_count > 0 THEN
      --初审没人、复审有人、直接进入复审
      update_apply_status(p_apply_id, p_apply_type, 'TO_TRIAL_APPLY');
    ELSIF l_first_trial_user_count > 0 THEN
      --初审有人
      SELECT COUNT(1)
        INTO l_counter
        FROM cms_role_t r
       WHERE r.attribute1 = 'FIRST_TRIAL'
         AND r.role_type = l_role_type
         AND r.comp_code = l_comp_code
         AND r.enable_flag = 'Y';
      IF l_counter = 0 THEN
        update_apply_status(p_apply_id, p_apply_type, 'APPROVED_APPLY');
      ELSE
        SELECT COUNT(1)
          INTO l_counter
          FROM cms_role_t r, cms_role_user_rel_t rur
         WHERE r.attribute1 = 'FIRST_TRIAL'
           AND r.role_type = l_role_type
           AND r.enable_flag = 'Y'
           AND r.comp_code = l_comp_code
           AND r.role_id = rur.role_id
           AND rur.user_id = p_proposer_id;
        IF l_counter > 0 THEN
          res_exp_download_approval(p_apply_id        => p_apply_id,
                                    p_apply_type      => p_apply_type,
                                    p_approver_id     => p_proposer_id,
                                    p_result          => 'APPROVE',
                                    p_rollback_reason => NULL,
                                    x_return_code     => x_return_code,
                                    x_return_msg      => x_return_msg);
        END IF;
      END IF;
    END IF;
    --COMMIT;
  EXCEPTION
    WHEN OTHERS THEN
      x_return_code := 'E';
      x_return_msg  := SQLCODE || SQLERRM;
  END res_exp_download_apply_submit;

  PROCEDURE res_exp_download_approval(p_apply_id        IN NUMBER,
                                      p_apply_type      IN VARCHAR2,
                                      p_approver_id     IN NUMBER,
                                      p_result          IN VARCHAR2,
                                      p_rollback_reason IN VARCHAR2,
                                      x_return_code     OUT VARCHAR2,
                                      x_return_msg      OUT VARCHAR2) AS
    l_next_status    VARCHAR2(40);
    l_next_role_code VARCHAR2(40);
    l_curr_status    VARCHAR2(40);
    l_role_type      VARCHAR2(40);
    l_action         VARCHAR2(80);
    l_seq            NUMBER;
    l_counter        NUMBER;
    l_user_count     NUMBER;
    l_comp_code      VARCHAR2(100);
  BEGIN
    --导出
    IF p_apply_type = 'EXP' THEN
      SELECT reah.status, resr.action, reah.comp_code
        INTO l_curr_status, l_action, l_comp_code
        FROM cms_res_exp_apply_h_t reah, cms_res_exp_status_role_v resr
       WHERE reah.apply_id = p_apply_id
         AND reah.status = resr.status;
      l_role_type := 'WORK_PLATFORM';
    ELSIF p_apply_type = 'DOWNLOAD' THEN
      --下载
      SELECT rdah.status, resr.action, rdah.comp_code
        INTO l_curr_status, l_action, l_comp_code
        FROM cms_res_download_apply_h_t rdah,
             cms_res_exp_status_role_v  resr
       WHERE rdah.apply_id = p_apply_id
         AND rdah.status = resr.status;
      l_role_type := 'PUB_PLATFORM';
    END IF;
    --如果是拒绝操作
    IF p_result = 'REJECT' THEN
      l_next_status := 'REJECTED_APPLY';
      --插入审批历史记录
      insert_approve_history(p_apply_id    => p_apply_id,
                             p_apply_type  => p_apply_type,
                             p_approver_id => p_approver_id,
                             p_action      => l_action,
                             p_result      => p_result);
      update_apply_status(p_apply_id, p_apply_type, l_next_status);
      IF p_apply_type = 'EXP' THEN
        UPDATE cms_res_exp_apply_h_t reah
           SET reah.attribute1 = p_rollback_reason
         WHERE reah.apply_id = p_apply_id;
      ELSE
        UPDATE cms_res_download_apply_h_t rdah
           SET rdah.attribute1 = p_rollback_reason
         WHERE rdah.apply_id = p_apply_id;
      END IF;
    ELSE
      BEGIN
        SELECT t.seq
          INTO l_seq
          FROM cms_res_exp_status_role_v t
         WHERE t.status = l_curr_status;
        SELECT resr.status, resr.role_code
          INTO l_next_status, l_next_role_code
          FROM cms_res_exp_status_role_v resr, cms_role_t r
         WHERE resr.role_code = r.attribute1
           AND r.enable_flag = 'Y'
           AND r.role_type = l_role_type
           AND r.comp_code = l_comp_code
           AND resr.seq = l_seq + 10;
      EXCEPTION
        WHEN no_data_found THEN
          l_next_status := 'APPROVED_APPLY';
      END;
      --如果下一状态下有人，则进入下一状态，如果没人，则下一状态自动审批过去
      update_apply_status(p_apply_id, p_apply_type, l_next_status);
      --判断某个角色下是否有人，如果这个角色下没人，则自动审批过去
      l_user_count := get_approve_user_count(l_role_type,
                                             l_next_role_code,
                                             l_comp_code);
      --插入审批历史记录
      insert_approve_history(p_apply_id    => p_apply_id,
                             p_apply_type  => p_apply_type,
                             p_approver_id => p_approver_id,
                             p_action      => l_action,
                             p_result      => p_result);
      IF l_user_count > 0 THEN
        --如果下个审批人中包含当前审批人,则自动审批过去.
        SELECT COUNT(1)
          INTO l_counter
          FROM cms_role_t r, cms_role_user_rel_t rur
         WHERE r.attribute1 = l_next_role_code
           AND r.role_type = l_role_type
           AND r.enable_flag = 'Y'
           AND r.role_id = rur.role_id
           AND r.comp_code = l_comp_code
           AND rur.user_id = p_approver_id;
        IF l_counter > 0 THEN
          res_exp_download_approval(p_apply_id        => p_apply_id,
                                    p_apply_type      => p_apply_type,
                                    p_approver_id     => p_approver_id,
                                    p_result          => 'APPROVE',
                                    p_rollback_reason => NULL,
                                    x_return_code     => x_return_code,
                                    x_return_msg      => x_return_msg);
        END IF;
      ELSIF l_user_count = 0 AND l_next_status <> 'APPROVED_APPLY' THEN
        res_exp_download_approval(p_apply_id        => p_apply_id,
                                  p_apply_type      => p_apply_type,
                                  p_approver_id     => p_approver_id,
                                  p_result          => 'APPROVE',
                                  p_rollback_reason => NULL,
                                  x_return_code     => x_return_code,
                                  x_return_msg      => x_return_msg);
      END IF;
    END IF;
    --COMMIT;
    x_return_code := 'S';
  EXCEPTION
    WHEN OTHERS THEN
      --ROLLBACK;
      x_return_code := 'E';
      x_return_msg  := SQLCODE || SQLERRM;
  END res_exp_download_approval;

  PROCEDURE insert_approve_history(p_apply_id    IN NUMBER,
                                   p_apply_type  IN VARCHAR2,
                                   p_approver_id IN NUMBER,
                                   p_action      IN VARCHAR2,
                                   p_result      IN VARCHAR2) IS
    l_res_exp_approval_his cms_res_exp_approval_his_t%ROWTYPE;
  BEGIN
    --插入审批历史记录
    SELECT cms_res_exp_approval_his_s.NEXTVAL
      INTO l_res_exp_approval_his.his_id
      FROM dual;
    l_res_exp_approval_his.apply_id              := p_apply_id;
    l_res_exp_approval_his.apply_type            := p_apply_type;
    l_res_exp_approval_his.approver_id           := p_approver_id;
    l_res_exp_approval_his.action                := p_action;
    l_res_exp_approval_his.action_time           := SYSDATE;
    l_res_exp_approval_his.RESULT                := p_result;
    l_res_exp_approval_his.remarks               := NULL;
    l_res_exp_approval_his.object_version_number := 1;
    l_res_exp_approval_his.created_by            := p_approver_id;
    l_res_exp_approval_his.creation_date         := SYSDATE;
    l_res_exp_approval_his.last_updated_by       := p_approver_id;
    l_res_exp_approval_his.last_update_date      := SYSDATE;
    INSERT INTO cms_res_exp_approval_his_t VALUES l_res_exp_approval_his;
  END insert_approve_history;

  FUNCTION get_ftp_path_last_section(p_ftp_path IN VARCHAR2) RETURN VARCHAR2 IS
    l_result   VARCHAR2(100);
    l_ftp_path VARCHAR2(500) := p_ftp_path;
    l_num_str  VARCHAR2(100);
    l_number   NUMBER;
  BEGIN
    IF instr(l_ftp_path, '/', length(l_ftp_path)) > 0 THEN
      l_ftp_path := substr(l_ftp_path, 1, length(l_ftp_path) - 1);
    END IF;
    FOR c IN (SELECT *
                FROM TABLE(CAST(cms_common_pkg.parse_string_with_separator(l_ftp_path,
                                                                           '/') AS
                                cms_table_type))) LOOP
      IF instr(c.column_value, '_') > 0 THEN
        l_num_str := substr(c.column_value, instr(c.column_value, '_') + 1);
        BEGIN
          SELECT to_number(l_num_str) INTO l_number FROM dual;
          IF l_num_str IS NOT NULL THEN
            l_result := c.column_value;
          END IF;
        EXCEPTION
          WHEN OTHERS THEN
            NULL;
        END;
      END IF;
    END LOOP;
    RETURN l_result;
  EXCEPTION
    WHEN OTHERS THEN
      RETURN NULL;
  END get_ftp_path_last_section;

  --论著条目入库
  PROCEDURE works_entry_processor(p_doc_id           IN VARCHAR2,
                                  p_lib_code         IN VARCHAR2,
                                  p_task_id          IN VARCHAR2,
                                  p_user_id          IN VARCHAR2,
                                  p_comp_code        IN VARCHAR2,
                                  p_file_name        IN VARCHAR2,
                                  p_ucm_did          IN VARCHAR2,
                                  p_ucm_doc_name     IN VARCHAR2,
                                  p_title            IN VARCHAR2,
                                  p_subtitle         IN VARCHAR2,
                                  p_english_title    IN VARCHAR2,
                                  p_english_subtitle IN VARCHAR2,
                                  p_source           IN VARCHAR2,
                                  p_author           IN VARCHAR2,
                                  p_traslator        IN VARCHAR2,
                                  p_lang             IN VARCHAR2,
                                  p_word_count       IN VARCHAR2,
                                  p_writing_time     IN VARCHAR2,
                                  p_keyword          IN VARCHAR2,
                                  p_category_name    IN VARCHAR2,
                                  p_abstract_desc    IN VARCHAR2,
                                  p_result_file_path IN VARCHAR2,
                                  p_entry_content    IN CLOB,
                                  x_return_code      OUT VARCHAR2,
                                  x_return_msg       OUT VARCHAR2) IS
    l_doc_row              cms_doc_t%ROWTYPE;
    l_doc_category_rel_row cms_doc_category_rel_t%ROWTYPE;
    l_cms_doc_ucm_rel      cms_doc_ucm_rel_t%ROWTYPE;
    l_works_entry_row      cms_works_entry_t%ROWTYPE;
    l_category_id          NUMBER;
    l_lang_code            VARCHAR2(50);
  BEGIN
    --获取分类ID
    BEGIN
      SELECT ct.category_id
        INTO l_category_id
        FROM cms_category_t ct, cms_lib_category_g_rel_t lcgr
       WHERE cms_common_pkg.get_category_top_node_id(ct.category_id) =
             lcgr.rel_id
         AND ct.category_name = p_category_name
         AND lcgr.lib_code = p_lib_code
         AND lcgr.comp_code = p_comp_code;
    EXCEPTION
      WHEN OTHERS THEN
        l_category_id := -1;
    END;
  
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
  
    --p_doc_id为空代表条目属于新入库，否则属于条目编辑
    IF p_doc_id IS NULL THEN
      --往cms_doc_t表里插入数据
      SELECT cms_doc_s.NEXTVAL INTO l_doc_row.doc_id FROM dual;
      l_doc_row.lib_code              := p_lib_code;
      l_doc_row.lib_type_code         := 'ENTRY_LIB';
      l_doc_row.comp_code             := p_comp_code;
      l_doc_row.object_version_number := 1;
      l_doc_row.created_by            := p_user_id;
      l_doc_row.creation_date         := SYSDATE;
      l_doc_row.last_updated_by       := p_user_id;
      l_doc_row.last_update_date      := SYSDATE;
      l_doc_row.status                := 'TO_INDEX_' || p_lib_code;
      INSERT INTO cms_doc_t VALUES l_doc_row;
      --插入分类数据
      SELECT cms_doc_category_rel_s.NEXTVAL
        INTO l_doc_category_rel_row.rel_id
        FROM dual;
      l_doc_category_rel_row.doc_id                := l_doc_row.doc_id;
      l_doc_category_rel_row.category_id           := l_category_id;
      l_doc_category_rel_row.object_version_number := 1;
      l_doc_category_rel_row.created_by            := p_user_id;
      l_doc_category_rel_row.creation_date         := SYSDATE;
      l_doc_category_rel_row.last_updated_by       := p_user_id;
      l_doc_category_rel_row.last_update_date      := SYSDATE;
      l_doc_category_rel_row.category_group_type   := 'BY_CATEGORY';
      INSERT INTO cms_doc_category_rel_t VALUES l_doc_category_rel_row;
    
      --插入UCM关联数据
      IF p_ucm_did IS NOT NULL THEN
        SELECT cms_doc_ucm_rel_s.NEXTVAL
          INTO l_cms_doc_ucm_rel.rel_id
          FROM dual;
        l_cms_doc_ucm_rel.doc_id                := l_doc_row.doc_id;
        l_cms_doc_ucm_rel.ucm_did               := p_ucm_did;
        l_cms_doc_ucm_rel.ucm_docname           := upper(p_ucm_doc_name);
        l_cms_doc_ucm_rel.file_name             := p_file_name;
        l_cms_doc_ucm_rel.ftp_path              := NULL;
        l_cms_doc_ucm_rel.ftp_upload_date       := NULL;
        l_cms_doc_ucm_rel.attribute3            := p_result_file_path;
        l_cms_doc_ucm_rel.object_version_number := 1;
        l_cms_doc_ucm_rel.created_by            := p_user_id;
        l_cms_doc_ucm_rel.creation_date         := SYSDATE;
        l_cms_doc_ucm_rel.last_updated_by       := p_user_id;
        l_cms_doc_ucm_rel.last_update_date      := SYSDATE;
        INSERT INTO cms_doc_ucm_rel_t VALUES l_cms_doc_ucm_rel;
      END IF;
      --插入条目数据
      SELECT cms_works_entry_s.NEXTVAL
        INTO l_works_entry_row.entry_id
        FROM dual;
      l_works_entry_row.doc_id                := l_doc_row.doc_id;
      l_works_entry_row.title                 := p_title;
      l_works_entry_row.subtitle              := p_subtitle;
      l_works_entry_row.english_title         := p_english_title;
      l_works_entry_row.english_subtitle      := p_english_subtitle;
      l_works_entry_row.SOURCE                := p_source;
      l_works_entry_row.author                := p_author;
      l_works_entry_row.translator            := p_traslator;
      l_works_entry_row.lang                  := l_lang_code;
      l_works_entry_row.word_count            := to_number(p_word_count);
      l_works_entry_row.writing_time          := to_date(p_writing_time,
                                                         'yyyy-MM');
      l_works_entry_row.keyword               := p_keyword;
      l_works_entry_row.abstarct_desc         := p_abstract_desc;
      l_works_entry_row.entry_content         := p_entry_content;
      l_works_entry_row.store_time            := SYSDATE;
      l_works_entry_row.object_version_number := 1;
      l_works_entry_row.created_by            := p_user_id;
      l_works_entry_row.creation_date         := SYSDATE;
      l_works_entry_row.last_updated_by       := p_user_id;
      l_works_entry_row.last_update_date      := SYSDATE;
      INSERT INTO cms_works_entry_t VALUES l_works_entry_row;
    ELSE
      --条目编辑
      IF p_category_name IS NOT NULL THEN
        UPDATE cms_doc_category_rel_t rt
           SET rt.category_id = l_category_id
         WHERE rt.doc_id = to_number(p_doc_id);
      END IF;
      UPDATE cms_doc_ucm_rel_t rt
         SET rt.ucm_did          = p_ucm_did,
             rt.ucm_docname      = p_ucm_doc_name,
             rt.file_name        = p_file_name,
             rt.last_updated_by  = p_user_id,
             rt.last_update_date = SYSDATE
       WHERE rt.doc_id = to_number(p_doc_id);
      UPDATE cms_works_entry_t et
         SET et.title            = p_title,
             et.subtitle         = p_subtitle,
             et.english_title    = p_english_title,
             et.english_subtitle = p_english_subtitle,
             et.SOURCE           = p_source,
             et.author           = p_author,
             et.translator       = p_traslator,
             et.lang             = l_lang_code,
             et.word_count       = to_number(p_word_count),
             et.writing_time     = to_date(p_writing_time, 'yyyy-MM'),
             et.keyword          = p_keyword,
             et.store_time       = SYSDATE,
             et.abstarct_desc    = p_abstract_desc,
             et.entry_content    = p_entry_content,
             et.last_updated_by  = p_user_id,
             et.last_update_date = SYSDATE
       WHERE et.doc_id = to_number(p_doc_id);
    END IF;
    x_return_code := 'S';
  EXCEPTION
    WHEN OTHERS THEN
      UPDATE cms_doc_t t
         SET t.status           = 'FAIL_OFFLINE_ENTRY_PROCESS',
             t.last_update_date = SYSDATE
       WHERE t.doc_id = (SELECT doc_id
                           FROM cms_task_monitor_t
                          WHERE task_id = to_number(p_task_id));
      COMMIT;
      x_return_code := 'E';
      x_return_msg  := SQLCODE || SQLERRM;
  END works_entry_processor;

  PROCEDURE encyclopedias_entry_processor(p_doc_id           IN VARCHAR2,
                                          p_lib_code         IN VARCHAR2,
                                          p_task_id          IN VARCHAR2,
                                          p_user_id          IN VARCHAR2,
                                          p_comp_code        IN VARCHAR2,
                                          p_file_name        IN VARCHAR2,
                                          p_ucm_did          IN VARCHAR2,
                                          p_ucm_doc_name     IN VARCHAR2,
                                          p_entry_header     IN VARCHAR2,
                                          p_source           IN VARCHAR2,
                                          p_knowledge_point  IN VARCHAR2,
                                          p_keyword          IN VARCHAR2,
                                          p_category_name    IN VARCHAR2,
                                          p_result_file_path IN VARCHAR2,
                                          p_entry_content    IN CLOB,
                                          x_return_code      OUT VARCHAR2,
                                          x_return_msg       OUT VARCHAR2) IS
    l_doc_row                 cms_doc_t%ROWTYPE;
    l_doc_category_rel_row    cms_doc_category_rel_t%ROWTYPE;
    l_cms_doc_ucm_rel         cms_doc_ucm_rel_t%ROWTYPE;
    l_encyclopedias_entry_row cms_encyclopedias_entry_t%ROWTYPE;
    l_category_id             NUMBER;
  
  BEGIN
    --获取分类ID
    BEGIN
      SELECT ct.category_id
        INTO l_category_id
        FROM cms_category_t ct, cms_lib_category_g_rel_t lcgr
       WHERE cms_common_pkg.get_category_top_node_id(ct.category_id) =
             lcgr.rel_id
         AND ct.category_name = p_category_name
         AND lcgr.lib_code = p_lib_code
         AND lcgr.comp_code = p_comp_code;
    EXCEPTION
      WHEN OTHERS THEN
        l_category_id := -1;
    END;
  
    --p_doc_id为空代表条目属于新入库，否则属于条目编辑
    IF p_doc_id IS NULL THEN
      --往cms_doc_t表里插入数据
      SELECT cms_doc_s.NEXTVAL INTO l_doc_row.doc_id FROM dual;
      l_doc_row.lib_code              := p_lib_code;
      l_doc_row.lib_type_code         := 'ENTRY_LIB';
      l_doc_row.comp_code             := p_comp_code;
      l_doc_row.object_version_number := 1;
      l_doc_row.created_by            := p_user_id;
      l_doc_row.creation_date         := SYSDATE;
      l_doc_row.last_updated_by       := p_user_id;
      l_doc_row.last_update_date      := SYSDATE;
      l_doc_row.status                := 'TO_INDEX_' || p_lib_code;
      INSERT INTO cms_doc_t VALUES l_doc_row;
      --插入分类数据
      SELECT cms_doc_category_rel_s.NEXTVAL
        INTO l_doc_category_rel_row.rel_id
        FROM dual;
      l_doc_category_rel_row.doc_id                := l_doc_row.doc_id;
      l_doc_category_rel_row.category_id           := l_category_id;
      l_doc_category_rel_row.object_version_number := 1;
      l_doc_category_rel_row.created_by            := p_user_id;
      l_doc_category_rel_row.creation_date         := SYSDATE;
      l_doc_category_rel_row.last_updated_by       := p_user_id;
      l_doc_category_rel_row.last_update_date      := SYSDATE;
      l_doc_category_rel_row.category_group_type   := 'BY_CATEGORY';
      INSERT INTO cms_doc_category_rel_t VALUES l_doc_category_rel_row;
    
      --插入UCM关联数据
      IF p_ucm_did IS NOT NULL THEN
        SELECT cms_doc_ucm_rel_s.NEXTVAL
          INTO l_cms_doc_ucm_rel.rel_id
          FROM dual;
        l_cms_doc_ucm_rel.doc_id                := l_doc_row.doc_id;
        l_cms_doc_ucm_rel.ucm_did               := p_ucm_did;
        l_cms_doc_ucm_rel.ucm_docname           := upper(p_ucm_doc_name);
        l_cms_doc_ucm_rel.file_name             := p_file_name;
        l_cms_doc_ucm_rel.ftp_path              := NULL;
        l_cms_doc_ucm_rel.ftp_upload_date       := NULL;
        l_cms_doc_ucm_rel.attribute3            := p_result_file_path;
        l_cms_doc_ucm_rel.object_version_number := 1;
        l_cms_doc_ucm_rel.created_by            := p_user_id;
        l_cms_doc_ucm_rel.creation_date         := SYSDATE;
        l_cms_doc_ucm_rel.last_updated_by       := p_user_id;
        l_cms_doc_ucm_rel.last_update_date      := SYSDATE;
        INSERT INTO cms_doc_ucm_rel_t VALUES l_cms_doc_ucm_rel;
      END IF;
      --插入条目数据
      SELECT cms_encyclopedias_entry_s.NEXTVAL
        INTO l_encyclopedias_entry_row.entry_id
        FROM dual;
      l_encyclopedias_entry_row.doc_id                := l_doc_row.doc_id;
      l_encyclopedias_entry_row.entry_header          := p_entry_header;
      l_encyclopedias_entry_row.SOURCE                := p_source;
      l_encyclopedias_entry_row.knowledge_point       := p_knowledge_point;
      l_encyclopedias_entry_row.keyword               := p_keyword;
      l_encyclopedias_entry_row.entry_content         := p_entry_content;
      l_encyclopedias_entry_row.store_time            := SYSDATE;
      l_encyclopedias_entry_row.object_version_number := 1;
      l_encyclopedias_entry_row.created_by            := p_user_id;
      l_encyclopedias_entry_row.creation_date         := SYSDATE;
      l_encyclopedias_entry_row.last_updated_by       := p_user_id;
      l_encyclopedias_entry_row.last_update_date      := SYSDATE;
      INSERT INTO cms_encyclopedias_entry_t
      VALUES l_encyclopedias_entry_row;
    ELSE
      --条目编辑
      IF p_category_name IS NOT NULL THEN
        UPDATE cms_doc_category_rel_t rt
           SET rt.category_id = l_category_id
         WHERE rt.doc_id = to_number(p_doc_id);
      END IF;
      UPDATE cms_doc_ucm_rel_t rt
         SET rt.ucm_did          = p_ucm_did,
             rt.ucm_docname      = p_ucm_doc_name,
             rt.file_name        = p_file_name,
             rt.last_updated_by  = p_user_id,
             rt.last_update_date = SYSDATE
       WHERE rt.doc_id = to_number(p_doc_id);
      UPDATE cms_encyclopedias_entry_t et
         SET et.entry_header     = p_entry_header,
             et.SOURCE           = p_source,
             et.knowledge_point  = p_knowledge_point,
             et.keyword          = p_keyword,
             et.store_time       = SYSDATE,
             et.entry_content    = p_entry_content,
             et.last_updated_by  = p_user_id,
             et.last_update_date = SYSDATE
       WHERE et.doc_id = to_number(p_doc_id);
    END IF;
    x_return_code := 'S';
  EXCEPTION
    WHEN OTHERS THEN
      UPDATE cms_doc_t t
         SET t.status           = 'FAIL_OFFLINE_ENTRY_PROCESS',
             t.last_update_date = SYSDATE
       WHERE t.doc_id = (SELECT doc_id
                           FROM cms_task_monitor_t
                          WHERE task_id = to_number(p_task_id));
      COMMIT;
      x_return_code := 'E';
      x_return_msg  := SQLCODE || SQLERRM;
  END encyclopedias_entry_processor;

  PROCEDURE update_task_when_finish(p_task_id     IN VARCHAR2,
                                    x_return_code OUT VARCHAR2,
                                    x_return_msg  OUT VARCHAR2) IS
    PRAGMA AUTONOMOUS_TRANSACTION;
    l_process_info VARCHAR2(1000);
  BEGIN
    BEGIN
      SELECT t.process_info
        INTO l_process_info
        FROM cms_task_monitor_log_t t
       WHERE t.doc_id = (SELECT doc_id
                           FROM cms_task_monitor_t
                          WHERE task_id = p_task_id)
         AND t.seq = (SELECT MAX(seq)
                        FROM cms_task_monitor_log_t
                       WHERE doc_id = t.doc_id);
    EXCEPTION
      WHEN OTHERS THEN
        l_process_info := SQLCODE || SQLERRM;
    END;
    UPDATE cms_task_monitor_t tmt
       SET tmt.description = l_process_info, tmt.last_update_date = SYSDATE
    
     WHERE tmt.task_id = to_number(p_task_id);
    UPDATE cms_doc_t t
       SET t.status           = 'IMPORTED_OFFLINE_ENTRY_PROCESS',
           t.last_update_date = SYSDATE
     WHERE t.doc_id = (SELECT doc_id
                         FROM cms_task_monitor_t
                        WHERE task_id = to_number(p_task_id));
    COMMIT;
    x_return_code := 'S';
  EXCEPTION
    WHEN OTHERS THEN
      ROLLBACK;
      x_return_code := 'E';
      x_return_msg  := SQLCODE || SQLERRM;
  END update_task_when_finish;

  PROCEDURE task_processor(p_task_id      IN VARCHAR2,
                           p_user_id      IN VARCHAR2,
                           p_file_name    IN VARCHAR2,
                           p_ucm_did      IN VARCHAR2,
                           p_ucm_doc_name IN VARCHAR2,
                           x_return_code  OUT VARCHAR2,
                           x_return_msg   OUT VARCHAR2) IS
    l_cms_doc_ucm_rel cms_doc_ucm_rel_t%ROWTYPE;
    l_doc_id          NUMBER;
  BEGIN
    BEGIN
      SELECT t.doc_id
        INTO l_doc_id
        FROM cms_task_monitor_t t
       WHERE t.task_id = to_number(p_task_id);
    EXCEPTION
      WHEN OTHERS THEN
        l_doc_id := NULL;
    END;
    SELECT cms_doc_ucm_rel_s.NEXTVAL
      INTO l_cms_doc_ucm_rel.rel_id
      FROM dual;
    l_cms_doc_ucm_rel.doc_id                := l_doc_id;
    l_cms_doc_ucm_rel.ucm_did               := p_ucm_did;
    l_cms_doc_ucm_rel.ucm_docname           := upper(p_ucm_doc_name);
    l_cms_doc_ucm_rel.file_name             := p_file_name;
    l_cms_doc_ucm_rel.ftp_path              := NULL;
    l_cms_doc_ucm_rel.ftp_upload_date       := NULL;
    l_cms_doc_ucm_rel.object_version_number := 1;
    l_cms_doc_ucm_rel.created_by            := p_user_id;
    l_cms_doc_ucm_rel.creation_date         := SYSDATE;
    l_cms_doc_ucm_rel.last_updated_by       := p_user_id;
    l_cms_doc_ucm_rel.last_update_date      := SYSDATE;
    INSERT INTO cms_doc_ucm_rel_t VALUES l_cms_doc_ucm_rel;
    x_return_code := 'S';
  EXCEPTION
    WHEN OTHERS THEN
      x_return_code := 'E';
      x_return_msg  := SQLCODE || SQLERRM;
  END task_processor;

  PROCEDURE task_monitor_log_processor(p_task_id      IN VARCHAR2,
                                       p_process_info IN VARCHAR2,
                                       p_operator     IN VARCHAR2,
                                       p_log_type     IN VARCHAR2,
                                       p_user_id      IN VARCHAR2,
                                       p_seq          IN NUMBER,
                                       x_return_code  OUT VARCHAR2,
                                       x_return_msg   OUT VARCHAR2) IS
    PRAGMA AUTONOMOUS_TRANSACTION;
    l_task_monitor_log_row cms_task_monitor_log_t%ROWTYPE;
    l_user_name            VARCHAR2(100);
    l_doc_id               NUMBER;
  BEGIN
    IF p_user_id IS NOT NULL THEN
      BEGIN
        SELECT diplay_name
          INTO l_user_name
          FROM cms_user_t
         WHERE user_id = p_user_id;
      EXCEPTION
        WHEN OTHERS THEN
          l_user_name := NULL;
      END;
    ELSE
      l_user_name := p_operator;
    END IF;
    BEGIN
      SELECT doc_id
        INTO l_doc_id
        FROM cms_task_monitor_t t
       WHERE t.task_id = to_number(p_task_id);
    EXCEPTION
      WHEN OTHERS THEN
        l_doc_id := NULL;
    END;
    IF p_log_type = 'INSERT' THEN
      SELECT cms_task_monitor_log_s.NEXTVAL
        INTO l_task_monitor_log_row.log_id
        FROM dual;
      l_task_monitor_log_row.doc_id                := l_doc_id;
      l_task_monitor_log_row.process_info          := p_process_info;
      l_task_monitor_log_row.task_operator         := l_user_name;
      l_task_monitor_log_row.seq                   := p_seq;
      l_task_monitor_log_row.object_version_number := 1;
      l_task_monitor_log_row.created_by            := nvl(p_user_id, -1);
      l_task_monitor_log_row.creation_date         := SYSDATE;
      l_task_monitor_log_row.last_updated_by       := nvl(p_user_id, -1);
      l_task_monitor_log_row.last_update_date      := SYSDATE;
      INSERT INTO cms_task_monitor_log_t VALUES l_task_monitor_log_row;
    ELSIF p_log_type = 'UPDATE' THEN
      UPDATE cms_task_monitor_log_t lt
         SET lt.process_info     = p_process_info,
             lt.task_operator    = l_user_name,
             lt.last_update_date = SYSDATE
       WHERE lt.doc_id = l_doc_id
         AND lt.seq = p_seq;
      UPDATE cms_task_monitor_t mt
         SET mt.description = p_process_info, mt.last_update_date = SYSDATE
       WHERE mt.task_id = p_task_id;
      UPDATE cms_doc_t t
         SET t.status           = 'FAIL_OFFLINE_ENTRY_PROCESS',
             t.last_update_date = SYSDATE
       WHERE t.doc_id = (SELECT doc_id
                           FROM cms_task_monitor_t
                          WHERE task_id = to_number(p_task_id));
    END IF;
    x_return_code := 'S';
    COMMIT;
  EXCEPTION
    WHEN OTHERS THEN
      ROLLBACK;
      x_return_code := 'E';
      x_return_msg  := SQLCODE || SQLERRM;
  END task_monitor_log_processor;

  FUNCTION delete_task(p_doc_id IN NUMBER) RETURN VARCHAR IS
  BEGIN
    DELETE FROM cms_task_monitor_t t WHERE t.doc_id = p_doc_id;
    DELETE FROM cms_doc_t d WHERE d.doc_id = p_doc_id;
    DELETE FROM cms_doc_ucm_rel_t r WHERE r.doc_id = p_doc_id;
    RETURN 'S';
  EXCEPTION
    WHEN OTHERS THEN
      RETURN SQLCODE || SQLERRM;
  END delete_task;

  FUNCTION get_lib_code(p_lib_id IN VARCHAR2) RETURN VARCHAR2 IS
    l_lib_code VARCHAR2(100);
  BEGIN
    --获取库名
    BEGIN
      SELECT lib.lib_code
        INTO l_lib_code
        FROM cms_lib_v lib
       WHERE lib.lib_id = p_lib_id;
      RETURN l_lib_code;
    EXCEPTION
      WHEN OTHERS THEN
        RETURN 'ENCYCLOPEDIAS_ENTRY';
        --  RETURN 'WORKS_ENTRY';
    END;
  END get_lib_code;

  FUNCTION delete_entry(p_doc_id IN NUMBER) RETURN VARCHAR2 IS
    l_lib_code VARCHAR2(100);
  BEGIN
    SELECT t.lib_code
      INTO l_lib_code
      FROM cms_doc_t t
     WHERE t.doc_id = p_doc_id;
    IF l_lib_code = 'WORKS_ENTRY' THEN
      DELETE FROM cms_works_entry_t et WHERE et.doc_id = p_doc_id;
      DELETE FROM dup_text_1 t1 WHERE t1.doc_id = p_doc_id;
      DELETE FROM dup_result_1 r1
       WHERE p_doc_id IN (r1.this_doc_id, r1.that_doc_id);
    ELSIF l_lib_code = 'ENCYCLOPEDIAS_ENTRY' THEN
      DELETE FROM cms_encyclopedias_entry_t et WHERE et.doc_id = p_doc_id;
      DELETE FROM dup_text_2 t2 WHERE t2.doc_id = p_doc_id;
      DELETE FROM dup_result_2 r2
       WHERE p_doc_id IN (r2.this_doc_id, r2.that_doc_id);
    END IF;
    DELETE FROM cms_doc_t t WHERE t.doc_id = p_doc_id;
    DELETE FROM cms_doc_ucm_rel_t rt WHERE rt.doc_id = p_doc_id;
    RETURN 'S';
  EXCEPTION
    WHEN OTHERS THEN
      RETURN SQLCODE || SQLERRM;
  END delete_entry;

  PROCEDURE get_gather_monitor_count(p_doc_id           IN NUMBER,
                                     x_success_count    OUT VARCHAR2,
                                     x_fail_count       OUT VARCHAR2,
                                     x_processing_count OUT VARCHAR2) IS
  BEGIN
    SELECT COUNT(*)
      INTO x_success_count
      FROM cms_gather_monitor_t t
     WHERE t.doc_id = p_doc_id
       AND t.RESULT IS NOT NULL
       AND t.RESULT <> 'FAIL'
       AND t.thumbnail_result <> 'FAIL';
    SELECT COUNT(*)
      INTO x_fail_count
      FROM cms_gather_monitor_t t
     WHERE t.doc_id = p_doc_id
       AND (t.RESULT = 'FAIL' OR t.thumbnail_result = 'FAIL');
    SELECT COUNT(*)
      INTO x_processing_count
      FROM cms_gather_monitor_t t
     WHERE t.doc_id = p_doc_id
       AND t.RESULT IS NULL;
  END get_gather_monitor_count;

  PROCEDURE get_batch_gather_monitor_count(p_comp_code        IN VARCHAR2,
                                           p_lib_code         IN VARCHAR2,
                                           p_user_name        IN VARCHAR2,
                                           x_success_count    OUT VARCHAR2,
                                           x_fail_count       OUT VARCHAR2,
                                           x_processing_count OUT VARCHAR2) IS
  
  BEGIN
    SELECT COUNT(*)
      INTO x_success_count
      FROM cms_gather_monitor_t t
     WHERE t.attribute1 = p_user_name
       AND t.attribute2 = p_comp_code
       AND t.attribute3 = p_lib_code
       AND t.RESULT IS NOT NULL
       AND t.RESULT <> 'FAIL'
       AND t.thumbnail_result <> 'FAIL';
    SELECT COUNT(*)
      INTO x_fail_count
      FROM cms_gather_monitor_t t
     WHERE t.attribute1 = p_user_name
       AND t.attribute2 = p_comp_code
       AND t.attribute3 = p_lib_code
       AND (t.RESULT = 'FAIL' OR t.thumbnail_result = 'FAIL');
    SELECT COUNT(*)
      INTO x_processing_count
      FROM cms_gather_monitor_t t
     WHERE t.attribute1 = p_user_name
       AND t.attribute2 = p_comp_code
       AND t.attribute3 = p_lib_code
       AND t.RESULT IS NULL;
  END get_batch_gather_monitor_count;

  FUNCTION get_doc_type(p_ftp_path IN VARCHAR2, p_lib_code IN VARCHAR2)
    RETURN VARCHAR2 IS
  BEGIN
    FOR c IN (SELECT r.attribute1, r.attribute2
                FROM cms_exp_res_v r
               WHERE r.lib_code = p_lib_code
                 AND substr(r.res_type_code,
                            1,
                            instr(r.res_type_code, '#') - 1) = 'ATTACH_TYPE') LOOP
      IF instr(p_ftp_path, c.attribute1) > 0 THEN
        RETURN c.attribute2;
      END IF;
    END LOOP;
    RETURN NULL;
  EXCEPTION
    WHEN OTHERS THEN
      RETURN NULL;
  END get_doc_type;

  FUNCTION get_doc_type_name(p_ftp_path IN VARCHAR2, p_doc_id IN NUMBER)
    RETURN VARCHAR2 IS
    l_result   VARCHAR2(100);
    l_lib_code VARCHAR2(100);
  BEGIN
    IF p_ftp_path IS NULL THEN
      RETURN '主文件';
    END IF;
    BEGIN
      SELECT t.lib_code
        INTO l_lib_code
        FROM cms_doc_t t
       WHERE t.doc_id = p_doc_id;
    EXCEPTION
      WHEN OTHERS THEN
        l_lib_code := NULL;
    END;
    BEGIN
      SELECT decode(r.attribute1, '原始文件', '主文件', r.attribute1)
        INTO l_result
        FROM cms_exp_res_v r
       WHERE r.lib_code = l_lib_code
         AND substr(r.res_type_code, 1, instr(r.res_type_code, '#') - 1) =
             'ATTACH_TYPE'
         AND instr(p_ftp_path, r.attribute1) > 0;
    EXCEPTION
      WHEN OTHERS THEN
        l_result := NULL;
    END;
    /*  FOR c IN (SELECT r.attribute1, r.attribute2
                FROM cms_exp_res_v r
               WHERE r.lib_code = l_lib_code
                 AND substr(r.res_type_code,
                            1,
                            instr(r.res_type_code, '#') - 1) = 'ATTACH_TYPE') LOOP
      IF instr(p_ftp_path, c.attribute1) > 0 THEN
        IF c.attribute1 = '原始文件' THEN
          RETURN '主文件';
        END IF;
        RETURN c.attribute1;
      END IF;
    END LOOP;*/
    RETURN l_result;
  EXCEPTION
    WHEN OTHERS THEN
      RETURN NULL;
  END get_doc_type_name;

  FUNCTION get_entry_title(p_doc_id IN NUMBER) RETURN VARCHAR2 IS
    l_entry_title VARCHAR2(2000);
    l_lib_code    VARCHAR2(50);
  BEGIN
    SELECT t.lib_code
      INTO l_lib_code
      FROM cms_doc_t t
     WHERE t.doc_id = p_doc_id;
    IF l_lib_code = 'WORKS_ENTRY' THEN
      SELECT t.title
        INTO l_entry_title
        FROM cms_works_entry_t t
       WHERE t.doc_id = p_doc_id;
    ELSIF l_lib_code = 'ENCYCLOPEDIAS_ENTRY' THEN
      SELECT t.entry_header
        INTO l_entry_title
        FROM cms_encyclopedias_entry_t t
       WHERE t.doc_id = p_doc_id;
    END IF;
    RETURN l_entry_title;
  EXCEPTION
    WHEN OTHERS THEN
      RETURN NULL;
  END get_entry_title;

  FUNCTION get_file_manage_directory(p_doc_id IN NUMBER) RETURN VARCHAR2 IS
    l_result   VARCHAR2(4000);
    l_lib_code VARCHAR2(100);
  BEGIN
    SELECT d.lib_code
      INTO l_lib_code
      FROM cms_doc_t d
     WHERE d.doc_id = p_doc_id;
    FOR c1 IN (SELECT ATTRIBUTE1 ftp_path
                 FROM cms_exp_res_v r
                WHERE r.lib_code = l_lib_code
                  AND r.res_type_simple_code = 'ATTACH_TYPE'
               UNION
               SELECT DISTINCT t.ftp_path
                 FROM cms_doc_ucm_rel_t t
                WHERE t.doc_id = p_doc_id) LOOP
      l_result := l_result || ',' || c1.ftp_path;
    END LOOP;
    IF l_result IS NOT NULL THEN
      l_result := substr(l_result, 2);
    END IF;
    RETURN l_result;
  EXCEPTION
    WHEN OTHERS THEN
      RETURN NULL;
  END get_file_manage_directory;

  PROCEDURE update_other_category_for_book(p_doc_id      IN NUMBER,
                                           p_category_id IN NUMBER) IS
    PRAGMA AUTONOMOUS_TRANSACTION;
    l_category_id NUMBER := p_category_id;
  BEGIN
    BEGIN
      IF p_category_id IS NULL THEN
        SELECT rt.category_id
          INTO l_category_id
          FROM cms_doc_category_rel_t rt
         WHERE rt.doc_id = p_doc_id;
      END IF;
    EXCEPTION
      WHEN OTHERS THEN
        l_category_id := -1;
    END;
    FOR c IN (SELECT parent_doc_id
                FROM cms_doc_rel_t
               WHERE doc_id = p_doc_id
                 AND rel_type = 'ILLUSTRATION') LOOP
      UPDATE cms_doc_category_rel_t t
         SET t.category_id = l_category_id, t.last_update_date = SYSDATE
       WHERE t.doc_id = c.parent_doc_id;
    END LOOP;
    COMMIT;
  EXCEPTION
    WHEN OTHERS THEN
      ROLLBACK;
  END update_other_category_for_book;

  FUNCTION validate_book_exsit(p_book_name  IN VARCHAR2,
                               p_isbn       VARCHAR2,
                               p_edition    IN VARCHAR2,
                               p_impression IN VARCHAR2) RETURN VARCHAR2 IS
    l_count NUMBER;
  BEGIN
    SELECT COUNT(*)
      INTO l_count
      FROM cms_doc_t d, cms_book_t b
     WHERE d.doc_id = b.doc_id
       AND b.book_name = p_book_name
       AND b.isbn = p_isbn
       AND b.edition = p_edition
       AND b.impression = p_impression;
    /* AND nvl(d.delete_flag, 'N') = 'N'*/
    IF l_count > 0 THEN
      RETURN 'Y';
    END IF;
    RETURN 'N';
  END validate_book_exsit;

  FUNCTION get_thumbnail_url(p_doc_id IN NUMBER) RETURN VARCHAR2 IS
    l_thumbnail_url VARCHAR2(500);
  BEGIN
    SELECT t.thumbnail_url
      INTO l_thumbnail_url
      FROM cms_doc_t t
     WHERE t.doc_id = p_doc_id;
    RETURN l_thumbnail_url;
  EXCEPTION
    WHEN OTHERS THEN
      RETURN NULL;
  END get_thumbnail_url;

  FUNCTION get_preview_url(p_doc_id IN NUMBER) RETURN VARCHAR2 IS
    l_lib_code    VARCHAR2(100);
    l_preview_url VARCHAR2(1000);
  BEGIN
    SELECT t.lib_code
      INTO l_lib_code
      FROM cms_doc_t t
     WHERE t.doc_id = p_doc_id;
    IF l_lib_code = 'AUDIO' THEN
      SELECT a.preview_url
        INTO l_preview_url
        FROM cms_audio_t a
       WHERE a.doc_id = p_doc_id;
    ELSIF l_lib_code = 'VIDEO' THEN
      SELECT v.preview_url
        INTO l_preview_url
        FROM cms_video_t v
       WHERE v.doc_id = p_doc_id;
    END IF;
    RETURN l_preview_url;
  EXCEPTION
    WHEN OTHERS THEN
      RETURN NULL;
  END get_preview_url;

END cms_common_pkg;
/
