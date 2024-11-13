// 獲取 DOM 元素 - 查詢(List)
const roomList = document.getElementById("roomList");

// 獲取 DOM 元素 - 新增(Add)
const roomIdInput = document.getElementById("roomId");
const roomNameInput = document.getElementById("roomName");
const roomSizeInput = document.getElementById("roomSize");
const addResultText = document.getElementById("addResult");

// 獲取 DOM 元素 - 修改房間(Model)
const editModal = document.getElementById('editModal');
const editRoomIdInput = document.getElementById('editRoomId');
const editRoomNameInput = document.getElementById('editRoomName');
const editRoomSizeInput = document.getElementById('editRoomSize');

// 透過 fetch 經由 http://localhost:8081/rest/room 取得遠端資料
				// 非同步函式 (await 搭配)
const fetchRooms = async () => {
	try{
					// 等待解析完畢再執行
		const response = await fetch("http://localhost:8081/rest/room");
								 // 取得 json 省略解析步驟
		const apiresponse = await response.json();
		console.log(apiresponse);
		// 只有data式需要資訊，其他為判讀訊息
		displayRooms(apiresponse.payload);
	}catch(e){
		console.error("遠端資料存取錯誤", e)
	}
};

// 顯示房間列表
const displayRooms = (rooms) => {
	roomList.innerHTML='';
	rooms.forEach(room => {
		const listItem = document.createElement("li"); // 建立 <li> 標籤元素	
						// ***使用反引號 ->  `  ` 
		listItem.textContent = `房號: ${room.roomId} 房名: ${room.roomName}  人數: ${room.roomSize} `;
		
		// 在 listItem 中加入刪除方式 (button)
		const deleteButton = document.createElement('button');
		deleteButton.textContent = "刪除";
		deleteButton.onclick = () => deleteRoom(room.roomId);
		listItem.appendChild(deleteButton);
		
		// 在 listItem 中加入修改方式 (button)
		const updateButton = document.createElement('button');
		updateButton.textContent = "修改";
		updateButton.onclick = () => openModal(room.roomId, room.roomName, room.roomSize);
		listItem.appendChild(updateButton);
		
		// 將 lsitItem 加入到 roomList 中
		roomList.appendChild(listItem);
	});
};

// 新增房間
const addRoom = async () => {
	const roomId = roomIdInput.value;
	const roomName = roomNameInput.value;
	const roomSize = roomSizeInput.value;
	
	// 檢查資料
	if(!roomId || !roomName || !roomSize){
		addResultText.textContent = "請輸入 id, name 與 size";
		return;
	}
	
	// 遠端新增程序
	try{
		// 將資料轉 json
		const roomDto = {
			roomId: roomId, 
			roomName: roomName,
			roomSize: roomSize
		};
		
		const response = await fetch('http://localhost:8081/rest/room', {
			method: 'POST',
			headers: {
				'Content-Type': 'application/json'
			},
			body: JSON.stringify(roomDto) // 轉 json string 後送出
		});
		
		const apiResponse = await response.json();
		addResultText.textContent = apiResponse.message;
		
		if(response.ok){
			// 重新查詢房間列表資料
			fetchRooms();
			// 清空新增房間的表單欄位
			roomIdInput.value = '';
			roomNameInput.value = '';
			roomSizeInput.value = '';
		}
		
	} catch(e){
		console.error('遠端資料存取錯誤:', e);
	}
	
};


// 刪除房間
const deleteRoom = async (roomId) => {
	try{
		const response = await fetch(`http://localhost:8081/rest/room/${roomId}`, {
			method: 'DELETE'
		});
		
		const apiResponse = await response.json();
		console.log('apiResponse: ', JSON.stringify(apiResponse));
		
		if(response.ok){
			fetchRooms();	// 刪除成功後重新加載房間列表
		}else{
			// 發生錯誤時顯錯誤訊息
			addResultText.testContent = apiResponse.message;
		}
		
	} 	catch(e) {
			addResultText.textContent = e;
	}
};


// 打開 Model 小視窗
const openModal = (roomId, roomName, roomSize) => {
	editRoomIdInput.value = roomId;
	editRoomNameInput.value = roomName;
	editRoomSizeInput.value = roomSize;
	editModal.style.display = 'flex';
};

// 關閉 Model 小視窗
const closeModal = () => {
	editModal.style.display = 'none';
};

// 修改確認
const confirmEdit = async() => {
	
	try{
		// 將資料轉換為 json 物件
		const roomDto = {
			roomName: editRoomNameInput.value, 
			roomSize: editRoomSizeInput.value
		};	
		
		const roomId = editRoomIdInput.value;
		const response = await fetch(`http://localhost:8081/rest/room/${roomId}`, {
			method: 'PUT',
			headers: {
				'Content-Type': 'application/json'
			},
			body: JSON.stringify(roomDto) // 轉 json string 後送出
		});	
		
		const apiResponse = await response.json();
		console.log('apiResponse: ', JSON.stringify(apiResponse));
		
		if(response.ok){
			fetchRooms();	// 修改成功後重新加載房間列表
		}else{
			// 發生錯誤時顯錯誤訊息
			addResultText.testContent = apiResponse.message;
		}
	
	}catch(e){
		// 發生錯誤時顯錯誤訊息
		addResultText.testContent = e;
	}
	// 關閉 Modal
	closeModal();
};


// 執行 fetchRooms()
fetchRooms();