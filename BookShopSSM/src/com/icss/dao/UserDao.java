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
	 * �û������¼��ѯ
	 * @param uname
	 * @param beginDate
	 * @param endDate
	 * @return
	 * @throws Exception
	 */
	public List<Buyinfo> getUserBuyinfo(String uname,
			       Date beginDate,Date endDate,TurnPage tp) throws Exception {
		
		
		tp.allRows = userMapper.getSqlAllRows(uname, beginDate, endDate);                          //�ܼ�¼��
		tp.allPages = (tp.allRows-1)/tp.rows + 1;                      //��ҳ��
		if(tp.page>tp.allPages){
			tp.page = tp.allPages;
		}
		int iStart = (tp.page-1) * tp.rows + 1;
		int iEnd = iStart + tp.rows;
	
		return userMapper.getUserBuyinfo(uname, beginDate, endDate, iStart, iEnd);
	}
	
	/**
	 * �û���ֵ��ۿ�
	 * @param money  money>0��ʾ��ֵ  �� money<0��ʾ�ۿ�
	 */
	public int updateUserAccout(String uname, double money) throws Exception{
	
		return userMapper.updateUserAccout(uname, money);
	}
	
	/**
	 * ��Ӷ���
	 * @param order
	 * @throws Exception
	 */
	public void addOrder(TOrder order) throws Exception{
		userMapper.addOrder(order);
	}
	
	/**
	 * ��Ӷ�����ϸ
	 * @param detail
	 * @throws Exception
	 */
	public void addOrderDetail(TOrderDetail detail)throws Exception{	
		userMapper.addOrderDetail(detail);
	}
	
/**
	 * ��ȡ�����û�
	 * @return
	 * @throws Exception
	 */
	public List<TUser> getAllUser() throws Exception {
		return userMapper.getAllUser();
		
	}
	

	/**
	 * ɾ���û�
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
	 * @return  int��ʾ���µļ�¼����
	 * @throws Exception
	 */
	public int updateUserPwd(String uname,String pwd) throws Exception{
		
		//return userMapper.updateUserPwd(uname, pwd);
		return 0;
	}
 
	
	/**
	 * �û�ע��
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
	  * �û���½
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
		     //����������ѭ����˵���Ѿ���½�ɹ�
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
	  * �û���½
	  * @param uname
	  * @param pwd
	  * @return
	  * @throws Exception
	  */
	 public TUser login(String uname,String pwd) throws Exception {
		
		return userMapper.login(uname, pwd);
	 }
	 

}
