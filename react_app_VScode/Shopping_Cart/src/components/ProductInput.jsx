import React from "react";

const ProductInput = ({product, onChange_name, onChange_price, onAdd}) => {

    return (
        <div>
            <input type="text" onChange={onChange_name} value={product.text} placeholder="請輸入商品名稱" />
            <input type="text" onChange={onChange_price} value={product.price} placeholder="請輸入商品價格" />
            <button onClick={onAdd}>Add</button>
        </div>
    )
}

export default ProductInput;