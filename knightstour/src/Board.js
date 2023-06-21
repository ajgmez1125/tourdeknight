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

    const[apiLoading, setApiLoading] = useState(false);
    const[adjList, setAdjList] = useState([])

    const api = '';

    useEffect(() => {
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
                <Square key = {squareCount} coordinates = {[x, y]} hasKnight = {setKnightLoc}>
                    {knightOnSquare(x,y) ? <Knight piece = {PieceConstants.KNIGHT}/> : null}
                </Square>)
            }
        }
        setBoardSquares(tempArray);
    }, [boardDimensions, knightLoc])

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