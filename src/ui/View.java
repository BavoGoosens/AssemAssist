package ui;

import businessmodel.Model;
import businessmodel.user.User;

public abstract class View {

    protected User user;
    private Model cmc;

    public View(Model cmc) {
        this.setModel(cmc);
    }

    public void helpOverview() {
        System.out.println("The >> characters indicate the system expects input");
        System.out.println("The > characters indicate standard output");
        System.out.println("The ! indicate an error occured ");
        System.out.println("To go back to the previous step enter: CANCEL");
        System.out.println("To go back to the login prompt enter: QUIT");
        System.out.println("If you need help enter: HELP");
    }

    protected void setUser(User user) {
        this.user = user;
    }

    public abstract void display();

    public abstract void displayHelp();

    public abstract void cancel();

    public abstract void quit();

    public abstract void error();

    protected Model getModel() {
        return this.cmc;
    }

    private void setModel(Model cmc) {
        this.cmc = cmc;
    }

    protected void check(String str) {
        if (str.equalsIgnoreCase("quit"))
            this.quit();
        if (str.equalsIgnoreCase("cancel"))
            this.cancel();
        if (str.equalsIgnoreCase("help"))
            this.displayHelp();
    }

}
