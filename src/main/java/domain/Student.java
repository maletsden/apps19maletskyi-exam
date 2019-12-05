package domain;

import json.*;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Andrii_Rodionov on 1/3/2017.
 */
public class Student extends BasicStudent {
    private Tuple<String, Integer>[] exams;

    public Student(String name, String surname, Integer year, Tuple<String, Integer>... exams) {
        super(name, surname, year);

        this.exams = exams;
    }

    public JsonObject toJsonObject() {
        JsonObject jsonObject = super.toJsonObject();

        Json[] array = new Json[exams.length];

        for (int i = 0; i < exams.length; i++) {
            array[i] = new JsonObject(
                    new JsonPair("course", new JsonString(exams[i].key)),
                    new JsonPair("mark", new JsonNumber(exams[i].value)),
                    new JsonPair("passed", new JsonBoolean(exams[i].value > 2))
            );
        }
        jsonObject.add(new JsonPair("exams", new JsonArray(
                array
        )));

        return jsonObject;
    }
}
