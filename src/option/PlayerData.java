package option;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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

	//保存された冒険者を読み込む
	public static Player load() {
		Scanner scan = new Scanner(System.in);
		List<Player> players = new ArrayList<>();
		ObjectMapper mapper = new ObjectMapper();
		//jsonファイル読み込み
		try (BufferedReader br = new BufferedReader(new FileReader("player/player.json"))) {
			String json = null;
			while ((json = br.readLine()) != null) {
				players.add(mapper.readValue(json, Player.class));
			}
		} catch (IOException e) {
			System.out.println("ファイルの読み込みに失敗しました");
		}

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
	public static void selectRemove(int select) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		//jsonファイルのパス取得
		File path = new File("player/player.json");
		String json = null;
		//データを一行ずつ読み込みリストに格納
//		List<String> players = new ArrayList<>();
		List<String> players = Option.loadString(path);
//		try (BufferedReader br = new BufferedReader(new FileReader(path))) {
//			while ((json = br.readLine()) != null) {
//				players.add(json);
//			}
//		} catch (IOException e) {
//			;
//		}
		//指定キャラ削除
		String removePlayer = mapper.readValue(players.get(select), Player.class).getName();//削除するキャラの名前取得
		players.remove(select);//指定キャラ削除
		//指定行を削除した後、新しい内容を上書き
		//既存ファイル削除
		path.delete();
		//指定パスが存在するか確認
		if (!path.exists()) {
			System.out.println("ファイルはありません");
		} else {
			System.out.println("残ってる");
		}
		//新しい内容を書き込み
		FileWriter fw = new FileWriter(path, true);
		//書き込み処理
		for (String s : players) {
			//改行コード付きでjsonファイルに書き込む
			json = s + System.getProperty("line.separator");
			fw.write(json);
		}
		fw.close();//ファイルを閉じる
		// 削除キャラの名前表示
		System.out.printf("%sを削除しました%n", removePlayer);
	}

	//保存されてるキャラ一覧
	public static boolean showStayPlayer() {
		try {
			ObjectMapper mapper = new ObjectMapper();
			FileInputStream fis = new FileInputStream("player/player.json");
			InputStreamReader isr = new InputStreamReader(fis, "utf-8");
			BufferedReader br = new BufferedReader(isr);
			List<Player> stayMember = new ArrayList<>();
			String line;
			while ((line = br.readLine()) != null) {
				stayMember.add(mapper.readValue(line, Player.class));
			}
			br.close();//ファイル閉じる
			//酒場に居る冒険者一覧
			System.out.println("酒場に居る冒険者");
			for (int i = 0; i < stayMember.size(); i++) {
				System.out.printf("%s %s%n", stayMember.get(i).getName(), i + 1);
			}
			return true;
		} catch (Exception e) {
			System.out.println("待機中の冒険者は居ません");
			return false;
		}
	}
}
