import React, { Component } from 'react';

class CalendarLine extends Component {
	render() {
		const positionFromTop = this.props.positionFromTop
		if(!positionFromTop){
			console.log("this.props.positionFromTop undefined")
		}
		const positionFromLeft = this.props.positionFromLeft
		if(!positionFromLeft){
			console.log("this.props.positionFromLef undefined")
		}

		return (
				<div tabIndex={1000} 
					style={{position:"absolute", marginTop:positionFromTop, marginLeft:positionFromLeft}}
					className="CalendarLine">
				</div>
			   );
	}
}

export default CalendarLine;
