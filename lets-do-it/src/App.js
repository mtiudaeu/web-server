import React, {useState} from 'react';
import logo from './logo.svg';
import './App.css';


function SimpleView(props) {
	let title = "TEST"
	if(props.entity){
		title = props.entity["compSimpleView"].title
	}
	return (
		<div className="App">
		<div>{title}</div>
		<button onClick={props.createFirstEntity} >
		Add View
		</button>
		</div>
	)
}


function Selector() {
	const [count, setCount] = useState(0);
	const [globalState, setGlobalState] = useState(
		{
			headEntity: null,
			arrayEntities: [],
			deletedEntities: []
		});

	const addNode = () => {
		const id = globalState.arrayEntities.length
		const newEntity = {
			id:id
		}

		globalState.arrayEntities.push(newEntity)
		//mdtmp setGlobalState(globalState)
		return id;
	};

	const setConnection = (props) => {
		const COMP_CONN = "compConnections"

		const id1 = props.id1
		const id2 = props.id2
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

		const id = props.id
		const title = props.title
		const entity = globalState.arrayEntities[id]
		if(!entity[COMP_VIEW]) {
			entity[COMP_VIEW] = {}
		}
		entity[COMP_VIEW].title = title

		//mdtmp setGlobalState(globalState)
	}

	const setHead = (props) => {
		globalState.headEntity = globalState.arrayEntities[props.id]
		//mdtmp setGlobalState(globalState)
	}

	function createFirstEntitymdtmp() {
		const id = globalState.arrayEntities.length
		const newEntity = {
			id:id
		}
		globalState.arrayEntities.push(newEntity)

		const COMP_VIEW = "compSimpleView"

		const title = `mdtmp${id}`
		const entity = globalState.arrayEntities[id]
		if(!entity[COMP_VIEW]) {
			entity[COMP_VIEW] = {}
		}
		entity[COMP_VIEW].title = title

		globalState.headEntity = globalState.arrayEntities[id]

		setGlobalState(globalState)
	}

	return (
		<div>
		<SimpleView 
			createFirstEntity={createFirstEntitymdtmp}
			entity={globalState.headEntity}/>
		<button onClick={()=>{setCount(count+1)}} >
		</button>
		<div>{count}</div>
		</div>
	)
}

function App() {
	return (
		<div>
		<Selector/>
		</div>
	)
}

export default App
