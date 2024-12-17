package org.mnp.orderdrooldemo.services;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.mnp.orderdrooldemo.config.DroolsAppConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.DefaultSingletonBeanRegistry;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import  org.mnp.orderdrooldemo.model.Participant;
import  org.mnp.orderdrooldemo.model.Rate;

@Service("DroolsService")
public class DroolsService {

    public Rate getRate(Participant applicantRequest) {
        KieContainer kieContainer = DroolsAppConfig.getKieContainer();
        Rate rate = new Rate();
        KieSession kieSession = kieContainer.newKieSession();
        kieSession.setGlobal("rate", rate);
        kieSession.insert(applicantRequest);
        kieSession.fireAllRules();
        kieSession.dispose();
        return rate;
    }
}