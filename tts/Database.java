package com.tts;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;


public class Database {
    // private static Object FileUtils;

    //Read the address book from a file and save into an ArrayList
    public static void readDatabase(List<Entry> addressbook) throws FileNotFoundException {

        String[] fields=new String[4];
        try {

            File file = new File("addressBook.txt");
            if(file.exists()){
                Scanner reader = new Scanner(file);
                reader.nextLine();
                reader.nextLine();
                if(!reader.nextLine().equals("")) {// For the case we don't have the last update line to skip reading line
                    reader.nextLine();

                }

                while (reader.hasNextLine()) {
                    String line = reader.nextLine();
                    fields = line.split(",");
                    addressbook.add(new Entry(fields[0], fields[1], fields[2], fields[3]));
                }
                reader.close();}
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (ArrayIndexOutOfBoundsException e){
            e.printStackTrace();
        }

    }
    // Save address book to a file
    public static void saveToDatabase(AddressBook addressbook) throws IOException {
        if(addressbook.getAddressBook().isEmpty())
            return;
        String[] fields=new String[4];
        try {
            File file=new File("addressBook.txt");
            FileWriter writer=null;
            if(file.createNewFile()||file.length()==0){
                writer=new FileWriter(file);
                writer.write("Created on "+ LocalDate.now()+" "+ LocalTime.now().format(DateTimeFormatter.ofPattern("hh:mm"))+" By "+System.getProperty("user.name")+"\n");
                writer.write("*********************************************************\n\n");

            }else{
                Scanner reader=new Scanner(file);
                String firstLine=reader.nextLine();
                System.out.println(firstLine);
                reader.close();
                writer=new FileWriter(file);
                writer.write(firstLine+"\n");
                writer.write("Last updated on "+LocalDate.now()+" "+ LocalTime.now().format(DateTimeFormatter.ofPattern("hh:mm"))+" By "+System.getProperty("user.name")+"\n");
                writer.write("***********************************************************\n\n");
            }

            for(Entry entry :addressbook.getAddressBook()){
                String line=entry.getFirstName()+","+
                        entry.getLastName()+","+
                        entry.getPhoneNumber()+","+
                        entry.getEmailAddress()+"\n";
                writer.write(line);

            }
            writer.close();
        }catch (IOException e){
            e.printStackTrace();
        }

    }
    // Delete the database File when clear the address book
    public static void deleteFile() throws IOException {
        File file=new File("addressBook.txt");
        FileWriter writer=new FileWriter(file);
        writer.write("");
        writer.close();
    }

    public static void main(String[] args) throws IOException {
        deleteFile();
    }
}

