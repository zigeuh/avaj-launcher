package io.github.zigeuh.avaj.weather;

import io.github.zigeuh.avaj.aircraft.Flyable;

import java.util.ArrayList;
import java.util.List;

public class Tower {
    private final List<Flyable> observers = new ArrayList<Flyable>();

    public void register(Flyable p_flyable) {
        if (!observers.contains(p_flyable)) {
            observers.add(p_flyable);
        }
    }

    public void unregister(Flyable p_flyable) {
        observers.remove(p_flyable);
    }

    protected void conditionChanged() {
        for (Flyable observer: observers) {
            observer.updateConditions();
        }
    }
}
