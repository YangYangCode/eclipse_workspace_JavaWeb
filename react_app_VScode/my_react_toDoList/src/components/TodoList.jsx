import React from 'react';
import TodoItem from './TodoItem';

const TodoList = ({ todos, onToggleCompletion }) => {
return (
    <ul className="list-group">
    {todos.map((todo) => (
        <TodoItem
        key={todo.id}
        todo={todo}
        onToggleCompletion={onToggleCompletion}
        />
    ))}
    </ul>
);
};

export default TodoList;