package com.gsg.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAuditDto implements Serializable {
    private String userId;
    private String operationType;
    private String entityType;
    private String property;
    private String originalValue;
    private String newValue;
    private String processDefinitionKey;
    private String processInstanceId;
    private String taskId;
    private String timestamp;
}
