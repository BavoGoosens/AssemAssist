package ui;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import businessmodel.Model;
import businessmodel.category.VehicleModel;
import businessmodel.category.VehicleOption;
import businessmodel.exceptions.NoClearanceException;
import businessmodel.exceptions.UnsatisfiedRestrictionException;
import businessmodel.order.Order;
import businessmodel.order.StandardVehicleOrder;
import businessmodel.user.User;
import businessmodel.util.OrderDateTimeComparator;
import control.StandardOrderController;
import control.StandardOrderHandler;

public class GarageHolderView extends View {

    private ArrayList<Order> pending_orders = new ArrayList<Order>();

    private ArrayList<Order> completed_orders = new ArrayList<Order>();

    private ArrayList<VehicleModel> available_vehiclemodels = new ArrayList<VehicleModel>();

    private StandardOrderController controller;

    private Scanner scan = new Scanner(System.in);

    public GarageHolderView(Model model, User user) {
        super(model);
        setUser(user);
        this.controller = new StandardOrderHandler(this.getModel());
    }

    @Override
    public void display() {
        this.update();
        System.out.println("> These are your pending orders: ");
        int size = this.pending_orders.size();
        int count = 1;
        for (Order or : this.pending_orders)
            System.out.println("> " + count++ + ") " + or.toString());
        System.out.println("> These are your completed orders: ");
        for (Order or : this.completed_orders)
            System.out.println("> " + count++ + ") " + or.toString());
        System.out.println("> If you wish to inspect an individual order enter the order's ranking number");
        System.out.println("> If you wish to place a new order enter ORDER: ");
        System.out.print(">> ");
        String response = this.scan.nextLine();
        this.check(response);
        Pattern pattern = Pattern.compile("^(\\d+)$");
        if (response.equalsIgnoreCase("order")) {
            this.startNewOrder();
        } else if (pattern.matcher(response).find()) {
            int number = Integer.parseInt(response);
            if (number > size) {
                if (((number - size) <= this.completed_orders.size()) && ((number - size) >= 1)) {
                    int index = number - size - 1;
                    StandardVehicleOrder or = (StandardVehicleOrder) this.completed_orders.get(index);
                    this.check(or);
                } else {
                    this.error();
                }
            } else if (number > 0) {
                StandardVehicleOrder or = (StandardVehicleOrder) this.pending_orders.get(number - 1);
                this.check(or);
            } else {
                this.error();
            }
        } else {
            this.error();
        }
    }

    private void check(StandardVehicleOrder or) {
        this.displayHelp();
        System.out.println("> Here are the order details: ");
        // TODO: kan gedetailleerder worden gemaakt.
        if (or.isCompleted()) {
            System.out.println("> Timestamp of ordering: " + or.getTimestamp().toString("EEE, dd MMM yyyy HH:mm:ss", Locale.ROOT));
            System.out.println("> Timestamp of completion: " + or.getCompletionDate().toString("EEE, dd MMM yyyy HH:mm:ss", Locale.ROOT));
        } else {
            System.out.println("> Timestamp of ordering: " + or.getTimestamp().toString("EEE, dd MMM yyyy HH:mm:ss", Locale.ROOT));
            System.out.println("> Estimated production time: " + or.getStandardTimeToFinish().toString());
        }
        System.out.print(">> ");
        String response = this.scan.nextLine();
        this.check(response);
        this.check(or);
    }

    private void startNewOrder() {
        // choose the car model
        try {
            this.available_vehiclemodels.clear();
            Iterator<VehicleModel> iter = null;

            iter = this.getModel().getVehicleModels(this.user);
            while (iter.hasNext())
                this.available_vehiclemodels.add(iter.next());
            System.out.println("> Please enter the corresponding number of the car model you wish to order:");
            int count = 1;
            for (VehicleModel cm : this.available_vehiclemodels)
                System.out.println("> " + count++ + ") " + cm.toString());
            System.out.print(">> ");
            String response = this.scan.nextLine();
            this.check(response);
            Pattern pattern = Pattern.compile("^(\\d+)$");
            if (pattern.matcher(response).find()) {
                int number = Integer.parseInt(response);
                if (number > this.available_vehiclemodels.size() || number < 1) {
                    System.out.println("! You entered something wrong please try again");
                    this.startNewOrder();
                }
                number -= 1;
                VehicleModel chosen = this.available_vehiclemodels.get(number);
                displayOrderingForm(chosen);
            } else {
                System.out.println("! wrong input");
                this.startNewOrder();
            }
        } catch (NoClearanceException e) {
            e.printStackTrace();
        }
    }

