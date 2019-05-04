import React, { Component } from 'react';
import logo from './logo.svg';

import './App.css';
import CalendarDay from './CalendarDay.js'

class App extends Component {
  render() {
    return (
      <div className="App">
          <img src={logo} className="App-logo" alt="logo" />
		<CalendarDay/>
      </div>
    );
  }
}

export default App;
