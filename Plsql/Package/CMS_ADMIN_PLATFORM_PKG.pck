CREATE OR REPLACE PACKAGE CMS_ADMIN_PLATFORM_PKG IS

  -- Author  : VICKY
  -- Created : 2014/8/19 15:29:10
  -- Purpose : 管理平台package包
  /************************************************************************
  *  PUBLIC FUNCTION                                                     *
  *       is_leaf_node                                                   *
  *                                                                      *
  *  DESCRIPTION                                                         *
  *       判断分类树上的节点是不是叶子节点                               *
  *  ARGUMENTS                                                           *
  *      --p_category_id    分类ID                                       *
  ************************************************************************/
  FUNCTION is_leaf_node(p_category_id IN NUMBER) RETURN VARCHAR2;

  /************************************************************************
  *  PUBLIC FUNCTION                                                     *
  *       delete_res_category                                            *
  *                                                                      *
  *  DESCRIPTION                                                         *
  *       删除分类树上的节点                                             *
  *  ARGUMENTS                                                           *
  *      --p_category_id    分类ID                                       *
  ************************************************************************/
  FUNCTION delete_res_category(p_cateogry_id IN VARCHAR2) RETURN VARCHAR2;

  /************************************************************************
  *  PUBLIC FUNCTION                                                    *
  *       add_res_category                                               *
  *                                                                      *
  *  DESCRIPTION                                                         *
  *       增加分类树节点                                                 *
  *  ARGUMENTS                                                           *
  *      --p_parent_node_id    父节点ID                                  *
  *      --p_category_name     分类名称                                  *
  *      --p_attribute1        扩展字段1                                 *
  *      --p_attribute2        扩展字段2                                 *
  *      --p_attribute3        扩展字段3                                 *
  ************************************************************************/
  FUNCTION add_res_category(p_parent_node_id IN VARCHAR2,
                            p_category_name  IN VARCHAR2,
                            p_attribute1     IN VARCHAR2,
                            p_attribute2     IN VARCHAR2,
                            p_attribute3     IN VARCHAR2) RETURN VARCHAR2;

  /************************************************************************
  *  PUBLIC FUNCTION                                                     *
  *       modify_res_category                                            *
  *                                                                      *
  *  DESCRIPTION                                                         *
  *       修改分类树节点                                                 *
  *  ARGUMENTS                                                           *
  *      --p_category_id       分类id                                    *
  *      --p_category_name     分类名称                                  *
  *      --p_attribute1        扩展字段1                                 *
  *      --p_attribute2        扩展字段2                                 *
  *      --p_attribute3        扩展字段3                                 *
  ************************************************************************/
  FUNCTION modify_res_category(p_category_id   IN VARCHAR2,
                               p_category_name IN VARCHAR2,
                               p_attribute1    IN VARCHAR2,
                               p_attribute2    IN VARCHAR2,
                               p_attribute3    IN VARCHAR2) RETURN VARCHAR2;

  /************************************************************************
  *  PUBLIC FUNCTION                                                     *
  *       delete_choice_category                                         *
  *                                                                      *
  *  DESCRIPTION                                                         *
  *       删除下列列表分类树上的节点                                             *
  *  ARGUMENTS                                                           *
  *      --p_category_id    分类ID                                       *
  ************************************************************************/
  FUNCTION delete_choice_category(p_cateogry_id IN VARCHAR2) RETURN VARCHAR2;

  /************************************************************************
  *  PUBLIC FUNCTION                                                     *
  *       add_choice_category                                            *
  *                                                                      *
  *  DESCRIPTION                                                         *
  *       增加下拉列表分类树节点                                         *
  *  ARGUMENTS                                                           *
  *      --p_parent_node_id    父节点ID                                  *
  *      --p_category_code     分类CODE                                  *
  *      --p_category_name     分类名称                                  *
  *      --p_category_type     分类类型                                  *
  *      --p_category_desc     分类描述                                  *
  ************************************************************************/
  FUNCTION add_choice_category(p_parent_node_id IN VARCHAR2,
                               p_category_code  IN VARCHAR2,
                               p_category_name  IN VARCHAR2,
                               p_category_type  IN VARCHAR2,
                               p_category_desc  IN VARCHAR2) RETURN VARCHAR2;

  /************************************************************************
  *  PUBLIC FUNCTION                                                     *
  *       modify_choice_category                                         *
  *                                                                      *
  *  DESCRIPTION                                                         *
  *       修改下拉列表分类树节点                                         *
  *  ARGUMENTS                                                           *
  *      --p_category_id       分类id                                    *
  *      --p_category_name     分类名称                                  *
  *      --p_category_desc     分类描述                                  *
  ************************************************************************/
  FUNCTION modify_choice_category(p_category_id   IN VARCHAR2,
                                  p_category_name IN VARCHAR2,
                                  p_category_desc IN VARCHAR2)
    RETURN VARCHAR2;

  /************************************************************************
  *  PUBLIC FUNCTION                                                     *
  *       get_periodical_published_date                                  *
  *                                                                      *
  *  DESCRIPTION                                                         *
  *       获取期刊或者报纸的发布日期                                     *
  ************************************************************************/
  FUNCTION get_periodical_published_date(p_category_id IN NUMBER,
                                         p_lib_code    IN VARCHAR2)
    RETURN VARCHAR2;

  /************************************************************************
  *  PUBLIC FUNCTION                                                     *
  *       save_periodical_setup                                          *
  *                                                                      *
  *  DESCRIPTION                                                         *
  *       保存期刊刊物设置                                               *
  ************************************************************************/
  FUNCTION save_periodical_setup(p_category_id     IN NUMBER,
                                 p_periodical_name IN VARCHAR2,
                                 p_periodical_type IN VARCHAR2,
                                 p_paper_type      IN VARCHAR2,
                                 p_lang            IN VARCHAR2,
                                 p_chief_editor    IN VARCHAR2,
                                 p_published_date  IN VARCHAR2,
                                 p_price           IN NUMBER,
                                 p_cn              IN VARCHAR2,
                                 p_issn            IN VARCHAR2,
                                 p_book_size       IN VARCHAR2,
                                 p_words           IN NUMBER,
                                 p_prod_size       IN VARCHAR2,
                                 p_reader_group    IN VARCHAR2,
                                 p_comp            IN VARCHAR2, --主办单位
                                 p_org             IN VARCHAR2,
                                 p_publishing      IN VARCHAR2,
                                 p_description     IN VARCHAR2,
                                 p_comp_code       IN VARCHAR2,
                                 p_user_id         IN NUMBER) RETURN VARCHAR2;

  /************************************************************************
  *  PUBLIC FUNCTION                                                     *
  *       delete_periodical_setup                                        *
  *                                                                      *
  *  DESCRIPTION                                                         *
  *       删除期刊刊物设置                                               *
  ************************************************************************/
  FUNCTION delete_periodical_setup(p_category_id IN NUMBER) RETURN VARCHAR2;

  /************************************************************************
  *  PUBLIC FUNCTION                                                     *
  *       delete_period_setup                                            *
  *                                                                      *
  *  DESCRIPTION                                                         *
  *       删除期刊刊期设置                                               *
  ************************************************************************/
  FUNCTION delete_period_setup(p_setup_id IN NUMBER) RETURN VARCHAR2;

  /************************************************************************
  *  PUBLIC FUNCTION                                                     *
  *       save_period_setup                                              *
  *                                                                      *
  *  DESCRIPTION                                                         *
  *       保存期刊刊期设置                                               *
  ************************************************************************/
  FUNCTION save_period_setup(p_mode           IN VARCHAR2,
                             p_setup_id       IN NUMBER,
                             p_category_id    IN NUMBER,
                             p_year           IN NUMBER,
                             p_period_num     IN NUMBER,
                             p_published_date IN DATE,
                             p_remarks        IN VARCHAR2,
                             p_user_id        IN NUMBER,
                             p_comp_code      IN VARCHAR2) RETURN VARCHAR2;

  /************************************************************************
  *  PUBLIC FUNCTION                                                     *
  *       save_newspaper_setup                                           *
  *                                                                      *
  *  DESCRIPTION                                                         *
  *       保存报纸刊物设置                                               *
  ************************************************************************/
  FUNCTION save_newspaper_setup(p_category_id    IN NUMBER,
                                p_newspaper_name IN VARCHAR2,
                                p_newspaper_type IN VARCHAR2,
                                p_paper_type     IN VARCHAR2,
                                p_lang           IN VARCHAR2,
                                p_chief_editor   IN VARCHAR2,
                                p_published_date IN VARCHAR2,
                                p_price          IN NUMBER,
                                p_cn             IN VARCHAR2,
                                p_issn           IN VARCHAR2,
                                p_book_size      IN VARCHAR2,
                                p_words          IN NUMBER,
                                p_prod_size      IN VARCHAR2,
                                p_reader_group   IN VARCHAR2,
                                p_comp           IN VARCHAR2, --主办单位
                                p_org            IN VARCHAR2,
                                p_publishing     IN VARCHAR2,
                                p_description    IN VARCHAR2,
                                p_comp_code      IN VARCHAR2,
                                p_user_id        IN NUMBER) RETURN VARCHAR2;

  /************************************************************************
  *  PUBLIC FUNCTION                                                     *
  *       delete_newspaper_setup                                         *
  *                                                                      *
  *  DESCRIPTION                                                         *
  *       删除报纸刊物设置                                               *
  ************************************************************************/
  FUNCTION delete_newspaper_setup(p_category_id IN NUMBER) RETURN VARCHAR2;

  /************************************************************************
  *  PUBLIC FUNCTION                                                     *
  *       delete_news_setup                                              *
  *                                                                      *
  *  DESCRIPTION                                                         *
  *       删除报纸刊期设置                                               *
  ************************************************************************/
  FUNCTION delete_news_setup(p_setup_id IN NUMBER) RETURN VARCHAR2;

  /************************************************************************
  *  PUBLIC FUNCTION                                                     *
  *       save_news_setup                                                *
  *                                                                      *
  *  DESCRIPTION                                                         *
  *       保存报纸刊期设置                                               *
  ************************************************************************/
  FUNCTION save_news_setup(p_mode           IN VARCHAR2,
                           p_setup_id       IN NUMBER,
                           p_category_id    IN NUMBER,
                           p_year           IN NUMBER,
                           p_news_num       IN NUMBER,
                           p_published_date IN DATE,
                           p_remarks        IN VARCHAR2,
                           p_user_id        IN NUMBER,
                           p_comp_code      IN VARCHAR2) RETURN VARCHAR2;

  --增加分社
  PROCEDURE add_comp(p_comp_code   IN VARCHAR2,
                     p_comp_name   IN VARCHAR2,
                     p_comp_desc   IN VARCHAR2,
                     p_group_flag  IN VARCHAR2,
                     p_user_id     IN NUMBER,
                     x_return_code OUT VARCHAR2,
                     x_return_msg  OUT VARCHAR2);

  --增加分社与库的对应关系
  PROCEDURE add_comp_lib_rel(p_comp_code IN VARCHAR2, p_user_id IN NUMBER);

  --增加库与分类的对应关系
  PROCEDURE add_lib_category_rel(p_comp_code IN VARCHAR2,
                                 p_user_id   IN NUMBER);

  --初始化分社的一些固化角色
  PROCEDURE init_comp_role(p_comp_code IN VARCHAR2, p_user_id IN NUMBER);

  --增加角色
  PROCEDURE add_comp_role(p_comp_code  IN VARCHAR2,
                          p_role_name  IN VARCHAR2,
                          p_role_type  IN VARCHAR2,
                          p_attribute1 IN VARCHAR2,
                          p_user_id    IN NUMBER);

  --修改分社
  PROCEDURE modify_comp(p_comp_code   IN VARCHAR2,
                        p_comp_name   IN VARCHAR2,
                        p_comp_desc   IN VARCHAR2,
                        p_group_flag  IN VARCHAR2,
                        p_user_id     IN NUMBER,
                        x_return_code OUT VARCHAR2,
                        x_return_msg  OUT VARCHAR2);

  --删除分社
  PROCEDURE delete_comp(p_comp_code   IN VARCHAR2,
                        x_return_code OUT VARCHAR2,
                        x_return_msg  OUT VARCHAR2);

  --获取第一个分社的CODE
  FUNCTION get_first_comp_code RETURN VARCHAR2;

  --当管理取消对某个管理员的库授权时，相应的收回平台管理员授出去的权限
  PROCEDURE revoke_privilege(p_comp_code     IN VARCHAR2,
                             p_lib_code      IN VARCHAR2,
                             p_platform_type IN VARCHAR2);
