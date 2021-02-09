package com.epam.jwd.final_project.controller.command;

public enum CommandType {

    TO_MAIN_PAGE("main-page"),
    TO_SIGN_IN_FORM("sign-in-form"),
    SIGN_IN("sign-in"),
    SIGN_OUT("sign-out"),
    TO_DB_ERROR_PAGE("db-error"),
    SHOW_ACCOUNT_INFO("account-info"),
    TO_NEW_ACCOUNT_FORM("new-account-form"),
    CREATE_NEW_ACCOUNT("create-account"),
    TO_LOGIN_RESULT("login-result"),
    TO_VALIDATION_ERROR("validation-error"),
    TO_REVIEW_FORM("review-form"),
    SHOW_USER_REVIEWS("show-user-reviews"),
    EDIT_REVIEW_MARKS("edit-marks"),
    SEARCH("search"),
    TO_SEARCH_RESULT("search-result"),
    TO_FORM("to-form"),
    SHOW_PRODUCTS("show-products"),
    SHOW_PRODUCT_INFO("product-info"),
    CREATE_REVIEW("create-review"),
    QUOTE_OPERATIONS("quote-operations"),
    USER_OPERATIONS("user-operations"),
    PRODUCT_OPERATIONS("product-operations"),
    TO_NEW_QUOTE_FORM("new-quote-form"),
    CREATE_QUOTE("create-quote"),
    EDIT_QUOTE("edit-quote"),
    TO_EDIT_QUOTE_FORM("edit-quote-form"),
    DELETE_QUOTE("delete-quote"),
    DELETE_USER("delete-user"),
    CONFIRM_QUOTE_DELETING("confirm-quote-deleting"),
    SHOW_USERS("users"),
    SHOW_FOUND_USER("found-user"),
    SHOW_FOUND_PRODUCT("found-product"),
    CHANGE_BLOCK_STATUS("change-block-status"),
    USER_SEARCH("user-search"),
    PRODUCT_SEARCH("product-search"),
    FIND_USER("find-user"),
    FIND_PRODUCT("find-product"),
    CREATE_MOVIE("create-movie"),
    DELETE_PRODUCT("delete-product"),
    EDIT_ACCOUNT("edit-account"),
    TO_EDIT_ACCOUNT_FORM("edit-account-form"),
    DELETE_ACCOUNT("delete-account"),
    SHOW_REVIEWS("show-reviews");

    private String commandName;

    CommandType(String commandName) {
        this.commandName = commandName;
    }

    public static CommandType getEnumByCommandName(String commandName) {
        CommandType type = null;
        CommandType[] types = CommandType.values();
        int i = 0;

        while (i < types.length){
            if(types[i].commandName.equals(commandName)) {
                type = types[i];
                break;
            }
            i++;
        }

        return  type;
    }

}
