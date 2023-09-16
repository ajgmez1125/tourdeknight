import { useState, useEffect } from 'react'
import { useDrag, DragPreviewImage } from 'react-dnd'
import { PieceConstants } from './PieceConstants'
import knight from './knight.png'

function Knight({piece, square})
{
    const [currentSquare, setCurrentSquare] = useState(square)
    const [{isDragging}, drag, dragPreview] = useDrag(
        () => ({
            type: piece,
        })
    )
    return(
        <>
        <DragPreviewImage src = {knight} connect = {dragPreview}/>
        <img src = {knight} ref = {drag} style = {{opacity: '100%'}}/>
        </>
    )    
}

export default Knight;