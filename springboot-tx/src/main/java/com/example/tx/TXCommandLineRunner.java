package com.example.tx;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.tx.entity.Book;
import com.example.tx.exception.InsufficientAmount;
import com.example.tx.repository.BookRepository;
import com.example.tx.service.BuyService;

@Component
public class TXCommandLineRunner implements CommandLineRunner{

	@Autowired
	private BuyService buyService;
	
	@Autowired
	private BookRepository bookRepository;
	
	@Override
//	@Transactional	// (Managed Entity: entity 會與資料表保持同步, 可不需要手動儲存)
	public void run(String... args) throws Exception {
		buyService.buyOneBook("john", 1);	// 買書
		
//		updateBookName(1, "C#");		// 改書名
		
	}
	
	
	// 修改書名
	private void updateBookName(Integer bookId, String newBookName) {
		Optional<Book> optBook = bookRepository.findById(bookId);
		if(optBook.isEmpty()) {
			return;
		}
		// 取得 book 實體	(Managed Entity: entity 會與資料表保持同步)		// 呼叫者須為 	@Transactional
		Book book = optBook.get();
		System.out.println("修改前: "+book);
		
		// 修改 book name
		book.setBookName(newBookName);
		// 手動儲存
//		bookRepository.save(book);		// 呼叫者須為 	@Transactional
		
		System.out.println("修改後: "+book);
		
		// 重抓資料庫資訊，確認已改並寫入
		System.out.println("修改後(資): "+bookRepository.findById(bookId).get());
		
	}
	
	// 買書
	 private void buyBook(String username, Integer bookId) {
		 try {
			 buyService.buyOneBook(username, bookId);
			 System.out.println("買書成功");
		} catch (InsufficientAmount e) {
			 System.out.println("買書成功");
			 System.out.println(e.getMessage());
		}
		 
	 }
	 
}
