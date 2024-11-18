import { useState } from 'react'
import './App.css'

function App() {

  const [todos, setToDos] = useState([
    {id: 1, text: '吃早餐', completed: true},
    {id: 2, text: '做運動', completed: false},
    {id: 3, text: '寫程式', completed: true},
    {id: 4, text: 'Debug', completed: false}
  ]);

  const [todo, setTodo] = useState('');   // 設定輸入欄可修改

  const handleClick = (e) => {
    if (!todo) return
    
    const newId = todos.length > 0 ? Math.max(...todos.map((t) => t.id)) +1 : 1;
    
    const newTodo = {
      id: newId, text: todo, completed: false
    };

    setToDos([...todos, newTodo])  // 同上方程式 (展開運算子)
    setTodo('');  // 將 ToDo 清空
  };

  const handleChange = (e) => {   // 輸入欄動態修改
    setTodo(e.target.value);
  }

  const toggleCompleted = (id) => {
    setToDos(
      todos.map((todo) => todo.id === id ? {...todo, completed: !todo.completed} : todo )
    );
  }

  return (
    <>
      <h1>My To Do List</h1>
      <div>
        <input type="text" onChange={handleChange} value={todo} />
        <button onClick={handleClick}>Add</button>
      </div>
      <ul>
        {todos.map((todo) => (
          <li key={todo.id}>
            {todo.id}
            <input type="checkbox" onChange={() => toggleCompleted(todo.id)} checked={todo.completed} />
            {todo.text}
          </li>))}
      </ul>

    </>
  )
}

export default App
