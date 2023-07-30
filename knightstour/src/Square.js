import { useState, useEffect } from 'react'
import { PieceConstants } from './PieceConstants';
import { useDrop } from 'react-dnd'
import Knight from './Knight.js'

function Square({key, coordinates, hasKnight, children, moveColor})
{
    const [squareCoordinates] = useState(coordinates)
    const [color, setColor] = useState();

    const[{isOver}, drop] = useDrop(
        () => ({
            accept: PieceConstants.KNIGHT,
            drop: () => hasKnight(squareCoordinates)
        })
    )

    useEffect(() => {
        var squareNumber = parseInt(coordinates[0]) + parseInt(coordinates[1])
        if(squareNumber % 2 == 0)
        {
            setColor('gray')
        }
        else
        {
            setColor('white')
        }
    }, [])

    return(
            <div className = 'square' style={{backgroundColor: color}} ref = {drop}>
                <div className = 'parent-square' style = {{opacity: '50%', backgroundColor: moveColor}}>
                {children}
                </div>
            </div>
    )
}

export default Square;