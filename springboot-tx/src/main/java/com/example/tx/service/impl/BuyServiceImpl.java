package com.example.tx.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.tx.exception.InsufficientAmount;
import com.example.tx.service.BookService;
import com.example.tx.service.BuyService;

@Service	// 交易服務
public class BuyServiceImpl implements BuyService {

	@Autowired
	private BookService bookService;
	
	@Transactional(			// 預設為 Propagation.REQUIRED, 需加入 rollbackFor = {InsufficientAmount.class} 受檢例外(try)沒有回滾 (只有runTimeException會回滾)
			propagation =  Propagation.REQUIRED,
			rollbackFor = {InsufficientAmount.class}
			)

	@Override
	public void buyOneBook(String username, Integer bookId) throws InsufficientAmount{
		System.out.println(username+"購買書籍");
		
		// 1. 查詢書本價格
		Integer bookPrice = bookService.getBookPrice(bookId);
		System.out.println("bookId price: "+bookPrice);
		
		// 2. 減去庫存 (1本)
		bookService.reduceBookAmount(bookId, 1);
		
		// 3. 修改餘額
		bookService.reduceWalletBalance(username, bookPrice) ;
		
		// 4. 其他處理
		System.out.println(username+"結帳完成...");
		
	}

}
