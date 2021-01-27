package com.epam.jwd.final_project.controller.command;

public enum CommandType {

    TO_MAIN_PAGE("main-page"),
    TO_SIGN_IN_FORM("sign-in-form"),
    SIGN_IN("sign-in"),
    TO_DB_ERROR_PAGE("db-error"),
    SHOW_ACCOUNT_INFO("account-info"),
    TO_NEW_ACCOUNT_FORM("new-account-form"),
    CREATE_NEW_ACCOUNT("create-account"),
    TO_LOGIN_RESULT("login-result"),
    TO_QUOTE_ERROR("quote-error");

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

    //NO_SHOW("no-show"),
    //    SHOW_ALL("show"),
    //    NEXT("next"),
    //    LOGIN("login"),
    //    TO_SIGN_IN_FORM("sign-in-form"),
    //    TO_NEW_ACCOUNT_FORM("new-account-form"),
    //    NEW_ACCOUNT("account"),
    //    CHANGE_LOCALE("change-locale"),
    //    TO_LOGIN_RESULT("login-result"),
    //    REFRESH("refresh"),
    //    TO_USER_ACCOUNT("user-account"),
    //    SHOW_ACCOUNT_INFO("account-info"),
    //    SHOW_USER_REVIEWS("show-user-reviews"),
    //    SHOW_ALL_MOVIES("show-movies"),
    //    TO_SECRET_PAGE("secret-page"),
    //    SHOW_REVIEWS("show-reviews"),
    //    UPDATE_REVIEW_MARKS("update-marks"),
    //    TO_REVIEW_FORM("review-form"),
    //    CREATE_REVIEW("create-review"),
    //    SHOW_TOP_MOVIES("top-movies"),
    //    TO_MAIN_PAGE("main-page"),
    //    SHOW_MOVIE_INFO("movie-info"),
    //    SHOW_ALL_TV_SERIES("show-tvseries"),
    //    SHOW_TV_SERIES_INFO("tvseries-info"),
    //    SEARCH("search"),
    //    TO_SEARCH_RESULT("search-result"),
    //    EDIT_ACCOUNT("edit-account"),
    //    TO_EDIT_ACCOUNT_FORM("edit-account-form"),
    //    DELETE_ACCOUNT("delete-account"),
    //    SHOW_ADMIN_ACTIONS("admin-actions"),
    //    EDIT_QUOTE("edit-quote"),
    //    TO_EDIT_QUOTE_FORM("edit-quote-form"),
    //    ADMIN_TO_USER_ACTIONS("user-actions"),
    //    SHOW_USERS("show-users"),
    //    CHANGE_BLOCK_STATUS("change-block-status"),
    //    TO_USER_BY_ID_FORM("user-by-id"),
    //    SHOW_FOUND_USER("found-user"),
    //    ADMIN_TO_PRODUCT_ACTIONS("product-actions"),
    //    FIND_PRODUCT("find-product"),
    //    ENTER_TITLE("enter-title"),
    //    TO_NEW_PRODUCT_FORM("new-product-form"),
    //    CREATE_MOVIE("create-movie"),
    //    QUOTE_OPERATIONS("quote-operations"),
    //    DELETE_QUOTE("delete-quote"),
    //    TO_NEW_QUOTE_FORM("new-quote-form"),
    //    CREATE_QUOTE("create-quote"),
    //    TO_QUOTE_ERROR("quote-error"),
    //    TO_DB_ERROR_PAGE("db-error"),
    //    CONFIRM_QUOTE_DELETING("confirm-quote-deleting");

}


//    NO_SHOW("no-show"),
//    SHOW_ALL("show"),
//    NEXT("next");
//
//    private String commandName;
//
//    CommandType(String commandName) {
//        this.commandName = commandName;
//    }
//
//    public static CommandType getEnumByCommandName(String commandName) {
//        CommandType type = null;
//
//        CommandType[] types = CommandType.values();
//        int i = 0;
//        while (i < types.length){
//            if(types[i].commandName.equals(commandName)) {
//                type = types[i];
//                break;
//            }
//
//            i++;
//        }
//
//        return  type;
//    }












/*
NO_SHOW("no-show", false),
    SHOW_ALL("show", false),
    LOGIN_RESULT("login", true);

    private String commandName;
    private boolean isRedirect;
//    private String beautifyUrl;

    CommandType(String commandName, boolean isRedirect) {
        this.commandName = commandName;
        this.isRedirect = isRedirect;
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
 */
