package ui;

import businessmodel.Model;
import businessmodel.assemblyline.AssemblyLine;
import businessmodel.assemblyline.AssemblyTask;
import businessmodel.assemblyline.WorkPost;
import businessmodel.exceptions.NoClearanceException;
import businessmodel.observer.Observer;
import businessmodel.observer.Subject;
import businessmodel.user.User;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class AssemblyLineStatusView extends View implements Observer {

    private AssemblyLine subject;

    private Scanner scan = new Scanner(System.in);

    private AssemblyLine selectedAssemblyLine;

    private boolean active = false;

    public AssemblyLineStatusView(Model cmc, User user, AssemblyLine line) {
        super(cmc);
        this.setAssemblyLine(line);
        line.subscribeObserver(this);
        setUser(user);
        this.setActive(true);
    }

    private void setAssemblyLine(AssemblyLine line) {
        this.selectedAssemblyLine = line;

    }

    private void setActive(boolean b) {
        this.active = b;
    }

    @Override
    public void display() {
        try {
            Iterator<WorkPost> postsIterator = this.getModel().getWorkPosts(this.user, this.selectedAssemblyLine);
            ArrayList<WorkPost> posts = new ArrayList<>();
            while (postsIterator.hasNext())
                posts.add(postsIterator.next());
            System.out.println("> This is the assembly line status: ");
            for (WorkPost wp : posts) {
                System.out.println("  > " + wp.getName() + "status: ");
                Iterator<AssemblyTask> pendingIterator = this.getModel().getPendingTasks(this.user, wp);
                ArrayList<AssemblyTask> pending = new ArrayList<>();
                while (pendingIterator.hasNext())
                    pending.add(pendingIterator.next());
                Iterator<AssemblyTask> finishedIterator = this.getModel().getFinishedTasks(this.user, wp);
                ArrayList<AssemblyTask> finished = new ArrayList<>();
                while (finishedIterator.hasNext())
                    finished.add(finishedIterator.next());
                System.out.println("    > pending tasks:");
                for (AssemblyTask task : pending)
                    System.out.println("        > " + task.toString());
                System.out.println("    > finished tasks:");
                for (AssemblyTask task : finished)
                    System.out.println("        > " + task.toString());
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
    public void cancel() {
        this.setActive(false);
        new VehicleMechanicView(this.getModel(), this.user, this.selectedAssemblyLine).display();
    }

    @Override
    public void quit() {
        new LoginView(this.getModel()).display();
    }

    @Override
    public void error() {
        System.out.println("! You entered something wrong. Please try again");
        this.display();
    }

    @Override
    public void update(Subject subject) {
        if (this.subject != subject)
            this.subject = (AssemblyLine) subject;
        if (this.active)
            this.display();
    }

}
