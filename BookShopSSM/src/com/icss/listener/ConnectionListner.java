package com.icss.listener;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;
/**
 * Application Lifecycle Listener implementation class ConnectionListner
 *
 */
@WebListener
public class ConnectionListner implements ServletRequestListener {

    /**
     * Default constructor. 
     */
    public ConnectionListner() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletRequestListener#requestDestroyed(ServletRequestEvent)
     */
    public void requestDestroyed(ServletRequestEvent arg0)  { 
    	
    }

	/**
     * @see ServletRequestListener#requestInitialized(ServletRequestEvent)
     */
    public void requestInitialized(ServletRequestEvent arg0)  {
    	 //不能在此处打开数据库，应该在dao层打开数据库    	
    }
	
}
