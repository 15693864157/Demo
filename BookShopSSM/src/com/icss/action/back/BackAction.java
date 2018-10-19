package com.icss.action.back;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.icss.biz.BookBiz;
import com.icss.biz.UserBiz;
import com.icss.dto.Buyinfo;
import com.icss.dto.TurnPage;
import com.icss.entity.TBook;
import com.icss.entity.TBookType;
import com.icss.util.Log;

@Controller
@RequestMapping("/back")
public class BackAction {
	
	@Autowired
	private UserBiz userBiz;
	
	@Autowired
	private BookBiz bookBiz;
	
	@RequestMapping(value="/upfile",method=RequestMethod.GET)
	public String addBook(Model model){
		String strRet;
		try {
			List<TBookType> types = bookBiz.getBookType();
			model.addAttribute("types",types);
			strRet = "/back/BookAdd.jsp";
		} catch (Exception e) {
			Log.logger.error(e.getMessage(),e);
			model.addAttribute("msg", "网络异常，请和管理员联系");
		    strRet = "/error/error.jsp";
		}
		return strRet;
		
	}
	
	@RequestMapping(value="/upfile",method=RequestMethod.POST)
	public String addBook(TBook book,@RequestParam("pic2") MultipartFile file,Model model){
		String strRet = null;
		
		try {
			if (!file.isEmpty()) {
				byte[] bytes = file.getBytes();
				book.setPic(bytes);
			}	
		} catch (Exception e) {
			Log.logger.error(e.getMessage(),e);
		}
		try {
			bookBiz.addBook(book);				   
		    List<TBookType> types = bookBiz.getBookType();
		    model.addAttribute("types",types);
		    model.addAttribute("msg", "添加成功--" +book.getBname());
			strRet = "/back/BookAdd.jsp";	
		} catch (Exception e) {
			Log.logger.error(e.getMessage(),e);
			model.addAttribute("msg", "网络异常，请和管理员联系");
			strRet = "/error/error.jsp";
		}
		
		return strRet;
	}

	@RequestMapping("/buyinfo")
	public String buyinfo(HttpServletRequest request){
		String strRet;
		
		   String uname = request.getParameter("uname");
		   String begin = request.getParameter("beginDate");
		   String end = request.getParameter("endDate");
		   String page = request.getParameter("page");	   
		   SimpleDateFormat sd = new SimpleDateFormat("MM/dd/yyyy");
		   Date beginDate = null,endDate = null;
		   try {
			   if(begin != null && !begin.equals("")){
				   beginDate = sd.parse(begin);
			   }
			   if(end != null && !end.equals("")){
				   endDate = sd.parse(end);
			   }
			   TurnPage tp = new TurnPage();
			   tp.rows = 8;
			   if(page != null){
				   int iPage = Integer.parseInt(page);
				   if(iPage<1){
					   iPage = 1;
				   }
				   tp.page = iPage;         //当前页号，永远不能<1   
			   }		   
			   List<Buyinfo> buyList = userBiz.getUserBuyinfo(uname, beginDate,endDate,tp);
			   request.setAttribute("buyList", buyList);
			   request.setAttribute("uname", uname);
			   request.setAttribute("begin", begin);
			   request.setAttribute("end", end);
			   request.setAttribute("tp", tp);
			   strRet = "/back/BuyinfoList.jsp";
		   } catch (Exception e) {
			   Log.logger.error(e.getMessage(),e);
			   request.setAttribute("msg", "网络异常，请和管理员联系");
			   strRet = "/error/error.jsp";
		   }
		   
		   return strRet;
	}
}
