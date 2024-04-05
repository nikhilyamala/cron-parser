package org.example.parsers;


import org.example.entities.TimeField;

import java.util.List;

public interface Parser {
    public String getRegex();
    public List<Integer> getTimings(TimeField timeField, String cronExpression);
}
