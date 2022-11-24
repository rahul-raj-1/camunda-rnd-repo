package com.example.workflow;

import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

@Slf4j
public class Step2Delegate implements JavaDelegate {

  @Override
  public void execute(DelegateExecution execution) throws Exception {

    int orderId = (int) execution.getVariable(CreateOrderController.ORDER_ID);
    log.debug("Step2Delegate {}", orderId);

  }

}
