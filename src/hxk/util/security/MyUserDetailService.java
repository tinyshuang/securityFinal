package hxk.util.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import hxk.dao.FunctionDao;
import hxk.dao.UserDao;  


/**
 * 
 * @author hxk
 * @description 用来在用户登录的时候获取这个用户的相关权限
 * 	
 *2015年8月25日  下午4:01:51
 */
public class MyUserDetailService implements UserDetailsService {   

    //登陆验证时，通过username获取用户的所有权限信息，  
    //并返回User放到spring的全局缓存SecurityContextHolder中，以供授权器使用  
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {     
        Collection<GrantedAuthority> auths=new ArrayList<GrantedAuthority>();   
        hxk.model.User myuser = userDao.find(username);
        List<String> functions = functionDao.getAuthority(username);
        for (String string : functions) {
            GrantedAuthority auth=new SimpleGrantedAuthority(string);
            auths.add(auth);
	}
        //包装返回security的User..
        User user = new User(myuser.getName(), myuser.getPwd(), true, true, true, true, auths);   
        return user;    
   }   
    
    
    //注入dao
    private UserDao userDao;
    private FunctionDao functionDao;

    public void setFunctionDao(FunctionDao functionDao) {
        this.functionDao = functionDao;
    }
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

}  
