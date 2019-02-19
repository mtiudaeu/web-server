'use strict';

PlayInit("root")

{ // view 1!
	const pathView1 = "view1/title"
	const state = "Title 1"
	const view = function(path, title)
	{
		return `<p>${title}</p>
			<button onclick=PlaySetState("${path}","BuuUUUuuUUuuton1") type="button">Click Me 2!</button> 
			`
	}
	PlaySetViewState(pathView1, view, state)
}
{ // view 2!
	const pathView2 = "view2"
	const state = {title:"Title 2"}
	const view2 = function(path, elem)
	{
		return `<p>${elem.title}</p>
			<button onclick=PlaySetState("${path}",{title:"button2"}) type="button">Click Me 2!</button> 
			`
	}
	PlaySetViewState(pathView2, view2, state)
}
