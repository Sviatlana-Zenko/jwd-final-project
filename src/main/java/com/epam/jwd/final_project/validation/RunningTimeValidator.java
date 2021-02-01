package com.epam.jwd.final_project.validation;

import com.epam.jwd.final_project.domain.CinemaProduct;
import java.util.List;

public class RunningTimeValidator extends Validator<CinemaProduct> {

    public void validate(CinemaProduct product, List<String> validationErrors, ValidationType type) {
        Integer runningTime = product.getRunningTime();

        if (runningTime == null || runningTime.equals(0)) {
            if (type == ValidationType.CREATE_OBJECT) {
                validationErrors.add("'running time' field is not filled or has wrong format");
            }
        } else {
            if (runningTime <= 0) {
                validationErrors.add("'running time' can't be negative or equal to zero");
            }
        }

        super.validate(product, validationErrors, type);

    }

}
