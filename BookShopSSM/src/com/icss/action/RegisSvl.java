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
    	//�첽����������----NIOģ��
    	String uname = request.getParameter("uname");
    	AsyncContext  ac = request.startAsync();           //������һ���µ��̣߳����й��̳߳��л�õģ�
    	ServletOutputStream out = ac.getResponse().getOutputStream();
    	
    	out.setWriteListener(new WriteListener() {
			
			@Override
			public void onWritePossible() throws IOException {
				if(out.isReady()){                    //����Ҫ��׼����֮�󣬲���д����
					PrintWriter out = ac.getResponse().getWriter();
			    	if(uname != null && uname.equals("tom")){
						out.println("1");
					}else{
						out.println("0");
					}
					//out.flush();         ------��NIO�в���ʹ��flush()
					out.close();				
					Log.logger.info("RegisSvl---------NIO����");	
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
    	ac.complete();                   //����Ҫ��������	
    	Log.logger.info("RegisSvl---------NIO--complete");	
    }
    
    protected void doGet44(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	//�첽����������
    	String uname = request.getParameter("uname");
    	AsyncContext  ac = request.startAsync();           //������һ���µ��̣߳����й��̳߳��л�õģ�
    	ac.start(new Runnable(){
			@Override
			public void run() {
				//���й��̳߳���,�ٴλ��һ�����߳�
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
					Log.logger.info("RegisSvl---------�ڲ��߳̽���");	
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				ac.complete();                   //����Ҫ��������							
			}  
    		
    	});
    	Log.logger.info("RegisSvl---------runnalbe ok");		
    	
    }
	
    protected void doGet33(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	//�첽����������
    	String uname = request.getParameter("uname");
    	AsyncContext  ac = request.startAsync();           //������һ���µ��̣߳����й��̳߳��л�õģ�
    	ac.setTimeout(2000);                               //����2���ʱ
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
		//ac.complete();                   //����Ҫ��������
		Log.logger.info("RegisSvl---------doGetOK");		
		
    }
    
	protected void doGet22(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//ͬ����������
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
