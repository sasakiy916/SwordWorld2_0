package option;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import character.player.Player;

public class PlayerData {
	//Playerクラスの情報をplayer.jsonに保存
	public static void save(Player player) {
		save(player, "player.json");
	}

	public static void save(Player player, String path) {
		save(player, path, true);
	}

	public static void save(Player player, String path, boolean isWrite) {
		try (FileWriter fw = new FileWriter("player/" + path, isWrite)) {//true:追加書き込み、第二引数無し：上書き
			//Json記述
			ObjectMapper mapper = new ObjectMapper();
			String json = mapper.writeValueAsString(player);//プレイヤーデータ
			json += System.getProperty("line.separator");//改行コード
			//jsonファイルへ書き込み
			fw.write(json);
		} catch (IOException e) {
			System.out.println("保存に失敗しました");
		}
	}
	
	// 待機メンバーの保存（上書き）
	public static void saveMember(List<Player> stayMember, String fileName) {
		File path = new File("player/player.json");
		path.delete();
		for(Player p:stayMember) {
			save(p);
		}
	}

	public static void saveMember(List<Player> stayMember) {
		saveMember(stayMember,"player/player.json");
	}

	//保存された冒険者を読み込む
	public static Player load() {
		Scanner scan = new Scanner(System.in);
		List<Player> players = getStayMember();

		//読み込んだ内容からPlayerクラス作成
		do {
			int target = 0;
			int count = 0;
			// 一覧表示
			for (Player player : players) {
				System.out.printf("%d:%s 冒険者レベル:%d HP:%d%n", ++count, player.getName(),
						player.getLevel(), player.getHp());
			}
			// キャラを選ぶ
			System.out.print("どのキャラを使いますか(選択中断:0)>>");
			target = scan.nextInt() - 1;
			if (target == -1) {
				return null;
			} else if (target < -1 || players.size() <= target) {
				System.out.println("選択肢以外が選択されました\n");
				continue;
			}
			// 確認
			System.out.println(players.get(target));
			System.out.print("このキャラを使います？(はい:0,いいえ:1)>>");
			if (scan.nextInt() == 0) {
				return players.get(target);
			}
		} while (true);
	}

	//指定キャラ削除
	public static void selectRemove(int select){
		// 待機メンバー取得
		List<Player> stayMember = getStayMember();
		// 待機メンバーから１人削除する
		String removePlayer = stayMember.remove(select).getName();
		saveMember(stayMember);
		// 削除キャラの名前表示
		System.out.printf("%sを削除しました%n", removePlayer);
	}

	//保存されてるキャラ一覧
	public static boolean showStayPlayer() {
		List<Player> stayMember = getStayMember();
		// 冒険者が居なければfalse
		if(stayMember == null)return false;

		//酒場に居る冒険者一覧
		System.out.println("酒場に居る冒険者");
		for (int i = 0; i < stayMember.size(); i++) {
			System.out.printf("%s %s%n", stayMember.get(i).getName(), i + 1);
		}
		return true;
	}

	// 待機メンバー取得
	public static List<Player> getStayMember() {
		List<Player> stayMember = null;

		// ファイルからデータ読み込み
		String path = "player/player.json";
		List<String> list = Option.loadString(path);
		if (list.size() <= 0) {
			System.out.println("待機中の冒険者は居ません");
			return stayMember;
		}

		// json文字列から待機メンバーをインスタンス化
		ObjectMapper mapper = new ObjectMapper();
		stayMember = new ArrayList<>();
		for (String json : list) {
			try {
				Player player = mapper.readValue(json, Player.class);
				stayMember.add(player);
			} catch (JsonProcessingException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
		}
		return stayMember;
	}
}
