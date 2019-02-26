'use strict';

PlayInit("root")

const nav1Path = "nav1"
PlaySetViewState(nav1Path, NavigationView, nav1State)


const pnState = PN_Global_Create()
const nav1 = PN_Nav_State_Create(pnState)
PN_Global_Focus_View(pnState,PN_Nav_State_Get_Id(nav1))

//mdtmp console.log( pnState)

const pnPath = "pn"
PlaySetViewState(pnPath, PN_View, pnState)


/*
const pathView1 = "view1/title"
PlaySetViewState(pathView1, view1, state1)
const pathView2 = "view2"
PlaySetViewState(pathView2, view2, state2)
const pathView3 = "view3"
PlaySetViewState(pathView3, view3, state3)
*/
