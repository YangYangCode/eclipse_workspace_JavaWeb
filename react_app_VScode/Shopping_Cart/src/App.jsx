import { useState } from 'react';
import './App.css'
import ProductInput from './components/ProductInput';
import ProductList from './components/ProductList';


function App() {
  
  const [products, setproducts] = useState([
    { id: 1, text: 'example-1', price: 0, completed: true },
    { id: 2, text: 'example-2', price: 10, completed: false }
  ]);

  const [product, setproduct] = useState('');

  const handleAdd = () => {
    if (!product.name || !product.price) return;
    const newId = products.length > 0 ? Math.max(...products.map((t) => t.id)) + 1 : 1;
    // const newproduct = { id: newId, text: product, price: product, completed: false };
    const newproduct = { id: newId, text: product.text, price: parseFloat(product.price), completed: false };
    setproducts([...products, newproduct]);
    setproduct('');
  };

  const handleChange_name = (text) => {
    setproduct(text.target.value);
  };

  const handleChange_price = (price) => {
    setproduct(price.target.value);
  };

  const toggleCompletion = (id) => {
    setproducts(
      products.map((product) =>
        product.id === id ? { ...product, completed: !product.completed } : product
      )
    );
  };

  return (
    <div>
      <h1>My Todo List</h1>
      <ProductInput product={product} onChange_name={handleChange_name} onChange_price={handleChange_price} onAdd={handleAdd} />
      <ProductList products={products} onToggleCompletion={toggleCompletion} />
    </div>
  );
}

export default App
