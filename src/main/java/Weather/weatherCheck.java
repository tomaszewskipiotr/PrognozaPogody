package Weather;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import net.aksingh.owmjapis.core.OWM;
import net.aksingh.owmjapis.api.APIException;
import net.aksingh.owmjapis.model.CurrentWeather;


@SuppressWarnings("unused")
@WebServlet("/weatherCheck")
public class weatherCheck extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public weatherCheck() 
    {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        response.setStatus(418);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String city = request.getParameter("city");
        OWM owm = new OWM("1df531e4b3a7266983def8fafee64a27");

            CurrentWeather currentWeather = null;
        try {
                if (city.equals("warszawa"))
                {
                    currentWeather = owm.currentWeatherByCityName("Warsaw, PL");
                }
                else if (city.equals("gdansk")) 
                {
                    currentWeather = owm.currentWeatherByCityName("Gdansk, PL");
                }
                else if (city.equals("krakow"))
                {
                    currentWeather = owm.currentWeatherByCityName("Krakow, PL");
                }
                else if (city.equals("wroclaw")) 
                {
                    currentWeather = owm.currentWeatherByCityName("Wroclaw, PL");
                }
                else if (city.equals("poznan")) 
                {
                    currentWeather = owm.currentWeatherByCityName("Poznan, PL");
                }
                else if (city.equals("lodz")) 
                {
                    currentWeather = owm.currentWeatherByCityName("Lodz, PL");
                }
                else if (city.equals("katowice")) 
                {
                    currentWeather = owm.currentWeatherByCityName("Katowice, PL");
                }
            } catch (APIException e) 
            {
                e.printStackTrace();
            }
            if (currentWeather.hasRespCode() && currentWeather.getRespCode() == 200) {
                if (currentWeather.hasCityName()) {
                    out.append("<br><br>Miasto: " + currentWeather.getCityName());
                }
                if (currentWeather.hasMainData() && currentWeather.getMainData().hasTempMax() && currentWeather.getMainData().hasTempMin()) {
                    out.append("<br>Temperatura: " + (currentWeather.getMainData().getTempMax() - 273.15)
                            + "/" + (currentWeather.getMainData().getTempMin() - 273.15) + "\'C");
                }
                if (currentWeather.hasMainData() && currentWeather.getMainData().hasPressure()) {
                    out.append("<br>Cisnienie: " + currentWeather.getMainData().getPressure() + " hPa");
                }
                if (currentWeather.hasMainData() && currentWeather.hasCloudData()) {
                    out.append("<br>Zachmurzenie: " + currentWeather.getCloudData().getCloud() + " %");
                }
                if (currentWeather.hasMainData() && currentWeather.hasWindData()) {
                    out.append("<br>Wiatr: " + currentWeather.getWindData().getSpeed() + " m/s");
                }
                out.append("<br><br><br><br><br><br><br><br><br>");
                out.append("<a href='index.jsp'>Wroc do strony glownej</a>");
            }
        out.append("</center>");
    }
}
