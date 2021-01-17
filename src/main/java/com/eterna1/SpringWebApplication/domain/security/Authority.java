package com.eterna1.SpringWebApplication.domain.security;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum Authority {
    ADMINAuth("Admin"),
    ADMINRole("ROLE_Admin");

    private String authority;
}
