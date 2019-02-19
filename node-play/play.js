'use strict';

let staticModified = [] //mdtmp check duplicate??
let staticState = {}
let staticRoot = undefined
let staticIndex = 0

function getValidLastElem(pathStr) {
	const paths = pathStr.split("/")
	if(paths.length===0) {return undefined}

	let itrElem = staticState
	for(const propertyStr of paths) {
		if(!itrElem.hasOwnProperty(propertyStr)) {
			// create element if doesn't exist
			itrElem[propertyStr] = {"id":staticIndex++}
		}
		itrElem = itrElem[propertyStr]
	}
	return itrElem
}

function PlayInit(id) {
	staticRoot = document.getElementById(id)
}

function PlaySetView(pathStr, view) {
	const elem = getValidLastElem(pathStr)
	if(!elem) return
	
	elem["view"] = view

	if(!staticRoot) return
	staticRoot.innerHTML += `<div id=Play${elem.id}>` + elem.view(pathStr, elem.value) + "</div>";
}


function PlaySetState(pathStr, newState) {
	const elem = getValidLastElem(pathStr)
	if(!elem) return
	elem["value"] = newState

	staticModified.push(pathStr)
	PlayRender()
}

function PlayRender() {
	if(staticModified.length===0) return
	const pathStr = staticModified[staticModified.length-1]
	staticModified.pop()

	const elem = getValidLastElem(pathStr)
	let htmlElem = document.getElementById(`Play${elem.id}`)
	if(!htmlElem) return
	htmlElem.innerHTML = elem.view(pathStr, elem.value)
	console.log(staticState) //mdtmp
}

