public class MetalArmor extends Protector {
	public enum MetalArmorList{
		SPLINTARMOR,
		CHAINARMOR,
	}
	public MetalArmor(MetalArmorList metalArmor) {
		setNames(new String[] {
				"スプリントアーマー",
				"チェインアーマー",
		});
		switch(metalArmor) {
		case SPLINTARMOR:
			setName(getNames()[metalArmor.ordinal()]);
			setNeedStr(15);
			setAvoi(0);
			setDef(5);
			setPrice(520);
			break;
		case CHAINARMOR:
			setName(getNames()[metalArmor.ordinal()]);
			setNeedStr(18);
			setAvoi(-1);
			setDef(6);
			setPrice(760);
			break;
		default:
			break;

		}
	}
}
