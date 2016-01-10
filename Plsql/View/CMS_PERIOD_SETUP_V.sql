CREATE OR REPLACE VIEW CMS_PERIOD_SETUP_V as
SELECT ps.setup_id,
       ps.periodical_category_id,
       ps.year,
       year_lookup.meaning year_meaning,
       ps.period_num,
       ps.public_date,
       year_lookup.seq
  FROM cms_period_setup_t ps, cms_lookup_value_v year_lookup
 WHERE to_char(ps.year) = year_lookup.lookup_value_code
