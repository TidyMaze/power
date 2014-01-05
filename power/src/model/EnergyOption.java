package model;

public class EnergyOption implements Comparable<EnergyOption> {
	private String guid;
	private String desc;
	private boolean selected;

	public EnergyOption(String guid, String desc, boolean selected) {
		super();
		this.guid = guid;
		this.desc = desc;
		this.selected = selected;
	}

	public String getGuid() {
		return guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	@Override
	public String toString() {
		return "EnergyOption [guid=" + guid + ", desc=" + desc + "]";
	}

	@Override
	public int compareTo(EnergyOption o) {
		return this.desc.compareTo(o.getDesc());
	}
}
