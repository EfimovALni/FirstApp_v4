package cz.firstapp.firstapp_v4.modelLogin;

public class RootLogin {
    private String status;
    private String loginResult;
    private String message;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLoginResult() {
        return loginResult;
    }

    public void setLoginResult(String loginResult) {
        this.loginResult = loginResult;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    //    {
//        "status": "OK",
//            "loginResult": "OK",
//            "message": "User testuser logged successfully with encoded password"
//    }
}
