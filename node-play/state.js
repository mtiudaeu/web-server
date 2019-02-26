'use strict';

const nav1State = {
viewType:"Navigation",
viewId:1,
childrenViews:[2,3],
parentView:undefined,
title:"Parent1"
}
const nav2State = {
viewType:"Navigation",
childrenViews:[],
parentView:1,
title:"Child2"
}
const nav3State = {
viewType:"Navigation",
childrenViews:[],
parentView:1,
title:"Child3"
}

//TODO remove below
const state1 = "Title 1"
const state2 = {title:"Title 2"}
const state3 = {}
