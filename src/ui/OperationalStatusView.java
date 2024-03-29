package ui;

import businessmodel.Model;
import businessmodel.assemblyline.AssemblyLine;
import businessmodel.exceptions.NoClearanceException;
import businessmodel.user.User;
import businessmodel.util.IteratorConverter;
import control.AssemblyLineController;
import control.AssemblyLineHandler;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;


/**
 * @author Team 10
 */
public class OperationalStatusView extends View {

    private AssemblyLineController controller;

    private AssemblyLine selectedAssemblyLine;

    private Scanner scan = new Scanner(System.in);

    public OperationalStatusView(Model cmc, User user) {
        super(cmc);
        setUser(user);
        this.controller = new AssemblyLineHandler(this.getModel());
    }

    @Override
    public void display() {
        this.selectAssemblyLine();
        this.changeState();
    }

    private void changeState() {
        try {
            System.out.println("> This is the current Assembly Line status: ");
            String current = this.getModel().getCurrentAssemblyLineStatus(this.user, this.selectedAssemblyLine);
            System.out.println("> " + current);
            System.out.println("> These are the available statuses: ");
            Iterator<String> available = this.getModel().getAvailableAssemblyLineStatus(this.user, this.selectedAssemblyLine);
            List<String> statuses = new IteratorConverter<String>().convert(available);
            int num = 1;
            for (String status : statuses)
                System.out.println("> " + num++ + ") " + status);
            System.out.print(">> ");
            String response = this.scan.nextLine();
            Pattern pattern = Pattern.compile("^\\d+$");
            this.check(response);
            if (pattern.matcher(response).find()) {
                int choice = Integer.parseInt(response);
                if (choice < 1 || choice > statuses.size())
                    this.error();
                String keuze = statuses.get(choice - 1);
                this.controller.changeOperationalStatus(this.user, this.selectedAssemblyLine, keuze);
                System.out.println("> The state was changed to: " + keuze);
                this.display();
            } else {
                this.error();
            }
        } catch (NoClearanceException e) {
            this.quit();
        }
    }

    private void selectAssemblyLine() {
        try {
            System.out.println("> Choose the assembly line you wish to change the status: ");
            System.out.println("> Enter the number of the assembly line: ");
            IteratorConverter<AssemblyLine> converter = new IteratorConverter<AssemblyLine>();
            ArrayList<AssemblyLine> lines = (ArrayList<AssemblyLine>) converter.convert(this.getModel().getAssemblyLines(this.user));
            int num = 1;
            for (AssemblyLine line : lines)
                System.out.println("> " + num++ + ") " + line.toString());
            System.out.print(">> ");
            String response = this.scan.nextLine();
            Pattern pattern = Pattern.compile("^\\d+$");
            this.check(response);
            if (pattern.matcher(response).find()) {
                int choice = Integer.parseInt(response);
                if (choice < 1 || choice > lines.size())
                    this.error();
                this.selectedAssemblyLine = lines.get(choice - 1);
            } else {
                this.error();
            }
        } catch (NoClearanceException e) {
            this.quit();
        }
    }

    @Override
    public void displayHelp() {
        super.helpOverview();
    }

    @Override
    public void cancel() {
        new ManagerView(this.getModel(), this.user).display();
    }

    @Override
    public void quit() {
        new LoginView(this.getModel()).display();
    }

    @Override
    public void error() {
        System.out.println("! Something went wrong. Please try again");
        this.display();
    }
}
