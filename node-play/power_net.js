'use strict';

//---------- Global ----------
function PN_Global_Create() {
	return {
		idItr:0,			// number
		focusView:undefined,// number
		trigger:undefined,	// (action:string){}
		notify:undefined,	// (){}
		views:[]			// number[]
	}
}
function PN_Global_Views(pnState) {
	return pnState.views
}
function PN_Global_Set_Focus_View(pnState, viewId) {
	pnState.focusView = viewId
}
function PN_Global_Set_Catch_Event(pnState, action) {
	switch(action) {
		case "ADD_VIEW":
			PN_Nav_State_Create(pnState)
			break
		case "CLICK_ON_CARD":
			//mdtmp
			console.log("mdtmp click on card")
			break
	}
	if(!pnState.notify) return
	pnState.notify()
}
function PN_Global_Set_Trigger(pnState, trigger) {
	pnState.trigger = trigger
}
function PN_Global_Set_Notifier(pnState, notify) {
	pnState.notify = notify
}
function PN_Global_Action_AddViews(pnState) {
	return PN_Global_Action(pnState, "ADD_VIEW")
}
function PN_Global_Action_clickOnCard(pnState) {
	return PN_Global_Action(pnState, "CLICK_ON_CARD")
}

function PN_Global_Action(pnState, action) {
	return `${pnState.trigger}("${action}")`
}

//---------- Nav ----------
function PN_Nav_State_Create(pnState) {
	const id = pnState.idItr++
	pnState.views.push(id)
	pnState[id] = 
		{
			viewType:"Navigation",
			viewId:id,
			childrenViews:[],
			parentView:undefined,
			title:undefined
		}
	return pnState[id]
}
