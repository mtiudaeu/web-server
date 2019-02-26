'use strict';

/*mdtmp 
PlayInit("root")

const nav1Path = "nav1"
PlaySetViewState(nav1Path, NavigationView, nav1State)
*/


const pnState = PN_Global_Create()
PN_Global_Set_Trigger(pnState,"PlayTriggerEvent")

const nav1 = PN_Nav_State_Create(pnState)
PN_Global_Focus_View(pnState,PN_Nav_State_Get_Id(nav1))

const catchEvent = (action) => {PN_Global_Set_Catch_Event(pnState,action)}
PlayInit("root", catchEvent)

const pnPath = "pn"
PlaySetViewState(pnPath, PN_View, pnState)
const notify = () => {PlaySetState(pnPath, pnState)}
PN_Global_Set_Notifier(pnState,notify)


/*
const pathView1 = "view1/title"
PlaySetViewState(pathView1, view1, state1)
const pathView2 = "view2"
PlaySetViewState(pathView2, view2, state2)
const pathView3 = "view3"
PlaySetViewState(pathView3, view3, state3)
*/
