package option;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Option {
	//ファイル読み込み、行ごとにString型にしてList返す
	public static List<String> loadString(String path) {
		return loadString(new File(path));
	}

	public static List<String> loadString(File path) {
		ArrayList<String> list = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path), "UTF-8"))) {
			String line = null;
			while ((line = br.readLine()) != null) {
				list.add(line);
			}
		} catch (IOException e) {
			System.out.println("読み込めるファイルがありません");
		}
		return list;
	}

	// csvファイルを読み込み、配列を返す
	public static List<String[]> loadFromCSV(String path) {
		List<String[]> list = new ArrayList<>();
		for (String line : loadString(path)) {
			String[] params = line.split(",", -1);
			list.add(params);
		}
		return list;
	}

	public static Map<String, String[]> loadFromCSVWithTitleCol(String path) {
		Map<String, String[]> map = new HashMap<>();
		List<String[]> list = loadFromCSV(path);

		// タイトル取得
		String[] titles = list.get(0);
		for (String s : titles) {
			map.put(s, new String[list.size() - 1]);
		}

		// タイトルごとのデータ一覧取得
		for (int col = 0; col < titles.length; col++) {
			String[] datas = map.get(titles[col]); // 取得する列のタイトル
			// 列の行ごとのデータ取得
			for (int row = 1; row < list.size(); row++) {
				datas[row - 1] = list.get(row)[col];
			}
		}
		return map;
	}

	// メニュー一覧を表示
	public static void showSelectMenu(List<String> menu) {
		String[] menuArray = new String[menu.size()];
		showSelectMenu(menu.toArray(menuArray));
	}

	public static void showSelectMenu(List<String> menu,int lineLength) {
		String[] menuArray = new String[menu.size()];
		showSelectMenu(menu.toArray(menuArray),lineLength);
	}

	public static void showSelectMenu(String[] menu) {
		showSelectMenu(menu, 30);
	}

	public static void showSelectMenu(String[] menu, int lineLength) {
		int length = Format.getMaxDigit(menu);
		Option.printLine(lineLength);
		for (int i = 0; i < menu.length; i++) {
			System.out.printf("%s %s%n", Format.format(menu[i], length), i + 1);
		}
		Option.printLine(lineLength);
	}

	// 選択肢表示→入力番号を返す
	public static int selectMenu(String[] menuNames, String menuName) {
		Scanner scan = new Scanner(System.in);
		showSelectMenu(menuNames);
		System.out.print("どの" + menuName + "にしますか？>>");
		return scan.nextInt() - 1;
	}

	// 指定ミリ秒数、一時停止
	public static void sleep(int miliTime) {
		try {
			Thread.sleep(miliTime);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	// 選択肢の範囲内チェック
	public static boolean isWithinRange(int target, int start, int last) {
		return start <= target && target <= last;
	}

	public static boolean isWithinRange(int target, int last) {
		return isWithinRange(target, 0, last);
	}

	//ライン表示
	public static void printLine(int num) {
		printLine(num, "-");
	}

	public static void printLine(int num, String line) {
		for (int i = 0; i < num; i++) {
			System.out.print(line);
		}
		System.out.println();
	}

	//全角半角の文字位置合わせ
	public static String format(String target, int length) {
		int byteDiff = (getByteLength(target) - target.length()) / 2;
		return String.format("%-" + (length - byteDiff) + "s", target);
	}

	//文字のバイト数取得
	private static int getByteLength(String string, Charset charset) {
		return string.getBytes(charset).length;
	}

	private static int getByteLength(String string) {
		return getByteLength(string, Charset.forName("UTF-8"));
	}
}
