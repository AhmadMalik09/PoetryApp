package pk.edu.uiit.poetryapp.Responce;

import java.util.ArrayList;
import java.util.List;

import pk.edu.uiit.poetryapp.Model.PoetryMOdel;

public class GetApiResponce {
String status;
String message;
List<PoetryMOdel> data;

    public GetApiResponce(String status, String message, List<PoetryMOdel> data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<PoetryMOdel> getData() {
        return data;
    }

    public void setData(List<PoetryMOdel> data) {
        this.data = data;
    }
}
