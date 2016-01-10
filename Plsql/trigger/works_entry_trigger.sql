CREATE OR REPLACE TRIGGER works_entry_trigger
  AFTER INSERT OR UPDATE OR DELETE ON cms_works_entry_t
  REFERENCING NEW AS NEW OLD AS OLD
  FOR EACH ROW
DECLARE
  l_index   NUMBER;
  l_db_id   NUMBER;
  l_lib_id  NUMBER;
BEGIN
  SELECT (max_index + 1) INTO l_index FROM dup_index t WHERE rownum = 1;
  SELECT t.db_id INTO l_db_id FROM dup_db t WHERE t.NAME = 'WORKS_ENTRY';
  SELECT lib_id
    INTO l_lib_id
    FROM cms_lib_v
   WHERE lib_code = 'WORKS_ENTRY';
  IF inserting THEN
    INSERT INTO dup_text_1
    VALUES
      (l_index,
       l_db_id,
       0,
       1,
       SYSDATE,
       :new.doc_id,
       l_lib_id,
       :new.entry_id,
       :new.entry_content);
    UPDATE dup_index di SET di.max_index = l_index;
  ELSIF updating THEN
    UPDATE dup_text_1 dt
       SET dt.content = :new.entry_content
     WHERE dt.item_id = :new.entry_id;
  ELSIF deleting THEN
    UPDATE dup_text_1 dt
       SET dt.status = 2
     WHERE dt.item_id = :old.entry_id;
  END IF;
EXCEPTION
  WHEN OTHERS THEN
    RAISE;
END;
