package ui;

import businessmodel.Model;
import businessmodel.user.User;

import java.util.Scanner;

public class ManagerView extends View {

    private Scanner scan = new Scanner(System.in);

    public ManagerView(Model model, User user) {
        super(model);
        setUser(user);
    }

    @Override
    public void display() {
        System.out.println("> To view the statistics enter STATS");
        System.out.println("> To view the available scheduling algorithms enter ALGO ");
        System.out.println("> To change the assmembly lines status enter LINE");
        System.out.print(">> ");
        String input = this.scan.nextLine();
        this.check(input);
        if (input.equalsIgnoreCase("stats"))
            this.checkStatistics();
        if (input.equalsIgnoreCase("algo"))
            this.changeAlgorithm();
        if (input.equalsIgnoreCase("line"))
            this.changeOperationalStatus();
        else
            this.error();
    }

    private void changeOperationalStatus() {
        new OperationalStatusView(this.getModel(), this.user).display();
    }

    private void changeAlgorithm() {
        new SchedulingView(this.getModel(), this.user).display();
    }

    private void checkStatistics() {
        new StatisticsView(this.getModel(), this.user).display();
    }

    @Override
    public void displayHelp() {
        super.helpOverview();
    }

    @Override
    public void error() {
        System.out.println("! Something went wrong. Please try again");
        this.display();
    }

    @Override
    public void cancel() {
        this.quit();
    }

    @Override
    public void quit() {
        new LoginView(this.getModel()).display();
    }
}
