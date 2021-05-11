package com.tts;

        import java.io.IOException;
        import java.util.ArrayList;
        import java.util.List;
        import java.util.Scanner;

public class AddressBook {
    private Scanner scanner = new Scanner(System.in);
    private String confirm;
    private  List<Entry> addressBook;
    public AddressBook(List<Entry> addressBook){
        this.addressBook=addressBook;
    }
    public AddressBook(){
        this.addressBook=new ArrayList<>();
    }
    public List<Entry> getAddressBook(){
        return this.addressBook;
    }
    public void  setAddressBook(List<Entry> addressBook){
        this.addressBook=addressBook;
    }
    //     ---------------------------Add Entry(s)---------------------------------------------
    public void addEntry(Entry entry){
        boolean found=false;
        for(Entry item:addressBook){
            if(item.getEmailAddress().equalsIgnoreCase(entry.getEmailAddress())) {
                found=true;
                int index=addressBook.indexOf(item);// Get the index of the matching entry found in the addressBook
                // to use it later if the user wants to update the existing entry
                System.out.println("There is an existing entry related to the entered entry email_address");
                System.out.println( item.toString());
                System.out.println("Do you want to update this existing entry? Y/Yes");
                confirm=scanner.nextLine();
                if (confirm.equalsIgnoreCase("y") || confirm.equalsIgnoreCase("yes")){
                    addressBook.set(index,entry);
                    System.out.println("This entry was successfully updated");
                    System.out.println(entry.toString());
                    return;

                }
                else
                    System.out.println("Thank you");
                return;
            }}
        if(!found) {// There is no existing entry with matched email address in the addressBook
            addressBook.add(entry);
            System.out.println("The entry was successfully added to the addressBook, thank you!");

        }




    }
    //     ---------------------------Delete Entry(s)---------------------------------------------
    public void deleteEntry(String email){
        for(Entry item:addressBook){
            if(email.equalsIgnoreCase(item.getEmailAddress())) {
                System.out.println( item.toString());
                System.out.println("Are you sure you want do delete this entry? Y/Yes");
                confirm = scanner.nextLine();
                if (confirm.equalsIgnoreCase("y") || confirm.equalsIgnoreCase("yes")){
                    System.out.println("This entry was successfully deleted\n"+item.toString());
                    addressBook.remove(item);

                }
                else
                    System.out.printf("Sorry! There is no entry related to the entered Email-Address %s\n",email);
            }
        }
    }
    //     ---------------------------Search By Email---------------------------------------------
    public void searchByEmail(String email){
        boolean found=false;
        for(Entry item:addressBook){
            if(email.equalsIgnoreCase(item.getEmailAddress())) {
                System.out.println("This entry related to the entered email address is:");
                System.out.println( item.toString());
                found=true;
                break;

            }
        }
        if(!found){
            System.out.printf("Sorry! There is no entry related to the entered Email-Address %s\n",email);
        }

    }
    //     ---------------------------Search by Phone #---------------------------------------------
    public void searchByPhone(String phone){
        List<Entry> temp=new ArrayList<>();
        for(Entry item:addressBook) {
            if (phone.equalsIgnoreCase(item.getPhoneNumber())) {
                System.out.println( item.toString());
                temp.add(item);
            }
        }
        System.out.println(temp);
        if(temp.size()>0){
            System.out.printf("We found (%d) related to the entered Phone-Number:\n",temp.size());
            for (Entry item:temp)
                System.out.println( item.toString());
        }
        else
            System.out.printf("Sorry! There is no entry related to the entered Phone-Number %s\n",phone);


    }
    //     ---------------------------Search By First Name---------------------------------------------
    public void searchByFname(String fname){
        List<Entry> temp=new ArrayList<>();
        for(Entry item:addressBook) {
            //if (item.getFirstName().toUpperCase().contains(fname.toUpperCase())) {// get all the entries which have the substring anywhere
            if (item.getFirstName().substring(0, fname.length()).equalsIgnoreCase(fname)) {//get all the entries which start with the entered substring
                temp.add(item);
            }
        }
        if(temp.size()>0){
            System.out.printf("We found (%d) related to the entered First name:\n",temp.size());
            for (Entry item:temp)
                System.out.println( item.toString());
        }
        else
            System.out.printf("Sorry! There is no entry related to the entered First name %s\n",fname);
    }
    //     ---------------------------Search By Last Name---------------------------------------------
    public void searchByLname(String lname){
        List<Entry> temp=new ArrayList<>();
        for(Entry item:addressBook) {
            // if (item.getLastName().toUpperCase().contains(lname.toUpperCase())) {
            if (item.getLastName().substring(0,lname.length()).equalsIgnoreCase(lname)) {
                temp.add(item);
            }
        }
        if(temp.size()>0){
            System.out.printf("We found (%d) related to the entered Last name:\n",temp.size());
            for (Entry item:temp)
                System.out.println( item.toString());
        }
        else
            System.out.printf("Sorry! There is no entry related to the entered Last name %s\n",lname);


    }
    //     ---------------------------Print Address Book---------------------------------------------
    public void printAddressBook(){

        if(addressBook.size()>0){
            System.out.printf("This address book contains (%d) entry :\n",addressBook.size());
            for (Entry item:addressBook)
                System.out.println( "****************** Entry "+(addressBook.indexOf(item)+1)+" **************\n" + item.toString());
        }
        else
            System.out.println("Sorry! This address book is empty");


    }
//     ---------------------------Delete Address Book---------------------------------------------
    public void deleteAddressBook() throws IOException {

        System.out.println("Are you sure you want do delete this entry? Y/Yes");
        confirm = scanner.nextLine();
        if (confirm.equalsIgnoreCase("y") || confirm.equalsIgnoreCase("yes")){
            addressBook.clear();
            Database.deleteFile();
            System.out.println("This addressBook was successfully deleted");
        }

    }



}

