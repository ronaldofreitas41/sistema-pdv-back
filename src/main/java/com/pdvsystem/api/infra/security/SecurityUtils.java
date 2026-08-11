package com.pdvsystem.api.infra.security;

import com.pdvsystem.api.domain.user.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public final class SecurityUtils {

    private SecurityUtils() {
    }

    public static String getCompanyId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication.getPrincipal() == null || !(authentication.getPrincipal() instanceof User user)) {
            throw new RuntimeException("Usuário não autenticado");
        }
        return user.getCompanyId();
    }
}
