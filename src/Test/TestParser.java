package Test;

import Model.WeatherDataParserHelper;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static junit.framework.TestCase.assertEquals;

/**
 * Test Class to test the parsing of data as well as test that data is being extracted correctly from
 * the constructed Array for a single day's forecast
 *
 * @author Mohit Kewalramani
 * @version 2.0
 * @since 2015-05-17
 */
public class TestParser {

    // Instance of the WeatherDataParserHelper Class
    private WeatherDataParserHelper helper;

    @Before
    public void initializeHelper(){
        helper = new WeatherDataParserHelper();
    }

    @Test
    public void testParseDataSize(){
        // Confirms that the url is providing us a 3 day forecast from the BBC Website
        assertEquals(0, helper.weatherDetails.size());
        helper.parseWeatherData("http://open.live.bbc.co.uk/weather/feeds/en/6173331/3dayforecast.rss");
        assertEquals(3, helper.weatherDetails.size());
    }

    @Test (expected = NullPointerException.class)
    public void testNullArrayWeatherDetails(){
        helper.getForecastDetail(null, "wind direction");
    }

    @Test
    public void testNullStringWeatherDetails(){
        String[] nullStringArray = new String[10];
        assertEquals("wind direction : No Detail", helper.getForecastDetail(nullStringArray, "wind direction"));
        assertEquals("wind speed : No Detail", helper.getForecastDetail(nullStringArray, "wind speed"));
        assertEquals("visibility : No Detail", helper.getForecastDetail(nullStringArray, "visibility"));
        assertEquals("pressure : No Detail", helper.getForecastDetail(nullStringArray, "pressure"));
        assertEquals("humidity : No Detail", helper.getForecastDetail(nullStringArray, "humidity"));
        assertEquals("sunrise : No Detail", helper.getForecastDetail(nullStringArray, "sunrise"));
        assertEquals("sunset : No Detail", helper.getForecastDetail(nullStringArray, "sunset"));
    }

    @Test
    public void testEmptyStringWeatherDetails(){
        String[] emptyStringArray = new String[10];
        Arrays.fill(emptyStringArray, "");
        assertEquals("wind direction : No Detail", helper.getForecastDetail(emptyStringArray, "wind direction"));
        assertEquals("wind speed : No Detail", helper.getForecastDetail(emptyStringArray, "wind speed"));
        assertEquals("visibility : No Detail", helper.getForecastDetail(emptyStringArray, "visibility"));
        assertEquals("pressure : No Detail", helper.getForecastDetail(emptyStringArray, "pressure"));
        assertEquals("humidity : No Detail", helper.getForecastDetail(emptyStringArray, "humidity"));
        assertEquals("sunrise : No Detail", helper.getForecastDetail(emptyStringArray, "sunrise"));
        assertEquals("sunset : No Detail", helper.getForecastDetail(emptyStringArray, "sunset"));
    }

    @Test
    public void testNoValidDetailsInStringArray(){
        String[] noValidDetailsArray = {"a", "b", "c", "d", "e", "f", "g"};
        assertEquals("wind direction : No Detail", helper.getForecastDetail(noValidDetailsArray, "wind direction"));
        assertEquals("wind speed : No Detail", helper.getForecastDetail(noValidDetailsArray, "wind speed"));
        assertEquals("visibility : No Detail", helper.getForecastDetail(noValidDetailsArray, "visibility"));
        assertEquals("pressure : No Detail", helper.getForecastDetail(noValidDetailsArray, "pressure"));
        assertEquals("humidity : No Detail", helper.getForecastDetail(noValidDetailsArray, "humidity"));
        assertEquals("sunrise : No Detail", helper.getForecastDetail(noValidDetailsArray, "sunrise"));
        assertEquals("sunset : No Detail", helper.getForecastDetail(noValidDetailsArray, "sunset"));
    }

    @Test
    public void testSomeValidDetailsInStringArray(){
        String[] someValidDetailsArray = new String[7];
        someValidDetailsArray[0] = "Wind Direction: South Westerly";        // Valid Detail
        someValidDetailsArray[1] = "";                                      // Invalid Detail
        someValidDetailsArray[2] = "Visibility: Very Good";                 // Valid Detail
        someValidDetailsArray[3] = "Pressure: 1009mb";                      // Valid Detail
        someValidDetailsArray[4] = "AAAAAA";                                // Invalid Detail
        someValidDetailsArray[5] = "000000";                                // Invalid Detail
        someValidDetailsArray[6] = "Sunset: 20:38 EDT";                     // Valid Detail

        assertEquals("Wind Direction: South Westerly", helper.getForecastDetail(someValidDetailsArray, "wind direction"));
        assertEquals("wind speed : No Detail", helper.getForecastDetail(someValidDetailsArray, "wind speed"));
        assertEquals("Visibility: Very Good", helper.getForecastDetail(someValidDetailsArray, "visibility"));
        assertEquals("Pressure: 1009mb", helper.getForecastDetail(someValidDetailsArray, "pressure"));
        assertEquals("humidity : No Detail", helper.getForecastDetail(someValidDetailsArray, "humidity"));
        assertEquals("sunrise : No Detail", helper.getForecastDetail(someValidDetailsArray, "sunrise"));
        assertEquals("Sunset: 20:38 EDT", helper.getForecastDetail(someValidDetailsArray, "sunset"));
    }

    @Test
    public void testCorrectStringWeatherDetails(){
        String[] allCorrectDetailsArray = new String[7];
        allCorrectDetailsArray[0] = "Wind Direction: Northerly";
        allCorrectDetailsArray[1] = "Wind Speed: 11mph";
        allCorrectDetailsArray[2] = "Visibility: Very Good";
        allCorrectDetailsArray[3] = "Pressure: 1020mb";
        allCorrectDetailsArray[4] = "Humidity: 64%";
        allCorrectDetailsArray[5] = "Sunrise: 05:49 EDT";
        allCorrectDetailsArray[6] = "Sunset: 20:40 EDT";

        assertEquals("Wind Direction: Northerly", helper.getForecastDetail(allCorrectDetailsArray, "wind direction"));
        assertEquals("Wind Speed: 11mph", helper.getForecastDetail(allCorrectDetailsArray, "wind speed"));
        assertEquals("Visibility: Very Good", helper.getForecastDetail(allCorrectDetailsArray, "visibility"));
        assertEquals("Pressure: 1020mb", helper.getForecastDetail(allCorrectDetailsArray, "pressure"));
        assertEquals("Humidity: 64%", helper.getForecastDetail(allCorrectDetailsArray, "humidity"));
        assertEquals("Sunrise: 05:49 EDT", helper.getForecastDetail(allCorrectDetailsArray, "sunrise"));
        assertEquals("Sunset: 20:40 EDT", helper.getForecastDetail(allCorrectDetailsArray, "sunset"));
    }

}
