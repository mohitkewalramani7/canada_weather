package UI;

import javax.swing.*;

/**
 * A constructor class for the new Window launched when the user selects a city
 * to view weather forecast data for
 *
 * @author Mohit Kewalramani
 * @version 2.0
 * @since 2017-05-17
 */
public class WeatherDataWindow {

    // The panel that is launched
    public JPanel forecastDetailPanel;

    // The overlaying title that is set to show the 3 day forecast
    public JLabel forecastDetailTitle;

    // The weather forecast fields that show details for the first day
    public JLabel dayOneTitle;
    public JLabel dayOneWindDirection;
    public JLabel dayOneWindSpeed;
    public JLabel dayOneVisibility;
    public JLabel dayOnePressure;
    public JLabel dayOneHumidity;
    public JLabel dayOneSunrise;
    public JLabel dayOneSunset;

    // The weather forecast fields that show details for the second day
    public JLabel dayTwoTitle;
    public JLabel dayTwoWindDirection;
    public JLabel dayTwoWindSpeed;
    public JLabel dayTwoVisibility;
    public JLabel dayTwoPressure;
    public JLabel dayTwoHumidity;
    public JLabel dayTwoSunrise;
    public JLabel dayTwoSunset;

    // The weather forecast fields that show details for the third day
    public JLabel dayThreeTitle;
    public JLabel dayThreeWindDirection;
    public JLabel dayThreeWindSpeed;
    public JLabel dayThreeVisibility;
    public JLabel dayThreePressure;
    public JLabel dayThreeHumidity;
    public JLabel dayThreeSunrise;
    public JLabel dayThreeSunset;
}
