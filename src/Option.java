
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
public class Option {
	//ファイル書き込み

	//ファイル読み込み
	//パーティ情報を読み込む用
	public static ArrayList<String> loadString(String path){
		ArrayList<String> list = new ArrayList<>();
		try {
			FileInputStream fis = new FileInputStream(path);
			InputStreamReader isr = new InputStreamReader(fis,"utf-8");
			BufferedReader br = new BufferedReader(isr);
			String line;
			while((line = br.readLine()) != null) {
				list.add(line);
			}
			br.close();
		}catch(Exception e) {
			System.out.println("読み込めるファイルがありません");
		}
		return list;
	}
	public static ArrayList<String[]> load(String path) {
		ArrayList<String[]> list = new ArrayList<>();
		try {
			FileInputStream fis = new FileInputStream(path);
			InputStreamReader isr = new InputStreamReader(fis,"utf-8");
			BufferedReader br = new BufferedReader(isr);
			String line;
			while((line = br.readLine()) != null) {
				String[] params = line.split(",",-1);
				list.add(params);
			}
			br.close();
		}catch(Exception e) {
			System.out.println("読み込めるファイルがありません。");
		}
		return list;
	}
	//ライン表示
	public static void printLine(int num) {
		printLine(num,"-",true);
	}
	public static void printLine(int num,boolean n) {
		printLine(num,"-",n);
	}
	public static void printLine(int num,String line) {
		printLine(num,line,true);
	}
	//ライン表示
	public static void printLine(int num,String line,boolean n) {
		for(int i=0;i<num;i++) {
			System.out.print(line);
		}
		if(n)System.out.println();
	}
	//全角半角の文字位置合わせ
	public static String format(String target, int length){
		int byteDiff = (getByteLength(target, Charset.forName("UTF-8"))-target.length())/2;
		return String.format("%-"+(length-byteDiff)+"s", target);
	}

	//文字のバイト数取得
	public  static int getByteLength(String string, Charset charset) {
		return string.getBytes(charset).length;
	}
}
