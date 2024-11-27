import React, { useState, useEffect } from "react";
import {
  BrowserRouter as Router,
  Routes,
  Route,
} from "react-router-dom";
import Navbar from "./components/Navbar";
import Footer from "./components/Footer";
import Home from "./pages/Home";
import Products from "./pages/Products";
import Cart from "./pages/Cart";
import Checkout from "./pages/Checkout";
import LoginPage from "./pages/LoginPage";
import "./App.css";

// 如何刷新時保存資料?   A.localstorage   B.直接存資料庫
// 未登入時的 "按鈕" 阻擋 - 如何提示?
// 加入購物車後如何改變購物車的"數量" - 一堆1列 -> 一列改數值
// 新增商品後清除資料
// 登入後的提示


function App() {
  return (
    <Router future={{ v7_startTransition: true, v7_relativeSplatPath: true }}>
      <Navbar />
      <div className="content">
        <Routes>
          <Route 
            path="/" 
            element={<Home />} />

          <Route
            path="/products"
            element={<Products />}
          />

          <Route
            path="/cart"
            element={<Cart />}
          />

          <Route
            path="/checkout"
            element={<Checkout />}
          />

          <Route 
            path="/login" 
            element={<LoginPage />} />

        </Routes>
      </div>
      <Footer />
    </Router>
  );
}

export default App;