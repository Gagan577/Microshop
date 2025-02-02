package com.solidvessel.account.infra.adapter.in.customer.rest.request;

import com.solidvessel.account.domain.customer.service.command.RemoveAddressCommand;
import com.solidvessel.shared.infra.util.SessionUtil;
import jakarta.validation.constraints.NotNull;

public record RemoveAddressRequest(@NotNull String name) {

    public RemoveAddressCommand toCommand() {
        return new RemoveAddressCommand(name, SessionUtil.getCurrentUserId());
    }
}
