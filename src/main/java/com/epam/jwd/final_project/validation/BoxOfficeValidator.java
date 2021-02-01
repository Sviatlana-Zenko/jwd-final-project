package com.epam.jwd.final_project.validation;

import com.epam.jwd.final_project.domain.CinemaProduct;
import com.epam.jwd.final_project.domain.Movie;

import java.util.List;

public class BoxOfficeValidator extends Validator<CinemaProduct> {

    public void validate(CinemaProduct product, List<String> validationErrors, ValidationType type) {
        Integer boxOffice = ((Movie) product).getBoxOffice();

        if (boxOffice == null) {
            if (type == ValidationType.CREATE_OBJECT) {
                validationErrors.add("'boxOffice' field is not filled or has wrong format");
            }
        } else {
            if (boxOffice < 0) {
                validationErrors.add("'boxOffice' can't be negative");
            }
        }

        super.validate(product, validationErrors, type);
    }

}
