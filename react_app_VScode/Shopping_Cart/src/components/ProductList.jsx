import React from 'react';
import ProductItem from './ProductItem';

const ProductList = ({ products, onToggleCompletion }) => {
return (
    <ul>
    {products.map((product) => (
        <ProductItem
        key={product.id}
        product={product}
        onToggleCompletion={onToggleCompletion}
        />
    ))}
    </ul>
);
};

export default ProductList;