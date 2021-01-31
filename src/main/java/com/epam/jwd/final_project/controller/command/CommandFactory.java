package com.epam.jwd.final_project.controller.command;

import com.epam.jwd.final_project.controller.command.impl.*;
import com.epam.jwd.final_project.controller.command.impl.NewAccountCommand;
import com.epam.jwd.final_project.controller.command.impl.ToNewProductFormCommand;

public class CommandFactory {

    public static Command command(String commandName) {
        if (commandName == null) {
            return new ToMainPageCommand();
        }

        switch (CommandType.getEnumByCommandName(commandName)) {
            case TO_MAIN_PAGE:
                return new ToMainPageCommand();
            case TO_SEARCH_RESULT:
                return new ToSearchResultCommand();
            case TO_SIGN_IN_FORM:
                return new ToSignInFormCommand();
            case TO_DB_ERROR_PAGE:
                return new ToDatabaseErrorPageCommand();
            case TO_NEW_ACCOUNT_FORM:
                return new ToNewAccountFormCommand();
            case TO_LOGIN_RESULT:
                return new ToLoginResultCommand();
            case TO_VALIDATION_ERROR:
                return new ToValidationErrorPageCommand();
            case TO_REVIEW_FORM:
                return new ToReviewFormCommand();
            case TO_NEW_QUOTE_FORM:
                return new ToNewQuoteFormCommand();
            case TO_EDIT_QUOTE_FORM:
                return new ToEditQuoteFormCommand();
            case TO_FORM:
                return new ToNewProductFormCommand();
            case USER_SEARCH:
                return new ToUserSearchParamsCommand();
            case PRODUCT_SEARCH:
                return new ToProductSearchParamsCommand();
            case SIGN_IN:
                return new SignInCommand();
            case SIGN_OUT:
                return new SignOutCommand();
            case SHOW_ACCOUNT_INFO:
                return new ShowAccountInfoCommand();
            case CREATE_NEW_ACCOUNT:
                return new NewAccountCommand();
            case SHOW_USER_REVIEWS:
                return new ShowUserReviewsCommand();
            case SHOW_PRODUCTS:
                return new ShowProductsCommand();
            case SHOW_PRODUCT_INFO:
                return new ShowProductInfoCommand();
            case SHOW_REVIEWS:
                 return new ShowReviewsCommand();
            case EDIT_REVIEW_MARKS:
                return new EditReviewMarksCommand();
            case SEARCH:
                return new SearchCommand();
            case CREATE_REVIEW:
                return new CreateReviewCommand();
            case QUOTE_OPERATIONS:
                return new QuoteOperationsCommand();
            case USER_OPERATIONS:
                return new UserOperationsCommand();
            case PRODUCT_OPERATIONS:
                return new ProductOperationsCommand();
            case CREATE_QUOTE:
                return new CreateQuoteCommand();
            case EDIT_QUOTE:
                return new EditQuoteCommand();
            case DELETE_QUOTE:
                return new DeleteQuoteCommand();
            case DELETE_USER:
                return new DeleteUserCommand();
            case FIND_USER:
                return new GetParamToFindUserCommand();
            case FIND_PRODUCT:
                return new GetParamToFindProductCommand();
            case CONFIRM_QUOTE_DELETING:
                return new ConfirmQuoteDeletingCommand();
            case CHANGE_BLOCK_STATUS:
                return new ChangeBlockStatusCommand();
            case SHOW_USERS:
                return new ShowUsersCommand();
            case SHOW_FOUND_USER:
                return new ShowFoundUserCommand();
            case SHOW_FOUND_PRODUCT:
                return new ShowFoundProductCommand();
            case CREATE_MOVIE:
                return new CreateMovieCommand();
            default:
                return new ToMainPageCommand();
        }
    }

