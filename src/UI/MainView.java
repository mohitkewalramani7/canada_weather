package UI;

import Model.WeatherDataParserHelper;

import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * This is a class that initializes the main view of the Java Swing UI once the
 * programme is launched
 *
 * @author Mohit Kewalramani
 * @version 2.0
 * @since 2017-05-17
 */
public class MainView extends JPanel{

    private JPanel mainPanel;               // The main panel shown to the user
    private JList providedCityList;         // The city list that is brought up on the left hand side of the main panel
    private JPanel mainPanelBackground;     // The map of Canada brought up on the right of the main panel

    // Instance of WeatherDataParserHelper in order to be ready to parse data for multiple listed cities
    private WeatherDataParserHelper weatherDataParserHelper;

    /**
     * Initializes the single instance of the main UI of the programme
     * @param args Command line arguments if script is run from command line
     */
    public static void main(String[] args){
        JFrame frame = new JFrame("Canada Weather");
        frame.setContentPane(new MainView().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 700);
        frame.setVisible(true);
    }

    /**
     * Constructs the main view. The right hand side of the view that contains
     * the Canada map for the user to view
     */
    public MainView(){
        mainPanelBackground.setLayout(new BorderLayout());
        JLabel background = new JLabel(new ImageIcon("src\\UI\\canada-map-vector.jpg"));
        background.setSize(200, 200);
        mainPanelBackground.add(background);

        weatherDataParserHelper = new WeatherDataParserHelper();
        constructWeatherWindow();
    }

