function ClearScreen()
{
    const submitHandler = (e) => {
        console.log('this really doesnt seem like a good method to clear the screen but it works. ill deal with it later lol')
    }
    
    return(
        <form onSubmit={submitHandler}>
            <button type = 'submit'>Reset</button>
        </form>
    )
}

export default ClearScreen;