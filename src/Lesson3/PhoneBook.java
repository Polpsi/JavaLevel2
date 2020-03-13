package Lesson3;

import java.util.ArrayList;
import java.util.HashMap;

public class PhoneBook {

    private HashMap<String, Person> entries = new HashMap<String, Person>();

    PhoneBook() {
    }

    PhoneBook(String str) {
        String[] convert = str.split(";");
        for (int i = 0; i < convert.length; i++) {
            String[] entry = convert[i].split(",");
            addEntry(entry);
        }
    }

    public void addEntry(String[] strEntry) {
        if (entries.containsKey(strEntry[0])) {
            Person person = entries.get(strEntry[0]);
            person.addPhone(strEntry[1]);
            person.addEmail(strEntry[2]);
        } else {
            entries.put(strEntry[0], new Person(strEntry[1], strEntry[2]));
        }
    }

    public ArrayList<String> getPhones(String surname) throws Exception {
        if (entries.containsKey(surname)) {
            return entries.get(surname).getPhone();
        } else {
            throw new Exception("Surname " + surname + " not found in PhoneBook entries");
        }
    }

    public ArrayList<String> getEmails(String surname) throws Exception {
        if (entries.containsKey(surname)) {
            return entries.get(surname).getEmail();
        } else {
            throw new Exception("Surname " + surname + " not found in PhoneBook Entries");
        }
    }

    //этого метода как-то не хватает.
    public boolean delEntry(String surname) {
        return entries.remove(surname) != null;
    }
}