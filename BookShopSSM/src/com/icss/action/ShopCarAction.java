package com.icss.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.icss.biz.BookBiz;
import com.icss.entity.TBook;
import com.icss.util.Log;

@Controller
@RequestMapping("/user")
public class ShopCarAction {
	
	@Autowired
	private BookBiz bookBiz;
	
	@RequestMapping("/shopcar")
	public String shopCar(HttpSession session,Model model)
	{
		String strRet;
		
		  Object shopcar = session.getAttribute("shopcar");
		  if(shopcar != null){
			 //提取购物车中的数据
			  Map<String,Integer> car =  (Map<String,Integer>)shopcar;
			  try {
				  List<TBook> books = bookBiz.getCarBooks(car.keySet());
				  model.addAttribute("books", books);
				  strRet = "/main/shopcar.jsp";
			  } catch (Exception e) {
				  Log.logger.error(e.getMessage(),e);
				  model.addAttribute("msg", "网络异常，请和管理员联系");
				  strRet = "/error/error.jsp";
			 }				  
		  }else{
			  strRet = "/main/shopcar.jsp";
		  }
			  
		return strRet;
	}
	
	@RequestMapping("/addshopcar")
	public String addShopCar(String isbn,HttpSession session,Model model)
	{
		String strRet;
    	  if(isbn != null){
    		  //加到session中的购物车里
    		  Object shopcar = session.getAttribute("shopcar");
    		  if(shopcar == null){
    			  Map<String,Integer> car = new HashMap<String,Integer>();
    			  car.put(isbn, 1);
    			  session.setAttribute("shopcar", car);
    		  }else{
    			  Map<String,Integer> car =  (Map<String,Integer>)shopcar;
    			  car.put(isbn, 1);        		  
    		  }        		  
    		  //显示购物车中的数据
    		  strRet = "forward:/user/shopcar.do";
    		
    	  }else{
    		  model.addAttribute("msg", "isbn参数丢失");
    		  strRet = "/error/error.jsp";
    	  }  
    	  return strRet;
      
	}
	
	@RequestMapping("/removeshopcar")
	public String removeShopCar(String isbn,HttpSession session,Model model)
	{
		String strRet;
		
		if(isbn != null){
			Object shopcar = session.getAttribute("shopcar");
			if(shopcar != null){
				Map<String,Integer> car =  (Map<String,Integer>)shopcar;
				car.remove(isbn);
				strRet = "forward:/user/shopcar.do";
			
			}else{
				model.addAttribute("msg", "异常访问，请从购物车进入");
				strRet = "/error/error.jsp";
			}		
		}else{
			model.addAttribute("msg", "入参isbn不能为空");
			strRet = "/error/error.jsp";
		}	
		return strRet;
	}

}
