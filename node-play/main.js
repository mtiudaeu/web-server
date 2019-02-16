'use strict';




const title = "Title!";
PlaySet(title);
//mdtmp set title.. (once/default value)
//read it, set value in UI
//attach set function to button

const view = `
	<p>${staticState}</p>
	<button onclick=console.log("allo") type="button">Click Me!</button> 
	<button onclick=PlaySet("buttonn2") type="button">Click Me 2!</button> 
	`;
PlayInit("root", view)
PlayRender()
