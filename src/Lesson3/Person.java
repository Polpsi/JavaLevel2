package Lesson3;

import java.util.ArrayList;

public class Person {
    private ArrayList<String> email = new ArrayList<>();
    private ArrayList<String> phone = new ArrayList<>();

    Person(String phone, String mail) {
        addPhone(phone);
        addEmail(mail);
    }

    public ArrayList<String> getEmail() {
        return email;
    }

    public ArrayList<String> getPhone() {
        return phone;
    }

    public void addPhone(String phoneNum) {
        if (!phone.contains(phoneNum)) {
            phone.add(phoneNum);
        }
    }

    public void addEmail(String addMail) {
        if (!email.contains(addMail)) {
            email.add(addMail);
        }
    }
}
