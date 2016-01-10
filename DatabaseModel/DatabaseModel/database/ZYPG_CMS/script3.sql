CREATE TABLE CMS_ENCYCLOPEDIAS_ENTRY_T 
(
  ENTRY_ID NUMBER NOT NULL 
, DOC_ID NUMBER 
, ENTRY_HEADER VARCHAR2(4000) 
, SOURCE VARCHAR2(200) 
, KNOWLEDGE_POINT VARCHAR2(100) 
, KEYWORD VARCHAR2(1000) 
, STORE_TIME DATE 
, ENTRY_CONTENT CLOB 
, OBJECT_VERSION_NUMBER NUMBER 
, CREATED_BY NUMBER 
, CREATION_DATE DATE 
, LAST_UPDATED_BY NUMBER 
, LAST_UPDATE_DATE DATE 
, ATTRIBUTE1 VARCHAR2(240) 
, ATTRIBUTE2 VARCHAR2(240) 
, ATTRIBUTE3 VARCHAR2(240) 
, ATTRIBUTE4 VARCHAR2(240) 
, ATTRIBUTE5 VARCHAR2(40) 
, ATTRIBUTE6 VARCHAR2(240) 
, ATTRIBUTE7 VARCHAR2(240) 
, ATTRIBUTE8 VARCHAR2(240) 
, ATTRIBUTE9 VARCHAR2(240) 
, ATTRIBUTE10 VARCHAR2(240) 
, ATTRIBUTE11 VARCHAR2(240) 
, ATTRIBUTE12 VARCHAR2(240) 
, ATTRIBUTE13 VARCHAR2(240) 
, ATTRIBUTE14 VARCHAR2(240) 
, ATTRIBUTE15 VARCHAR2(240) 
, CONSTRAINT CMS_ENCYCLOPEDIAS_ENTRY_T_PK PRIMARY KEY 
  (
    ENTRY_ID 
  )
  USING INDEX 
  (
      CREATE UNIQUE INDEX CMS_ENCYCLOPEDIAS_ENTRY_Ｔ_PK ON CMS_ENCYCLOPEDIAS_ENTRY_T (ENTRY_ID ASC) 
  )
  ENABLE 
);

CREATE TABLE CMS_TASK_MONITOR_LOG_T 
(
  LOG_ID NUMBER NOT NULL 
, DOC_ID NUMBER 
, PROCESS_INFO VARCHAR2(1000) 
, TASK_OPERATOR VARCHAR2(100) 
, SEQ NUMBER 
, OBJECT_VERSION_NUMBER NUMBER 
, CREATED_BY NUMBER 
, CREATION_DATE DATE 
, LAST_UPDATED_BY NUMBER 
, LAST_UPDATE_DATE DATE 
, ATTRIBUTE1 VARCHAR2(240) 
, ATTRIBUTE2 VARCHAR2(240) 
, ATTRIBUTE3 VARCHAR2(240) 
, ATTRIBUTE4 VARCHAR2(240) 
, ATTRIBUTE5 VARCHAR2(240) 
, ATTRIBUTE6 VARCHAR2(240) 
, ATTRIBUTE7 VARCHAR2(240) 
, ATTRIBUTE8 VARCHAR2(240) 
, ATTRIBUTE9 VARCHAR2(240) 
, ATTRIBUTE10 VARCHAR2(240) 
, ATTRIBUTE11 VARCHAR2(240) 
, ATTRIBUTE12 VARCHAR2(240) 
, ATTRIBUTE13 VARCHAR2(240) 
, ATTRIBUTE14 VARCHAR2(240) 
, ATTRIBUTE15 VARCHAR2(240) 
, CONSTRAINT CMS_TASK_MONITOR_LOG_T_PK PRIMARY KEY 
  (
    LOG_ID 
  )
  USING INDEX 
  (
      CREATE UNIQUE INDEX CMS_TASK_MONITOR_LOG_Ｔ_PK ON CMS_TASK_MONITOR_LOG_T (LOG_ID ASC) 
  )
  ENABLE 
);

