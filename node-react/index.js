/*
import React from 'react'
import { render } from 'react-dom'
import { Provider } from 'react-redux'
import { createStore } from 'redux'
import rootReducer from './reducers'
import App from './components/App'
*/
'use strict'


const reducer = (state={}, action) => {
  switch (action.type) {
    case 'ADD_TODO':
      return state
    default:
      return state
  }
}

const store1= Redux.createStore(reducer)


/* mdtmp
render(
  <Provider store={store}>
    <App />
  </Provider>,
  document.getElementById('root')
)
*/
