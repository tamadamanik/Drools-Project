package Rule;

import org.easyrules.annotation.Action;
import org.easyrules.annotation.Condition;
import org.easyrules.annotation.Rule;
import org.easyrules.core.BasicRule;

import Module.ShopCart;
import Enum.MemberType;

@Rule(name = "Shop Rule")
public class ShopEasyRule {

    private ShopCart shopCart ;
    
    public ShopEasyRule(ShopCart shopCart) {
        this.shopCart = shopCart ;
    }
    
    @Condition
    public boolean when() {
        return shopCart.getMemberType()==MemberType.BUSINESS || shopCart.getMemberType() == MemberType.PUBLIC ||
                shopCart.getTotalBill() > 1000000 || shopCart.getTotalItem() > 10 ;
    }
    
    @Action
    public void then() throws Exception {
        try {
            if (shopCart.getMemberType()==MemberType.BUSINESS) shopCart.setDiscount(7) ;
            else shopCart.setDiscount(4);
            if (shopCart.getTotalBill() > 1000000) shopCart.setDiscount(5);
            if (shopCart.getTotalItem() > 10) shopCart.setDiscount(5);
            shopCart.setPaidBill();
        } catch (Exception e) {
            throw e ;
        }
    }
    
    public ShopCart getResult() { return this.shopCart ; }
}
