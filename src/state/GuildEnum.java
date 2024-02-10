package state;

public enum GuildEnum {
	BAR("酒場"),
	SHOP("ショップ"),
	BOARD("掲示板"),
	GROWUP("成長"),
	;

	private final String text;

	GuildEnum(final String name) {
		this.text = name;
	}
	
	public String getText() {
		return this.text;
	}
}
