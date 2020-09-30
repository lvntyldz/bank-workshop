### private methodun erişimi
method ismiyle birlikte "getDeclaredMethod(...)" method çağrıldığında aradığımız methodun sonucunu döner
```
Product.class.getDeclaredMethod("calculateDiscount", null);
```
bulunan sonuç içinde accessible=true yaptığımızda reflection ile private method erişilebilir hale gelir.

```
privateMethod.setAccessible(true); 
```

üstteki değişikliklerin son hali
```
    Method privateMethod = Product.class.getDeclaredMethod("calculateDiscount", null);
    privateMethod.setAccessible(true);
```
