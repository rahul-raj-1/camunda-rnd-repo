package com.example.workflow;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class Step1Delegate implements JavaDelegate {

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		
               int orderId = (int) execution.getVariable("orderId");
		
		System.out.println(" Step1Delegate :  " + orderId);
		
		if(orderId < 0 )
		{
			
			// If orderId is "-ve" then RestController should return the below orderResponse object
			// and the workslow should end i.e it should not go to step 2
			OrderResponse orderResponse = new OrderResponse();
	        orderResponse.setOrderId(orderId);
	        orderResponse.setMessage("FAILED DUE TO INVALID ORDER ID ");
	        
	        execution.setVariable("orderResponse", orderResponse);
	        return;
	        
		}
		
	}

}
