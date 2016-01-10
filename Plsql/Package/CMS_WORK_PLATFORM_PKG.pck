CREATE OR REPLACE PACKAGE cms_work_platform_pkg IS

  -- Author  : VICKY
  -- Created : 2014/4/29 10:04:38
  -- Purpose : 工作平台功能包

  /**插入流程日志**/
  PROCEDURE insert_process_log(p_doc_id      IN NUMBER,
                               p_operation   IN VARCHAR2,
                               p_operator    IN VARCHAR2,
                               p_end_point   IN VARCHAR2,
                               p_lib_code    IN VARCHAR2,
                               x_return_code OUT VARCHAR2,
                               x_return_msg  OUT VARCHAR2);

  PROCEDURE destroy_assort_doc(p_doc_id IN NUMBER, p_lib_code IN VARCHAR2);

  PROCEDURE destroy_doc(p_doc_id      IN NUMBER,
                        x_return_code OUT VARCHAR2,
                        x_return_msg  OUT VARCHAR2);

  PROCEDURE delete_chapter(p_doc_id IN NUMBER);

  FUNCTION get_author_category_count(p_category_id IN NUMBER,
                                     p_comp_code   IN VARCHAR2,
                                     p_status      VARCHAR2) RETURN NUMBER;

  FUNCTION get_detail_category_count(p_category_id IN NUMBER,
                                     p_comp_code   IN VARCHAR2) RETURN NUMBER;

  FUNCTION update_book_assort_res_status(p_parent_doc_id IN NUMBER,
                                         p_status        VARCHAR2,
                                         p_lib_code      IN VARCHAR2)
    RETURN VARCHAR2;

  FUNCTION get_category_tree_node_path(p_comp_code IN VARCHAR2,
                                       p_lib_code  IN VARCHAR2)
    RETURN VARCHAR2;

  FUNCTION get_category_tree_node_xml(p_comp_code IN VARCHAR2,
                                      p_lib_code  IN VARCHAR2) RETURN CLOB;

  FUNCTION get_category_lib_code(p_category_id IN NUMBER) RETURN VARCHAR2;

  FUNCTION insert_theme_tree_node(p_node_name      IN VARCHAR2,
                                  p_node_type      IN VARCHAR2,
                                  p_parent_node_id IN VARCHAR2,
                                  p_user_id        IN VARCHAR2,
                                  p_comp_code      IN VARCHAR2)
    RETURN VARCHAR2;

  FUNCTION delete_theme_tree_node(p_node_id IN NUMBER) RETURN VARCHAR2;

  FUNCTION get_theme_resource_count(p_node_id IN NUMBER) RETURN NUMBER;

  FUNCTION get_theme_resource_statistics(p_node_id IN NUMBER) RETURN VARCHAR2;

  FUNCTION delete_theme_resource(p_resource_id IN NUMBER) RETURN VARCHAR2;

  FUNCTION insert_theme_resource(p_node_id   IN VARCHAR2,
                                 p_doc_id    IN VARCHAR2,
                                 p_comp_code IN VARCHAR,
                                 p_user_id   IN NUMBER) RETURN VARCHAR2;

  FUNCTION get_theme_tree_parent_node(p_node_id IN VARCHAR2) RETURN VARCHAR2;

  FUNCTION get_docname_by_doc_id(p_doc_id IN NUMBER) RETURN VARCHAR2;

  FUNCTION get_libcode_by_doc_id(p_doc_id IN NUMBER) RETURN VARCHAR2;

  --插入文件管理监控记录
  PROCEDURE insert_fm_monitor_record(p_doc_id           IN NUMBER,
                                     p_curr_file_count  OUT VARCHAR2,
                                     p_total_file_count OUT VARCHAR2);

  FUNCTION get_category_level(p_apply_id IN NUMBER) RETURN NUMBER;

  FUNCTION get_category_res(p_category_id IN NUMBER, p_apply_id IN NUMBER)
    RETURN VARCHAR2;

  --当导出类型为THEME时，p_doc_id代表主题的docId,当导出类型为申请时，p_doc_id代表申请Id
  PROCEDURE create_export_log(p_doc_id      IN VARCHAR2,
                              p_export_type IN VARCHAR2,
                              p_user_id     IN VARCHAR2);

  PROCEDURE insert_log_row(p_doc_id       IN VARCHAR2,
                           p_content      IN VARCHAR2,
                           p_bacth_number IN NUMBER,
                           p_attribute1   IN VARCHAR2,
                           p_attribute2   IN VARCHAR2,
                           p_user_id      IN VARCHAR2);

  PROCEDURE update_export_log_status(p_attribute1  IN VARCHAR2,
                                     p_export_type IN VARCHAR2,
                                     p_user_id     IN NUMBER);

  PROCEDURE update_export_log_to_finish(p_attribute1    IN VARCHAR2,
                                        p_export_type   IN VARCHAR2,
                                        p_resource_name IN VARCHAR2,
                                        p_user_id       IN NUMBER);

  FUNCTION get_status_doc_count(p_category_id       IN NUMBER,
                                p_delete_flag       IN VARCHAR2,
                                p_categoryGroupType IN VARCHAR2,
                                p_comp_code         IN VARCHAR2,
                                p_status_code       IN VARCHAR2,
                                p_search_value      IN VARCHAR2)
    RETURN NUMBER;

  --判断资源是否已经在暂存架中存在
  FUNCTION check_res_exsit_in_shelf(p_doc_id  IN VARCHAR2,
                                    p_user_id IN NUMBER) RETURN VARCHAR2;

  --判断资源是否已经在主题中存在
  FUNCTION check_res_exsit_in_theme(p_doc_id    IN VARCHAR2,
                                    p_node_id   IN VARCHAR2,
                                    p_comp_code IN VARCHAR2) RETURN VARCHAR2;
