import React from "react";

const ProductInput = ({ product, setProduct, onAdd }) => {
  // 函數用於更新 product 的特定字段
  const changeData = (value, field) => {
    setProduct((prevProduct) => ({...prevProduct,[field]: value}));
  };

  return (
    <div>
      <input
        onChange={(e) => 
            changeData(e.target.value, "text")
        } // 更新名稱
        value={product.text}
        placeholder="請輸入商品名稱"
      />
      <input
        onChange={(e) => changeData(e.target.value, "price")} // 更新價格
        value={product.price}
        placeholder="請輸入商品價格"
      />
      <button onClick={onAdd}>Add</button>
    </div>
  );
};

export default ProductInput;
