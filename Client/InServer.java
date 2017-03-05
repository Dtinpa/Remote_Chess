import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.net.Socket;


public class InServer extends Input
{
	//private Socket clientSocket; 
	//private BufferedReader reader; 
	private OutFile logging = OutFile.getInstance();
	
	ObjectInputStream stream;
	InServer(Socket s)
	{
		try
		{ stream = new ObjectInputStream(new BufferedInputStream(s.getInputStream())); }
		catch (IOException e)
		{
		// TODO Auto-generated catch block
		e.printStackTrace();
		}
	}
	
	public Byte readByte()		// read first byte to determine message type
	{
		Byte b = null;
		try
		{ b = stream.readByte(); }
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return b;
	}
	
	public Object read() 
	{
		logging.write("Getting socket.");		
		//clientSocket = getSocketToUse();
		
		try 
		{
			//reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			//return reader.readLine();
			return stream.readObject();
		}
		catch (IOException e) 
		{
			logging.writeError("Could not read from socket.");
		}
		catch (ClassNotFoundException e)
		{
			logging.writeError("Could not find class to deserialize.");
		} 
		return null; 
	}
	
	/*ObjectInputStream stream;
	
	InServer(Socket s)
	{
		try
		{ stream = new ObjectInputStream(new BufferedInputStream(s.getInputStream())); }
		catch (IOException e)
		{
		// TODO Auto-generated catch block
		e.printStackTrace();
		}
	}
	
	public Byte readByte()		// read first byte to determine message type
	{
		Byte b = null;
		try
		{ b = stream.readByte(); }
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return b;
	}
	
	public Object read()		// read rest of message
	{
		Object o = null;
		try
		{ o = stream.readObject(); }
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (ClassNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return o;
	}*/
}
