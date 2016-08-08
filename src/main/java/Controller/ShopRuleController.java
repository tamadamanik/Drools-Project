package Controller;

import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import Enum.MemberType;
import Module.ShopCart;
import Service.ShopRuleService;

@RestController
@RequestMapping(value = ShopRuleController.BASE_PATH, produces = {MediaType
        .APPLICATION_JSON_VALUE})
public class ShopRuleController {

    public static final String BASE_PATH = "/shop" ;
    
    ShopRuleService shopRuleService = new ShopRuleService() ;
    
    @RequestMapping(value = "/xml")
    public Map<String,Object> ruleFromXML(@RequestParam String name, @RequestParam int totalItem,
            @RequestParam int totalBill, @RequestParam String memberType) {
        return shopRuleService.runRuleWithXML(new ShopCart(name,totalItem,totalBill,MemberType.valueOf(memberType)));
    }
    
    @RequestMapping(value = "/base")
    public Map<String,Object> ruleFromKnowledgeBase(@RequestParam String name, @RequestParam int totalItem,
            @RequestParam int totalBill, @RequestParam String memberType) {
        return shopRuleService.runRuleWithKnowledgeBase(new ShopCart(name,totalItem,totalBill,MemberType.valueOf(memberType)));
    }
    
    @RequestMapping(value = "/attribute/activationgroup")
    public Map<String,Object> ruleWithRuleFlowAttribue(@RequestParam String name, @RequestParam int totalItem,
            @RequestParam int totalBill, @RequestParam String memberType) {
        return shopRuleService.runRule_RuleaActivationGroup(new ShopCart(name,totalItem,totalBill,MemberType.valueOf(memberType)));
    }
    
    @RequestMapping(value = "/attribute/agendagroup")
    public Map<String,Object> ruleWithAgendaGroupAttribue(@RequestParam String name, @RequestParam int totalItem,
            @RequestParam int totalBill, @RequestParam String memberType) {
        return shopRuleService.runRule_RuleAgendaGroup(new ShopCart(name,totalItem,totalBill,MemberType.valueOf(memberType)));
    }
    
    @RequestMapping(value = "/attribute/autofocus")
    public Map<String,Object> ruleWithAutoFocuesAttribue(@RequestParam String name, @RequestParam int totalItem,
            @RequestParam int totalBill, @RequestParam String memberType) {
        return shopRuleService.runRule_RuleAutoFocus(new ShopCart(name,totalItem,totalBill,MemberType.valueOf(memberType)));
    }
    
    @RequestMapping(value = "/attribute/looprule")
    public Map<String,Object> ruleWithInfiniteLoop(@RequestParam String name, @RequestParam int totalItem,
            @RequestParam int totalBill, @RequestParam String memberType) {
        return shopRuleService.runRule_testLoop(new ShopCart(name,totalItem,totalBill,MemberType.valueOf(memberType)));
    }
    
    @RequestMapping(value = "/attribute/nolooprule")
    public Map<String,Object> ruleWithNoLoop(@RequestParam String name, @RequestParam int totalItem,
            @RequestParam int totalBill, @RequestParam String memberType) {
        return shopRuleService.runRule_testNoLoop(new ShopCart(name,totalItem,totalBill,MemberType.valueOf(memberType)));
    }
    
    @RequestMapping(value = "/attribute/salience")
    public Map<String,Object> ruleWithSalienceAttribue(@RequestParam String name, @RequestParam int totalItem,
            @RequestParam int totalBill, @RequestParam String memberType) {
        return shopRuleService.runRule_RuleSalience(new ShopCart(name,totalItem,totalBill,MemberType.valueOf(memberType)));
    }
    
    @RequestMapping(value = "/easyrule")
    public Map<String,Object> ruleFromWithEasyRule(@RequestParam String name, @RequestParam int totalItem,
            @RequestParam int totalBill, @RequestParam String memberType) {
        return shopRuleService.runRuleWithEasyRule(new ShopCart(name,totalItem,totalBill,MemberType.valueOf(memberType)));
    }
}