    /**
     * Constructs the left hand side of the view that brings up a list of cities
     * in Canada. The user can click any city nad the 3 day forecast will show up
     * in a new window
     */
    private void constructWeatherWindow(){
        providedCityList.addMouseListener(new MouseInputAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String userInput = providedCityList.getSelectedValue().toString();
                weatherDataParserHelper = new WeatherDataParserHelper();
                parseCityData(userInput);
                showWindow(userInput);
            }
        });
    }

    /**
     *  A helper method that parses the weather forecast for the next 3 days of the city's weather forecast
     *  from the BBC website
     *
     * @param city_name The city name clicked.
     */
    private void parseCityData(String city_name){
        switch (city_name){
            case "Toronto, Ontario":
                weatherDataParserHelper.parseWeatherData("http://open.live.bbc.co.uk/weather/feeds/en/6167865/3dayforecast.rss");
                break;
            case "Montréal, Quebec":
                weatherDataParserHelper.parseWeatherData("http://open.live.bbc.co.uk/weather/feeds/en/6077243/3dayforecast.rss");
                break;
            case "Calgary, Alberta":
                weatherDataParserHelper.parseWeatherData("http://open.live.bbc.co.uk/weather/feeds/en/5913490/3dayforecast.rss");
                break;
            case "Ottawa, Ontario":
                weatherDataParserHelper.parseWeatherData("http://open.live.bbc.co.uk/weather/feeds/en/6094817/3dayforecast.rss");
                break;
            case "Edmonton, Alberta":
                weatherDataParserHelper.parseWeatherData("http://open.live.bbc.co.uk/weather/feeds/en/5946768/3dayforecast.rss");
                break;
            case "Mississauga, Ontario":
                weatherDataParserHelper.parseWeatherData("http://open.live.bbc.co.uk/weather/feeds/en/6075357/3dayforecast.rss");
                break;
            case "Winnipeg, Manitoba":
                weatherDataParserHelper.parseWeatherData("http://open.live.bbc.co.uk/weather/feeds/en/6183235/3dayforecast.rss");
                break;
            case "Vancouver, British Columbia":
                weatherDataParserHelper.parseWeatherData("http://open.live.bbc.co.uk/weather/feeds/en/6173331/3dayforecast.rss");
                break;
            case "Hamilton, Ontario":
                weatherDataParserHelper.parseWeatherData("http://open.live.bbc.co.uk/weather/feeds/en/5969782/3dayforecast.rss");
                break;
            case "Quebec City, Quebec":
                weatherDataParserHelper.parseWeatherData("http://open.live.bbc.co.uk/weather/feeds/en/6325494/3dayforecast.rss");
                break;
            case "Halifax, Nova Scotia":
                weatherDataParserHelper.parseWeatherData("http://open.live.bbc.co.uk/weather/feeds/en/6324729/3dayforecast.rss");
                break;
            case "London, Ontario":
                weatherDataParserHelper.parseWeatherData("http://open.live.bbc.co.uk/weather/feeds/en/6058560/3dayforecast.rss");
                break;
            case "Saskatoon, Saskatchewan":
                weatherDataParserHelper.parseWeatherData("http://open.live.bbc.co.uk/weather/feeds/en/6141256/3dayforecast.rss");
                break;
            case "Regina, Saskatchewan":
                weatherDataParserHelper.parseWeatherData("http://open.live.bbc.co.uk/weather/feeds/en/6301474/3dayforecast.rss");
                break;
            case "St.John's, Newfoundland and Labrador":
                weatherDataParserHelper.parseWeatherData("http://open.live.bbc.co.uk/weather/feeds/en/6324733/3dayforecast.rss");
                break;
            case "Windsor, Ontario":
                weatherDataParserHelper.parseWeatherData("http://open.live.bbc.co.uk/weather/feeds/en/6182962/3dayforecast.rss");
                break;
            case "Charlottetown, Prince Edward Island":
                weatherDataParserHelper.parseWeatherData("http://open.live.bbc.co.uk/weather/feeds/en/5920288/3dayforecast.rss");
                break;
            case "Whitehorse, Yukon":
                weatherDataParserHelper.parseWeatherData("http://open.live.bbc.co.uk/weather/feeds/en/6180550/3dayforecast.rss");
                break;
            case "Victoria, British Columbia":
                weatherDataParserHelper.parseWeatherData("http://open.live.bbc.co.uk/weather/feeds/en/6174041/3dayforecast.rss");
                break;
            case "Yellowknife, Northwest Territories":
                weatherDataParserHelper.parseWeatherData("http://open.live.bbc.co.uk/weather/feeds/en/6185377/3dayforecast.rss");
                break;
        }
    }

    /**
     * Launches the new window that would show up if a user clicked on a city. This new window contains
     * the data for the weather forecast for the next 3 days of the city
     *
     * @param cityName The name of the city clicked
     */
    private void showWindow(String cityName){
        WeatherDataWindow newWindow = new WeatherDataWindow();
        JFrame weatherDetailsFrame = new JFrame("3 Day Forecast");
        weatherDetailsFrame.setContentPane(newWindow.forecastDetailPanel);
        weatherDetailsFrame.setSize(1200, 630);
        weatherDetailsFrame.setTitle(cityName);
        newWindow.forecastDetailTitle.setText("3 Day Forecast for " + cityName);
        setAllDaysData(newWindow);
        weatherDetailsFrame.setVisible(true);
    }

    /**
     * Writes the data to the new window launched to match the data parsed from the BBC website
     * for the weather forecast
     *
     * @param newWindow The new window Launched
     */
    private void setAllDaysData(WeatherDataWindow newWindow){
        newWindow.dayOneTitle.setText(weatherDataParserHelper.weatherDetails.get(0).getTitle());
        newWindow.dayOneWindSpeed.setText(weatherDataParserHelper.weatherDetails.get(0).getWind_speed());
        newWindow.dayOneVisibility.setText(weatherDataParserHelper.weatherDetails.get(0).getVisibility());
        newWindow.dayOneWindDirection.setText(weatherDataParserHelper.weatherDetails.get(0).getWind_direction());
        newWindow.dayOnePressure.setText(weatherDataParserHelper.weatherDetails.get(0).getPressure());
        newWindow.dayOneHumidity.setText(weatherDataParserHelper.weatherDetails.get(0).getHumidity());
        newWindow.dayOneSunrise.setText(weatherDataParserHelper.weatherDetails.get(0).getSunrise());
        newWindow.dayOneSunset.setText(weatherDataParserHelper.weatherDetails.get(0).getSunset());

        newWindow.dayTwoTitle.setText(weatherDataParserHelper.weatherDetails.get(1).getTitle());
        newWindow.dayTwoWindSpeed.setText(weatherDataParserHelper.weatherDetails.get(1).getWind_speed());
        newWindow.dayTwoVisibility.setText(weatherDataParserHelper.weatherDetails.get(1).getVisibility());
        newWindow.dayTwoWindDirection.setText(weatherDataParserHelper.weatherDetails.get(1).getWind_direction());
        newWindow.dayTwoPressure.setText(weatherDataParserHelper.weatherDetails.get(1).getPressure());
        newWindow.dayTwoHumidity.setText(weatherDataParserHelper.weatherDetails.get(1).getHumidity());
        newWindow.dayTwoSunrise.setText(weatherDataParserHelper.weatherDetails.get(1).getSunrise());
        newWindow.dayTwoSunset.setText(weatherDataParserHelper.weatherDetails.get(1).getSunset());

        newWindow.dayThreeTitle.setText(weatherDataParserHelper.weatherDetails.get(2).getTitle());
        newWindow.dayThreeWindSpeed.setText(weatherDataParserHelper.weatherDetails.get(2).getWind_speed());
        newWindow.dayThreeVisibility.setText(weatherDataParserHelper.weatherDetails.get(2).getVisibility());
        newWindow.dayThreeWindDirection.setText(weatherDataParserHelper.weatherDetails.get(2).getWind_direction());
        newWindow.dayThreePressure.setText(weatherDataParserHelper.weatherDetails.get(2).getPressure());
        newWindow.dayThreeHumidity.setText(weatherDataParserHelper.weatherDetails.get(2).getHumidity());
        newWindow.dayThreeSunrise.setText(weatherDataParserHelper.weatherDetails.get(2).getSunrise());
        newWindow.dayThreeSunset.setText(weatherDataParserHelper.weatherDetails.get(2).getSunset());
    }

}
