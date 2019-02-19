'use strict';

const pathView1 = "view1/title"
const title = "Title!"
const view = function(path, title)
{
	return `<p>${title}</p>
		<button onclick=console.log("allo") type="button">Click Me!</button> 
		<button onclick=PlaySetState("${path}","buttonn2") type="button">Click Me 2!</button> 
		`;
}
PlaySetState(pathView1, title);
PlaySetView(pathView1, view);

PlayInit("root")
PlayRender()
