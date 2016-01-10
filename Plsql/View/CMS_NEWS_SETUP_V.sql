CREATE OR REPLACE VIEW CMS_NEWS_SETUP_V AS
SELECT ns.setup_id,
       ns.newspaper_category_id,
       ns.year,
       year_lookup.meaning year_meaning,
       ns.news_num,
       ns.public_date,
       year_lookup.seq
  FROM cms_news_setup_t ns, cms_lookup_value_v year_lookup
 WHERE to_char(ns.year) = year_lookup.lookup_value_code;
