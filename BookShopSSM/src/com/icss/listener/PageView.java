package com.icss.listener;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;

import com.icss.util.Log;

/**
 * Application Lifecycle Listener implementation class PageView
 *
 */
@WebListener
public class PageView implements ServletRequestListener {

	private Integer allPageView;
	
    /**
     * Default constructor. 
     */
    public PageView() {
    	allPageView = 0;
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
    	allPageView += 1;
    	//Log.logger.info("allPageView=" + allPageView);
    }
	
}
