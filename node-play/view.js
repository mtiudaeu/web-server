'use strict';

const PN_View = function(path, pnState) {
	let childrenHTML = ""
	const views = PN_Global_Views(pnState)
	for(let view of views) {
		childrenHTML += `<div class="card">
			<img class="card-img-top" src="https://via.placeholder.com/150" alt="Card image cap">
			<div class="card-body">
			<h5 class="card-title">${view}</h5>
			<p class="card-text">This is a longer card with supporting text below as a natural lead-in to additional content. This content is a little bit longer.</p>
			</div>
			</div>
			`

	}
	const addViewAction = PN_Global_Action_AddViews(pnState)
	const clickOnCard = PN_Global_Action_clickOnCard(pnState)

	//FIXME Add header (no scrolldown)
	//		"Add Views" button into the header.
	return `
		<button onclick=${addViewAction} type="button">Add Views</button> 
		<p>Focus View : ${pnState.focusView}</p>
		<div onclick=${clickOnCard} class="card-columns" style="column-count:4;padding:5rem">
		${childrenHTML}
		</div>
		`
}

