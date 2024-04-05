package org.example.parsers;

import org.example.entities.TimeField;

import java.util.ArrayList;
import java.util.List;

public class StarParser implements Parser{

    @Override
    public String getRegex() {
        return "^\\*$";
    }

    @Override
    public List<Integer> getTimings(TimeField timeField, String cronExpression) {
        List<Integer> result = new ArrayList<>();
        int startValue = timeField.getStartValue();
        int endValue = timeField.getEndValue();

        while(startValue <= endValue) {
            result.add(startValue);
            startValue++;
        }
        return result;
    }
}
