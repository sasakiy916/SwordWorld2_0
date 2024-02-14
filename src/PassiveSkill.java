
public class PassiveSkill extends Skill {

	public PassiveSkill() {
		effect();
	}
	@Override
	public void effect() {
		PassiveSkills skill = PassiveSkills.WEPONMASTERY;
		switch(skill) {
		case WEPONMASTERY:
			setName("武器習熟");
			Weapon.WeponList wl = Weapon.WeponList.SWORD;
			switch(wl) {
			case AXE:
				break;
			case SWORD:
				setName(getName() + ":ソード");
				break;
			default:
				break;
			}
			break;
		case ARMORMASTERY:
			break;
		case BOTHHANDED:
			break;
		default:
			break;
		}
	}

}
