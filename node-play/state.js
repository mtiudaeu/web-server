'use strict';

const nav1 = {
viewType:"Navigation",
viewId:1,
childrenViews:[2,3],
parentView:undefined
title:"Parent1",
}
const nav2 = {
viewType:"Navigation",
childrenViews:[],
parentView:1
title:"Child2",
}
const nav3 = {
viewType:"Navigation",
childrenViews:[],
parentView:1
title:"Child3",
}

const state1 = "Title 1"
const state2 = {title:"Title 2"}
const state3 = {}
