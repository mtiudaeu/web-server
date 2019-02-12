/*
const getVisibleTodos = (todos, filter) => {
  switch (filter) {
    case VisibilityFilters.SHOW_ALL:
      return todos
    case VisibilityFilters.SHOW_COMPLETED:
      return todos.filter(t => t.completed)
    case VisibilityFilters.SHOW_ACTIVE:
      return todos.filter(t => !t.completed)
    default:
      throw new Error('Unknown filter: ' + filter)
  }
}

const mapStateToProps = state => ({
  todos: getVisibleTodos(state.todos, state.visibilityFilter)
})



import React from 'react'
import PropTypes from 'prop-types'
import Todo from './Todo'

const TodoList = ({ todos, toggleTodo }) => (
  <ul>
    {todos.map(todo => (
      <Todo key={todo.id} {...todo} onClick={() => toggleTodo(todo.id)} />
    ))}
  </ul>
)

TodoList.propTypes = {
  mdtmp: PropTypes.string.isRequired
  mdtmpCb: PropTypes.func.isRequired
}

export default TodoList



*/
'use strict'

const MDTMPView = ({ todos, toggleTodo }) => (
  <div>
	test!!
  </div>
)

MDTMPView.propTypes = {
  mdtmp: PropTypes.string.isRequired,
  mdtmpCb: PropTypes.func.isRequired
}


const mapStateToProps = state => ({
  mdtmp: state.mdtmp
})

const mapDispatchToProps = dispatch => ({
  mdtmpCb(){
	console.log("mdtmp");
  }
})

const MDTMP = ReactRedux.connect(
  mapStateToProps,
  mapDispatchToProps
)(MDTMPView)


const reducer = (state={}, action) => {
  switch (action.type) {
    case 'ADD_TODO':
      return state
    default:
      return state
  }
}

const store= Redux.createStore(reducer)


ReactDOM.render(
  <React.Provider store={store}>
    <MDTMP />
  </React.Provider>,
  document.getElementById('root')
)
