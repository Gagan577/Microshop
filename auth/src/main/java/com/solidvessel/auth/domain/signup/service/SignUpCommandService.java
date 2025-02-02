package com.solidvessel.auth.domain.signup.service;

import com.solidvessel.auth.domain.appuser.event.UserSavedEvent;
import com.solidvessel.auth.domain.appuser.model.AppUser;
import com.solidvessel.auth.domain.appuser.port.AppUserPort;
import com.solidvessel.auth.domain.common.exception.AuthDomainException;
import com.solidvessel.auth.domain.signup.service.command.SignUpCommand;
import com.solidvessel.shared.domain.event.EventPublisher;
import com.solidvessel.shared.domain.service.CommandService;
import com.solidvessel.shared.domain.service.OperationResult;
import com.solidvessel.shared.domain.service.ResultType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SignUpCommandService implements CommandService<SignUpCommand> {

    private final AppUserPort appUserPort;
    private final EventPublisher<UserSavedEvent> userSavedEventPublisher;

    @Override
    public OperationResult execute(SignUpCommand command) {
        checkIfEmailIsAlreadyRegistered(command.email());
        Long userId = appUserPort.save(AppUser.newAppUser(command.email(), command.password()));
        userSavedEventPublisher.publish(new UserSavedEvent(userId, command.firstName(), command.lastName(), command.email(), command.birthDate(), command.phoneNumber()));
        return new OperationResult("Your registration is successful.", ResultType.SUCCESS);
    }

    private void checkIfEmailIsAlreadyRegistered(String email) {
        if (appUserPort.isEmailRegistered(email)) {
            throw new AuthDomainException("Email is already registered.");
        }
    }
}
