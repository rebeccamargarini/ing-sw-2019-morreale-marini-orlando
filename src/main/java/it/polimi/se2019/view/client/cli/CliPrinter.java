package it.polimi.se2019.view.client.cli;

import it.polimi.se2019.model.rep.AmmoRep;
import it.polimi.se2019.model.rep.BoardRep;
import it.polimi.se2019.model.rep.CardRep;
import it.polimi.se2019.model.rep.LightGameVersion;
import it.polimi.se2019.utils.HandyFunctions;

import java.util.*;

/**
 * Class used to draw all the graphic elements of the CLI interface
 * @author Simone Orlando
 */
public final class CliPrinter {

    private CliPrinter() {
        /*
            Not necessary configuration
         */
    }

    private static final String RESET = "\u001B[0m";

    /**
     * Print a message on the terminal, resetting the color to default
     * @param msg to print
     */
    static void stamp(String msg) {
        HandyFunctions.printConsole(msg + RESET);
    }

    /**
     * Print a message on the terminal
     * @param msg to print
     * @param textColor of the message
     * @param backColor of the message
     */
    private static void noResetStamp(String msg, CliColor textColor, CliColor backColor) {
        String out;
        out = backColor.getCode() + msg;
        noResetStamp(out,textColor);
    }

    /**
     * Print a message on the terminal
     * @param msg to print
     * @param textColor of the message
     */
    private static void noResetStamp(String msg, CliColor textColor) {
        String out;
        out = textColor.getCode() + msg;
        noResetStamp(out);
    }

    /**
     * Print a message on the terminal
     * @param msg to print
     */
    private static void noResetStamp(String msg) {
        HandyFunctions.printConsole(msg);
    }

    /**
     * Reset the color to default
     */
    static void reset() {
        HandyFunctions.printConsole(RESET);
    }

    /**
     * Print a message on the terminal, resetting the color to default
     * @param msg to print
     * @param textColor of the message
     */
     static void stamp(String msg, CliColor textColor) {
        String out;
        out = textColor.getCode() + msg;
        stamp(out);
    }

    /**
     * Print a message on the terminal, resetting the color to default
     * @param msg to print
     * @param textColor of the message
     * @param backColor of the message
     */
    public static void stamp(String msg, CliColor textColor, CliColor backColor) {
        String out;
        out = backColor.getCode() + msg;
        stamp(out,textColor);
    }

    /**
     * Print the game logo
     */
    public static void welcomeMessage() {

        stamp("\n" + " \t\t\t\t █████╗ ██████╗ ██████╗ ███████╗███╗   ██╗ █████╗ ██╗     ██╗███╗   ██╗███████╗\n" +
                "\t\t\t\t██╔══██╗██╔══██╗██╔══██╗██╔════╝████╗  ██║██╔══██╗██║     ██║████╗  ██║██╔════╝\n" +
                "\t\t\t\t███████║██║  ██║██████╔╝█████╗  ██╔██╗ ██║███████║██║     ██║██╔██╗ ██║█████╗  \n" +
                "\t\t\t\t██╔══██║██║  ██║██╔══██╗██╔══╝  ██║╚██╗██║██╔══██║██║     ██║██║╚██╗██║██╔══╝  \n" +
                "\t\t\t\t██║  ██║██████╔╝██║  ██║███████╗██║ ╚████║██║  ██║███████╗██║██║ ╚████║███████╗\n" +
                "\t\t\t\t╚═╝  ╚═╝╚═════╝ ╚═╝  ╚═╝╚══════╝╚═╝  ╚═══╝╚═╝  ╚═╝╚══════╝╚═╝╚═╝  ╚═══╝╚══════╝", CliColor.TEXTRED);
    }

    /**
     * Print the graphic interface selection box
     */
    public static void boxInterfaceMessage() {
        CliPrinter.stamp("\t\t\t\t\t ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓\n");
        CliPrinter.stamp("\t\t\t\t\t ┃                                                          ┃\n");
        CliPrinter.stamp("\t\t\t\t\t ┃           Which interface do you want to use?            ┃\n");
        CliPrinter.stamp("\t\t\t\t\t ┃               <1> CLI                                    ┃\n");
        CliPrinter.stamp("\t\t\t\t\t ┃               <2> GUI                                    ┃\n");
        CliPrinter.stamp("\t\t\t\t\t ┃           choice:");
        CliSetUp.savePosition();
        CliPrinter.stamp("                                        ┃\n");
        CliPrinter.stamp("\t\t\t\t\t ┃                                                          ┃\n");
        CliPrinter.stamp("\t\t\t\t\t ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛\n");
        CliSetUp.restorePosition();
    }

    /**
     * Print the connection type selection box
     */
     static void boxConnectionMessage() {
        CliPrinter.stamp("\t\t\t\t\t ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓\n");
        CliPrinter.stamp("\t\t\t\t\t ┃                                                          ┃\n");
        CliPrinter.stamp("\t\t\t\t\t ┃           Choose a connection type:                      ┃\n");
        CliPrinter.stamp("\t\t\t\t\t ┃               <1> Socket                                 ┃\n");
        CliPrinter.stamp("\t\t\t\t\t ┃               <2> Rmi                                    ┃\n");
        CliPrinter.stamp("\t\t\t\t\t ┃           choice:");
        CliSetUp.savePosition();
        CliPrinter.stamp("                                        ┃\n");
        CliPrinter.stamp("\t\t\t\t\t ┃                                                          ┃\n");
        CliPrinter.stamp("\t\t\t\t\t ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛\n");
        CliSetUp.restorePosition();
    }

    /**
     * Print the username selection box
     */
     static void boxUsernameMessage() {
        CliPrinter.stamp("\t\t\t\t\t ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓\n");
        CliPrinter.stamp("\t\t\t\t\t ┃                                                          ┃\n");
        CliPrinter.stamp("\t\t\t\t\t ┃           Username: ");
        CliSetUp.savePosition();
        CliPrinter.stamp("                                     ┃\n");
        CliPrinter.stamp("\t\t\t\t\t ┃                                                          ┃\n");
        CliPrinter.stamp("\t\t\t\t\t ┃           Ip:                                            ┃\n");
        CliPrinter.stamp("\t\t\t\t\t ┃                                                          ┃\n");
        CliPrinter.stamp("\t\t\t\t\t ┃                                                          ┃\n");
        CliPrinter.stamp("\t\t\t\t\t ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛\n");
        CliSetUp.restorePosition();
    }

    /**
     *  Print the ip selection box
     * @param usename chosen by the player
     */
     static void boxIpMessage(String usename) {
        CliPrinter.stamp("\t\t\t\t\t ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓\n");
        CliPrinter.stamp("\t\t\t\t\t ┃                                                          ┃\n");
        CliPrinter.stamp("\t\t\t\t\t ┃           Username: "+usename);
        for (int i=0; i < 37-usename.length(); i++) {
            CliPrinter.stamp(" ");
        }
        CliPrinter.stamp("┃\n");
        CliPrinter.stamp("\t\t\t\t\t ┃                                                          ┃\n");
        CliPrinter.stamp("\t\t\t\t\t ┃           Ip: ");
        CliSetUp.savePosition();
        CliPrinter.stamp("                                           ┃\n");
        CliPrinter.stamp("\t\t\t\t\t ┃                                                          ┃\n");
        CliPrinter.stamp("\t\t\t\t\t ┃                                                          ┃\n");
        CliPrinter.stamp("\t\t\t\t\t ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛\n");
        CliSetUp.restorePosition();
    }

    /**
     * Print the waiting box
     */
    static void boxWaitingMessage() {
        CliPrinter.stamp("\t\t\t\t\t ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓\n");
        CliPrinter.stamp("\t\t\t\t\t ┃                                                          ┃\n");
        CliPrinter.stamp("\t\t\t\t\t ┃                                                          ┃\n");
        CliPrinter.stamp("\t\t\t\t\t ┃               Waiting for other players...               ┃\n");
        CliPrinter.stamp("\t\t\t\t\t ┃                                                          ┃\n");
        CliPrinter.stamp("\t\t\t\t\t ┃                                                          ┃\n");
        CliPrinter.stamp("\t\t\t\t\t ┃                                                          ┃\n");
        CliPrinter.stamp("\t\t\t\t\t ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛\n");
    }

    /**
     * Print the lobby box
     * @param users in the lobby
     */
    static void boxLobbyMessage(List<String> users) {
        if (users.size() == 1) {
            CliPrinter.stamp("\t\t\t\t\t ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓\n");
            CliPrinter.stamp("\t\t\t\t\t ┃  " + users.get(0) + " joined the game");
            for (int i = 0; i < 40 - (users.get(0)).length(); i++) {
                CliPrinter.stamp(" ");
            }
            CliPrinter.stamp("┃\n");
            CliPrinter.stamp("\t\t\t\t\t ┃                                                          ┃\n");
            CliPrinter.stamp("\t\t\t\t\t ┃                                                          ┃\n");
            CliPrinter.stamp("\t\t\t\t\t ┃                                                          ┃\n");
            CliPrinter.stamp("\t\t\t\t\t ┃                                                          ┃\n");
            CliPrinter.stamp("\t\t\t\t\t ┃                                                          ┃\n");
            CliPrinter.stamp("\t\t\t\t\t ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛\n");
        }
        else if (users.size() == 2) {
            CliPrinter.stamp("\t\t\t\t\t ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓\n");
            CliPrinter.stamp("\t\t\t\t\t ┃  " + users.get(0) + " joined the game");
            for (int i = 0; i < 40 - (users.get(0)).length(); i++) {
                CliPrinter.stamp(" ");
            }
            CliPrinter.stamp("┃\n");
            CliPrinter.stamp("\t\t\t\t\t ┃  " + users.get(1) + " joined the game");
            for (int i = 0; i < 40 - (users.get(1)).length(); i++) {
                CliPrinter.stamp(" ");
            }
            CliPrinter.stamp("┃\n");
            CliPrinter.stamp("\t\t\t\t\t ┃                                                          ┃\n");
            CliPrinter.stamp("\t\t\t\t\t ┃                                                          ┃\n");
            CliPrinter.stamp("\t\t\t\t\t ┃                                                          ┃\n");
            CliPrinter.stamp("\t\t\t\t\t ┃                                                          ┃\n");
            CliPrinter.stamp("\t\t\t\t\t ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛\n");
        }
        else if (users.size() == 3) {
            CliPrinter.stamp("\t\t\t\t\t ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓\n");
            CliPrinter.stamp("\t\t\t\t\t ┃  " + users.get(0) + " joined the game");
            for (int i = 0; i < 40 - (users.get(0)).length(); i++) {
                CliPrinter.stamp(" ");
            }
            CliPrinter.stamp("┃\n");
            CliPrinter.stamp("\t\t\t\t\t ┃  " + users.get(1) + " joined the game");
            for (int i = 0; i < 40 - (users.get(1)).length(); i++) {
                CliPrinter.stamp(" ");
            }
            CliPrinter.stamp("┃\n");
            CliPrinter.stamp("\t\t\t\t\t ┃  " + users.get(2) + " joined the game");
            for (int i = 0; i < 40 - (users.get(2)).length(); i++) {
                CliPrinter.stamp(" ");
            }
            CliPrinter.stamp("┃\n");
            CliPrinter.stamp("\t\t\t\t\t ┃                                                          ┃\n");
            CliPrinter.stamp("\t\t\t\t\t ┃                                                          ┃\n");
            CliPrinter.stamp("\t\t\t\t\t ┃                                                          ┃\n");
            CliPrinter.stamp("\t\t\t\t\t ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛\n");
        }
        else if (users.size() == 4) {
            CliPrinter.stamp("\t\t\t\t\t ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓\n");
            CliPrinter.stamp("\t\t\t\t\t ┃  " + users.get(0) + " joined the game");
            for (int i = 0; i < 40 - (users.get(0)).length(); i++) {
                CliPrinter.stamp(" ");
            }
            CliPrinter.stamp("┃\n");
            CliPrinter.stamp("\t\t\t\t\t ┃  " + users.get(1) + " joined the game");
            for (int i = 0; i < 40 - (users.get(1)).length(); i++) {
                CliPrinter.stamp(" ");
            }
            CliPrinter.stamp("┃\n");
            CliPrinter.stamp("\t\t\t\t\t ┃  " + users.get(2) + " joined the game");
            for (int i = 0; i < 40 - (users.get(2)).length(); i++) {
                CliPrinter.stamp(" ");
            }
            CliPrinter.stamp("┃\n");
            CliPrinter.stamp("\t\t\t\t\t ┃  " + users.get(3) + " joined the game");
            for (int i = 0; i < 40 - (users.get(3)).length(); i++) {
                CliPrinter.stamp(" ");
            }
            CliPrinter.stamp("┃\n");
            CliPrinter.stamp("\t\t\t\t\t ┃                                                          ┃\n");
            CliPrinter.stamp("\t\t\t\t\t ┃                                                          ┃\n");
            CliPrinter.stamp("\t\t\t\t\t ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛\n");
        }
        else {
            CliPrinter.stamp("\t\t\t\t\t ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓\n");
            CliPrinter.stamp("\t\t\t\t\t ┃  " + users.get(0) + " joined the game");
            for (int i = 0; i < 40 - (users.get(0)).length(); i++) {
                CliPrinter.stamp(" ");
            }
            CliPrinter.stamp("┃\n");
            CliPrinter.stamp("\t\t\t\t\t ┃  " + users.get(1) + " joined the game");
            for (int i = 0; i < 40 - (users.get(1)).length(); i++) {
                CliPrinter.stamp(" ");
            }
            CliPrinter.stamp("┃\n");
            CliPrinter.stamp("\t\t\t\t\t ┃  " + users.get(2) + " joined the game");
            for (int i = 0; i < 40 - (users.get(2)).length(); i++) {
                CliPrinter.stamp(" ");
            }
            CliPrinter.stamp("┃\n");
            CliPrinter.stamp("\t\t\t\t\t ┃  " + users.get(3) + " joined the game");
            for (int i = 0; i < 40 - (users.get(3)).length(); i++) {
                CliPrinter.stamp(" ");
            }
            CliPrinter.stamp("┃\n");
            CliPrinter.stamp("\t\t\t\t\t ┃  " + users.get(4) + " joined the game");
            for (int i = 0; i < 40 - (users.get(4)).length(); i++) {
                CliPrinter.stamp(" ");
            }
            CliPrinter.stamp("┃\n");
            CliPrinter.stamp("\t\t\t\t\t ┃                                                          ┃\n");
            CliPrinter.stamp("\t\t\t\t\t ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛\n");
        }
    }

