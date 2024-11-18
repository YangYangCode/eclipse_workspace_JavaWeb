package com.example.demo.controller;

import java.util.Collection;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.internal.bytebuddy.asm.Advice.Return;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.bean.Book;
import com.example.demo.response.ApiResponse;

import jakarta.websocket.server.PathParam;

import org.springframework.web.bind.annotation.RequestParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


	
// 了解各總不同 URL 與參數的傳遞接收
@RestController			// 當所有method皆要return，可免去 @ResponseBody。 若有要回傳jsp則不可使用
@RequestMapping("/api")	// 統一 URL 前綴 (網址需 + "/api")
public class ApiController {
	
	private static final Logger logger = LoggerFactory.getLogger(ApiController.class);

	
	/**	
	 *	1.歡迎頁
	 *	路徑: /welcome
	 *	路徑: /home
	 *	網址: http://localhost:8088/mvc/api/welcome
	 *	網址: http://localhost:8088/mvc/api/home
	 */
	
	@GetMapping(value = {"/welcome", "/home"})
	public String welcome() {
		
		// 確認是否有執行到此方法
		logger.info("這是一條日誌訊息");
		
		return "Welcome";
	}
	
	/**	
	 *	2. ?帶參數
	 *	路徑: /greet?name=John&age=18
	 *	路徑: /greet?name=Mary
	 *	網址: http://localhost:8088/mvc/api/greet?name=John&age=18
	 *	結果: John, 18 (成年)
	 *	網址: http://localhost:8088/mvc/api/greet?name=Mary
	 *	結果: Mary, 0 (未成年)
	 *	限制: name 參數一定要加，age 參數可不加 (若無 age -> 初始值: 0)
	 */
	@GetMapping("/greet")
	public String greet(@RequestParam (value= "name", required = true) String name,
						@RequestParam (value = "age", required = false, defaultValue = "0") Integer age) {
		// 觀察參數
		logger.info("uesrname = " + name + ", userage = " + age);
		
		return String.format("Hi %s, 年齡: %d 歲, (%s)", name, age, age >= 18?"成年":"未成年");
	}
	
	// 3.上述(2)的精簡寫法
	@GetMapping("/greet2")
	// 參數、命名相同可去除
	// required = true 為預設
	// 當有defaultValue, required = false 為預設
	public String greet2(@RequestParam String name,
						@RequestParam (defaultValue = "0") Integer age) {
		return String.format("Hi %s, 年齡: %d 歲, (%s)", name, age, age >= 18?"成年":"未成年");
	}
	
	// 當參數、命名"不相同"才需寫出程式碼
	@GetMapping("/greet3")
	public String greet3(@RequestParam (value= "name", required = true) String username,
						@RequestParam (value = "age", required = false, defaultValue = "0") Integer userage) {
		return String.format("Hi %s, 年齡: %d 歲, (%s)", username, userage, userage >= 18?"成年":"未成年");
	}
	
	/**	
	 *	4. Lab練習
	 *	路徑: /bmi?h=170&w=60
	 *	網址: http://localhost:8088/mvc/api/bmi?h=170&w=60
	 *	結果: bmi = 20.76
	 */
	
	@GetMapping("/bmi")
	public String bmi(// @RequestParam (name = h, required = true) 
					  @RequestParam Double h, @RequestParam Double w) {
		double bmi = w/ Math.pow(h/100, 2);
		// return String.format("bmi = %.2f", bmi);
		return """
				{
				"bmi": %.2f
				}
				""".formatted(bmi);
	}
	
	@GetMapping("/bmi2")
	public Double bmi2(// @RequestParam (name = h, required = true) 
					  @RequestParam Double h, @RequestParam Double w) {
		double bmi = w/ Math.pow(h/100, 2);
		return bmi;
	}
	
	/**	
	 *	5. 同名多筆資料
	 *	路徑: /age?age=17&age=21&age=20
	 *	網址: http://localhost:8088/mvc/api/age?age=17&age=21&age=20
	 *	計算出平均年齡
	 */
	
