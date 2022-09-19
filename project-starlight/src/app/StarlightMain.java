package src.app;

import java.util.*;
import src.hack.*;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.FileOutputStream;

public class StarlightMain {
    /** 
     * Alphabetically sorts the user's hackathon list by hackathon name
     * @param userList the user's hackathon list
     */
    public static void alphaInsertionSort (ArrayList<Hack> userList) {
        int len = userList.size();
        int j;
        for (int i = 1; i < len; ++i) {
            j = i;
            while(j > 0 && userList.get(i).getHackName().compareTo(userList.get(j-1).getHackName()) < 0) {
                Hack temp = userList.get(j);
                userList.set(j, userList.get(j-1));
                userList.set(j-1, temp);
                j--;
            }
        }
    }

    /** 
     * Prints out the options a user can choose from to search for hackathons
     */
    public static void printOptions() {
        System.out.println("Select the criteria you would like to filter hackathons by: ");
        System.out.println("A: Date");
        System.out.println("B: Mode (In-Person, Hybrid, Virtual)");
        System.out.println("C: State");
        System.out.println("D: Name");
        System.out.println("E: Women and Nonbinary Oriented Hackathons");
        System.out.println("F: Registration Open Hackathons");
        System.out.println("G: Display all hackathons");
    }

    /** 
     * Prints a hackathon's information after searching for hackathons, which includes 
     * the name of the hackathon, link to website, and an additional characteristic that depends on the user's search
     * @param type the characteristic the user searches for
     * @param hackathons the main list for hackathons
     * @param i the index of the specific hackathon to be printed
     */
    public static void printResults (String type, ArrayList<Hack> hackathons, int i) {
        String name = hackathons.get(i).getHackName();
        String link = hackathons.get(i).getHackLink();
        if (type == null) {
            System.out.println(name + "\nLink: " + link + "\n");
        }
        else {
            System.out.println(name + ": " + type + "\nLink: " + link + "\n");
        }
    }

