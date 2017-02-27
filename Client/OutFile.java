import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;


public class OutFile extends Output
{
	
	private ObjectOutputStream output; 
	private File logFile = new File(""); 
	private File errorLog = new File("");
	
	private OutConsole console = new OutConsole(); 
	
	public void Write(String message)
	{
		this.Write(logFile, message);
	}
	
	public void WriteError(String message)
	{
		this.Write(errorLog, message); 
	}
	
	private void Write(File file, String message)
	{
		String[] messages = {message}; 
		Write(file, messages); 
	}
	
	private void Write(File file, String[] messages)
	{
		if (!logFile.mkdirs())
		{
			//Error
		}

		try
		{
			if (!logFile.exists())
			{
				//Make file 
				logFile.createNewFile(); 
			}
			
			output = new ObjectOutputStream(new FileOutputStream(file.getAbsolutePath())); 
			
			output.writeObject(dateTimeString + ": " + messages);
		}
		catch(Exception ex)
		{
			console.WriteError(ex.getMessage());
		}
	}
	
	/*@Override
	public void write(Object[] message)
	{
		PrintWriter writer;
		try
		{
			writer = new PrintWriter(configFile, "UTF-8");	// edit this line to an invalid file path to get the error to throw
			writer.println(message[0]);
		    writer.println(message[1]);
		    writer.close();
		}
		catch (FileNotFoundException | UnsupportedEncodingException e)
		{
			try
			{
				writer = new PrintWriter(logFile, "UTF-8");
				writer.println(getTime() + "  Error writing to config file");	// find way to display current method?
			    writer.close();
			}
			catch (FileNotFoundException | UnsupportedEncodingException e1)
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	    

	}*/

}
