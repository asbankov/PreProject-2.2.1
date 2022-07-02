package hiber.service;

import hiber.model.Car;
import hiber.model.User;

import java.util.List;

public interface UserService {
    void add(User user);
    List<User> listUsers();
    void addWithCar(User user, Car car);
    User userFromCar(String model, int series);
    void removeUserWithCar(User user);
}
