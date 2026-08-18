package io.github.zigeuh.avaj.aircraft;

public class Jetplane extends Aircraft {
    public Jetplane(long p_id, String p_name, Coordinates p_coordinate) {
        super(p_id, p_name, p_coordinate);
    }

    @Override
    public void updateConditions() {
        Coordinates currentCoords = this.coordinates;
        String currentWeather = weatherTower.getWeather(currentCoords);
        int longitude = currentCoords.getLongitude();
        int latitude = currentCoords.getLatitude();
        int height = currentCoords.getHeight();

        String prefix = "Jetplane#" + this.name + "(" + this.id + ")";

        String message = prefix + ": ";

        switch (currentWeather) {
            case "SUN" -> {
                latitude += 10;
                height += 2;
                message += "Sun’s out, twin engines out!";
            }
            case "RAIN" -> {
                latitude += 5;
                message += "Free car wash at 500 knots, hanging tight for takeoff!";
            }
            case "FOG" -> {
                latitude += 1;
                message += "Playing hide and seek with the runway today, but my instruments know the way!";
            }
            case "SNOW" -> {
                height -= 7;
                message += "Brrr, the wings are turning into ice skates! Requesting a quick de-ice session so we don't slide off target.";
            }
        }

        this.coordinates = new Coordinates(longitude, latitude, Math.min(height, 100));
        System.out.println(message);

        if (this.coordinates.getHeight() <= 0) {
            System.out.println(prefix + " landing");
            System.out.println("Tower says: " + prefix + " unregistered from weather tower.");
            weatherTower.unregister(this);
        }
    }
}