    /**
     * Print the countdown for the start of the game
     * @param count the remaining seconds
     */
    static void timerMessage(int count) {
        HandyFunctions.printConsole("\r\t\t\t\t\t                  The game will start in: "+count);
    }

    /**
     * Print the maps selection box
     * @param timer the remaing seconds
     * @param vote array containing the votes for each map
     */
    static void possibleMapsMessage(int timer, int[] vote) {
        CliPrinter.stamp("\t\t\t\t\t ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓\n");
        CliPrinter.stamp("\t\t\t\t\t ┃                      Choose the map                      ┃\n");
        CliPrinter.stamp("\t\t\t\t\t ┃                                                          ┃\n");
        CliPrinter.stamp("\t\t\t\t\t ┃    <1> 3-4-4(votes: "+vote[0]+")            <3> 3-4-3(votes: "+vote[2]+")    ┃\n");
        CliPrinter.stamp("\t\t\t\t\t ┃                                                          ┃\n");
        CliPrinter.stamp("\t\t\t\t\t ┃    <2> 4-4-3(votes: "+vote[1]+")            <4> 4-4-4(votes: "+vote[3]+")    ┃\n");
        CliPrinter.stamp("\t\t\t\t\t ┃                                                          ┃\n");
        CliPrinter.stamp("\t\t\t\t\t ┃            press the <key> followed by enter ");
        CliSetUp.savePosition();
        CliPrinter.stamp("            ┃\n");
        CliPrinter.stamp("\t\t\t\t\t ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛\n");
        CliPrinter.stamp("\t\t\t\t\t  Time left: "+timer);
        CliSetUp.restorePosition();
        CliPrinter.noResetStamp("c",CliColor.TEXTWHITE, CliColor.BACKWHITE);
        CliSetUp.cursorLeft(1);
    }

