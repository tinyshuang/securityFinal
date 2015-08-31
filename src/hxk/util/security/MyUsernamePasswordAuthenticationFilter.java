package hxk.util.security;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import hxk.dao.UserDao;
import hxk.model.User;


/**
 * 
 * @author hxk
 * @description
 *2015年8月28日  下午2:32:50
 */
public class MyUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter{
    	//以下属性是可以使用request来获取值得..对应表单的input值
    	public static final String USERNAME = "name";//登录名
	public static final String PASSWORD = "pwd";//密码
	
	@Resource
	private UserDao userDao;
	
	/**
	 * 这个就是登录验证的方法
	 * 这里我们就可以实现一些自己定义的东西了..
	 * 比如表单的其他属性的传值,如验证码等..
	 * 然后可以实现在登录后要实现的业务逻辑..如记录登录操作..实现单点登录等
	 * 如果要实现异步反馈的话..
	 * 可以通过response来实现 :
	 * 	response.getWriter().write();
	   	response.getWriter().flush();
	   	return null;
	 */
    	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {
    	    String name = obtainUsername(request);
    	    String pwd  = obtainPassword(request);
    	    User user = userDao.find(name);
    	    if(!user.getPwd().equals(obtainPassword(request))){
    		BadCredentialsException exception = new BadCredentialsException("用户名或密码不匹配");
    		throw exception;
    	    }
    	    
    	    //我不知道以下操作是干嘛的...
    	    //应该就是验证,配置user..然后接着去调用UserDetailsService的loadUserByUsername
    	    //实现验证  
    	    UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(name, pwd);  
            //允许设置用户详细属性  
            setDetails(request, authRequest);  
            //运行  
            return this.getAuthenticationManager().authenticate(authRequest);  
    	}

	@Override
	protected String obtainUsername(HttpServletRequest request) {
		Object obj = request.getParameter(USERNAME);
		return null == obj ? "" : obj.toString();
	}

	@Override
	protected String obtainPassword(HttpServletRequest request) {
		Object obj = request.getParameter(PASSWORD);
		return null == obj ? "" : obj.toString();
	}
	
	
}
