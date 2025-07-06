package com.xzit.rental.security;

import com.xzit.rental.entity.Permission;
import com.xzit.rental.entity.User;
import com.xzit.rental.service.IPermissionService;
import com.xzit.rental.service.IUserService;
import jakarta.annotation.Resource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
public class CustomerUserDetailsService implements UserDetailsService {
    @Resource
    private IUserService userService;

    @Resource
    private IPermissionService permissionService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user=userService.selectByUserName(username);
        if (user==null){
            throw new UsernameNotFoundException("用户不存在");
        }
        List<Permission> permissions = permissionService.selectPermissionListByUserId(user.getId());
        user.setPermissionList(permissions);
        List<String> list = permissions.stream().filter(Objects::nonNull)
                .map(Permission::getPermissionCode)
                .filter(Objects::nonNull)
                .toList();

        String[] array = list.toArray(new String[list.size()]);
        List<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(array);
        user.setAuthorities(authorities);
        return user;
    }
}
