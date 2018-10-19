package com.icss.dao.batis;

import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Param;

import com.icss.entity.TBook;
import com.icss.entity.TBookType;
import com.sun.org.glassfish.gmbal.ParameterNames;

public interface IBookMapper {
	public List<TBookType> getBookType() throws Exception;
	public void addBook(TBook book) throws Exception;
	public int updateBookCount(@Param("isbn")String isbn,@Param("bcount")int bcount)throws Exception;
	public List<TBook> getCarBooks(@Param("isbns")Set<String> isbns) throws Exception;
	public TBook getBookInfo(String isbn) throws Exception;
	public TBook getPic(String isbn) throws Exception;
	public List<TBook> getAllBooks() throws Exception;

}
