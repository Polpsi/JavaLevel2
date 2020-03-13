package Lesson3;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class Lesson3 {

    public static void main(String[] args) {

        //Пункт 1
        String str = "пирожки " +
                "перед оксаной в тёмном парке " +
                "олег распахивает плащ " +
                "под ним второй а дальше третий " +
                "оксана терпеливо ждёт" +
                " " +
                "сегодня ольгой овладели " +
                "сперва тоска потом олег " +
                "потом опять олег еще раз " +
                "и окончательно тоска" +
                " " +
                "я с отвращеньем хлопнул дверью " +
                "но понял вдруг что перегнул " +
                "открыл опять и снова хлопнул " +
                "без отвращения уже";

        System.out.println(checkUnicWord(str)); //Уникальные слова
        System.out.println(calcWord(str)); //Подсчет количества повторений слов

        //Пункт 2
        String textPhoneArr = "Павлинов,1523,pavl1@mail.ru;" +
                "Снегирев,1548,sneg1@mail.ru;" +
                "Индюков,6512,ind1@mail.ru;" +
                "Кукушкина,9875,kuk1@mail.ru;" +
                "Сорокина,3658,sor1@mail.ru;" +
                "Ворон,7452,vor1@mail.ru;" +
                "Снегирев,6541,sneg2@mail.ru;" +
                "Индюков,7456,ind2@mail.ru;" +
                "Кукушкина,9852,kuk2@mail.ru;" +
                "Сорокина,1258,sor2@mail.ru;" +
                "Кукушкина,2684,kuk3@mail.ru;" +
                "Павлинов,1397,pavl2@mail.ru;" +
                "Сорокина,1258,sor3@mail.ru;" +
                "Кукушкина,6485,kuk3@mail.ru;";

        //Можно создать пустую и добавлять отдельные записи, а можно словно из csv импортировать.
        PhoneBook phoneBook = new PhoneBook(textPhoneArr);
        phoneBook.addEntry(new String[]{"Галкин", "6545", "gal1@mail.ru"});

        String[] getSomeEntries = {"Кукушкина", "Сорокина", "Внезапно-Коровкин", "Индюков", "Павлинов", "Галкин"};
        for (int i = 0; i < getSomeEntries.length; i++) {
            try {
                System.out.println(getSomeEntries[i] + ": " +
                        phoneBook.getPhones(getSomeEntries[i]) + ", " +
                        phoneBook.getEmails(getSomeEntries[i]));
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        System.out.println(phoneBook.delEntry("Галкин") ? "Запись удалена" : "Такой записи и не было");
    }

    private static HashSet<String> checkUnicWord(String str) {
        String[] newStr = str.split(" ");
        return new HashSet<>(Arrays.asList(newStr));
    }

    private static HashMap calcWord(String str) {
        String[] tmpStr = str.split(" ");
        HashMap<String, Integer> calc = new HashMap<>();
        for (String s : tmpStr) {
            if (!calc.containsKey(s)) {
                calc.put(s, 1);
            } else {
                calc.put(s, calc.get(s) + 1);
            }
        }
        return calc;
    }
}
