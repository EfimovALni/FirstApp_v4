package cz.firstapp.firstapp_v4.model;

public class RootMainScreen {
    private String status;
    private MainScreen MainScreen;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public MainScreen getMainScreen() {
        return MainScreen;
    }

    public void setMainScreen(MainScreen mainScreen) {
        MainScreen = mainScreen;
    }
}