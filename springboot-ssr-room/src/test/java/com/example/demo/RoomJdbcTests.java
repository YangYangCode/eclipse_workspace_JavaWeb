package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.mapper.RoomMapper;
import com.example.demo.model.dto.RoomDto;
import com.example.demo.model.entity.Room;
import com.example.demo.repository.RoomRepositoryJdbc;
import com.example.demo.repository.RoomRepository;

@SpringBootTest
class RoomJdbcTests {

	@Autowired
	private RoomRepository roomRepository;
	
	@Test
	public void testRoomAdd() {
		Room room = new Room(302, "102(S)", 5);
		room = roomRepository.save(room);
		System.out.println("測試新增: "+ room);
	}

	@Test
	public void testFindAllRooms() {
		System.out.println("測試查詢全部: "+ roomRepository.findAll());
	}
	
	@Test
	public void testGetOneRoom() {
		System.out.println("測試查詢單筆: "+ roomRepository.findById(101));
		System.out.println("測試查詢單筆: "+ roomRepository.findById(102));
		System.out.println("測試查詢單筆: "+ roomRepository.findById(109));
	}
	
	@Test
	public void updateRoom() {
		Room upRoom = new Room(102, "102(L)", 100);
		Room room = roomRepository.save(upRoom);
		System.out.println("測試新增: "+ upRoom + " 結果回傳: " + room);
	}
	
	@Test 
	public void deleteRoom() {
		int roomId = 101;
		roomRepository.deleteById(roomId);
		System.out.println("測試刪除: " + roomId);
	}
	
	
}
