package com.icss.action;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.icss.biz.BookBiz;
import com.icss.biz.UserBiz;
import com.icss.entity.TBook;
import com.icss.entity.TUser;
import com.icss.exception.MoneyNotEnoughException;
import com.icss.util.Log;

@Controller
@RequestMapping("user")
public class PayAction {
	
	@Autowired
	private BookBiz bookBiz;
	@Autowired
	private UserBiz userBiz;
	
	
	@RequestMapping("/checkout")
	public String checkOut(HttpServletRequest request,Model model)
	{
		
		String strRet;
		 Object shopcar = request.getSession().getAttribute("shopcar");
		 if(shopcar != null){			
			  try {
				  //提取购物车中的数据
				  Map<String,Integer> car =  (Map<String,Integer>)shopcar;
				  Set<String> isbns = car.keySet();
				  for(String isbn : isbns){
					  String bcount = request.getParameter(isbn);				 
					  car.put(isbn,  Integer.parseInt(bcount));    //可能在转换时出错
				  }
				  List<TBook> books = bookBiz.getCarBooks(car.keySet());
				  double allPrice = 0;
				  for(TBook bk : books){
					  bk.setMcount(car.get(bk.getIsbn()));
					  allPrice += bk.getPrice() * car.get(bk.getIsbn());
				  }
				  model.addAttribute("allPrice", allPrice);
				  model.addAttribute("books", books);
				  strRet = "/main/checkout.jsp";
				 
			  } catch (Exception e) {
				 Log.logger.error(e.getMessage(),e);
				 model.addAttribute("msg", "网络异常，请和管理员联系");
				  strRet = "/error/error.jsp";
				  
			 }		  
		 }	else
		 {
			 model.addAttribute("msg", "当前购物车里无商品可结算");
			  strRet = "/error/error.jsp";
		 }
		 return strRet;
	}
	
	@RequestMapping("/paymoney")
	public String payMoney(@RequestParam("allmoney")double allPrice,HttpSession session,Model model)
	{
		String strRet;
		 try {
	        	TUser user = (TUser)session.getAttribute("user");
	    	    Map<String,Integer> shopcar = (Map<String,Integer>)session.getAttribute("shopcar");
	    	    
	    		List<TBook> books = bookBiz.getCarBooks(shopcar.keySet());
	    	   
	    		for(TBook bk : books){
	    			  bk.setMcount(shopcar.get(bk.getIsbn()));    			  
	    		}
	    	  
	    	    userBiz.payMoney(user, books, allPrice);	
	    	    //更新session中的用户金额
	    	    double left =  user.getAccount() - allPrice;
	    	    user.setAccount(left); 
	    	    model.addAttribute("allmoney", allPrice);
	    	    strRet = "/main/PayOK.jsp";
	        }catch(MoneyNotEnoughException e){
	        	model.addAttribute("msg", e.getMessage());
	        	strRet = "/error/error.jsp";
				
			} catch (Exception e) {
				Log.logger.error(e.getMessage(),e);
				model.addAttribute("msg", "网络异常，请和管理员联系");
				strRet = "/error/error.jsp";
			}	
		 
		 return strRet;
	}
	
	

}
