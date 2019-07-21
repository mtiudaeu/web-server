import React, {useState} from 'react';
import logo from './logo.svg';
import './App.css';

//mdmtp
/*
   -GlobalState
   headEntity
   arrayEntities
   deletedEntities

   -CurrentViewer
//displaycurrentNode

-nodeComponent
Connections
Views(...)

*/

function SimpleView(props){

	}

function App() {

	const [globalState, setGlobalState] = useState(
		{
			headEntity: null,
			arrayEntities: [],
			deletedEntities: []
		});

	const addNode = () => {

		const id = globalState.arrayEntities.length
		const newEntity = {
			id:id,
			components:[]
		}

		globalState.arrayEntities.push(newEntity)
		setGlobalState(globalState)
	};

	const setConnection = (props) => {
		const COMP_CONN = "compConnections"

		const id1, id2 = {...props}
		const entity1 = globalState.arrayEntities[id1]
		const entity2 = globalState.arrayEntities[id2]

		if(!entity1[COMP_CONN]) {
			entity1[COMP_CONN] = []
		}
		if(!entity2[COMP_CONN]) {
			entity2[COMP_CONN] = []
		}
		entity1[COMP_CONN].push(id2)
		entity2[COMP_CONN].push(id1)

		setGlobalState(globalState)
	}
	//FIXME delete connection (look above)

	const setView = (props) => {
		const COMP_VIEW = "compSimpleView"

		const id, title = {...props}
		const entity = globalState.arrayEntities[id]
		if(!entity[COMP_VIEW]) {
			entity[COMP_VIEW] = []
		}
		entity[COMP_VIEW].title = title

		setGlobalState(globalState)
	}


	return (
		<div className="App">
		<header className="App-header">
		<img src={logo} className="App-logo" alt="logo" />
		</header>
		</div>
	);
}

export default App;
