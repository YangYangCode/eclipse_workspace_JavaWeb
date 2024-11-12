package com.example.demo.model.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import org.hibernate.validator.constraints.Range;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomDto {		// 透過 modelMapper 進行 entity 與 roomDTO 之間的轉換
	
	@NotNull(message = "{roomDto.roomId.notNull}")
	private Integer roomId;
	
	@NotNull(message = "{roomDto.roomName.notNull}")
	@Size(min = 3, message = "{roomDto.roomName.size}")
	private String roomName;
	
	@NotNull(message = "{roomDto.roomSize.notNull}")
	@Range(min=1, max=500, message = "房間人數必須介於 {min} ~ {max} 人之間")
	private Integer roomSize;
	

}
