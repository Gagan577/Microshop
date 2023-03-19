package com.solidvessel.auth.infra.adapter.appuser.db;

import com.solidvessel.auth.domain.appuser.model.AppUser;
import com.solidvessel.auth.domain.appuser.port.AppUserPort;
import com.solidvessel.auth.infra.adapter.appuser.db.entity.AppUserJpaEntity;
import com.solidvessel.auth.infra.adapter.appuser.db.repository.AppUserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AppUserDBAdapter implements AppUserPort {

    private final AppUserRepository appUserRepository;

    @Override
    public AppUser getById(final Long id) {
        AppUserJpaEntity appUserJpaEntity = appUserRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("User not found!"));
        return appUserJpaEntity.toDomainModel();
    }

    @Override
    public AppUser getByUsername(final String userName) {
        AppUserJpaEntity appUserJpaEntity = appUserRepository.findByUsername(userName).orElseThrow(() -> new RuntimeException("User not found!"));
        return appUserJpaEntity.toDomainModel();
    }

    @Override
    public Long add(final AppUser appUser) {
        AppUserJpaEntity appUserJpaEntity = appUserRepository.save(AppUserJpaEntity.from(appUser));
        return appUserJpaEntity.getId();
    }
}