	//@GetMapping("/age")					 //也可是HTML	  UTF-8 編碼設定
	// @GetMapping(value = "/age", produces = "text/plain;charest=utf-8")		// 把數值回傳格式也加入 (避免 springboot 舊版本對於中文格式的亂碼)
	@GetMapping(value = "/age", produces = "application/json;charest=utf-8")
	//public String getAverageOfAge(@RequestParam("age") List<Integer> ages) {
	public ResponseEntity<ApiResponse<Object>> getAverageOfAge(@RequestParam("age") List<String> ages) {

//	因以使用 @ControllerAdvice 檢測錯誤
//		try {
			double avgOfAge = ages.stream().mapToInt(Integer::parseInt).average().getAsDouble();
			//return String.format("平均年齡: %.1f", avgOfAge);
			//return Map.of("平均年齡", String.format("%.1f", avgOfAge));
			
			Object data = Map.of("平均年齡", String.format("%.1f", avgOfAge));
			//return ResponseEntity.status(200).body(ApiResponse.success("查詢成功", data));
			return ResponseEntity.ok(ApiResponse.success("查詢成功", data));
			
//		} catch (NumberFormatException e) {
//			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
//								 .body(ApiResponse.error(HttpStatus.BAD_REQUEST.value(), "參數不正確"));
//		}catch (Exception e) {
//			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//					 .body(ApiResponse.error(HttpStatus.BAD_REQUEST.value(), "內部伺服器錯誤"));
//		}
		
	}
	
	/**
	 * 6. Lab 練習: 得到多筆 score 資料
	 * 路徑: "/exam?score=80&score=100&score=50&score=70&score=30"
	 * 網址: http://localhost:8088/mvc/api/exam?score=80&score=100&score=50&score=70&score=30
	 * 請自行設計一個方法，此方法可以
	 * 印出: 最高分=?、最低分=?、平均=?、總分=?、及格分數=?、不及格=?
	 * (支援中文字印出) 
	 * 提示: IntSummaryStatistics, Collectors.partitioningBy
	 * */
	
	@GetMapping(value = "/exam", produces = "text/plain;charest=utf-8")
	public String examScore(@RequestParam("score") List<Integer> scores) {
		Integer maxScore=0, minScore=0, total=0;
		Double avgOfScore = scores.stream().mapToInt(Integer::intValue).average().getAsDouble();
		for (int score: scores) {
			if(score > maxScore) {
				maxScore = score;
			}
			if(score < minScore) {
				minScore = score;
			}
			total += score;

		}
		
		return String.format("最高分: %d\n最低分: %d\n平均: %.1f\n總分: %d", maxScore, minScore, avgOfScore, total);
	}
	
	// http://localhost:8088/mvc/api/examTeacher?score=80&score=100&score=50&score=70&score=30
	@GetMapping(value = "/examTeacher", produces = "application/json;charest=utf-8")
												//  因key-value為json格式
	public ResponseEntity<ApiResponse<Object>> examScoreTeacher(@RequestParam("score") List<String> scores) {
		// 驗證score 是否可以轉為有效整數
//		try {
			// 統計資料
			IntSummaryStatistics stat = scores.stream().mapToInt(Integer::parseInt).summaryStatistics();
			// 利用 Collectors.partitioningBy 分組
			// key=true 及格分數, key=false 不及格分數
			// Map<Boolean, List<Integer>> resultMap = scores.stream().collect(Collectors.partitioningBy(score -> score >= 60));
			Map<Boolean, List<String>> resultMap = scores.stream()
												.collect(Collectors.partitioningBy(score -> Integer.parseInt(score) >= 60));
			
			// 定義格式
			Object data = Map.of(
					"最高分", stat.getMax(),
					"最低分", stat.getMin(),
					"平均", stat.getAverage(),
					"總分", stat.getSum(),
					"及格分數", resultMap.get(true),
					"不及格分數", resultMap.get(false)
					);
			
			//return ResponseEntity.status(200).body(ApiResponse.success("查詢成功", data));
			return ResponseEntity.ok(ApiResponse.success("查詢成功", data));
			
//		} catch (NumberFormatException e) {
//			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
//					 .body(ApiResponse.error(HttpStatus.BAD_REQUEST.value(), "參數不正確"));
//		}catch (Exception e) {
//		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//				 .body(ApiResponse.error(HttpStatus.BAD_REQUEST.value(), "內部伺服器錯誤"));
//		}
	}
	
