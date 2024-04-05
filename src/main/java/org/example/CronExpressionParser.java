package org.example;

import org.example.entities.CronResponse;
import org.example.entities.TimeField;
import org.example.manager.ParsingManager;

import java.util.List;

public class CronExpressionParser {
    private ParsingManager parsingManager;

    public CronExpressionParser(ParsingManager parsingManager) {
        this.parsingManager = parsingManager;
    }

    public CronResponse parseExpression(String cronExpression) {
        String[] subCronExpressions = cronExpression.split(" ");

        List<Integer> MinuteResult = parsingManager.getTimingsList(TimeField.MINUTE,subCronExpressions[0]);
        List<Integer> HourResult = parsingManager.getTimingsList(TimeField.HOUR,subCronExpressions[1]);
        List<Integer> DayOfMonthResult = parsingManager.getTimingsList(TimeField.DAY_OF_MONTH,subCronExpressions[2]);
        List<Integer> MonthResult = parsingManager.getTimingsList(TimeField.MONTH,subCronExpressions[3]);
        List<Integer> DayOfWeekResult = parsingManager.getTimingsList(TimeField.DAY_OF_WEEK,subCronExpressions[4]);
        String cronCommand = subCronExpressions[5];

        CronResponse cronResponse = new CronResponse(MinuteResult,HourResult,DayOfMonthResult,MonthResult,DayOfWeekResult,cronCommand);
        return cronResponse;
    }
}
