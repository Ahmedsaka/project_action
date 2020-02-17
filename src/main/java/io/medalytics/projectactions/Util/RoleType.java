package io.medalytics.projectactions.Util;

import lombok.Getter;

@Getter
public enum RoleType {

    ROLE_USER("ROLE_USER"),
    ROLE_ADMIN("ROLE_ADMIN");

    private String value;

    RoleType(String value) {
        this.value = value;
    }
}
