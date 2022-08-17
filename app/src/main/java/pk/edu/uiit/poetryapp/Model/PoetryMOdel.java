package pk.edu.uiit.poetryapp.Model;

public class PoetryMOdel {
    int id;
    String poetry_data;
    String poet_name;
    String date_time;

    public PoetryMOdel(int id, String poetrydata, String poetName, String dateTime) {
        this.id = id;
        poetry_data = poetrydata;
        poet_name = poetName;
        this.date_time = dateTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPoetry_data() {
        return poetry_data;
    }

    public void setPoetry_data(String poetrydata) {
        poetry_data = poetrydata;
    }

    public String getPoetName() {
        return poet_name;
    }

    public void setPoetName(String poetName) {
        poet_name = poetName;
    }

    public String getDateTime() {
        return date_time;
    }

    public void setDateTime(String dateTime) {
        this.date_time = dateTime;
    }
}
