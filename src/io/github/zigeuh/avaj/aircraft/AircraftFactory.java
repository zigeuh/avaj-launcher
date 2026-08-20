package io.github.zigeuh.avaj.aircraft;

public class AircraftFactory {

    private static int idCounter = 0;
    private static final AircraftFactory instance = new AircraftFactory();

    private AircraftFactory() {}

    public static AircraftFactory getInstance() {
        return instance;
    }

    public Flyable newAircraft(String p_type, String p_name, Coordinates p_coordinates) {

        Flyable flyable;

        switch (p_type.toLowerCase()) {
            case "helicopter" -> flyable = new Helicopter(idCounter++, p_name, p_coordinates);
            case "balloon" -> flyable = new Balloon(idCounter++, p_name, p_coordinates);
            case "jetplane" -> flyable = new Jetplane(idCounter++, p_name, p_coordinates);
            default -> flyable = null;
        }
        return flyable;
    }
}
