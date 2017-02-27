
public class OutConsole extends Output
{
	public void Write(String message)
	{
		System.out.println(dateTimeString + ": " + message);
	}
	
	public void Write(String[] messages)
	{
		for(int i = 0; i < messages.length; i++)
		{
			System.out.println(dateTimeString + ": " + messages[i]);
		}
	}
	
	public void WriteError(String message)
	{
		System.out.println("[Error] - " + dateTimeString + ": " + message);
	}
}