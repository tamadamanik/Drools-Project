package Globals;

import org.kie.api.io.ResourceType;
import org.kie.internal.KnowledgeBase;
import org.kie.internal.KnowledgeBaseFactory;
import org.kie.internal.builder.DecisionTableConfiguration;
import org.kie.internal.builder.DecisionTableInputType;
import org.kie.internal.builder.KnowledgeBuilder;
import org.kie.internal.builder.KnowledgeBuilderFactory;
import org.kie.internal.io.ResourceFactory;

public class Knowledge {


    public static KnowledgeBase kBase ;
    public static boolean isActive = false ;
    
    public Knowledge() {}
    
    public void addKnowledgeBaseFromFile(String filePath) {
        if (!isActive) {
            kBase = KnowledgeBaseFactory.newKnowledgeBase();
            isActive = true ;
        }
        KnowledgeBuilder kBuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
        kBuilder.add(ResourceFactory.newClassPathResource(
               filePath), ResourceType.DRL); 
        kBase.addKnowledgePackages(kBuilder.getKnowledgePackages());
    }
    
    public void addKnowledgeBaseFromDSLFile(String filePath, String dslPath) {
        if (!isActive) {
            kBase = KnowledgeBaseFactory.newKnowledgeBase();
            isActive = true ;
        }
        KnowledgeBuilder kBuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
        kBuilder.add(ResourceFactory.newClassPathResource(dslPath),ResourceType.DSL);
        kBuilder.add(ResourceFactory.newClassPathResource(filePath), ResourceType.DSLR);         
        kBase.addKnowledgePackages(kBuilder.getKnowledgePackages());
//        Resource dsl = ResourceFactory.newClassPathResource( dslPath, getClass() );
//        kBuilder.add( dsl, ResourceType.DSL );
//        Resource dslr = ResourceFactory.newClassPathResource( dslrPath, getClass() );
//        kBuilder.add( dslr, ResourceType.DSLR );
    }
    
    public void addKnowledgeBaseFromString(String newRule) {
        if (!isActive) {
            kBase = KnowledgeBaseFactory.newKnowledgeBase();
            isActive = true ;
        }
        KnowledgeBuilder kBuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
        kBuilder.add( ResourceFactory.newByteArrayResource( newRule.getBytes() ), ResourceType.DRL );
        if ( kBuilder.hasErrors() ) {
            System.out.println(kBuilder.getErrors().toString() );
        }                
        kBase.addKnowledgePackages( kBuilder.getKnowledgePackages() );        
    }
    
    public void addKnowledgeFromDecisionTable(String filePath) {
        if (!isActive) {
            kBase = KnowledgeBaseFactory.newKnowledgeBase();
            isActive = true ;
        }
        DecisionTableConfiguration dtconf = KnowledgeBuilderFactory.newDecisionTableConfiguration();
        dtconf.setInputType(DecisionTableInputType.XLS);
        KnowledgeBuilder knowledgeBuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
        knowledgeBuilder.add(ResourceFactory.newClassPathResource(filePath),ResourceType.DTABLE,dtconf);
        if (knowledgeBuilder.hasErrors()) {
            throw new RuntimeException(knowledgeBuilder.getErrors().toString());
        }
        kBase.addKnowledgePackages(knowledgeBuilder.getKnowledgePackages());
    }
    
    public void deleteRuleFromPackage(String packageName, String ruleName) {
        if (!isActive) return ;
        kBase.removeRule(packageName, ruleName);
    }
    
    public void deleteRuleFromWholePackage(String packageName) {
        if (!isActive) return ;
        kBase.removeKnowledgePackage(packageName);
    }

    public void resetKnowledgeBase() {
        if (isActive) kBase = KnowledgeBaseFactory.newKnowledgeBase();
    }
}
