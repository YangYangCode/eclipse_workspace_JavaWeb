// TodoList Rest CRUD

/**
 * WEB API
 * ------------------------------------------
 * servlet-path: /todolist  (@RequestMapping)
 * ------------------------------------------
 * GET    "/"     獲取所有待辦事項
 * POST   "/"     新增待辦事項
 * PUT    "/{id}" 更新待辦事項
 * DELETE "/{id}" 刪除待辦事項
 * ------------------------------------------
 * */

const BASE_URL = "http://localhost:8085/todolist"

// 獲取所有代辦事項
export const fetchTodos = async() => {
    const response = await fetch(BASE_URL);
    const result = await response.json();
    if (result.status === 200){
        return result.data; // 返回資料
    }
    throw new Error(result.message);
}

// 新增代辦事項
export const addTodo = async(todo) => {
    const response = await fetch(BASE_URL, {
        method: 'POST',
        headers: {
            'Content-type': 'application/json',
        },
        body: JSON.stringify(todo)
    });
    const result = await response.json();
    if (result.status === 200){
        return result.data; // 返回資料 json 給 then(json) 接收
    }
    throw new Error(result.message);
}

// 更新代辦事項
export const updateTodo = async(updateTodo) => {
    const response = await fetch(`${BASE_URL}/${updateTodo.id}`,{
        method:'PUT',
        headers:{
            'Content-type': 'application/json',
        },
        body: JSON.stringify(updateTodo)
    });
    const result = await response.json();
    if (result.status === 200){
        return result.data; // 返回資料 json 給 then(json) 接收
    }
    throw new Error(result.message);
}

// 刪除代辦事項
export const deleteTodo = async(id) => {
    const response = await fetch(`${BASE_URL}/${id}`, {
        method:'DELETE',
    });
    const result = await response.json();
    if (result.status === 200){
        return true; // 返回true
    }
    throw new Error(result.message);
}