CREATE TABLE CMS_TASK_MONITOR_T 
(
  TASK_ID NUMBER NOT NULL 
, DOC_ID NUMBER 
, TASK_NAME VARCHAR2(200) 
, TASK_SPONSOR VARCHAR2(80) 
, CURR_OPERATOR VARCHAR2(80) 
, DESCRIPTION VARCHAR2(1000) 
, OBJECT_VERSION_NUMBER NUMBER 
, CREATED_BY NUMBER 
, CREATION_DATE DATE 
, LAST_UPDATED_BY NUMBER 
, LAST_UPDATE_DATE DATE 
, ATTRIBUTE1 VARCHAR2(240) 
, ATTRIBUTE2 VARCHAR2(240) 
, ATTRIBUTE3 VARCHAR2(240) 
, ATTRIBUTE4 VARCHAR2(240) 
, ATTRIBUTE5 VARCHAR2(240) 
, ATTRIBUTE6 VARCHAR2(240) 
, ATTRIBUTE7 VARCHAR2(240) 
, ATTRIBUTE8 VARCHAR2(240) 
, ATTRIBUTE9 VARCHAR2(240) 
, ATTRIBUTE10 VARCHAR2(240) 
, ATTRIBUTE11 VARCHAR2(240) 
, ATTRIBUTE12 VARCHAR2(240) 
, ATTRIBUTE13 VARCHAR2(240) 
, ATTRIBUTE14 VARCHAR2(240) 
, ATTRIBUTE15 VARCHAR2(240) 
, CONSTRAINT CMS_TASK_MONITOR_T_PK PRIMARY KEY 
  (
    TASK_ID 
  )
  ENABLE 
);

CREATE TABLE CMS_THEME_RESOURCE_T 
(
  RESOURCE_ID NUMBER NOT NULL 
, RESOURCE_NAME VARCHAR2(200) 
, LIB_CODE VARCHAR2(80) 
, TREE_NODE_ID NUMBER 
, COMP_CODE VARCHAR2(80) 
, OBJECT_VERSION_NUMBER NUMBER 
, CREATED_BY NUMBER 
, CREATION_DATE DATE 
, LAST_UPDATED_BY NUMBER 
, LAST_UPDATE_DATE DATE 
, ATTRIBUTE1 VARCHAR2(240) 
, ATTRIBUTE2 VARCHAR2(240) 
, ATTRIBUTE3 VARCHAR2(240) 
, ATTRIBUTE4 VARCHAR2(240) 
, ATTRIBUTE5 VARCHAR2(240) 
, ATTRIBUTE6 VARCHAR2(240) 
, ATTRIBUTE7 VARCHAR2(240) 
, ATTRIBUTE8 VARCHAR2(240) 
, ATTRIBUTE9 VARCHAR2(240) 
, ATTRIBUTE10 VARCHAR2(240) 
, ATTRIBUTE11 VARCHAR2(240) 
, ATTRIBUTE12 VARCHAR2(240) 
, ATTRIBUTE13 VARCHAR2(240) 
, ATTRIBUTE14 VARCHAR2(240) 
, ATTRIBUTE15 VARCHAR2(240) 
, CONSTRAINT CMS_THEME_RESOURCE_T_PK PRIMARY KEY 
  (
    RESOURCE_ID 
  )
  ENABLE 
);

