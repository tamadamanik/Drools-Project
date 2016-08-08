package Service;

import java.util.HashMap;
import java.util.Map;

import org.kie.api.definition.rule.Rule;
import org.kie.internal.definition.KnowledgePackage;

import Globals.Knowledge; 

public class KnowledgeService {

    Knowledge knowledge = new Knowledge() ;
    
    public Map<String,Object> addRuleFromString(String rule) {
        Map<String,Object> response= new HashMap<String,Object>();
        response.put("process","ADD_RULE_FROM_STRING");
        response.put("new_rule",rule);
        knowledge.addKnowledgeBaseFromString(rule);
        return response ;
    }
    
    public Map<String,Object> addRuleFromFile(String filePath) {
        Map<String,Object> response= new HashMap<String,Object>();
        response.put("process","ADD_RULE_FROM_FILE");
        response.put("file_path",filePath);
        knowledge.addKnowledgeBaseFromFile(filePath);
        return response ;
    }
    
    public Map<String,Object> addRuleFromDSLFile(String filePath,String dslPath) {
        Map<String,Object> response= new HashMap<String,Object>();
        response.put("process","ADD_RULE_FROM_DSL_FILE");
        response.put("file_path",filePath);
        knowledge.addKnowledgeBaseFromDSLFile(filePath,dslPath);
        return response ;
    }
    
    public Map<String,Object> addRuleFromDecisionTable(String filePath) {
        Map<String,Object> response= new HashMap<String,Object>();
        response.put("process","ADD_RULE_FROM_DECISION_TABLE");
        response.put("file_path",filePath);
        knowledge.addKnowledgeFromDecisionTable(filePath);
        return response ;
    }
    
    public Map<String,Object> deleteRule(String ruleName, String packageName) {
        Map<String,Object> response= new HashMap<String,Object>();
        response.put("process","DELETE_RULE");
        response.put("rule_name",ruleName);
        response.put("package_name",packageName);
        knowledge.deleteRuleFromPackage(packageName, ruleName);
        return response ;
    }
    
    public Map<String,Object> deletePackage(String packageName) {
        Map<String,Object> response= new HashMap<String,Object>();
        response.put("process","DELETE_PACKAGE");
        response.put("package_name",packageName);
        knowledge.deleteRuleFromWholePackage(packageName);
        return response ;
    }
    
    public Map<String,Object> resetKnowledgeBase() {
        Map<String,Object> response= new HashMap<String,Object>();
        response.put("process","RESET_KNOWLEDGE");
        knowledge.resetKnowledgeBase();
        return response ;
    }
    
    public Map<String,Object> getAllRules() {
        int totalPackage=0, totalRules ;
        Map<String,Object> response= new HashMap<String,Object>();
        response.put("process","READ_ALL_RULES");
        if (Knowledge.isActive) {
            for (KnowledgePackage pckg : Knowledge.kBase.getKnowledgePackages()) {
                totalPackage++;
                totalRules=0 ;
                Map<String,Object> rules = new HashMap<String,Object>() ;
                rules.put("name", pckg.getName());
                for (Rule rule : pckg.getRules()) {
                    totalRules++;
                    rules.put("Rules "+Integer.toString(totalRules), rule.getName());
                }
                rules.put("total rules", totalRules);
                response.put("Package "+Integer.toString(totalPackage),rules);                
            }
        }
        response.put("total package", totalPackage);
        return response ;
    }
}
