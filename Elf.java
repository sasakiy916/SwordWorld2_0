public class Elf extends Player {
	public Elf() {
		this.setRace("エルフ");
		this.getBirths().put("剣士", new int[]{12,5,9});
		this.getBirths().put("射手", new int[]{13,5,8});
	}
	public void learnJob() {
		switch(this.getBirth()) {
		case "剣士":
			jobLevelUp("フェンサー");
			setExp(2500);
			break;
		case "射手":
			jobLevelUp("シューター");
			setExp(2500);
			break;
		}
	}
}
