package option;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;

public class Option {
	//ファイル読み込み
	//パーティ情報を読み込む用
	public static ArrayList<String> loadString(String path) {
		return loadString(new File(path));
	}

	public static ArrayList<String> loadString(File path) {
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

	public static ArrayList<String[]> loadFromCSV(String path) {
		ArrayList<String[]> list = new ArrayList<>();
		for (String line : loadString(path)) {
			String[] params = line.split(",", -1);
			list.add(params);
		}
		return list;
	}

	// メニュー一覧を表示
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
