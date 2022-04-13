import java.util.Scanner;

public class Human extends Player{
	//コンストラクタ
	public Human(){
		this.setRace("人間");
		this.getBirths().put("魔動機師", new int[]{8,4,9});
		this.getBirths().put("魔法使い", new int[]{7,4,10});
		this.getBirths().put("軽戦士", new int[]{10,7,8});
	}
	//生まれによる技能習得
	public void learnJob() {
		Scanner scan = new Scanner(System.in);
		int select = 0;
		switch(this.getBirth()) {
		case "魔動機師":
			jobLevelUp("マギテック");
			setExp(2000);
			break;
		case "魔法使い":
			do {
				System.out.println("取得可能技能");
				System.out.println("ソーサラー:0");
				System.out.println("コンジャラー:1");
				System.out.print("取得する技能を選んで下さい>>");
				select = scan.nextInt();
				switch(select) {
				case 0:	
					jobLevelUp("ソーサラー");
					break;
				case 1:
					jobLevelUp("コンジャラー");
					break;
				}
			}while(select != 0 && select != 1);
			setExp(2000);
			break;
		case "軽戦士":
			jobLevelUp("スカウト");
			jobLevelUp("フェンサー");
			setExp(2000);
			break;
		}
		System.out.println();
	}
	
}
