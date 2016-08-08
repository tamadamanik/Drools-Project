package Service;

import java.util.HashMap;
import java.util.Map;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.internal.KnowledgeBase;
import org.kie.internal.runtime.StatefulKnowledgeSession;
import org.easyrules.api.RulesEngine;
import static org.easyrules.core.RulesEngineBuilder.aNewRulesEngine;

import Globals.Knowledge;
import Module.ShopCart;
import Rule.ShopEasyRule;

public class ShopRuleService { 
    
    Knowledge knowledge= new Knowledge() ;
    
    public Map<String,Object> runRuleWithXML(ShopCart shopCart) {
        Map<String,Object> response = new HashMap<String,Object>() ;
        response.put("process", "RUN_RULE_WITH_XML_SESSION");
        response.put("input_data",shopCart.toJSON());
        KieServices ks = KieServices.Factory.get();
        KieContainer kContainer = ks.getKieClasspathContainer();
        KieSession kSession = kContainer.newKieSession("shop_session");
        kSession.insert(shopCart);
        kSession.fireAllRules();
        response.put("output_data", shopCart.toJSON());
        return response ;
    }
    
    public Map<String,Object> runRule_RuleaActivationGroup(ShopCart shopCart) {
        Map<String,Object> response = new HashMap<String,Object>() ;
        response.put("process", "RUN_RULE_WITH_ATTRIBUTE_ACTIVATION_GROUP");
        response.put("input_data",shopCart.toJSON());
        knowledge.resetKnowledgeBase();
        knowledge.addKnowledgeBaseFromFile("shopRule/shopRule_ruleFlow.drl");
        KnowledgeBase kBase = Knowledge.kBase;
        StatefulKnowledgeSession kSession = kBase.newStatefulKnowledgeSession();
        kSession.getAgenda().getActivationGroup("aa") ;
        kSession.insert(shopCart);
        kSession.fireAllRules();
        response.put("output_data", shopCart.toJSON());
        return response ;
    }
    
    public Map<String,Object> runRule_RuleAgendaGroup(ShopCart shopCart) {
        Map<String,Object> response = new HashMap<String,Object>() ;
        response.put("process", "RUN_RULE_WITH_ATTRIBUTE_AGENDA_GROUP");
        response.put("input_data",shopCart.toJSON());
        knowledge.resetKnowledgeBase();
        knowledge.addKnowledgeBaseFromFile("shopRule/shopRule_agendaGroup.drl");
        KnowledgeBase kBase = Knowledge.kBase;
        StatefulKnowledgeSession kSession = kBase.newStatefulKnowledgeSession();
        kSession.getAgenda().getAgendaGroup("member").setFocus();
        kSession.insert(shopCart);
        kSession.fireAllRules();
        response.put("output_data", shopCart.toJSON());
        return response ;
    }
    
    public Map<String,Object> runRule_RuleAutoFocus(ShopCart shopCart) {
        Map<String,Object> response = new HashMap<String,Object>() ;
        response.put("process", "RUN_RULE_WITH_ATTRIBUTE_AGENDA_GROUP");
        response.put("input_data",shopCart.toJSON());
        knowledge.resetKnowledgeBase();
        knowledge.addKnowledgeBaseFromFile("shopRule/shopRule_autoFocus.drl");
        KnowledgeBase kBase = Knowledge.kBase;
        StatefulKnowledgeSession kSession = kBase.newStatefulKnowledgeSession();
        kSession.getAgenda().getAgendaGroup("member");
        kSession.insert(shopCart);
        kSession.fireAllRules();
        response.put("output_data", shopCart.toJSON());
        return response ;
    }
    
    public Map<String,Object> runRule_testLoop(ShopCart shopCart) {
        Map<String,Object> response = new HashMap<String,Object>() ;
        response.put("process", "RUN_RULE_WITH_INFINITE_LOOP");
        response.put("input_data",shopCart.toJSON());
        knowledge.resetKnowledgeBase();
        knowledge.addKnowledgeBaseFromFile("shopRule/shopRule_loop.drl");
        KnowledgeBase kBase = Knowledge.kBase;
        StatefulKnowledgeSession kSession = kBase.newStatefulKnowledgeSession();
        kSession.getAgenda().getAgendaGroup("member");
        kSession.insert(shopCart);
        kSession.fireAllRules();
        response.put("output_data", shopCart.toJSON());
        return response ;
    }
    
    public Map<String,Object> runRule_testNoLoop(ShopCart shopCart) {
        Map<String,Object> response = new HashMap<String,Object>() ;
        response.put("process", "RUN_RULE_WITH_INFINITE_LOOP");
        response.put("input_data",shopCart.toJSON());
        knowledge.resetKnowledgeBase();
        knowledge.addKnowledgeBaseFromFile("shopRule/shopRule_noloop.drl");
        KnowledgeBase kBase = Knowledge.kBase;
        StatefulKnowledgeSession kSession = kBase.newStatefulKnowledgeSession();
        kSession.getAgenda().getAgendaGroup("member");
        kSession.insert(shopCart);
        kSession.fireAllRules();
        response.put("output_data", shopCart.toJSON());
        return response ;
    }
    
    public Map<String,Object> runRule_RuleSalience(ShopCart shopCart) {
        Map<String,Object> response = new HashMap<String,Object>() ;
        response.put("process", "RUN_RULE_WITH_ATTRIBUTE_SALIENCE");
        response.put("input_data",shopCart.toJSON());
        knowledge.resetKnowledgeBase();
        knowledge.addKnowledgeBaseFromFile("shopRule/shopRule_salience.drl");
        KnowledgeBase kBase = Knowledge.kBase;
        StatefulKnowledgeSession kSession = kBase.newStatefulKnowledgeSession();
        kSession.insert(shopCart);
        kSession.fireAllRules();
        response.put("output_data", shopCart.toJSON());
        return response ;
    }
    
    public Map<String,Object> runRuleWithKnowledgeBase(ShopCart shopCart) {
        Map<String,Object> response = new HashMap<String,Object>() ;
        response.put("process", "RUN_RULE_WITH_KNOWLEDGE_BASE");
        response.put("input_data",shopCart.toJSON());
        KnowledgeBase kBase = Knowledge.kBase;
        StatefulKnowledgeSession kSession = kBase.newStatefulKnowledgeSession();
        kSession.insert(shopCart);
        kSession.fireAllRules();
        kSession.dispose();
        response.put("output_data", shopCart.toJSON());
        return response ;
    }
    
    public Map<String,Object> runRuleWithEasyRule(ShopCart shopCart) {
        Map<String,Object> response = new HashMap<String,Object>() ;
        response.put("process", "RUN_RULE_WITH_EASY_RULE");
        response.put("input_data",shopCart.toJSON());
        ShopEasyRule shopEasyRule = new ShopEasyRule(shopCart);
        RulesEngine rulesEngine= aNewRulesEngine().build();
        rulesEngine.registerRule(shopEasyRule);
        rulesEngine.fireRules();
        ShopCart newShopCart = shopEasyRule.getResult();
        response.put("output_data",newShopCart.toJSON());
        return response ;
    }
       
}