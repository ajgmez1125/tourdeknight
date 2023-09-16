import { useState, useEffect } from 'react'
import BoardSettings from './BoardSettings.js'
import Square from './Square.js'
import Knight from './Knight.js'
import ClearScreen from './ClearScreen.js'
import Loading from './Loading.js'
import { PieceConstants } from './PieceConstants';

function Board()
{
    const[knightLoc, setKnightLoc] = useState([])
    const[boardDimensions, setBoardDimensions] = useState({x: 8, y: 8})
    const[boardSquares, setBoardSquares] = useState([[]])
    const[adjacencyList, setAdjacencyList] = useState({})
    const adjColors = {
        0: null,
        1: 'blue',
        2: 'purple',
        3: 'green',
        4: 'pink',
        5: 'yellow',
        6: 'orange',
        7: 'red'
    }
    const[apiLoading, setApiLoading] = useState(false);
    const api = 'http://localhost:8080/pathfind/find?knight='+knightLoc[0]+','+knightLoc[1]+'&x='+boardDimensions.x+'&y='+boardDimensions.y;

    useEffect(() => {
        if(knightLoc.length > 0)
        {
            console.log(api)
            fetch(api)
            .then((response) => response.json())
            .then((json) => {
                setAdjacencyList(json)
            })
        }
        else
        {
            loadBoard()
        }
    }, [boardDimensions, knightLoc])

    useEffect(() => {
        if(Object.keys(adjacencyList).length !== 0)
        {
            loadBoard();
        }
    }, [adjacencyList])

    const loadBoard = () => {
        console.log('loading board')
        var tempArray = [];
        setBoardSquares([])
        var squareCount = 0;
        for(var y = 1; y <= boardDimensions.y; y++)
        {
            tempArray.push([])
            for(var x = 1; x <= boardDimensions.x; x++)
            {
                squareCount++
                tempArray[y - 1].push(
                <Square key = {squareCount} coordinates = {[x, y]} hasKnight = {setKnightLoc} moves = {getNumOfMoves([x, y])}>
                    {knightOnSquare(x,y) ? <Knight piece = {PieceConstants.KNIGHT}/> : null}
                </Square>)
            }
        }
        setBoardSquares(tempArray);
    }

    const knightOnSquare = (x,y) => {
        if (knightLoc.length < 2) return false;
        if (knightLoc[0] == x && knightLoc[1] == y) return true
        else return false;
    }

    const returnRow = (y) => {
        var rowReturn = [];
        for(var x = 0; x < boardDimensions.x; x++)
        {
            rowReturn.push(<td>{boardSquares[y][x]}</td>)
        }
        return <tr>{rowReturn}</tr>
    }

    const renderSquares = () => {
        if(boardSquares.length == boardDimensions.y && boardSquares[0].length == boardDimensions.x)
        {
            var JSXReturn = [];
            for(var y = boardDimensions.y - 1; y >= 0; y--)
            {
                JSXReturn.push(returnRow(y))
            }
            return <tbody>{JSXReturn}</tbody>
        }
    }

    const getMoveColor = (numOfMoves) => {
        return adjColors[numOfMoves]
    }

    const getNumOfMoves = (squareCoordinates) => {
        let coordinatesString = "["+squareCoordinates[0]+", "+squareCoordinates[1]+"]"
        return adjacencyList[coordinatesString];
    }

    const renderScreen = () => {
        if(apiLoading === false)
        {
            return(
                <div className = "board">
                    <table className = "board-squares">
                        {renderSquares()}
                    </table>
                    {knightLoc.length > 0 ? <ClearScreen /> : <BoardSettings setDimensions = {setBoardDimensions} currentDimensions = {boardDimensions} isEnabled = {knightLoc !== null}/>}
                    {knightLoc.length == 0 ? <Knight piece = {PieceConstants.KNIGHT}/> : null}
                </div>
            )
        }
        else if(apiLoading === true)
        {
            return(
                <Loading />
            )
        }
        else if(apiLoading === null)
        {
            return(
                <h1>There was an error processing your request</h1>
            )
        }
    }

    return(
        <>
        {renderScreen()}
        </>
    )
}

export default Board;