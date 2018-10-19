package com.icss.dao.batis;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.icss.dto.Buyinfo;
import com.icss.dto.TurnPage;
import com.icss.entity.TOrder;
import com.icss.entity.TOrderDetail;
import com.icss.entity.TUser;

public interface IUserMapper {
	
	public int updateUserAccout(@Param("uname")String uname, @Param("money")double money) throws Exception;
	
	public int getSqlAllRows(@Param("uname")String uname,
		       @Param("beginDate")Date beginDate,@Param("endDate")Date endDate) throws Exception;
	
	public List<Buyinfo> getUserBuyinfo(@Param("uname")String uname,
			@Param("beginDate")Date beginDate,@Param("endDate") Date endDate,
			@Param("iStart")int iStart,@Param("iEnd")int iEnd) throws Exception;
	
	public void addOrder(TOrder order) throws Exception;
	public void addOrderDetail(TOrderDetail detail)throws Exception;
	public List<TUser> getAllUser() throws Exception;
	public int deleteUser(@Param("uname")String uname) throws Exception;
	public int updateUserPwd(@Param("uname")String uname,@Param("pwd")String pwd) throws Exception;
	public void regist(TUser user) throws SQLIntegrityConstraintViolationException,Exception;
	public TUser login(@Param("uname")String uname,@Param("pwd")String pwd) throws Exception;

}
