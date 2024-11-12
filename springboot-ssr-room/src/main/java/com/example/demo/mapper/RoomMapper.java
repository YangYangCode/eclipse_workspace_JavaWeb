package com.example.demo.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.model.dto.RoomDto;
import com.example.demo.model.entity.Room;

@Component	// 此元件由 Springboot 自動掃描後管理
public class RoomMapper {

	// 方式A, 單純拿物件
	@Autowired
	private ModelMapper modelMapper;
	
//	// 方式B, 可以先執行一些程序
//	private ModelMapper modelMapper;
//	
//	@Autowired
//	public RoomMapper(ModelMapper modelMapper) {
//		//... do something
//		this.modelMapper = modelMapper;
//	}
	
	public RoomDto toDto(Room room) {
		// Entity to DTO
		return modelMapper.map(room, RoomDto.class);
	}
	
	public Room toEntity(RoomDto roomDto) {
		// DTO to Entity
		return modelMapper.map(roomDto, Room.class);
	}
}
