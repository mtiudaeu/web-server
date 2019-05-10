import React, { Component } from 'react';

import CalendarDay from './CalendarDay.js'
import CalendarSideBarHour from './CalendarSideBarHour.js'

class Calendar extends Component {
  render() {
    return (
      <div className="Calendar">
      	<CalendarSideBarHour/>
		<CalendarDay/>
      </div>
    );
  }
}

export default Calendar;
