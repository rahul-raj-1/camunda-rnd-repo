package com.example.workflow;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;
@Component
public class Step2Delegate implements JavaDelegate {

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		
               int orderId = (int) execution.getVariable("orderId");
		
               System.out.println(" Step2Delegate :  " + orderId);
		
		
	}

}
