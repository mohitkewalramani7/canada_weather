package Model;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Class that allows programme to connect to the BBC Website in order to parse data
 * for the selected city for the next three days
 *
 * @author Mohit Kewalramani
 * @version 2.0
 * @since 2017-05-17
 */
public class WeatherDataParserHelper {

    // A list of WeatherDataType objects (should be 3, for a 3 day forecast) that will be displayed on the UI
    public List<WeatherDataType> weatherDetails = new ArrayList<>();

    /**
     * Method to go through the RSS feed for the weather forecast over the next three days
     * and store each forecast as a WeatherDataType object for display on the UI.
     *
     * @param url The url on the BBC Website that corresponds to the RSS feed for the 3 day forecast.
     */
    public void parseWeatherData(String url){
        try{
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(createInputStream(url));
            document.getDocumentElement().normalize();
            NodeList nodeList = document.getElementsByTagName("item");
            for (int i = 0; i < nodeList.getLength(); i++){
                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE){
                    Element element = (Element) node;
                    String title = element.getElementsByTagName("title").item(0).getTextContent();
                    String description_string = element.getElementsByTagName("description").item(0).getTextContent();
                    String[] details = description_string.split(",");
                    weatherDetails.add(new WeatherDataType(
                            title,
                            getForecastDetail(details, "wind direction"),
                            getForecastDetail(details, "wind speed"),
                            getForecastDetail(details, "visibility"),
                            getForecastDetail(details, "pressure"),
                            getForecastDetail(details, "humidity"),
                            getForecastDetail(details, "sunrise"),
                            getForecastDetail(details, "sunset")
                    ));
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * This method builds the input stream to the given URL to allow parsing the RSS feed.
     *
     * @param url The URL to open a connection to.
     * @return inputStream The functionality by which the script can connect and go through the
     *      RSS feed.
     */
    private InputStream createInputStream(String url){
        try{
            URL builtUrl = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) builtUrl.openConnection();
            connection.setReadTimeout(10000);
            connection.setConnectTimeout(15000);
            connection.setRequestMethod("GET");
            connection.setDoInput(true);
            connection.connect();
            InputStream inputStream = connection.getInputStream();
            return inputStream;
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * A method that iterates through the details parsed through the RSS feed and picks out the
     * weather forecast detail being searched. Examples include "wind speed" or "sunset"
     *
     * @param forecastDetails The array consisting all the weather forecast details for a single day
     * @param detailToLookup The weather forecast detail that we wish to lookup in the array
     * @return The detail parsed from the RSS feed or simply "No Detail" if it wasn't found in the feed
     */
    public String getForecastDetail(String[] forecastDetails, String detailToLookup){
        for (String detail : forecastDetails){
            if (detail != null && detail.toLowerCase().contains(detailToLookup)){
                return detail;
            }
        }
        return String.format("%s : No Detail", detailToLookup);
    }

}
