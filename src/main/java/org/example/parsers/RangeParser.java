package org.example.parsers;


import org.example.entities.TimeField;

import java.util.ArrayList;
import java.util.List;

public class RangeParser implements Parser{

    @Override
    public String getRegex() {
        return "^\\d+\\-\\d+$";
    }

    @Override
    public List<Integer> getTimings(TimeField timeField, String cronExpression) {
        List<Integer> result = new ArrayList<>();
        String[] rangeValues = cronExpression.split("-");
        int startValue = Integer.valueOf(rangeValues[0]);
        int endValue = Integer.valueOf(rangeValues[1]);
        if(startValue >= timeField.getStartValue() && startValue <= endValue && endValue <= timeField.getEndValue()) {
            while(startValue <= endValue) {
                result.add(startValue);
                startValue++;
            }
        }
        else{
            throw new RuntimeException("Entered values are not in the range");
        }
        return result;
    }
}
