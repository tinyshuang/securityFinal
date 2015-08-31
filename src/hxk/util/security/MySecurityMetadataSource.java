package hxk.util.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;

import hxk.dao.FunctionDao;
import hxk.model.Function;

public class MySecurityMetadataSource implements FilterInvocationSecurityMetadataSource {
    private static Logger logger = Logger.getLogger(MySecurityMetadataSource.class);
    
    //声明url与角色的关系
    private static Map<String, Collection<ConfigAttribute>> map = null;  

    
    
    public MySecurityMetadataSource() {}

    /**
     * 这里得使用spring的构造注入保证dao已经注入进去了..然后才能调用loadResourceDefine方法..
     * @param functionDao
     */
    public MySecurityMetadataSource(FunctionDao functionDao) {
	this.functionDao = functionDao;
	loadResourceDefine();
    }
    
    
    /**
     * @description 加载整个系统的权限设计	
     * 
     *2015年8月31日  下午3:03:53
     *返回类型:void
     */
    private void loadResourceDefine() {
	if (map == null) {
	    map = new HashMap<String, Collection<ConfigAttribute>>();
	    List<Function> functions = functionDao.getAll();
	    for (Function function : functions) {
		//这里因为同一个url可能对应多个权限值,所以当存在时,得从map拿
		Collection<ConfigAttribute> atts;
		if(map.containsKey(function.getUrl())){
		    atts = map.get(function.getUrl());
		}
		else{
		    atts = new ArrayList<ConfigAttribute>(); 
		}
		ConfigAttribute configAttribute = new SecurityConfig(function.getId());
		atts.add(configAttribute);
		map.put(function.getUrl(), atts);
	    }
	}
    }

    
    

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
	return null;
    }
    
    
    @Override
    /**
     * @description 获取用户访问的地址所需的权限
     *2015年8月25日  下午4:19:16
     *返回类型:void
     */
    public Collection<ConfigAttribute> getAttributes(Object arg0) throws IllegalArgumentException {
	FilterInvocation invation = (FilterInvocation) arg0;
	String requestUrl = invation.getRequestUrl();
	requestUrl=requestUrl.substring(1,requestUrl.length());
	int flag =requestUrl.indexOf("?");
	if(flag>0){
		requestUrl=requestUrl.substring(0,flag);
	}
	logger.info("=============> url :" + requestUrl);
	if(map == null) {
		try {
			//执行加载权限方法
			loadResourceDefine();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	logger.info("==============> function : " + map.get(requestUrl));
	return map.get(requestUrl);
    }

    @Override
    public boolean supports(Class<?> arg0) {
	//这里返回true就可以解决无法注入的问题
	//特么这里一定要返回true
	return true;
    }
    
    
    
    private FunctionDao functionDao;


    public void setFunctionDao(FunctionDao functionDao) {
        this.functionDao = functionDao;
    }
    
    
	


}
