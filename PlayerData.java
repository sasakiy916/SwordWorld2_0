import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.fasterxml.jackson.databind.ObjectMapper;


public class PlayerData {
	//Playerクラスの情報をplayer.jsonに保存
	public static void save(Player player) throws IOException {
		//Json記述
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(player) + System.getProperty("line.separator");//改行コード付き
		//jsonファイルへ書き込み
		FileWriter fw = new FileWriter("player/player.json",true);//true:追加書き込み、第二引数無し：上書き
		fw.write(json);
		fw.close();//ファイルを閉じる
		//デバッグ用表示
		System.out.printf("%sのデータを保存しました。",player.getName());
	}

	//保存された冒険者を読み込む
	//未完成
	public static Player load() throws IOException {
		Scanner scan = new Scanner(System.in);
		//jsonファイル読み込み
		BufferedReader br = new BufferedReader(new FileReader("player/player.json"));
		List<String> jsonList = new ArrayList<>();
		String json = br.readLine();
		while(json != null) {
			jsonList.add(json);
			json = br.readLine();
		}
		//ファイル閉じる
		br.close();
		//読み込んだ内容からPlayerクラス作成
		//未完成
		ObjectMapper mapper = new ObjectMapper();
		List<Player> players = new ArrayList<>();
		int target;
		do {
			try {
				int count = 0;
				for(String s:jsonList) {
					players.add(mapper.readValue(s,Player.class));
					System.out.printf("%d:%s 冒険者レベル:%d HP:%d%n",++count,players.get(count-1).getName(),players.get(count-1).getLevel(),players.get(count-1).getHp());
				}
				//キャラを選ぶ
				System.out.print("どのキャラを使いますか>>");
				target = scan.nextInt()-1;
				System.out.println(players.get(target));
				System.out.print("このキャラを使います？(はい:0,いいえ:0以外)>>");
				if(scan.nextInt() == 0)break;

			}catch(Exception e) {
				System.out.println("選択肢以外が選択されました");
				System.out.println();
			}
		}while(true);
		//デバッグ用
		Player player = mapper.readValue(jsonList.get(target), Player.class);
		return player;
	}
	//指定キャラ削除
	public static void selectRemove(int select) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		//jsonファイルのパス取得
		File path = new File("player/player.json");
		//データを一行ずつ読み込みリストに格納
		LineNumberReader nr = new LineNumberReader(new BufferedReader(new FileReader(path)));
		List<String> players = new ArrayList<>();
		String json;
		while((json = nr.readLine()) != null) {
			players.add(json);
		}
		nr.close();//ファイル閉じる
		//指定キャラ削除
		String removePlayer = mapper.readValue(players.get(select),Player.class).getName();//削除するキャラの名前取得
		players.remove(select);//指定キャラ削除
		//指定行を削除した後、新しい内容を上書き
		//既存ファイル削除
		path.delete();
		//指定パスが存在するか確認
		if(!path.exists()) {
			System.out.println("ファイルはありません");
		}else {
			System.out.println("残ってる");
		}
		//新しい内容を書き込み
		FileWriter fw = new FileWriter(path,true);
		//書き込み処理
		for(String s:players) {
			//改行コード付きでjsonファイルに書き込む
			json = s + System.getProperty("line.separator");
			fw.write(json);
		}	
		fw.close();//ファイルを閉じる
		//削除キャラの名前表示
		System.out.printf("%sを削除しました%n",removePlayer);
	}
}
