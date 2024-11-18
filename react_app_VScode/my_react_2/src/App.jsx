
import './App.css'

// var: 作用範圍 fumction scope, 可以多次重覆宣告
// let: 作用範圍 block scope, 同一個 scope 不可重複宣告
// const: 作用範圍 block scope, 不可重複宣告

function App() {
  let message = "";

  const handleChange = (e) => {
    console.log(e.target.value);
    message = e.target.value;   // 僅有變數改變，但畫面不會更新渲染
    console.log("massage: ", message);

    // 直接用 DOM 超做來更新內容
    document.getElementById('displayMessage').textContent = `顯示: ${message}`;
  };

  return (
    <>
      <div>
        <input type="text" placeholder='請輸入一些內容' onChange={handleChange} />
      </ div>
      <div id="displayMessage"> 
        顯示: {message}
      </div>
    </>
  )
}

export default App
