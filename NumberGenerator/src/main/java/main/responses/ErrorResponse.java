package main.responses;

public class ErrorResponse {

    private String data;

    public ErrorResponse(String error) {
        this.data = error;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
