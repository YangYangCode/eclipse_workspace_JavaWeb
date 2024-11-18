import './App.css'

function App() {

  const todos = [
    '吃早餐', '做運動', '寫程式', 'Debug'
  ]

  return (
    <>
      <h1>My To Do List</h1>
      <div>
        <input type="text" />
        <button>Add</button>
      </div>
      <ul>
        {/* {   // foreach
          todos.map((todo, index) => {
            return (<li key={index}>{todo}</li>)
          })
        } */}
        
        {/* 簡化寫法 */}
        { todos.map((todo, index) => (<li key={index}>{todo}</li>))}
  
      </ul>

    </>
  )
}

export default App
