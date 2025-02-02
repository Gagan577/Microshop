package com.solidvessel.auth.infra.adapter.in.logout.rest;

import com.solidvessel.auth.domain.logout.port.LogoutPort;
import com.solidvessel.shared.domain.service.OperationResult;
import com.solidvessel.shared.domain.service.ResultType;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LogoutController {

    private final LogoutPort logoutPort;

    @GetMapping("/logout")
    public OperationResult logout() {
        logoutPort.logout();
        return new OperationResult("Logout successful.", ResultType.SUCCESS);
    }
}
