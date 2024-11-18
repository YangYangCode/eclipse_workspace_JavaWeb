
import { useState } from 'react';
import './App.css'
import MyInput from './components/MyInput';
import MyDisplay from './components/MyDisplay';


function App() {
  //    設定命名  設定更改名稱   初始值
  const [message, setmessage] = useState('');

  return (
    <>
        <MyInput setmessage={setmessage} />
        <MyDisplay message={message} />
    </>
  )
}

export default App
