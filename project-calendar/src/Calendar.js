import React, { Component } from 'react';

import CalendarDay from './CalendarDay.js'
import CalendarSideBarHour from './CalendarSideBarHour.js'

const STYLE_NB_OF_DAYS = 3;
const STYLE_SQUARE_HEIGHT = "5em";

class Calendar extends Component {

	renderDays() {
		let ret = []
		for(let i=0; i<STYLE_NB_OF_DAYS; i++){
			ret.push(<CalendarDay key={i} height={STYLE_SQUARE_HEIGHT}/>)
		}
		return ret;
	}

	render() {
		return (
				<div className="Calendar">
				<CalendarSideBarHour height={STYLE_SQUARE_HEIGHT} />
				{this.renderDays()}
				</div>
			   );
	}
}

export default Calendar;
