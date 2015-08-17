package com.alexandrrs.crud;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Alexandr on 14.08.2015.
 */
public class CsvManipulator {

    private static final File csvFile = PropertiesHelper.getCsvFile();
    private static final SimpleDateFormat dateFormat = PropertiesHelper.getCsvDateFormat();

    private static List<Person> persons = new ArrayList<>();

    static {
        load();
    }

    private CsvManipulator() {
    }

    public static List<Person> getListofPersons() {
        return persons;
    }

    public static void addPerson(Person person) {
        persons.add(person);
    }

    public static Person getPerson(int id)
    {
        if (id > persons.size() - 1)
        {
            throw new IndexOutOfBoundsException();
        }
        return persons.get(id);
    }

    public static void updatePerson(int id, Person person) throws IndexOutOfBoundsException{

        if (id > persons.size() - 1)
        {
            throw new IndexOutOfBoundsException();
        }
        persons.add(id, person);
        persons.remove(id + 1);
    }

    public static void deletePerson(int id) throws IndexOutOfBoundsException{

        Person person = persons.get(id);
        person.setIsDeleted(true);

        if (id > persons.size() - 1) {
            throw new IndexOutOfBoundsException();
        }
    }
    public static void load()
    {
        try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))){

            String string;
            while((string = reader.readLine()) != null && !string.equals("")){
                String[] data = string.split(";");
                String firstName = data[0];
                String lastName = data[1];
                Sex sex = data[2].equalsIgnoreCase("M")? Sex.Male : Sex.Female;
                Date birthDay = dateFormat.parse(data[3]);
                String address = data[4];
                boolean isDeleted = Boolean.parseBoolean(data[5]);

                Person person = new Person(firstName, lastName, sex, birthDay, address, isDeleted);

                persons.add(person);
            }

        } catch (FileNotFoundException e) {
            ConsoleHelper.writeMessage("CSV file not found. Will be created");
        } catch (ParseException e) {
            ConsoleHelper.writeMessage("Troubles with parsin file");
        } catch (IOException e) {
            ConsoleHelper.writeMessage("Error with reading CSV file");
        }
    }
    public static void save() {
        try {
            PrintWriter printWriter = new PrintWriter(csvFile);

            for (int i = 0; i < persons.size(); i++)
            {
                Person person = persons.get(i);
                printWriter.format("%s;%s;%s;%s;%s;%s",
                        person.getFirstName(),
                        person.getLastName(),
                        person.getSex() == Sex.Male ? "M" : "W",
                        dateFormat.format(person.getBirthDate()),
                        person.getAddress(),
                        person.isDeleted()
                        );
                if (i != persons.size() - 1)
                    printWriter.println();
            }
            printWriter.close();
        } catch (FileNotFoundException e) {
            ConsoleHelper.writeMessage("CSV file not found");
        }
    }
}