END CMS_ADMIN_PLATFORM_PKG;
/
CREATE OR REPLACE PACKAGE BODY CMS_ADMIN_PLATFORM_PKG IS
  FUNCTION is_leaf_node(p_category_id IN NUMBER) RETURN VARCHAR2 IS
    l_count NUMBER;
  BEGIN
    SELECT COUNT(*)
      INTO l_count
      FROM CMS_RES_CATEGORY_V v
     WHERE v.parent_node_id = p_category_id;
    IF l_count > 0 THEN
      RETURN 'N';
    END IF;
    RETURN 'Y';
  END is_leaf_node;

  FUNCTION delete_res_category(p_cateogry_id IN VARCHAR2) RETURN VARCHAR2 IS
    PRAGMA AUTONOMOUS_TRANSACTION;
  BEGIN
    UPDATE cms_doc_category_rel_t rt
       SET rt.category_id = -1
     WHERE rt.category_id IN
           (SELECT category_id
              FROM cms_category_t c
            CONNECT BY PRIOR c.category_id = c.parent_node_id
             START WITH c.category_id = p_cateogry_id);
    DELETE FROM cms_category_t t
     WHERE t.category_id IN
           (SELECT category_id
              FROM cms_category_t c
            CONNECT BY PRIOR c.category_id = c.parent_node_id
             START WITH c.category_id = p_cateogry_id);
    COMMIT;
    RETURN 'S';
  EXCEPTION
    WHEN OTHERS THEN
      ROLLBACK;
      RETURN SQLCODE || SQLERRM;
  END delete_res_category;

  FUNCTION add_res_category(p_parent_node_id IN VARCHAR2,
                            p_category_name  IN VARCHAR2,
                            p_attribute1     IN VARCHAR2,
                            p_attribute2     IN VARCHAR2,
                            p_attribute3     IN VARCHAR2) RETURN VARCHAR2 IS
    PRAGMA AUTONOMOUS_TRANSACTION;
    l_category_row     cms_category_t%ROWTYPE;
    l_parent_node_id   NUMBER := to_number(p_parent_node_id);
    l_next_category_id NUMBER := 0;
    l_max_category_id  NUMBER;
    l_id_str           VARCHAR2(240);
    l_parent_id_str    VARCHAR2(240);
    l_top_parent_id    NUMBER;
    l_seq              NUMBER := 0;
  BEGIN
    SELECT nvl(MAX(category_id), 0)
      INTO l_max_category_id
      FROM cms_category_t;
    LOOP
      SELECT cms_category_s.NEXTVAL INTO l_next_category_id FROM dual;
      EXIT WHEN l_next_category_id > l_max_category_id;
    END LOOP;
    l_id_str                             := '-' || l_next_category_id || '-';
    l_category_row.category_id           := l_next_category_id;
    l_category_row.category_name         := p_category_name;
    l_category_row.object_version_number := 1;
    l_category_row.created_by            := -1;
    l_category_row.creation_date         := SYSDATE;
    l_category_row.last_updated_by       := -1;
    l_category_row.last_update_date      := SYSDATE;
    l_category_row.attribute1            := p_attribute1;
    l_category_row.attribute2            := p_attribute2;
    l_category_row.attribute3            := p_attribute3;
    l_category_row.parent_node_id        := l_parent_node_id;
    IF l_parent_node_id < 0 THEN
      l_category_row.parent_node_type := 'CATEGORY_GROUP';
      l_top_parent_id                 := l_parent_node_id;
    ELSE
      l_category_row.parent_node_type := 'CATEGORY';
      l_top_parent_id                 := -cms_common_pkg.get_category_top_node_id(l_parent_node_id);
    END IF;
    FOR c IN (SELECT *
                FROM cms_category_t t
              CONNECT BY PRIOR t.parent_node_id = t.category_id
               START WITH t.category_id = l_parent_node_id) LOOP
      l_id_str        := l_id_str || '-' || c.category_id || '-';
      l_parent_id_str := l_parent_id_str || '-' || c.category_id || '-';
    END LOOP;
    l_id_str                  := l_id_str || '-' || l_top_parent_id || '-';
    l_category_row.attribute5 := l_id_str;
    l_parent_id_str           := l_parent_id_str || '-' || l_top_parent_id || '-';
    SELECT nvl(MAX(t.seq), 0) + 10
      INTO l_seq
      FROM cms_category_t t
     WHERE t.attribute5 LIKE '%' || l_parent_id_str || '%'
       AND t.parent_node_type = l_category_row.parent_node_type;
    l_category_row.seq := l_seq;
    INSERT INTO cms_category_t VALUES l_category_row;
    COMMIT;
    RETURN l_next_category_id;
  EXCEPTION
    WHEN OTHERS THEN
      ROLLBACK;
      RETURN SQLCODE || SQLERRM;
  END add_res_category;

  FUNCTION modify_res_category(p_category_id   IN VARCHAR2,
                               p_category_name IN VARCHAR2,
                               p_attribute1    IN VARCHAR2,
                               p_attribute2    IN VARCHAR2,
                               p_attribute3    IN VARCHAR2) RETURN VARCHAR2 IS
    PRAGMA AUTONOMOUS_TRANSACTION;
  BEGIN
    UPDATE cms_category_t t
       SET t.category_name = p_category_name,
           t.attribute1    = p_attribute1,
           t.attribute2    = p_attribute2,
           t.attribute3    = p_attribute3
     WHERE t.category_id = p_category_id;
    COMMIT;
    RETURN 'S';
  EXCEPTION
    WHEN OTHERS THEN
      ROLLBACK;
      RETURN SQLCODE || SQLERRM;
  END modify_res_category;

  FUNCTION delete_choice_category(p_cateogry_id IN VARCHAR2) RETURN VARCHAR2 IS
    PRAGMA AUTONOMOUS_TRANSACTION;
    l_category_id NUMBER := to_number(p_cateogry_id);
  BEGIN
    IF l_category_id < 0 THEN
      DELETE FROM cms_lookup_value_t lvt
       WHERE lvt.lookup_type_code =
             (SELECT ltt.lookup_type_code
                FROM cms_lookup_type_t ltt
               WHERE ltt.lookup_type_id = abs(l_category_id));
      DELETE FROM cms_lookup_type_t ltt
       WHERE ltt.lookup_type_id = abs(l_category_id);
    ELSE
      DELETE FROM cms_lookup_value_t lvt
       WHERE lvt.lookup_value_id = abs(l_category_id);
    END IF;
    COMMIT;
    RETURN 'S';
  EXCEPTION
    WHEN OTHERS THEN
      ROLLBACK;
      RETURN SQLCODE || SQLERRM;
  END delete_choice_category;

  FUNCTION add_choice_category(p_parent_node_id IN VARCHAR2,
                               p_category_code  IN VARCHAR2,
                               p_category_name  IN VARCHAR2,
                               p_category_type  IN VARCHAR2,
                               p_category_desc  IN VARCHAR2) RETURN VARCHAR2 IS
    PRAGMA AUTONOMOUS_TRANSACTION;
    l_lookup_type_row  cms_lookup_type_t%ROWTYPE;
    l_lookup_value_row cms_lookup_value_t%ROWTYPE;
    l_parent_node_id   NUMBER := to_number(p_parent_node_id);
    l_next_category_id NUMBER := 0;
    l_max_category_id  NUMBER;
    l_category_count   NUMBER := 0;
    l_lookup_type_code VARCHAR2(100);
    l_seq              NUMBER := 0;
  BEGIN
    IF l_parent_node_id <= -100000000 THEN
      SELECT COUNT(*)
        INTO l_category_count
        FROM cms_lookup_type_t t
       WHERE t.lookup_type_code = p_category_code;
      IF l_category_count > 0 THEN
        RETURN 'E该分类已经存在';
      END IF;
      SELECT nvl(MAX(lookup_type_id), 0)
        INTO l_max_category_id
        FROM cms_lookup_type_t;
      LOOP
        SELECT cms_lookup_type_s.NEXTVAL INTO l_next_category_id FROM dual;
        EXIT WHEN l_next_category_id > l_max_category_id;
      END LOOP;
      l_lookup_type_row.lookup_type_id        := l_next_category_id;
      l_lookup_type_row.lookup_type_code      := p_category_code;
      l_lookup_type_row.lookup_type_name      := p_category_name;
      l_lookup_type_row.lookup_type_desc      := p_category_desc;
      l_lookup_type_row.enable_flag           := 'Y';
      l_lookup_type_row.object_version_number := 1;
      l_lookup_type_row.created_by            := -1;
      l_lookup_type_row.creation_date         := SYSDATE;
      l_lookup_type_row.last_updated_by       := -1;
      l_lookup_type_row.last_update_date      := SYSDATE;
      l_lookup_type_row.attribute2            := p_category_type;
      l_lookup_type_row.seq                   := 10;
      INSERT INTO cms_lookup_type_t VALUES l_lookup_type_row;
      COMMIT;
      RETURN - l_next_category_id;
    ELSE
      SELECT COUNT(*)
        INTO l_category_count
        FROM cms_lookup_value_t t
       WHERE t.lookup_value_code = p_category_code;
      IF l_category_count > 0 THEN
        RETURN 'E该分类已经存在';
      END IF;
      SELECT t.lookup_type_code
        INTO l_lookup_type_code
        FROM cms_lookup_type_t t
       WHERE t.lookup_type_id = abs(l_parent_node_id);
      BEGIN
        SELECT nvl(MAX(t.seq), 0)
          INTO l_seq
          FROM cms_lookup_value_t t
         WHERE t.lookup_type_code = l_lookup_type_code;
      EXCEPTION
        WHEN OTHERS THEN
          l_seq := 10;
      END;
      SELECT nvl(MAX(lookup_value_id), 0)
        INTO l_max_category_id
        FROM cms_lookup_value_t;
      LOOP
        SELECT cms_lookup_value_s.NEXTVAL
          INTO l_next_category_id
          FROM dual;
        EXIT WHEN l_next_category_id > l_max_category_id;
      END LOOP;
      l_lookup_value_row.lookup_value_id       := l_next_category_id;
      l_lookup_value_row.lookup_value_code     := p_category_code;
      l_lookup_value_row.lookup_type_code      := l_lookup_type_code;
      l_lookup_value_row.lookup_value_desc     := p_category_desc;
      l_lookup_value_row.meaning               := p_category_name;
      l_lookup_value_row.enable_flag           := 'Y';
      l_lookup_value_row.object_version_number := 1;
      l_lookup_value_row.created_by            := -1;
      l_lookup_value_row.creation_date         := SYSDATE;
      l_lookup_value_row.last_updated_by       := -1;
      l_lookup_value_row.last_update_date      := SYSDATE;
      l_lookup_value_row.seq                   := l_seq;
      INSERT INTO cms_lookup_value_t VALUES l_lookup_value_row;
      COMMIT;
      RETURN l_next_category_id;
    END IF;
    RETURN NULL;
  EXCEPTION
    WHEN OTHERS THEN
      ROLLBACK;
      RETURN 'E' || SQLERRM;
  END add_choice_category;

  FUNCTION modify_choice_category(p_category_id   IN VARCHAR2,
                                  p_category_name IN VARCHAR2,
                                  p_category_desc IN VARCHAR2)
    RETURN VARCHAR2 IS
    PRAGMA AUTONOMOUS_TRANSACTION;
    l_category_id NUMBER := to_number(p_category_id);
  BEGIN
    IF l_category_id < 0 THEN
      UPDATE cms_lookup_type_t t
         SET t.lookup_type_name = p_category_name,
             t.lookup_type_desc = p_category_desc
       WHERE t.lookup_type_id = abs(p_category_id);
    ELSE
      UPDATE cms_lookup_value_t lvt
         SET lvt.meaning           = p_category_name,
             lvt.lookup_value_desc = p_category_desc
       WHERE lvt.lookup_value_id = abs(p_category_id);
    END IF;
    COMMIT;
    RETURN 'S';
  EXCEPTION
    WHEN OTHERS THEN
      ROLLBACK;
      RETURN SQLCODE || SQLERRM;
  END modify_choice_category;

  FUNCTION get_chinese_num(p_arabic_num IN VARCHAR2) RETURN VARCHAR2 IS
    l_result VARCHAR2(100);
  BEGIN
    IF p_arabic_num = '1' THEN
      l_result := '一';
    ELSIF p_arabic_num = '2' THEN
      l_result := '二';
    ELSIF p_arabic_num = '3' THEN
      l_result := '三';
    ELSIF p_arabic_num = '4' THEN
      l_result := '四';
    ELSIF p_arabic_num = '5' THEN
      l_result := '五';
    ELSIF p_arabic_num = '6' THEN
      l_result := '六';
    ELSIF p_arabic_num = '7' THEN
      l_result := '七';
    ELSIF p_arabic_num = '8' THEN
      l_result := '八';
    ELSIF p_arabic_num = '9' THEN
      l_result := '九';
    END IF;
    RETURN l_result;
  END get_chinese_num;

  FUNCTION get_periodical_published_date(p_category_id IN NUMBER,
                                         p_lib_code    IN VARCHAR2)
    RETURN VARCHAR2 IS
    l_periodical_type VARCHAR2(100);
    l_attribute1      VARCHAR2(100);
    l_result          VARCHAR2(1000);
    l_old_even        VARCHAR2(10);
  BEGIN
    IF p_lib_code = 'PERIODICAL' THEN
      SELECT t.periodical_type, t.attribute1
        INTO l_periodical_type, l_attribute1
        FROM cms_periodical_setup_t t
       WHERE t.periodical_category_id = p_category_id;
    ELSIF p_lib_code = 'NEWSPAPER' THEN
      SELECT t.newspaper_type, t.attribute1
        INTO l_periodical_type, l_attribute1
        FROM cms_newspaper_setup_t t
       WHERE t.newspaper_category_id = p_category_id;
    END IF;
    IF l_attribute1 IS NULL THEN
      RETURN NULL;
    END IF;
    IF l_periodical_type = 'WEEKLY1' THEN
      l_result := '每周' || get_chinese_num(l_attribute1);
    ELSIF l_periodical_type = 'BIWEEKLY' THEN
      l_old_even := substr(l_attribute1, 0, instr(l_attribute1, ',', 1) - 1);
      IF l_old_even = '1' THEN
        l_result := '奇数周的周' ||
                    get_chinese_num(substr(l_attribute1,
                                           instr(l_attribute1, ',', 1) + 1,
                                           instr(l_attribute1, ',', 2)));
      ELSE
        l_result := '偶数周的周' ||
                    get_chinese_num(substr(l_attribute1,
                                           instr(l_attribute1, ',', 1) + 1,
                                           instr(l_attribute1, ',', 2)));
      END IF;
    
    ELSIF l_periodical_type = 'MONTHLY' THEN
      l_result := '每月的' || l_attribute1 || '日';
    ELSIF l_periodical_type = 'SEMIMONTHLY' THEN
      l_result := '每月的' ||
                  substr(l_attribute1, 0, instr(l_attribute1, ',', 1) - 1) || '日、' ||
                  substr(l_attribute1,
                         instr(l_attribute1, ',', 1) + 1,
                         instr(l_attribute1, ',', 2)) || '日';
    ELSIF l_periodical_type = 'BIMONTHLY' THEN
      l_old_even := substr(l_attribute1, 0, instr(l_attribute1, ',', 1) - 1);
      IF l_old_even = '1' THEN
        l_result := '奇数月的' || substr(l_attribute1,
                                     instr(l_attribute1, ',', 1) + 1,
                                     instr(l_attribute1, ',', 2)) || '日';
      ELSE
        l_result := '偶数月的' || substr(l_attribute1,
                                     instr(l_attribute1, ',', 1) + 1,
                                     instr(l_attribute1, ',', 2)) || '日';
      END IF;
    ELSIF l_periodical_type = 'TEN_DAYS' THEN
      l_result := '每月的' ||
                  substr(l_attribute1, 0, instr(l_attribute1, ',', 1) - 1) || '日、' ||
                  substr(l_attribute1,
                         instr(l_attribute1, ',', 1) + 1,
                         instr(l_attribute1, ',', 2)) || '日、' ||
                  substr(l_attribute1,
                         instr(l_attribute1, ',', 3) + 1,
                         length(l_attribute1)) || '日';
    ELSIF l_periodical_type = 'QUARTERLY' THEN
      l_result := '每个季度第' ||
                  substr(l_attribute1, 0, instr(l_attribute1, ',', 1) - 1) ||
                  '个月的' || substr(l_attribute1,
                                  instr(l_attribute1, ',', 1) + 1,
                                  instr(l_attribute1, ',', 2)) || '日';
    ELSIF l_periodical_type = 'WEEKLY2' THEN
      l_result := '每周';
      FOR c IN (SELECT *
                  FROM TABLE(CAST(cms_common_pkg.parse_string(l_attribute1) AS
                                  cms_table_type))) LOOP
        l_result := l_result || get_chinese_num(c.column_value) || '、';
      END LOOP;
      l_result := substr(l_result, 0, length(l_result) - 1);
    END IF;
    RETURN l_result;
  EXCEPTION
    WHEN OTHERS THEN
      RETURN SQLERRM;
  END get_periodical_published_date;

  FUNCTION save_periodical_setup(p_category_id     IN NUMBER,
                                 p_periodical_name IN VARCHAR2,
                                 p_periodical_type IN VARCHAR2,
                                 p_paper_type      IN VARCHAR2,
                                 p_lang            IN VARCHAR2,
                                 p_chief_editor    IN VARCHAR2,
                                 p_published_date  IN VARCHAR2,
                                 p_price           IN NUMBER,
                                 p_cn              IN VARCHAR2,
                                 p_issn            IN VARCHAR2,
                                 p_book_size       IN VARCHAR2,
                                 p_words           IN NUMBER,
                                 p_prod_size       IN VARCHAR2,
                                 p_reader_group    IN VARCHAR2,
                                 p_comp            IN VARCHAR2, --主办单位
                                 p_org             IN VARCHAR2,
                                 p_publishing      IN VARCHAR2,
                                 p_description     IN VARCHAR2,
                                 p_comp_code       IN VARCHAR2,
                                 p_user_id         IN NUMBER) RETURN VARCHAR2 IS
    l_category_row         cms_category_t%ROWTYPE;
    l_periodical_setup_row cms_periodical_setup_t%ROWTYPE;
    l_next_category_id     NUMBER := 0;
    l_max_category_id      NUMBER;
    l_next_setup_id        NUMBER := 0;
    l_max_setup_id         NUMBER;
    l_parent_node_id       NUMBER;
  BEGIN
    --p_category_id为-99则代表新建，否则为修改
    IF p_category_id = -99 THEN
      SELECT nvl(MAX(category_id), 0)
        INTO l_max_category_id
        FROM cms_category_t;
      LOOP
        SELECT cms_category_s.NEXTVAL INTO l_next_category_id FROM dual;
        EXIT WHEN l_next_category_id > l_max_category_id;
      END LOOP;
      SELECT nvl(MAX(setup_id), 0)
        INTO l_max_setup_id
        FROM cms_periodical_setup_t;
      LOOP
        SELECT cms_periodical_setup_s.NEXTVAL
          INTO l_next_setup_id
          FROM dual;
        EXIT WHEN l_next_setup_id > l_max_setup_id;
      END LOOP;
      SELECT t.rel_id
        INTO l_parent_node_id
        FROM cms_lib_category_g_rel_t t
       WHERE t.lib_code = 'PERIODICAL'
         AND t.comp_code = p_comp_code;
      l_category_row.category_id           := l_next_category_id;
      l_category_row.category_name         := p_periodical_name;
      l_category_row.parent_node_id        := -l_parent_node_id;
      l_category_row.parent_node_type      := 'CATEGORY_GROUP';
      l_category_row.object_version_number := 1;
      l_category_row.created_by            := p_user_id;
      l_category_row.creation_date         := SYSDATE;
      l_category_row.last_updated_by       := p_user_id;
      l_category_row.last_update_date      := SYSDATE;
      INSERT INTO cms_category_t VALUES l_category_row;
      l_periodical_setup_row.setup_id               := l_next_setup_id;
      l_periodical_setup_row.periodical_category_id := l_next_category_id;
      l_periodical_setup_row.periodical_type        := p_periodical_type;
      l_periodical_setup_row.paper_type             := p_paper_type;
      l_periodical_setup_row.chief_editor           := p_chief_editor;
      l_periodical_setup_row.price                  := p_price;
      l_periodical_setup_row.book_size              := p_book_size;
      l_periodical_setup_row.cn                     := p_cn;
      l_periodical_setup_row.issn                   := p_issn;
      l_periodical_setup_row.words                  := p_words;
      l_periodical_setup_row.prod_size              := p_prod_size;
      l_periodical_setup_row.comp_code              := p_comp_code;
      l_periodical_setup_row.reader_group           := p_reader_group;
      l_periodical_setup_row.org                    := p_org;
      l_periodical_setup_row.publishing             := p_publishing;
      l_periodical_setup_row.description            := p_description;
      l_periodical_setup_row.lang                   := p_lang;
      l_periodical_setup_row.attribute1             := p_published_date; --发行日期
      l_periodical_setup_row.attribute2             := p_comp; --主管单位
      l_periodical_setup_row.object_version_number  := 1;
      l_periodical_setup_row.created_by             := p_user_id;
      l_periodical_setup_row.creation_date          := SYSDATE;
      l_periodical_setup_row.last_updated_by        := p_user_id;
      l_periodical_setup_row.last_update_date       := SYSDATE;
      INSERT INTO cms_periodical_setup_t VALUES l_periodical_setup_row;
    ELSE
      UPDATE cms_category_t c
         SET c.category_name = p_periodical_name
       WHERE c.category_id = p_category_id;
      UPDATE cms_periodical_setup_t s
         SET s.periodical_type  = p_periodical_type,
             s.paper_type       = p_paper_type,
             s.chief_editor     = p_chief_editor,
             s.price            = p_price,
             s.book_size        = p_book_size,
             s.cn               = p_cn,
             s.issn             = p_issn,
             s.words            = p_words,
             s.prod_size        = p_prod_size,
             s.comp_code        = p_comp_code,
             s.reader_group     = p_reader_group,
             s.org              = p_org,
             s.publishing       = p_publishing,
             s.description      = p_description,
             s.lang             = p_lang,
             s.attribute1       = nvl(p_published_date, s.attribute1), --发行日期
             s.attribute2       = p_comp,
             s.creation_date    = SYSDATE,
             s.last_update_date = SYSDATE,
             s.created_by       = p_user_id,
             s.last_updated_by  = p_user_id
       WHERE s.periodical_category_id = p_category_id;
    END IF;
    RETURN 'S';
  EXCEPTION
    WHEN OTHERS THEN
      RETURN SQLERRM;
  END save_periodical_setup;

  FUNCTION delete_periodical_setup(p_category_id IN NUMBER) RETURN VARCHAR2 IS
  BEGIN
    DELETE FROM cms_periodical_setup_t t
     WHERE t.periodical_category_id = p_category_id;
    DELETE FROM cms_category_t t WHERE t.category_id = p_category_id;
    RETURN 'S';
  EXCEPTION
    WHEN OTHERS THEN
      RETURN SQLERRM;
  END delete_periodical_setup;

  FUNCTION delete_period_setup(p_setup_id IN NUMBER) RETURN VARCHAR2 IS
  BEGIN
    DELETE FROM cms_period_setup_t t WHERE t.setup_id = p_setup_id;
    RETURN 'S';
  EXCEPTION
    WHEN OTHERS THEN
      RETURN SQLERRM;
  END delete_period_setup;

  FUNCTION save_period_setup(p_mode           IN VARCHAR2,
                             p_setup_id       IN NUMBER,
                             p_category_id    IN NUMBER,
                             p_year           IN NUMBER,
                             p_period_num     IN NUMBER,
                             p_published_date IN DATE,
                             p_remarks        IN VARCHAR2,
                             p_user_id        IN NUMBER,
                             p_comp_code      IN VARCHAR2) RETURN VARCHAR2 IS
    l_next_setup_id    NUMBER := 0;
    l_max_setup_id     NUMBER;
    l_period_setup_row cms_period_setup_t%ROWTYPE;
    l_count            NUMBER;
  BEGIN
    IF p_mode = 'NEW' THEN
      SELECT COUNT(*)
        INTO l_count
        FROM cms_period_setup_t       t,
             cms_category_t           ct,
             cms_lib_category_g_rel_t rt
       WHERE t.periodical_category_id = p_category_id
         AND t.periodical_category_id = ct.category_id
         AND cms_common_pkg.get_category_top_node_id(ct.category_id) =
             rt.rel_id
         AND rt.comp_code = p_comp_code
         AND t.YEAR = p_year
         AND t.period_num = p_period_num;
      IF l_count > 0 THEN
        RETURN '该刊期已存在';
      END IF;
      SELECT nvl(MAX(setup_id), 0)
        INTO l_max_setup_id
        FROM cms_period_setup_t;
      LOOP
        SELECT cms_period_setup_s.NEXTVAL INTO l_next_setup_id FROM dual;
        EXIT WHEN l_next_setup_id > l_max_setup_id;
      END LOOP;
      l_period_setup_row.setup_id               := l_next_setup_id;
      l_period_setup_row.periodical_category_id := p_category_id;
      l_period_setup_row.YEAR                   := p_year;
      l_period_setup_row.period_num             := p_period_num;
      l_period_setup_row.public_date            := p_published_date;
      l_period_setup_row.remarks                := p_remarks;
      l_period_setup_row.object_version_number  := 1;
      l_period_setup_row.created_by             := p_user_id;
      l_period_setup_row.creation_date          := SYSDATE;
      l_period_setup_row.last_updated_by        := p_user_id;
      l_period_setup_row.last_update_date       := SYSDATE;
      INSERT INTO cms_period_setup_t VALUES l_period_setup_row;
    ELSIF p_mode = 'EDIT' THEN
      SELECT COUNT(*)
        INTO l_count
        FROM cms_period_setup_t       t,
             cms_category_t           ct,
             cms_lib_category_g_rel_t rt
       WHERE t.periodical_category_id = p_category_id
         AND t.periodical_category_id = ct.category_id
         AND cms_common_pkg.get_category_top_node_id(ct.category_id) =
             rt.rel_id
         AND rt.comp_code = p_comp_code
         AND t.YEAR = p_year
         AND t.period_num = p_period_num
         AND t.setup_id <> p_setup_id;
      IF l_count > 0 THEN
        RETURN '该刊期已存在';
      END IF;
      UPDATE cms_period_setup_t t
         SET t.period_num  = p_period_num,
             t.public_date = p_published_date,
             t.remarks     = p_remarks
       WHERE t.setup_id = p_setup_id;
    END IF;
    RETURN 'S';
  EXCEPTION
    WHEN OTHERS THEN
      RETURN SQLERRM;
  END save_period_setup;

  FUNCTION save_newspaper_setup(p_category_id    IN NUMBER,
                                p_newspaper_name IN VARCHAR2,
                                p_newspaper_type IN VARCHAR2,
                                p_paper_type     IN VARCHAR2,
                                p_lang           IN VARCHAR2,
                                p_chief_editor   IN VARCHAR2,
                                p_published_date IN VARCHAR2,
                                p_price          IN NUMBER,
                                p_cn             IN VARCHAR2,
                                p_issn           IN VARCHAR2,
                                p_book_size      IN VARCHAR2,
                                p_words          IN NUMBER,
                                p_prod_size      IN VARCHAR2,
                                p_reader_group   IN VARCHAR2,
                                p_comp           IN VARCHAR2, --主办单位
                                p_org            IN VARCHAR2,
                                p_publishing     IN VARCHAR2,
                                p_description    IN VARCHAR2,
                                p_comp_code      IN VARCHAR2,
                                p_user_id        IN NUMBER) RETURN VARCHAR2 IS
    l_category_row        cms_category_t%ROWTYPE;
    l_newspaper_setup_row cms_newspaper_setup_t%ROWTYPE;
    l_next_category_id    NUMBER := 0;
    l_max_category_id     NUMBER;
    l_next_setup_id       NUMBER := 0;
    l_max_setup_id        NUMBER;
    l_parent_node_id      NUMBER;
  BEGIN
    --p_category_id为-99则代表新建，否则为修改
    IF p_category_id = -99 THEN
      SELECT nvl(MAX(category_id), 0)
        INTO l_max_category_id
        FROM cms_category_t;
      LOOP
        SELECT cms_category_s.NEXTVAL INTO l_next_category_id FROM dual;
        EXIT WHEN l_next_category_id > l_max_category_id;
      END LOOP;
      SELECT nvl(MAX(setup_id), 0)
        INTO l_max_setup_id
        FROM cms_newspaper_setup_t;
      LOOP
        SELECT cms_newspaper_setup_s.NEXTVAL
          INTO l_next_setup_id
          FROM dual;
        EXIT WHEN l_next_setup_id > l_max_setup_id;
      END LOOP;
      SELECT t.rel_id
        INTO l_parent_node_id
        FROM cms_lib_category_g_rel_t t
       WHERE t.lib_code = 'NEWSPAPER'
         AND t.comp_code = p_comp_code;
      l_category_row.category_id           := l_next_category_id;
      l_category_row.category_name         := p_newspaper_name;
      l_category_row.parent_node_id        := -l_parent_node_id;
      l_category_row.parent_node_type      := 'CATEGORY_GROUP';
      l_category_row.object_version_number := 1;
      l_category_row.created_by            := p_user_id;
      l_category_row.creation_date         := SYSDATE;
      l_category_row.last_updated_by       := p_user_id;
      l_category_row.last_update_date      := SYSDATE;
      INSERT INTO cms_category_t VALUES l_category_row;
      l_newspaper_setup_row.setup_id              := l_next_setup_id;
      l_newspaper_setup_row.newspaper_category_id := l_next_category_id;
      l_newspaper_setup_row.newspaper_type        := p_newspaper_type;
      l_newspaper_setup_row.paper_type            := p_paper_type;
      l_newspaper_setup_row.chief_editor          := p_chief_editor;
      l_newspaper_setup_row.price                 := p_price;
      l_newspaper_setup_row.book_size             := p_book_size;
      l_newspaper_setup_row.cn                    := p_cn;
      l_newspaper_setup_row.issn                  := p_issn;
      l_newspaper_setup_row.words                 := p_words;
      l_newspaper_setup_row.prod_size             := p_prod_size;
      l_newspaper_setup_row.comp_code             := p_comp_code;
      l_newspaper_setup_row.reader_group          := p_reader_group;
      l_newspaper_setup_row.org                   := p_org;
      l_newspaper_setup_row.publishing            := p_publishing;
      l_newspaper_setup_row.description           := p_description;
      l_newspaper_setup_row.lang                  := p_lang;
      l_newspaper_setup_row.attribute1            := p_published_date; --发行日期
      l_newspaper_setup_row.attribute2            := p_comp;
      l_newspaper_setup_row.object_version_number := 1;
      l_newspaper_setup_row.created_by            := p_user_id;
      l_newspaper_setup_row.creation_date         := SYSDATE;
      l_newspaper_setup_row.last_updated_by       := p_user_id;
      l_newspaper_setup_row.last_update_date      := SYSDATE;
      INSERT INTO cms_newspaper_setup_t VALUES l_newspaper_setup_row;
    ELSE
      UPDATE cms_category_t c
         SET c.category_name = p_newspaper_name
       WHERE c.category_id = p_category_id;
      UPDATE cms_newspaper_setup_t s
         SET s.newspaper_type   = p_newspaper_type,
             s.paper_type       = p_paper_type,
             s.chief_editor     = p_chief_editor,
             s.price            = p_price,
             s.book_size        = p_book_size,
             s.cn               = p_cn,
             s.issn             = p_issn,
             s.words            = p_words,
             s.prod_size        = p_prod_size,
             s.comp_code        = p_comp_code,
             s.reader_group     = p_reader_group,
             s.org              = p_org,
             s.publishing       = p_publishing,
             s.description      = p_description,
             s.lang             = p_lang,
             s.attribute1       = nvl(p_published_date, s.attribute1), --发行日期
             s.attribute2       = p_comp,
             s.creation_date    = SYSDATE,
             s.last_update_date = SYSDATE,
             s.created_by       = p_user_id,
             s.last_updated_by  = p_user_id
       WHERE s.newspaper_category_id = p_category_id;
    END IF;
    RETURN 'S';
  EXCEPTION
    WHEN OTHERS THEN
      RETURN SQLERRM;
  END save_newspaper_setup;

  FUNCTION delete_newspaper_setup(p_category_id IN NUMBER) RETURN VARCHAR2 IS
  BEGIN
    DELETE FROM cms_newspaper_setup_t t
     WHERE t.newspaper_category_id = p_category_id;
    DELETE FROM cms_category_t t WHERE t.category_id = p_category_id;
    RETURN 'S';
  EXCEPTION
    WHEN OTHERS THEN
      RETURN SQLERRM;
  END delete_newspaper_setup;

  FUNCTION delete_news_setup(p_setup_id IN NUMBER) RETURN VARCHAR2 IS
  BEGIN
    DELETE FROM cms_news_setup_t t WHERE t.setup_id = p_setup_id;
    RETURN 'S';
  EXCEPTION
    WHEN OTHERS THEN
      RETURN SQLERRM;
  END delete_news_setup;

  FUNCTION save_news_setup(p_mode           IN VARCHAR2,
                           p_setup_id       IN NUMBER,
                           p_category_id    IN NUMBER,
                           p_year           IN NUMBER,
                           p_news_num       IN NUMBER,
                           p_published_date IN DATE,
                           p_remarks        IN VARCHAR2,
                           p_user_id        IN NUMBER,
                           p_comp_code      IN VARCHAR2) RETURN VARCHAR2 IS
    l_next_setup_id  NUMBER := 0;
    l_max_setup_id   NUMBER;
    l_news_setup_row cms_news_setup_t%ROWTYPE;
    l_count          NUMBER;
  BEGIN
    IF p_mode = 'NEW' THEN
      SELECT COUNT(*)
        INTO l_count
        FROM cms_news_setup_t         t,
             cms_category_t           ct,
             cms_lib_category_g_rel_t rt
       WHERE t.newspaper_category_id = p_category_id
         AND t.newspaper_category_id = ct.category_id
         AND cms_common_pkg.get_category_top_node_id(ct.category_id) =
             rt.rel_id
         AND rt.comp_code = p_comp_code
         AND t.YEAR = p_year
         AND t.news_num = p_news_num;
      IF l_count > 0 THEN
        RETURN '该刊期已存在';
      END IF;
      SELECT nvl(MAX(setup_id), 0)
        INTO l_max_setup_id
        FROM cms_period_setup_t;
      LOOP
        SELECT cms_news_setup_s.NEXTVAL INTO l_next_setup_id FROM dual;
        EXIT WHEN l_next_setup_id > l_max_setup_id;
      END LOOP;
      l_news_setup_row.setup_id              := l_next_setup_id;
      l_news_setup_row.newspaper_category_id := p_category_id;
      l_news_setup_row.YEAR                  := p_year;
      l_news_setup_row.news_num              := p_news_num;
      l_news_setup_row.public_date           := p_published_date;
      l_news_setup_row.remarks               := p_remarks;
      l_news_setup_row.object_version_number := 1;
      l_news_setup_row.created_by            := p_user_id;
      l_news_setup_row.creation_date         := SYSDATE;
      l_news_setup_row.last_updated_by       := p_user_id;
      l_news_setup_row.last_update_date      := SYSDATE;
      INSERT INTO cms_news_setup_t VALUES l_news_setup_row;
    ELSIF p_mode = 'EDIT' THEN
      SELECT COUNT(*)
        INTO l_count
        FROM cms_news_setup_t         t,
             cms_category_t           ct,
             cms_lib_category_g_rel_t rt
       WHERE t.newspaper_category_id = p_category_id
         AND t.YEAR = p_year
         AND t.newspaper_category_id = ct.category_id
         AND cms_common_pkg.get_category_top_node_id(ct.category_id) =
             rt.rel_id
         AND rt.comp_code = p_comp_code
         AND t.news_num = p_news_num
         AND t.setup_id <> p_setup_id;
      IF l_count > 0 THEN
        RETURN '该刊期已存在';
      END IF;
      UPDATE cms_news_setup_t t
         SET t.news_num    = p_news_num,
             t.public_date = p_published_date,
             t.remarks     = p_remarks
       WHERE t.setup_id = p_setup_id;
    END IF;
    RETURN 'S';
  EXCEPTION
    WHEN OTHERS THEN
      RETURN SQLERRM;
  END save_news_setup;

  PROCEDURE add_comp(p_comp_code   IN VARCHAR2,
                     p_comp_name   IN VARCHAR2,
                     p_comp_desc   IN VARCHAR2,
                     p_group_flag  IN VARCHAR2,
                     p_user_id     IN NUMBER,
                     x_return_code OUT VARCHAR2,
                     x_return_msg  OUT VARCHAR2) IS
    l_lookup_value_row     cms_lookup_value_t%ROWTYPE;
    l_next_lookup_value_id NUMBER := 0;
    l_max_lookup_value_id  NUMBER;
    l_seq                  NUMBER;
    l_comp_count           NUMBER;
  BEGIN
    SELECT COUNT(*)
      INTO l_comp_count
      FROM cms_lookup_value_t t
     WHERE (t.lookup_value_code = p_comp_code OR t.meaning = p_comp_name)
       AND t.lookup_type_code = 'COMP';
    IF l_comp_count > 0 THEN
      x_return_code := 'E';
      x_return_msg  := '该分社已经存在';
      RETURN;
    END IF;
    SELECT nvl(MAX(lookup_value_id), 0)
      INTO l_max_lookup_value_id
      FROM cms_lookup_value_t;
    LOOP
      SELECT cms_lookup_value_s.NEXTVAL
        INTO l_next_lookup_value_id
        FROM dual;
      EXIT WHEN l_next_lookup_value_id > l_max_lookup_value_id;
    END LOOP;
    BEGIN
      SELECT nvl(MAX(seq), 0)
        INTO l_seq
        FROM cms_lookup_value_t t
       WHERE t.lookup_type_code = 'COMP';
    EXCEPTION
      WHEN OTHERS THEN
        l_seq := 10;
    END;
    l_lookup_value_row.lookup_value_id       := l_next_lookup_value_id;
    l_lookup_value_row.lookup_value_code     := p_comp_code;
    l_lookup_value_row.lookup_type_code      := 'COMP';
    l_lookup_value_row.meaning               := p_comp_name;
    l_lookup_value_row.lookup_value_desc     := p_comp_desc;
    l_lookup_value_row.enable_flag           := 'Y';
    l_lookup_value_row.object_version_number := 1;
    l_lookup_value_row.created_by            := p_user_id;
    l_lookup_value_row.creation_date         := SYSDATE;
    l_lookup_value_row.last_updated_by       := p_user_id;
    l_lookup_value_row.last_update_date      := SYSDATE;
    l_lookup_value_row.seq                   := l_seq + 10;
    l_lookup_value_row.attribute4            := p_group_flag; --集团用户标识
    INSERT INTO cms_lookup_value_t VALUES l_lookup_value_row;
    add_comp_lib_rel(p_comp_code, p_user_id);
    add_lib_category_rel(p_comp_code, p_user_id);
    init_comp_role(p_comp_code, p_user_id);
    x_return_code := 'S';
  EXCEPTION
    WHEN OTHERS THEN
      x_return_code := 'E';
      x_return_msg  := SQLERRM;
  END add_comp;

  PROCEDURE add_comp_lib_rel(p_comp_code IN VARCHAR2, p_user_id IN NUMBER) IS
    l_comp_lib_rel_row cms_comp_lib_rel_t%ROWTYPE;
    l_next_rel_id      NUMBER := 0;
    l_max_rel_id       NUMBER;
  BEGIN
    SELECT nvl(MAX(rel_id), 0) INTO l_max_rel_id FROM cms_comp_lib_rel_t;
    FOR c IN (SELECT t.lookup_value_code lib_code,
                     t.lookup_type_code  lib_type_code
                FROM cms_lookup_value_t t
               WHERE t.lookup_type_code IN
                     ('END_PROD_LIB', 'MATERIAL_LIB', 'JOB_LIB',
                      'RES_EXP_LIB', 'COPYRIGHT_LIB', 'ENTRY_LIB',
                      'THEME_LIB')) LOOP
      LOOP
        SELECT cms_comp_lib_rel_s.NEXTVAL INTO l_next_rel_id FROM dual;
        EXIT WHEN l_next_rel_id > l_max_rel_id;
      END LOOP;
      l_comp_lib_rel_row.rel_id                := l_next_rel_id;
      l_comp_lib_rel_row.comp_code             := p_comp_code;
      l_comp_lib_rel_row.lib_code              := c.lib_code;
      l_comp_lib_rel_row.lib_type_code         := c.lib_type_code;
      l_comp_lib_rel_row.object_version_number := 1;
      l_comp_lib_rel_row.created_by            := p_user_id;
      l_comp_lib_rel_row.creation_date         := SYSDATE;
      l_comp_lib_rel_row.last_updated_by       := p_user_id;
      l_comp_lib_rel_row.last_update_date      := SYSDATE;
      INSERT INTO cms_comp_lib_rel_t VALUES l_comp_lib_rel_row;
    END LOOP;
  END add_comp_lib_rel;

  PROCEDURE add_lib_category_rel(p_comp_code IN VARCHAR2,
                                 p_user_id   IN NUMBER) IS
    l_lib_category_rel_row cms_lib_category_g_rel_t%ROWTYPE;
    l_next_rel_id          NUMBER := 0;
    l_max_rel_id           NUMBER;
  BEGIN
    SELECT nvl(MAX(rel_id), 0)
      INTO l_max_rel_id
      FROM cms_lib_category_g_rel_t;
    FOR c IN (SELECT t.lookup_value_code lib_code,
                     t.lookup_type_code  lib_type_code
                FROM cms_lookup_value_t t
               WHERE t.lookup_type_code IN ('END_PROD_LIB', 'MATERIAL_LIB',
                      'COPYRIGHT_LIB', 'ENTRY_LIB')) LOOP
    
      LOOP
        SELECT cms_lib_category_g_rel_s.NEXTVAL
          INTO l_next_rel_id
          FROM dual;
        EXIT WHEN l_next_rel_id > l_max_rel_id;
      END LOOP;
      l_lib_category_rel_row.rel_id                := l_next_rel_id;
      l_lib_category_rel_row.category_group_code   := 'BY_CATEGORY';
      l_lib_category_rel_row.comp_code             := p_comp_code;
      l_lib_category_rel_row.lib_code              := c.lib_code;
      l_lib_category_rel_row.lib_type_code         := c.lib_type_code;
      l_lib_category_rel_row.primary_flag          := 'Y';
      l_lib_category_rel_row.enable_flag           := 'Y';
      l_lib_category_rel_row.object_version_number := 1;
      l_lib_category_rel_row.created_by            := p_user_id;
      l_lib_category_rel_row.creation_date         := SYSDATE;
      l_lib_category_rel_row.last_updated_by       := p_user_id;
      l_lib_category_rel_row.last_update_date      := SYSDATE;
      INSERT INTO cms_lib_category_g_rel_t VALUES l_lib_category_rel_row;
    END LOOP;
  END add_lib_category_rel;

  PROCEDURE init_comp_role(p_comp_code IN VARCHAR2, p_user_id IN NUMBER) IS
    l_comp_name VARCHAR2(100);
  BEGIN
    SELECT comp.comp_name
      INTO l_comp_name
      FROM cms_comp_v comp
     WHERE comp.comp_code = p_comp_code;
    add_comp_role(p_comp_code,
                  '工作平台管理员',
                  'WORK_PLATFORM',
                  NULL,
                  p_user_id);
    add_comp_role(p_comp_code,
                  '资源导出初审员',
                  'WORK_PLATFORM',
                  'FIRST_TRIAL',
                  p_user_id);
    add_comp_role(p_comp_code,
                  '资源导出复审员',
                  'WORK_PLATFORM',
                  'TRIAL',
                  p_user_id);
    add_comp_role(p_comp_code,
                  '资源导出终审员',
                  'WORK_PLATFORM',
                  'FINAL_JUDGMENT',
                  p_user_id);
    add_comp_role(p_comp_code,
                  '发布平台管理员',
                  'PUB_PLATFORM',
                  NULL,
                  p_user_id);
    add_comp_role(p_comp_code,
                  '资源下载初审员',
                  'PUB_PLATFORM',
                  'FIRST_TRIAL',
                  p_user_id);
    add_comp_role(p_comp_code,
                  '资源下载复审员',
                  'PUB_PLATFORM',
                  'TRIAL',
                  p_user_id);
    add_comp_role(p_comp_code,
                  '资源下载终审员',
                  'PUB_PLATFORM',
                  'FIRST_TRIAL',
                  p_user_id);
    add_comp_role(p_comp_code,
                  l_comp_name || '管理员',
                  'ADMIN_PLATFORM',
                  NULL,
                  p_user_id);
  END init_comp_role;

  PROCEDURE add_comp_role(p_comp_code  IN VARCHAR2,
                          p_role_name  IN VARCHAR2,
                          p_role_type  IN VARCHAR2,
                          p_attribute1 IN VARCHAR2,
                          p_user_id    IN NUMBER) IS
    l_role_row     cms_role_t%ROWTYPE;
    l_next_role_id NUMBER := 0;
    l_max_role_id  NUMBER;
  BEGIN
    SELECT nvl(MAX(role_id), 0) INTO l_max_role_id FROM cms_role_t;
    LOOP
      SELECT cms_role_s.NEXTVAL INTO l_next_role_id FROM dual;
      EXIT WHEN l_next_role_id > l_max_role_id;
    END LOOP;
    l_role_row.role_id               := l_next_role_id;
    l_role_row.role_name             := p_role_name;
    l_role_row.role_type             := p_role_type;
    l_role_row.enable_flag           := 'Y';
    l_role_row.comp_code             := p_comp_code;
    l_role_row.remarks               := NULL;
    l_role_row.attribute1            := p_attribute1;
    l_role_row.object_version_number := 1;
    l_role_row.created_by            := p_user_id;
    l_role_row.creation_date         := SYSDATE;
    l_role_row.last_updated_by       := p_user_id;
    l_role_row.last_update_date      := SYSDATE;
    INSERT INTO cms_role_t VALUES l_role_row;
  END add_comp_role;

  PROCEDURE modify_comp(p_comp_code   IN VARCHAR2,
                        p_comp_name   IN VARCHAR2,
                        p_comp_desc   IN VARCHAR2,
                        p_group_flag  IN VARCHAR2,
                        p_user_id     IN NUMBER,
                        x_return_code OUT VARCHAR2,
                        x_return_msg  OUT VARCHAR2) IS
    l_comp_count NUMBER;
  BEGIN
    SELECT COUNT(*)
      INTO l_comp_count
      FROM cms_lookup_value_t t
     WHERE t.lookup_value_code <> p_comp_code
       AND t.lookup_type_code = 'COMP'
       AND t.meaning = p_comp_name;
    IF l_comp_count > 0 THEN
      x_return_code := 'E';
      x_return_msg  := '该分社已经存在';
    ELSE
      UPDATE cms_lookup_value_t t
         SET t.lookup_value_desc = p_comp_desc,
             t.meaning           = p_comp_name,
             t.last_updated_by   = p_user_id,
             t.last_update_date  = SYSDATE,
             t.attribute4        = p_group_flag --集团用户标识
       WHERE t.lookup_value_code = p_comp_code
         AND t.lookup_type_code = 'COMP';
      x_return_code := 'S';
    END IF;
  EXCEPTION
    WHEN OTHERS THEN
      x_return_code := 'E';
      x_return_msg  := SQLERRM;
  END modify_comp;

  PROCEDURE delete_comp(p_comp_code   IN VARCHAR2,
                        x_return_code OUT VARCHAR2,
                        x_return_msg  OUT VARCHAR2) IS
  BEGIN
    /* UPDATE cms_lookup_value_t t
      SET t.enable_flag = 'N'
    WHERE t.lookup_value_code = p_comp_code
      AND t.lookup_type_code = 'COMP';*/
    DELETE FROM cms_lookup_value_t lvt
     WHERE lvt.lookup_value_code = p_comp_code
       AND lvt.lookup_type_code = 'COMP';
    FOR c IN (SELECT *
                FROM cms_category_t t
              CONNECT BY PRIOR t.category_id = t.parent_node_id
               START WITH t.parent_node_id IN
                          (SELECT -grt.rel_id
                             FROM cms_lib_category_g_rel_t grt
                            WHERE grt.comp_code = p_comp_code)) LOOP
      DELETE FROM cms_category_t t WHERE t.category_id = c.category_id;
    END LOOP;
    DELETE FROM cms_comp_lib_rel_t lrt WHERE lrt.comp_code = p_comp_code;
    DELETE FROM cms_lib_category_g_rel_t grt
     WHERE grt.comp_code = p_comp_code;
    DELETE FROM cms_role_t role WHERE role.comp_code = p_comp_code;
    DELETE FROM cms_3rd_authorization_items_t ait
     WHERE ait.item_id IN
           (SELECT item_id
              FROM cms_3rd_authorization_item_t it
             WHERE it.order_doc_id IN
                   (SELECT doc_id
                      FROM cms_doc_t d
                     WHERE d.comp_code = p_comp_code));
    DELETE FROM cms_3rd_authorization_item_t it
     WHERE it.order_doc_id IN
           ((SELECT doc_id FROM cms_doc_t d WHERE d.comp_code = p_comp_code));
    DELETE FROM cms_3rd_authorization_order_t ot
     WHERE ot.doc_id IN
           ((SELECT doc_id FROM cms_doc_t d WHERE d.comp_code = p_comp_code));
    DELETE FROM cms_activity_t t
     WHERE t.doc_id IN
           ((SELECT doc_id FROM cms_doc_t d WHERE d.comp_code = p_comp_code));
    DELETE FROM cms_audio_t t
     WHERE t.doc_id IN
           ((SELECT doc_id FROM cms_doc_t d WHERE d.comp_code = p_comp_code));
    DELETE FROM cms_author_copyright_t t
     WHERE t.doc_id IN
           ((SELECT doc_id FROM cms_doc_t d WHERE d.comp_code = p_comp_code));
    DELETE FROM cms_book_t t
     WHERE t.doc_id IN
           ((SELECT doc_id FROM cms_doc_t d WHERE d.comp_code = p_comp_code));
    DELETE FROM cms_chapter_t t
     WHERE t.doc_id IN
           ((SELECT doc_id FROM cms_doc_t d WHERE d.comp_code = p_comp_code));
    DELETE FROM cms_company_copyright_t t WHERE t.comp_code = p_comp_code;
    DELETE FROM cms_courseware_t t
     WHERE t.doc_id IN
           ((SELECT doc_id FROM cms_doc_t d WHERE d.comp_code = p_comp_code));
    DELETE FROM cms_elec_prod_t t
     WHERE t.doc_id IN
           ((SELECT doc_id FROM cms_doc_t d WHERE d.comp_code = p_comp_code));
    DELETE FROM cms_encyclopedias_entry_t t
     WHERE t.doc_id IN
           ((SELECT doc_id FROM cms_doc_t d WHERE d.comp_code = p_comp_code));
    DELETE FROM cms_illustration_t t
     WHERE t.doc_id IN
           ((SELECT doc_id FROM cms_doc_t d WHERE d.comp_code = p_comp_code));
    DELETE FROM cms_jb_info_t t
     WHERE t.doc_id IN
           ((SELECT doc_id FROM cms_doc_t d WHERE d.comp_code = p_comp_code));
    DELETE FROM cms_material_figure_t t
     WHERE t.doc_id IN
           ((SELECT doc_id FROM cms_doc_t d WHERE d.comp_code = p_comp_code));
    DELETE FROM cms_newspaper_t t
     WHERE t.doc_id IN
           ((SELECT doc_id FROM cms_doc_t d WHERE d.comp_code = p_comp_code));
    DELETE FROM cms_periodical_t t
     WHERE t.doc_id IN
           ((SELECT doc_id FROM cms_doc_t d WHERE d.comp_code = p_comp_code));
    DELETE FROM cms_photography_figure_t t
     WHERE t.doc_id IN
           ((SELECT doc_id FROM cms_doc_t d WHERE d.comp_code = p_comp_code));
    DELETE FROM cms_process_log_t t
     WHERE t.doc_id IN
           ((SELECT doc_id FROM cms_doc_t d WHERE d.comp_code = p_comp_code));
    DELETE FROM cms_teaching_plan_t t
     WHERE t.doc_id IN
           ((SELECT doc_id FROM cms_doc_t d WHERE d.comp_code = p_comp_code));
    DELETE FROM cms_video_t t
     WHERE t.doc_id IN
           ((SELECT doc_id FROM cms_doc_t d WHERE d.comp_code = p_comp_code));
    DELETE FROM cms_works_entry_t t
     WHERE t.doc_id IN
           ((SELECT doc_id FROM cms_doc_t d WHERE d.comp_code = p_comp_code));
    DELETE FROM cms_xc_info_t t
     WHERE t.doc_id IN
           ((SELECT doc_id FROM cms_doc_t d WHERE d.comp_code = p_comp_code));
    DELETE FROM cms_attach_t t
     WHERE t.doc_id IN
           ((SELECT doc_id FROM cms_doc_t d WHERE d.comp_code = p_comp_code));
    DELETE FROM cms_docbook_monitor_t mt
     WHERE mt.job_id IN (SELECT job_id
                           FROM cms_docbook_job_t t
                          WHERE t.comp_code = p_comp_code);
    DELETE FROM cms_docbook_job_t t WHERE t.comp_code = p_comp_code;
    DELETE FROM cms_doc_category_rel_t t
     WHERE t.doc_id IN
           ((SELECT doc_id FROM cms_doc_t d WHERE d.comp_code = p_comp_code));
    DELETE FROM cms_doc_group_rel_t t
     WHERE t.doc_id IN
           ((SELECT doc_id FROM cms_doc_t d WHERE d.comp_code = p_comp_code));
    DELETE FROM cms_doc_rel_t t
     WHERE t.doc_id IN
           ((SELECT doc_id FROM cms_doc_t d WHERE d.comp_code = p_comp_code));
    DELETE FROM cms_doc_ucm_rel_t t
     WHERE t.doc_id IN
           ((SELECT doc_id FROM cms_doc_t d WHERE d.comp_code = p_comp_code));
    DELETE FROM cms_figure_exif_info_t t
     WHERE t.doc_id IN
           ((SELECT doc_id FROM cms_doc_t d WHERE d.comp_code = p_comp_code));
    DELETE FROM cms_figure_group_t t WHERE t.comp_code = p_comp_code;
    DELETE FROM cms_gather_monitor_t t
     WHERE t.doc_id IN
           ((SELECT doc_id FROM cms_doc_t d WHERE d.comp_code = p_comp_code));
    DELETE FROM cms_res_temp_shelf_pub_h_t t
     WHERE t.doc_id IN
           ((SELECT doc_id FROM cms_doc_t d WHERE d.comp_code = p_comp_code));
    DELETE FROM cms_rollback_log_t t
     WHERE t.doc_id IN
           ((SELECT doc_id FROM cms_doc_t d WHERE d.comp_code = p_comp_code));
    DELETE FROM cms_task_monitor_log_t t
     WHERE t.doc_id IN
           ((SELECT doc_id FROM cms_doc_t d WHERE d.comp_code = p_comp_code));
    DELETE FROM cms_task_monitor_t t
     WHERE t.doc_id IN
           ((SELECT doc_id FROM cms_doc_t d WHERE d.comp_code = p_comp_code));
    DELETE FROM cms_theme_resource_t t WHERE t.comp_code = p_comp_code;
    DELETE FROM cms_theme_t t
     WHERE t.doc_id IN
           ((SELECT doc_id FROM cms_doc_t d WHERE d.comp_code = p_comp_code));
    DELETE FROM cms_theme_tree_t t WHERE t.comp_code = p_comp_code;
    DELETE FROM cms_user_t t WHERE t.comp_code = p_comp_code;
    DELETE FROM cms_doc_t d WHERE d.comp_code = p_comp_code;
    x_return_code := 'S';
  EXCEPTION
    WHEN OTHERS THEN
      x_return_code := 'E';
      x_return_msg  := SQLERRM;
  END delete_comp;

  FUNCTION get_first_comp_code RETURN VARCHAR2 IS
    l_comp_code VARCHAR2(100);
  BEGIN
    SELECT v.comp_code
      INTO l_comp_code
      FROM cms_comp_v v
     WHERE v.seq = (SELECT MIN(seq) FROM cms_comp_v);
    RETURN l_comp_code;
  EXCEPTION
    WHEN OTHERS THEN
      RETURN NULL;
  END get_first_comp_code;

  PROCEDURE revoke_privilege(p_comp_code     IN VARCHAR2,
                             p_lib_code      IN VARCHAR2,
                             p_platform_type IN VARCHAR2) IS
  BEGIN
    FOR c IN (SELECT rel.rel_id
                FROM cms_role_lib_rel_t rel, cms_role_t r
               WHERE rel.role_id = r.role_id
                 AND r.comp_code = p_comp_code
                 AND rel.lib_code = p_lib_code
                 AND rel.attribute1 = p_platform_type) LOOP
      DELETE FROM cms_role_lib_rel_t lrt WHERE lrt.rel_id = c.rel_id;
    END LOOP;
    COMMIT;
  END revoke_privilege;

END CMS_ADMIN_PLATFORM_PKG;
/
