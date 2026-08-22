package io.github.zigeuh.avaj;

import io.github.zigeuh.avaj.aircraft.AircraftFactory;
import io.github.zigeuh.avaj.aircraft.Coordinates;
import io.github.zigeuh.avaj.aircraft.Flyable;
import io.github.zigeuh.avaj.exceptions.InvalidNumberInputException;
import io.github.zigeuh.avaj.exceptions.InvalidScenarioException;
import io.github.zigeuh.avaj.utils.Logger;
import io.github.zigeuh.avaj.weather.WeatherTower;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Simulator {

    private static final List<Flyable> flyables = new ArrayList<>();

    public static void main(String[] args) {

        if (args.length != 1) {
            System.out.println("Usage : java -cp src/ io.github.zigeuh.avaj.Simulator <scenario.txt>");
            return;
        }

        try {
            List<String> lines = Files.readAllLines(Path.of(args[0]));
            if (lines.isEmpty()) {
                throw new InvalidScenarioException(args[0] + " is empty.");
            }

            int simulations;
            try {
                simulations = Integer.parseInt(lines.getFirst().trim());
            } catch (NumberFormatException e) {
                throw new InvalidNumberInputException("First line \"" + lines.getFirst() + "\" is not a valid integer.");
            }

            if (simulations <= 0) {
                throw new InvalidNumberInputException("Simulation count must be a positive integer (found: " + simulations + ").");
            }

            lines = lines.subList(1, lines.size());
            if (lines.isEmpty()) {
                throw new InvalidScenarioException(args[0] + " doesn't contain any aircraft.");
            }

            AircraftFactory aircraftFactory = AircraftFactory.getInstance();

            for (String line: lines) {

                line = line.trim();
                if (line.isEmpty())
                    continue;

                String[] parts = line.split("\\s+");
                if (parts.length != 5) {
                    throw new InvalidScenarioException("Invalid scenario line format: " + line);
                }

                int longitude, latitude, height;
                try {
                    longitude = Integer.parseInt(parts[2]);
                    latitude = Integer.parseInt(parts[3]);
                    height = Integer.parseInt(parts[4]);
                } catch (NumberFormatException e) {
                    throw new InvalidNumberInputException("Coordinates must be valid integers in line: " + line);
                }

                if (longitude < 0 || latitude < 0 || height < 0) {
                    throw new InvalidNumberInputException("Coordinates cannot be negative in line: " + line);
                }
                if (height > 100) {
                    throw new InvalidNumberInputException("Initial height cannot exceed 100 in line: " + line);
                }

                Coordinates coordinates = aircraftFactory.createCoordinates(longitude, latitude, height);
                Flyable flyable = aircraftFactory.newAircraft(parts[0], parts[1], coordinates);
                flyables.add(flyable);
            }

            WeatherTower weatherTower = new WeatherTower();

            Logger.init("simulation.txt");

            for (Flyable flyable: flyables) {
                flyable.registerTower(weatherTower);
            }

            for (int i = 0; i < simulations; i++) {
                weatherTower.changeWeather();
            }

        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Number Format Exception: " + e.getMessage());
        } catch (InvalidNumberInputException | InvalidScenarioException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("Unexpected error: " + e.getMessage());
        } finally {
            Logger.close();
        }
    }
}
