'use strict';

PlayInit("root")

{ // view 1!
	const pathView1 = "view1/title"
	const view = function(path, title)
	{
		return `<p>${title}</p>
			<button onclick=PlaySetState("${path}","BuuUUUuuUUuuton1") type="button">Click Me 2!</button> 
			`
	}
	PlaySetView(pathView1, view)
	const title1 = "Title 1"
	PlaySetState(pathView1, title1)
}
{ // view 2!
	const pathView2 = "view2/title"
	const view2 = function(path, title)
	{
		return `<p>${title}</p>
			<button onclick=PlaySetState("${path}","buttonn2") type="button">Click Me 2!</button> 
			`
	}
	PlaySetView(pathView2, view2)
	const title2 = "Title 2"
	PlaySetState(pathView2, title2)
}