CREATE TABLE CMS_THEME_T 
(
  THEME_ID NUMBER NOT NULL 
, DOC_ID NUMBER 
, THEME_NAME VARCHAR2(100) 
, THEME_DESC VARCHAR2(4000) 
, BINDING_THEME_ID NUMBER 
, BINDING_THEME_NAME VARCHAR2(100) 
, PUBLIC_DATE DATE 
, OBJECT_VERSION_NUMBER NUMBER 
, CREATED_BY NUMBER 
, CREATION_DATE DATE 
, LAST_UPDATED_BY NUMBER 
, LAST_UPDATE_DATE DATE 
, ATTRIBUTE1 VARCHAR2(240) 
, ATTRIBUTE2 VARCHAR2(240) 
, ATTRIBUTE3 VARCHAR2(240) 
, ATTRIBUTE4 VARCHAR2(240) 
, ATTRIBUTE5 VARCHAR2(240) 
, ATTRIBUTE6 VARCHAR2(240) 
, ATTRIBUTE7 VARCHAR2(240) 
, ATTRIBUTE8 VARCHAR2(240) 
, ATTRIBUTE9 VARCHAR2(240) 
, ATTRIBUTE10 VARCHAR2(240) 
, ATTRIBUTE11 VARCHAR2(240) 
, ATTRIBUTE12 VARCHAR2(240) 
, ATTRIBUTE13 VARCHAR2(240) 
, ATTRIBUTE14 VARCHAR2(240) 
, ATTRIBUTE15 VARCHAR2(240) 
, CONSTRAINT CMS_THEME_T_PK PRIMARY KEY 
  (
    THEME_ID 
  )
  ENABLE 
);

CREATE TABLE CMS_THEME_TREE_T 
(
  NODE_ID NUMBER NOT NULL 
, NODE_NAME VARCHAR2(100) 
, NODE_TYPE VARCHAR2(100) 
, PARENT_NODE_ID NUMBER 
, COMP_CODE VARCHAR2(20) 
, ENABLE_FLAG VARCHAR2(20) 
, REMARKS VARCHAR2(1000) 
, OBJECT_VERSION_NUMBER NUMBER 
, CREATED_BY NUMBER 
, CREATION_DATE DATE 
, LAST_UPDATED_BY NUMBER 
, LAST_UPDATE_DATE DATE 
, ATTRIBUTE1 VARCHAR2(240) 
, ATTRIBUTE2 VARCHAR2(240) 
, ATTRIBUTE3 VARCHAR2(240) 
, ATTRIBUTE4 VARCHAR2(240) 
, ATTRIBUTE5 VARCHAR2(240) 
, ATTRIBUTE6 VARCHAR2(240) 
, ATTRIBUTE7 VARCHAR2(240) 
, ATTRIBUTE8 VARCHAR2(240) 
, ATTRIBUTE9 VARCHAR2(240) 
, ATTRIBUTE10 VARCHAR2(240) 
, ATTRIBUTE11 VARCHAR2(240) 
, ATTRIBUTE12 VARCHAR2(240) 
, ATTRIBUTE13 VARCHAR2(240) 
, ATTRIBUTE14 VARCHAR2(240) 
, ATTRIBUTE15 VARCHAR2(240) 
, CONSTRAINT CMS_THEME_TREE_T_PK PRIMARY KEY 
  (
    NODE_ID 
  )
  ENABLE 
);

