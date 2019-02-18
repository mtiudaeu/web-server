'use strict';

let staticModified = [] //mdtmp check duplicate??
let staticState = {}
let staticView = undefined
let staticRoot = undefined

function getLastElem(pathStr) {
	//mdtmp duplicated code
	const paths = pathStr.split("/")
	if(paths.length===0) {return undefined}

	const lastElementIdx = paths.length-1
	let itrElem = staticState
	{ // iterate to the deepest child
		for(let i=0; i < lastElementIdx; ++i){
			const propertyStr = paths[i]
			if(!itrElem.hasOwnProperty(propertyStr)) {
				// create parent property if doesn't exist
				itrElem[propertyStr] = {}
			}
			itrElem = itrElem[propertyStr]
		}
	}
	return itrElem[paths[lastElementIdx]]
}

function PlaySet(pathStr, newState) {
	const paths = pathStr.split("/")
	if(paths.length===0) {return}

	const lastElementIdx = paths.length-1
	let itrElem = staticState
	{ // iterate to the deepest child
		for(let i=0; i < lastElementIdx; ++i){
			const propertyStr = paths[i]
			if(!itrElem.hasOwnProperty(propertyStr)) {
				// create parent property if doesn't exist
				itrElem[propertyStr] = {}
			}
			itrElem = itrElem[propertyStr]
		}
	}

	//mdtmp could push modified path for every change and
	//pop change from path to view...
	itrElem[paths[lastElementIdx]] = newState
	staticModified.push(pathStr)

	console.log("PlaySet  : ")
	console.log(staticState)
	PlayRender() // put render somewhere else?
}

function PlayInit(id, view) {
	staticView = view
	staticRoot = document.getElementById("root")
}
function PlayRender() {
	if(!staticState) return
	if(!staticView) return
	if(!staticRoot) return

	if(staticModified.length===0) return
	const pathStr = staticModified[staticModified.length-1]
	staticModified.pop()

	const elem = getLastElem(pathStr)
	staticRoot.innerHTML = staticView(pathStr, elem)
}
