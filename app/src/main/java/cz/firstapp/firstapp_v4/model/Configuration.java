package cz.firstapp.firstapp_v4.model;

import java.util.List;

/**
 * The {@code Configuration} class which repeat JSON model. According this
 * class JSON will be deserialize.
 *
 * @author Alexander Efimov
 * @version 0.0.1
 * @date last changed: 14.10.2018
 */

public class Configuration {
    private List<Initial_screen> initial_screen; // TODO: Change on "Screen Elements"

    public List<Initial_screen> getInitialScreen() {
        return initial_screen;
    }

    public void setInitialScreen(List<Initial_screen> initial_screen) {
        this.initial_screen = initial_screen;
    }
}