CREATE TABLE CMS_WORKS_ENTRY_T 
(
  ENTRY_ID NUMBER NOT NULL 
, DOC_ID NUMBER 
, TITLE VARCHAR2(4000) 
, SUBTITLE VARCHAR2(4000) 
, ENGLISH_TITLE VARCHAR2(4000) 
, ENGLISH_SUBTITLE VARCHAR2(4000) 
, SOURCE VARCHAR2(200) 
, AUTHOR VARCHAR2(80) 
, TRANSLATOR VARCHAR2(80) 
, LANG VARCHAR2(20) 
, WORD_COUNT NUMBER 
, WRITING_TIME DATE 
, STORE_TIME DATE 
, KEYWORD VARCHAR2(1000) 
, ENTRY_CONTENT CLOB 
, ABSTARCT_DESC VARCHAR2(4000) 
, OBJECT_VERSION_NUMBER NUMBER 
, CREATED_BY NUMBER 
, CREATION_DATE DATE 
, LAST_UPDATED_BY NUMBER 
, LAST_UPDATE_DATE DATE 
, ATTRIBUTE1 VARCHAR2(240) 
, ATTRIBUTE2 VARCHAR2(240) 
, ATTRIBUTE3 VARCHAR2(240) 
, ATTRIBUTE4 VARCHAR2(240) 
, ATTRIBUTE5 VARCHAR2(240) 
, ATTRIBUTE6 VARCHAR2(240) 
, ATTRIBUTE7 VARCHAR2(240) 
, ATTRIBUTE8 VARCHAR2(240) 
, ATTRIBUTE9 VARCHAR2(240) 
, ATTRIBUTE10 VARCHAR2(240) 
, ATTRIBUTE11 VARCHAR2(240) 
, ATTRIBUTE13 VARCHAR2(240) 
, ATTRIBUTE14 VARCHAR2(240) 
, ATTRIBUTE15 VARCHAR2(240) 
, ATTRIBUTE12 VARCHAR2(240) 
, CONSTRAINT CMS_WORKS_ENTRY_T_PK PRIMARY KEY 
  (
    ENTRY_ID 
  )
  ENABLE 
);

COMMENT ON COLUMN CMS_ENCYCLOPEDIAS_ENTRY_T.ENTRY_HEADER IS '条头';

COMMENT ON COLUMN CMS_ENCYCLOPEDIAS_ENTRY_T.SOURCE IS '来源';

COMMENT ON COLUMN CMS_ENCYCLOPEDIAS_ENTRY_T.KNOWLEDGE_POINT IS '知识点';

COMMENT ON COLUMN CMS_ENCYCLOPEDIAS_ENTRY_T.KEYWORD IS '关键词';

COMMENT ON COLUMN CMS_ENCYCLOPEDIAS_ENTRY_T.STORE_TIME IS '入库时间';

COMMENT ON COLUMN CMS_ENCYCLOPEDIAS_ENTRY_T.ENTRY_CONTENT IS '条目内容';

COMMENT ON COLUMN CMS_TASK_MONITOR_LOG_T.LOG_ID IS '主键';

COMMENT ON COLUMN CMS_TASK_MONITOR_LOG_T.DOC_ID IS 'DOC_ID';

COMMENT ON COLUMN CMS_TASK_MONITOR_LOG_T.PROCESS_INFO IS '处理信息';

COMMENT ON COLUMN CMS_TASK_MONITOR_LOG_T.TASK_OPERATOR IS '操作者';

COMMENT ON COLUMN CMS_TASK_MONITOR_LOG_T.SEQ IS '顺序';

COMMENT ON COLUMN CMS_TASK_MONITOR_T.TASK_ID IS '任务ID';

COMMENT ON COLUMN CMS_TASK_MONITOR_T.TASK_NAME IS '任务名称';

COMMENT ON COLUMN CMS_TASK_MONITOR_T.TASK_SPONSOR IS '任务发起人';

COMMENT ON COLUMN CMS_TASK_MONITOR_T.CURR_OPERATOR IS '当前处理人';

COMMENT ON COLUMN CMS_TASK_MONITOR_T.DESCRIPTION IS '详细状态';

COMMENT ON COLUMN CMS_THEME_RESOURCE_T.RESOURCE_ID IS '主键';

COMMENT ON COLUMN CMS_THEME_RESOURCE_T.RESOURCE_NAME IS '资源名称';

COMMENT ON COLUMN CMS_THEME_RESOURCE_T.LIB_CODE IS '资源类型（资源库）';

COMMENT ON COLUMN CMS_THEME_RESOURCE_T.TREE_NODE_ID IS '主题树节点ID';

COMMENT ON COLUMN CMS_THEME_T.THEME_NAME IS '主题名称';

COMMENT ON COLUMN CMS_THEME_T.THEME_DESC IS '主题描述';

