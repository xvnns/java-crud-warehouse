INSERT INTO product(price, quantity, quantity_update_date, create_ts, id, name, description, category, vendor_code)
SELECT 10,
       1,
       LOCALTIMESTAMP,
       LOCALTIMESTAMP,
       gen_random_uuid(),
       'test' || a.n,
       'description',
       'LAPTOP',
       '#' || TO_CHAR(a.n, 'fm0000000')
FROM generate_series(1, 1000000) as a(n);