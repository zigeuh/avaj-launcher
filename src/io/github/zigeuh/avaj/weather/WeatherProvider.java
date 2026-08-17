package io.github.zigeuh.avaj.weather;

import io.github.zigeuh.avaj.aircraft.Coordinates;

public class WeatherProvider {
    private static final WeatherProvider instance = new WeatherProvider();
    private final String[] weather = { "RAIN", "FOG", "SUN", "SNOW" };

    private WeatherProvider() {}

    public String getCurrentWeather(Coordinates p_coordinates) {

        int seed = p_coordinates.getHeight() + p_coordinates.getLatitude() + p_coordinates.getLongitude();

        return weather[seed % 4];
    }

    public static WeatherProvider getInstance() {
        return instance;
    }
}
