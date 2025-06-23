package com.ing.customer.loan.auth;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Collection;

public class AuthenticatedUserDetails {

    public boolean isAdmin(Authentication authentication){






        if (authentication != null && authentication.isAuthenticated()) {
            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

            for (GrantedAuthority authority : authorities) {
                if("ROLE_ADMIN".equals(authority.getAuthority()))
                    return true;
            }
        }
        return false;
        }

    protected final String operatedCustomer(String requestedCustomer){
        boolean isAdmin = false;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication != null && authentication.isAuthenticated()){
            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
            for (GrantedAuthority authority : authorities) {
                if("ROLE_ADMIN".equals(authority.getAuthority()))
                    isAdmin = true;
            }
        }
        if(!isAdmin)
            return SecurityContextHolder.getContext().getAuthentication().getName();
        return requestedCustomer;

        }
}


