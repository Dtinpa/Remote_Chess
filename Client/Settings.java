import java.awt.event.ActionEvent;

public class Settings extends Screen
{
	public static Settings singleton;
	private DrawSettings drawSettings;
		
	Settings()
	{ }
	
	public static Settings getSettings()	// implements singleton
	{
		if(singleton == null)
		{ singleton = new Settings(); }
		return singleton;
	}

	@Override
	public void execute()
	{ 
		if (drawSettings == null)
		{ drawSettings = new DrawSettings();  }
		drawSettings.show();
	}
	
	@Override
	public void dispose()
	{ drawSettings.hide(); }

	@Override
	public void actionPerformed(ActionEvent e)	// logic for handling button clicks
	{
		switch(e.getActionCommand())
		{
			case("Save"):
			//todo
				break;
			case("Cancel"):
				Settings.getSettings().dispose();
				MainScreen.getMainScreen().execute();
				break;
		}
		
	}
}
