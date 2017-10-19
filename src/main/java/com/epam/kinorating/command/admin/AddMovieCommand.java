package com.epam.kinorating.command.admin;

import com.epam.kinorating.command.Command;
import com.epam.kinorating.command.user.SearchMovieCommand;
import com.epam.kinorating.config.Attribute;
import com.epam.kinorating.config.Messages;
import com.epam.kinorating.config.PagePath;
import com.epam.kinorating.entity.Movie;
import com.epam.kinorating.exception.ServiceException;
import com.epam.kinorating.service.MovieService;
import com.epam.kinorating.service.UserActionService;
import com.epam.kinorating.service.UserService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class AddMovieCommand implements Command{
    private final Logger log = Logger.getLogger(SearchMovieCommand.class);

    @Override
    public String execute(HttpServletRequest request, UserService userService, MovieService movieService,
                          UserActionService userActionService) {
        String name_ru = request.getParameter(Attribute.ATTRIBUTE_NAME_RU);
        String name_en = request.getParameter(Attribute.ATTRIBUTE_NAME_EN);
        String genre_ru = request.getParameter(Attribute.ATTRIBUTE_GENRE_RU);
        String genre_en = request.getParameter(Attribute.ATTRIBUTE_GENRE_EN);
        int year = Integer.parseInt(request.getParameter(Attribute.ATTRIBUTE_YEAR));
        String title_ru = request.getParameter(Attribute.ATTRIBUTE_TITLE_RU);
        String title_en = request.getParameter(Attribute.ATTRIBUTE_TITLE_EN);
        String country_ru = request.getParameter(Attribute.ATTRIBUTE_COUNTRY_RU);
        String country_en = request.getParameter(Attribute.ATTRIBUTE_COUNTRY_EN);
        int duration = Integer.parseInt(request.getParameter(Attribute.ATTRIBUTE_DURATION));
        String cast_ru = request.getParameter(Attribute.ATTRIBUTE_CAST_RU);
        String cast_en = request.getParameter(Attribute.ATTRIBUTE_CAST_EN);
        String awards_ru = request.getParameter(Attribute.ATTRIBUTE_AWARDS_RU);
        String awards_en = request.getParameter(Attribute.ATTRIBUTE_AWARDS_EN);
        String image = request.getParameter(Attribute.ATTRIBUTE_IMAGE);
        String tvSerialCheckBox = request.getParameter(Attribute.ATTRIBUTE_TVSERIAL);
        boolean tvSerial = "true".equals(tvSerialCheckBox);

        Movie movie = new Movie(name_ru, name_en, genre_ru, genre_en, year, title_ru, title_en, country_ru, country_en,
                duration, cast_ru, cast_en, awards_ru, awards_en, tvSerial, image);
        try {
            movieService.add(movie);
        } catch (ServiceException e) {
            log.error("Exception while adding movie " + e.getMessage());
            request.setAttribute(Attribute.ATTRIBUTE_ERROR_MESSAGE, Messages.UNKNOWN_ERROR);
            return PagePath.PAGE_INFO;
        }

        return "controller?command=goToHome";
    }
}
