[condition][]If there is a ShopCart with member type is business=$sc : ShopCart(memberType == MemberType.BUSINESS)
[condition][]If there is a ShopCart with member type is public=$sc : ShopCart(memberType == MemberType.PUBLIC)
[condition][]If there is a ShopCart with total item more than {item}=$sc : ShopCart(totalItem > {item})
[condition][]If there is a ShopCart with totalBill more than {bill}=$sc : ShopCart(totalBill > {bill})
[consequence][]Set discount {discount}=$sc.setDiscount({discount});
[consequence][]Set new paid bill=$sc.setPaidBill();
[consequence][]Message business member rules=System.out.println("Business member rule");
[consequence][]Message public member rules=System.out.println("Public member rule");
[consequence][]Message total item rules=System.out.println("Total item rule");
[consequence][]Message total bill rules=System.out.println("Total bill rule");
