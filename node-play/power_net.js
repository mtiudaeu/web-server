'use strict';

//---------- Global ----------
//return: GlobalState
function PN_Global_Create() {
	return {
		idItr:0,
		focusView:undefined
	}
}

//globalState: GlobalState
//viewId: number
function PN_Global_Focus_View(globalState, viewId) {
	globalState.focusView = viewId
}

//globalState: GlobalState
//return: number[]
function PN_Global_Get_All_Views(globalState) {
	//mdtmp unimplemented
	return [1,2]
}


//---------- Nav ----------
//globalState: GlobalState
function PN_Nav_State_Create(globalState) {
	const id = globalState.idItr++
	globalState[id] = 
		{
			viewType:"Navigation",
			viewId:id,
			childrenViews:[],
			parentView:undefined,
			title:undefined
		}
	return globalState[id]
}

//navState: NavState
function PN_Nav_State_Get_Id(navState) {
	return navState.viewId
}
