import { useEffect, useState } from 'react';
import './App.css';
import TodoList from './components/TodoList';
import TodoInput from './components/TodoInput';
import 'bootstrap/dist/css/bootstrap.min.css'; // 引入 Bootstrap 樣式
import {fetchTodos, addTodo, updateTodo, deleteTodo} from './services/todoService'


function App() {
  const [todos, setTodos] = useState([]);
  const [todo, setTodo] = useState('');

  useEffect(() => {
    console.log('抓取 todo list 資料');
    fetchTodos()
      .then(setTodos)
      .catch((error) => console.error('error: ', error));
  }, [])

  // 基本格式 useEffect(() => {}, [])   在渲染完成後開始監聽，( 傳入參數 )，{ 執行動作 }, [ 監聽物件 ] 
  useEffect(() => {
    console.log('抓取 To do List 資料')
  }, []);

  // 新增待辦事項
  const handleAdd = () => {
    if (!todo) return;
    const newTodo = { text: todo, completed: false };

    addTodo(newTodo)
      .then((addedTodo) => setTodos([...todos, addedTodo]))
      .catch((error) => console.error('error: ', error));
    console.log('input');
    setTodo('');
  };

  const handleChange = (e) => {
    setTodo(e.target.value);
  };

  // 更新待辦事項
  const toggleCompletion = (id) => {
    // 先修改前端，準備todos
    const uptTodos = todos.map((todo) => 
      todo.id === id ? {...todo, completed: !todo.completed} : todo
    );
    // 要修改的 todo
    const uptTodo = uptTodos.find((todo) => todo.id === id);
    // 找到要修改的編號，傳給後端
    updateTodo(uptTodo)
      .then(() => setTodos(uptTodos))
      .catch((error) => console.error('error: ', error));
  };

  // 刪除代辦事項
  const handleDelete = (id) => {
    deleteTodo(id)
      .then(() => setTodos(todos.filter((todo) => todo.id !== id)))
      .catch((error) => console.error('error: ', error));
  };
  


  return (
    <div className='container mt-5'>
      <h1 className='text-center mb-4'>My Todo List</h1>
      <TodoInput todo={todo} onChange={handleChange} onAdd={handleAdd} />
      <TodoList todos={todos} onToggleCompletion={toggleCompletion} onDelete={handleDelete} />
    </div>
  );
}

export default App;