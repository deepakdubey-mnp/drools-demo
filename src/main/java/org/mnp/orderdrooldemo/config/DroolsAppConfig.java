package org.mnp.orderdrooldemo.config;

import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.KieModule;
import org.kie.api.runtime.KieContainer;
import org.kie.internal.io.ResourceFactory;


public class DroolsAppConfig {

    private static final String RULES_PATH = "rules/loan_rate.drl";
    private static KieContainer kieContainer;
    private DroolsAppConfig(){}

    private static KieContainer kieContainer() {
        KieServices kieServices = KieServices.Factory.get();
        kieServices.newKieBaseConfiguration();
        KieFileSystem kieFileSystem = kieServices.newKieFileSystem();
        kieFileSystem.write(ResourceFactory.newFileResource("/Users/Deepak_Dubey/Documents/CODE/spring-samples/order-drool-demo/src/main/resources/rules/loan_rate.drl"));
        KieBuilder kb = kieServices.newKieBuilder(kieFileSystem);
        kb.buildAll();
        KieModule kieModule = kb.getKieModule();
        KieContainer kieContainer = kieServices.newKieContainer(kieModule.getReleaseId());
        return kieContainer;
    }
    public static KieContainer getKieContainer() {
        if(kieContainer == null) {
            kieContainer = kieContainer();
        }
        return kieContainer;
    }
    public static void refresh() {
        System.out.println("Refreshing KieContainer");
        kieContainer=null;
        getKieContainer();
    }
}
