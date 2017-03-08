import java.io.IOException;
import java.util.*;

public class MatchMaking
{
	ArrayList<ClientInfo> clientOnes;
	ArrayList<ClientInfo> clientTwos;
	
	public MatchMaking()
	{
		clientOnes = new ArrayList<ClientInfo>();
		clientTwos = new ArrayList<ClientInfo>();
	}
	
	private int getIndexForNewMatch()
	{
		for (int i = 0; i < clientOnes.size(); i++)
		{
			if (clientOnes.get(i) == null)
				return i;
		}
		
		return clientOnes.size();
	}
	
	public void matchClients()
	{
		Transcription transcription = Transcription.getTranscription();
		transcription.removeDisconnectedClients();
		if (transcription.getClientsCount() > 2)
		{
			int matchIndex = getIndexForNewMatch();
			ClientInfo firstClient = transcription.getClientForMatching();
			ClientInfo secondClient = transcription.getClientForMatching();
			if (matchIndex == clientOnes.size())
			{
				clientOnes.add(firstClient);
				clientTwos.add(secondClient);
			}
			else
			{
				clientOnes.set(matchIndex, firstClient);
				clientTwos.set(matchIndex, secondClient);
			}
			GameManagement.getInstance().openGameForClients(matchIndex);
		}
	}
	
	public int getClientIndex(ClientInfo disconnected)
	{
		int index = -1;
		index = clientOnes.indexOf(disconnected);
		if (index == -1)
			index = clientTwos.indexOf(disconnected);
		return index;
	}
	
	public void unmatchClients(int game_index)
	{
		try
		{
			clientOnes.get(game_index).getSocket().close();
			clientTwos.get(game_index).getSocket().close();
			clientOnes.set(game_index, null);
			clientTwos.set(game_index, null);
			
			GameManagement.getInstance().closeGame(game_index);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}
