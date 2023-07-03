public class App
{
    public static void main(String[] args)
    {
        new Board(8,8).generateObjAdjacencyMap(new Knight(new int[] {4,4}));
    }
}
