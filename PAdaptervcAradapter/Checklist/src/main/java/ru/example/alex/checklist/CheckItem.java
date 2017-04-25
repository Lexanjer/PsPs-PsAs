package ru.example.alex.checklist;

/**
 * Created by Alex on 27.03.2017.
 */

public class CheckItem {

    private String option;
    private String checked;

    public CheckItem (String option, String checked){

        this.option =option;
        this.checked = checked;


    }

    public String getChecked() {
        return this.checked;
    }

    public String getOption() {
        return this.option;
    }

    public void setChecked(String checked) {
        this.checked = checked;
    }

    public void setOption(String option) {
        this.option = option;
    }
}
