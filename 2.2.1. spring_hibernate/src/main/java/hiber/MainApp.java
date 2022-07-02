package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      User first = new User("User1", "Lastname1", "user1@mail.ru");
      userService.addWithCar(first, new Car("Toyota", 13));
      User second = new User("User2", "Lastname2", "user2@mail.ru");
      userService.addWithCar(second, new Car("BMW", 12));

      userService.removeUserWithCar(first);

      System.out.println(userService.userFromCar("BMW", 12));

      context.close();
   }
}
