import React, { Component } from 'react';

class CalendarEvent extends Component {
	render() {
		const heightPerHour = this.props.heightPerHour;
		if(!heightPerHour){
			console.log("this.props.heightPerHour undefined")
		}
//mdtmp nb of hours?
/*
		const heightPerHour = this.props.heightPerHour;
		if(!heightPerHour){
			console.log("this.props.heightPerHour undefined")
		}
*/


//mdtmp calculate total height
		const height = "2em"
		const topOffset = "0"
		return (
				<div className="CalendarEvent" style={{height:height, top:topOffset}}>
				</div>
			   );
	}
}

export default CalendarEvent;

