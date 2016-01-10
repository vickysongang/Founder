DECLARE
  l_id_str VARCHAR2(240);
BEGIN
  FOR c IN (SELECT * FROM cms_category_t a) LOOP
    l_id_str := '-' || c.category_id || '-';
    FOR c1 IN (SELECT *
                 FROM cms_category_t t
               CONNECT BY PRIOR t.parent_node_id = t.category_id
                START WITH t.category_id = c.category_id) LOOP
      l_id_str := l_id_str || '-' || c1.parent_node_id || '-';
    END LOOP;
    UPDATE cms_category_t ct
       SET ct.attribute5 = l_id_str
     WHERE ct.category_id = c.category_id;
  END LOOP;
END;
