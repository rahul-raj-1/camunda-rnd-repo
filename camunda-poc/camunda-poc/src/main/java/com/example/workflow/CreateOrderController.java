package com.example.workflow;


	import java.util.HashMap;
import java.util.Map;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

	@RestController
	public class CreateOrderController  {
		
		  @Autowired 
		  private RuntimeService runtimeService; 
		  
		
		
		@PostMapping("/order/{id}")
		public OrderResponse createOrder(@PathVariable("id") int orderId) {
			
	        
	        Map<String, Object> parameters = new HashMap<>();
	        parameters.put("orderId", orderId);
	        
	        
	 
	        

	        runtimeService.startProcessInstanceByKey("poc-workflow",parameters);
	       
	    


	        
	        System.out.println("done");
	        
	        
	        OrderResponse orderResponse = new OrderResponse();
	        orderResponse.setOrderId(orderId);
	        orderResponse.setMessage("SUCCESS");
	        
	        
	      

			return orderResponse;
			
			
			
		}
		
		
	

}
