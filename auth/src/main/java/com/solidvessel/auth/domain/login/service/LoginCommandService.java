package com.solidvessel.auth.domain.login.service;

import com.solidvessel.auth.domain.appuser.model.AppUser;
import com.solidvessel.auth.domain.appuser.port.AppUserPort;
import com.solidvessel.auth.domain.login.port.LoginPort;
import com.solidvessel.auth.domain.login.service.command.LoginCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginCommandService {

    private final AppUserPort appUserPort;
    private final LoginPort loginPort;

    public void login(LoginCommand command) {
        AppUser appUser = appUserPort.getByUsername(command.username());
        if (!appUser.getPassword().equals(command.password())) {
            throw new RuntimeException("Invalid username or password!");
        }
        loginPort.login(appUser.getId().toString());
    }
}
