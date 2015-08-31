# securityFinal

项目说明 : 
	这是一个spring-security的Demo..第三版...基本可以拿来作为一个项目的雏形的权限设计了..
	
	
数据库表 :
user --用户表 
		*name
		 *pwd
    	 *roleid  //关键是这个属性
    
role --角色表
	*id  					//角色id
	*roleName		//角色名字

function
	*id			//功能表id
	*roleid		//角色id
	*url			//相应的路径


****重点*****	
我们以角色表为中间表,关联起来用户表和功能权限表...

security在初始化时会读入全部的权限设计....
比如url--->{functionid}  //字符串-->集合的 Map

用户登录时会保存一个用户的{functionids}

最后用户每访问一个路径时,security会根据这个url找到对应的{system-functionids}集合,然后再去判断用户的权限{user-functionids}是否有这个值..

****重点解释*****	


关于权限设计这一块,看项目需要...你可以设计得更细分..设计更多的表来区分权限..中心思想类似于我上面说的..

项目里面以及配套了sql语句.
admin用户可以访问全部页面,tinys用户只可以访问index页面..
login没设置拦截...accessDeny是权限不足返回的页面..
这里本来想统筹spring mvc...但是为了体现security,就直接使用jsp来替代了...

主要的代码在于 :
util.security包..以及spring-security.xml配置文件



