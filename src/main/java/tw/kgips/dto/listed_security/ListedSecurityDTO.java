package tw.kgips.dto.listed_security;

import tw.kgips.persistence.entity.ListedSecurityEntity;

public class ListedSecurityDTO {

	private String companyCode;

	private String companyName;

	private String listingDate;

	private String marketCat;

	private String industryCat;

	private String isinCode;

	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getListingDate() {
		return listingDate;
	}

	public void setListingDate(String listingDate) {
		this.listingDate = listingDate;
	}

	public String getMarketCat() {
		return marketCat;
	}

	public void setMarketCat(String marketCat) {
		this.marketCat = marketCat;
	}

	public String getIndustryCat() {
		return industryCat;
	}

	public void setIndustryCat(String industryCat) {
		this.industryCat = industryCat;
	}

	public String getIsinCode() {
		return isinCode;
	}

	public void setIsinCode(String isinCode) {
		this.isinCode = isinCode;
	}

	public static ListedSecurityDTO fromEntity(ListedSecurityEntity entity) {

		if (entity == null) {
			return null;
		}

		ListedSecurityDTO dto = new ListedSecurityDTO();

		dto.setCompanyCode(entity.getCompanyCode());
		dto.setCompanyName(entity.getCompanyName());
		dto.setListingDate(entity.getListingDate());
		dto.setMarketCat(entity.getMarketCat());
		dto.setIndustryCat(entity.getIndustryCat());
		dto.setIsinCode(entity.getIsinCode());

		return dto;
	}

}
