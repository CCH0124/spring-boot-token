package com.cch.demojwt.definition;

import java.util.Arrays;
import java.util.Optional;

public enum ERole {
    ROLE_USER("user"),
    ROLE_MODERATOR("moderator"),
    ROLE_ADMIN("admin");

    private String roleName;

    ERole(String roleName){
        this.roleName = roleName;
    }

    public String getRoleName() {
        return this.roleName;
    }
    
    public static Optional<ERole> getRole(String role) {
        return Arrays.stream(ERole.values())
            .filter(rol -> rol.roleName.equals(role))
            .findFirst();
    }
}
