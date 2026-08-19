package io.github.zigeuh.avaj.weather;

import io.github.zigeuh.avaj.aircraft.Flyable;
import io.github.zigeuh.avaj.utils.Logger;

import java.util.ArrayList;
import java.util.List;

public class Tower {
    private final List<Flyable> observers = new ArrayList<>();

    public void register(Flyable p_flyable) {
        if (!observers.contains(p_flyable)) {
            observers.add(p_flyable);

            Logger.log("Tower says: " + p_flyable + " registered to weather tower.");
        }
    }

    public void unregister(Flyable p_flyable) {
        observers.remove(p_flyable);
        Logger.log("Tower says: " + p_flyable + " unregistered to weather tower.");
    }

    protected void conditionChanged() {
        for (Flyable observer: observers) {
            observer.updateConditions();
        }
    }
}
