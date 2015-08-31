package hxk.dao;

import java.util.List;

import hxk.model.Function;

/**
 * @author hxk
 * @description
 *2015年8月31日  下午1:43:43
 */
public interface FunctionDao {
    /**
     * @description 根据用户名字获取其相应的权限	
     * @param name
     * @return
     *2015年8月31日  下午1:44:35
     *返回类型:List<String>
     */
    public List<String> getAuthority(String name);
    
    /**
     * @description 获取全部的权限相关	
     * @return
     *2015年8月31日  下午1:51:54
     *返回类型:List<String>
     */
    public List<Function> getAll();
}
