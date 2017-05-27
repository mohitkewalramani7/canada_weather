package Model;

/**
 * A class to represent the WeatherDataType object that is built and referenced
 * when parsing data from the BBC Website as well as when displaying data on the
 * newly constructed Window
 *
 * @author Mohit Kewalramani
 * @version 2.0
 * @since 2017-05-17
 */
public class WeatherDataType {

    private String title;
    private String wind_direction;
    private String wind_speed;
    private String visibility;
    private String pressure;
    private String humidity;
    private String sunrise;
    private String sunset;

    public WeatherDataType(String title, String wind_direction, String wind_speed, String visibility, String pressure,
                           String humidity, String sunrise, String sunset){
        this.title = title;
        this.wind_direction = wind_direction;
        this.wind_speed = wind_speed;
        this.visibility = visibility;
        this.pressure = pressure;
        this.humidity = humidity;
        this.sunrise = sunrise;
        this.sunset = sunset;
    }

    public String getTitle() {
        return title;
    }

    public String getWind_direction() {
        return wind_direction;
    }

    public String getWind_speed() {
        return wind_speed;
    }

    public String getVisibility() {
        return visibility;
    }

    public String getHumidity() {
        return humidity;
    }

    public String getPressure() {
        return pressure;
    }

    public String getSunrise() {
        return sunrise;
    }

    public String getSunset() {
        return sunset;
    }

}