    //switch (CommandType.getEnumByCommandName(commandName)) {
    //            case SHOW_ALL:
    //                return  new ShowCommand();
    //            case NO_SHOW:
    //                return new NoShowCommand();
    //            case NEXT:
    //                return new NextCommand();
    //            case LOGIN:
    //                return new LoginCommand();
    //            case TO_SIGN_IN_FORM:
    //                return new ToSignInFormCommand();
    //            case TO_NEW_ACCOUNT_FORM:
    //                return new ToNewAccountFormCommand();
    //            case NEW_ACCOUNT:
    //                return new NewAccountCommand();
    //            case CHANGE_LOCALE:
    //                return new ChangeLocaleCommand();
    //            case TO_LOGIN_RESULT:
    //                return new ToLoginResultCommand();
    //            case TO_USER_ACCOUNT:
    //                return new ToUserAccountCommand();
    //            case SHOW_ACCOUNT_INFO:
    //                return new ShowAccountInfoCommand();
    //            case SHOW_USER_REVIEWS:
    //                return new ShowUserReviewsCommand();
    //            case SHOW_ALL_MOVIES:
    //                return new ShowMoviesCommand();
    //            case TO_SECRET_PAGE:
    //                return new ToSecretPageCommand();
    //            case SHOW_REVIEWS:
    //                return new ShowReviewsCommand();
    //            case UPDATE_REVIEW_MARKS:
    //                return new EditReviewMarksCommand();
    //            case TO_REVIEW_FORM:
    //                return new ToReviewFormCommand();
    //            case CREATE_REVIEW:
    //                return new CreateReviewCommand();
    //            case SHOW_TOP_MOVIES:
    //                return new ShowTopMoviesCommand();
    //            case TO_MAIN_PAGE:
    //                return new ToMainPageCommand();
    //            case SHOW_MOVIE_INFO:
    //                return new ShowMovieInfoCommand();
    //            case SHOW_ALL_TV_SERIES:
    //                return new ShowTvSeriesCommand();
    //            case SHOW_TV_SERIES_INFO:
    //                return new ShowTvSeriesInfoCommand();
    //            case SEARCH:
    //                return new SearchCommand();
    //            case TO_SEARCH_RESULT:
    //                return new ToSearchResultCommand();
    //            case EDIT_ACCOUNT:
    //                return new EditAccountCommand();
    //            case TO_EDIT_ACCOUNT_FORM:
    //                return new ToEditAccountFormCommand();
    //            case DELETE_ACCOUNT:
    //                return new DeleteAccountCommand();
    //            case SHOW_ADMIN_ACTIONS:
    //                return new ShowAdminActionsCommand();
    //            case EDIT_QUOTE:
    //                return new EditQuoteCommand();
    //            case TO_EDIT_QUOTE_FORM:
    //                return new ToEditQuoteFormCommand();
    //            case ADMIN_TO_USER_ACTIONS:
    //                return new AdminToUserActionsCommand();
    //            case SHOW_USERS:
    //                return new ShowUsersCommand();
    //            case CHANGE_BLOCK_STATUS:
    //                return new ChangeBlockStatusCommand();
    //            case TO_USER_BY_ID_FORM:
    //                return new ToFindByIdFormCommand();
    //            case SHOW_FOUND_USER:
    //                return new ShowFoundUserCommand();
    //            case ADMIN_TO_PRODUCT_ACTIONS:
    //                return new AdminToProductsActionsCommand();
    //            case FIND_PRODUCT:
    //                return new FindProductCommand();
    //            case ENTER_TITLE:
    //                return new EnterTitleToFindCommand();
    //            case TO_NEW_PRODUCT_FORM:
    //                return new ToNewProductFormCommand();
    //            case CREATE_MOVIE:
    //                return new CreateMovieCommand();
    //            case QUOTE_OPERATIONS:
    //                return new QuoteOperationsCommand();
    //            case DELETE_QUOTE:
    //                return new DeleteQuoteCommand();
    //            case TO_NEW_QUOTE_FORM:
    //                return new ToNewQuoteFormCommand();
    //            case CREATE_QUOTE:
    //                return new CreateQuoteCommand();
    //            case TO_QUOTE_ERROR:
    //                return new ToQuoteErrorPageCommand();
    //            case TO_DB_ERROR_PAGE:
    //                return new ToDatabaseErrorPageCommand();
    //            case CONFIRM_QUOTE_DELETING:
    //                return new ConfirmQuoteDeletingCommand();
    //            default:
    //                return new MainCommand();
    //        }

}


//    public static Command command(String commandName) {
//        if (commandName == null) {
//            return new MainCommand();
//        }
//
////        switch (CommandType.getEnumByCommandName(commandName)) {
//        switch (CommandType.getEnumByCommandName(commandName)) {
//            case SHOW_ALL:
//                return  new ShowCommand();
//            case NO_SHOW:
//                return new NoShowCommand();
//            case NEXT:
//                return new NextCommand();
//            default:
//                return new MainCommand();
//        }
//    }
