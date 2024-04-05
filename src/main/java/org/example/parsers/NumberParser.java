package org.example.parsers;

import org.example.entities.TimeField;

import java.util.ArrayList;
import java.util.List;

public class NumberParser implements Parser{

    @Override
    public String getRegex() {
        return "^\\d+$";
    }

    @Override
    public List<Integer> getTimings(TimeField timeField, String cronExpression) {
        List<Integer> result = new ArrayList<>();
        int timeValue = Integer.valueOf(cronExpression);
        if(timeValue >= timeField.getStartValue() && timeValue <= timeField.getEndValue()) {
            result.add(timeValue);
        }
        else{
            throw new RuntimeException("Incorrect time value provided!");
        }
        return result;
    }
}
