//package com.riznuchok.security;
//
//import com.riznuchok.entity.Role;
//import com.riznuchok.entity.User;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import java.util.*;
//import java.util.stream.Collectors;
//
//public class UserDetailsDecorator implements UserDetails {
//
//    public static final String ROLES_PREFIX = "ROLE_";
//
//    private User user;
//
//    public UserDetailsDecorator(User user) {
//        this.user = user;
//    }
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        List<String> rolesList = new ArrayList<>();
//        rolesList.add(user.getRole().name());
//        String roles[] = (String[]) rolesList.toArray();
//
//        if (roles == null) {
//            return Collections.emptyList();
//        }
//
//        return Arrays.stream(roles).map(
//                role -> (GrantedAuthority) () -> ROLES_PREFIX + role
//        ).collect(Collectors.toList());
//    }
//
//    @Override
//    public String getPassword() {
//        return user.getPassword();
//    }
//
//    @Override
//    public String getUsername() {
//        return user.getEmail();
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return true;
//    }
//
//}
