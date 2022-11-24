package com.example.workflow;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.runtime.MessageCorrelationResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MessageEndEvent implements JavaDelegate{
	
	

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		
		System.out.println(" In MessageEndEvent ");
		// TODO Auto-generated method stub
					execution.
				getProcessEngine().
				getRuntimeService().
				createMessageCorrelation("my-message")
				 .processInstanceBusinessKey("poc-workflow")
				 .setVariable("payment_type", "creditCard")
				  .correlate();
				 
		
		System.out.println("End here ");
		
	}

}
