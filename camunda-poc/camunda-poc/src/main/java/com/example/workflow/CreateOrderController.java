package com.example.workflow;


import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.javassist.compiler.ast.Variable;
import org.camunda.bpm.engine.HistoryService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.history.HistoricVariableInstance;
import org.camunda.bpm.engine.variable.Variables;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
public class CreateOrderController {
  public static final String ORDER_RESPONSE = "orderResponse";
  public static final String ORDER_ID = "orderId";
  @Autowired
  private RuntimeService runtimeService;

  @Autowired
  private HistoryService historyService;

  @PostMapping("/order/{id}")
  public OrderResponse createOrder(@PathVariable("id") int orderId) {

    Map<String, Object> parameters = new HashMap<>();
    parameters.put(ORDER_ID, orderId);

    String processInstanceId = runtimeService.startProcessInstanceByKey("poc-workflow", parameters).getId();
    log.info("Started process instance: {}", processInstanceId);

    HistoricVariableInstance variable = historyService.createHistoricVariableInstanceQuery()
        .processInstanceId(processInstanceId)
        .variableName(ORDER_RESPONSE)
        .singleResult();

    OrderResponse orderResponse;

    if (null!=variable) orderResponse = (OrderResponse) variable.getValue();
    else
    {
      orderResponse = new OrderResponse();
      orderResponse.setOrderId(orderId);
      orderResponse.setMessage("Meh");
    }

    return orderResponse;
  }
}

