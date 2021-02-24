package com.gsg.demo.rest;

import com.gsg.demo.dto.UserAuditDto;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.gsg.demo.service.UserAuditService;

import java.util.List;

@RestController
@RequestMapping("/rest-custom/user")
@AllArgsConstructor
public class UserAuditRestService {

    private final UserAuditService userAuditService;

    @GetMapping("/{userName}")
    public List<UserAuditDto> getUserAuditByUserName(@PathVariable String userName) {
        return userAuditService.getUserAuditByUserName(userName);
    }

}
