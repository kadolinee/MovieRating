package com.epam.kinorating.command.admin;

import com.epam.kinorating.command.Command;
import com.epam.kinorating.command.user.SearchMovieCommand;
import com.epam.kinorating.config.Attribute;
import com.epam.kinorating.config.Messages;
import com.epam.kinorating.config.PagePath;
import com.epam.kinorating.entity.Movie;
import com.epam.kinorating.exception.ServiceException;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class AddMovieCommand implements Command{
    private final Logger log = Logger.getLogger(SearchMovieCommand.class);

    @Override
    public String execute(HttpServletRequest request, UserService userService, MovieService movieService,
                          UserActionService userActionService) {
        String nameRu = request.getParameter(Attribute.ATTRIBUTE_NAME_RU);
        String nameEn = request.getParameter(Attribute.ATTRIBUTE_NAME_EN);
        String genreRu = request.getParameter(Attribute.ATTRIBUTE_GENRE_RU);
        String genreEn = request.getParameter(Attribute.ATTRIBUTE_GENRE_EN);
        int year = Integer.parseInt(request.getParameter(Attribute.ATTRIBUTE_YEAR));
        String titleRu = request.getParameter(Attribute.ATTRIBUTE_TITLE_RU);
        String titleEn = request.getParameter(Attribute.ATTRIBUTE_TITLE_EN);
        String countryRu = request.getParameter(Attribute.ATTRIBUTE_COUNTRY_RU);
        String countryEn = request.getParameter(Attribute.ATTRIBUTE_COUNTRY_EN);
        int duration = Integer.parseInt(request.getParameter(Attribute.ATTRIBUTE_DURATION));
        String castRu = request.getParameter(Attribute.ATTRIBUTE_CAST_RU);
        String castEn = request.getParameter(Attribute.ATTRIBUTE_CAST_EN);
        String awardsRu = request.getParameter(Attribute.ATTRIBUTE_AWARDS_RU);
        String awardsEn = request.getParameter(Attribute.ATTRIBUTE_AWARDS_EN);
        String image = request.getParameter(Attribute.ATTRIBUTE_IMAGE);
        String tvSerialCheckBox = request.getParameter(Attribute.ATTRIBUTE_TVSERIAL);
        boolean tvSerial = "true".equals(tvSerialCheckBox);

        Movie movie = new Movie(nameRu, nameEn, genreRu, genreEn, year, titleRu, titleEn, countryRu, countryEn,
                duration, castRu, castEn, awardsRu, awardsEn, tvSerial, image);
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
