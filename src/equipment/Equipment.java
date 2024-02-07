package equipment;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use=JsonTypeInfo.Id.CLASS)
@JsonIgnoreProperties(ignoreUnknown=true)
public abstract class Equipment{
	public enum EquipmentList {
		WEPON,
		PROTECTOR,
	}
	private String name;//名称
	private int needStr;//必要筋力
	private int price;//価格
	//名称
	public String getName() {
		return this.name;
	}
	public void setName(String name) {
		this.name = name;
	}
	//必筋
	public int getNeedStr() {
		return this.needStr;
	}
	public void setNeedStr(int needStr) {
		this.needStr = needStr;
	}
	//価格
	public int getPrice() {
		return this.price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	//武器情報の表示
	public String toString() {
		System.out.println("---------------------------------------");
		System.out.println((this instanceof Weapon?"・武器名:":"・防具名:") + getName());
		System.out.println("・価格:"+getPrice()+"G");
		System.out.println("・必要筋力:"+getNeedStr());
		return "";
	}
}