COMMENT ON COLUMN CMS_THEME_T.BINDING_THEME_ID IS '绑定的应用主题ID';

COMMENT ON COLUMN CMS_THEME_T.BINDING_THEME_NAME IS '绑定的应用主题名称';

COMMENT ON COLUMN CMS_THEME_T.PUBLIC_DATE IS '发布时间';

COMMENT ON COLUMN CMS_THEME_T.CREATED_BY IS '创建人';

COMMENT ON COLUMN CMS_THEME_T.CREATION_DATE IS '创建日期';

COMMENT ON COLUMN CMS_THEME_TREE_T.NODE_NAME IS '节点名称';

COMMENT ON COLUMN CMS_THEME_TREE_T.NODE_TYPE IS '节点类型';

COMMENT ON COLUMN CMS_THEME_TREE_T.PARENT_NODE_ID IS '父节点ID';

COMMENT ON COLUMN CMS_THEME_TREE_T.REMARKS IS '备注';

COMMENT ON COLUMN CMS_WORKS_ENTRY_T.ENTRY_ID IS '主键';

COMMENT ON COLUMN CMS_WORKS_ENTRY_T.DOC_ID IS '文档ID';

COMMENT ON COLUMN CMS_WORKS_ENTRY_T.TITLE IS '标题';

COMMENT ON COLUMN CMS_WORKS_ENTRY_T.SUBTITLE IS '副标题';

COMMENT ON COLUMN CMS_WORKS_ENTRY_T.ENGLISH_TITLE IS '英文标题';

COMMENT ON COLUMN CMS_WORKS_ENTRY_T.ENGLISH_SUBTITLE IS '英文副标题';

COMMENT ON COLUMN CMS_WORKS_ENTRY_T.SOURCE IS '来源';

COMMENT ON COLUMN CMS_WORKS_ENTRY_T.AUTHOR IS '作者';

COMMENT ON COLUMN CMS_WORKS_ENTRY_T.LANG IS '语种';

COMMENT ON COLUMN CMS_WORKS_ENTRY_T.WORD_COUNT IS '字数';

COMMENT ON COLUMN CMS_WORKS_ENTRY_T.WRITING_TIME IS '写作时间';

COMMENT ON COLUMN CMS_WORKS_ENTRY_T.STORE_TIME IS '入库时间';

COMMENT ON COLUMN CMS_WORKS_ENTRY_T.KEYWORD IS '关键词';

COMMENT ON COLUMN CMS_WORKS_ENTRY_T.ENTRY_CONTENT IS '条目内容';

COMMENT ON COLUMN CMS_WORKS_ENTRY_T.ABSTARCT_DESC IS '摘要';

CREATE SEQUENCE CMS_ENCYCLOPEDIAS_ENTRY_S INCREMENT BY 1 START WITH 1 MAXVALUE 99999999 MINVALUE 1 CACHE 20;

CREATE SEQUENCE CMS_TASK_MONITOR_LOG_S INCREMENT BY 1 START WITH 1 MAXVALUE 99999999 MINVALUE 1 CACHE 20;

CREATE SEQUENCE CMS_TASK_MONITOR_S INCREMENT BY 1 START WITH 1 MAXVALUE 99999999 MINVALUE 1 CACHE 20;

CREATE SEQUENCE CMS_THEME_RESOURCE_S INCREMENT BY 1 START WITH 1 MAXVALUE 99999999 MINVALUE 1 CACHE 20;

CREATE SEQUENCE CMS_THEME_S INCREMENT BY 1 START WITH 1 MAXVALUE 99999999 MINVALUE 1 CACHE 20;

CREATE SEQUENCE CMS_THEME_TREE_S INCREMENT BY 1 START WITH 1 MAXVALUE 99999999 MINVALUE 1 CACHE 20;

CREATE SEQUENCE CMS_WORKS_ENTRY_S INCREMENT BY 1 START WITH 1 MAXVALUE 99999999 MINVALUE 1 CACHE 20;
