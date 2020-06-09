package Education;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ObservableWithTypes {
    Map<String, List<Observer>> listeners = new HashMap<>();

    // Create the types that the observable will emit
    public ObservableWithTypes(String... operations) {
        for (String operation : operations) {
            this.listeners.put(operation, new ArrayList<>());
        }
    }

    // Add listener to HashMap with type
    public void subscribe(String eventType, Observer listener) {
        listeners.get(eventType).add(listener);
    }

    // remove listener from HashMap with type
    public void unsubscribe(String eventType, Observer listener) {
        listeners.get(eventType).remove(listener);
    }

    // Notify the listeners that are listening for event type
    public void notify(String eventType, Game game) {
        for (Observer listener : listeners.get(eventType)) {
            listener.update(eventType, game);
        }
    }
}

