package com.icss.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.icss.dao.batis.IUserMapper;
import com.icss.dto.Buyinfo;
import com.icss.dto.TurnPage;
import com.icss.entity.TOrder;
import com.icss.entity.TOrderDetail;
import com.icss.entity.TUser;

@Repository("userDao")
public class UserDao  extends BaseDao{
	
	@Autowired
	private IUserMapper userMapper;
	/**
	 * 用户购买记录查询
	 * @param uname
	 * @param beginDate
	 * @param endDate
	 * @return
	 * @throws Exception
	 */
	public List<Buyinfo> getUserBuyinfo(String uname,
			       Date beginDate,Date endDate,TurnPage tp) throws Exception {
		
		
		tp.allRows = userMapper.getSqlAllRows(uname, beginDate, endDate);                          //总记录数
		tp.allPages = (tp.allRows-1)/tp.rows + 1;                      //总页数
		if(tp.page>tp.allPages){
			tp.page = tp.allPages;
		}
		int iStart = (tp.page-1) * tp.rows + 1;
		int iEnd = iStart + tp.rows;
	
		return userMapper.getUserBuyinfo(uname, beginDate, endDate, iStart, iEnd);
	}
	
	/**
	 * 用户充值或扣款
	 * @param money  money>0表示充值  ， money<0表示扣款
	 */
	public int updateUserAccout(String uname, double money) throws Exception{
	
		return userMapper.updateUserAccout(uname, money);
	}
	
	/**
	 * 添加订单
	 * @param order
	 * @throws Exception
	 */
	public void addOrder(TOrder order) throws Exception{
		userMapper.addOrder(order);
	}
	
	/**
	 * 添加订单明细
	 * @param detail
	 * @throws Exception
	 */
	public void addOrderDetail(TOrderDetail detail)throws Exception{	
		userMapper.addOrderDetail(detail);
	}
	
/**
	 * 读取所有用户
	 * @return
	 * @throws Exception
	 */
	public List<TUser> getAllUser() throws Exception {
		return userMapper.getAllUser();
		
	}
	

	/**
	 * 删除用户
	 * @param uname
	 * @return
	 * @throws Exception
	 */
    public int deleteUser(String uname) throws Exception {
    	return userMapper.deleteUser(uname);
		
	}
	
	/**
	 * 
	 * @param uname
	 * @param pwd
	 * @return  int表示更新的记录数量
	 * @throws Exception
	 */
	public int updateUserPwd(String uname,String pwd) throws Exception{
		
		//return userMapper.updateUserPwd(uname, pwd);
		return 0;
	}
 
	
	/**
	 * 用户注册
	 * @param user
	 * @throws Exception
	 */
	public void regist(TUser user) throws SQLIntegrityConstraintViolationException,Exception {		
		/*String sql = "insert into tuser values(?,?,?,?,?)";
		Connection conn = this.openConnection();
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, user.getUname());
		ps.setString(2, user.getPwd());
		ps.setInt(3, user.getRole());	
		ps.executeUpdate();
		ps.close();*/
	}
	
	/* *//**
	  * 用户登陆
	  * @param uname
	  * @param pwd
	  * @return
	  * @throws Exception
	  *//*
	 public TUser login2(String uname,String pwd) throws Exception {
		 TUser user = null;
		 
		 String sql = "select * from tuser where uname='" + uname + "' and pwd='" + pwd + "'";
		 Connection conn = this.openConnection();
		 Statement st = conn.createStatement();
		 ResultSet rs = st.executeQuery(sql);
		 while(rs.next()) {
		     //如果进入这个循环，说明已经登陆成功
			 int role = rs.getInt("role");
			 int bb = rs.getInt(3);
			 user = new TUser();
			 user.setUname(uname);
			 user.setPwd(pwd);
			 user.setRole(role);
			 user.setAccount(rs.getDouble("account"));
			 user.setAddress(rs.getString("address"));
		 }	
		 rs.close();
		 
		 return user;
		 
		 
	 }
	*/
	 /**
	  * 用户登陆
	  * @param uname
	  * @param pwd
	  * @return
	  * @throws Exception
	  */
	 public TUser login(String uname,String pwd) throws Exception {
		
		return userMapper.login(uname, pwd);
	 }
	 

}
