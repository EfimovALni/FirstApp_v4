package cz.firstapp.firstapp_v4.secondScreen;

import java.util.List;

public class Converter {
    private String name_button;
    public Spinner spinner;
    private List<Text> texts;
    public List<Checkbox> checkbox;
    private List<Button> buttons;

    public Converter(String name_button, Spinner spinner, List<Text> texts, List<Checkbox> checkbox, List<Button> buttons) {
        this.name_button = name_button;
        this.spinner = spinner;
        this.texts = texts;
        this.checkbox = checkbox;
        this.buttons = buttons;
    }

    public String getName_button() {
        return name_button;
    }

    public void setName_button(String name_button) {
        this.name_button = name_button;
    }

    public Spinner getSpinner() {
        return spinner;
    }

    public void setSpinner(Spinner spinner) {
        this.spinner = spinner;
    }

    public List<Text> getTexts() {
        return texts;
    }

    public void setTexts(List<Text> texts) {
        this.texts = texts;
    }

    public List<Checkbox> getCheckbox() {
        return checkbox;
    }

    public void setCheckbox(List<Checkbox> checkbox) {
        this.checkbox = checkbox;
    }

    public List<Button> getButtons() {
        return buttons;
    }

    public void setButtons(List<Button> buttons) {
        this.buttons = buttons;
    }
}


class Item {
    private String item;

    public Item(String item) {
        this.item = item;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }
}


class Spinner {
    private String set_prompt;
    private List<Item> items;

    public Spinner(String set_prompt, List<Item> items) {
        this.set_prompt = set_prompt;
        this.items = items;
    }

    public String getSet_prompt() {
        return set_prompt;
    }

    public void setSet_prompt(String set_prompt) {
        this.set_prompt = set_prompt;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}

class Text {
    private String info_1;
    private String info_2;
    private String email;
    private String phone;
    private String purpose;
    private String improvements;
    private String fields_filled;


    public Text(String info_1, String info_2, String email, String phone, String purpose, String improvements, String fields_filled) {
        this.info_1 = info_1;
        this.info_2 = info_2;
        this.email = email;
        this.phone = phone;
        this.purpose = purpose;
        this.improvements = improvements;
        this.fields_filled = fields_filled;
    }

    public String getInfo_1() {
        return info_1;
    }

    public void setInfo_1(String info_1) {
        this.info_1 = info_1;
    }

    public String getInfo_2() {
        return info_2;
    }

    public void setInfo_2(String info_2) {
        this.info_2 = info_2;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getImprovements() {
        return improvements;
    }

    public void setImprovements(String improvements) {
        this.improvements = improvements;
    }

    public String getFields_filled() {
        return fields_filled;
    }

    public void setFields_filled(String fields_filled) {
        this.fields_filled = fields_filled;
    }
}

class Checkbox {
    private String under;
    private String more_than;
    private String purchase;

    public Checkbox(String under, String more_than, String purchase) {
        this.under = under;
        this.more_than = more_than;
        this.purchase = purchase;
    }

    public String getUnder() {
        return under;
    }

    public void setUnder(String under) {
        this.under = under;
    }

    public String getMore_than() {
        return more_than;
    }

    public void setMore_than(String more_than) {
        this.more_than = more_than;
    }

    public String getPurchase() {
        return purchase;
    }

    public void setPurchase(String purchase) {
        this.purchase = purchase;
    }
}

class Button extends Throwable {
    private String attachment;
    private String ticket;
    private String cancel;

    public Button(String attachment, String ticket, String cancel) {
        this.attachment = attachment;
        this.ticket = ticket;
        this.cancel = cancel;
    }

    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public String getCancel() {
        return cancel;
    }

    public void setCancel(String cancel) {
        this.cancel = cancel;
    }
}
