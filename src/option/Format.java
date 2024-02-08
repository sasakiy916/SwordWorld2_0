package option;

public class Format {
	// 幅寄せ指定無し
	public static String format(String str, int length) {
		String modifier = str.matches("[0-9.]*") ? "" : "-";
		int digit = getHarfDigit(str, length);
		return String.format("%" + modifier + digit + "s", str);
	}

	// 幅寄せ指定有り
	public static String format(String str, int length, boolean isAlignRight) {
		String modifier = isAlignRight ? "" : "-";
		int digit = getHarfDigit(str, length);
		return String.format("%" + modifier + digit + "s", str);
	}

	// 半角換算の桁数取得
	private static int getHarfDigit(String str, int length) {
		int diff = 0;
		for (String s : str.split("")) {
			diff += s.getBytes().length / 2;
		}
		return length - diff;
	}

	private static int getHarfDigit(String str) {
		int digit = 0;
		for (String s : str.split("")) {
			digit += s.length() + s.getBytes().length / 2;
		}
		return digit;
	}
	
	// 配列の要素で一番多い文字数（半角換算）取得
	public static int getMaxDigit(String[] array) {
		int maxDigit = 0;
		for(int i=0;i<array.length;i++) {
			maxDigit = Math.max(maxDigit, getHarfDigit(array[i]));
		}
		return maxDigit;
	}
}
