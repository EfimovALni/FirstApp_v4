package cz.firstapp.firstapp_v4.modelSecondScreen;


import java.util.List;

public class SecondScreen {
    private List<SpinnerMy> spinnerMy;
    private List<TextsMy> textsMy;
    private List<CheckboxMy> checkboxMy;
    private List<ButtonsMy> buttonsMy;

    public List<SpinnerMy> getSpinnerMy() {
        return spinnerMy;
    }

    public void setSpinnerMy(List<SpinnerMy> spinnerMy) {
        this.spinnerMy = spinnerMy;
    }

    public List<TextsMy> getTextsMy(){
        return textsMy;
    }
    public void setTextsMy(List<TextsMy> input){
        this.textsMy = input;
    }
    public List<CheckboxMy> getCheckboxMy(){
        return checkboxMy;
    }
    public void setCheckboxMy(List<CheckboxMy> input){
        this.checkboxMy = input;
    }
    public List<ButtonsMy> getButtonsMy(){
        return buttonsMy;
    }
    public void setButtonsMy(List<ButtonsMy> input){
        this.buttonsMy = input;
    }
}

