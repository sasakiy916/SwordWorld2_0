import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

public class PlayerData {
	public static String save(Player player) throws IOException {
		//Json記述
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(player) + System.getProperty("line.separator");//改行コード付き
		//jsonファイルへ書き込み
		FileWriter fw = new FileWriter("player/player.json",true);//true:追加書き込み、第二引数無し：上書き
		fw.write(json);
		fw.close();//ファイルを閉じる
		//デバッグ用表示
		System.out.println(json);
		return json;
	}
	//保存された冒険者を読み込む
	public static Player load() throws IOException {
		//jsonファイル読み込み
		BufferedReader br = new BufferedReader(new FileReader("player/player.json"));
		List<String> jsonList = new ArrayList<>();
		String json = br.readLine();
		while(json != null) {
			jsonList.add(json);
			json = br.readLine();
		}
		br.close();
		//読み込んだ内容をplayerにセット
		for(String s:jsonList) {
			System.out.println(s);
		}
		ObjectMapper mapper = new ObjectMapper();
		Player player = mapper.readValue(jsonList.get(0), Player.class);
		Player player2 = mapper.readValue(jsonList.get(1), Player.class);
		//デバッグ用表示
		System.out.println(player.getName());
		System.out.println(player2.getName() + "2");
		System.out.println(jsonList.size());
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
