
import { useState } from 'react';
import './App.css'


function App() {
  //    設定命名  設定更改名稱   初始值
  const [message, setmessage] = useState('');

  const handleChange = (e) => {
    setmessage(e.target.value);   // 透過 set'XXX' 改變 'XXX' 畫面會更新渲染
    console.log("massage: ", message);

  };

  return (
    <>
      <div>
        <input type="text" placeholder='請輸入一些內容' onChange={handleChange} />
      </ div>
      <div> 
        顯示: {message}
      </div>
    </>
  )
}

export default App
