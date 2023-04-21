package ru.appline.logic;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;


public class Model implements Serializable {
    private static final Model instance = new Model();

    private final Map<Integer, User> model;

    public static Model getInstance() {
        return instance;
    }

    private Model() {
        model = new HashMap<>();

        model.put(1, new User("Roman", "Maksimov", 999999));
        model.put(2, new User("Anton", "Rasokhin", 888888));
        model.put(3, new User("Alisa", "Lubina", 777777));
    }

    public void add(User user, int id) {
        model.put(id, user);
    }

    public Map<Integer, User> getFromList() {
        return model;
    }

    public void remove(int id) {
        model.remove(id);
    }
    public boolean put(User user, int id){
        model.put(id, user);
        return false;
    }
}
