package com.icss.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.icss.dao.batis.IBookMapper;
import com.icss.entity.TBook;
import com.icss.entity.TBookType;

@Repository("bookDao")
public class BookDao extends BaseDao{
	
	@Autowired
	private IBookMapper bookMapper;
	/**
	 * 读取图书类型信息
	 * @return
	 * @throws Exception
	 */
	public List<TBookType> getBookType() throws Exception{
		
		return bookMapper.getBookType();		
	}
	
	
	
	/**
	 * 图书上传
	 * @param book
	 * @throws Exception
	 */
	public void addBook(TBook book) throws Exception{
		bookMapper.addBook(book);
	}
	
	
	/**
	 * 增加库存 或减少库存 
	 * @param isbn
	 * @param bcount     bcount>0为加库存,bcount<0为减库存
	 * @return
	 * @throws Exception
	 */
	public int updateBookCount(String isbn,int bcount)throws Exception{
	
		return bookMapper.updateBookCount(isbn, bcount);
	}
	
	/**
	 * 提取购物车中的图书信息 （不含图片）
	 * @return
	 * @throws Exception
	 */
	public List<TBook> getCarBooks(Set<String> isbns) throws Exception{
		
		return bookMapper.getCarBooks(isbns);
		
	}
	
	/**
	 * 提取图书详情 (不包含封面图片)
	 * @param isbn
	 * @return
	 * @throws Exception
	 */
	public TBook getBookInfo(String isbn) throws Exception{
		return bookMapper.getBookInfo(isbn);
	}
	
	/**
	 * 根据图书的isbn，提取封面
	 * @param isbn
	 * @return
	 * @throws Exception
	 */
	public byte[] getPic(String isbn) throws Exception{
		
		TBook book = bookMapper.getPic(isbn);
		return book.getPic();
	}
	
	
	/**
	 * 读取所有图书信息(不包含图片信息)
	 * @return
	 * @throws Exception
	 */
	public List<TBook> getAllBooks() throws Exception{
		List<TBook> books;
		
		books = bookMapper.getAllBooks();
		
		return books;
		
	}

}
