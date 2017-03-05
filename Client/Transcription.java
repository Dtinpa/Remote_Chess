import java.net.Socket;

import javax.swing.SwingWorker;

public class Transcription
{
	public InServer inServer;
	public OutServer outServer;
	public ServerInfo serverInfo;
	public Connect connect;
	public Listen listen;
	public Send send;
	public ParseToServer parseToServer;
	public ParseFromServer parseFromServer;
	
	private Socket socket;
	
	private static Transcription singleton;
	
	private IO io;
	
	private Transcription()
	{
		//inServer = new InServer();
		//outServer = new OutServer();	// these are done in Game.Connect, when we have a socket
		connect();
		outServer = new OutServer(socket);
		inServer = new InServer(socket);
		
		serverInfo = new ServerInfo();
		connect = new Connect();
		listen = new Listen();
		send = new Send();
		parseToServer = new ParseToServer();
		parseFromServer = new ParseFromServer();
	}
	
	public static Transcription getTranscription()	// implements singleton
	{
		if(singleton == null)
		{ singleton = new Transcription(); }
		return singleton;
	}
	
	public Object read()
	{ return inServer.read(); }
	
	public Byte readByte()
	{ return inServer.readByte(); }
	
	public void write(String message)
	{ outServer.write(message); }
	
	public void sendMove(Space clicked)
	{ send.sendMove(clicked); }
	
	public void translateToServer(Space clicked)
	{ parseToServer.translate(clicked); }
	
	public void translateFromServer(Byte messageType)
	{ parseFromServer.translate(messageType); }
	
	public void connect()
	{
		socket = connect.connectSocket();
		//outServer = new OutServer(socket);
		//inServer = new InServer(socket);
		
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
		
	/*	Byte messageType = inServer.readByte();
		if(messageType == 'C')
		{
			String color = (String) inServer.read();
			GameScreen.getGameScreen().setColor(color);
		}*/
	}
	
	public void listen()
	{ listen.retrieveMessages(); }
	
	public Socket getServerSocket()
	{
		return serverInfo.getSocket(); 
	}
}
