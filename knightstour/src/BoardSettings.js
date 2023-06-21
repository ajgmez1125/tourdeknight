import { useState, useEffect } from 'react'

function BoardSettings({setDimensions, currentDimensions, boardInUse})
{
    const[x, setX] = useState(currentDimensions.x);
    const[y, setY] = useState(currentDimensions.y);

    const maxVal = 16
    const minVal = 4
    const [options, setOptions] = useState([]);

    useEffect(() => {
        if(options.length !== maxVal - minVal + 1)
        {
            var tempOptions = [];
            for(var i = minVal; i <= maxVal; i++)
            {
                tempOptions.push(i)
            }
            setOptions(tempOptions)
        }
    }, [maxVal, minVal, options.length])

    const submitHandler = (e) => {
        if (boardInUse) return
        e.preventDefault();
        setDimensions({x, y})
    }

    const renderForm = () => {
        if(options.length > 0)
        {
            return(
                <form onSubmit = {submitHandler}>
                <div className = 'dimension-picker'>
                    <label htmlFor = 'xOptions'> X: </label>
                    <select name = 'xOptions' value = {x} onChange = {e => setX(e.target.value)}>
                        {options.map((option) => {
                            return <option key = {option} value = {option}>{option}</option>
                        })}
                    </select>
                </div>
                <div className = 'dimension-picker'>
                    <label htmlFor = 'yOptions'>  Y: </label>
                    <select name = 'yOptions' value = {y} onChange = {e => setY(e.target.value)}>
                        {options.map((option) => {
                            return <option key = {option} value = {option}>{option}</option>
                        })}
                    </select>
                </div>
                <button type = 'submit'> Submit </button>
                </form>
            )
        }
    }

    return(
        <div className = "board-settings">
            {renderForm()}
        </div>
    )
}

export default BoardSettings;