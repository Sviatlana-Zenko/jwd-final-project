package com.epam.jwd.final_project.validation;

import com.epam.jwd.final_project.domain.CinemaProduct;
import java.util.List;

public class PosterUrlValidator extends Validator<CinemaProduct> {

    private static final int MAX_LENGTH = 80;
    private static final String URL_PATTERN = "^https://drive\\.google\\.com/.+";

    @Override
    public void validate(CinemaProduct product, List<String> validationErrors, ValidationType type) {
        String posterUrl = product.getPosterUrl();
        if (type == ValidationType.CREATE_OBJECT
                & (posterUrl == null || posterUrl.length() == 0)) {
            validationErrors.add("'poster url' field is not filled");
        } else {
            if (posterUrl != null) {
                if (posterUrl.length() > MAX_LENGTH) {
                    validationErrors.add("'poster url' is longer than " + MAX_LENGTH + " characters");
                } else {
                    if (!posterUrl.matches(URL_PATTERN)) {
                        validationErrors.add("'poster url' doesn't start with 'https://drive.google.com/...'");
                    }
                }
            }
        }

        super.validate(product, validationErrors, type);
    }
    
}
