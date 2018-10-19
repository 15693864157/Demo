package com.icss.action;

import java.io.IOException;

import java.io.PrintWriter;

import javax.servlet.AsyncContext;
import javax.servlet.AsyncEvent;
import javax.servlet.AsyncListener;
import javax.servlet.ReadListener;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.util.Log;

/**
 * Servlet implementation class RegisSvl
 */
@WebServlet(urlPatterns="/RegistSvl",asyncSupported=true)
public class RegisSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisSvl() {
        super();
        // TODO Auto-generated constructor stub
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	//异步非阻塞操作----NIO模型
    	String uname = request.getParameter("uname");
    	AsyncContext  ac = request.startAsync();           //启动了一个新的线程（从托管线程池中获得的）
    	ServletOutputStream out = ac.getResponse().getOutputStream();
    	
    	out.setWriteListener(new WriteListener() {
			
			@Override
			public void onWritePossible() throws IOException {
				if(out.isReady()){                    //必须要等准备好之后，才能写数据
					PrintWriter out = ac.getResponse().getWriter();
			    	if(uname != null && uname.equals("tom")){
						out.println("1");
					}else{
						out.println("0");
					}
					//out.flush();         ------在NIO中不能使用flush()
					out.close();				
					Log.logger.info("RegisSvl---------NIO结束");	
				}				
			}
			
			@Override
			public void onError(Throwable arg0) {
				
				
			}
		});
    	
    	ServletInputStream in = ac.getRequest().getInputStream();
    	in.setReadListener(new ReadListener() {
			
			@Override
			public void onError(Throwable arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onDataAvailable() throws IOException {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onAllDataRead() throws IOException {
				// TODO Auto-generated method stub
				
			}
		});    	
    	ac.complete();                   //必须要做完结操作	
    	Log.logger.info("RegisSvl---------NIO--complete");	
    }
    
    protected void doGet44(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	//异步非阻塞操作
    	String uname = request.getParameter("uname");
    	AsyncContext  ac = request.startAsync();           //启动了一个新的线程（从托管线程池中获得的）
    	ac.start(new Runnable(){
			@Override
			public void run() {
				//从托管线程池中,再次获得一个新线程
				try {
					Thread.sleep(5000);
					PrintWriter out = ac.getResponse().getWriter();
			    	if(uname != null && uname.equals("tom")){
						out.println("1");
					}else{
						out.println("0");
					}
					out.flush();
					out.close();
					Log.logger.info("RegisSvl---------内部线程结束");	
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				ac.complete();                   //必须要做完结操作							
			}  
    		
    	});
    	Log.logger.info("RegisSvl---------runnalbe ok");		
    	
    }
	
    protected void doGet33(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	//异步非阻塞操作
    	String uname = request.getParameter("uname");
    	AsyncContext  ac = request.startAsync();           //启动了一个新的线程（从托管线程池中获得的）
    	ac.setTimeout(2000);                               //设置2秒后超时
    	ac.addListener(new AsyncListener() {
			
			@Override
			public void onTimeout(AsyncEvent arg0) throws IOException {
				Log.logger.info("RegisSvl---------onTimeout");						
			}
			
			@Override
			public void onStartAsync(AsyncEvent arg0) throws IOException {
				Log.logger.info("RegisSvl---------onStartAsync");	
			}
			
			@Override
			public void onError(AsyncEvent arg0) throws IOException {
				Log.logger.info("RegisSvl---------onError");	
				
			}
			
			@Override
			public void onComplete(AsyncEvent arg0) throws IOException {
				Log.logger.info("RegisSvl---------onComplete");				
			}
		});
    	PrintWriter out = ac.getResponse().getWriter();
    	if(uname != null && uname.equals("tom")){
			out.println("1");
		}else{
			out.println("0");
		}
		out.flush();
		out.close();
		//ac.complete();                   //必须要做完结操作
		Log.logger.info("RegisSvl---------doGetOK");		
		
    }
    
	protected void doGet22(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//同步阻塞操作
		String uname = request.getParameter("uname");
		PrintWriter out = response.getWriter();
		if(uname != null && uname.equals("tom")){
			out.println("1");
		}else{
			out.println("0");
		}
		out.flush();
		out.close();
	}

	

}
