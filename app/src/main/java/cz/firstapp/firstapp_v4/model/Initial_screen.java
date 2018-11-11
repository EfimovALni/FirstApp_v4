package cz.firstapp.firstapp_v4.model;

/**
 * The {@code Initial_screen} class which repeat JSON model. According this
 * class JSON will be deserialize.
 *
 * @author Alexander Efimov
 * @version 0.0.1
 * @date last changed: 14.10.2018
 */

public class Initial_screen {
    private String api;
    private String text;
    private String icon;

    public Initial_screen(String api, String text, String icon) {
        this.api = api;
        this.text = text;
        this.icon = icon;
    }

    public String getApi() {
        return api;
    }

    public void setApi(String api) {
        this.api = api;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}