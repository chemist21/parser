# Wildberries parser

Current app can be used to take 300's items from Wildberries by brands id* 
```
http://localhost:8080/wildberries/{brand_id}
```
(request will return list and save list into db)

For every item select next list of params:
- id
- name
- priceU - price without discount
- salePriceU - price with discount
- reviewRating 
- feedbacks - number of feedbacks

---
API to get items from DB
```
- GET http://localhost:8080/wildberries/item - return all items from db
- GET http://localhost:8080/wildberries/item/{id} - return Item by Id
```

---
   *for some brands it can be found in url, but not always
  (https://www.wildberries.ru/brands/742547-O!LINO)

some example brand_id with more then 300 items
- 27445
- 7676