    /**
     * Print the standard action selection box
     */
    static void standardActionsMessage() {
        CliPrinter.stamp("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter.stamp("┃                                            Choose an action:                                           ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter.stamp("┃                                        <1> move                                                        ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter.stamp("┃                                        <2> grab                                                        ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter.stamp("┃                                        <3> shoot                                                       ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter.stamp("┃                                        <4> reload                                                      ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter.stamp("┃                                        <5> use power up                                                ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter.stamp("┃                                        <6> end turn                                                    ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter.stamp("┃                                        <7> convert power up                                            ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter.stamp("┃                                     press the <key> followed by enter                                  ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter.stamp("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
    }

    /**
     * Print the damaged action selection box
     */
    static void actionMessage1() {
        CliPrinter.stamp("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter.stamp("┃                                            Choose an action:                                           ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter.stamp("┃                                        <1> move                                                        ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter.stamp("┃                                        <2> grab (>>)                                                   ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter.stamp("┃                                        <3> shoot                                                       ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter.stamp("┃                                        <4> reload                                                      ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter.stamp("┃                                        <5> use power up                                                ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter.stamp("┃                                        <6> end turn                                                    ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter.stamp("┃                                        <7> convert power up                                            ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter.stamp("┃                                     press the <key> followed by enter                                  ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter.stamp("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
    }

    /**
     * Print the very damaged action selection box
     */
    static void actionMessage2() {
        CliPrinter.stamp("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter.stamp("┃                                            Choose an action:                                           ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter.stamp("┃                                        <1> move                                                        ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter.stamp("┃                                        <2> grab  (>>)                                                  ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter.stamp("┃                                        <3> shoot (>)                                                   ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter.stamp("┃                                        <4> reload                                                      ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter.stamp("┃                                        <5> use power up                                                ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter.stamp("┃                                        <6> end turn                                                    ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter.stamp("┃                                        <7> convert power up                                            ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter.stamp("┃                                     press the <key> followed by enter                                  ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter.stamp("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
    }

    /**
     * Print the frenzy mode 1 action selection box
     */
    static void actionMessage3() {
        CliPrinter.stamp("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter.stamp("┃                                            Choose an action:                                           ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter.stamp("┃                                        <1> shoot (> + reload)                                          ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter.stamp("┃                                        <2> move  (>>>>)                                                ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter.stamp("┃                                        <3> grab (>>)                                                   ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter.stamp("┃                                        <4> reload                                                      ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter.stamp("┃                                        <5> use power up                                                ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter.stamp("┃                                        <6> end turn                                                    ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter.stamp("┃                                        <7> convert power up                                            ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter.stamp("┃                                     press the <key> followed by enter                                  ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter.stamp("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
    }

    /**
     * Print the frenzy mode 2 action selection box
     */
    static void actionMessage4() {
        CliPrinter.stamp("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter.stamp("┃                                            Choose an action:                                           ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter.stamp("┃                                        <1> shoot (>> + reload)                                         ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter.stamp("┃                                        <2> grab  (>>>)                                                 ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter.stamp("┃                                        <4> reload                                                      ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter.stamp("┃                                        <5> use power up                                                ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter.stamp("┃                                        <6> end turn                                                    ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter.stamp("┃                                        <7> convert power up                                            ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter.stamp("┃                                                                                                        ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter.stamp("┃                                     press the <key> followed by enter                                  ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter.stamp("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
    }

    /**
     * Print the character selection box
     * @param timer the remaining seconds
     * @param chosen the characters already chosen
     * @param myChoice the character chosen by the current player
     */
    static void possibleCharMessage(int timer, ArrayList<String> chosen, int myChoice) {

        char cBan = ' ';
        char cSpr = ' ';
        char cDoz = ' ';
        char cVio = ' ';
        char cDis = ' ';
        if(chosen.contains("BANSHEE")) {
            cBan = '✖';
        }
        if(chosen.contains("SPROG")) {
            cSpr = '✖';
        }
        if(chosen.contains("DOZER")) {
            cDoz = '✖';
        }
        if(chosen.contains("VIOLET")) {
            cVio = '✖';
        }
        if(chosen.contains("DISTRUCTOR")) {
            cDis = '✖';
        }

        if(myChoice == 1) {
            cBan = '✔';
        }
        else if(myChoice ==2) {
            cSpr = '✔';
        }
        else if(myChoice ==3) {
            cDoz = '✔';
        }
        else if(myChoice ==4) {
            cVio = '✔';
        }
        else if(myChoice ==5) {
            cDis = '✔';
        }
        CliPrinter.stamp("\t\t\t\t\t ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓\n");
        CliPrinter.stamp("\t\t\t\t\t ┃                   Choose the character                   ┃\n");
        CliPrinter.stamp("\t\t\t\t\t ┃                                                          ┃\n");
        CliPrinter.stamp("\t\t\t\t\t ┃  <1> Banshee "+cBan+"          <2> Sprog "+cSpr+"         <3> Dozer "+cDoz+"  ┃\n");
        CliPrinter.stamp("\t\t\t\t\t ┃                                                          ┃\n");
        CliPrinter.stamp("\t\t\t\t\t ┃            <4> Violet "+cVio+"          <5> Distructor "+cDis+"        ┃\n");
        CliPrinter.stamp("\t\t\t\t\t ┃                                                          ┃\n");
        CliPrinter.stamp("\t\t\t\t\t ┃            press the <key> followed by enter ");
        CliSetUp.savePosition();
        CliPrinter.stamp("            ┃\n");
        CliPrinter.stamp("\t\t\t\t\t ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛\n");
        CliPrinter.stamp("\t\t\t\t\t  Time left: "+timer);
        CliSetUp.restorePosition();
        CliPrinter.noResetStamp("c",CliColor.TEXTWHITE, CliColor.BACKWHITE);
        CliSetUp.cursorLeft(1);
    }

    /**
     * Print weapons to buy box
     * @param color of the box
     * @param powWeapons all the box weapons
     */
    private static void weaponBox(CliColor color, List<CardRep> powWeapons) {
        CliSetUp.savePosition();
        CliPrinter.stamp("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓", color);
        CliSetUp.restorePosition();
        CliSetUp.cursorDown(1);
        CliSetUp.savePosition();
        CliPrinter.stamp("┃     "+powWeapons.get(0).getTitle(), color);
        for(int i=0; i < 24 - powWeapons.get(0).getTitle().length(); i++) {
            CliPrinter.stamp(" ");
        }
        CliPrinter.stamp("┃", color);
        CliSetUp.restorePosition();
        CliSetUp.cursorDown(1);
        CliSetUp.savePosition();
        CliPrinter.stamp("┃                             ┃", color);
        CliSetUp.restorePosition();
        CliSetUp.cursorDown(1);
        CliSetUp.savePosition();
        CliPrinter.stamp("┃     "+powWeapons.get(1).getTitle(), color);
        for(int i=0; i < 24 - powWeapons.get(1).getTitle().length(); i++) {
            CliPrinter.stamp(" ");
        }
        CliPrinter.stamp("┃", color);
        CliSetUp.restorePosition();
        CliSetUp.cursorDown(1);
        CliSetUp.savePosition();
        CliPrinter.stamp("┃                             ┃", color);
        CliSetUp.restorePosition();
        CliSetUp.cursorDown(1);
        CliSetUp.savePosition();
        CliPrinter.stamp("┃     "+powWeapons.get(2).getTitle(), color);
        for(int i=0; i < 24 - powWeapons.get(2).getTitle().length(); i++) {
            CliPrinter.stamp(" ");
        }
        CliPrinter.stamp("┃", color);
        CliSetUp.restorePosition();
        CliSetUp.cursorDown(1);
        CliSetUp.savePosition();
        CliPrinter.stamp("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛", color);
        CliSetUp.restorePosition();
        CliSetUp.cursorDown(2);

    }

    /**
     * Print the map 1
     * @param map the two-dimensional array that represents the map
     * @param ammoReps the ammunitions in platforms
     * @param posWeapons the weapons for weaponBox
     */
    static void printMap1(int[][] map, List<AmmoRep> ammoReps, Map<String,List<CardRep>> posWeapons) {

        CliMap1 map1 = new CliMap1();

        map1.plat1.setAmmo(ammoReps.get(0).getType());
        map1.plat2.setAmmo(ammoReps.get(1).getType());
        map1.plat5.setAmmo(ammoReps.get(5).getType());
        map1.plat6.setAmmo(ammoReps.get(6).getType());
        map1.plat7.setAmmo(ammoReps.get(7).getType());
        map1.plat8.setAmmo(ammoReps.get(8).getType());
        map1.plat9.setAmmo(ammoReps.get(9).getType());
        map1.plat10.setAmmo(ammoReps.get(10).getType());

        CliSetUp.savePosition();
        CliPrinter.stamp("\n\t\t\t\t\t");

        map1.stamp();

        CliSetUp.restorePosition();
        CliSetUp.cursorRight(2);
        weaponBox(CliColor.TEXTRED, posWeapons.get("1,0"));
        weaponBox(CliColor.TEXTBLUE, posWeapons.get("0,2"));
        weaponBox(CliColor.TEXTYELLOW, posWeapons.get("2,3"));
        CliSetUp.cursorRight(111);
        CliSetUp.cursorUp(24);
        drawPlayersInfoBox(null,null);
        CliSetUp.cursorDown(20);
    }

    /**
     * Print the map 2
     * @param map the two-dimensional array that represents the map
     * @param ammoReps the ammunitions in platforms
     * @param posWeapons the weapons for weaponBox
     */
    static void printMap2(int[][] map, List<AmmoRep> ammoReps, Map<String,List<CardRep>> posWeapons) {

        CliMap2 map2 = new CliMap2();

        map2.plat1.setAmmo(ammoReps.get(0).getType());
        map2.plat2.setAmmo(ammoReps.get(1).getType());
        map2.plat4.setAmmo(ammoReps.get(3).getType());
        map2.plat6.setAmmo(ammoReps.get(5).getType());
        map2.plat7.setAmmo(ammoReps.get(6).getType());
        map2.plat8.setAmmo(ammoReps.get(7).getType());
        map2.plat9.setAmmo(ammoReps.get(9).getType());
        map2.plat10.setAmmo(ammoReps.get(10).getType());

        CliSetUp.savePosition();
        CliPrinter.stamp("\n\t\t\t\t\t");

        map2.stamp();

        CliSetUp.restorePosition();
        CliSetUp.cursorRight(2);
        weaponBox(CliColor.TEXTRED, posWeapons.get("1,0"));
        weaponBox(CliColor.TEXTBLUE, posWeapons.get("0,2"));
        weaponBox(CliColor.TEXTYELLOW, posWeapons.get("2,3"));
        CliSetUp.cursorRight(111);
        CliSetUp.cursorUp(24);
        drawPlayersInfoBox(null,null);
        CliSetUp.cursorDown(20);
    }

    /**
     * Print the map 3
     * @param map the two-dimensional array that represents the map
     * @param ammoReps the ammunitions in platforms
     * @param posWeapons the weapons for weaponBox
     */
    static void printMap3(int[][] map, List<AmmoRep> ammoReps, Map<String,List<CardRep>> posWeapons) {

        CliMap3 map3 = new CliMap3();

        map3.plat1.setAmmo(ammoReps.get(0).getType());
        map3.plat2.setAmmo(ammoReps.get(1).getType());
        map3.plat5.setAmmo(ammoReps.get(5).getType());
        map3.plat6.setAmmo(ammoReps.get(6).getType());
        map3.plat7.setAmmo(ammoReps.get(7).getType());
        map3.plat8.setAmmo(ammoReps.get(9).getType());
        map3.plat9.setAmmo(ammoReps.get(10).getType());

        CliSetUp.savePosition();
        CliPrinter.stamp("\n\t\t\t\t\t");

        map3.stamp();

        CliSetUp.restorePosition();
        CliSetUp.cursorRight(2);
        weaponBox(CliColor.TEXTRED, posWeapons.get("1,0"));
        weaponBox(CliColor.TEXTBLUE, posWeapons.get("0,2"));
        weaponBox(CliColor.TEXTYELLOW, posWeapons.get("2,3"));
        CliSetUp.cursorRight(111);
        CliSetUp.cursorUp(24);
        drawPlayersInfoBox(null,null);
        CliSetUp.cursorDown(20);
    }

    /**
     * Print the map 4
     * @param map the two-dimensional array that represents the map
     * @param ammoReps the ammunitions in platforms
     * @param posWeapons the weapons for weaponBox
     */
    static void printMap4(int[][] map, List<AmmoRep> ammoReps, Map<String,List<CardRep>> posWeapons) {

        CliMap4 map4 = new CliMap4();

        map4.plat1.setAmmo(ammoReps.get(0).getType());
        map4.plat2.setAmmo(ammoReps.get(1).getType());
        map4.plat4.setAmmo(ammoReps.get(3).getType());
        map4.plat6.setAmmo(ammoReps.get(5).getType());
        map4.plat7.setAmmo(ammoReps.get(6).getType());
        map4.plat8.setAmmo(ammoReps.get(7).getType());
        map4.plat9.setAmmo(ammoReps.get(8).getType());
        map4.plat10.setAmmo(ammoReps.get(9).getType());
        map4.plat11.setAmmo(ammoReps.get(10).getType());

        CliSetUp.savePosition();
        CliPrinter.stamp("\n\t\t\t\t\t");

        map4.stamp();

        CliSetUp.restorePosition();
        CliSetUp.cursorRight(2);
        weaponBox(CliColor.TEXTRED, posWeapons.get("1,0"));
        weaponBox(CliColor.TEXTBLUE, posWeapons.get("0,2"));
        weaponBox(CliColor.TEXTYELLOW, posWeapons.get("2,3"));
        CliSetUp.cursorRight(111);
        CliSetUp.cursorUp(24);
        drawPlayersInfoBox(null, null);
        CliSetUp.cursorDown(20);
    }

    /**
     * Print the power up selection box
     * @param p1 the first powerup
     * @param p2 the second powerup
     */
    static void choosePowerUpMessage(CardRep p1, CardRep p2) {
        CliSetUp.cursorRight(2);
        CliPrinter.stamp("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓\n");
        CliSetUp.cursorRight(2);
        CliPrinter.stamp("┃                                             Choose a powerup:                                          ┃\n");
        CliSetUp.cursorRight(2);
        CliPrinter.stamp("┃                                                                                                        ┃\n");
        CliSetUp.cursorRight(2);
        CliPrinter.stamp("┃                                                                                                        ┃\n");
        CliSetUp.cursorRight(2);
        CliPrinter.stamp("┃                                      <1> " + p1.getTitle());
        for (int i=0; i < 62 - p1.getTitle().length(); i++) {
            CliPrinter.stamp(" ");
        }
        CliPrinter.stamp("┃\n");
        CliSetUp.cursorRight(2);
        CliPrinter.stamp("┃                                                                                                        ┃\n");
        CliSetUp.cursorRight(2);
        CliPrinter.stamp("┃                                      <2> " + p2.getTitle());
        for (int i=0; i < 62 - p2.getTitle().length(); i++) {
            CliPrinter.stamp(" ");
        }
        CliPrinter.stamp("┃\n");
        CliSetUp.cursorRight(2);
        CliPrinter.stamp("┃                                                                                                        ┃\n");
        CliSetUp.cursorRight(2);
        CliPrinter.stamp("┃                                                                                                        ┃\n");
        CliSetUp.cursorRight(2);
        CliPrinter.stamp("┃                                      press the <key> followed by enter                                 ┃\n");
        CliSetUp.cursorRight(2);
        CliPrinter.stamp("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛\n");
        CliSetUp.cursorUp(1);
    }

    /**
     * Print the power up selection box
     * @param powerUps arrayList of the possibile powerups
     */
    static void choosePowerUpMessage2(List<CardRep> powerUps) {
        CliSetUp.cursorRight(2);
        CliPrinter.stamp("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter.stamp("┃                                              Choose a powerUp :                                        ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter.stamp("┃                                                                                                        ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter.stamp("┃                                                                                                        ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter.stamp("┃                                                                                                        ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter.stamp("┃                                                                                                        ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter.stamp("┃                                                                                                        ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter.stamp("┃                                                                                                        ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter.stamp("┃                                                                                                        ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter.stamp("┃                                                                                                        ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter.stamp("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
        CliSetUp.cursorLeft(78);
        CliSetUp.cursorUp(6);
        int counter = 0;
        for (CardRep c: powerUps) {
                CliPrinter.stamp(c.getTitle());
                if (counter < 2) {
                    CliPrinter.stamp(", ");
                }
            counter++;
        }
        stamp(" <0,1,2>: ");
    }

    /**
     * Print the use powerUp selection box
     * @param lightGameVersion all game information
     * @param myChar the character chosen
     * @param powerUps all the powerups
     */
    static void usePowerUpMessage(LightGameVersion lightGameVersion, String myChar, List<String> powerUps) {

        Map<String, List<CardRep>> playerPowerUps = lightGameVersion.getPlayerPowerups();
        List<CardRep> myPowerUps = playerPowerUps.get(myChar);

        CliSetUp.cursorRight(2);
        CliPrinter.stamp("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter.stamp("┃                                           Choose a powerUp to use:                                     ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter.stamp("┃                                                                                                        ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter.stamp("┃                                                                                                        ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter.stamp("┃                                                                                                        ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter.stamp("┃                                                                                                        ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter.stamp("┃                                                                                                        ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter.stamp("┃                                                                                                        ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter.stamp("┃                                                                                                        ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter.stamp("┃                                                                                                        ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter.stamp("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
        CliSetUp.cursorLeft(78);
        CliSetUp.cursorUp(6);
        int counter = 0;
        for (CardRep c: myPowerUps) {
            if (powerUps.contains(Integer.toString(c.getId()))) {
                CliPrinter.stamp(c.getTitle());
                if (counter < 2) {
                    CliPrinter.stamp(", ");
                }
            }
            counter++;
        }
        stamp(" <0,1,2>: ");
    }

    /**
     * Print the convert powerUp selection box
     * @param lightGameVersion all game information
     * @param myChar the character chosen
     * @return -1, if myPowerUps.size() == 0, else 0
     */
    static int convertPowerUpMessage(LightGameVersion lightGameVersion, String myChar) {

        Map<String, List<CardRep>> playerPowerUps = lightGameVersion.getPlayerPowerups();
        List<CardRep> myPowerUps = playerPowerUps.get(myChar);
        if(myPowerUps.isEmpty())
            return -1;

        CliSetUp.cursorRight(2);
        CliPrinter.stamp("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter.stamp("┃                                             Choose a powerUp:                                          ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter.stamp("┃                                                                                                        ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter.stamp("┃                                                                                                        ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter.stamp("┃                                                                                                        ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter.stamp("┃                                                                                                        ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter.stamp("┃                                                                                                        ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter.stamp("┃                                                                                                        ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter.stamp("┃                                                                                                        ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter.stamp("┃                                                                                                        ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter.stamp("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
        CliSetUp.cursorLeft(78);
        CliSetUp.cursorUp(6);
        int counter = 0;
        for (CardRep c: myPowerUps) {
            CliPrinter.stamp(c.getTitle());
            if (counter < 2) {
                CliPrinter.stamp(", ");
            }
            counter++;
        }
        stamp(" <0,1,2>: ");
        return 0;
    }

    /**
     * Print message box
     * @param msg the message to print
     */
    static void boxMessage(String msg) {
        CliSetUp.savePosition();
        CliSetUp.cursorLeft(7);
        CliSetUp.cursorDown(2);
        CliPrinter.stamp("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter.stamp("┃                                                 Messages:                                              ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter.stamp("┃                                                                                                        ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter.stamp("┃                                                                                                        ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter.stamp("┃                                                                                                        ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter.stamp("┃                                                                                                        ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter.stamp("┃                                                                                                        ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter.stamp("┃                                                                                                        ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter.stamp("┃                                                                                                        ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter.stamp("┃                                                                                                        ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter.stamp("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
        CliSetUp.cursorLeft(100);
        CliSetUp.cursorUp(7);
        if (msg != null) {
            stamp(msg);
        }
        CliSetUp.restorePosition();
    }

    /**
     * Draws game information
     * @param lightGameVersion all game information
     */
    static void drawGameInfoBox(LightGameVersion lightGameVersion) {
        stamp("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
        CliSetUp.cursorLeft(61 - 25);
        CliSetUp.cursorDown(1);
        for (int i=0;i<45;i++) {
            stamp("┃");
            CliSetUp.cursorLeft(1);
            CliSetUp.cursorDown(1);
        }
        stamp("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
        CliSetUp.cursorUp(45);
        CliSetUp.cursorLeft(1);
        for (int i=0;i<45;i++) {
            stamp("┃");
            CliSetUp.cursorLeft(1);
            CliSetUp.cursorDown(1);
        }
        CliSetUp.cursorUp(45);
        CliSetUp.cursorLeft(23);
        CliPrinter.stamp("GAME'S INFO");
        CliSetUp.cursorDown(2);
        CliSetUp.cursorLeft(20);
        CliSetUp.savePosition();
        CliPrinter.stamp("Total skulls: " + lightGameVersion.getTotalSkulls());
        CliSetUp.restorePosition();
        CliSetUp.cursorDown(1);
        CliSetUp.savePosition();
        CliPrinter.stamp("Number of skulls: " + lightGameVersion.getSkullsNum());
        CliSetUp.restorePosition();
        CliSetUp.cursorDown(1);
        CliSetUp.savePosition();
        List<String> charactersThatKilled = lightGameVersion.getCharactersThatKilled();
        List<Integer> quantityOfMarks = lightGameVersion.getQuantityOfMarks();
        int counter = 0;
        int flag = 0;
        for (int i=0; i < charactersThatKilled.size(); i++) {
            if (charactersThatKilled.get(i).equals("BANSHEE")) {
                flag = 1;
                CliPrinter.stamp("▲", CliColor.TEXTBLUE);
                counter++;
                if (quantityOfMarks.get(i) == 2) {
                    CliPrinter.stamp("▲", CliColor.TEXTBLUE);
                }
            }
            if (charactersThatKilled.get(i).equals("SPROG")) {
                flag = 1;
                CliPrinter.stamp("▲", CliColor.TEXTGREEN);
                counter++;
                if (quantityOfMarks.get(i) == 2) {
                    CliPrinter.stamp("▲", CliColor.TEXTGREEN);
                }
            }
            if(charactersThatKilled.get(i).equals("DOZER")) {
                flag = 1;
                CliPrinter.stamp("▲", CliColor.TEXTWHITE);
                counter++;
                if (quantityOfMarks.get(i) == 2) {
                    CliPrinter.stamp("▲", CliColor.TEXTWHITE);
                }
            }
            if(charactersThatKilled.get(i).equals("VIOLET")) {
                flag = 1;
                CliPrinter.stamp("▲", CliColor.TEXTPURPLE);
                counter++;
                if (quantityOfMarks.get(i) == 2) {
                    CliPrinter.stamp("▲", CliColor.TEXTPURPLE);
                }
            }
            if(charactersThatKilled.get(i).equals("DISTRUCTOR")) {
                flag = 1;
                CliPrinter.stamp("▲", CliColor.TEXTYELLOW);
                counter++;
                if (quantityOfMarks.get(i) == 2) {
                    CliPrinter.stamp("▲", CliColor.TEXTYELLOW);
                }
            }
            CliSetUp.restorePosition();
            CliSetUp.cursorDown(1);
            CliSetUp.savePosition();
        }
        if (flag ==0)
            CliSetUp.restorePosition();
        else
            CliSetUp.cursorUp(counter);
    }

    /**
     * Draw all player information
     * @param lightGameVersion all game information
     * @param myChar the character chosen
     */
    static void drawPlayersInfoBox(LightGameVersion lightGameVersion, String myChar) {

        int playerCounter = 0;

        Map<String, List<CardRep>> playerPowerups;
        Map<String, List<CardRep>> playerWeapons;
        Map<String, BoardRep> playerBoardRep;

        BoardRep bansheeRep;
        BoardRep sprogRep;
        BoardRep dozerRep;
        BoardRep violetRep;
        BoardRep distructorRep;

        List<String> bansheeDamages;
        List<String> bansheemarks;
        Map<String,Integer> bansheeAmmos;
        List<CardRep> bansheePowerUp;
        List<CardRep> bansheeWeapons;

        List<String> sprogDamages;
        List<String> sprogmarks;
        Map<String,Integer> sprogAmmos;
        List<CardRep> sprogPowerUp;
        List<CardRep> sprogWeapons;

        List<String> dozerDamages;
        List<String> dozermarks;
        Map<String,Integer> dozerAmmos;
        List<CardRep> dozerPowerUp;
        List<CardRep> dozerWeapons;

        List<String> violetDamages;
        List<String> violetmarks;
        Map<String,Integer> violetAmmos;
        List<CardRep> violetPowerUp;
        List<CardRep> violetWeapons;

        List<String> distructorDamages;
        List<String> distructormarks;
        Map<String,Integer> distructorAmmos;
        List<CardRep> distructorPowerUp;
        List<CardRep> distructorWeapons;


        stamp("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
        CliSetUp.cursorLeft(61);
        CliSetUp.cursorDown(1);
        for (int i=0;i<45;i++) {
            stamp("┃");
            CliSetUp.cursorLeft(1);
            CliSetUp.cursorDown(1);
        }
        stamp("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
        CliSetUp.cursorUp(45);
        CliSetUp.cursorLeft(1);
        for (int i=0;i<45;i++) {
            stamp("┃");
            CliSetUp.cursorLeft(1);
            CliSetUp.cursorDown(1);
        }
        CliSetUp.cursorUp(45);
        CliSetUp.cursorLeft(36);
        CliPrinter.stamp("PLAYERS' INFO");
        CliSetUp.cursorDown(2);
        CliSetUp.cursorLeft(34);
        CliSetUp.savePosition(); // per poter scrivere sempre all'inizio del box, basta scendere di uno

        if (lightGameVersion != null) {
            playerPowerups = lightGameVersion.getPlayerPowerups();
            playerWeapons = lightGameVersion.getPlayerWeapons();
            playerBoardRep = lightGameVersion.getPlayerBoardRep();

            bansheeRep = playerBoardRep.get("BANSHEE");
            if (bansheeRep != null) {
                playerCounter++;

                CliPrinter.stamp("BANSHEE:", CliColor.TEXTBLUE);
                CliSetUp.restorePosition();
                CliSetUp.cursorDown(1);

                bansheeDamages = bansheeRep.getDamages();
                bansheemarks = bansheeRep.getMarks();
                bansheeAmmos = bansheeRep.getColorQtyAmmos();
                bansheePowerUp = playerPowerups.get("BANSHEE");
                bansheeWeapons = playerWeapons.get("BANSHEE");

                int redAmmos = bansheeAmmos.get("RED");
                int blueAmmos = bansheeAmmos.get("BLUE");
                int yellowAmmos = bansheeAmmos.get("YELLOW");

                CliSetUp.savePosition();
                CliPrinter.stamp("damages: ");
                for (String d: bansheeDamages) {
                    if (d.equals("BANSHEE"))
                        CliPrinter.stamp("▲", CliColor.TEXTBLUE);
                    if (d.equals("SPROG"))
                        CliPrinter.stamp("▲", CliColor.TEXTGREEN);
                    if(d.equals("DOZER"))
                        CliPrinter.stamp("▲", CliColor.TEXTWHITE);
                    if(d.equals("VIOLET"))
                        CliPrinter.stamp("▲", CliColor.TEXTPURPLE);
                    if(d.equals("DISTRUCTOR"))
                        CliPrinter.stamp("▲", CliColor.TEXTYELLOW);
                }

                CliSetUp.restorePosition();
                CliSetUp.cursorDown(1);
                CliSetUp.savePosition();
                CliPrinter.stamp("marks: ");
                for (String d: bansheemarks) {
                    if (d.equals("BANSHEE"))
                        CliPrinter.stamp("◀", CliColor.TEXTBLUE);
                    if (d.equals("SPROG"))
                        CliPrinter.stamp("◀", CliColor.TEXTGREEN);
                    if(d.equals("DOZER"))
                        CliPrinter.stamp("◀", CliColor.TEXTWHITE);
                    if(d.equals("VIOLET"))
                        CliPrinter.stamp("◀", CliColor.TEXTPURPLE);
                    if(d.equals("DISTRUCTOR"))
                        CliPrinter.stamp("◀", CliColor.TEXTYELLOW);
                }

                CliSetUp.restorePosition();
                CliSetUp.cursorDown(1);
                CliSetUp.savePosition();
                CliPrinter.stamp("Ammos: ");
                CliPrinter.stamp("■ x " + redAmmos + ", ",CliColor.TEXTRED);
                CliPrinter.stamp("■ x " + blueAmmos + ", ", CliColor.TEXTBLUE);
                CliPrinter.stamp("■ x "+ yellowAmmos, CliColor.TEXTYELLOW);

                CliSetUp.restorePosition();
                CliSetUp.cursorDown(1);
                CliSetUp.savePosition();
                CliPrinter.stamp("powerups: ");
                if (myChar.equals("BANSHEE")) {
                    for (CardRep c : bansheePowerUp) {
                        CliPrinter.stamp(c.getTitle() + ",");
                    }
                }
                CliSetUp.restorePosition();
                CliSetUp.cursorDown(1);
                CliSetUp.savePosition();
                CliPrinter.stamp("weapons: ");
                for (CardRep c: bansheeWeapons) {
                    CliPrinter.stamp(c.getTitle() + ",");
                }
                CliSetUp.restorePosition();
                CliSetUp.cursorDown(1);
                CliSetUp.savePosition();
                CliPrinter.stamp("Points: ");
                if (myChar.equals("BANSHEE")) {
                    HandyFunctions.printConsole(bansheeRep.getPoints());
                }
                CliSetUp.restorePosition();
                CliSetUp.cursorDown(1);
                CliSetUp.savePosition();
                CliPrinter.stamp("Skulls: " + bansheeRep.getSkullsNum());
                CliSetUp.restorePosition();

                CliSetUp.cursorDown(1);
                CliSetUp.savePosition();

            }

            sprogRep = playerBoardRep.get("SPROG");
            if(sprogRep != null) {
                playerCounter++;

                CliPrinter.stamp("SPROG:", CliColor.TEXTGREEN);
                CliSetUp.restorePosition();
                CliSetUp.cursorDown(1);

                sprogDamages = sprogRep.getDamages();
                sprogmarks = sprogRep.getMarks();
                sprogAmmos = sprogRep.getColorQtyAmmos();
                sprogPowerUp = playerPowerups.get("SPROG");
                sprogWeapons = playerWeapons.get("SPROG");

                int redAmmos = sprogAmmos.get("RED");
                int blueAmmos = sprogAmmos.get("BLUE");
                int yellowAmmos = sprogAmmos.get("YELLOW");

                CliSetUp.savePosition();
                CliPrinter.stamp("damages: ");
                for (String d: sprogDamages) {
                    if (d.equals("BANSHEE"))
                        CliPrinter.stamp("▲", CliColor.TEXTBLUE);
                    if (d.equals("SPROG"))
                        CliPrinter.stamp("▲", CliColor.TEXTGREEN);
                    if(d.equals("DOZER"))
                        CliPrinter.stamp("▲", CliColor.TEXTWHITE);
                    if(d.equals("VIOLET"))
                        CliPrinter.stamp("▲", CliColor.TEXTPURPLE);
                    if(d.equals("DISTRUCTOR"))
                        CliPrinter.stamp("▲", CliColor.TEXTYELLOW);
                }

                CliSetUp.restorePosition();
                CliSetUp.cursorDown(1);
                CliSetUp.savePosition();
                CliPrinter.stamp("marks: ");
                for (String d: sprogmarks) {
                    if (d.equals("BANSHEE"))
                        CliPrinter.stamp("◀", CliColor.TEXTBLUE);
                    if (d.equals("SPROG"))
                        CliPrinter.stamp("◀", CliColor.TEXTGREEN);
                    if(d.equals("DOZER"))
                        CliPrinter.stamp("◀", CliColor.TEXTWHITE);
                    if(d.equals("VIOLET"))
                        CliPrinter.stamp("◀", CliColor.TEXTPURPLE);
                    if(d.equals("DISTRUCTOR"))
                        CliPrinter.stamp("◀", CliColor.TEXTYELLOW);
                }

                CliSetUp.restorePosition();
                CliSetUp.cursorDown(1);
                CliSetUp.savePosition();
                CliPrinter.stamp("Ammos: ");
                CliPrinter.stamp("■ x " + redAmmos + ", ",CliColor.TEXTRED);
                CliPrinter.stamp("■ x " + blueAmmos + ", ", CliColor.TEXTBLUE);
                CliPrinter.stamp("■ x "+ yellowAmmos, CliColor.TEXTYELLOW);

                CliSetUp.restorePosition();
                CliSetUp.cursorDown(1);
                CliSetUp.savePosition();
                CliPrinter.stamp("powerups: ");
                if(myChar.equals("SPROG")) {
                    for (CardRep c : sprogPowerUp) {
                        CliPrinter.stamp(c.getTitle() + ",");
                    }
                }
                CliSetUp.restorePosition();
                CliSetUp.cursorDown(1);
                CliSetUp.savePosition();
                CliPrinter.stamp("weapons: ");
                for (CardRep c: sprogWeapons) {
                    CliPrinter.stamp(c.getTitle() + ",");
                }
                CliSetUp.restorePosition();
                CliSetUp.cursorDown(1);
                CliSetUp.savePosition();
                CliPrinter.stamp("Points: ");
                if (myChar.equals("SPROG")) {
                    HandyFunctions.printConsole(sprogRep.getPoints());
                }
                CliSetUp.restorePosition();
                CliSetUp.cursorDown(1);
                CliSetUp.savePosition();
                CliPrinter.stamp("Skulls: " + sprogRep.getSkullsNum());
                CliSetUp.restorePosition();

                CliSetUp.cursorDown(1);
                CliSetUp.savePosition();
            }

            dozerRep = playerBoardRep.get("DOZER");
            if (dozerRep != null) {
                playerCounter++;

                CliPrinter.stamp("DOZER:", CliColor.TEXTWHITE);
                CliSetUp.restorePosition();
                CliSetUp.cursorDown(1);

                dozerDamages = dozerRep.getDamages();
                dozermarks = dozerRep.getMarks();
                dozerAmmos = dozerRep.getColorQtyAmmos();
                dozerPowerUp = playerPowerups.get("DOZER");
                dozerWeapons = playerWeapons.get("DOZER");

                int redAmmos = dozerAmmos.get("RED");
                int blueAmmos = dozerAmmos.get("BLUE");
                int yellowAmmos = dozerAmmos.get("YELLOW");

                CliSetUp.savePosition();
                CliPrinter.stamp("damages: ");
                for (String d: dozerDamages) {
                    if (d.equals("BANSHEE"))
                        CliPrinter.stamp("▲", CliColor.TEXTBLUE);
                    if (d.equals("SPROG"))
                        CliPrinter.stamp("▲", CliColor.TEXTGREEN);
                    if(d.equals("DOZER"))
                        CliPrinter.stamp("▲", CliColor.TEXTWHITE);
                    if(d.equals("VIOLET"))
                        CliPrinter.stamp("▲", CliColor.TEXTPURPLE);
                    if(d.equals("DISTRUCTOR"))
                        CliPrinter.stamp("▲", CliColor.TEXTYELLOW);
                }

                CliSetUp.restorePosition();
                CliSetUp.cursorDown(1);
                CliSetUp.savePosition();
                CliPrinter.stamp("marks: ");
                for (String d: dozermarks) {
                    if (d.equals("BANSHEE"))
                        CliPrinter.stamp("◀", CliColor.TEXTBLUE);
                    if (d.equals("SPROG"))
                        CliPrinter.stamp("◀", CliColor.TEXTGREEN);
                    if(d.equals("DOZER"))
                        CliPrinter.stamp("◀", CliColor.TEXTWHITE);
                    if(d.equals("VIOLET"))
                        CliPrinter.stamp("◀", CliColor.TEXTPURPLE);
                    if(d.equals("DISTRUCTOR"))
                        CliPrinter.stamp("◀", CliColor.TEXTYELLOW);
                }

                CliSetUp.restorePosition();
                CliSetUp.cursorDown(1);
                CliSetUp.savePosition();
                CliPrinter.stamp("Ammos: ");
                CliPrinter.stamp("■ x " + redAmmos + ", ",CliColor.TEXTRED);
                CliPrinter.stamp("■ x " + blueAmmos + ", ", CliColor.TEXTBLUE);
                CliPrinter.stamp("■ x "+ yellowAmmos, CliColor.TEXTYELLOW);

                CliSetUp.restorePosition();
                CliSetUp.cursorDown(1);
                CliSetUp.savePosition();
                CliPrinter.stamp("powerups: ");
                if(myChar.equals("DOZER")) {
                    for (CardRep c : dozerPowerUp) {
                        CliPrinter.stamp(c.getTitle() + ",");
                    }
                }
                CliSetUp.restorePosition();
                CliSetUp.cursorDown(1);
                CliSetUp.savePosition();
                CliPrinter.stamp("weapons: ");
                for (CardRep c: dozerWeapons) {
                    CliPrinter.stamp(c.getTitle() + ",");
                }
                CliSetUp.restorePosition();
                CliSetUp.cursorDown(1);
                CliSetUp.savePosition();
                CliPrinter.stamp("Points: ");
                if (myChar.equals("DOZER")) {
                    HandyFunctions.printConsole(dozerRep.getPoints());
                }
                CliSetUp.restorePosition();
                CliSetUp.cursorDown(1);
                CliSetUp.savePosition();
                CliPrinter.stamp("Skulls: " + dozerRep.getSkullsNum());
                CliSetUp.restorePosition();

                CliSetUp.cursorDown(1);
                CliSetUp.savePosition();
            }

            violetRep = playerBoardRep.get("VIOLET");
            if (violetRep != null) {
                playerCounter++;

                CliPrinter.stamp("VIOLET:", CliColor.TEXTPURPLE);
                CliSetUp.restorePosition();
                CliSetUp.cursorDown(1);

                violetDamages = violetRep.getDamages();
                violetmarks = violetRep.getMarks();
                violetAmmos = violetRep.getColorQtyAmmos();
                violetPowerUp = playerPowerups.get("VIOLET");
                violetWeapons = playerWeapons.get("VIOLET");

                int redAmmos = violetAmmos.get("RED");
                int blueAmmos = violetAmmos.get("BLUE");
                int yellowAmmos = violetAmmos.get("YELLOW");

                CliSetUp.savePosition();
                CliPrinter.stamp("damages: ");
                for (String d: violetDamages) {
                    if (d.equals("BANSHEE"))
                        CliPrinter.stamp("▲", CliColor.TEXTBLUE);
                    if (d.equals("SPROG"))
                        CliPrinter.stamp("▲", CliColor.TEXTGREEN);
                    if(d.equals("DOZER"))
                        CliPrinter.stamp("▲", CliColor.TEXTWHITE);
                    if(d.equals("VIOLET"))
                        CliPrinter.stamp("▲", CliColor.TEXTPURPLE);
                    if(d.equals("DISTRUCTOR"))
                        CliPrinter.stamp("▲", CliColor.TEXTYELLOW);
                }

                CliSetUp.restorePosition();
                CliSetUp.cursorDown(1);
                CliSetUp.savePosition();
                CliPrinter.stamp("marks: ");
                for (String d: violetmarks) {
                    if (d.equals("BANSHEE"))
                        CliPrinter.stamp("◀", CliColor.TEXTBLUE);
                    if (d.equals("SPROG"))
                        CliPrinter.stamp("◀", CliColor.TEXTGREEN);
                    if(d.equals("DOZER"))
                        CliPrinter.stamp("◀", CliColor.TEXTWHITE);
                    if(d.equals("VIOLET"))
                        CliPrinter.stamp("◀", CliColor.TEXTPURPLE);
                    if(d.equals("DISTRUCTOR"))
                        CliPrinter.stamp("◀", CliColor.TEXTYELLOW);
                }

                CliSetUp.restorePosition();
                CliSetUp.cursorDown(1);
                CliSetUp.savePosition();
                CliPrinter.stamp("Ammos: ");
                CliPrinter.stamp("■ x " + redAmmos + ", ",CliColor.TEXTRED);
                CliPrinter.stamp("■ x " + blueAmmos + ", ", CliColor.TEXTBLUE);
                CliPrinter.stamp("■ x "+ yellowAmmos, CliColor.TEXTYELLOW);

                CliSetUp.restorePosition();
                CliSetUp.cursorDown(1);
                CliSetUp.savePosition();
                CliPrinter.stamp("powerups: ");
                if(myChar.equals("VIOLET")) {
                    for (CardRep c : violetPowerUp) {
                        CliPrinter.stamp(c.getTitle() + ",");
                    }
                }
                CliSetUp.restorePosition();
                CliSetUp.cursorDown(1);
                CliSetUp.savePosition();
                CliPrinter.stamp("weapons: ");
                for (CardRep c: violetWeapons) {
                    CliPrinter.stamp(c.getTitle() + ",");
                }
                CliSetUp.restorePosition();
                CliSetUp.cursorDown(1);
                CliSetUp.savePosition();
                CliPrinter.stamp("Points: ");
                if (myChar.equals("VIOLET")) {
                    HandyFunctions.printConsole(violetRep.getPoints());
                }
                CliSetUp.restorePosition();
                CliSetUp.cursorDown(1);
                CliSetUp.savePosition();
                CliPrinter.stamp("Skulls: " + violetRep.getSkullsNum());
                CliSetUp.restorePosition();

                CliSetUp.cursorDown(1);
                CliSetUp.savePosition();
            }

            distructorRep = playerBoardRep.get("DISTRUCTOR");
            if (distructorRep != null) {
                playerCounter++;

                CliPrinter.stamp("DISTRUCTOR:", CliColor.TEXTYELLOW);
                CliSetUp.restorePosition();
                CliSetUp.cursorDown(1);

                distructorDamages = distructorRep.getDamages();
                distructormarks = distructorRep.getMarks();
                distructorAmmos = distructorRep.getColorQtyAmmos();
                distructorPowerUp = playerPowerups.get("DISTRUCTOR");
                distructorWeapons = playerWeapons.get("DISTRUCTOR");

                int redAmmos = distructorAmmos.get("RED");
                int blueAmmos = distructorAmmos.get("BLUE");
                int yellowAmmos = distructorAmmos.get("YELLOW");

                CliSetUp.savePosition();
                CliPrinter.stamp("damages: ");
                for (String d: distructorDamages) {
                    if (d.equals("BANSHEE"))
                        CliPrinter.stamp("▲", CliColor.TEXTBLUE);
                    if (d.equals("SPROG"))
                        CliPrinter.stamp("▲", CliColor.TEXTGREEN);
                    if(d.equals("DOZER"))
                        CliPrinter.stamp("▲", CliColor.TEXTWHITE);
                    if(d.equals("VIOLET"))
                        CliPrinter.stamp("▲", CliColor.TEXTPURPLE);
                    if(d.equals("DISTRUCTOR"))
                        CliPrinter.stamp("▲", CliColor.TEXTYELLOW);
                }

                CliSetUp.restorePosition();
                CliSetUp.cursorDown(1);
                CliSetUp.savePosition();
                CliPrinter.stamp("marks: ");
                for (String d: distructormarks) {
                    if (d.equals("BANSHEE"))
                        CliPrinter.stamp("◀", CliColor.TEXTBLUE);
                    if (d.equals("SPROG"))
                        CliPrinter.stamp("◀", CliColor.TEXTGREEN);
                    if(d.equals("DOZER"))
                        CliPrinter.stamp("◀", CliColor.TEXTWHITE);
                    if(d.equals("VIOLET"))
                        CliPrinter.stamp("◀", CliColor.TEXTPURPLE);
                    if(d.equals("DISTRUCTOR"))
                        CliPrinter.stamp("◀", CliColor.TEXTYELLOW);
                }

                CliSetUp.restorePosition();
                CliSetUp.cursorDown(1);
                CliSetUp.savePosition();
                CliPrinter.stamp("Ammos: ");
                CliPrinter.stamp("■ x " + redAmmos + ", ",CliColor.TEXTRED);
                CliPrinter.stamp("■ x " + blueAmmos + ", ", CliColor.TEXTBLUE);
                CliPrinter.stamp("■ x "+ yellowAmmos, CliColor.TEXTYELLOW);

                CliSetUp.restorePosition();
                CliSetUp.cursorDown(1);
                CliSetUp.savePosition();
                CliPrinter.stamp("powerups: ");
                if(myChar.equals("DISTRUCTOR")) {
                    for (CardRep c : distructorPowerUp) {
                        CliPrinter.stamp(c.getTitle() + ",");
                    }
                }
                CliSetUp.restorePosition();
                CliSetUp.cursorDown(1);
                CliSetUp.savePosition();
                CliPrinter.stamp("weapons: ");
                for (CardRep c: distructorWeapons) {
                    CliPrinter.stamp(c.getTitle() + ",");
                }
                CliSetUp.restorePosition();
                CliSetUp.cursorDown(1);
                CliSetUp.savePosition();
                CliPrinter.stamp("Points: ");
                if (myChar.equals("DISTRUCTOR")) {
                    HandyFunctions.printConsole(distructorRep.getPoints());
                }
                CliSetUp.restorePosition();
                CliSetUp.cursorDown(1);
                CliSetUp.savePosition();
                CliPrinter.stamp("Skulls: " + distructorRep.getSkullsNum());
                CliSetUp.restorePosition();

                CliSetUp.cursorDown(1);
                CliSetUp.savePosition();
            }

            CliSetUp.cursorUp(8*playerCounter);
        }
    }

    /**
     * Print the discart ammo box
     * @param lightGameVersion all game information
     * @param myChar the character chosen
     * @return a list of possible ammos to discart
     */
    static List<String> discartAmmoMessage(LightGameVersion lightGameVersion, String myChar) {
        BoardRep mine = lightGameVersion.getPlayerBoardRep().get(myChar);
        List<String> ammoList = new ArrayList<>();
        CliSetUp.cursorRight(2);
        CliPrinter.stamp("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter.stamp("┃                                             Choose ammo to discart:                                    ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter.stamp("┃                                                                                                        ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter.stamp("┃                                                                                                        ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter.stamp("┃                                                                                                        ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter.stamp("┃                                                                                                        ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter.stamp("┃                                                                                                        ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter.stamp("┃                                                                                                        ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter.stamp("┃                                                                                                        ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter.stamp("┃                                                                                                        ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter.stamp("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
        CliSetUp.cursorLeft(78);
        CliSetUp.cursorUp(6);

        if (mine.getColorQtyAmmos().get("BLUE") > 0) {
           stamp("BLUE,");
           ammoList.add("BLUE");
        }
        if (mine.getColorQtyAmmos().get("RED") > 0) {
            stamp("RED,");
            ammoList.add("RED");
        }
        if (mine.getColorQtyAmmos().get("YELLOW") > 0) {
            stamp("YELLOW,");
            ammoList.add("YELLOW");
        }
        stamp(" <0,1,2>: ");
        return ammoList;
    }

    /**
     * Draw the discart weapon box
     * @param lightGameVersion all game information
     * @param myChar the character chosen
     */
    static void discartWeaponMessage(LightGameVersion lightGameVersion, String myChar) {
        CliSetUp.savePosition();
        Map<String, List<CardRep>> playerWeapons = lightGameVersion.getPlayerWeapons();
        List<CardRep> myWeapons = playerWeapons.get(myChar);

        CliSetUp.cursorRight(2);
        CliPrinter.stamp("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter.stamp("┃                                          Choose a weapon to discart:                                   ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter.stamp("┃                                                                                                        ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter.stamp("┃                                                                                                        ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter.stamp("┃                                                                                                        ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter.stamp("┃                                                                                                        ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter.stamp("┃                                                                                                        ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter.stamp("┃                                                                                                        ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter.stamp("┃                                                                                                        ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter.stamp("┃                                                                                                        ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter.stamp("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
        CliSetUp.cursorLeft(78);
        CliSetUp.cursorUp(6);
        int counter = 0;
        for (CardRep c: myWeapons) {
            CliPrinter.stamp(c.getTitle());
            if (counter <2) {
                CliPrinter.stamp(", ");
            }
            counter++;
        }
        stamp(" <0,1,2>: ");
    }

    /**
     * Draw the reload weapon box
     * @param lightGameVersion all game information
     * @param myChar the character chosen
     * @param weapons the possible weapons
     */
     static void reloadWeaponMessage(LightGameVersion lightGameVersion, String myChar, List<String> weapons) {
        CliSetUp.savePosition();
        Map<String, List<CardRep>> playerWeapons = lightGameVersion.getPlayerWeapons();
        List<CardRep> myWeapons = playerWeapons.get(myChar);
        CliSetUp.cursorRight(2);
        CliPrinter.stamp("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter.stamp("┃                                          Choose a weapon to reload:                                    ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter.stamp("┃                                                                                                        ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter.stamp("┃                                                                                                        ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter.stamp("┃                                                                                                        ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter.stamp("┃                                                                                                        ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter.stamp("┃                                                                                                        ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter.stamp("┃                                                                                                        ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter.stamp("┃                                                                                                        ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter.stamp("┃                                                                                                        ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter.stamp("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
        CliSetUp.cursorLeft(78);
        CliSetUp.cursorUp(6);
        int counter = 0;
        for (CardRep c: myWeapons) {
            if (weapons.contains(Integer.toString(c.getId()))) {
                CliPrinter.stamp(c.getTitle());
                if (counter < 2) {
                    CliPrinter.stamp(", ");
                }
            }
            counter++;
        }
        stamp(" <0,1,2>: ");
    }

    /**
     * Draw the choose weapon box
     * @param lightGameVersion all game information
     * @param myChar the character chosen
     * @param weapons the possible weapons
     */
    static void chooseWeaponMessage(LightGameVersion lightGameVersion, String myChar, List<String> weapons) {

        Map<String, List<CardRep>> playerWeapons = lightGameVersion.getPlayerWeapons();
        List<CardRep> myWeapons = playerWeapons.get(myChar);
        CliSetUp.cursorRight(2);
        CliPrinter.stamp("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter.stamp("┃                                               Choose a weapon:                                         ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter.stamp("┃                                                                                                        ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter.stamp("┃                                                                                                        ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter.stamp("┃                                                                                                        ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter.stamp("┃                                                                                                        ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter.stamp("┃                                                                                                        ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter.stamp("┃                                                                                                        ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter.stamp("┃                                                                                                        ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter.stamp("┃                                                                                                        ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter.stamp("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
        CliSetUp.cursorLeft(78);
        CliSetUp.cursorUp(6);
        int counter = 0;
        for (CardRep c: myWeapons) {
            if (weapons.contains(Integer.toString(c.getId()))) {
                CliPrinter.stamp(c.getTitle());
                if (counter < 2) {
                    CliPrinter.stamp(", ");
                }
            }
            counter++;
        }
        stamp(" <0,1,2>: ");

    }

    /**
     * Draw the possible target box
     * @param lightGameVersion  all game information
     * @param targets the possible targets
     */
    static void showTargetMessage(LightGameVersion lightGameVersion, List<String> targets) {
        CliSetUp.cursorRight(2);
        CliPrinter.stamp("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter.stamp("┃                                               Choose a target:                                         ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter.stamp("┃                                                                                                        ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter.stamp("┃                                                                                                        ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter.stamp("┃                                                                                                        ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter.stamp("┃                                                                                                        ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter.stamp("┃                                                                                                        ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter.stamp("┃                                                                                                        ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter.stamp("┃                                                                                                        ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter.stamp("┃                                                                                                        ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter.stamp("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
        CliSetUp.cursorLeft(78);
        CliSetUp.cursorUp(6);
        int counter = 0;
        for (String s: targets) {
            CliPrinter.stamp(s);
            if (counter < 2) {
                CliPrinter.stamp(", ");
            }
            counter++;
        }
        stamp(" <0,1,2,3,4>: ");
    }

    /**
     * Draw the possible effects box
     * @param effects all the possible effects
     */
    static void enlightenEffectsMessage(List<Integer> effects) {
        CliSetUp.cursorRight(2);
        CliPrinter.stamp("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter.stamp("┃                                              Choose an effect:                                         ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter.stamp("┃                                                                                                        ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter.stamp("┃                                                                                                        ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter.stamp("┃                                                                                                        ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter.stamp("┃                                                                                                        ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter.stamp("┃                                                                                                        ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter.stamp("┃                                                                                                        ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter.stamp("┃                                                                                                        ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter.stamp("┃                                                                                                        ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter.stamp("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
        CliSetUp.cursorLeft(78);
        CliSetUp.cursorUp(6);
        int counter = 0;
        for (Integer s: effects) {
            HandyFunctions.printConsole(s);
            if (counter < 2) {
                CliPrinter.stamp(", ");
            }
            counter++;
        }
        stamp(" : ");

    }

    /**
     * Print the weapon Box
     * @param lightGameVersion all game informations
     */
    static void printPlatformWeapons(LightGameVersion lightGameVersion) {
        CliPrinter.weaponBox(CliColor.TEXTRED, lightGameVersion.getPlatformWeapons().get("1,0"));
        CliPrinter.weaponBox(CliColor.TEXTBLUE,lightGameVersion.getPlatformWeapons().get("0,2"));
        CliPrinter.weaponBox(CliColor.TEXTYELLOW,lightGameVersion.getPlatformWeapons().get("2,3"));
    }

    /**
     * Print the possible platforms box
     * @param platforms all the possible platforms
     */
     static void printPossiblePlatform(List<String> platforms) {
        CliSetUp.cursorRight(2);
        CliPrinter.stamp("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter.stamp("┃                                             Choose a platform:                                         ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter.stamp("┃                                                                                                        ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter.stamp("┃                                                                                                        ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter.stamp("┃                                                                                                        ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter.stamp("┃                                                                                                        ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter.stamp("┃                                                                                                        ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter.stamp("┃                                                                                                        ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter.stamp("┃                                                                                                        ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter.stamp("┃                                                                                                        ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter.stamp("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
        CliSetUp.cursorLeft(78);
        CliSetUp.cursorUp(6);
        for (String s: platforms) {
            stamp("("+s+")"+ " ");
        }
        stamp(": ");
    }

    /**
     * Print the possible weapons box
     * @param lightGameVersion all game informations
     * @param weapons the possible weapons
     * @return a map with hashes of weapons
     */
    static Map<Integer, Integer> printPossibleWeapon(LightGameVersion lightGameVersion, List<String> weapons) {
        Map<String, List<CardRep>> platformWeapons = lightGameVersion.getPlatformWeapons();
        List<CardRep> plat1 = platformWeapons.get("0,2");
        List<CardRep> plat2 = platformWeapons.get("1,0");
        List<CardRep> plat3 = platformWeapons.get("2,3");
        Map<Integer, Integer> hashes = new HashMap<>();

        CliSetUp.cursorRight(2);
        CliPrinter.stamp("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter.stamp("┃                                              Choose a weapon:                                          ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter.stamp("┃                                                                                                        ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter.stamp("┃                                                                                                        ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter.stamp("┃                                                                                                        ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter.stamp("┃                                                                                                        ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter.stamp("┃                                                                                                        ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter.stamp("┃                                                                                                        ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter.stamp("┃                                                                                                        ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter.stamp("┃                                                                                                        ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter.stamp("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
        CliSetUp.cursorLeft(78);
        CliSetUp.cursorUp(6);
        int counter = 0;
        for (CardRep c: plat1) {
            for (String w: weapons) {
                if(c.getId() == Integer.parseInt(w)) {
                    CliPrinter.stamp(c.getTitle());
                    hashes.put(counter,c.getId());
                    if (counter < 2)
                        CliPrinter.stamp(", ");
                    counter ++;
                }
            }
        }
        for (CardRep c: plat2) {
            for (String w: weapons) {
                if(c.getId() == Integer.parseInt(w)) {
                    CliPrinter.stamp(c.getTitle());
                    hashes.put(counter,c.getId());
                    if (counter < 2)
                        CliPrinter.stamp(", ");
                    counter++;
                }
            }
        }
        for (CardRep c: plat3) {
            for (String w: weapons) {
                if(c.getId() == Integer.parseInt(w)) {
                    CliPrinter.stamp(c.getTitle());
                    hashes.put(counter,c.getId());
                    if (counter < 2)
                        CliPrinter.stamp(", ");
                    counter++;
                }
            }
        }
        stamp(" <0,1,2>: ");
        return hashes;
    }

    /**
     * Draw binary options box
     * @param msg the message to draw
     */
    static void binaryOptionMessage(String msg) {
        CliSetUp.cursorRight(2);
        CliPrinter.stamp("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter.stamp("┃                                                 Message:                                               ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter.stamp("┃                                                                                                        ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter.stamp("┃                                                                                                        ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter.stamp("┃                                                                                                        ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter.stamp("┃                                                                                                        ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter.stamp("┃                                                                                                        ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter.stamp("┃                                                                                                        ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter.stamp("┃                                                                                                        ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter.stamp("┃                                                                                                        ┃");
        CliSetUp.cursorLeft(106);
        CliSetUp.cursorDown(1);
        CliPrinter.stamp("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
        CliSetUp.cursorLeft(78);
        CliSetUp.cursorUp(6);
        CliPrinter.stamp(msg + "<y/n>: ");
    }

    /**
     * Draw the map chosen
     * @param lightGameVersion all game informations
     * @param choosenBoard the map chosen
     */
    static void printMap(LightGameVersion lightGameVersion, String choosenBoard) {
        if(choosenBoard.equals("1"))
            printLightMap1(lightGameVersion);
        else if(choosenBoard.equals("2"))
            printLightMap2(lightGameVersion);
        else if(choosenBoard.equals("3"))
            printLightMap3(lightGameVersion);
        else
            printLightMap4(lightGameVersion);
    }

    /**
     * Print the map 1
     * @param lightGameVersion all game informations
     */
    static void printLightMap1(LightGameVersion lightGameVersion) {
        CliMap1 map1 = new CliMap1();
        Map<String, AmmoRep> platformAmmoTile = lightGameVersion.getPlatformAmmoTile();
        Map<String, String> playerPlatform = lightGameVersion.getPlayerPlatform();
        String bansheePos = playerPlatform.get("BANSHEE");
        String sprogPos = playerPlatform.get("SPROG");
        String dozerPos = playerPlatform.get("DOZER");
        String violetPos = playerPlatform.get("VIOLET");
        String distructorPos = playerPlatform.get("DISTRUCTOR");

        if(bansheePos != null) {
            if (bansheePos.equals("0,0")) {
                map1.plat1.setBansheeInside();
            } else {
                map1.plat1.noBansheeInside();
            }
            if (bansheePos.equals("0,1")) {
                map1.plat2.setBansheeInside();
            } else {
                map1.plat2.noBansheeInside();
            }
            if (bansheePos.equals("0,2")) {
                map1.plat3.setBansheeInside();
            } else {
                map1.plat3.noBansheeInside();
            }
            if (bansheePos.equals("1,0")) {
                map1.plat4.setBansheeInside();
            } else {
                map1.plat4.noBansheeInside();
            }
            if (bansheePos.equals("1,1")) {
                map1.plat5.setBansheeInside();
            } else {
                map1.plat5.noBansheeInside();
            }
            if (bansheePos.equals("1,2")) {
                map1.plat6.setBansheeInside();
            } else {
                map1.plat6.noBansheeInside();
            }
            if (bansheePos.equals("1,3")) {
                map1.plat7.setBansheeInside();
            } else {
                map1.plat7.noBansheeInside();
            }
            if (bansheePos.equals("2,0")) {
                map1.plat8.setBansheeInside();
            } else {
                map1.plat8.noBansheeInside();
            }
            if (bansheePos.equals("2,1")) {
                map1.plat9.setBansheeInside();
            } else {
                map1.plat9.noBansheeInside();
            }
            if (bansheePos.equals("2,2")) {
                map1.plat10.setBansheeInside();
            } else {
                map1.plat10.noBansheeInside();
            }
            if (bansheePos.equals("2,3")) {
                map1.plat11.setBansheeInside();
            } else {
                map1.plat11.noBansheeInside();
            }
        }

        if (sprogPos != null) {
            if (sprogPos.equals("0,0")) {
                map1.plat1.setSprogInside();
            } else {
                map1.plat1.noSprogInside();
            }
            if (sprogPos.equals("0,1")) {
                map1.plat2.setSprogInside();
            } else {
                map1.plat2.noSprogInside();
            }
            if (sprogPos.equals("0,2")) {
                map1.plat3.setSprogInside();
            } else {
                map1.plat3.noSprogInside();
            }
            if (sprogPos.equals("1,0")) {
                map1.plat4.setSprogInside();
            } else {
                map1.plat4.noSprogInside();
            }
            if (sprogPos.equals("1,1")) {
                map1.plat5.setSprogInside();
            } else {
                map1.plat5.noSprogInside();
            }
            if (sprogPos.equals("1,2")) {
                map1.plat6.setSprogInside();
            } else {
                map1.plat6.noSprogInside();
            }
            if (sprogPos.equals("1,3")) {
                map1.plat7.setSprogInside();
            } else {
                map1.plat7.noSprogInside();
            }
            if (sprogPos.equals("2,0")) {
                map1.plat8.setSprogInside();
            } else {
                map1.plat8.noSprogInside();
            }
            if (sprogPos.equals("2,1")) {
                map1.plat9.setSprogInside();
            } else {
                map1.plat9.noSprogInside();
            }
            if (sprogPos.equals("2,2")) {
                map1.plat10.setSprogInside();
            } else {
                map1.plat10.noSprogInside();
            }
            if (sprogPos.equals("2,3")) {
                map1.plat11.setSprogInside();
            } else {
                map1.plat11.noSprogInside();
            }
        }

        if (dozerPos != null) {
            if (dozerPos.equals("0,0")) {
                map1.plat1.setDozerInside();
            } else {
                map1.plat1.noDozerInside();
            }
            if (dozerPos.equals("0,1")) {
                map1.plat2.setDozerInside();
            } else {
                map1.plat2.noDozerInside();
            }
            if (dozerPos.equals("0,2")) {
                map1.plat3.setDozerInside();
            } else {
                map1.plat3.noDozerInside();
            }
            if (dozerPos.equals("1,0")) {
                map1.plat4.setDozerInside();
            } else {
                map1.plat4.noDozerInside();
            }
            if (dozerPos.equals("1,1")) {
                map1.plat5.setDozerInside();
            } else {
                map1.plat5.noDozerInside();
            }
            if (dozerPos.equals("1,2")) {
                map1.plat6.setDozerInside();
            } else {
                map1.plat6.noDozerInside();
            }
            if (dozerPos.equals("1,3")) {
                map1.plat7.setDozerInside();
            } else {
                map1.plat7.noDozerInside();
            }
            if (dozerPos.equals("2,0")) {
                map1.plat8.setDozerInside();
            } else {
                map1.plat8.noDozerInside();
            }
            if (dozerPos.equals("2,1")) {
                map1.plat9.setDozerInside();
            } else {
                map1.plat9.noDozerInside();
            }
            if (dozerPos.equals("2,2")) {
                map1.plat10.setDozerInside();
            } else {
                map1.plat10.noDozerInside();
            }
            if (dozerPos.equals("2,3")) {
                map1.plat11.setDozerInside();
            } else {
                map1.plat11.noDozerInside();
            }
        }

        if (violetPos != null) {
            if (violetPos.equals("0,0")) {
                map1.plat1.setVioletInside();
            } else {
                map1.plat1.noVioletInside();
            }
            if (violetPos.equals("0,1")) {
                map1.plat2.setVioletInside();
            } else {
                map1.plat2.noVioletInside();
            }
            if (violetPos.equals("0,2")) {
                map1.plat3.setVioletInside();
            } else {
                map1.plat3.noVioletInside();
            }
            if (violetPos.equals("1,0")) {
                map1.plat4.setVioletInside();
            } else {
                map1.plat4.noVioletInside();
            }
            if (violetPos.equals("1,1")) {
                map1.plat5.setVioletInside();
            } else {
                map1.plat5.noVioletInside();
            }
            if (violetPos.equals("1,2")) {
                map1.plat6.setVioletInside();
            } else {
                map1.plat6.noVioletInside();
            }
            if (violetPos.equals("1,3")) {
                map1.plat7.setVioletInside();
            } else {
                map1.plat7.noVioletInside();
            }
            if (violetPos.equals("2,0")) {
                map1.plat8.setVioletInside();
            } else {
                map1.plat8.noVioletInside();
            }
            if (violetPos.equals("2,1")) {
                map1.plat9.setVioletInside();
            } else {
                map1.plat9.noVioletInside();
            }
            if (violetPos.equals("2,2")) {
                map1.plat10.setVioletInside();
            } else {
                map1.plat10.noVioletInside();
            }
            if (violetPos.equals("2,3")) {
                map1.plat11.setVioletInside();
            } else {
                map1.plat11.noVioletInside();
            }
        }

        if (distructorPos != null) {
            if (distructorPos.equals("0,0")) {
                map1.plat1.setDistructorInside();
            } else {
                map1.plat1.noDistructorInside();
            }
            if (distructorPos.equals("0,1")) {
                map1.plat2.setDistructorInside();
            } else {
                map1.plat2.noDistructorInside();
            }
            if (distructorPos.equals("0,2")) {
                map1.plat3.setDistructorInside();
            } else {
                map1.plat3.noDistructorInside();
            }
            if (distructorPos.equals("1,0")) {
                map1.plat4.setDistructorInside();
            } else {
                map1.plat4.noDistructorInside();
            }
            if (distructorPos.equals("1,1")) {
                map1.plat5.setDistructorInside();
            } else {
                map1.plat5.noDistructorInside();
            }
            if (distructorPos.equals("1,2")) {
                map1.plat6.setDistructorInside();
            } else {
                map1.plat6.noDistructorInside();
            }
            if (distructorPos.equals("1,3")) {
                map1.plat7.setDistructorInside();
            } else {
                map1.plat7.noDistructorInside();
            }
            if (distructorPos.equals("2,0")) {
                map1.plat8.setDistructorInside();
            } else {
                map1.plat8.noDistructorInside();
            }
            if (distructorPos.equals("2,1")) {
                map1.plat9.setDistructorInside();
            } else {
                map1.plat9.noDistructorInside();
            }
            if (distructorPos.equals("2,2")) {
                map1.plat10.setDistructorInside();
            } else {
                map1.plat10.noDistructorInside();
            }
            if (distructorPos.equals("2,3")) {
                map1.plat11.setDistructorInside();
            } else {
                map1.plat11.noDistructorInside();
            }
        }

        map1.plat1.setAmmo(platformAmmoTile.get("0,0").getType());
        map1.plat2.setAmmo(platformAmmoTile.get("0,1").getType());
        map1.plat5.setAmmo(platformAmmoTile.get("1,1").getType());
        map1.plat6.setAmmo(platformAmmoTile.get("1,2").getType());
        map1.plat7.setAmmo(platformAmmoTile.get("1,3").getType());
        map1.plat8.setAmmo(platformAmmoTile.get("2,0").getType());
        map1.plat9.setAmmo(platformAmmoTile.get("2,1").getType());
        map1.plat10.setAmmo(platformAmmoTile.get("2,2").getType());

        map1.stamp();
    }

    /**
     * Print the map 2
     * @param lightGameVersion all game informations
     */
    static void printLightMap2(LightGameVersion lightGameVersion) {
        CliMap2 map2 = new CliMap2();
        Map<String, AmmoRep> platformAmmoTile = lightGameVersion.getPlatformAmmoTile();
        Map<String, String> playerPlatform = lightGameVersion.getPlayerPlatform();
        String bansheePos = playerPlatform.get("BANSHEE");
        String sprogPos = playerPlatform.get("SPROG");
        String dozerPos = playerPlatform.get("DOZER");
        String violetPos = playerPlatform.get("VIOLET");
        String distructorPos = playerPlatform.get("DISTRUCTOR");

        if(bansheePos != null) {
            if (bansheePos.equals("0,0")) {
                map2.plat1.setBansheeInside();
            } else {
                map2.plat1.noBansheeInside();
            }
            if (bansheePos.equals("0,1")) {
                map2.plat2.setBansheeInside();
            } else {
                map2.plat2.noBansheeInside();
            }
            if (bansheePos.equals("0,2")) {
                map2.plat3.setBansheeInside();
            } else {
                map2.plat3.noBansheeInside();
            }
            if (bansheePos.equals("0,3")) {
                map2.plat4.setBansheeInside();
            } else {
                map2.plat4.noBansheeInside();
            }
            if (bansheePos.equals("1,0")) {
                map2.plat5.setBansheeInside();
            } else {
                map2.plat5.noBansheeInside();
            }
            if (bansheePos.equals("1,1")) {
                map2.plat6.setBansheeInside();
            } else {
                map2.plat6.noBansheeInside();
            }
            if (bansheePos.equals("1,2")) {
                map2.plat7.setBansheeInside();
            } else {
                map2.plat7.noBansheeInside();
            }
            if (bansheePos.equals("1,3")) {
                map2.plat8.setBansheeInside();
            } else {
                map2.plat8.noBansheeInside();
            }
            if (bansheePos.equals("2,1")) {
                map2.plat9.setBansheeInside();
            } else {
                map2.plat9.noBansheeInside();
            }
            if (bansheePos.equals("2,2")) {
                map2.plat10.setBansheeInside();
            } else {
                map2.plat10.noBansheeInside();
            }
            if (bansheePos.equals("2,3")) {
                map2.plat11.setBansheeInside();
            } else {
                map2.plat11.noBansheeInside();
            }
        }

        if (sprogPos != null) {
            if (sprogPos.equals("0,0")) {
                map2.plat1.setSprogInside();
            } else {
                map2.plat1.noSprogInside();
            }
            if (sprogPos.equals("0,1")) {
                map2.plat2.setSprogInside();
            } else {
                map2.plat2.noSprogInside();
            }
            if (sprogPos.equals("0,2")) {
                map2.plat3.setSprogInside();
            } else {
                map2.plat3.noSprogInside();
            }
            if (sprogPos.equals("0,3")) {
                map2.plat4.setSprogInside();
            } else {
                map2.plat4.noSprogInside();
            }
            if (sprogPos.equals("1,0")) {
                map2.plat5.setSprogInside();
            } else {
                map2.plat5.noSprogInside();
            }
            if (sprogPos.equals("1,1")) {
                map2.plat6.setSprogInside();
            } else {
                map2.plat6.noSprogInside();
            }
            if (sprogPos.equals("1,2")) {
                map2.plat7.setSprogInside();
            } else {
                map2.plat7.noSprogInside();
            }
            if (sprogPos.equals("1,3")) {
                map2.plat8.setSprogInside();
            } else {
                map2.plat8.noSprogInside();
            }
            if (sprogPos.equals("2,1")) {
                map2.plat9.setSprogInside();
            } else {
                map2.plat9.noSprogInside();
            }
            if (sprogPos.equals("2,2")) {
                map2.plat10.setSprogInside();
            } else {
                map2.plat10.noSprogInside();
            }
            if (sprogPos.equals("2,3")) {
                map2.plat11.setSprogInside();
            } else {
                map2.plat11.noSprogInside();
            }
        }

        if (dozerPos != null) {
            if (dozerPos.equals("0,0")) {
                map2.plat1.setDozerInside();
            } else {
                map2.plat1.noDozerInside();
            }
            if (dozerPos.equals("0,1")) {
                map2.plat2.setDozerInside();
            } else {
                map2.plat2.noDozerInside();
            }
            if (dozerPos.equals("0,2")) {
                map2.plat3.setDozerInside();
            } else {
                map2.plat3.noDozerInside();
            }
            if (dozerPos.equals("0,3")) {
                map2.plat4.setDozerInside();
            } else {
                map2.plat4.noDozerInside();
            }
            if (dozerPos.equals("1,0")) {
                map2.plat5.setDozerInside();
            } else {
                map2.plat5.noDozerInside();
            }
            if (dozerPos.equals("1,1")) {
                map2.plat6.setDozerInside();
            } else {
                map2.plat6.noDozerInside();
            }
            if (dozerPos.equals("1,2")) {
                map2.plat7.setDozerInside();
            } else {
                map2.plat7.noDozerInside();
            }
            if (dozerPos.equals("1,3")) {
                map2.plat8.setDozerInside();
            } else {
                map2.plat8.noDozerInside();
            }
            if (dozerPos.equals("2,1")) {
                map2.plat9.setDozerInside();
            } else {
                map2.plat9.noDozerInside();
            }
            if (dozerPos.equals("2,2")) {
                map2.plat10.setDozerInside();
            } else {
                map2.plat10.noDozerInside();
            }
            if (dozerPos.equals("2,3")) {
                map2.plat11.setDozerInside();
            } else {
                map2.plat11.noDozerInside();
            }
        }

        if (violetPos != null) {
            if (violetPos.equals("0,0")) {
                map2.plat1.setVioletInside();
            } else {
                map2.plat1.noVioletInside();
            }
            if (violetPos.equals("0,1")) {
                map2.plat2.setVioletInside();
            } else {
                map2.plat2.noVioletInside();
            }
            if (violetPos.equals("0,2")) {
                map2.plat3.setVioletInside();
            } else {
                map2.plat3.noVioletInside();
            }
            if (violetPos.equals("0,3")) {
                map2.plat4.setVioletInside();
            } else {
                map2.plat4.noVioletInside();
            }
            if (violetPos.equals("1,0")) {
                map2.plat5.setVioletInside();
            } else {
                map2.plat5.noVioletInside();
            }
            if (violetPos.equals("1,1")) {
                map2.plat6.setVioletInside();
            } else {
                map2.plat6.noVioletInside();
            }
            if (violetPos.equals("1,2")) {
                map2.plat7.setVioletInside();
            } else {
                map2.plat7.noVioletInside();
            }
            if (violetPos.equals("1,3")) {
                map2.plat8.setVioletInside();
            } else {
                map2.plat8.noVioletInside();
            }
            if (violetPos.equals("2,1")) {
                map2.plat9.setVioletInside();
            } else {
                map2.plat9.noVioletInside();
            }
            if (violetPos.equals("2,2")) {
                map2.plat10.setVioletInside();
            } else {
                map2.plat10.noVioletInside();
            }
            if (violetPos.equals("2,3")) {
                map2.plat11.setVioletInside();
            } else {
                map2.plat11.noVioletInside();
            }
        }

        if (distructorPos != null) {
            if (distructorPos.equals("0,0")) {
                map2.plat1.setDistructorInside();
            } else {
                map2.plat1.noDistructorInside();
            }
            if (distructorPos.equals("0,1")) {
                map2.plat2.setDistructorInside();
            } else {
                map2.plat2.noDistructorInside();
            }
            if (distructorPos.equals("0,2")) {
                map2.plat3.setDistructorInside();
            } else {
                map2.plat3.noDistructorInside();
            }
            if (distructorPos.equals("0,3")) {
                map2.plat4.setDistructorInside();
            } else {
                map2.plat4.noDistructorInside();
            }
            if (distructorPos.equals("1,0")) {
                map2.plat5.setDistructorInside();
            } else {
                map2.plat5.noDistructorInside();
            }
            if (distructorPos.equals("1,1")) {
                map2.plat6.setDistructorInside();
            } else {
                map2.plat6.noDistructorInside();
            }
            if (distructorPos.equals("1,2")) {
                map2.plat7.setDistructorInside();
            } else {
                map2.plat7.noDistructorInside();
            }
            if (distructorPos.equals("1,3")) {
                map2.plat8.setDistructorInside();
            } else {
                map2.plat8.noDistructorInside();
            }
            if (distructorPos.equals("2,1")) {
                map2.plat9.setDistructorInside();
            } else {
                map2.plat9.noDistructorInside();
            }
            if (distructorPos.equals("2,2")) {
                map2.plat10.setDistructorInside();
            } else {
                map2.plat10.noDistructorInside();
            }
            if (distructorPos.equals("2,3")) {
                map2.plat11.setDistructorInside();
            } else {
                map2.plat11.noDistructorInside();
            }
        }

        map2.plat1.setAmmo(platformAmmoTile.get("0,0").getType());
        map2.plat2.setAmmo(platformAmmoTile.get("0,1").getType());
        map2.plat4.setAmmo(platformAmmoTile.get("0,3").getType());
        map2.plat6.setAmmo(platformAmmoTile.get("1,1").getType());
        map2.plat7.setAmmo(platformAmmoTile.get("1,2").getType());
        map2.plat8.setAmmo(platformAmmoTile.get("1,3").getType());
        map2.plat9.setAmmo(platformAmmoTile.get("2,1").getType());
        map2.plat10.setAmmo(platformAmmoTile.get("2,2").getType());

        map2.stamp();
    }

    /**
     * Print map 3
     * @param lightGameVersion all game informatins
     */
    static void printLightMap3(LightGameVersion lightGameVersion) {
        CliMap3 map3 = new CliMap3();
        Map<String, AmmoRep> platformAmmoTile = lightGameVersion.getPlatformAmmoTile();
        Map<String, String> playerPlatform = lightGameVersion.getPlayerPlatform();
        String bansheePos = playerPlatform.get("BANSHEE");
        String sprogPos = playerPlatform.get("SPROG");
        String dozerPos = playerPlatform.get("DOZER");
        String violetPos = playerPlatform.get("VIOLET");
        String distructorPos = playerPlatform.get("DISTRUCTOR");

        if(bansheePos != null) {
            if (bansheePos.equals("0,0")) {
                map3.plat1.setBansheeInside();
            } else {
                map3.plat1.noBansheeInside();
            }
            if (bansheePos.equals("0,1")) {
                map3.plat2.setBansheeInside();
            } else {
                map3.plat2.noBansheeInside();
            }
            if (bansheePos.equals("0,2")) {
                map3.plat3.setBansheeInside();
            } else {
                map3.plat3.noBansheeInside();
            }
            if (bansheePos.equals("1,0")) {
                map3.plat4.setBansheeInside();
            } else {
                map3.plat4.noBansheeInside();
            }
            if (bansheePos.equals("1,1")) {
                map3.plat5.setBansheeInside();
            } else {
                map3.plat5.noBansheeInside();
            }
            if (bansheePos.equals("1,2")) {
                map3.plat6.setBansheeInside();
            } else {
                map3.plat6.noBansheeInside();
            }
            if (bansheePos.equals("1,3")) {
                map3.plat7.setBansheeInside();
            } else {
                map3.plat7.noBansheeInside();
            }
            if (bansheePos.equals("2,1")) {
                map3.plat8.setBansheeInside();
            } else {
                map3.plat8.noBansheeInside();
            }
            if (bansheePos.equals("2,2")) {
                map3.plat9.setBansheeInside();
            } else {
                map3.plat9.noBansheeInside();
            }
            if (bansheePos.equals("2,3")) {
                map3.plat10.setBansheeInside();
            } else {
                map3.plat10.noBansheeInside();
            }
        }

        if (sprogPos != null) {
            if (sprogPos.equals("0,0")) {
                map3.plat1.setSprogInside();
            } else {
                map3.plat1.noSprogInside();
            }
            if (sprogPos.equals("0,1")) {
                map3.plat2.setSprogInside();
            } else {
                map3.plat2.noSprogInside();
            }
            if (sprogPos.equals("0,2")) {
                map3.plat3.setSprogInside();
            } else {
                map3.plat3.noSprogInside();
            }
            if (sprogPos.equals("1,0")) {
                map3.plat4.setSprogInside();
            } else {
                map3.plat4.noSprogInside();
            }
            if (sprogPos.equals("1,1")) {
                map3.plat5.setSprogInside();
            } else {
                map3.plat5.noSprogInside();
            }
            if (sprogPos.equals("1,2")) {
                map3.plat6.setSprogInside();
            } else {
                map3.plat6.noSprogInside();
            }
            if (sprogPos.equals("1,3")) {
                map3.plat7.setSprogInside();
            } else {
                map3.plat7.noSprogInside();
            }
            if (sprogPos.equals("2,1")) {
                map3.plat8.setSprogInside();
            } else {
                map3.plat8.noSprogInside();
            }
            if (sprogPos.equals("2,2")) {
                map3.plat9.setSprogInside();
            } else {
                map3.plat9.noSprogInside();
            }
            if (sprogPos.equals("2,3")) {
                map3.plat10.setSprogInside();
            } else {
                map3.plat10.noSprogInside();
            }
        }

        if (dozerPos != null) {
            if (dozerPos.equals("0,0")) {
                map3.plat1.setDozerInside();
            } else {
                map3.plat1.noDozerInside();
            }
            if (dozerPos.equals("0,1")) {
                map3.plat2.setDozerInside();
            } else {
                map3.plat2.noDozerInside();
            }
            if (dozerPos.equals("0,2")) {
                map3.plat3.setDozerInside();
            } else {
                map3.plat3.noDozerInside();
            }
            if (dozerPos.equals("1,0")) {
                map3.plat4.setDozerInside();
            } else {
                map3.plat4.noDozerInside();
            }
            if (dozerPos.equals("1,1")) {
                map3.plat5.setDozerInside();
            } else {
                map3.plat5.noDozerInside();
            }
            if (dozerPos.equals("1,2")) {
                map3.plat6.setDozerInside();
            } else {
                map3.plat6.noDozerInside();
            }
            if (dozerPos.equals("1,3")) {
                map3.plat7.setDozerInside();
            } else {
                map3.plat7.noDozerInside();
            }
            if (dozerPos.equals("2,1")) {
                map3.plat8.setDozerInside();
            } else {
                map3.plat8.noDozerInside();
            }
            if (dozerPos.equals("2,2")) {
                map3.plat9.setDozerInside();
            } else {
                map3.plat9.noDozerInside();
            }
            if (dozerPos.equals("2,3")) {
                map3.plat10.setDozerInside();
            } else {
                map3.plat10.noDozerInside();
            }
        }

        if (violetPos != null) {
            if (violetPos.equals("0,0")) {
                map3.plat1.setVioletInside();
            } else {
                map3.plat1.noVioletInside();
            }
            if (violetPos.equals("0,1")) {
                map3.plat2.setVioletInside();
            } else {
                map3.plat2.noVioletInside();
            }
            if (violetPos.equals("0,2")) {
                map3.plat3.setVioletInside();
            } else {
                map3.plat3.noVioletInside();
            }
            if (violetPos.equals("1,0")) {
                map3.plat4.setVioletInside();
            } else {
                map3.plat4.noVioletInside();
            }
            if (violetPos.equals("1,1")) {
                map3.plat5.setVioletInside();
            } else {
                map3.plat5.noVioletInside();
            }
            if (violetPos.equals("1,2")) {
                map3.plat6.setVioletInside();
            } else {
                map3.plat6.noVioletInside();
            }
            if (violetPos.equals("1,3")) {
                map3.plat7.setVioletInside();
            } else {
                map3.plat7.noVioletInside();
            }
            if (violetPos.equals("2,1")) {
                map3.plat8.setVioletInside();
            } else {
                map3.plat8.noVioletInside();
            }
            if (violetPos.equals("2,2")) {
                map3.plat9.setVioletInside();
            } else {
                map3.plat9.noVioletInside();
            }
            if (violetPos.equals("2,3")) {
                map3.plat10.setVioletInside();
            } else {
                map3.plat10.noVioletInside();
            }
        }

        if (distructorPos != null) {
            if (distructorPos.equals("0,0")) {
                map3.plat1.setDistructorInside();
            } else {
                map3.plat1.noDistructorInside();
            }
            if (distructorPos.equals("0,1")) {
                map3.plat2.setDistructorInside();
            } else {
                map3.plat2.noDistructorInside();
            }
            if (distructorPos.equals("0,2")) {
                map3.plat3.setDistructorInside();
            } else {
                map3.plat3.noDistructorInside();
            }
            if (distructorPos.equals("1,0")) {
                map3.plat4.setDistructorInside();
            } else {
                map3.plat4.noDistructorInside();
            }
            if (distructorPos.equals("1,1")) {
                map3.plat5.setDistructorInside();
            } else {
                map3.plat5.noDistructorInside();
            }
            if (distructorPos.equals("1,2")) {
                map3.plat6.setDistructorInside();
            } else {
                map3.plat6.noDistructorInside();
            }
            if (distructorPos.equals("1,3")) {
                map3.plat7.setDistructorInside();
            } else {
                map3.plat7.noDistructorInside();
            }
            if (distructorPos.equals("2,1")) {
                map3.plat8.setDistructorInside();
            } else {
                map3.plat8.noDistructorInside();
            }
            if (distructorPos.equals("2,2")) {
                map3.plat9.setDistructorInside();
            } else {
                map3.plat9.noDistructorInside();
            }
            if (distructorPos.equals("2,3")) {
                map3.plat10.setDistructorInside();
            } else {
                map3.plat10.noDistructorInside();
            }
        }

        map3.plat1.setAmmo(platformAmmoTile.get("0,0").getType());
        map3.plat2.setAmmo(platformAmmoTile.get("0,1").getType());
        map3.plat5.setAmmo(platformAmmoTile.get("1,1").getType());
        map3.plat6.setAmmo(platformAmmoTile.get("1,2").getType());
        map3.plat7.setAmmo(platformAmmoTile.get("1,3").getType());
        map3.plat8.setAmmo(platformAmmoTile.get("2,1").getType());
        map3.plat9.setAmmo(platformAmmoTile.get("2,2").getType());

        map3.stamp();
    }

    /**
     * Print the map 4
     * @param lightGameVersion all game informations
     */
    static void printLightMap4(LightGameVersion lightGameVersion) {
        CliMap4 map4 = new CliMap4();
        Map<String, AmmoRep> platformAmmoTile = lightGameVersion.getPlatformAmmoTile();
        Map<String, String> playerPlatform = lightGameVersion.getPlayerPlatform();
        String bansheePos = playerPlatform.get("BANSHEE");
        String sprogPos = playerPlatform.get("SPROG");
        String dozerPos = playerPlatform.get("DOZER");
        String violetPos = playerPlatform.get("VIOLET");
        String distructorPos = playerPlatform.get("DISTRUCTOR");

        if(bansheePos != null) {
            if (bansheePos.equals("0,0")) {
                map4.plat1.setBansheeInside();
            } else {
                map4.plat1.noBansheeInside();
            }
            if (bansheePos.equals("0,1")) {
                map4.plat2.setBansheeInside();
            } else {
                map4.plat2.noBansheeInside();
            }
            if (bansheePos.equals("0,2")) {
                map4.plat3.setBansheeInside();
            } else {
                map4.plat3.noBansheeInside();
            }
            if (bansheePos.equals("0,3")) {
                map4.plat4.setBansheeInside();
            } else {
                map4.plat4.noBansheeInside();
            }
            if (bansheePos.equals("1,0")) {
                map4.plat5.setBansheeInside();
            } else {
                map4.plat5.noBansheeInside();
            }
            if (bansheePos.equals("1,1")) {
                map4.plat6.setBansheeInside();
            } else {
                map4.plat6.noBansheeInside();
            }
            if (bansheePos.equals("1,2")) {
                map4.plat7.setBansheeInside();
            } else {
                map4.plat7.noBansheeInside();
            }
            if (bansheePos.equals("1,3")) {
                map4.plat8.setBansheeInside();
            } else {
                map4.plat8.noBansheeInside();
            }
            if (bansheePos.equals("2,0")) {
                map4.plat9.setBansheeInside();
            } else {
                map4.plat9.noBansheeInside();
            }
            if (bansheePos.equals("2,1")) {
                map4.plat10.setBansheeInside();
            } else {
                map4.plat10.noBansheeInside();
            }
            if (bansheePos.equals("2,2")) {
                map4.plat11.setBansheeInside();
            } else {
                map4.plat11.noBansheeInside();
            }
            if (bansheePos.equals("2,3")) {
                map4.plat12.setBansheeInside();
            } else {
                map4.plat12.noBansheeInside();
            }
        }

        if (sprogPos != null) {
            if (sprogPos.equals("0,0")) {
                map4.plat1.setSprogInside();
            } else {
                map4.plat1.noSprogInside();
            }
            if (sprogPos.equals("0,1")) {
                map4.plat2.setSprogInside();
            } else {
                map4.plat2.noSprogInside();
            }
            if (sprogPos.equals("0,2")) {
                map4.plat3.setSprogInside();
            } else {
                map4.plat3.noSprogInside();
            }
            if (sprogPos.equals("0,3")) {
                map4.plat4.setSprogInside();
            } else {
                map4.plat4.noSprogInside();
            }
            if (sprogPos.equals("1,0")) {
                map4.plat5.setSprogInside();
            } else {
                map4.plat5.noSprogInside();
            }
            if (sprogPos.equals("1,1")) {
                map4.plat6.setSprogInside();
            } else {
                map4.plat6.noSprogInside();
            }
            if (sprogPos.equals("1,2")) {
                map4.plat7.setSprogInside();
            } else {
                map4.plat7.noSprogInside();
            }
            if (sprogPos.equals("1,3")) {
                map4.plat8.setSprogInside();
            } else {
                map4.plat8.noSprogInside();
            }
            if (sprogPos.equals("2,0")) {
                map4.plat9.setSprogInside();
            } else {
                map4.plat9.noSprogInside();
            }
            if (sprogPos.equals("2,1")) {
                map4.plat10.setSprogInside();
            } else {
                map4.plat10.noSprogInside();
            }
            if (sprogPos.equals("2,2")) {
                map4.plat11.setSprogInside();
            } else {
                map4.plat11.noSprogInside();
            }
            if (sprogPos.equals("2,3")) {
                map4.plat12.setSprogInside();
            } else {
                map4.plat12.noSprogInside();
            }
        }

        if (dozerPos != null) {
            if (dozerPos.equals("0,0")) {
                map4.plat1.setDozerInside();
            } else {
                map4.plat1.noDozerInside();
            }
            if (dozerPos.equals("0,1")) {
                map4.plat2.setDozerInside();
            } else {
                map4.plat2.noDozerInside();
            }
            if (dozerPos.equals("0,2")) {
                map4.plat3.setDozerInside();
            } else {
                map4.plat3.noDozerInside();
            }
            if (dozerPos.equals("0,3")) {
                map4.plat4.setDozerInside();
            } else {
                map4.plat4.noDozerInside();
            }
            if (dozerPos.equals("1,0")) {
                map4.plat5.setDozerInside();
            } else {
                map4.plat5.noDozerInside();
            }
            if (dozerPos.equals("1,1")) {
                map4.plat6.setDozerInside();
            } else {
                map4.plat6.noDozerInside();
            }
            if (dozerPos.equals("1,2")) {
                map4.plat7.setDozerInside();
            } else {
                map4.plat7.noDozerInside();
            }
            if (dozerPos.equals("1,3")) {
                map4.plat8.setDozerInside();
            } else {
                map4.plat8.noDozerInside();
            }
            if (dozerPos.equals("2,0")) {
                map4.plat9.setDozerInside();
            } else {
                map4.plat9.noDozerInside();
            }
            if (dozerPos.equals("2,1")) {
                map4.plat10.setDozerInside();
            } else {
                map4.plat10.noDozerInside();
            }
            if (dozerPos.equals("2,2")) {
                map4.plat11.setDozerInside();
            } else {
                map4.plat11.noDozerInside();
            }
            if (dozerPos.equals("2,3")) {
                map4.plat12.setDozerInside();
            } else {
                map4.plat12.noDozerInside();
            }
        }

        if (violetPos != null) {
            if (violetPos.equals("0,0")) {
                map4.plat1.setVioletInside();
            } else {
                map4.plat1.noVioletInside();
            }
            if (violetPos.equals("0,1")) {
                map4.plat2.setVioletInside();
            } else {
                map4.plat2.noVioletInside();
            }
            if (violetPos.equals("0,2")) {
                map4.plat3.setVioletInside();
            } else {
                map4.plat3.noVioletInside();
            }
            if (violetPos.equals("0,3")) {
                map4.plat4.setVioletInside();
            } else {
                map4.plat4.noVioletInside();
            }
            if (violetPos.equals("1,0")) {
                map4.plat5.setVioletInside();
            } else {
                map4.plat5.noVioletInside();
            }
            if (violetPos.equals("1,1")) {
                map4.plat6.setVioletInside();
            } else {
                map4.plat6.noVioletInside();
            }
            if (violetPos.equals("1,2")) {
                map4.plat7.setVioletInside();
            } else {
                map4.plat7.noVioletInside();
            }
            if (violetPos.equals("1,3")) {
                map4.plat8.setVioletInside();
            } else {
                map4.plat8.noVioletInside();
            }
            if (violetPos.equals("2,0")) {
                map4.plat9.setVioletInside();
            } else {
                map4.plat9.noVioletInside();
            }
            if (violetPos.equals("2,1")) {
                map4.plat10.setVioletInside();
            } else {
                map4.plat10.noVioletInside();
            }
            if (violetPos.equals("2,2")) {
                map4.plat11.setVioletInside();
            } else {
                map4.plat11.noVioletInside();
            }
            if (violetPos.equals("2,3")) {
                map4.plat12.setVioletInside();
            } else {
                map4.plat12.noVioletInside();
            }
        }

        if (distructorPos != null) {
            if (distructorPos.equals("0,0")) {
                map4.plat1.setDistructorInside();
            } else {
                map4.plat1.noDistructorInside();
            }
            if (distructorPos.equals("0,1")) {
                map4.plat2.setDistructorInside();
            } else {
                map4.plat2.noDistructorInside();
            }
            if (distructorPos.equals("0,2")) {
                map4.plat3.setDistructorInside();
            } else {
                map4.plat3.noDistructorInside();
            }
            if (distructorPos.equals("0,3")) {
                map4.plat4.setDistructorInside();
            } else {
                map4.plat4.noDistructorInside();
            }
            if (distructorPos.equals("1,0")) {
                map4.plat5.setDistructorInside();
            } else {
                map4.plat5.noDistructorInside();
            }
            if (distructorPos.equals("1,1")) {
                map4.plat6.setDistructorInside();
            } else {
                map4.plat6.noDistructorInside();
            }
            if (distructorPos.equals("1,2")) {
                map4.plat7.setDistructorInside();
            } else {
                map4.plat7.noDistructorInside();
            }
            if (distructorPos.equals("1,3")) {
                map4.plat8.setDistructorInside();
            } else {
                map4.plat8.noDistructorInside();
            }
            if (distructorPos.equals("2,0")) {
                map4.plat9.setDistructorInside();
            } else {
                map4.plat9.noDistructorInside();
            }
            if (distructorPos.equals("2,1")) {
                map4.plat10.setDistructorInside();
            } else {
                map4.plat10.noDistructorInside();
            }
            if (distructorPos.equals("2,2")) {
                map4.plat11.setDistructorInside();
            } else {
                map4.plat11.noDistructorInside();
            }
            if (distructorPos.equals("2,3")) {
                map4.plat12.setDistructorInside();
            } else {
                map4.plat12.noDistructorInside();
            }
        }

        map4.plat1.setAmmo(platformAmmoTile.get("0,0").getType());
        map4.plat2.setAmmo(platformAmmoTile.get("0,1").getType());
        map4.plat4.setAmmo(platformAmmoTile.get("0,3").getType());
        map4.plat6.setAmmo(platformAmmoTile.get("1,1").getType());
        map4.plat7.setAmmo(platformAmmoTile.get("1,2").getType());
        map4.plat8.setAmmo(platformAmmoTile.get("1,3").getType());
        map4.plat9.setAmmo(platformAmmoTile.get("2,0").getType());
        map4.plat10.setAmmo(platformAmmoTile.get("2,1").getType());
        map4.plat11.setAmmo(platformAmmoTile.get("2,2").getType());

        map4.stamp();
    }


}