    private void displayOrderingForm(VehicleModel model) {
        ArrayList<VehicleOption> chosen = new ArrayList<VehicleOption>();
        ArrayList<VehicleOption> available = model.getPossibilities();
        StandardVehicleOrder validorder = null;
        while (validorder == null) {
            System.out.println("> Your chosen car options: ");
            int rem = 1;
            for (VehicleOption cho : chosen) {
                System.out.println("> " + rem + ") " + cho.toString());
                rem++;
            }
            System.out.println("> Enter the number of the option you want to include in your car");
            System.out.println("> If you want to remove a chosen car option enter REMOVE followed by the number of the option");
            System.out.println("> If you have chosen everything enter ORDER");
            int number = 1;
            for (VehicleOption opt : available) {
                System.out.println("> " + number + ") " + opt.toString());
                number++;
            }
            System.out.print(">> ");
            String response = this.scan.nextLine();
            this.check(response);
            Pattern remover = Pattern.compile("remove *(\\d*)", Pattern.CASE_INSENSITIVE);
            Matcher matcher = remover.matcher(response);
            Pattern pattern = Pattern.compile("^\\d+$");
            if (pattern.matcher(response).find()) {
                int choice = Integer.parseInt(response);
                if (choice > available.size() || choice < 1) {
                    System.out.println("! You entered something wrong. Please try again");
                } else {
                    VehicleOption want = available.remove(choice - 1);
                    chosen.add(want);
                }
            } else if (matcher.find()) {
                String num = matcher.group();
                num = num.split(" ")[1];

                int nam = Integer.parseInt(num);
                if (nam > chosen.size() || nam < 1) {
                    System.out.println("! You entered something wrong. Please try again");
                } else {
                    VehicleOption removed = chosen.remove(nam - 1);
                    available.add(removed);
                }
            } else if (response.equalsIgnoreCase("order")) {
                // try placing the order
                // errors get thrown if it does not comply to the restrictions
                try {
                    validorder = new StandardVehicleOrder(this.user, chosen, model);
                } catch (UnsatisfiedRestrictionException e) {
                    String message = e.getMessage();
                    System.out.println("! Your requested selection of car options "
                            + "does not comply with the model restrictions: ");
                    System.out.println("! " + message);
                    System.out.println("> Please update your selection accordingly");
                } catch (IllegalArgumentException e) {
                    System.out.println("! You entered something wrong. Please try again");
                } catch (NoClearanceException e) {
                    System.out.println("! Your user type is wrong. Please login again");
                    new LoginView(getModel()).display();
                }
            } else {
                System.out.println("! You entered something wrong. Please try again");
                this.displayOrderingForm(model);
            }
        }
        System.out.println("The order has been placed");
        this.controller.placeOrder(this.user, validorder);
        this.display();
    }

    @Override
    public void displayHelp() {
        super.helpOverview();
    }

    @Override
    public void error() {
        System.out.println("! Something went wrong please try again");
        this.displayHelp();
        this.display();
    }

    public void update() {
        try {
            Iterator<Order> pending = this.getModel().getPendingOrders(this.user);
            Iterator<Order> completed = this.getModel().getCompletedOrders(this.user);
            this.pending_orders.clear();
            this.completed_orders.clear();
            while (pending.hasNext())
                this.pending_orders.add(pending.next());
            while (completed.hasNext())
                this.completed_orders.add(completed.next());
            Collections.sort(this.pending_orders, new OrderDateTimeComparator());
            Collections.sort(this.completed_orders, new OrderDateTimeComparator());

        } catch (NoClearanceException e) {
            // NOP
        }
    }

    @Override
    public void cancel() {
        this.displayHelp();
        this.display();
    }

    @Override
    public void quit() {
        new LoginView(this.getModel()).display();
    }

}
