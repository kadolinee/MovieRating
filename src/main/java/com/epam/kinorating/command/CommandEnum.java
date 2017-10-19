package com.epam.kinorating.command;

import com.epam.kinorating.command.admin.*;
import com.epam.kinorating.command.user.*;

public enum CommandEnum {
    LOGIN(new LoginCommand()),
    GOTOLOGIN(new GoToLoginCommand()),
    REGISTRATION(new RegistrationCommand()),
    GOTOREGISTRATION(new GoToRegistrationCommand()),
    GOTOHOME(new GoToHomeCommand()),
    LOGOUT(new LogoutCommand()),
    DORATING(new DoRatingCommand()),
    MAKEREVIEW(new MakeReviewCommand()),
    GOTOMOVIE(new GoToMovieCommand()),
    SEARCHMOVIE(new SearchMovieCommand()),
    GOTOACCOUNT(new GoToAccountCommand()),
    GOTOERROR(new GoToErrorCommand()),

    ADDMOVIE(new AddMovieCommand()),
    GOTOADDMOVIE(new GoToAddMovieCommand()),
    EDITUSER(new EditUserCommand()),
    GOTOEDITUSER(new GoToEditUserCommand()),
    VIEWUSERS(new ViewUsersCommand()),
    BANUSER(new BanUserCommand());

    private Command command;

    CommandEnum(Command command) {
        this.command = command;
    }

    public Command getCurrentCommand() {
        return command;
    }
}
