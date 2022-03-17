package com.microshop.auth.service;

import com.microshop.auth.entity.AppUser;
import com.microshop.auth.repository.AppUserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Service
@Transactional
public class AppUserService {

    private final AppUserRepository appUserRepository;

    public AppUserService(final AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    public AppUser getById(final Long id) {
        return appUserRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("User not found!"));
    }

    public AppUser getByUsername(final String userName) {
        return appUserRepository.findByUsername(userName).orElseThrow(() -> new RuntimeException("Invalid username or password!"));
    }

    public AppUser add(final AppUser appUser) {
        return appUserRepository.save(appUser);
    }
}
