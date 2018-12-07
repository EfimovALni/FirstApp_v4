package cz.firstapp.firstapp_v4.modelApiSecurity;

public class RootSecurity {
    private String status;
    private ApiSecurity ApiSecurity;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public cz.firstapp.firstapp_v4.modelApiSecurity.ApiSecurity getApiSecurity() {
        return ApiSecurity;
    }

    public void setApiSecurity(cz.firstapp.firstapp_v4.modelApiSecurity.ApiSecurity apiSecurity) {
        ApiSecurity = apiSecurity;
    }
}
