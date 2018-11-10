package cz.firstapp.firstapp_v4.modelSecondScreen;


import java.util.List;

public class SecondScreenModel{
    private SpinnerMy spinnerMy;
    private List<TextsMy> textsMy;
    private List<CheckboxMy> checkboxMy;
    private List<ButtonsMy> buttonsMy;

    public SpinnerMy getSpinnerMy(){
        return spinnerMy;
    }
    public void setSpinnerMy(SpinnerMy input){
        this.spinnerMy = input;
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

class ItemsMy{
    private String itemMy;

    public String getItemMy(){
        return itemMy;
    }
    public void setItemMy(String input){
        this.itemMy = input;
    }
}
class SpinnerMy{
    private String promptMy;
    private List<ItemsMy> itemsMy;

    public String getPromptMy(){
        return promptMy;
    }
    public void setPromptMy(String input){
        this.promptMy = input;
    }
    public List<ItemsMy> getItemsMy(){
        return itemsMy;
    }
    public void setItemsMy(List<ItemsMy> input){
        this.itemsMy = input;
    }
}
class TextsMy{
    private String name;
    private String text;

    public String getName(){
        return name;
    }
    public void setName(String input){
        this.name = input;
    }
    public String getText(){
        return text;
    }
    public void setText(String input){
        this.text = input;
    }
}
class CheckboxMy{
    private String name;
    private String text;

    public String getName(){
        return name;
    }
    public void setName(String input){
        this.name = input;
    }
    public String getText(){
        return text;
    }
    public void setText(String input){
        this.text = input;
    }
}
class ButtonsMy{
    private String name;
    private String text;

    public String getName(){
        return name;
    }
    public void setName(String input){
        this.name = input;
    }
    public String getText(){
        return text;
    }
    public void setText(String input){
        this.text = input;
    }
}
