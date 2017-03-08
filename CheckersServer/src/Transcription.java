import java.net.Socket;
import java.util.*;

import javax.swing.SwingWorker;

public class Transcription
{
	public InClient inClient;
	public OutClient outClient;
	public ClientInfo clientsInfo;
	public Connect connect;
	public Listen listen;
	public Send send;
	public ParseToClient parseToClient;
	public ParseFromClient parseFromClient;
	
	private Socket socket;
	
	private static Transcription singleton;
	
	private IO io;

	private Transcription()
	{
		//inClient = new inClient();
		//outClient = new outClient();	// these are done in Game.Connect, when we have a socket

		clientsInfo = new ClientInfo();
		
		connect = new Connect();
		connect(); 
		
		outClient = new OutClient(socket);
		//Needs connection before this happens 
		inClient = new InClient(socket);
		
		listen = new Listen();
		send = new Send();
		parseToClient = new ParseToClient();
		parseFromClient = new ParseFromClient();
	}
	
	public static Transcription getTranscription()	// implements singleton
	{
		if(singleton == null)
		{ 
			singleton = new Transcription(); 
		}
		return singleton;
	}
	
	public Socket getClientSocket(int index)
	{
		/*if (clientsInfo != null && clientsInfo.size() >= index)
		{
			return clientsInfo.get(index).getSocket(); 
		}*/
		return null;
		
	}
	
	public ClientInfo getClientForMatching()
	{
		//ClientInfo client = clientsInfo.remove(0);
		//return client;
		return null;
	}
	
	public int getClientsCount()
	{
		//return clientsInfo.size();
		return 0; 
	}
	
	public void removeDisconnectedClients()
	{
		/*ArrayList<ClientInfo> disconnectedClients = new ArrayList<ClientInfo>();
		for (int i = 0; i < clientsInfo.size(); i++)
		{
			ClientInfo client = clientsInfo.get(i);
			// if (Socket of ClientInfo is disconnected) (test through reading or writing)
			if (false)
				disconnectedClients.add(client);
		}
		
		clientsInfo.removeAll(disconnectedClients);*/
	}
	
	public Object read()
	{ return inClient.read(); }
	
	public Byte readByte()
	{ return inClient.readByte(); }
	
	public void write(String message)
	{ outClient.write(message); }
	
	public void readMove()
	{
		
	}
	
	public void translateToClient()
	{
		
	}
	
	public void translateFromClient(Byte messageType)
	{
		
	}
	
	public void connect()
	{
		socket = connect.connectSocket();
		//outClient = new outClient(socket);
		//inClient = new inClient(socket);
		
		SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>()
		{
			protected Void doInBackground() throws Exception
			{                
				listen();
				return null;
			}
			@Override
			protected void done()
			{
				try
				{ }
				catch (Exception e)
				{ }
			}
		};
		worker.execute();
		
	/*	Byte messageType = inClient.readByte();
		if(messageType == 'C')
		{
			String color = (String) inClient.read();
			GameScreen.getGameScreen().setColor(color);
		}*/
	}
	
	public void listen()
	{ listen.retrieveMessages(); }
	
	public Socket getServerSocket()
	{
		return clientsInfo.getSocket(); 
	}
}
