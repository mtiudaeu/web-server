import React, { Component } from 'react';

import CalendarHour from './CalendarHour.js'

class CalendarDay extends Component {
  render() {
    return (
      <div className="CalendarDay">
		{//todo pass the hour CalendarHour
		}
		<CalendarHour/>
		<CalendarHour/>
		<CalendarHour/>
		<CalendarHour/>
      </div>
    );
  }
}

export default CalendarDay;