END cms_work_platform_pkg;
/
CREATE OR REPLACE PACKAGE BODY cms_work_platform_pkg IS
  PROCEDURE insert_process_log(p_doc_id      IN NUMBER,
                               p_operation   IN VARCHAR2,
                               p_operator    IN VARCHAR2,
                               p_end_point   IN VARCHAR2,
                               p_lib_code    IN VARCHAR2,
                               x_return_code OUT VARCHAR2,
                               x_return_msg  OUT VARCHAR2) IS
    PRAGMA AUTONOMOUS_TRANSACTION;
    l_log_id           NUMBER;
    l_log_row          CMS_PROCESS_LOG_T%ROWTYPE;
    l_end_point_name   VARCHAR2(100);
    l_start_point_name VARCHAR2(100);
    l_curr_status      VARCHAR2(100);
  BEGIN
    SELECT CMS_PROCESS_LOG_S.NEXTVAL INTO l_log_id FROM dual;
    BEGIN
      SELECT T.LOOKUP_TYPE_NAME
        INTO l_end_point_name
        FROM CMS_LOOKUP_TYPE_T T
       WHERE T.LOOKUP_TYPE_CODE = p_end_point;
    EXCEPTION
      WHEN OTHERS THEN
        SELECT v.status_name
          INTO l_start_point_name
          FROM cms_doc_status_v v
         WHERE v.status_code = p_end_point
           AND v.lib_code = p_lib_code;
    END;
    SELECT t.status
      INTO l_curr_status
      FROM cms_doc_t t
     WHERE t.doc_id = p_doc_id;
    BEGIN
      SELECT T.LOOKUP_TYPE_NAME
        INTO l_start_point_name
        FROM CMS_LOOKUP_TYPE_T T
       WHERE T.LOOKUP_TYPE_CODE = l_curr_status;
    EXCEPTION
      WHEN OTHERS THEN
        SELECT v.status_name
          INTO l_start_point_name
          FROM cms_doc_status_v v
         WHERE v.status_code = l_curr_status
           AND v.lib_code = p_lib_code;
    END;
    l_log_row.log_id                := l_log_id;
    l_log_row.doc_id                := p_doc_id;
    l_log_row.operation             := p_operation;
    l_log_row.process_operator      := p_operator;
    l_log_row.process_start_point   := l_start_point_name;
    l_log_row.process_end_point     := l_end_point_name;
    l_log_row.operation_time        := SYSDATE;
    l_log_row.object_version_number := 0;
    l_log_row.created_by            := p_operator;
    l_log_row.creation_date         := SYSDATE;
    l_log_row.last_updated_by       := p_operator;
    l_log_row.last_update_date      := SYSDATE;
    INSERT INTO CMS_PROCESS_LOG_T VALUES l_log_row;
    x_return_code := 'SUCCESS';
    COMMIT;
  EXCEPTION
    WHEN OTHERS THEN
      x_return_code := 'ERROR';
      x_return_msg  := SQLCODE || ':' || SQLERRM;
  END insert_process_log;

  PROCEDURE destroy_assort_doc(p_doc_id IN NUMBER, p_lib_code IN VARCHAR2) IS
  BEGIN
    --删除配套素材
    DELETE FROM cms_book_t WHERE doc_id = p_doc_id;
    DELETE FROM cms_elec_prod_t WHERE doc_id = p_doc_id;
    DELETE FROM cms_newspaper_t WHERE doc_id = p_doc_id;
    DELETE FROM cms_periodical_t WHERE doc_id = p_doc_id;
    DELETE FROM cms_activity_t WHERE doc_id = p_doc_id;
    DELETE FROM cms_teaching_plan_t WHERE doc_id = p_doc_id;
    DELETE FROM cms_illustration_t WHERE doc_id = p_doc_id;
    DELETE FROM cms_courseware_t WHERE doc_id = p_doc_id;
    DELETE FROM cms_photography_figure_t WHERE doc_id = p_doc_id;
    DELETE FROM cms_audio_t WHERE doc_id = p_doc_id;
    DELETE FROM cms_photography_figure_t WHERE doc_id = p_doc_id;
    DELETE FROM cms_video_t WHERE doc_id = p_doc_id;
    DELETE FROM cms_material_figure_t WHERE doc_id = p_doc_id;
    DELETE FROM cms_works_entry_t WHERE doc_id = p_doc_id;
    DELETE FROM cms_encyclopedias_entry_t WHERE doc_id = p_doc_id;
    DELETE FROM cms_doc_t t
     WHERE t.doc_id = p_doc_id
       AND t.lib_code = p_lib_code;
    DELETE FROM cms_doc_category_rel_t WHERE doc_id = p_doc_id;
    DELETE FROM cms_doc_rel_t WHERE doc_id = p_doc_id;
    DELETE FROM cms_doc_rel_t WHERE parent_doc_id = p_doc_id;
  END destroy_assort_doc;

  PROCEDURE destroy_doc(p_doc_id      IN NUMBER,
                        x_return_code OUT VARCHAR2,
                        x_return_msg  OUT VARCHAR2) IS
  BEGIN
    --根据id查找cms_doc_t 进行删除
    DELETE FROM cms_doc_t WHERE doc_id = p_doc_id;
    DELETE FROM cms_doc_category_rel_t WHERE doc_id = p_doc_id;
    DELETE FROM cms_doc_ucm_rel_t WHERE doc_id = p_doc_id;
    FOR c IN (SELECT t.ref_doc_id, t.doc_type
                FROM cms_doc_ucm_rel_t t
               WHERE t.doc_id = p_doc_id
                 AND t.ref_doc_id IS NOT NULL) LOOP
      destroy_assort_doc(c.ref_doc_id, c.doc_type);
    END LOOP;
    DELETE FROM cms_doc_rel_t WHERE doc_id = p_doc_id;
    DELETE FROM cms_doc_rel_t WHERE parent_doc_id = p_doc_id;
    --删除章节
    DELETE FROM cms_chapter_t t WHERE t.doc_id = p_doc_id;
    --删除精编信息
    DELETE FROM cms_jb_info_t t WHERE t.doc_id = p_doc_id;
    --删除宣传信息
    DELETE FROM cms_jb_info_t t WHERE t.doc_id = p_doc_id;
    --删除回退日志
    DELETE FROM cms_rollback_log_t t WHERE t.doc_id = p_doc_id;
    --删除操作日志
    DELETE FROM cms_process_log_t t WHERE t.doc_id = p_doc_id;
    --根据id查找其他表  进行删除
    DELETE FROM cms_book_t WHERE doc_id = p_doc_id;
    DELETE FROM cms_elec_prod_t WHERE doc_id = p_doc_id;
    DELETE FROM cms_newspaper_t WHERE doc_id = p_doc_id;
    DELETE FROM cms_periodical_t WHERE doc_id = p_doc_id;
    DELETE FROM cms_activity_t WHERE doc_id = p_doc_id;
    DELETE FROM cms_teaching_plan_t WHERE doc_id = p_doc_id;
    DELETE FROM cms_illustration_t WHERE doc_id = p_doc_id;
    DELETE FROM cms_courseware_t WHERE doc_id = p_doc_id;
    DELETE FROM cms_photography_figure_t WHERE doc_id = p_doc_id;
    DELETE FROM cms_audio_t WHERE doc_id = p_doc_id;
    DELETE FROM cms_photography_figure_t WHERE doc_id = p_doc_id;
    DELETE FROM cms_video_t WHERE doc_id = p_doc_id;
    DELETE FROM cms_material_figure_t WHERE doc_id = p_doc_id;
    DELETE FROM cms_works_entry_t WHERE doc_id = p_doc_id;
    DELETE FROM cms_encyclopedias_entry_t WHERE doc_id = p_doc_id;
    DELETE FROM cms_extend_attr_storage_t t WHERE t.doc_id = p_doc_id;
    --删除版权相关信息
    DELETE FROM cms_doc_t d
     WHERE d.doc_id IN (SELECT doc_id
                          FROM cms_author_copyright_t
                         WHERE book_doc_id = p_doc_id);
    DELETE FROM cms_3rd_authorization_item_t item
     WHERE item.author_doc_id IN
           (SELECT doc_id
              FROM cms_author_copyright_t
             WHERE book_doc_id = p_doc_id);
    DELETE FROM cms_author_copyright_t author
     WHERE author.book_doc_id = p_doc_id;
    x_return_code := 'SUCCESS';
    COMMIT;
  EXCEPTION
    WHEN OTHERS THEN
      x_return_code := 'ERROR';
      x_return_msg  := SQLCODE || ':' || SQLERRM;
  END destroy_doc;

  PROCEDURE delete_chapter(p_doc_id IN NUMBER) IS
  BEGIN
    DELETE FROM cms_chapter_t t WHERE t.doc_id = p_doc_id;
  END;

  FUNCTION get_author_category_count(p_category_id IN NUMBER,
                                     p_comp_code   IN VARCHAR2,
                                     p_status      VARCHAR2) RETURN NUMBER IS
    l_count      NUMBER := 0;
    l_attribute1 VARCHAR2(20);
    l_attribute2 VARCHAR2(10);
    l_start_num  NUMBER;
    l_end_num    NUMBER;
  BEGIN
    IF p_category_id = -2 OR p_category_id IS NULL THEN
      SELECT COUNT(*)
        INTO l_count
        FROM cms_author_copyright_t act, cms_doc_t d, cms_book_t b
       WHERE act.doc_id = d.doc_id
         AND d.comp_code = p_comp_code
         AND act.book_doc_id = b.doc_id
         AND d.status = nvl(p_status, d.status);
      RETURN l_count;
    END IF;
    IF p_category_id = -1 THEN
      SELECT COUNT(*)
        INTO l_count
        FROM cms_author_copyright_t act,
             cms_doc_category_rel_t dcr,
             cms_doc_t              d,
             cms_book_t             b
       WHERE act.doc_id = dcr.doc_id
         AND d.doc_id = act.doc_id
         AND d.comp_code = p_comp_code
         AND act.book_doc_id = b.doc_id
         AND d.status = nvl(p_status, d.status)
         AND dcr.category_id = -1;
      RETURN l_count;
    END IF;
    SELECT c.attribute2
      INTO l_attribute2
      FROM cms_category_t c
     WHERE c.category_id = p_category_id;
    SELECT ct.attribute1
      INTO l_attribute1
      FROM cms_category_t ct
     WHERE ct.parent_node_type = 'CATEGORY_GROUP'
     START WITH ct.category_id = p_category_id
    CONNECT BY PRIOR ct.parent_node_id = ct.category_id
           AND get_category_lib_code(ct.category_id) = 'AUTHOR';
    IF l_attribute2 IS NOT NULL THEN
      l_start_num := to_number(substr(l_attribute2,
                                      1,
                                      (instr(l_attribute2, ',') - 1)));
      l_end_num   := to_number(substr(l_attribute2,
                                      (instr(l_attribute2, ',') + 1)));
      FOR c IN (SELECT months_between(nvl(act.network_end_time, SYSDATE),
                                      SYSDATE) network_months,
                       act.network_copyright_flag,
                       months_between(nvl(act.paper_medium_end_time, SYSDATE),
                                      SYSDATE) paper_months,
                       act.paper_medium_copyright_flag,
                       months_between(nvl(act.broadcast_end_time, SYSDATE),
                                      SYSDATE) broadcast_months,
                       act.broadcast_copyright_flag,
                       months_between(nvl(act.audio_video_end_time, SYSDATE),
                                      SYSDATE) media_months,
                       act.audio_video_copyright_flag,
                       months_between(nvl(act.electronic_end_time, SYSDATE),
                                      SYSDATE) elec_months,
                       act.electronic_copyright_flag,
                       act.copyright_id
                  FROM cms_author_copyright_t act,
                       cms_doc_category_rel_t dcr,
                       cms_doc_t              d,
                       cms_book_t             b
                 WHERE act.doc_id = dcr.doc_id
                   AND act.doc_id = d.doc_id
                   AND d.comp_code = p_comp_code
                   AND act.book_doc_id = b.doc_id
                   AND d.status = nvl(p_status, d.status)
                   AND dcr.category_id IN
                       (SELECT ct.category_id
                          FROM cms_category_t ct
                         START WITH ct.category_id = p_category_id
                        CONNECT BY PRIOR ct.parent_node_id = ct.category_id
                               AND get_category_lib_code(ct.category_id) =
                                   'AUTHOR')) LOOP
        dbms_output.put_line(c.copyright_id);
        IF l_attribute1 = 'NETWORK' AND c.network_copyright_flag = 'YOU' THEN
          IF c.network_months < l_end_num AND
             c.network_months >= l_start_num THEN
            l_count := l_count + 1;
          END IF;
        END IF;
        IF l_attribute1 = 'PAPER' AND c.paper_medium_copyright_flag = 'YOU' THEN
          IF c.paper_months < l_end_num AND c.paper_months >= l_start_num THEN
            l_count := l_count + 1;
          END IF;
        END IF;
        IF l_attribute1 = 'ELEC' AND c.electronic_copyright_flag = 'YOU' THEN
          IF c.elec_months < l_end_num AND c.elec_months >= l_start_num THEN
            l_count := l_count + 1;
          END IF;
        END IF;
        IF l_attribute1 = 'MEDIA' AND c.audio_video_copyright_flag = 'YOU' THEN
          IF c.media_months < l_end_num AND c.media_months >= l_start_num THEN
            l_count := l_count + 1;
          END IF;
        END IF;
        IF l_attribute1 = 'BROADCAST' AND
           c.broadcast_copyright_flag = 'YOU' THEN
          IF c.broadcast_months < l_end_num AND
             c.broadcast_months >= l_start_num THEN
            l_count := l_count + 1;
          END IF;
        END IF;
      END LOOP;
    ELSE
      SELECT COUNT(*)
        INTO l_count
        FROM cms_author_copyright_t act,
             cms_doc_category_rel_t dcr,
             cms_doc_t              d
       WHERE act.doc_id = dcr.doc_id
         AND d.doc_id = act.doc_id
         AND d.comp_code = p_comp_code
         AND decode(l_attribute1,
                    'NETWORK',
                    act.network_copyright_flag,
                    'PAPER',
                    act.paper_medium_copyright_flag,
                    'ELEC',
                    act.electronic_copyright_flag,
                    'MEDIA',
                    act.audio_video_copyright_flag,
                    'BROADCAST',
                    act.broadcast_copyright_flag) = 'YOU'
         AND dcr.category_id = p_category_id
         AND d.status = nvl(p_status, d.status);
    END IF;
    RETURN l_count;
  EXCEPTION
    WHEN OTHERS THEN
      RETURN 0;
  END get_author_category_count;

  FUNCTION get_detail_category_count(p_category_id IN NUMBER,
                                     p_comp_code   IN VARCHAR2) RETURN NUMBER IS
    l_attribute2 VARCHAR2(10);
    l_start_num  NUMBER;
    l_end_num    NUMBER;
    l_count      NUMBER := 0;
  BEGIN
    SELECT c.attribute2
      INTO l_attribute2
      FROM cms_category_t c
     WHERE c.category_id = p_category_id;
    IF l_attribute2 IS NOT NULL THEN
      l_start_num := to_number(substr(l_attribute2,
                                      1,
                                      (instr(l_attribute2, ',') - 1)));
      l_end_num   := to_number(substr(l_attribute2,
                                      (instr(l_attribute2, ',') + 1)));
      FOR c IN (SELECT third.authorized_start_time,
                       months_between(add_months(third.authorized_start_time,
                                                 to_number(substr(third.authorized_valid_period,
                                                                  instr(third.authorized_valid_period,
                                                                        '_') + 1))),
                                      SYSDATE) expire_months
                  FROM cms_3rd_authorization_order_t third,
                       cms_3rd_authorization_item_t  item,
                       cms_author_copyright_t        author,
                       cms_book_t                    book
                 WHERE item.order_doc_id = third.doc_id
                   AND item.author_doc_id = author.doc_id
                   AND author.book_doc_id = book.doc_id
                   AND item.comp_code = p_comp_code) LOOP
        IF c.expire_months < l_end_num AND c.expire_months >= l_start_num THEN
          l_count := l_count + 1;
        END IF;
      END LOOP;
    END IF;
    RETURN l_count;
  EXCEPTION
    WHEN OTHERS THEN
      RETURN 0;
  END get_detail_category_count;

  FUNCTION update_book_assort_res_status(p_parent_doc_id IN NUMBER,
                                         p_status        VARCHAR2,
                                         p_lib_code      IN VARCHAR2)
    RETURN VARCHAR2 IS
  BEGIN
    FOR c IN (SELECT d.doc_id
                FROM cms_doc_t d, cms_doc_rel_t r
               WHERE d.doc_id = r.doc_id
                 AND d.lib_code = p_lib_code
                 AND r.parent_doc_id = p_parent_doc_id) LOOP
      UPDATE cms_doc_t doc
         SET doc.status = p_status, doc.last_update_date = SYSDATE
       WHERE doc.doc_id = c.doc_id;
    END LOOP;
    RETURN 'Y';
  EXCEPTION
    WHEN OTHERS THEN
      RETURN 'N';
  END update_book_assort_res_status;

  FUNCTION get_category_tree_node_path(p_comp_code IN VARCHAR2,
                                       p_lib_code  IN VARCHAR2)
    RETURN VARCHAR2 IS
    l_tree_path        VARCHAR2(4000);
    l_path             VARCHAR2(1000);
    l_count            NUMBER;
    l_parent_node_name VARCHAR2(100);
  BEGIN
    FOR cur_a IN (SELECT c.category_id, c.category_name, c.parent_node_id
                    FROM cms_category_t c, cms_lib_category_g_rel_t lcgr
                   WHERE lcgr.lib_code = p_lib_code
                     AND lcgr.comp_code = p_comp_code
                     AND abs(c.parent_node_id) = lcgr.rel_id
                   ORDER BY c.seq) LOOP
      l_path             := '';
      l_parent_node_name := cur_a.category_name || '_' || cur_a.category_id;
      FOR cur_b IN (SELECT a.*
                      FROM cms_category_t a
                     START WITH a.category_id = cur_a.category_id
                    CONNECT BY PRIOR a.category_id = a.parent_node_id
                           AND a.parent_node_type = 'CATEGORY') LOOP
        SELECT COUNT(*)
          INTO l_count
          FROM dual
         WHERE EXISTS (SELECT 1
                  FROM cms_category_t ct
                 WHERE ct.parent_node_id = cur_b.category_id
                   AND ct.parent_node_type = 'CATEGORY');
        IF cur_b.parent_node_type = 'CATEGORY' THEN
          l_path := l_path || '/' || cur_b.category_name || '_' ||
                    cur_a.category_id;
          IF l_count = 0 THEN
            l_tree_path := l_tree_path || ',' || l_parent_node_name ||
                           l_path;
            l_path      := '';
          END IF;
        ELSE
          IF l_count = 0 THEN
            l_tree_path := l_tree_path || ',' || l_parent_node_name;
          END IF;
        END IF;
      END LOOP;
    END LOOP;
    RETURN substr(l_tree_path, 2);
  EXCEPTION
    WHEN OTHERS THEN
      RETURN NULL;
  END get_category_tree_node_path;

  FUNCTION get_category_tree_node_xml(p_comp_code IN VARCHAR2,
                                      p_lib_code  IN VARCHAR2) RETURN CLOB IS
    l_tree_node_xml   CLOB;
    l_xmlHeader       VARCHAR2(100) := '<?xml version="1.0" encoding="UTF-8"?>';
    l_num             NUMBER;
    l_num1            NUMBER;
    l_temp_node_xml   VARCHAR2(4000);
    l_count_no_child  NUMBER := 0;
    l_count_has_child NUMBER := 0;
  BEGIN
    dbms_lob.createtemporary(l_tree_node_xml, TRUE);
    /*l_tree_node_xml := l_xmlHeader;*/
    dbms_lob.writeappend(l_tree_node_xml, length(l_xmlHeader), l_xmlHeader);
    /* l_tree_node_xml := l_tree_node_xml || ' <' || p_lib_code || '>';*/
    dbms_lob.writeappend(l_tree_node_xml,
                         length('<' || p_lib_code || '>'),
                         '<' || p_lib_code || '>');
    FOR cur_a IN (SELECT c.category_id, c.category_name, c.parent_node_id
                    FROM cms_category_t c, cms_lib_category_g_rel_t lcgr
                   WHERE lcgr.lib_code = p_lib_code
                     AND lcgr.comp_code = p_comp_code
                     AND c.parent_node_id = lcgr.rel_id
                   ORDER BY c.seq) LOOP
      SELECT COUNT(*)
        INTO l_num
        FROM dual
       WHERE EXISTS (SELECT 1
                FROM cms_category_t ct
               WHERE ct.parent_node_id = cur_a.category_id
                 AND ct.parent_node_type = 'CATEGORY');
      IF l_num = 0 THEN
        /* l_tree_node_xml := l_tree_node_xml || ' <category name="' ||
        cur_a.category_name || '" id="' ||
        cur_a.category_id || '"' || ' >';*/
        dbms_lob.writeappend(l_tree_node_xml,
                             length('<category name="' ||
                                    cur_a.category_name || '" id="' ||
                                    cur_a.category_id || '"' || ' >'),
                             '<category name="' || cur_a.category_name ||
                             '" id="' || cur_a.category_id || '"' || '/>');
      
      ELSE
        FOR cur_b IN (SELECT a.*
                        FROM cms_category_t a
                       START WITH a.category_id = cur_a.category_id
                      CONNECT BY PRIOR a.category_id = a.parent_node_id
                             AND a.parent_node_type = 'CATEGORY') LOOP
          SELECT COUNT(*)
            INTO l_num1
            FROM dual
           WHERE EXISTS (SELECT 1
                    FROM cms_category_t ct
                   WHERE ct.parent_node_id = cur_b.category_id
                     AND ct.parent_node_type = 'CATEGORY');
          --子目录，且下面还有子目录
          IF cur_b.parent_node_type = 'CATEGORY' AND l_num1 = 1 THEN
            l_count_has_child := l_count_has_child + 1;
            /* l_temp_node_xml   := l_temp_node_xml || ' <category name="' ||
            cur_b.category_name || '" id="' ||
            cur_b.category_id || '"' || ' >';*/
            dbms_lob.writeappend(l_tree_node_xml,
                                 length('<category name="' ||
                                        cur_b.category_name || '" id="' ||
                                        cur_b.category_id || '"' || '>'),
                                 '<category name="' || cur_b.category_name ||
                                 '" id="' || cur_b.category_id || '"' || '>');
            --叶子节点
          ELSIF cur_b.parent_node_type = 'CATEGORY' AND l_num1 = 0 THEN
            l_count_no_child := l_count_no_child + 1;
            /*l_temp_node_xml  := l_temp_node_xml || ' <category name="' ||
            cur_b.category_name || '" id="' ||
            cur_b.category_id || '"' || ' >';*/
            dbms_lob.writeappend(l_tree_node_xml,
                                 length('<category name="' ||
                                        cur_b.category_name || '" id="' ||
                                        cur_b.category_id || '"' || '>'),
                                 '<category name="' || cur_b.category_name ||
                                 '" id="' || cur_b.category_id || '"' || '>');
            FOR c IN 1 .. (l_count_has_child + l_count_no_child - 1) LOOP
              /* l_temp_node_xml := l_temp_node_xml || ' </category>';*/
              dbms_lob.writeappend(l_tree_node_xml,
                                   length('</category>'),
                                   '</category>');
            END LOOP;
            l_count_has_child := 0;
          ELSE
            -- 根目录
            IF l_num1 > 0 THEN
              /*l_temp_node_xml := l_temp_node_xml || ' <category name="' ||
              cur_a.category_name || '" id="' ||
              cur_a.category_id || '"' || ' >';*/
              dbms_lob.writeappend(l_tree_node_xml,
                                   length('<category name="' ||
                                          cur_a.category_name || '" id="' ||
                                          cur_a.category_id || '"' || '>'),
                                   '<category name="' ||
                                   cur_a.category_name || '" id="' ||
                                   cur_a.category_id || '"' || '>');
            END IF;
          END IF;
        END LOOP;
      END IF;
    END LOOP;
    /*l_tree_node_xml := l_tree_node_xml || l_temp_node_xml || ' </' ||
    p_lib_code || '>';*/
    /*  FOR i IN 1 .. 20 LOOP
      dbms_lob.writeappend(l_tree_node_xml,
                           length('<category name="test" id="1"/>'),
                           '<category name="test" id="1"/>');
    END LOOP;*/
    dbms_lob.writeappend(l_tree_node_xml,
                         length(l_temp_node_xml || ' </' || p_lib_code || '>'),
                         l_temp_node_xml || ' </' || p_lib_code || '>');
    /*    dbms_output.put_line(length(l_tree_node_xml));
    dbms_output.put_line(l_tree_node_xml);*/
    RETURN l_tree_node_xml;
  END get_category_tree_node_xml;

  FUNCTION get_category_lib_code(p_category_id IN NUMBER) RETURN VARCHAR2 IS
    l_lib_code VARCHAR2(100);
  BEGIN
    SELECT lcgr.lib_code
      INTO l_lib_code
      FROM cms_category_t c, cms_lib_category_g_rel_t lcgr
     WHERE cms_common_pkg.get_category_top_node_id(c.category_id) =
           lcgr.rel_id
       AND c.category_id = p_category_id;
    RETURN l_lib_code;
  EXCEPTION
    WHEN OTHERS THEN
      RETURN NULL;
  END get_category_lib_code;

  FUNCTION insert_theme_tree_node(p_node_name      IN VARCHAR2,
                                  p_node_type      IN VARCHAR2,
                                  p_parent_node_id IN VARCHAR2,
                                  p_user_id        IN VARCHAR2,
                                  p_comp_code      IN VARCHAR2)
  
   RETURN VARCHAR2 IS
    l_theme_tree_row cms_theme_tree_t%ROWTYPE;
  BEGIN
    SELECT cms_theme_tree_s.NEXTVAL
      INTO l_theme_tree_row.node_id
      FROM dual;
    l_theme_tree_row.node_name             := p_node_name;
    l_theme_tree_row.node_type             := p_node_type;
    l_theme_tree_row.parent_node_id        := p_parent_node_id;
    l_theme_tree_row.comp_code             := p_comp_code;
    l_theme_tree_row.object_version_number := 1;
    l_theme_tree_row.created_by            := p_user_id;
    l_theme_tree_row.creation_date         := SYSDATE;
    l_theme_tree_row.last_updated_by       := p_user_id;
    l_theme_tree_row.last_update_date      := SYSDATE;
    INSERT INTO cms_theme_tree_t VALUES l_theme_tree_row;
    RETURN l_theme_tree_row.node_id;
  EXCEPTION
    WHEN OTHERS THEN
      RETURN SQLCODE || SQLERRM;
  END insert_theme_tree_node;

  FUNCTION delete_theme_tree_node(p_node_id IN NUMBER) RETURN VARCHAR2 IS
  BEGIN
    DELETE FROM cms_theme_tree_t t
     WHERE t.node_id IN (SELECT tt.node_id
                           FROM cms_theme_tree_t tt
                         CONNECT BY PRIOR tt.node_id = tt.parent_node_id
                          START WITH tt.node_id = p_node_id);
    DELETE FROM cms_theme_resource_t t
     WHERE t.tree_node_id IN
           ((SELECT t.node_id
               FROM cms_theme_tree_t t
             CONNECT BY PRIOR t.node_id = t.parent_node_id
              START WITH t.node_id = p_node_id));
    RETURN 'S';
  EXCEPTION
    WHEN OTHERS THEN
      RETURN SQLCODE || SQLERRM;
  END delete_theme_tree_node;

  FUNCTION get_theme_resource_count(p_node_id IN NUMBER) RETURN NUMBER IS
    l_count NUMBER;
  BEGIN
    SELECT COUNT(1)
      INTO l_count
      FROM cms_theme_resource_t t
     WHERE t.tree_node_id IN
           ((SELECT t.node_id
               FROM cms_theme_tree_t t
             CONNECT BY PRIOR t.node_id = t.parent_node_id
              START WITH t.node_id = p_node_id));
    RETURN l_count;
  EXCEPTION
    WHEN OTHERS THEN
      RETURN 0;
  END get_theme_resource_count;

  FUNCTION get_theme_resource_statistics(p_node_id IN NUMBER) RETURN VARCHAR2 IS
    l_result VARCHAR2(2000);
  BEGIN
    FOR c IN (SELECT lib.lib_name, COUNT(rt.lib_code) res_count
                FROM cms_theme_resource_t rt, cms_lib_v lib
               WHERE rt.lib_code = lib.lib_code
                 AND rt.tree_node_id IN
                     ((SELECT t.node_id
                         FROM cms_theme_tree_t t
                       CONNECT BY PRIOR t.node_id = t.parent_node_id
                        START WITH t.node_id = p_node_id))
               GROUP BY lib.lib_name) LOOP
      l_result := l_result || c.lib_name || ':' || c.res_count || '   ';
    END LOOP;
    RETURN l_result;
  EXCEPTION
    WHEN OTHERS THEN
      RETURN NULL;
  END get_theme_resource_statistics;

  FUNCTION delete_theme_resource(p_resource_id IN NUMBER) RETURN VARCHAR2 IS
  BEGIN
    DELETE FROM cms_theme_resource_t t WHERE t.resource_id = p_resource_id;
    RETURN 'S';
  EXCEPTION
    WHEN OTHERS THEN
      RETURN SQLCODE || SQLERRM;
  END delete_theme_resource;

  FUNCTION insert_theme_resource(p_node_id   IN VARCHAR2,
                                 p_doc_id    IN VARCHAR2,
                                 p_comp_code IN VARCHAR,
                                 p_user_id   IN NUMBER) RETURN VARCHAR2 IS
    l_theme_resource_row cms_theme_resource_t%ROWTYPE;
    p_resource_name      VARCHAR2(500);
    p_lib_code           VARCHAR2(50);
  BEGIN
    SELECT v.title, v.lib_code
      INTO p_resource_name, p_lib_code
      FROM cms_theme_resource_origin_v v
     WHERE v.doc_id = p_doc_id;
    SELECT cms_theme_resource_s.NEXTVAL
      INTO l_theme_resource_row.resource_id
      FROM dual;
    l_theme_resource_row.resource_name         := p_resource_name;
    l_theme_resource_row.lib_code              := p_lib_code;
    l_theme_resource_row.tree_node_id          := p_node_id;
    l_theme_resource_row.comp_code             := p_comp_code;
    l_theme_resource_row.attribute1            := p_doc_id;
    l_theme_resource_row.object_version_number := 1;
    l_theme_resource_row.created_by            := p_user_id;
    l_theme_resource_row.creation_date         := SYSDATE;
    l_theme_resource_row.last_updated_by       := p_user_id;
    l_theme_resource_row.last_update_date      := SYSDATE;
    INSERT INTO cms_theme_resource_t VALUES l_theme_resource_row;
    RETURN 'S';
  EXCEPTION
    WHEN OTHERS THEN
      RETURN SQLCODE || SQLERRM;
  END insert_theme_resource;

  FUNCTION get_theme_tree_parent_node(p_node_id IN VARCHAR2) RETURN VARCHAR2 IS
    l_parent_node_id VARCHAR2(20);
  BEGIN
    SELECT to_char(t.parent_node_id)
      INTO l_parent_node_id
      FROM cms_theme_tree_t t
     WHERE t.node_type = 'ROOT'
    CONNECT BY PRIOR t.parent_node_id = t.node_id
     START WITH t.node_id = to_number(p_node_id);
    RETURN l_parent_node_id;
  EXCEPTION
    WHEN OTHERS THEN
      RETURN NULL;
  END get_theme_tree_parent_node;

  FUNCTION get_docname_by_doc_id(p_doc_id IN NUMBER) RETURN VARCHAR2 IS
    l_docname VARCHAR2(1000);
  BEGIN
    SELECT res.unique_res_name
      INTO l_docname
      FROM cms_res_common_info_v res
     WHERE res.doc_id = p_doc_id;
    RETURN l_docname;
  EXCEPTION
    WHEN OTHERS THEN
      RETURN NULL;
  END get_docname_by_doc_id;

  FUNCTION get_libcode_by_doc_id(p_doc_id IN NUMBER) RETURN VARCHAR2 IS
    l_libcode VARCHAR2(100);
  BEGIN
    SELECT lib_code
      INTO l_libcode
      FROM cms_doc_t t
     WHERE t.doc_id = p_doc_id;
    RETURN l_libcode;
  EXCEPTION
    WHEN OTHERS THEN
      RETURN NULL;
  END get_libcode_by_doc_id;

  PROCEDURE insert_fm_monitor_record(p_doc_id           IN NUMBER,
                                     p_curr_file_count  OUT VARCHAR2,
                                     p_total_file_count OUT VARCHAR2) IS
    PRAGMA AUTONOMOUS_TRANSACTION;
    l_monitor_row cms_file_manage_monitor_t%ROWTYPE;
    l_count       NUMBER;
  BEGIN
    SELECT COUNT(*)
      INTO l_count
      FROM cms_file_manage_monitor_t t
     WHERE t.doc_id = p_doc_id;
    IF l_count = 0 THEN
      SELECT cms_file_manage_monitor_s.NEXTVAL
        INTO l_monitor_row.monitor_id
        FROM dual;
      l_monitor_row.doc_id                := p_doc_id;
      l_monitor_row.curr_file_count       := p_curr_file_count;
      l_monitor_row.total_file_count      := p_total_file_count;
      l_monitor_row.created_by            := -1;
      l_monitor_row.creation_date         := SYSDATE;
      l_monitor_row.last_updated_by       := -1;
      l_monitor_row.last_update_date      := SYSDATE;
      l_monitor_row.object_version_number := 1;
      INSERT INTO cms_file_manage_monitor_t VALUES l_monitor_row;
    ELSE
      UPDATE cms_file_manage_monitor_t t
         SET t.curr_file_count  = p_curr_file_count,
             t.total_file_count = p_total_file_count
       WHERE t.doc_id = p_doc_id;
    END IF;
    COMMIT;
  EXCEPTION
    WHEN OTHERS THEN
      ROLLBACK;
  END insert_fm_monitor_record;

  FUNCTION get_category_level(p_apply_id IN NUMBER) RETURN NUMBER IS
    l_category_level NUMBER;
  BEGIN
    SELECT to_number(substr(h.category_level, length(h.category_level)))
      INTO l_category_level
      FROM cms_book_export_html_t h
     WHERE h.export_id IN (SELECT attribute1
                             FROM cms_res_exp_apply_l_t l
                            WHERE l.head_id = p_apply_id)
       AND ROWNUM = 1;
    RETURN l_category_level;
  EXCEPTION
    WHEN OTHERS THEN
      RETURN 1;
  END get_category_level;

  FUNCTION get_category_res(p_category_id NUMBER, p_apply_id NUMBER)
    RETURN VARCHAR2 IS
    l_category_res VARCHAR2(4000) := NULL;
  BEGIN
    IF p_category_id <> -1 THEN
      FOR c IN (SELECT v.doc_id
                  FROM cms_res_temp_shelf_v   v,
                       cms_doc_category_rel_t crt,
                       cms_category_t         c
                 WHERE v.shelf_id IN
                       (SELECT shelf_id
                          FROM cms_res_exp_apply_l_t l
                         WHERE l.head_id = p_apply_id)
                   AND v.doc_id = crt.doc_id(+)
                   AND crt.category_id = c.category_id(+)
                   AND p_category_id IN (c.category_id, c.parent_node_id)) LOOP
        l_category_res := l_category_res || ',' || c.doc_id;
      END LOOP;
    ELSE
      FOR c IN (SELECT v.doc_id
                  FROM cms_res_temp_shelf_v   v,
                       cms_doc_category_rel_t crt,
                       cms_category_t         c
                 WHERE v.shelf_id IN
                       (SELECT shelf_id
                          FROM cms_res_exp_apply_l_t l
                         WHERE l.head_id = p_apply_id)
                   AND v.doc_id = crt.doc_id(+)
                   AND crt.category_id = c.category_id(+)
                   AND p_category_id = nvl(c.category_id, -1)) LOOP
        l_category_res := l_category_res || ',' || c.doc_id;
      END LOOP;
    END IF;
    IF l_category_res IS NOT NULL THEN
      l_category_res := substr(l_category_res, 2);
    END IF;
    RETURN l_category_res;
  EXCEPTION
    WHEN OTHERS THEN
      RETURN NULL;
  END get_category_res;

  PROCEDURE create_export_log(p_doc_id      IN VARCHAR2,
                              p_export_type IN VARCHAR2,
                              p_user_id     IN VARCHAR2) IS
    l_doc_id       NUMBER := to_number(p_doc_id);
    l_batch_number NUMBER;
    l_theme_name   VARCHAR2(2000);
    l_apply_name   VARCHAR2(2000);
    l_category_id  NUMBER;
  BEGIN
    SELECT (MAX(t.batch_number) + 1)
      INTO l_batch_number
      FROM cms_theme_export_log_t t
     WHERE t.attribute2 = p_export_type
       AND t.created_by = p_user_id;
    IF l_batch_number IS NULL THEN
      l_batch_number := 1;
    END IF;
    IF p_export_type = 'THEME' THEN
      SELECT t.theme_name
        INTO l_theme_name
        FROM cms_theme_t t
       WHERE t.doc_id = l_doc_id;
      --生成主题的xml文件日志
      insert_log_row(p_doc_id,
                     '导出主题:【' || l_theme_name || '】,生成:' || l_theme_name ||
                     '/主题.xml 文件',
                     l_batch_number,
                     0,
                     p_export_type,
                     p_user_id);
      --生成库的xml文件日志
      FOR c IN (SELECT DISTINCT t.lib_code, lib.lib_name
                  FROM cms_theme_resource_t t, cms_lib_v lib
                 WHERE t.tree_node_id IN
                       (SELECT tree.node_id
                          FROM cms_theme_tree_t tree
                        CONNECT BY PRIOR tree.node_id = tree.parent_node_id
                         START WITH tree.parent_node_id = l_doc_id)
                   AND t.lib_code = lib.lib_code) LOOP
        insert_log_row(p_doc_id,
                       '导出库:【' || c.lib_name || '】,生成:' || c.lib_name || '/' ||
                       c.lib_name || '.xml',
                       l_batch_number,
                       0,
                       p_export_type,
                       p_user_id);
      END LOOP;
    ELSIF p_export_type = 'TEMPLATE' THEN
      SELECT t.apply_name
        INTO l_apply_name
        FROM cms_res_exp_apply_h_t t
       WHERE t.apply_id = p_doc_id;
      insert_log_row(p_doc_id,
                     '下载：【' || l_apply_name || '】,' || '生成：' ||
                     l_apply_name || '/index.xml',
                     l_batch_number,
                     0,
                     p_export_type,
                     p_user_id);
      insert_log_row(p_doc_id,
                     '下载：【' || l_apply_name || '】,' || '生成：' ||
                     l_apply_name || '/index.xls',
                     l_batch_number,
                     0,
                     p_export_type,
                     p_user_id);
      FOR c IN (SELECT t.res_name, t.res_id
                  FROM cms_res_exp_apply_l_v t
                 WHERE t.head_id = p_doc_id) LOOP
        insert_log_row(p_doc_id,
                       '下载：【' || l_apply_name || '】,' || '生成：' ||
                       l_apply_name || '/docs/' || c.res_name || '_' ||
                       c.res_id || '.xml',
                       l_batch_number,
                       0,
                       p_export_type,
                       p_user_id);
      END LOOP;
    ELSIF p_export_type = 'HTML' THEN
      SELECT t.apply_name
        INTO l_apply_name
        FROM cms_res_exp_apply_h_t t
       WHERE t.apply_id = p_doc_id;
      FOR c IN (SELECT v.doc_id
                  FROM cms_res_temp_shelf_v v
                 WHERE v.shelf_id IN
                       (SELECT shelf_id
                          FROM cms_res_exp_apply_l_t l
                         WHERE l.head_id = l_doc_id)) LOOP
        SELECT nvl(c.category_id, -1) category_id
          INTO l_category_id
          FROM cms_book_t b, cms_doc_category_rel_t crt, cms_category_t c
         WHERE b.doc_id = crt.doc_id(+)
           AND crt.category_id = c.category_id(+)
           AND b.doc_id = c.doc_id;
        insert_log_row(p_doc_id,
                       '下载：【' || l_apply_name || '】,' || '生成：' ||
                       '/main/Category_' || l_category_id || '/' ||
                       c.doc_id || '/' || c.doc_id || '.html',
                       l_batch_number,
                       0,
                       p_export_type,
                       p_user_id);
      END LOOP;
      FOR c1 IN (SELECT DISTINCT crt.category_id,
                                 c.parent_node_id,
                                 nvl(c.category_name, '未分类') category_name,
                                 c.parent_node_type
                   FROM cms_res_temp_shelf_v   v,
                        cms_doc_category_rel_t crt,
                        cms_category_t         c
                  WHERE v.shelf_id IN
                        (SELECT shelf_id
                           FROM cms_res_exp_apply_l_t l
                          WHERE l.head_id = l_doc_id)
                    AND v.doc_id = crt.doc_id(+)
                    AND crt.category_id = c.category_id(+)) LOOP
        insert_log_row(p_doc_id,
                       '下载：【' || l_apply_name || '】,' || '生成：' ||
                       '/main/Category_' || c1.category_id || '.html',
                       l_batch_number,
                       0,
                       p_export_type,
                       p_user_id);
      END LOOP;
      insert_log_row(p_doc_id,
                     '下载：【' || l_apply_name || '】,' || '生成：' || 'left.html',
                     l_batch_number,
                     0,
                     p_export_type,
                     p_user_id);
      insert_log_row(p_doc_id,
                     '下载：【' || l_apply_name || '】,' || '生成：' || 'main.html',
                     l_batch_number,
                     0,
                     p_export_type,
                     p_user_id);
      insert_log_row(p_doc_id,
                     '下载：【' || l_apply_name || '】,' || '生成：' || 'data.xml',
                     l_batch_number,
                     0,
                     p_export_type,
                     p_user_id);
    END IF;
  END create_export_log;

  PROCEDURE insert_log_row(p_doc_id       IN VARCHAR2,
                           p_content      IN VARCHAR2,
                           p_bacth_number IN NUMBER,
                           p_attribute1   IN VARCHAR2,
                           p_attribute2   IN VARCHAR2,
                           p_user_id      IN VARCHAR2) IS
    PRAGMA AUTONOMOUS_TRANSACTION;
    l_export_log_row cms_theme_export_log_t%ROWTYPE;
    l_log_id         NUMBER;
  BEGIN
    SELECT cms_theme_export_log_s.NEXTVAL INTO l_log_id FROM dual;
    l_export_log_row.log_id                := l_log_id;
    l_export_log_row.log_content           := p_content;
    l_export_log_row.batch_number          := p_bacth_number;
    l_export_log_row.object_version_number := 1;
    l_export_log_row.created_by            := p_user_id;
    l_export_log_row.creation_date         := SYSDATE;
    l_export_log_row.last_updated_by       := p_user_id;
    l_export_log_row.last_update_date      := SYSDATE;
    l_export_log_row.attribute1            := p_attribute1;
    l_export_log_row.attribute2            := p_attribute2;
    l_export_log_row.attribute3            := p_doc_id;
    INSERT INTO cms_theme_export_log_t VALUES l_export_log_row;
    COMMIT;
  EXCEPTION
    WHEN OTHERS THEN
      ROLLBACK;
  END insert_log_row;

  PROCEDURE update_export_log_status(p_attribute1  IN VARCHAR2,
                                     p_export_type IN VARCHAR2,
                                     p_user_id     IN NUMBER) IS
    PRAGMA AUTONOMOUS_TRANSACTION;
  BEGIN
    UPDATE cms_theme_export_log_t t
       SET t.attribute1 = p_attribute1
     WHERE t.batch_number =
           (SELECT MAX(batch_number)
              FROM cms_theme_export_log_t
             WHERE created_by = t.created_by
               AND attribute2 = t.attribute2)
       AND t.attribute2 = p_export_type
       AND t.created_by = p_user_id;
    COMMIT;
  EXCEPTION
    WHEN OTHERS THEN
      ROLLBACK;
  END update_export_log_status;

  PROCEDURE update_export_log_to_finish(p_attribute1    IN VARCHAR2,
                                        p_export_type   IN VARCHAR2,
                                        p_resource_name IN VARCHAR2,
                                        p_user_id       IN NUMBER) IS
    PRAGMA AUTONOMOUS_TRANSACTION;
  BEGIN
    UPDATE cms_theme_export_log_t t
       SET t.attribute1 = p_attribute1
     WHERE t.batch_number =
           (SELECT MAX(batch_number)
              FROM cms_theme_export_log_t
             WHERE created_by = t.created_by
               AND attribute2 = t.attribute2
               AND log_content = t.log_content)
       AND t.created_by = p_user_id
       AND t.attribute2 = p_export_type
       AND t.log_content LIKE '%' || p_resource_name || '%';
    COMMIT;
  EXCEPTION
    WHEN OTHERS THEN
      ROLLBACK;
  END update_export_log_to_finish;

  FUNCTION get_status_doc_count(p_category_id       IN NUMBER,
                                p_delete_flag       IN VARCHAR2,
                                p_categoryGroupType IN VARCHAR2,
                                p_comp_code         IN VARCHAR2,
                                p_status_code       IN VARCHAR2,
                                p_search_value      IN VARCHAR2)
    RETURN NUMBER IS
    l_count NUMBER := 0;
  BEGIN
    IF p_search_value IS NULL THEN
      SELECT COUNT(1)
        INTO l_count
        FROM cms_doc_t d, cms_doc_category_rel_t dcr
       WHERE d.status = p_status_code
         AND d.doc_id = dcr.doc_id
         AND dcr.category_group_type = p_categoryGroupType
         AND (p_category_id IS NULL OR
             dcr.category_id IN
             (SELECT c.category_id
                 FROM cms_category_t c
                WHERE c.attribute5 LIKE
                      '%' || (SELECT attribute5
                                FROM cms_category_t
                               WHERE category_id = p_category_id) || '%') OR
             dcr.category_id = p_category_id)
         AND nvl(d.delete_flag, 'N') = p_delete_flag
         AND d.comp_code = p_comp_code;
    ELSE
      /*SELECT COUNT(1)
       INTO l_count
       FROM cms_doc_t              d,
            cms_doc_category_rel_t dcr,
            CMS_RES_COMMON_INFO_V  res
      WHERE d.status = p_status_code
        AND d.doc_id = dcr.doc_id
        AND dcr.category_group_type = p_categoryGroupType
        AND (p_category_id IS NULL OR
            dcr.category_id IN
            (SELECT c.category_id
                FROM cms_category_t c
               WHERE c.attribute5 LIKE
                     '%' || (SELECT attribute5
                               FROM cms_category_t
                              WHERE category_id = p_category_id) || '%') OR
            dcr.category_id = p_category_id)
        AND nvl(d.delete_flag, 'N') = p_delete_flag
        AND d.comp_code = p_comp_code
        AND d.doc_id = res.doc_id
        AND res.res_name LIKE '%' || p_search_value || '%';*/
      SELECT COUNT(1)
        INTO l_count
        FROM cms_doc_t d, cms_doc_category_rel_t dcr
       WHERE d.status = p_status_code
         AND d.doc_id = dcr.doc_id
         AND dcr.category_group_type = p_categoryGroupType
         AND (p_category_id IS NULL OR
             dcr.category_id IN
             (SELECT c.category_id
                 FROM cms_category_t c
                WHERE c.attribute5 LIKE
                      '%' || (SELECT attribute5
                                FROM cms_category_t
                               WHERE category_id = p_category_id) || '%') OR
             dcr.category_id = p_category_id)
         AND nvl(d.delete_flag, 'N') = p_delete_flag
         AND d.comp_code = p_comp_code
         AND d.doc_id IN (SELECT *
                            FROM TABLE(CAST(cms_common_pkg.parse_string_with_separator(p_search_value,
                                                                                       ',') AS
                                            cms_table_type)));
    END IF;
    RETURN l_count;
  EXCEPTION
    WHEN OTHERS THEN
      RETURN 0;
  END get_status_doc_count;

  --判断资源是否已经在暂存架中存在
  FUNCTION check_res_exsit_in_shelf(p_doc_id  IN VARCHAR2,
                                    p_user_id IN NUMBER) RETURN VARCHAR2 IS
    l_res_name VARCHAR2(1000);
  BEGIN
    SELECT info.res_name
      INTO l_res_name
      FROM cms_res_temp_shelf_t shelf, cms_res_common_info_v info
     WHERE shelf.doc_id = info.doc_id
       AND shelf.doc_id = to_number(p_doc_id)
       AND nvl(shelf.apply_submit_flag, 'N') = 'N'
       AND nvl(shelf.delete_flag, 'N') = 'N'
       AND shelf.user_id = p_user_id
       AND rownum = 1;
    RETURN l_res_name;
  EXCEPTION
    WHEN OTHERS THEN
      RETURN NULL;
  END check_res_exsit_in_shelf;

  --判断资源是否已经在主题中存在
  FUNCTION check_res_exsit_in_theme(p_doc_id    IN VARCHAR2,
                                    p_node_id   IN VARCHAR2,
                                    p_comp_code IN VARCHAR2) RETURN VARCHAR2 IS
    l_res_name VARCHAR2(1000);
  BEGIN
    SELECT info.res_name
      INTO l_res_name
      FROM cms_theme_resource_t res, cms_res_common_info_v info
     WHERE res.attribute1 = p_doc_id
       AND res.tree_node_id = p_node_id
       AND res.comp_code = p_comp_code
       AND to_number(res.attribute1) = info.doc_id
       AND rownum = 1;
    RETURN l_res_name;
  EXCEPTION
    WHEN OTHERS THEN
      RETURN NULL;
  END check_res_exsit_in_theme;

END cms_work_platform_pkg;
/
