import React from "react";


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
  
  
  export default MyInput;