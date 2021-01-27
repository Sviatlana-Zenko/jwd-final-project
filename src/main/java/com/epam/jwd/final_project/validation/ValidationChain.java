package com.epam.jwd.final_project.validation;

import com.epam.jwd.final_project.domain.AppEntity;

import java.util.List;

public interface ValidationChain<T extends AppEntity> {

    List<String> getValidationReport(T t, ValidationType type);

}
