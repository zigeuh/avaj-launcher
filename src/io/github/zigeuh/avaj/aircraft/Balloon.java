package io.github.zigeuh.avaj.aircraft;

import io.github.zigeuh.avaj.utils.Logger;

public class Balloon extends Aircraft {
    public Balloon(long p_id, String p_name, Coordinates p_coordinate) {
        super(p_id, p_name, p_coordinate);
    }

    @Override
    public void updateConditions() {
        Coordinates currentCoords = this.coordinates;
        String currentWeather = weatherTower.getWeather(currentCoords);
        int longitude = currentCoords.getLongitude();
        int latitude = currentCoords.getLatitude();
        int height = currentCoords.getHeight();

        String message = ": ";

        switch (currentWeather) {
            case "SUN" -> {
                longitude += 2;
                height += 4;
                message += "Perfect sunshine for a slow-motion sightseeing tour!";
            }
            case "RAIN" -> {
                height -= 5;
                message += "Looks like the rain is giving our balloon an unexpected weight-loss workout, time to burn a bit more fuel to stay up!";
            }
            case "FOG" -> {
                height -= 3;
                message += "Down we go into the soup! Hold tight while I find us a nice little clearing to land.";
            }
            case "SNOW" -> {
                height -= 15;
                message += "Snow is accumulating on top and pushing us down, looks like Santa's helping us land earlier than planned!";
            }
        }

        this.coordinates = new Coordinates(longitude, latitude, Math.min(height, 100));
        Logger.log(this + message);

        if (this.coordinates.getHeight() <= 0) {
            Logger.log(this + " landing");
            weatherTower.unregister(this);
        }
    }
}
