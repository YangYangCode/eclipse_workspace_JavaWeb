
import { useState } from 'react';
import './App.css'


const MyInput = ({setmessage}) => {

  const handleChange = (e) => {
    setmessage(e.target.value);   // 透過 set'XXX' 改變 'XXX' 畫面會更新渲染
    console.log("e.target.value: ", e.target.value);
  };

  return (
    <div>
      <input type="text" placeholder='請輸入一些內容' onChange={handleChange} />
    </div>
  );
};


const MyDisplay = ({message}) => {
  return(
    <div>
      顯示: {message}
    </div>
  );
};


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