	/**
	 * 7. 多筆參數轉 Map
	 * name: 書名(String), price: 價格(Double), amount 數量(Integer), pub 出/停刊(Boolean)
	 * 路徑: /book?name=Math&price=12.5&amount=10&pub=true
	 * 路徑: /book?name=English&price=10.5&amount=20&pub=false
	 * 網址: http://localhost:8088/mvc/api/book?name=Math&price=12.5&amount=10&pub=true
	 * 網址: http://localhost:8088/mvc/api/book?name=Comics&price=10.5&amount=20&pub=false
	 * */
	
//	@GetMapping("/book")												// 自動轉為 Map 集合
//	public ResponseEntity<ApiResponse<Object>> getBookInfo(@RequestParam Map<String, Object> bookMap) {
//		return ResponseEntity.ok(ApiResponse.success("查詢成功", bookMap));
//	}
	
	/**
	 * 8. 多筆參數轉指定 Bean 物件
	 */
	@GetMapping("/book")
	public ResponseEntity<ApiResponse<Object>> getBookInfo(Book book) {
		return ResponseEntity.ok(ApiResponse.success("查詢成功", book));
	}
	
	/**
	 * 8. 多筆參數轉指定 Bean 物件
	 * 路徑: /book/1
	 * 路徑: /book/3
	 * 網址: http://localhost:8088/mvc/api/book/1
	 * 網址: http://localhost:8088/mvc/api/book/3
	 */
	
	@GetMapping("/book/{id}")
	public ResponseEntity<ApiResponse<Book>> getBookId(@PathVariable Integer id){
		List<Book> books = List.of(
				new Book(1, "Math1", 12.5, 20, true), 
				new Book(2, "Math2", 13.5, 21, false), 
				new Book(3, "Math3", 14.5, 22, true), 
				new Book(4, "Math4", 15.5, 23, false), 
				new Book(5, "Math5", 16.5, 24, true), 
				new Book(6, "Math6", 17.5, 25, false));
		
		Optional<Book> optbook = books.stream().filter(b->b.getId().equals(id)).findAny();
		
		if(optbook.isEmpty()) {
			throw new RuntimeException("查無此書");
		}
		return ResponseEntity.ok(ApiResponse.success("查詢成功", optbook.get()));
	}
	
	/**
	 * 10. Lab: 請列出書本價格介於 13~17 之間的書
	 * 如何設計 GET API ?
	 * http://localhost:8088/mvc/api/book/pub/true?min=""&max=""
	 * */
	@GetMapping("/book/pub/{pub}")
	public ResponseEntity<ApiResponse<List<String>>> queryBook(@PathVariable Boolean pub,
			@RequestParam Double min,@RequestParam Double max){
		List<Book> books = List.of(
				new Book(1, "Math1", 12.5, 20, true), 
				new Book(2, "Math2", 13.5, 21, false), 
				new Book(3, "Math3", 14.5, 22, true), 
				new Book(4, "Math4", 15.5, 23, false), 
				new Book(5, "Math5", 16.5, 24, true), 
				new Book(6, "Math6", 17.5, 25, false));
		
//		Optional<Book> resultbook = books.stream().filter(p->p.getPrice()>=13 && p.getPrice() <=17 && p.getPub().equals(true)).allMatch();
		List<String> bookNames = books.stream().filter(b -> b.getPub())
											   .filter(b -> b.getPrice() >= min && b.getPrice() <= max)
											   .map(b -> b.getName())
											   .collect(Collectors.toList());
		
		if(bookNames.size() == 0) {
			throw new RuntimeException("此範圍查無任何書籍");
		}
		
		return ResponseEntity.ok(ApiResponse.success("查詢成功", bookNames));
	}
		
	
	
	
}