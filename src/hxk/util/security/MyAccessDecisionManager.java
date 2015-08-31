package hxk.util.security;

import java.util.Collection;
import java.util.Iterator;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;  

/**
 * 
 * @author hxk
 * @description 检查用户是否够权限访问资源 
 *2015年8月25日  下午4:11:28
 */
public class MyAccessDecisionManager implements AccessDecisionManager {  
      
    //检查用户是否够权限访问资源  
    //参数authentication是从spring的全局缓存SecurityContextHolder中拿到的，里面是用户的权限信息  
    //参数object是url  
    //参数configAttributes所需的权限  
    @Override
    public void decide(Authentication authentication, Object object,Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {  
        if(configAttributes == null){   
            return;         
        }    
          
        Iterator<ConfigAttribute> ite=configAttributes.iterator();  
        while(ite.hasNext()){  
            ConfigAttribute ca=ite.next();    
            String needRole=((SecurityConfig)ca).getAttribute();  
            for(GrantedAuthority ga : authentication.getAuthorities()){   
                if(needRole.equals(ga.getAuthority())){    
                    return;                
                }              
            }        
        }   
        //注意：执行这里，后台是会抛异常的，但是界面会跳转到所配的access-denied-page页面  
        throw new AccessDeniedException("no right");     
    }  
    
    @Override
    public boolean supports(ConfigAttribute attribute) {   
        return true;  
    }  
    @Override
    public boolean supports(Class<?>clazz) {  
        return true;   
        }   
    } 
