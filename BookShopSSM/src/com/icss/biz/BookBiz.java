package com.icss.biz;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.icss.dao.BookDao;
import com.icss.entity.TBook;
import com.icss.entity.TBookType;

@Service("bookBiz")
@Transactional(readOnly=true)
public class BookBiz {
	@Autowired
	private BookDao bookDao;
	/**
	 * 读取图书类型信息
	 * @return
	 * @throws Exception
	 */
	public List<TBookType> getBookType() throws Exception{			
		return	 bookDao.getBookType();			
	}
	
	/**
	 * 图书上传
	 * @param book
	 * @throws Exception
	 */
	public void addBook(TBook book) throws Exception{
		
		bookDao.addBook(book);	
		
	}
	
	/**
	 * 提取图书详情 (不包含封面图片)
	 * @param isbn
	 * @return
	 * @throws Exception
	 */
	public TBook getBookInfo(String isbn) throws Exception{
		
		return bookDao.getBookInfo(isbn);	
		
	}
	
	/**
	 * 根据图书的isbn，提取封面
	 * @param isbn
	 * @return
	 * @throws Exception
	 */
	public byte[] getPic(String isbn) throws Exception{
		
		return bookDao.getPic(isbn);	
	
	}
	
	/**
	 * 读取所有图书信息(不包含图片信息)
	 * @return
	 * @throws Exception
	 */
	public List<TBook> getAllBooks() throws Exception{
		
		
     	return bookDao.getAllBooks();
		
		
	}
	
	/**
	 * 提取购物车中的图书信息 （不含图片）
	 * @return
	 * @throws Exception
	 */
	public List<TBook> getCarBooks(Set<String> isbns) throws Exception{
		List<TBook> books = null;
		
		if(isbns != null && isbns.size()>0){
			
			books = bookDao.getCarBooks(isbns);	
						
		}
		
		return books;		
	}
	

}