    /** 
     * Displays the results of the user's search for hackathon depending on the category the user chose
     * @param answer the user's category selection
     * @param scnr a scanner used to determine the user's answers
     * @param hackathons the main hackathon list
     */
    public static void determineResults(String answer, Scanner scnr, ArrayList<Hack> hackathons) {
        int check = 0;
        switch(answer){
            case "A": 
                System.out.println("Which timeframe would you like to view? Type Fall 2022 or Spring 2023 below: ");
                String season = scnr.nextLine();
                String date = "";
                if (season.equals("Fall 2022")) {
                    System.out.println("Here is a list of hackathons occuring in Fall 2022:");
                    for (int i = 0; i < hackathons.size(); ++i) {
                        if (hackathons.get(i).getHackEndDate().equals("")) {
                            date = hackathons.get(i).getHackStartDate();
                        }
                        else if (!hackathons.get(i).getHackEndDate().equals("")) {
                            date = hackathons.get(i).getHackStartDate() + " - " + hackathons.get(i).getHackEndDate();
                        }
                        if (date.contains("2022")){
                            printResults(date, hackathons, i);
                        }
                    }
                }
                else if (season.equals("Spring 2023")) {
                    System.out.println("Here is a list of hackathons occuring in Spring 2023:");
                    for (int i = 0; i < hackathons.size(); ++i) {
                        if (hackathons.get(i).getHackEndDate().equals("")) {
                            date = hackathons.get(i).getHackStartDate();
                        }
                        else if (!hackathons.get(i).getHackEndDate().equals("")) {
                            date = hackathons.get(i).getHackStartDate() + " - " + hackathons.get(i).getHackEndDate();
                        }
                        if (date.contains("2023")){
                            printResults(date, hackathons, i);
                        }
                    }
                }
                break;
            
            case "B": 
                System.out.println("Which mode would you prefer? Type In-Person, Hybrid, or Virtual below. You may include multiple options.");
                String mode = scnr.nextLine();
                if (mode.contains("In-Person")) {
                    System.out.println("Here is a list of hackathons occuring in-person:");
                    for (int i = 0; i < hackathons.size(); ++i) {
                        String modes = hackathons.get(i).getHackMode();
                        if (modes.contains("In Person")){
                            printResults(modes, hackathons, i);
                        }
                    }
                }
                if (mode.contains("Hybrid")) {
                    System.out.println("Here is a list of hackathons occuring in hybrid format:");
                    for (int i = 0; i < hackathons.size(); ++i) {
                        String modes = hackathons.get(i).getHackMode();
                        if (modes.contains("Hybrid")){
                            printResults(modes, hackathons, i);
                        }
                    }
                }
                if (mode.contains("Virtual")) {
                    System.out.println("Here is a list of hackathons occuring virtually:");
                    for (int i = 0; i < hackathons.size(); ++i) {
                        String modes = hackathons.get(i).getHackMode();
                        if (modes.contains("Virtual")){
                            printResults(modes, hackathons, i);
                        }
                    }
                }
                break;

            case "C":
                System.out.println("Which state are you interested to attend hackathons in? Please type an abbreviation below: ");
                String state = scnr.nextLine();
                System.out.println("Here is a list of hackathons occuring in " + state + ":");
                for (int i = 0; i < hackathons.size(); ++i) {
                    String states = hackathons.get(i).getHackLocation();
                    if (states.contains(state)){
                        printResults(states, hackathons, i);
                        check++;
                    }
                }
                if (check == 0) {
                    System.out.println("There were no results found for your state.");
                }
                break;
                
            case "D":
                System.out.println("Please enter the name of the hackathon you would like to search below: ");
                String event = scnr.nextLine();
                for (int i = 0; i < hackathons.size(); ++i) {
                    String names = hackathons.get(i).getHackName();
                    if (names.contains(event)){
                        printResults(null, hackathons, i);
                        check++;
                    }
                }
                if (check == 0) {
                    System.out.println("No hackathons were found with that name.");
                }
                break;

            case "E": 
                System.out.println("Here is a list of all women and nonbinary hackathons: ");
                for (int i = 0; i < hackathons.size(); ++i) {
                    boolean womenHack = hackathons.get(i).getWomenHack();
                    if (womenHack) {
                        printResults("Hackathon targets women and nonbinary audience", hackathons, i);
                    }
                }
                break;

            case "F":
                System.out.println("Here is a list of all hackathons currently open for registration: ");
                
                for (int i = 0; i < hackathons.size(); ++i) {
                    String reg = hackathons.get(i).getHackReg();
                    if (reg.equals("open")) {
                        printResults(reg, hackathons, i);
                        check++;
                    }
                }
                if (check == 0) {
                    System.out.println("No hackathons included in Project Starlight are currently open.");
                }
                break;

            case "G": 
                System.out.println("Here is a list of all hackathons: ");
                for (int i = 0; i < hackathons.size(); ++i) {
                    printResults(null, hackathons, i);
                }
                break;

            default: 
                System.out.println("Please try again.");
        }
    }

    /** 
     * Adds the user's choice of hackathons to their own list from the main list
     * @param hackathons the main list of hackathons
     * @param userList the user's hackathon list
     */
    public static void addToList(Scanner user, ArrayList<Hack> hackathons, ArrayList<Hack> userList) {
        System.out.println("Which hackathons would you like to save? Please type their names below with a comma in between or type none to skip this step: ");
        String userHacks = user.nextLine();
        if (userHacks.toLowerCase().equals("none")) {
            return;
        }
        else {
            for (int i = 0; i < hackathons.size(); i++) {
                if(userHacks.contains(hackathons.get(i).getHackName())) {
                    userList.add(hackathons.get(i));
                }
            }
        }
    }

    /** 
     * Deletes any repeating hackathons from the user's hackathon list
     * @param userList the user's list of hackathons
     */
    public static void hackathonRepeatFilter(ArrayList<Hack> userList) {
        for (int i = 0; i < userList.size(); i++) {
            for (int j = 1; j < userList.size(); j++){
                if (userList.get(i).getHackName().equals(userList.get(j).getHackName()) && i != j) {
                    userList.remove(j);
                }
            }
        }
    }

