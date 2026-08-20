package io.github.zigeuh.avaj.aircraft;

import io.github.zigeuh.avaj.exceptions.InvalidScenarioException;

public class AircraftFactory {

    private static int idCounter = 0;
    private static final AircraftFactory instance = new AircraftFactory();

    private AircraftFactory() {}

    public static AircraftFactory getInstance() {
        return instance;
    }

    public Flyable newAircraft(String p_type, String p_name, Coordinates p_coordinates) throws InvalidScenarioException {

        Flyable flyable;

        switch (p_type.toLowerCase()) {
            case "helicopter" -> flyable = new Helicopter(idCounter++, p_name, p_coordinates);
            case "balloon" -> flyable = new Balloon(idCounter++, p_name, p_coordinates);
            case "jetplane" -> flyable = new JetPlane(idCounter++, p_name, p_coordinates);
            default -> throw new InvalidScenarioException(p_type + " is not a valid flyable type.");
        }
        return flyable;
    }

    public Coordinates createCoordinates(int p_longitude, int p_latitude, int p_height) {
        return new Coordinates(p_longitude, p_latitude, p_height);
    }
}
