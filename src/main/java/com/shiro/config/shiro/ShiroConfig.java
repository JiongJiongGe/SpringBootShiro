package com.shiro.config.shiro;

import com.shiro.config.shiro.filter.ShiroCustomAccessFilter;
import com.shiro.config.shiro.realm.ShiroRealm;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

	@Bean
	public ShiroFilterFactoryBean shirFilter(SecurityManager securityManager) {
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
		shiroFilterFactoryBean.setSecurityManager(securityManager);
		/**
		 * 拦截器，配置不会被shiro拦截的路径
		 */
		Map<String,String> filterChainDefinitionMap = new LinkedHashMap<String,String>();
		Map<String, Filter> filters = new LinkedHashMap<>();
		filters.put("authc", getCustomAccessFilter());
		filterChainDefinitionMap.put("/shiro/filer", "anon");
		filterChainDefinitionMap.put("/logout", "logout");
		filterChainDefinitionMap.put("/**", "authc");
		shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
		/**
		 * 配置未验证成功跳转的路径
		 */
		shiroFilterFactoryBean.setLoginUrl("/login");
		/**
		 * 配置验证成功后跳转的路径
		 */
		//shiroFilterFactoryBean.setSuccessUrl("/index");
		/**
		 * 未经授权跳转的路径
		 */
		//shiroFilterFactoryBean.setUnauthorizedUrl("/shiro/no/permission");
		shiroFilterFactoryBean.setFilters(filters);
		return shiroFilterFactoryBean;
	}

	/**
	 * 配置安全数据源，shiro从数据库中获取安全数据(用户、角色、权限)
	 *
	 * @return
	 */
	@Bean
	public ShiroRealm shiroRealm(){
		ShiroRealm shiroRealm = new ShiroRealm();
		return shiroRealm;
	}

	@Bean
	public ShiroCustomAccessFilter getCustomAccessFilter(){
		return new ShiroCustomAccessFilter();
	}

	/**
	 * 配置securityManager,通过Realm获取相应的用户身份确认合法性
	 * @return
	 */
	@Bean
	public SecurityManager securityManager(){
		DefaultWebSecurityManager securityManager =  new DefaultWebSecurityManager();
		securityManager.setRealm(shiroRealm());
		return securityManager;
	}

	/**
	 * 开启shiro aop注解支持.
	 * 使用代理方式;所以需要开启代码支持;
	 * @param securityManager
	 * @return
	 */
	@Bean
	public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager){
		AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
		authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
		return authorizationAttributeSourceAdvisor;
	}


}