     /** 
     * Creates a prioritized list of hackathons
     * @param pri a scanner for obtaining a user's response
     * @param priInt a scanner for obtaining integers
     * @param userList the user's hackathon list
     * @param priorityList the user's priority list
     */
    public static void createPriorityList (Scanner pri, Scanner priInt, ArrayList<Hack> userList, ArrayList<PriorityHack> priorityList) {
        System.out.println("Would you like to assign priorities to each hackathon? Type yes or no below: ");
        String priorityResponse = pri.nextLine();
        if (priorityResponse.toLowerCase().equals("yes")) {
            for (int i = 0; i < userList.size(); i++) {
                System.out.println(userList.get(i).getHackName());
            }
            System.out.println("For each hackathon listed, type a number or 0 below to indicate no priority. ");
            System.out.println("You may also include a note about the hackathon listed, and press enter between each entry:");
            for (int i = 0; i < userList.size(); ++i) {
                System.out.println(userList.get(i).getHackName() + "   Hackathon " + (i+1) + "/" + userList.size());
                int priorityNo = priInt.nextInt();
                String note = pri.nextLine();
                PriorityHack curr = new PriorityHack(userList.get(i), priorityNo, note);
                priorityList.add(curr);
            }
        }
        priorityListSelectionSort(priorityList);
    }
    
    /** 
     * Sorts the priority list via selection sort
     * @param priorityList the user's priority list
     */
    public static void priorityListSelectionSort (ArrayList<PriorityHack> priorityList) {
        int len = priorityList.size();
        for (int i = 0; i < len - 1; i++) {
            int minIndex = i;
            for (int j = i+1; j < len; j++) {
                if (priorityList.get(j).getPriorityNo() < priorityList.get(minIndex).getPriorityNo()) {
                    minIndex = j;
                    PriorityHack temp = priorityList.get(minIndex);
                    priorityList.set(minIndex, priorityList.get(i));
                    priorityList.set(i, temp);
                }
            }
        }
    }

    /** 
     * Saves the user's personal list of hackathons to a text file they can download
     * @param scnr a scanner used to record the user's response
     * @param userList the user's hackathon list
     * @param priUserHacks the user's priority list
     * @param userFile the user's text file
     */
    public static void saveHackathons(Scanner scnr, ArrayList<Hack> userList, ArrayList<PriorityHack> priorityList, File newFile, FileOutputStream userFile) throws IOException{
        System.out.println("Would you like to save your hackathon list or priority list? Please type hackathon list, priority list, or no below: ");
        String hacks = scnr.nextLine();
        if (hacks.toLowerCase().equals("no")) {
            return;
        }
        else {
            //Creates a new file
            if (newFile.createNewFile()) {
                System.out.println("Your file has been successfully created!");
            }
            else {
                System.out.println("This file exists.");
            }
            if (hacks.toLowerCase().contains("hackathon list")) {
                PrintWriter out = new PrintWriter(userFile);
                for (int i = 0; i < userList.size(); ++i) { 
                    out.println(userList.get(i).printString());
                }
                System.out.println("Your hackathons have been stored!");
                out.close();
            }
            else if (hacks.toLowerCase().contains("priority list")) {
                if (priorityList != null) {
                    PrintWriter out = new PrintWriter(userFile);
                    for (int i = 0; i < priorityList.size(); ++i) {
                            out.println(priorityList.get(i).printString()); 
                    }
                    System.out.println("Your hackathons have been stored!");
                    out.close();
                }
                else {
                    System.out.println("Your priority list does not exist.");
                }
            }

        }
    }

