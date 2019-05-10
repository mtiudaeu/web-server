import React, { Component } from 'react';

import CalendarDay from './CalendarDay.js'
import CalendarSideBarHour from './CalendarSideBarHour.js'

const STYLE_NB_OF_DAYS = 3;

class Calendar extends Component {

	renderDays() {
		let ret = []
		for(let i=0; i<STYLE_NB_OF_DAYS; i++){
			ret.push(<CalendarDay/>)
		}
		return ret;
	}

	render() {
		return (
				<div className="Calendar">
				<CalendarSideBarHour height="5em"/>
				{this.renderDays()}
				</div>
			   );
	}
}

export default Calendar;
