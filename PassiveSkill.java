public class PassiveSkill extends Skill {

	@Override
	public void effect() {
		PassiveSkills skill = PassiveSkills.WEPONMASTERY;
		switch(skill) {
		case WEPONMASTERY:
			WeponList wl = WeponList.KNIFE;
			switch(wl) {
			case KNIFE:
				break;
			case SHORTSWORD:
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
