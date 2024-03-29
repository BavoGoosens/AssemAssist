package ui;

import businessmodel.Model;
import businessmodel.exceptions.IllegalNumberException;
import businessmodel.exceptions.NoClearanceException;
import businessmodel.order.Order;
import businessmodel.statistics.OrderStatistics;
import businessmodel.statistics.VehicleStatistics;
import businessmodel.user.User;
import businessmodel.util.Tuple;
import org.joda.time.LocalDate;

import java.util.ArrayList;
import java.util.Scanner;

public class StatisticsView extends View {

    private Scanner scan = new Scanner(System.in);

    public StatisticsView(Model cmc, User user) {
        super(cmc);
        setUser(user);
    }

    @Override
    public void display() {
        try {
            VehicleStatistics vehiclestats = this.getModel().getVehicleStatistics(this.user);
            OrderStatistics orderstats = this.getModel().getOrderStatistics(this.user);
            System.out.println("> Here are the order statistics:");
            System.out.println("> Average delay on an Order: " + orderstats.getAverage() + " min");
            System.out.println("> Median delay on an Order: " + orderstats.getAverage() + " min");
            try {
                ArrayList<Tuple<Order, Integer>> ords = orderstats.getLast(2);
                System.out.println("> The last two production days:");
                for (Tuple<Order, Integer> day : ords)
                    System.out.println("  > Order: " + day.getX() + "\n"
                            + "  > delay: " + day.getY() + "min");
            } catch (IllegalNumberException e) {
                System.out.println("! The system has not been operational long enough to provide these statistics");
            }
            System.out.println("> Here are the car statistics:");
            System.out.println("> Average number of vehicles finished per day: " + vehiclestats.getAverage());
            System.out.println("> Median number of vehicles finished per day: " + vehiclestats.getAverage());
            try {
                ArrayList<Tuple<LocalDate, Integer>> lastdays = vehiclestats.getLastDays(2);
                System.out.println("> The last two production days:");
                for (Tuple<LocalDate, Integer> day : lastdays)
                    System.out.println("  > Date: " + day.getX() + "\n"
                            + "  > number of vehicles : " + day.getY());
            } catch (IllegalArgumentException e) {
                System.out.println("! The system has not been operational long enough to provide these statistics");
            }
            System.out.print(">> ");
            String response = this.scan.nextLine();
            this.check(response);
            this.error();
        } catch (NoClearanceException e) {
            this.quit();
        }
    }

    @Override
    public void displayHelp() {
        this.helpOverview();
    }

    @Override
    public void error() {
        System.out.println("! You entered something wrong. Please try again");
        this.display();
    }

    @Override
    public void cancel() {
        new ManagerView(this.getModel(), this.user).display();
    }

    @Override
    public void quit() {
        new LoginView(this.getModel()).display();
    }

}
