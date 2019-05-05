package it.polimi.se2019.view.client.cli;

import it.polimi.se2019.exceptions.NoInputException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;


/**
 * @author Simone Orlando
 */
public class CliReader {


    private int seconds;
    private Scanner scanner;

    public CliReader(int seconds) {
        this.seconds = seconds;
        scanner = new Scanner(System.in);
    }

    public String getTimedString() throws IOException, NoInputException {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        long startTime = System.currentTimeMillis();
        while ((System.currentTimeMillis() - startTime) < seconds * 1000 && !in.ready()) {

        }
        if (in.ready()) {
            return in.readLine();
        }
        else {
            throw new NoInputException("Time out for input!");
        }
    }

    public int getTimedInt() throws IOException, NoInputException{
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        long startTime = System.currentTimeMillis();
        while ((System.currentTimeMillis() - startTime) < seconds * 1000 && !in.ready()) {

        }
        if (in.ready()) {
            String temp = in.readLine();
            return Integer.parseInt(temp);
        }
        else {
            throw new NoInputException("Time out for input!");
        }
    }

    public String getString() {
        return scanner.nextLine();
    }

    public int getInt() {
        return scanner.nextInt();
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }

}