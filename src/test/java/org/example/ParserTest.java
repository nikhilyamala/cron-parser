package org.example;

import org.example.entities.CronResponse;
import org.example.entities.TimeField;
import org.example.manager.ParsingManager;
import org.example.parsers.*;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ParserTest {

    @Test
    public void testCronExpressionParsingPositive() throws RuntimeException {
        ParsingManager parsingManager = new ParsingManager();
        parsingManager.registerParsers();

        CronExpressionParser cronExpressionParser = new CronExpressionParser(parsingManager);

        CronResponse cronExpressionResponse = cronExpressionParser.parseExpression("*/15 0 1,15 * 1-5 /usr/bin/find");
        assertNotNull(cronExpressionResponse);
    }

    @Test
    public void testCronExpressionParsingNegative() throws RuntimeException {
        ParsingManager parsingManager = new ParsingManager();
        parsingManager.registerParsers();

        CronExpressionParser cronExpressionParser = new CronExpressionParser(parsingManager);
        try {
            cronExpressionParser.parseExpression("*/15 0 1,15 # 1-59 /usr/bin/find");
        }
        catch(RuntimeException re) {
            assertEquals("Invalid cron expression entered! Cannot be parsed!", re.getMessage());
        }
    }

    @Test
    public void testStarParsingPositive() throws RuntimeException {
        Parser p = new StarParser();
        List<Integer> result;

        result = p.getTimings(TimeField.DAY_OF_WEEK,"*");
        assertEquals("[1, 2, 3, 4, 5, 6, 7]", result.toString());
    }

    @Test
    public void testStarParsingNegative()  {
        Parser parser = new StarParser();
        expressionParserUtil("#", TimeField.DAY_OF_MONTH, "Invalid value entered!", parser);
    }

    @Test
    public void testRangeParsingPositive() throws RuntimeException {
        Parser p = new RangeParser();
        List<Integer> result;

        result = p.getTimings(TimeField.HOUR,"1-3");
        assertEquals("[1, 2, 3]", result.toString());

        result = p.getTimings(TimeField.DAY_OF_WEEK,"4-7");
        assertEquals("[4, 5, 6, 7]", result.toString());

        result = p.getTimings(TimeField.MINUTE,"2-2");
        assertEquals("[2]", result.toString());
    }

    @Test
    public void testRangeParsingNegative()  {
        Parser parser = new RangeParser();
        expressionParserUtil("2-57", TimeField.DAY_OF_MONTH, "Entered values are not in the range", parser);
        expressionParserUtil("70-80", TimeField.MINUTE, "Entered values are not in the range", parser);
        expressionParserUtil("2-10", TimeField.DAY_OF_WEEK, "Entered values are not in the range", parser);
    }

    @Test
    public void testNumberParsingPositive() throws RuntimeException {
        Parser p = new NumberParser();
        List<Integer> result;

        result = p.getTimings(TimeField.HOUR,"9");
        assertEquals("[9]", result.toString());

        result = p.getTimings(TimeField.DAY_OF_WEEK,"2");
        assertEquals("[2]", result.toString());

        result = p.getTimings(TimeField.MINUTE,"39");
        assertEquals("[39]", result.toString());
    }

    @Test
    public void testNumberParsingNegative()  {
        Parser parser = new NumberParser();
        expressionParserUtil("-1", TimeField.DAY_OF_MONTH, "Incorrect time value provided!", parser);
        expressionParserUtil("100", TimeField.MINUTE, "Incorrect time value provided!", parser);
        expressionParserUtil("8", TimeField.DAY_OF_WEEK, "Incorrect time value provided!", parser);
    }

    @Test
    public void testMultipleValuesParsingPositive() throws RuntimeException {
        Parser p = new MultipleValuesParser();
        List<Integer> result;

        result = p.getTimings(TimeField.HOUR,"1,2,3");
        assertEquals("[1, 2, 3]", result.toString());

        result = p.getTimings(TimeField.HOUR,"1,1,1");
        assertEquals("[1]", result.toString());

        result = p.getTimings(TimeField.HOUR,"3,1,2");
        assertEquals("[1, 2, 3]", result.toString());
    }

    @Test
    public void testMultipleValuesParsingNegative()  {
        Parser parser = new MultipleValuesParser();
        expressionParserUtil("-1,35", TimeField.DAY_OF_MONTH, "Incorrect time value entered!", parser);
        expressionParserUtil("2,34", TimeField.DAY_OF_MONTH, "Incorrect time value entered!", parser);
        expressionParserUtil("0,5", TimeField.DAY_OF_WEEK, "Incorrect time value entered!", parser);
    }

    @Test
    public void testIncrementParsingPositive() throws RuntimeException {
        Parser p = new IncrementParser();
        List<Integer> result;

        result = p.getTimings(TimeField.HOUR,"*/10");
        assertEquals("[0, 10, 20]", result.toString());

        result = p.getTimings(TimeField.HOUR,"*/24");
        assertEquals("[0]", result.toString());

        result = p.getTimings(TimeField.DAY_OF_MONTH,"*/20");
        assertEquals("[1, 21]", result.toString());

        result = p.getTimings(TimeField.DAY_OF_WEEK,"*/2");
        assertEquals("[1, 3, 5, 7]", result.toString());
    }
    @Test
    public void testIncrementParsingNegative()  {
        Parser parser = new IncrementParser();
        expressionParserUtil("*/0", TimeField.DAY_OF_MONTH, "Invalid interval value entered!", parser);
        expressionParserUtil("*/10/10", TimeField.DAY_OF_MONTH, "Invalid interval value entered!", parser);
        expressionParserUtil("*/A", TimeField.DAY_OF_MONTH, "Invalid interval value entered!", parser);
    }

    private void expressionParserUtil(String incomingText, TimeField timeField, String msg, Parser parser) {
        try {
            parser.getTimings(timeField,incomingText);
        }
        catch(RuntimeException re) {
            assertEquals(msg, re.getMessage());
        }
    }
}
