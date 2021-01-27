package com.epam.jwd.final_project.domain;

import java.util.Objects;

public class Quote extends AbstractAppEntity {

    private String quoteText;
    private String productTitle;
    private String posterUrl;

    public Quote(Long id, String quoteText, String productTitle, String posterUrl) {
        super(id);
        this.quoteText = quoteText;
        this.productTitle = productTitle;
        this.posterUrl = posterUrl;
    }

    public Quote(String quoteText, String productTitle, String posterUrl) {
        this.quoteText = quoteText;
        this.productTitle = productTitle;
        this.posterUrl = posterUrl;
    }

    public Quote(Long id) {
        super(id);
    }

    public String getQuoteText() {
        return quoteText;
    }

    public String getProductTitle() {
        return productTitle;
    }

    public String getPosterUrl() {
        return posterUrl;
    }

    public void setQuoteText(String quoteText) {
        this.quoteText = quoteText;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
    }

    public void setPosterUrl(String posterUrl) {
        this.posterUrl = posterUrl;
    }

    @Override
    public String toString() {
        return "Quote{" +
                "quoteText='" + quoteText + '\'' +
                ", productTitle='" + productTitle + '\'' +
                ", posterUrl='" + posterUrl + '\'' +
                "} " + super.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Quote quote = (Quote) o;
        return Objects.equals(quoteText, quote.quoteText) &&
               Objects.equals(productTitle, quote.productTitle) &&
               Objects.equals(posterUrl, quote.posterUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(quoteText, productTitle, posterUrl);
    }
}
