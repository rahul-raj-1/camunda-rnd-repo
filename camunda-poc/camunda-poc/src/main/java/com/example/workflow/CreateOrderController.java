package com.example.workflow;


	import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.history.HistoricVariableInstance;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

	@RestController
	public class CreateOrderController  {
		
		  @Autowired 
		  private RuntimeService runtimeService;
		  
		  @Autowired
		 private  ProcessEngine processEngine;
		  
		  @Autowired
		  private TaskService taskService;
		  
		
		
		@PostMapping("/order/{id}")
		public OrderResponse createOrder(@PathVariable("id") int orderId) {
			
	        
	        Map<String, Object> parameters = new HashMap<>();
	        parameters.put("orderId", orderId);
	        
	        
	 
	        

	        String  pi =    runtimeService.startProcessInstanceByKey("poc-workflow",parameters).getId();
	       
	    
	        List<HistoricVariableInstance> variables = 
	        		processEngine.getHistoryService()
	        		.createHistoricVariableInstanceQuery()
	        		.processInstanceId(pi)
	        		.variableName("orderResponse")
	        		.list();

                      
	        
	        System.out.println("Controller done " );
	        
	        OrderResponse orderResponse = new OrderResponse();
	        orderResponse.setOrderId(orderId);
	        orderResponse.setMessage("SUCCESS");
	        
	        for( HistoricVariableInstance historicVariableInstance : variables)
	        {
	        	orderResponse=(OrderResponse) historicVariableInstance.getValue();
	        }
	        
	        return orderResponse;
			
	        
			
			
		}
		
		
	

}
