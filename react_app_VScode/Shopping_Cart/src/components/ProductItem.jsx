import React from 'react';

const ProductItem = ({ product, onToggleCompletion }) => {
return (
    <li>
        <span>ID: {product.id}, </span> 
        <input
        type="checkbox"
        checked={product.completed}
        onChange={() => onToggleCompletion(product.id)}
        />
        <span>{product.text}, </span> 
        <span>${product.price}</span>
    </li>
    );
};

export default ProductItem;