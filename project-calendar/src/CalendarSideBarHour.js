import React, { Component } from 'react';


class CalendarSideBarHour extends Component {
	renderHours(){
		const height = this.props.height;
		if(!height){
			console.log("this.props.height undefined")
		}
		let ret = []
			const NB_OF_HOURS=24
			for(let i=0; i<NB_OF_HOURS; i++){
				ret.push(<div key={i} style={{height:height}}>{i}</div>)

			}
		return ret;
	}

	render() {
		return (
				<div className="CalendarSideBarHour">
				{this.renderHours()}
				</div>
			   );
	}
}

export default CalendarSideBarHour;
