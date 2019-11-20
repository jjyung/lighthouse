package tw.kgips.dto.listed_security;

public enum MarketCat {
	SII("上市"), OTC("上櫃");

	private String value;

	MarketCat(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
