package com.itheima.security;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.pojo.Permission;
import com.itheima.pojo.Role;
import com.itheima.pojo.User;
import com.itheima.service.UserService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
public class SpringSecurityUserService implements UserDetailsService {
    @Reference
    private UserService userService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findByUsername(username);
        if (user == null){
            return null;
        }

        List<GrantedAuthority> list = new ArrayList<>();

        Set<Role> roles = user.getRoles();
        if (roles != null && roles.size() >0){
            for (Role role: roles){
                String keyword = role.getKeyword();
                list.add(new SimpleGrantedAuthority(keyword));
                for (Permission permission : role.getPermissions()){
                    String permissionKeyword = permission.getKeyword();
                    list.add(new SimpleGrantedAuthority(permissionKeyword));
                }
            }
        }
        return new org.springframework.security.core.userdetails.User(username,user.getPassword(),list);
    }
}
