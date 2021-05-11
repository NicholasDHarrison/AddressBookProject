package com.tts;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    static AddressBook addressBook=new AddressBook();
    static Scanner scanner=new Scanner(System.in);
    //-------------------------------------Do Add Entry(s)----------------------------------------------
    public static void addAction() throws IOException {
        String fname;
        String lname;
        String phoneNumber;
        String email;
        System.out.println("Enter First Name");
        fname=scanner.nextLine();
        if(Validation.exitApp(fname)){//If Q/Quit exit the application
            Database.saveToDatabase(addressBook);
            System.exit(0) ;}
        while(!Validation.validateFirstName(fname)){
            System.out.println("This is not a valid First Name, try again!");
            fname=scanner.nextLine();
        }
        System.out.println("Enter Last Name");
        lname=scanner.nextLine();
        if(Validation.exitApp(lname)){//If Q/Quit exit the application
            Database.saveToDatabase(addressBook);
            System.exit(0) ;}
        while(!Validation.validateFirstName(lname)){
            System.out.println("This is not a valid First Name, try again!");
            lname=scanner.nextLine();
        }
        System.out.println("Enter a valid Phone Number");
        phoneNumber=scanner.nextLine();
        while(!Validation.validatePhoneNumber(phoneNumber)){
            System.out.println("This is not a valid phone number, try again!");
            phoneNumber=scanner.nextLine();
        }
        System.out.println("Enter a valid Email Address");
        email=scanner.nextLine();
        while(!Validation.validateEmailAddress(email)){
            System.out.println("This is not a valid email address, try again!");
            email=scanner.nextLine();
        }
        addressBook.addEntry(new Entry(fname,lname,phoneNumber,email));
    }
    //-------------------------------------Do Search Entry(s)----------------------------------------------
    public static void searchAction() throws IOException {
        System.out.println("What field do you want to search by? 1-4");
        System.out.println("1) First name");
        System.out.println("2) Last name");
        System.out.println("3) Email address");
        System.out.println("4) Phone number");
        String input;

        int action=0;
        while(!(action>0 && action<5)) {
            try {
                String quitApp=scanner.nextLine();
                System.out.println("Enter a valid number from 1 to 5 please");
                if(Validation.exitApp(quitApp)){//If Q/Quit exit the application
                    Database.saveToDatabase(addressBook);
                    System.exit(0) ;}
                action = Integer.parseInt(quitApp);
            } catch (Exception e) {
                System.out.println("This is not valid number from 1 to 4, try again");
            }
        }

        switch (action){
            case 1:
                System.out.println("Enter First Name");
                input=scanner.nextLine();
                if(Validation.exitApp(input)){//If Q/Quit exit the application
                    Database.saveToDatabase(addressBook);
                    System.exit(0) ;}
                while(!Validation.validateFirstName(input)){
                    System.out.println("This is not a valid First Name, try again!");
                    input=scanner.nextLine();
                }
                addressBook.searchByFname(input);
                break;
            case 2:
                System.out.println("Enter Last Name");
                input=scanner.nextLine();
                if(Validation.exitApp(input)){//If Q/Quit exit the application
                    Database.saveToDatabase(addressBook);
                    System.exit(0) ;}
                while(!Validation.validateLastName(input)){
                    System.out.println("This is not a valid Last Name, try again!");
                    input=scanner.nextLine();
                }
                addressBook.searchByLname(input);
                break;
            case 3:
                System.out.println("Enter a valid Email Address");
                input=scanner.nextLine();
                if(Validation.exitApp(input)){//If Q/Quit exit the application
                    Database.saveToDatabase(addressBook);
                    System.exit(0) ;}
                while(!Validation.validateEmailAddress(input)){
                    System.out.println("This is not a valid phone number, try again!");
                    input=scanner.nextLine();
                }
                addressBook.searchByEmail(input);
                break;
            case 4:
                System.out.println("Enter a valid Phone Number");
                input=scanner.nextLine();
                if(Validation.exitApp(input)){//If Q/Quit exit the application
                    Database.saveToDatabase(addressBook);
                    System.exit(0) ;}
                while(!Validation.validatePhoneNumber(input)){
                    System.out.println("This is not a valid phone number, try again!");
                    input=scanner.nextLine();
                }
                addressBook.searchByPhone(input);
                break;

        }

    }
    //-------------------------------------Do Remove Entry(s)----------------------------------------------
    public static void removeAction(){
        String email;
        System.out.println("Enter a valid Email Address for the entry you want to remove");
        email=scanner.nextLine();
        while(!Validation.validateEmailAddress(email)){
            System.out.println("This is not a valid email address, try again!");
            email=scanner.nextLine();
        }
        addressBook.deleteEntry(email);
    }
    //-------------------------------------Do Delete AddressBook Action----------------------------------------------
    public static void deleteAction() throws IOException {
        addressBook.deleteAddressBook();
    }
    //-------------------------------------Do Print Action----------------------------------------------
    public static void printAction(){
        addressBook.printAddressBook();
    }
    //-------------------------------------Do Action----------------------------------------------
    public static void doAction(int action ) throws IOException {
        switch (action){
            case 1:
                addAction();
                break;
            case 2:
                removeAction();
                break;
            case 3:
                searchAction();
                break;
            case 4:
                printAction();
                break;
            case 5:
                deleteAction();
                break;
        }
    }
    public static void main(String[] args) throws IOException {
        addressBook=new AddressBook();
        Database.readDatabase(addressBook.getAddressBook());
        String quit="";
        out:
        do{
            System.out.println("Please choose what you'd like to do with the database:\n");
            System.out.println("*********** Actions List **************");
            System.out.println("1) Add an entry");
            System.out.println("2) Remove an entry");
            System.out.println("3) Search for a specific entry");
            System.out.println("4) Print the address book");
            System.out.println("5) Delete the address book");
            System.out.println("6) Quit");
            int action=0;
            do{
                try {
                    System.out.println("Enter a valid number from 1 to 5 please");
                    String input=scanner.nextLine();
                    if(Validation.exitApp(input)) {
                        Database.saveToDatabase(addressBook);
                        break out;
                    }
                    action = Integer.parseInt(input);
                }catch (Exception e){
                    System.out.println("Enter a valid number from 1 to 5 please");
                }}while (!(action>0 && action <7));
            if(action>0 && action <7) {
                doAction(action);

            }
            else
                System.out.println("Enter a valid number from 1 to 6 please");
            if(action==6) {
                Database.saveToDatabase(addressBook);
                break;
            }
            System.out.println("Do you want to perform more action? Y/Yes");
            quit=scanner.nextLine();
        }while(quit.equalsIgnoreCase("y")||quit.equalsIgnoreCase("yes"));
        Database.saveToDatabase(addressBook);
    }

}

