-- Create table
create table DUP_DB
(
  db_id INTEGER not null,
  name  VARCHAR2(255) not null,
  rate  INTEGER not null,
  note  VARCHAR2(255)
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
-- Create/Recreate primary, unique and foreign key constraints 
alter table DUP_DB
  add primary key (DB_ID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );


-- Create table
create table DUP_FILTER
(
  filter_id       NUMBER(16) not null,
  this_doc_id     INTEGER not null,
  this_doc_lib_id INTEGER not null,
  that_doc_id     INTEGER not null,
  that_doc_lib_id INTEGER not null,
  db_id           INTEGER not null
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255;
-- Create/Recreate primary, unique and foreign key constraints 
alter table DUP_FILTER
  add primary key (FILTER_ID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255;

-- Create table
create table DUP_INDEX
(
  name      VARCHAR2(50) not null,
  max_index NUMBER(16) not null
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
-- Create/Recreate primary, unique and foreign key constraints 
alter table DUP_INDEX
  add primary key (NAME)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

  -- Create table
create table DUP_PID
(
  userid INTEGER not null,
  qstid  NUMBER(16) not null
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255;


  -- Create table
create table DUP_QUERY
(
  query_id NUMBER(16) not null,
  db_id    INTEGER,
  text_id  NUMBER(16)
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
-- Create/Recreate primary, unique and foreign key constraints 
alter table DUP_QUERY
  add primary key (QUERY_ID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );


-- Create table
create table DUP_RESULT_1
(
  result_id       NUMBER(16) not null,
  this_text_id    NUMBER(16) not null,
  this_type       INTEGER,
  this_doc_id     INTEGER not null,
  this_doc_lib_id INTEGER,
  this_item_id    INTEGER,
  this_fill_time  DATE,
  that_text_id    NUMBER(16) not null,
  that_type       INTEGER,
  that_doc_id     INTEGER not null,
  that_doc_lib_id INTEGER,
  that_item_id    INTEGER,
  that_fill_time  DATE,
  dup_time        DATE not null,
  rate            INTEGER not null
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
-- Create/Recreate indexes 
create index DUP_RESULT_1_INDEX on DUP_RESULT_1 (THIS_FILL_TIME DESC, RATE DESC, THIS_DOC_ID)
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
create index DUP_RESULT_1_THATTEXT_INDEX on DUP_RESULT_1 (THAT_TEXT_ID)
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
create index DUP_RESULT_1_THAT_INDEX on DUP_RESULT_1 (THAT_DOC_ID)
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
create index DUP_RESULT_1_THISTEXT_INDEX on DUP_RESULT_1 (THIS_TEXT_ID)
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
create index DUP_RESULT_1_THIS_INDEX on DUP_RESULT_1 (THIS_DOC_ID)
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
-- Create/Recreate primary, unique and foreign key constraints 
alter table DUP_RESULT_1
  add primary key (RESULT_ID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );


-- Create table
create table DUP_RESULT_2
(
  result_id       NUMBER(16) not null,
  this_text_id    NUMBER(16) not null,
  this_type       INTEGER,
  this_doc_id     INTEGER not null,
  this_doc_lib_id INTEGER,
  this_item_id    INTEGER,
  this_fill_time  DATE,
  that_text_id    NUMBER(16) not null,
  that_type       INTEGER,
  that_doc_id     INTEGER not null,
  that_doc_lib_id INTEGER,
  that_item_id    INTEGER,
  that_fill_time  DATE,
  dup_time        DATE not null,
  rate            INTEGER not null
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255;
-- Create/Recreate indexes 
create index DUP_RESULT_2_INDEX on DUP_RESULT_2 (THIS_FILL_TIME DESC, RATE DESC, THIS_DOC_ID)
  pctfree 10
  initrans 2
  maxtrans 255;
create index DUP_RESULT_2_THATTEXT_INDEX on DUP_RESULT_2 (THAT_TEXT_ID)
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255;
create index DUP_RESULT_2_THAT_INDEX on DUP_RESULT_2 (THAT_DOC_ID)
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255;
create index DUP_RESULT_2_THISTEXT_INDEX on DUP_RESULT_2 (THIS_TEXT_ID)
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255;
create index DUP_RESULT_2_THIS_INDEX on DUP_RESULT_2 (THIS_DOC_ID)
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255;
-- Create/Recreate primary, unique and foreign key constraints 
alter table DUP_RESULT_2
  add primary key (RESULT_ID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255;


-- Create table
create table DUP_TEXT_1
(
  text_id    NUMBER(16) not null,
  db_id      INTEGER not null,
  type       INTEGER not null,
  status     INTEGER not null,
  fill_time  DATE,
  doc_id     INTEGER,
  doc_lib_id INTEGER,
  item_id    INTEGER,
  content    CLOB
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
-- Create/Recreate primary, unique and foreign key constraints 
alter table DUP_TEXT_1
  add primary key (TEXT_ID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );


-- Create table
create table DUP_TEXT_2
(
  text_id    NUMBER(16) not null,
  db_id      INTEGER not null,
  type       INTEGER not null,
  status     INTEGER not null,
  fill_time  DATE,
  doc_id     INTEGER,
  doc_lib_id INTEGER,
  item_id    INTEGER,
  content    CLOB
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
-- Create/Recreate primary, unique and foreign key constraints 
alter table DUP_TEXT_2
  add primary key (TEXT_ID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );


