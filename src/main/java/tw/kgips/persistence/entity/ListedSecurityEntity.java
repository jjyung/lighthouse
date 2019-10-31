package tw.kgips.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "listed_security")
public class ListedSecurityEntity {

    @Id
    @Column(name = "company_code", length = 15)
    private String companyCode;

    @Column(name = "company_name", length = 15)
    private String companyName;

    @Column(name = "listing_date", length = 15)
    private String listingDate;

    @Column(name = "market_cat", length = 15)
    private String marketCat;

    @Column(name = "industry_cat", length = 63)
    private String industryCat;

    @Column(name = "isin_code", length = 63)
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
}
