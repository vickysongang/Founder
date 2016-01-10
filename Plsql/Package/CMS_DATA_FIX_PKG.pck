CREATE OR REPLACE PACKAGE CMS_DATA_FIX_PKG IS

  -- Author  : VICKY
  -- Created : 2014/12/9 10:43:01
  -- Purpose : 数据FIX程序包

  --删除已有分类
  FUNCTION delete_category(p_parent_category_id IN VARCHAR2) RETURN VARCHAR2;

  FUNCTION insert_category(p_category_name    IN VARCHAR2,
                           p_parent_node_type IN VARCHAR2,
                           p_parent_node_id   IN VARCHAR2,
                           p_cat_type         IN VARCHAR2,
                           p_cat_id           IN VARCHAR2,
                           p_seq              IN NUMBER,
                           p_level            IN NUMBER) RETURN VARCHAR2;

END CMS_DATA_FIX_PKG;
/
CREATE OR REPLACE PACKAGE BODY CMS_DATA_FIX_PKG IS

  FUNCTION delete_category(p_parent_category_id IN VARCHAR2) RETURN VARCHAR2 IS
  BEGIN
    FOR cur IN (SELECT *
                  FROM cms_category_t c
                 WHERE cms_common_pkg.get_category_top_node_id(c.category_id) =
                       to_number(p_parent_category_id)) LOOP
      DELETE FROM cms_category_t t WHERE t.category_id = cur.category_id;
    END LOOP;
    RETURN 'S';
  END delete_category;

  FUNCTION insert_category(p_category_name    IN VARCHAR2,
                           p_parent_node_type IN VARCHAR2,
                           p_parent_node_id   IN VARCHAR2,
                           p_cat_type         IN VARCHAR2,
                           p_cat_id           IN VARCHAR2,
                           p_seq              IN NUMBER,
                           p_level            IN NUMBER) RETURN VARCHAR2 IS
    l_category_row   cms_category_t%ROWTYPE;
    l_category_id    NUMBER;
    l_parent_node_id NUMBER;
  BEGIN
    IF p_level = 1 THEN
      l_parent_node_id := - (to_number(p_parent_node_id));
    ELSE
      l_parent_node_id := to_number(p_parent_node_id);
    END IF;
    SELECT cms_category_s.NEXTVAL INTO l_category_id FROM dual;
    l_category_row.category_id           := l_category_id;
    l_category_row.category_name         := p_category_name;
    l_category_row.parent_node_type      := p_parent_node_type;
    l_category_row.parent_node_id        := l_parent_node_id;
    l_category_row.seq                   := p_seq;
    l_category_row.object_version_number := 1;
    l_category_row.created_by            := -1;
    l_category_row.creation_date         := SYSDATE;
    l_category_row.last_updated_by       := -1;
    l_category_row.last_update_date      := SYSDATE;
    l_category_row.attribute14           := p_cat_id;
    l_category_row.attribute15           := p_cat_type;
    INSERT INTO cms_category_t VALUES l_category_row;
    RETURN l_category_id;
  EXCEPTION
    WHEN OTHERS THEN
      RETURN NULL;
  END insert_category;

END CMS_DATA_FIX_PKG;
/
