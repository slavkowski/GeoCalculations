package pl.sgeonet.FieldObservationsObjects;

import java.io.Serializable;

public class Geoid implements Serializable {
    private static final long serialVersionUID = 1L;

    private String test;


    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }

    @Override
    public String toString() {
        return "Geoid{" +
                "test='" + test + '\'' +
                '}';
    }
}
