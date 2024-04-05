package org.example.manager;

import org.example.entities.TimeField;
import org.example.parsers.Parser;
import org.example.parsers.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

public class ParsingManager {

    private Set<Parser> parserSet = new HashSet<>();

    public List<Integer> getTimingsList(TimeField timeField, String cronExpression) {
        for(Parser parser: parserSet) {
            if(Pattern.matches(parser.getRegex(),cronExpression)) {
                return parser.getTimings(timeField,cronExpression);
            }
        }
        throw new RuntimeException("Invalid cron expression entered! Cannot be parsed!");
    }

    public void registerParsers() {
        parserSet.add(new StarParser());
        parserSet.add(new IncrementParser());
        parserSet.add(new MultipleValuesParser());
        parserSet.add(new NumberParser());
        parserSet.add(new RangeParser());
    }
}
