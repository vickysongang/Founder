CREATE OR REPLACE PACKAGE cms_pub_platform_pkg IS
  PROCEDURE add_to_res_temp_shelf(p_doc_id       IN NUMBER,
                                  p_user_id      IN NUMBER,
                                  p_storage_type IN VARCHAR2,
                                  p_data         IN VARCHAR2,
                                  x_return_code  OUT VARCHAR2,
                                  x_return_msg   OUT VARCHAR2);

  PROCEDURE save_res_download_apply(p_doc_id      IN NUMBER,
                                    p_apply_name  IN VARCHAR2,
                                    p_user_id     IN NUMBER,
                                    p_reason      IN VARCHAR2,
                                    p_remarks     IN VARCHAR2,
                                    p_data        IN VARCHAR2,
                                    x_return_code OUT VARCHAR2,
                                    x_return_msg  OUT VARCHAR2);

  PROCEDURE submit_res_download_apply(p_doc_id      IN NUMBER,
                                      p_apply_name  IN VARCHAR2,
                                      p_user_id     IN NUMBER,
                                      p_reason      IN VARCHAR2,
                                      p_remarks     IN VARCHAR2,
                                      p_data        IN VARCHAR2,
                                      x_return_code OUT VARCHAR2,
                                      x_return_msg  OUT VARCHAR2);

  PROCEDURE delete_res_download_apply(p_apply_id    IN NUMBER,
                                      x_return_code OUT VARCHAR2,
                                      x_return_msg  OUT VARCHAR2);

  PROCEDURE get_res_download_dids(x_return_code OUT VARCHAR2,
                                  x_return_msg  OUT VARCHAR2);

  PROCEDURE end_res_download_zip(p_apply_id    IN NUMBER,
                                 p_zip_flag    IN NUMBER,
                                 x_return_code OUT VARCHAR2,
                                 x_return_msg  OUT VARCHAR2);

  PROCEDURE delete_item_from_temp_shelf(p_rel_id      IN NUMBER,
                                        p_user_id     IN NUMBER,
                                        x_return_code OUT VARCHAR2,
                                        x_return_msg  OUT VARCHAR2);

  PROCEDURE res_browse_download_rec(p_doc_id          IN NUMBER,
                                    p_type            IN VARCHAR2,
                                    p_user_id         IN NUMBER DEFAULT -1,
                                    p_download_reason IN VARCHAR2 DEFAULT NULL,
                                    x_return_code     OUT VARCHAR2,
                                    x_return_msg      OUT VARCHAR2);

  PROCEDURE res_download_rec(p_apply_id    IN NUMBER,
                             x_return_code OUT VARCHAR2,
                             x_return_msg  OUT VARCHAR2);

  PROCEDURE get_curr_approver_info(p_apply_id      IN NUMBER,
                                   p_apply_type    IN VARCHAR2,
                                   x_return_code   OUT VARCHAR2,
                                   x_status_name   OUT VARCHAR2,
                                   x_approver_name OUT VARCHAR2,
                                   x_reject_reason OUT VARCHAR2);

  FUNCTION get_approve_to_position(p_his_id IN NUMBER) RETURN VARCHAR2;

  FUNCTION get_category_rel(p_parent_category_id IN NUMBER,
                            p_category_id        IN NUMBER) RETURN VARCHAR2;

  --获取每个分类上的资源数量
  FUNCTION get_category_res_count(p_lib_code      IN VARCHAR2,
                                  p_lib_type_code IN VARCHAR2,
                                  p_comp_code     IN VARCHAR2,
                                  p_rel_chain     IN VARCHAR2) RETURN NUMBER;
