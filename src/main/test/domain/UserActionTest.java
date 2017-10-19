package test.domain;

import com.epam.kinorating.entity.UserAction;
import org.junit.Before;
import org.junit.Test;

import java.sql.Date;

import static org.junit.Assert.assertEquals;

public class UserActionTest {
    private UserAction object;

    @Before
    public void setUp() throws Exception {
        object = new UserAction();
    }

    @Test
    public void testGetID() {
        int id = 1;
        object.setID(id);

        assertEquals(id, object.getID());
    }

    @Test
    public void testGetMovieID() {
        int movieId = 1;
        object.setMovieID(movieId);

        assertEquals(movieId, object.getMovieID());
    }

    @Test
    public void testUserID() {
        int userId = 1;
        object.setUserID(userId);
        assertEquals(userId, object.getUserID());
    }

    @Test
    public void testGetReview() {
        String review = "testReview";
        object.setReview(review);

        assertEquals(review, object.getReview());
    }

    @Test
    public void testRating() {
        int rating = 1;
        object.setRating(rating);

        assertEquals(rating, object.getRating());
    }

    @Test
    public void testGetDateValuation() {
        Date dateValuation = new Date(2017);
        object.setDateValuation(dateValuation);

        assertEquals(dateValuation, object.getDateValuation());
    }

    @Test
    public void testGetDateReview() {
        Date dateReview = new Date(2017);
        object.setDateReview(dateReview);

        assertEquals(dateReview, object.getDateReview());
    }
}
