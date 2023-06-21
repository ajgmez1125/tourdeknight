import knight from './knight.png';

function Loading()
{
    return(
        <div className = 'loading-screen'>
            <img src = {knight} className = "loading-knight"/>
        </div>
    )
}

export default Loading