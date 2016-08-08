package Module;

import java.util.HashMap;
import java.util.Map;

import Enum.MemberType; 

public class ShopCart {
    
    private String name ;
    private int totalItem ;
    private int totalBill ;
    private MemberType memberType ;
    private int discount ;
    private int paidBill ;
    
    public ShopCart(String name, int totalItem, int totalBill, MemberType memberType) {
        this.name = name ;
        this.totalItem = totalItem ;
        this.totalBill = totalBill ;
        this.memberType = memberType ;
        this.discount = 0 ;
        this.paidBill = this.totalBill ;
    }
    
    public String getName() { return name ; }
    public int getTotalItem() { return totalItem ; }
    public int getTotalBill() { return totalBill ; }
    public MemberType getMemberType() { return memberType ; }
    public int getDiscount() { return discount ; }
    public int getPaidBbill() { return paidBill ; }
    
    public Map<String,Object> toJSON() {
        Map<String,Object> response = new HashMap<String,Object>() ;
        response.put("name", name);
        response.put("total_bill", totalBill);
        response.put("total_item", totalItem);
        response.put("member_type", memberType);
        response.put("discount", discount);
        response.put("paid_bill", paidBill);
        return response ;
    }
    
    public void setDiscount(int discount) { this.discount += discount ; }
    public void setPaidBill() { 
        this.paidBill = (int) (this.totalBill -  (this.totalBill*((double)this.discount/100))) ;
    }
 
}
