package manager;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import character.player.Character;
import character.player.Player;
import option.Option;

public class PartyManager {
	private static final PartyManager instance = new PartyManager();
	private List<Character> playerParty;
	private List<Character> monsterParty;
	String path = "player/party.json";

	private PartyManager() {
		playerParty = new ArrayList<>();
		monsterParty = new ArrayList<>();

		//パーティ情報の読み込み
		ObjectMapper mapper = new ObjectMapper();
		List<String> loadParty = Option.loadString(path);
		for (String loadMember : loadParty) {
			try {
				playerParty.add(mapper.readValue(loadMember, Player.class));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static PartyManager getInstance() {
		return instance;
	}
	
	public List<Character> getPlayerParty(){
		return this.playerParty;
	}
	
	public List<Player> getPlayerPartyAsPlayer(){
		List<Player> list = new ArrayList<>();
		for(Character c : this.playerParty) {
			if(c instanceof Player) {
				list.add((Player)c);
			}else {
				return null;
			}
		}
		return list;
	}

	public List<Character> getMonsterParty(){
		return this.monsterParty;
	}
}
