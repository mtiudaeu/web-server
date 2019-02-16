'use strict';

let staticState = {}
let staticView = {}
let staticRoot = {}
function PlaySet(newState) {
	staticState = newState
	console.log(`PlaySet  : ${staticState}`)
	PlayRender()
}

function PlayInit(id, view) {
	staticView = view
	staticRoot = document.getElementById("root")
}
function PlayRender() {
	// mdtmp Could probably resolve this each time useing tag function...
	// https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Template_literals
	//
	root.innerHTML = staticView
}
