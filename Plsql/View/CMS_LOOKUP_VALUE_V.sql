CREATE OR REPLACE VIEW CMS_LOOKUP_VALUE_V AS
SELECT lv.lookup_value_id,
       lv.lookup_value_code,
       lv.meaning,
       lt.lookup_type_code,
       lt.lookup_type_name,
       lv.seq,
       lt.seq seq2,
       lt.module_type,
       lt.module_code,
       lt.attribute1 process_flag,
       lv.attribute1,
       lv.attribute2,
       lv.attribute3,
       lv.attribute4, --�����û���ʶ
       lv.attribute5,
       lv.attribute6,
       lv.attribute7,
       lv.attribute8,
       lv.attribute9,
       lv.attribute10,
       lv.attribute11,
       lv.attribute12,
       lv.attribute13,
       lv.attribute14,
       lv.attribute15,
       lt.enable_flag lookup_type_enable_flag,
       lv.enable_flag lookup_value_enable_flag,
       lv.lookup_value_desc
  FROM cms_lookup_type_t lt, cms_lookup_value_t lv
 WHERE lt.lookup_type_code = lv.lookup_type_code
   AND lt.enable_flag = 'Y'
   AND lv.enable_flag = 'Y';
