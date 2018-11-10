package cz.firstapp.firstapp_v4.modelSecondScreen;

import java.util.List;

public class SpinnerMy{
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
