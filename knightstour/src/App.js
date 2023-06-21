import logo from './logo.svg';
import './App.css';
import Board from './Board.js'

function App() {
  return (
    <div className="app">
      <h1>Knights Tour</h1>
      <p>Place the your knight onto the board</p>
      <Board />
    </div>
  );
}

export default App;
