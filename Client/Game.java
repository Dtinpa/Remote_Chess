import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Game extends Screen implements MouseListener
{
	public static Game singleton;
	
	private DrawGame gameUI;
	private Board playerPos;
	
	Game()
	{ playerPos = new Board(); }
	
	public static Game getGame()	// implements singleton
	{
		if(singleton == null)
		{ singleton = new Game(); }
		return singleton;
	}
	
	@Override
	public void execute()
	{
		if (gameUI == null)
		{ gameUI = new DrawGame(); }
		gameUI.show();
	}
	
	@Override
	public void dispose()
	{ gameUI.hide(); }

	@Override
	public void actionPerformed(ActionEvent e)	// logic for handling button clicks
	{
		switch(e.getActionCommand())
		{
			case("Resign"):
				System.out.println("resign");
				break;
			case("Help"):
				Help.getHelp().execute();
				break;
		}
	}

	@Override
	public void mousePressed(MouseEvent e)	// logic for handling board clicks
	{
		Space clicked = (Space) e.getSource();
		System.out.println(clicked.getContents().toString());
		playerPos.setAt(clicked.getRow(), clicked.getCol(), Element.GREENSPACE);
		gameUI.reDraw(playerPos);
	}
	
	@Override
	public void mouseClicked(MouseEvent e)
	{ }
	
	@Override
	public void mouseReleased(MouseEvent e)
	{ }

	@Override
	public void mouseEntered(MouseEvent e)
	{ }

	@Override
	public void mouseExited(MouseEvent e)
	{ }
}
