package hxk.model;

import java.io.Serializable;

/**
 * @author hxk
 * @description
 *2015年8月31日  上午11:50:12
 */
public class Function implements Serializable{
    private static final long serialVersionUID = -792959324409523354L;
    
    private String id;
    private String roleId;
    private String url;
    
    
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getRoleId() {
        return roleId;
    }
    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    
    
}
