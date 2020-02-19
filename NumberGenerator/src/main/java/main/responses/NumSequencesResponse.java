package main.responses;

import java.util.List;
import java.util.Set;

public class NumSequencesResponse {

    List<Set<Integer>> data;

    public NumSequencesResponse(List<Set<Integer>> data) {
        this.data = data;
    }

    public List<Set<Integer>> getData() {
        return data;
    }

    public void setData(List<Set<Integer>> data) {
        this.data = data;
    }
}
