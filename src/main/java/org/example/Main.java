package org.example;

import org.example.entities.CronResponse;
import org.example.manager.ParsingManager;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        String cronExpression = args[0];

        if(cronExpression.split(" ").length != 6) {
            throw new RuntimeException("Invalid number of arguments passed!");
        }

        ParsingManager parsingManager = new ParsingManager();
        parsingManager.registerParsers();

        CronExpressionParser cronExpressionParser = new CronExpressionParser(parsingManager);
        try{
            CronResponse cronExpressionResponse = cronExpressionParser.parseExpression(cronExpression);
            cronExpressionResponse.printResponse();
        } catch(RuntimeException ex) {
            System.out.println(ex.getMessage());
        }
    }
}