import { useState } from 'react';
import './App.css'
import ProductInput from './components/ProductInput';
import ProductList from './components/ProductList';


function App() {
  const data = [
    { id: 1, text: 'example-1', price: 0, completed: true },
    { id: 2, text: 'example-2', price: 10, completed: false }
  ]

  const [products, setproducts] = useState(data);

  const [product, setProduct] = useState('');

  const handleAdd = () => {
    if (!product.text || !product.price) return;
    const newId = products.length > 0 ? Math.max(...products.map((t) => t.id)) + 1 : 1;
    // const newproduct = { id: newId, text: product, price: product, completed: false };
    const newproduct = { id: newId, text: product.text, price: parseFloat(product.price), completed: false };
    setproducts([...products, newproduct]);
    setproduct('');
  };

  // const handleChange = (data) => {
  //   setproduct((prev)=>[...prev,data]);
  // };

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
      <ProductInput product={product} setProduct={setProduct} onAdd={handleAdd} />
      <ProductList products={products} onToggleCompletion={toggleCompletion} />
    </div>
  );
}

export default App
