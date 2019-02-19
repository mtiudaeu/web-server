'use strict';

let staticModified = [] //mdtmp check duplicate??
let staticState = {}
let staticView = undefined
let staticRoot = undefined
let staticIndex = 0

function getValidLastElem(pathStr) {
	const paths = pathStr.split("/")
	if(paths.length===0) {return undefined}

	let itrElem = staticState
	for(const propertyStr of paths) {
		if(!itrElem.hasOwnProperty(propertyStr)) {
			// create parent property if doesn't exist
			itrElem[propertyStr] = {"id":staticIndex++}
		}
		itrElem = itrElem[propertyStr]
	}
	return itrElem
}

function PlaySetState(pathStr, newState) {
	const itrElem = getValidLastElem(pathStr)
	if(!itrElem) return

	itrElem["value"] = newState
	staticModified.push(pathStr)
	PlayRender() // mdtmp ... put render somewhere else?
}

function PlaySetView(pathStr, view) {
	const itrElem = getValidLastElem(pathStr)
	if(!itrElem) return
	
	itrElem["view"] = view
}

function PlayInit(id) {
	staticRoot = document.getElementById(id)
}

function PlayRender() {
	if(!staticState) return
	if(!staticRoot) return

	if(staticModified.length===0) return
	const pathStr = staticModified[staticModified.length-1]
	staticModified.pop()

	const elem = getValidLastElem(pathStr)
	staticRoot.innerHTML = `<div id=Play${elem.id}>` + elem.view(pathStr, elem.value) + "</div>";
}

