'use strict';

const pathView1 = "view1/title"
const title = "Title!"
PlaySet(pathView1, title);
//mdtmp set title.. (once/default value)
//read it, set value in UI
//attach set function to button

const view = function(path, title)
{
	console.log(path)
	return `<p>${title}</p>
		<button onclick=console.log("allo") type="button">Click Me!</button> 
		<button onclick=PlaySet("${path}","buttonn2") type="button">Click Me 2!</button> 
		`;
}
PlayInit("root", view)
PlayRender()
