'use strict';

//---------- Global ----------
function PN_Global_Create() {
	return {
		idItr:0,
		focusView:undefined,
		trigger:undefined,
		notify:undefined,
		views:[]
	}
}

//viewId: number
function PN_Global_Focus_View(pnState, viewId) {
	pnState.focusView = viewId
}

//return: number[]
function PN_Global_Get_All_Views(pnState) {
	return pnState.views
}

function PN_Global_Set_Catch_Event(pnState, action) {
	switch(action) {
		case "ADD_VIEW":
			PN_Nav_State_Create(pnState)
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
	return `${pnState.trigger}("ADD_VIEW")`
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

function PN_Nav_State_Get_Id(navState) {
	return navState.viewId
}
