package com.icss.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import com.icss.entity.IRole;
import com.icss.entity.TUser;

/**
 * Servlet Filter implementation class AdminFilter
 */
@WebFilter(urlPatterns="/back/*",asyncSupported=true)
public class AdminFilter implements Filter {

    /**
     * Default constructor. 
     */
    public AdminFilter() {
    	System.out.println("AdminFilter  start");
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
	
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		//1. 用户必须已经登录
		//2. 已登录用户，具有管理员的权限
		HttpServletRequest req = (HttpServletRequest)request;
		Object obj = req.getSession().getAttribute("user");
		if(obj == null){
			req.setAttribute("msg", "你无权访问，请先登录");
			req.getRequestDispatcher("/login.jsp").forward(req, response);
		}else{
			//判断权限
			TUser user = (TUser)obj;
			if(user.getRole() == IRole.ADMIN){
				chain.doFilter(request, response);                    //继续原来的请求	
			}else{
				req.setAttribute("msg", "你无权访问，请重新登录");
				req.getRequestDispatcher("/login.jsp").forward(req, response);
			}						
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
