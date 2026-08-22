package io.github.zigeuh.avaj.aircraft;

import io.github.zigeuh.avaj.utils.Logger;

public class Helicopter extends Aircraft {
    public Helicopter(long p_id, String p_name, Coordinates p_coordinate) {
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
                longitude += 10;
                height += 2;
                message += "The weather is so nice, even the rotors are enjoying the breeze!";
            }
            case "RAIN" -> {
                longitude += 5;
                message += "Don't worry, the blades work like giant windshield wipers anyway!";
            }
            case "FOG" -> {
                longitude += 1;
                message += "Well, at least we don't have to worry about the view distracting us today!";
            }
            case "SNOW" -> {
                height -= 12;
                message += "Whoa, it’s a full whiteout out there! I’m easing down before we end up as a frozen ice pop.";
            }
        }

        this.coordinates = new Coordinates(longitude, latitude, Math.min(height, 100));
        Logger.log(this + message);

        if (this.coordinates.getHeight() <= 0) {
            Logger.log(this + " landing.");
            weatherTower.unregister(this);
        }
    }
}
