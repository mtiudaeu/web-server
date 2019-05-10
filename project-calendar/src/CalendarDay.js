import React, { Component } from 'react';

import CalendarHour from './CalendarHour.js'

class CalendarDay extends Component {
	renderHours(){
		const height = this.props.height;
		if(!height){
			console.log("this.props.height undefined")
		}
		let ret = []
			const NB_OF_HOURS=24
			for(let i=0; i<NB_OF_HOURS; i++){
				ret.push(<CalendarHour key={i} height={height}/>)
			}
		return ret;
	}

	render() {
		return (
				<div className="CalendarDay">
				{this.renderHours()}
				</div>
			   );
	}
}

export default CalendarDay;
