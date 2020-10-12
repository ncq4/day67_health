package com.itheima.service;

import com.itheima.pojo.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
//如果我们要从数据库动态查询用户信息，就必须按照spring security框架的要求提供一个实现
//UserDetailsService接口的实现类，并按照框架的要求进行配置即可。框架会自动调用实现类中的方法
//并自动进行密码校验。

/*我们提供了UserService实现类，并且按照框架的要求实现了UserDetailsService接口。在spring
配置文件中注册UserService，指定其作为认证过程中根据用户名查询用户信息的处理类。当我们进行
登录操作时，spring security框架会调用UserService的loadUserByUsername方法查询用户信息，并
根据此方法中提供的密码和用户页面输入的密码进行比对来实现认证操作。*/
public class SpringSceurityUserService implements UserDetailsService {
    public static Map<String, com.itheima.pojo.User> map = new HashMap<String, User>();
    static {
        com.itheima.pojo.User user1 = new com.itheima.pojo.User();
        user1.setUsername("admin");
        user1.setPassword("admin");

        com.itheima.pojo.User user2 = new com.itheima.pojo.User();
        user2.setUsername("xiaoming");
        user2.setPassword("1234");

        map.put(user1.getUsername(),user1);
        map.put(user2.getUsername(),user2);
    }
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("用户输入的用户名为："+username);
        User user = map.get(username);
        if (user == null){
            return null;
        }else {
            //将用户信息返回给框架
            //框架会自动进行密码比对
            //GrantedAuthority 用户所拥有的所有权限
            ArrayList<GrantedAuthority> list = new ArrayList();
            list.add(new SimpleGrantedAuthority("permission_A"));
            list.add(new SimpleGrantedAuthority("premission_B"));

            if (username.equals("admin")){
                list.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
            }
            org.springframework.security.core.userdetails.User securityUser = new org.springframework.security.core.userdetails.User(username,"{noop}"+user.getPassword(),list);
            return securityUser;
        }
    }
}
