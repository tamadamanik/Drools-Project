package Controller;

import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import Service.KnowledgeService;

@RestController
@RequestMapping(value = KnowledgeController.BASE_PATH, produces = {MediaType
        .APPLICATION_JSON_VALUE})
public class KnowledgeController {

    public static final String BASE_PATH = "/knowledge" ;
    
    KnowledgeService knowledgeService = new KnowledgeService() ;
    
    @RequestMapping(value = "/add/drl")
    public Map<String,Object> addFromDRLFile(@RequestParam String filePath) {
        return knowledgeService.addRuleFromFile(filePath);
    }
    
    @RequestMapping(value = "/add/string")
    public Map<String,Object> addFromString(@RequestParam String rule) {
        return knowledgeService.addRuleFromString(rule);
    }
    
    @RequestMapping(value = "/add/dsl")
    public Map<String,Object> addFromDSLFile(@RequestParam String filePath, @RequestParam String dslPath) {
        return knowledgeService.addRuleFromDSLFile(filePath,dslPath);
    }
    
    @RequestMapping(value = "/add/table")
    public Map<String,Object> addFromDecisionTable(@RequestParam String filePath) {
        return knowledgeService.addRuleFromDecisionTable(filePath);
    }
    
    @RequestMapping(value = "/reset")
    public Map<String,Object> resetKnowledge() {
        return knowledgeService.resetKnowledgeBase();
    }
    
    @RequestMapping(value = "/delete/rule")
    public Map<String,Object> deleteRule(@RequestParam String ruleName,@RequestParam String packageName) {
        return knowledgeService.deleteRule(ruleName, packageName);
    }
    
    @RequestMapping(value = "/delete/package")
    public Map<String,Object> deletePackage(@RequestParam String packageName) {
        return knowledgeService.deletePackage(packageName);  
    }
    
    @RequestMapping(value = "/all")
    public Map<String,Object> readAllRule() {
        return knowledgeService.getAllRules();
    }
}
