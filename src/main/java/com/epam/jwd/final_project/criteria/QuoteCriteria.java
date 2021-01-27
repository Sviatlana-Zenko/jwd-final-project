package com.epam.jwd.final_project.criteria;

import com.epam.jwd.final_project.domain.Quote;

public class QuoteCriteria extends Criteria<Quote> {

    private String quoteText;
    private String productTitle;
    private String posterUrl;

    public QuoteCriteria(Long id, String quoteText,
                         String productTitle, String posterUrl) {
        super(id);
        this.quoteText = quoteText;
        this.productTitle = productTitle;
        this.posterUrl = posterUrl;
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

    public static class QuoteCriteriaBuilder extends CriteriaBuilder {
        private String quoteText;
        private String productTitle;
        private String posterUrl;

        public void quoteText(String quoteText) {
            this.quoteText = quoteText;
        }

        public void productTitle(String productTitle) {
            this.productTitle = productTitle;
        }

        public void posterUrl(String posterUrl) {
            this.posterUrl = posterUrl;
        }


        public QuoteCriteria build() {
            return new QuoteCriteria(id, quoteText, productTitle, posterUrl);
        }
    }

    @Override
    public String toString() {
        return "QuoteCriteria{" +
                "quoteText='" + quoteText + '\'' +
                ", productTitle='" + productTitle + '\'' +
                ", posterUrl='" + posterUrl + '\'' +
                "} " + super.toString();
    }
}
