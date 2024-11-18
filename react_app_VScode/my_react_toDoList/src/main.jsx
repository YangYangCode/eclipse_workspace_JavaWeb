import React from 'react'
import ReactDOM from 'react-dom/client'
import App from './App6.jsx'
import './index.css'

ReactDOM.createRoot(document.getElementById('root')).render(
  // 嚴格模式, 避免網路資料存取漏失 (呼叫兩次) =>  <React.StrictMode>    
  <React.StrictMode>    
    <App />
  </React.StrictMode>,
)
