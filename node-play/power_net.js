'use strict';

//---------- Global ----------
function PN_Global_Create() {
	return {
		idItr:0,
		focusView:undefined,
		trigger:undefined
	}
}

//viewId: number
function PN_Global_Focus_View(pnState, viewId) {
	pnState.focusView = viewId
}

//return: number[]
function PN_Global_Get_All_Views(pnState) {
	//mdtmp unimplemented
	return [1,2]
}

function PN_Global_Set_Catch_Event(pnState, action) {
	//mdtmp unimplemented
	console.log(pnState)
	console.log(action)
}

function PN_Global_Set_Trigger(pnState, trigger) {
	pnState.trigger = trigger
}

function PN_Global_Action_AddViews(pnState) {
	return `${pnState.trigger}("ADD_VIEW")`
}

//---------- Nav ----------
function PN_Nav_State_Create(pnState) {
	const id = pnState.idItr++
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
