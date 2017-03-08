import java.io.Serializable;


public class Board implements Serializable
{
	private Element[][] storage;

	Board()		// creates the "new game" board
	{
		int size = 8;
		storage = new Element[size][size];
		for (int row=0; row<size; row++)
		{
			for (int col=0; col<size; col++)
			{
				if (row%2 ==col%2)
				{
					if (row<3)
					{ storage[row][col] = Element.RED; }
					else if (row>4)
					{ storage[row][col] = Element.BLUE; }
					else
					{ storage[row][col] = Element.BLACKSPACE; }
				}
				else
				{ storage[row][col] = Element.WHITESPACE; }
			}
		}
	}
	
	public Element getAt(int row, int col)
	{ return storage[row][col]; }
	
	public void setAt(int row, int col, Element e)
	{ storage[row][col] = e; }
}