    //Start of Main Method
    public static void main(String args[]) throws Exception {
        //reading and storing info from the hackathoninfo.txt file
        Scanner in = new Scanner(new File("hackathoninfo.txt"));
        in.useDelimiter("-|\n");

        ArrayList<Hack> hackathons = new ArrayList<Hack>();
        while(in.hasNext()) {
            int hackID = in.nextInt();
            String hackName = in.next();
            String hackStartDate = in.next();
            String hackEndDate = in.next();
            String hackMode = in.next();
            String hackLocation = in.next();
            String womenHack = in.next();
            String hackReg = in.next();
            String hackLink = in.next();

            Hack hackathon = new Hack(hackID, hackName, hackStartDate, hackEndDate, hackMode, hackLocation, Boolean.parseBoolean(womenHack), hackReg, hackLink);
            hackathons.add(hackathon);
        }

        in.close();

        System.out.println("Welcome to Starlight, a search engine for hackatons!");
        Scanner scnr = new Scanner(System.in);
        Scanner scnrDelim = new Scanner(System.in);
        scnrDelim.useDelimiter(",");
        Scanner scnrInt = new Scanner(System.in);
        ArrayList<Hack> userList = new ArrayList<Hack>();

        //Displays intial settings that a user can use
        printOptions();
        String answer = scnr.nextLine();
        boolean isTrue = false;

        //Allows for continued search of file and saving of preferred hackathons
        while (!isTrue) {
            determineResults(answer, scnr, hackathons);
            addToList(scnrDelim, hackathons, userList);
            System.out.println("Would you like to continue searching for hackathons? Type yes or no below: ");
            answer = scnr.nextLine();
            if(answer.toLowerCase().equals("yes")) { 
                printOptions();
                answer = scnr.nextLine();
            }
            else {
                isTrue = true;
            }
        }

        //Removes any repeating hackathons from the list
        hackathonRepeatFilter(userList);

        //Assigns priorities and stores them in a new array
        ArrayList<PriorityHack> priUserHacks = new ArrayList<PriorityHack>();
        createPriorityList(scnr, scnrInt, userList, priUserHacks);

        //Allows user to view or edit hackathons
        System.out.println("Would you like to view your hackathon list or remove hackathons from your list? Type yes or no below: ");
        String response = scnr.nextLine();
        if (response.toLowerCase().equals("yes")) {
            System.out.println("Wonderful! Type view or remove to either view your hackathon list or remove hackathons from your hackathon list: ");
            String choice = scnr.nextLine();
            if (choice.toLowerCase().equals("view")) {
                System.out.println("Please choose which format you would like to view your list by: ");
                System.out.println("A: Alphabetical");
                System.out.println("B: By Priority");
                System.out.println("C: Standard");
                String options = scnr.nextLine();
                switch(options) {
                    case "A":
                        alphaInsertionSort(userList);
                        for (int i = 0; i < userList.size(); i++) {
                            System.out.println(userList.get(i));
                        }
                        break;
                    case "B": 
                        for (int i = 0; i < priUserHacks.size(); ++i) {
                            System.out.println(priUserHacks.get(i).getHackathon().getHackName() + "\nPriority: " + priUserHacks.get(i).getPriorityNo());
                        }
                        break;
                    case "C":
                        for (int i = 0; i < userList.size(); i++) {
                            System.out.println(userList.get(i));
                        }
                        break;
                    default: 
                        System.out.println("Error - please try again."); 
                }
            }
            else if (choice.toLowerCase().equals("remove")) {
                boolean terminate = false;
                while (!terminate) {
                    System.out.println("Which hackathon would you like to remove? Please select from the options listed below: ");
                    for (int i = 0; i < userList.size(); ++i) {
                        System.out.println(userList.get(i).getHackName());
                    }
                    String hack = scnr.nextLine();
                    System.out.println("Would you like to remove this hackathon? Type yes or no below: ");
                    response = scnr.nextLine();
                    if (response.toLowerCase().equals("yes")) {
                        int index = 0;
                        for (int i = 0; i < userList.size(); ++i) {
                            if (userList.get(i).getHackName().contains(hack)) {
                                index = i;
                                break;
                            }
                        }
                        userList.remove(index);
                        if (priUserHacks != null) {
                            for (int i = 0; i < priUserHacks.size(); ++i) {
                                if (priUserHacks.get(i).getHackathon().getHackName().contains(response)) {
                                    index = i;
                                    break;
                                }
                            }
                        }
                    }
                    System.out.println("Would you like to remove any additional hackathons? Type yes or no below: ");
                    response = scnr.nextLine();
                    if (response.toLowerCase().equals("no")) {
                        terminate = true;
                    }
                }
            }
        }

        //Creates a schedule for the user to follow
        File newFile = new File("userFile.txt");
        FileOutputStream userFile = new FileOutputStream("userfile.txt");
        saveHackathons(scnr, userList, priUserHacks, newFile, userFile);

        System.out.println("Thank you for using Project Starlight!");
        scnr.close();
        scnrDelim.close();
    }
}
