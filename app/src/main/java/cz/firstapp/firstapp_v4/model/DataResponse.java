package cz.firstapp.firstapp_v4.model;

/**
 * The {@code DataResponse} class which repeat JSON model. According this
 * class JSON will be deserialize.
 *
 * @author Alexander Efimov
 * @version 0.0.1
 * @date last changed: 14.10.2018
 */

public class DataResponse {
    private String status;
    private Float actual_version;
    private Configuration configuration;    //TODO: change in JSON 'configuartion' to 'configuration'

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Float getActual_version() {
        return actual_version;
    }

    public void setActual_version(Float actual_version) {
        this.actual_version = actual_version;
    }

    public Configuration getConfiguartion() {
        return configuration;
    }

    public void setConfiguartion(Configuration configuartion) {
        this.configuration = configuartion;
    }
}
