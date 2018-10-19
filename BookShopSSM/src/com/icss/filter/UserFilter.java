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

import com.icss.util.Log;

/**
 * Servlet Filter implementation class UserFilter
 */
@WebFilter(urlPatterns="/user/*",asyncSupported=true)
public class UserFilter implements Filter {

    /**
     * Default constructor. 
     */
    public UserFilter() {
    	System.out.println("UserFilter  start");
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
	
		Log.logger.info("userfilter---" + this.hashCode());
		
		HttpServletRequest req = (HttpServletRequest)request;
		Object obj = req.getSession().getAttribute("user");
		if(obj == null){
			req.setAttribute("msg", "你无权访问，请先登录");
			req.getRequestDispatcher("/login.jsp").forward(req, response);
		}else{
			chain.doFilter(request, response);                    //继续原来的请求			
		}	
		Log.logger.info("userfilter---end");	
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
