import React, { Component } from 'react';

class CalendarHour extends Component {
	render() {
		const height = this.props.height;
		if(!height){
			console.log("this.props.height undefined")
		}

		return (
				<div className="CalendarHour" style={{height:height}}>
				</div>
			   );
	}
}

export default CalendarHour;
