import React, { Component } from 'react';
import logo from './logo.svg';

import './App.css';
import Calendar from './Calendar.js'

class App extends Component {
  render() {
    return (
      <div className="App">
          <img src={logo} className="App-logo" alt="logo" />
		<Calendar/>
      </div>
    );
  }
}

export default App;
