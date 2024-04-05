package org.example.parsers;


import org.example.entities.TimeField;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class MultipleValuesParser implements Parser{

    @Override
    public String getRegex() {
        return "^\\d+(,\\d+)*$";
    }

    @Override
    public List<Integer> getTimings(TimeField timeField, String cronExpression) {
        Set<Integer> result = new TreeSet<>();
        String[] cronValues = cronExpression.split(",");
        for(String cronValue: cronValues) {
            int timeValue = Integer.valueOf(cronValue);
            if(timeValue >= timeField.getStartValue() && timeValue <= timeField.getEndValue()) {
                result.add(timeValue);
            }
            else {
                throw new RuntimeException("Incorrect time value entered!");
            }
        }
        List<Integer> mainList = new ArrayList<>();
        mainList.addAll(result);
        return mainList;
    }
}
