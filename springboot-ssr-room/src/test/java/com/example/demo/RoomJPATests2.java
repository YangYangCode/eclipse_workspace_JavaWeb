package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.mapper.RoomMapper;
import com.example.demo.model.dto.RoomDto;
import com.example.demo.model.entity.Room;
import com.example.demo.repository.RoomRepositoryJdbc;

@SpringBootTest
class RoomJPATests2 {

	// 可由此方式達到呼叫不同資料庫
//	@Autowired
//	@Qualifier("roomJdbc_mssql")	
//	private RoomRepositoryJdbc repositoryJdbc_2;
//	
//	@Autowired
//	@Qualifier("roomJdbc_oracle")	
//	private RoomRepositoryJdbc repositoryJdbc_3;
	
	@Autowired
//	@Qualifier("roomJdbc")	// (當沒指定時，會自動抓RoomRepositoryJdbc的實踐class)
	private RoomRepositoryJdbc roomRepositoryJdbc;
	
	@Test
	public void testRoomAdd() {
		Room room = new Room(302, "102(S)", 5);
		int rowcount = roomRepositoryJdbc.save(room);
		System.out.println("測試新增: "+ room + " 結果回傳: " + rowcount + "(1 表示正確新增一筆)");
	}

	@Test
	public void testFindAllRooms() {
		System.out.println("測試查詢全部: "+ roomRepositoryJdbc.findAll());
	}
	
	@Test
	public void testGetOneRoom() {
		System.out.println("測試查詢單筆: "+ roomRepositoryJdbc.findById(101));
		System.out.println("測試查詢單筆: "+ roomRepositoryJdbc.findById(102));
		System.out.println("測試查詢單筆: "+ roomRepositoryJdbc.findById(109));
	}
	
	@Test
	public void updateRoom() {
		Room upRoom = new Room(102, "102(L)", 100);
		int rowcount = roomRepositoryJdbc.update(upRoom);
		System.out.println("測試新增: "+ upRoom + " 結果回傳: " + rowcount + "(1 表示正確修改一筆)");
	}
	
	@Test 
	public void deleteRoom() {
		int roomId = 101;
		int rowcount = roomRepositoryJdbc.deleteById(roomId);
		System.out.println("測試新增: "+ roomId + " 結果回傳: " + rowcount + "(1 表示刪除修改一筆)");
	}
	
	
}
