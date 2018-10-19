package com.icss.listener;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.icss.util.Log;

/**
 * Application Lifecycle Listener implementation class OnlineUser
 *
 */
@WebListener
public class OnlineUser implements HttpSessionListener {

	private Map<String,HttpSession> userMap;
	
    /**
     * Default constructor. 
     */
    public OnlineUser() {
    	userMap = new HashMap<String,HttpSession>();
    }

	/**
     * @see HttpSessionListener#sessionCreated(HttpSessionEvent)
     */
    public void sessionCreated(HttpSessionEvent arg0)  { 
    	Object obj = arg0.getSession().getServletContext().getAttribute("OnlineUser");
    	Map<String,HttpSession> users;
    	if(obj != null){
    		users = (Map<String,HttpSession>)obj;
    		users.put(arg0.getSession().getId(), arg0.getSession());
    	}else{
    		users = new HashMap<>();
    		users.put(arg0.getSession().getId(), arg0.getSession());
    		arg0.getSession().getServletContext().setAttribute("OnlineUser", users);
    	}
    	Log.logger.info("在线用户数：" + users.size());
    	
    	userMap.put(arg0.getSession().getId(), arg0.getSession());
    	
    	Log.logger.info("在线用户数22*******" + userMap.size());
    	
    }

	/**
     * @see HttpSessionListener#sessionDestroyed(HttpSessionEvent)
     */
    public void sessionDestroyed(HttpSessionEvent arg0)  { 
         //主动调用request.getSession().invalidate(); 或者超时调用,都会触发这个事件
    	Map<String,HttpSession> users = (Map<String,HttpSession>)arg0.getSession().getServletContext().getAttribute("OnlineUser");
    	users.remove(arg0.getSession().getId());
    	Log.logger.info("----剩余在线用户数：" + users.size());
    	
    	userMap.remove(arg0.getSession().getId());
    	Log.logger.info("**************剩余在线用户数22：" + userMap.size());
    	
    }
	
}
