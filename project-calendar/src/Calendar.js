import React, { Component } from 'react';

import CalendarSideBarHour from './CalendarSideBarHour.js'
import CalendarDay from './CalendarDay.js'
import CalendarLine from './CalendarLine.js'

const STYLE_NB_OF_DAYS = 3;
const STYLE_SQUARE_HEIGHT = "4.9em";
const STYLE_SIDE_BAR_WIDTH = "3em";

class Calendar extends Component {

	renderHoursSideBar() {
		return (<CalendarSideBarHour 
					width={STYLE_SIDE_BAR_WIDTH}
					height={STYLE_SQUARE_HEIGHT} />)
	}

	renderDays() {
		let ret = []
		for(let i=0; i<STYLE_NB_OF_DAYS; i++){
			ret.push(<CalendarDay key={i} height={STYLE_SQUARE_HEIGHT}/>)
		}
		return ret;
	}
	renderCalendarLine() {
		return (<CalendarLine positionFromTop={"13em"} positionFromLeft={STYLE_SIDE_BAR_WIDTH} />)
	}

	render() {
		return (
				<div className="Calendar">
				{this.renderHoursSideBar()}
				{this.renderDays()}
				{this.renderCalendarLine()}
				</div>
			   );
	}
}

export default Calendar;
