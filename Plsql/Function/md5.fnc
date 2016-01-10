CREATE OR REPLACE FUNCTION md5(passwd IN VARCHAR2) RETURN VARCHAR2 IS
  retval VARCHAR2(32);
BEGIN
  retval := utl_raw.cast_to_raw(dbms_obfuscation_toolkit.md5(input_string => passwd));
  RETURN retval;
END;
/
