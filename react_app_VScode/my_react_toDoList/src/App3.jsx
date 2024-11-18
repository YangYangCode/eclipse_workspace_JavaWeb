import { useState } from 'react'
import './App.css'

function App() {

  const [todos, setToDos] = useState([
    '吃早餐', '做運動', '寫程式', 'Debug'
  ]);

  const [todo, setTodo] = useState('');   // 設定輸入欄可修改

  const handleClick = (e) => {
    if (!todo) return
    // setToDos(todos.concat(todo));
    setToDos([...todos, todo])  // 同上方程式 (展開運算子)
    setTodo('');  // 將 ToDo 清空
  };

  const handleChange = (e) => {   // 輸入欄動態修改
    setTodo(e.target.value);
  }

  return (
    <>
      <h1>My To Do List</h1>
      <div>
        <input type="text" onChange={handleChange} value={todo} />
        <button onClick={handleClick}>Add</button>
      </div>
      <ul>
        { todos.map((todo, index) => (<li key={index}>{todo}</li>))}
      </ul>

    </>
  )
}

export default App
