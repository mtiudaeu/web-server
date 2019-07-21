import React, {useState} from 'react';
import logo from './logo.svg';
import './App.css';


function SimpleView(props) {
	return (
		<div className="App">
		<header className="App-header">
		<img src={logo} className="App-logo" alt="logo" />
		</header>
		</div>
	)
}


function Selector() {
	const [globalState, setGlobalState] = useState(
		{
			headEntity: null,
			arrayEntities: [],
			deletedEntities: []
		});
	//mdtmp
	console.log(globalState)

	const addNode = () => {
		const id = globalState.arrayEntities.length
		const newEntity = {
			id:id
		}

		globalState.arrayEntities.push(newEntity)
		setGlobalState(globalState)
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

		setGlobalState(globalState)
	}

	const setHead = (props) => {
		globalState.headEntity = globalState.arrayEntities[props.id]
		setGlobalState(globalState)
	}

	if(!globalState.headEntity) {
		const createFirstEntity = () => {
			const id = addNode();			
			setView({
				id:id,
				title:`mdtmp{id}`
			});
			setHead({
				id:id
			})
		}
		return (
			<div>
			<button onClick={createFirstEntity} >
			Add View
			</button>
			</div>
		)
	}

	return (
		<div>
		<SimpleView/>
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
