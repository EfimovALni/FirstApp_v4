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
    private String color;
    private String text;
    private String icon;

    public Initial_screen(String color, String text, String icon) {
        this.color = color;
        this.text = text;
        this.icon = icon;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
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

    @Override
    public String toString() {
        return "Initial_screen{" +
                "color='" + color + '\'' +
                ", text='" + text + '\'' +
                ", icon='" + icon + '\'' +
                '}';
    }
}