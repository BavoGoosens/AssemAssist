package ui;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.util.regex.Pattern;

import businessmodel.Model;
import businessmodel.category.VehicleOption;
import businessmodel.exceptions.NoClearanceException;
import businessmodel.user.User;
import control.SchedulingController;
import control.SchedulingHandler;

public class SchedulingView extends View {

    private Scanner scan = new Scanner(System.in);

    private SchedulingController controller;

    public SchedulingView(Model cmc, User user) {
        super(cmc);
        setUser(user);
        this.controller = new SchedulingHandler(this.getModel());
    }

    @Override
    public void display() {
        try {
            Iterator<String> algoss = this.getModel().getSchedulingAlgorithms(user);
            ArrayList<String> algos = new ArrayList<String>();
            while (algoss.hasNext())
                algos.add(algoss.next());
            String currentalgo = this.getModel().getCurrentSystemWideAlgorithm(this.user);
            System.out.println("> The active algorithm: ");
            System.out.println("> " + currentalgo);
            System.out.println("> To choose an algorithm enter the corresponding number. "
                    + "\n> These are the available algorithms: ");
            int count = 1;
            for (String alg : algos)
                System.out.println("> " + count++ + ") " + alg);
            System.out.print(">> ");
            String response = this.scan.nextLine();
            Pattern pattern = Pattern.compile("^\\d+$");
            this.check(response);
            if (pattern.matcher(response).find()) {
                int choice = Integer.parseInt(response);
                if (choice < 1 || choice > algos.size())
                    this.error();
                String algo = algos.get(choice - 1);
                Iterator<ArrayList<VehicleOption>> optss = this.getModel().getUnscheduledVehicleOptions(this.user);
                ArrayList<ArrayList<VehicleOption>> opts = new ArrayList<ArrayList<VehicleOption>>();
                while (optss.hasNext())
                    opts.add(optss.next());
                if (algo.equals("SpecificationBatch")) {
                    if (opts.isEmpty()) {
                        System.out.println("! there are not enough orders available");
                        this.display();
                    }
                    System.out.println("> Enter the number of the car option you want to supply as a parameter");
                    int num = 1;
                    for (ArrayList<VehicleOption> opt : opts)
                        System.out.println("> " + num++ + ") " + opt.toString());
                    System.out.print(">> ");
                    response = this.scan.nextLine();
                    this.check(response);
                    if (pattern.matcher(response).find()) {
                        choice = Integer.parseInt(response);
                        if (choice < 1 || choice > opts.size())
                            this.error();
                        ArrayList<VehicleOption> param = opts.get(choice - 1);
                        try {
                            this.controller.selectAlgorithm(this.user, algo, param);
                            System.out.println("> Algorithm changed :)");
                            this.display();
                        } catch (Exception e) {
                            System.out.println("! " + e.getMessage());
                            this.display();
                        }
                    } else {
                        this.error();
                    }
                } else {
                    try {
                        this.controller.selectAlgorithm(this.user, algo, null);
                        System.out.println("> Algorithm changed :)");
                        this.display();
                    } catch (Exception e) {
                        System.out.println("! " + e.getMessage());
                        this.display();
                    }
                }
            } else {
                this.error();
            }
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
