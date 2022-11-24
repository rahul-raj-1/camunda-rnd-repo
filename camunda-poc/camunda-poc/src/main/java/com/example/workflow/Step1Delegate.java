package com.example.workflow;

import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.BpmnError;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.variable.Variables;
import org.camunda.bpm.engine.variable.value.ObjectValue;

@Slf4j
public class Step1Delegate implements JavaDelegate {
  @Override
  public void execute(DelegateExecution execution) throws Exception {

    int orderId = (int) execution.getVariable(CreateOrderController.ORDER_ID);

    log.info("Step1Delegate {}", orderId);

    // If orderId is "-ve" then RestController should return the below orderResponse object
    // and the workflow should end, i.e it should not go to step 2
    if (orderId < 0) {
      throw new BpmnError("100", "\"FAILED DUE TO INVALID ORDER ID \"");
    }

    OrderResponse orderResponse = new OrderResponse();
    orderResponse.setOrderId(orderId);
    orderResponse.setMessage("Happy camper");

    // Do not store java object directly, but use JSON serialization instead.
    ObjectValue orderObj = Variables
        .objectValue(orderResponse)
        .serializationDataFormat(Variables.SerializationDataFormats.JSON)
        .create();

    execution.setVariable( CreateOrderController.ORDER_RESPONSE, orderObj);
  }

}