END cms_pub_platform_pkg;
/
CREATE OR REPLACE PACKAGE BODY cms_pub_platform_pkg IS

  PROCEDURE ass_res_download_apply_line(p_header_id   IN NUMBER,
                                        p_rel_id      IN NUMBER,
                                        p_user_id     IN NUMBER,
                                        x_return_code OUT VARCHAR2,
                                        x_return_msg  OUT VARCHAR2) IS
    l_res_download_apply_l cms_res_download_apply_l_t%ROWTYPE;
  BEGIN
    SELECT cms_res_download_apply_l_s.NEXTVAL
      INTO l_res_download_apply_l.line_id
      FROM dual;
    UPDATE cms_res_download_apply_h_t rdah
       SET rdah.comp_code = (SELECT d.comp_code
                               FROM cms_doc_ucm_rel_t dur, cms_doc_t d
                              WHERE dur.rel_id = p_rel_id
                                AND dur.doc_id = d.doc_id)
     WHERE rdah.comp_code IS NULL
       AND rdah.apply_id = p_header_id;
    l_res_download_apply_l.header_id             := p_header_id;
    l_res_download_apply_l.rel_id                := p_rel_id;
    l_res_download_apply_l.object_version_number := 1;
    l_res_download_apply_l.created_by            := p_user_id;
    l_res_download_apply_l.creation_date         := SYSDATE;
    l_res_download_apply_l.last_updated_by       := p_user_id;
    l_res_download_apply_l.last_update_date      := SYSDATE;
    INSERT INTO cms_res_download_apply_l_t VALUES l_res_download_apply_l;
    x_return_code := 'S';
  EXCEPTION
    WHEN OTHERS THEN
      x_return_code := 'E';
      x_return_msg  := SQLCODE || SQLERRM;
  END ass_res_download_apply_line;

  PROCEDURE submit_res_download_apply(p_doc_id      IN NUMBER,
                                      p_apply_name  IN VARCHAR2,
                                      p_user_id     IN NUMBER,
                                      p_reason      IN VARCHAR2,
                                      p_remarks     IN VARCHAR2,
                                      p_data        IN VARCHAR2,
                                      x_return_code OUT VARCHAR2,
                                      x_return_msg  OUT VARCHAR2) IS
  BEGIN
    save_res_download_apply(p_doc_id      => p_doc_id,
                            p_apply_name  => p_apply_name,
                            p_user_id     => p_user_id,
                            p_reason      => p_reason,
                            p_remarks     => p_remarks,
                            p_data        => p_data,
                            x_return_code => x_return_code,
                            x_return_msg  => x_return_msg);
    IF x_return_code = 'S' THEN
      cms_common_pkg.res_exp_download_apply_submit(p_apply_id    => x_return_msg,
                                                   p_proposer_id => p_user_id,
                                                   p_apply_type  => 'DOWNLOAD',
                                                   x_return_code => x_return_code,
                                                   x_return_msg  => x_return_msg);
    END IF;
  END submit_res_download_apply;

  PROCEDURE save_res_download_apply(p_doc_id      IN NUMBER,
                                    p_apply_name  IN VARCHAR2,
                                    p_user_id     IN NUMBER,
                                    p_reason      IN VARCHAR2,
                                    p_remarks     IN VARCHAR2,
                                    p_data        IN VARCHAR2,
                                    x_return_code OUT VARCHAR2,
                                    x_return_msg  OUT VARCHAR2) IS
    l_res_download_apply_h cms_res_download_apply_h_t%ROWTYPE;
    l_data                 VARCHAR2(4000) := p_data;
    l_temp_data            VARCHAR2(4000);
    l_ftp_path             VARCHAR2(480);
    l_rel_id_str           VARCHAR2(4000);
    l_index1               NUMBER;
    l_index2               NUMBER;
    l_return_code          VARCHAR2(40) := 'S';
    l_return_msg           VARCHAR2(480);
  BEGIN
    SELECT cms_res_download_apply_h_s.NEXTVAL
      INTO l_res_download_apply_h.apply_id
      FROM dual;
    l_res_download_apply_h.apply_name            := p_apply_name;
    l_res_download_apply_h.user_id               := p_user_id;
    l_res_download_apply_h.reason                := p_reason;
    l_res_download_apply_h.remarks               := p_remarks;
    l_res_download_apply_h.status                := 'TO_SUBMIT_APPLY';
    l_res_download_apply_h.submit_date           := SYSDATE;
    l_res_download_apply_h.object_version_number := 1;
    l_res_download_apply_h.created_by            := p_user_id;
    l_res_download_apply_h.creation_date         := SYSDATE;
    l_res_download_apply_h.last_updated_by       := p_user_id;
    l_res_download_apply_h.last_update_date      := SYSDATE;
    l_res_download_apply_h.attribute2            := '下载申请';
    INSERT INTO cms_res_download_apply_h_t VALUES l_res_download_apply_h;
  
    WHILE instr(l_data, ';') > 0 LOOP
      l_index1    := instr(l_data, ';');
      l_temp_data := substr(l_data, 0, l_index1 - 1);
      l_data      := substr(l_data, l_index1 + 1);
      l_ftp_path  := substr(l_temp_data, 0, instr(l_temp_data, ':') - 1);
      l_temp_data := substr(l_temp_data, instr(l_temp_data, ':') + 1) || ',';
      WHILE instr(l_temp_data, ',') > 0 LOOP
        l_index2     := instr(l_temp_data, ',');
        l_rel_id_str := substr(l_temp_data, 0, l_index2 - 1);
        l_temp_data  := substr(l_temp_data, l_index2 + 1);
        IF l_rel_id_str = 'ALL' THEN
          FOR a_res IN (SELECT dur.rel_id
                          FROM cms_doc_ucm_rel_t dur
                         WHERE dur.doc_id = p_doc_id
                           AND dur.ftp_path = l_ftp_path) LOOP
            ass_res_download_apply_line(p_header_id   => l_res_download_apply_h.apply_id,
                                        p_rel_id      => a_res.rel_id,
                                        p_user_id     => p_user_id,
                                        x_return_code => l_return_code,
                                        x_return_msg  => l_return_msg);
          END LOOP;
        ELSE
          ass_res_download_apply_line(p_header_id   => l_res_download_apply_h.apply_id,
                                      p_rel_id      => substr(l_rel_id_str,
                                                              instr(l_rel_id_str,
                                                                    '_') + 1),
                                      p_user_id     => p_user_id,
                                      x_return_code => l_return_code,
                                      x_return_msg  => l_return_msg);
        END IF;
      END LOOP;
      IF l_return_code <> 'S' THEN
        EXIT;
      END IF;
    END LOOP;
  
    x_return_code := 'S';
    x_return_msg  := l_res_download_apply_h.apply_id;
  EXCEPTION
    WHEN OTHERS THEN
      x_return_code := 'E';
      x_return_msg  := SQLCODE || SQLERRM;
  END save_res_download_apply;

  PROCEDURE delete_res_download_apply(p_apply_id    IN NUMBER,
                                      x_return_code OUT VARCHAR2,
                                      x_return_msg  OUT VARCHAR2) IS
  BEGIN
    DELETE FROM cms_res_download_apply_l_t l
     WHERE l.header_id = p_apply_id;
    DELETE FROM cms_res_download_apply_h_t h WHERE h.apply_id = p_apply_id;
    x_return_code := 'S';
  EXCEPTION
    WHEN OTHERS THEN
      x_return_code := 'E';
      x_return_msg  := SQLCODE || SQLERRM;
  END delete_res_download_apply;

  PROCEDURE ass_res_temp_shelf_line(p_header_id   IN NUMBER,
                                    p_user_id     IN NUMBER,
                                    p_rel_id      IN VARCHAR2,
                                    x_return_code OUT VARCHAR2,
                                    x_return_msg  OUT VARCHAR2) IS
    l_res_temp_shelf_pub_l cms_res_temp_shelf_pub_l_t%ROWTYPE;
    l_counter              NUMBER;
  BEGIN
    SELECT COUNT(1)
      INTO l_counter
      FROM cms_res_temp_shelf_pub_l_t l, cms_res_temp_shelf_pub_h_t h
     WHERE l.rel_id = p_rel_id
       AND l.shelf_header_id = h.shelf_header_id
       AND h.user_id = p_user_id;
    IF l_counter = 0 THEN
      SELECT cms_res_temp_shelf_pub_l_s.NEXTVAL
        INTO l_res_temp_shelf_pub_l.shlef_line_id
        FROM dual;
      l_res_temp_shelf_pub_l.shelf_header_id       := p_header_id;
      l_res_temp_shelf_pub_l.rel_id                := p_rel_id;
      l_res_temp_shelf_pub_l.object_version_number := 1;
      l_res_temp_shelf_pub_l.created_by            := p_user_id;
      l_res_temp_shelf_pub_l.creation_date         := SYSDATE;
      l_res_temp_shelf_pub_l.last_updated_by       := p_user_id;
      l_res_temp_shelf_pub_l.last_update_date      := SYSDATE;
      INSERT INTO cms_res_temp_shelf_pub_l_t VALUES l_res_temp_shelf_pub_l;
    END IF;
    x_return_code := 'S';
  EXCEPTION
    WHEN OTHERS THEN
      x_return_code := 'E';
      x_return_msg  := SQLCODE || SQLERRM;
  END ass_res_temp_shelf_line;

  PROCEDURE add_to_res_temp_shelf(p_doc_id       IN NUMBER,
                                  p_user_id      IN NUMBER,
                                  p_storage_type IN VARCHAR2,
                                  p_data         IN VARCHAR2,
                                  x_return_code  OUT VARCHAR2,
                                  x_return_msg   OUT VARCHAR2) IS
    l_index1               NUMBER;
    l_index2               NUMBER;
    l_data                 VARCHAR2(4000) := p_data;
    l_temp_data            VARCHAR2(4000);
    l_ftp_path             VARCHAR2(480);
    l_rel_id_str           VARCHAR2(4000);
    l_res_temp_shelf_pub_h cms_res_temp_shelf_pub_h_t%ROWTYPE;
    l_return_code          VARCHAR2(40) := 'S';
    l_return_msg           VARCHAR2(480);
  BEGIN
    SELECT cms_res_temp_shelf_pub_h_s.NEXTVAL
      INTO l_res_temp_shelf_pub_h.shelf_header_id
      FROM dual;
    SELECT d.comp_code
      INTO l_res_temp_shelf_pub_h.comp_code
      FROM cms_doc_t d
     WHERE d.doc_id = p_doc_id;
    l_res_temp_shelf_pub_h.doc_id                := p_doc_id;
    l_res_temp_shelf_pub_h.storage_type          := p_storage_type;
    l_res_temp_shelf_pub_h.user_id               := p_user_id;
    l_res_temp_shelf_pub_h.object_version_number := 1;
    l_res_temp_shelf_pub_h.created_by            := p_user_id;
    l_res_temp_shelf_pub_h.creation_date         := SYSDATE;
    l_res_temp_shelf_pub_h.last_updated_by       := p_user_id;
    l_res_temp_shelf_pub_h.last_update_date      := SYSDATE;
    INSERT INTO cms_res_temp_shelf_pub_h_t VALUES l_res_temp_shelf_pub_h;
  
    WHILE instr(l_data, ';') > 0 LOOP
      l_index1    := instr(l_data, ';');
      l_temp_data := substr(l_data, 0, l_index1 - 1);
      l_data      := substr(l_data, l_index1 + 1);
      l_ftp_path  := substr(l_temp_data, 0, instr(l_temp_data, ':') - 1);
      l_temp_data := substr(l_temp_data, instr(l_temp_data, ':') + 1) || ',';
      WHILE instr(l_temp_data, ',') > 0 LOOP
        l_index2     := instr(l_temp_data, ',');
        l_rel_id_str := substr(l_temp_data, 0, l_index2 - 1);
        l_temp_data  := substr(l_temp_data, l_index2 + 1);
        IF l_rel_id_str = 'ALL' THEN
          FOR a_res IN (SELECT dur.rel_id
                          FROM cms_doc_ucm_rel_t dur
                         WHERE dur.doc_id = p_doc_id
                           AND dur.ftp_path = l_ftp_path) LOOP
            ass_res_temp_shelf_line(p_header_id   => l_res_temp_shelf_pub_h.shelf_header_id,
                                    p_user_id     => p_user_id,
                                    p_rel_id      => a_res.rel_id,
                                    x_return_code => l_return_code,
                                    x_return_msg  => l_return_msg);
          END LOOP;
        ELSE
          ass_res_temp_shelf_line(p_header_id   => l_res_temp_shelf_pub_h.shelf_header_id,
                                  p_user_id     => p_user_id,
                                  p_rel_id      => substr(l_rel_id_str,
                                                          instr(l_rel_id_str,
                                                                '_') + 1),
                                  x_return_code => l_return_code,
                                  x_return_msg  => l_return_msg);
        END IF;
      END LOOP;
      IF l_return_code <> 'S' THEN
        EXIT;
      END IF;
    END LOOP;
    x_return_code := l_return_code;
    x_return_msg  := l_return_msg;
  EXCEPTION
    WHEN OTHERS THEN
      x_return_code := 'E';
      x_return_msg  := SQLCODE || SQLERRM;
  END add_to_res_temp_shelf;

  PROCEDURE get_res_download_dids(x_return_code OUT VARCHAR2,
                                  x_return_msg  OUT VARCHAR2) IS
    l_data        VARCHAR2(32767);
    l_batch_code  VARCHAR2(40);
    l_doc_id_temp NUMBER;
    l_apply_name  VARCHAR2(240);
  BEGIN
    --下载测试001#D123:U1,U2,U3;D234:U4,U5@下载测试002#D345:U6,U7,U8;D456:U9,U10@
    SELECT to_char(SYSDATE, 'yyyymmddHH24miss')
      INTO l_batch_code
      FROM dual;
    --先更新(提交)下载申请头表打包状态字段.
    FOR c_res IN (SELECT h.apply_id
                    FROM cms_res_download_apply_h_t h
                   WHERE (nvl(h.zip_flag, 0) = 0 OR h.zip_flag = 3)
                     FOR UPDATE NOWAIT) LOOP
      NULL;
    END LOOP;
    UPDATE cms_res_download_apply_h_t h
       SET h.zip_flag = 1, h.batch_code = l_batch_code
     WHERE (nvl(h.zip_flag, 0) = 0 OR h.zip_flag = 3);
    FOR a_res IN (SELECT h.apply_id, h.apply_name
                    FROM cms_res_download_apply_h_t h
                   WHERE h.zip_flag = 1
                     AND h.batch_code = l_batch_code) LOOP
      l_doc_id_temp := NULL;
      l_apply_name  := REPLACE(a_res.apply_name, '/', '') || '_' ||
                       a_res.apply_id;
      l_apply_name  := REPLACE(l_apply_name, ' ', '');
      l_data        := l_data || l_apply_name || '###';
      FOR b_res IN (SELECT dur.doc_id, rci.res_name, dur.ucm_did
                      FROM cms_res_download_apply_l_t l,
                           cms_doc_ucm_rel_t          dur,
                           cms_res_common_info_v      rci
                     WHERE l.header_id = a_res.apply_id
                       AND dur.rel_id = l.rel_id
                       AND dur.doc_id = rci.doc_id
                     ORDER BY dur.doc_id) LOOP
        IF nvl(l_doc_id_temp, -99) <> b_res.doc_id THEN
          IF l_doc_id_temp IS NOT NULL THEN
            l_data := substr(l_data, 0, length(l_data) - 3) || ';;;';
          END IF;
          l_doc_id_temp := b_res.doc_id;
          l_data        := l_data ||
                           REPLACE(REPLACE(b_res.res_name, '/', ''),
                                   ' ',
                                   '') || '_' || b_res.doc_id || ':::';
        END IF;
        l_data := l_data || b_res.ucm_did || ',,,';
      END LOOP;
      l_data := substr(l_data, 0, length(l_data) - 3) || '@@@';
      UPDATE cms_res_download_apply_h_t h
         SET h.zip_path = '/home/oracle/cms/pub_download/' || l_apply_name || '/' ||
                          l_apply_name || '.zip'
       WHERE h.apply_id = a_res.apply_id;
    END LOOP;
    x_return_code := 'S';
    x_return_msg  := substr(l_data, 0, length(l_data) - 3);
  EXCEPTION
    WHEN OTHERS THEN
      x_return_code := 'E';
      x_return_msg  := SQLCODE || SQLERRM;
  END get_res_download_dids;

  PROCEDURE end_res_download_zip(p_apply_id    IN NUMBER,
                                 p_zip_flag    IN NUMBER,
                                 x_return_code OUT VARCHAR2,
                                 x_return_msg  OUT VARCHAR2) IS
  BEGIN
    UPDATE cms_res_download_apply_h_t h
       SET h.zip_flag = p_zip_flag
     WHERE h.apply_id = p_apply_id;
    x_return_code := 'S';
  EXCEPTION
    WHEN OTHERS THEN
      x_return_code := 'E';
      x_return_msg  := SQLCODE || SQLERRM;
  END end_res_download_zip;

  PROCEDURE delete_item_from_temp_shelf(p_rel_id      IN NUMBER,
                                        p_user_id     IN NUMBER,
                                        x_return_code OUT VARCHAR2,
                                        x_return_msg  OUT VARCHAR2) IS
  BEGIN
    /*DELETE cms_res_download_apply_l_t l
    WHERE l.rel_id = p_rel_id
      AND l.header_id IN (SELECT h.apply_id
                            FROM cms_res_download_apply_h_t h
                           WHERE h.user_id = p_user_id);*/
    DELETE FROM cms_res_temp_shelf_pub_l_t l
     WHERE l.rel_id = p_rel_id
       AND l.shelf_header_id IN
           (SELECT t.shelf_header_id
              FROM cms_res_temp_shelf_pub_h_t t
             WHERE t.user_id = p_user_id);
    x_return_code := 'S';
  EXCEPTION
    WHEN OTHERS THEN
      x_return_code := 'E';
      x_return_msg  := SQLCODE || SQLERRM;
  END delete_item_from_temp_shelf;

  PROCEDURE res_browse_download_rec(p_doc_id          IN NUMBER,
                                    p_type            IN VARCHAR2,
                                    p_user_id         IN NUMBER DEFAULT -1,
                                    p_download_reason IN VARCHAR2 DEFAULT NULL,
                                    x_return_code     OUT VARCHAR2,
                                    x_return_msg      OUT VARCHAR2) IS
    l_res_browse_download cms_res_browse_download_t%ROWTYPE;
  BEGIN
    SELECT cms_res_browse_download_s.NEXTVAL
      INTO l_res_browse_download.bd_id
      FROM dual;
    l_res_browse_download.doc_id                := p_doc_id;
    l_res_browse_download.TYPE                  := p_type;
    l_res_browse_download.num                   := 1;
    l_res_browse_download.object_version_number := 1;
    l_res_browse_download.created_by            := p_user_id;
    l_res_browse_download.creation_date         := SYSDATE;
    l_res_browse_download.attribute1            := p_download_reason;
    l_res_browse_download.last_updated_by       := p_user_id;
    l_res_browse_download.last_update_date      := SYSDATE;
    INSERT INTO cms_res_browse_download_t VALUES l_res_browse_download;
    x_return_code := 'S';
  EXCEPTION
    WHEN OTHERS THEN
      x_return_code := 'E';
      x_return_msg  := SQLCODE || SQLERRM;
  END res_browse_download_rec;

  PROCEDURE res_download_rec(p_apply_id    IN NUMBER,
                             x_return_code OUT VARCHAR2,
                             x_return_msg  OUT VARCHAR2) IS
  BEGIN
    FOR a_res IN (SELECT DISTINCT dur.doc_id, rdal.created_by, rdah.reason
                    FROM cms_res_download_apply_h_t rdah,
                         cms_res_download_apply_l_t rdal,
                         cms_doc_ucm_rel_t          dur
                   WHERE rdal.header_id = p_apply_id
                     AND rdal.rel_id = dur.rel_id
                     AND rdah.apply_id = rdal.header_id) LOOP
      res_browse_download_rec(p_doc_id          => a_res.doc_id,
                              p_type            => 'DOWNLOAD',
                              p_user_id         => a_res.created_by,
                              p_download_reason => a_res.reason,
                              x_return_code     => x_return_code,
                              x_return_msg      => x_return_msg);
      IF x_return_code <> 'S' THEN
        EXIT;
      END IF;
    END LOOP;
  EXCEPTION
    WHEN OTHERS THEN
      NULL;
  END res_download_rec;

  PROCEDURE get_curr_approver_info(p_apply_id      IN NUMBER,
                                   p_apply_type    IN VARCHAR2,
                                   x_return_code   OUT VARCHAR2,
                                   x_status_name   OUT VARCHAR2,
                                   x_approver_name OUT VARCHAR2,
                                   x_reject_reason OUT VARCHAR2) IS
    l_role_type  VARCHAR2(100);
    l_status     VARCHAR2(100);
    l_comp_code  VARCHAR2(100);
    l_attribute1 VARCHAR2(1000);
  BEGIN
    IF p_apply_type = 'EXP' THEN
      l_role_type := 'WORK_PLATFORM';
      SELECT reah.status, reah.comp_code, sv.status_name, reah.attribute1
        INTO l_status, l_comp_code, x_status_name, l_attribute1
        FROM cms_res_exp_apply_h_t reah, cms_res_exp_status_role_v sv
       WHERE reah.apply_id = p_apply_id
         AND reah.status = sv.status;
    ELSIF p_apply_type = 'DOWNLOAD' THEN
      l_role_type := 'PUB_PLATFORM';
      SELECT rdah.status, rdah.comp_code, sv.status_name, rdah.attribute1
        INTO l_status, l_comp_code, x_status_name, l_attribute1
        FROM cms_res_download_apply_h_t rdah, cms_res_exp_status_role_v sv
       WHERE rdah.apply_id = p_apply_id
         AND rdah.status = sv.status;
    END IF;
    IF l_status = 'REJECTED_APPLY' THEN
      x_reject_reason := l_attribute1;
    ELSE
      x_reject_reason := NULL;
    END IF;
    FOR c IN (SELECT resr.status,
                     resr.role_code,
                     r.role_name,
                     r.role_type,
                     rurt.user_id,
                     u.diplay_name
                FROM cms_res_exp_status_role_v resr,
                     cms_role_t                r,
                     cms_role_user_rel_t       rurt,
                     cms_user_t                u
               WHERE resr.role_code = r.attribute1
                 AND r.enable_flag = 'Y'
                 AND r.role_id = rurt.role_id
                 AND rurt.user_id = u.user_id
                 AND resr.status = l_status
                 AND r.role_type = l_role_type
                 AND r.comp_code = l_comp_code) LOOP
      x_approver_name := x_approver_name || ',' || c.diplay_name;
    END LOOP;
    IF x_approver_name IS NOT NULL THEN
      x_approver_name := substr(x_approver_name, 2);
    END IF;
    x_return_code := 'S';
  EXCEPTION
    WHEN OTHERS THEN
      x_return_code   := 'E';
      x_status_name   := NULL;
      x_approver_name := NULL;
  END get_curr_approver_info;

  FUNCTION get_approve_to_position(p_his_id IN NUMBER) RETURN VARCHAR2
  
   IS
    l_apply_type    VARCHAR2(100);
    l_apply_id      NUMBER;
    l_result        VARCHAR2(100);
    l_action        VARCHAR2(100);
    l_status        VARCHAR2(100);
    l_status_name   VARCHAR2(100);
    l_creation_date DATE;
    l_next_flag     VARCHAR2(10);
  BEGIN
    SELECT his.apply_id, his.apply_type, his.action, his.creation_date
      INTO l_apply_id, l_apply_type, l_action, l_creation_date
      FROM cms_res_exp_approval_his_t his
     WHERE his.his_id = p_his_id;
    IF l_apply_type = 'EXP' THEN
      SELECT reah.status
        INTO l_status
        FROM cms_res_exp_apply_h_t reah
       WHERE reah.apply_id = l_apply_id;
    ELSIF l_apply_type = 'DOWNLOAD' THEN
      SELECT rdah.status, resr.status_name
        INTO l_status, l_status_name
        FROM cms_res_download_apply_h_t rdah,
             cms_res_exp_status_role_v  resr
       WHERE rdah.apply_id = l_apply_id
         AND rdah.status = resr.status;
    END IF;
    l_next_flag := NULL;
    FOR c IN (SELECT *
                FROM cms_res_exp_approval_his_t h
               WHERE h.apply_id = l_apply_id
                 AND h.apply_type = l_apply_type
               ORDER BY h.creation_date) LOOP
      IF c.creation_date > l_creation_date OR c.his_id > p_his_id THEN
        l_next_flag := 'Y';
      END IF;
    END LOOP;
    IF l_next_flag = 'Y' THEN
      IF l_action = '初审' THEN
        l_result := '待复审';
      ELSIF l_action = '复审' THEN
        l_result := '待终审';
      END IF;
    ELSIF (l_next_flag IS NULL OR l_next_flag <> 'Y') AND
          l_status NOT IN ('APPROVED_APPLY', 'REJECTED_APPLY') THEN
      l_result := l_status_name;
    ELSE
      IF l_status = 'APPROVED_APPLY' THEN
        l_result := '已批准';
      ELSIF l_status = 'REJECTED_APPLY' THEN
        l_result := '已拒绝';
      END IF;
    END IF;
    RETURN l_result;
  END get_approve_to_position;

  FUNCTION get_category_rel(p_parent_category_id IN NUMBER,
                            p_category_id        IN NUMBER) RETURN VARCHAR2 IS
  BEGIN
    FOR c IN (SELECT a.category_id
                FROM cms_category_t a
               START WITH a.category_id = p_parent_category_id
              CONNECT BY PRIOR a.category_id = a.parent_node_id
                     AND a.parent_node_type = 'CATEGORY') LOOP
      IF c.category_id = p_category_id THEN
        RETURN 'Y';
      END IF;
    END LOOP;
    RETURN 'N';
  EXCEPTION
    WHEN OTHERS THEN
      RETURN 'N';
  END get_category_rel;

  FUNCTION get_category_res_count(p_lib_code      IN VARCHAR2,
                                  p_lib_type_code IN VARCHAR2,
                                  p_comp_code     IN VARCHAR2,
                                  p_rel_chain     IN VARCHAR2) RETURN NUMBER IS
    l_count NUMBER := 0;
  BEGIN
    SELECT COUNT(1)
      INTO l_count
      FROM cms_doc_t d, cms_doc_category_rel_t dcr, cms_category_t ct
     WHERE d.lib_code = p_lib_code
       AND d.lib_type_code = p_lib_type_code
       AND d.comp_code = p_comp_code
       AND d.doc_id = dcr.doc_id
       AND dcr.category_id = ct.category_id
       AND ct.attribute5 LIKE '%' || p_rel_chain || '%'
       AND nvl(d.delete_flag, 'N') = 'N'
       AND d.status LIKE 'PUBED%';
    RETURN l_count;
  END get_category_res_count;
END cms_pub_platform_pkg;
/
