'use strict';


const pnState = PN_Global_Create()
PN_Global_Set_Trigger(pnState,"PlayTriggerEvent")

const catchEvent = (action) => {PN_Global_Set_Catch_Event(pnState,action)}
PlayInit("root", catchEvent)

const pnPath = "pn"
PlaySetViewState(pnPath, PN_View, pnState)
const notify = () => {PlaySetState(pnPath, pnState)}
PN_Global_Set_Notifier(pnState,notify)

