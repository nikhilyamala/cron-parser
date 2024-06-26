package org.example.parsers;

import org.example.entities.TimeField;
import java.util.ArrayList;
import java.util.List;

public class IncrementParser implements Parser{

    @Override
    public String getRegex() {
        return "^\\*\\/\\d+$";
    }

    @Override
    public List<Integer> getTimings(TimeField timeField, String cronExpression) {
        List<Integer> result = new ArrayList<>();
        String expr = "*/";
        int incrementValue;
        try{
            incrementValue = Integer.valueOf(cronExpression.substring(expr.length()));
        } catch(NumberFormatException ex) {
            throw new RuntimeException("Invalid interval value entered!");
        }
        int startValue = timeField.getStartValue();
        int endValue = timeField.getEndValue();
        if(incrementValue < startValue) {
            throw new RuntimeException("Invalid interval value entered!");
        }
        while (startValue <= endValue) {
            result.add(startValue);
            startValue += incrementValue;
        }
        return result;
    }
}
