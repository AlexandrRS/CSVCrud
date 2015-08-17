package com.alexandrrs.crud;

import com.alexandrrs.crud.exeptions.ImmediatlilyStopOperationException;

import java.util.Date;
import java.util.List;

/**
 * Created by Alexandr on 14.08.2015.
 */
public class Controller {
    private Controller() {
    }

    public static void operate(Operation operation) throws ImmediatlilyStopOperationException {
        switch (operation){
            case LIST: list();
                break;
            case CREATE: create();
                break;
            case UPDATE: update();
                break;
            case DELETE: delete();
                break;
            case EXIT: exit();
                break;
        }
    }

    private static void list() {

        List<Person> persons = CsvManipulator.getListofPersons();
        for (int i = 0; i < persons.size(); i++) {
            Person person = persons.get(i);
            if (!person.isDeleted()) {
                ConsoleHelper.writeMessage(String.format("ID: %s, %s", i, person.toString()));
            }
        }
    }

    private static void create() throws ImmediatlilyStopOperationException {

        Person person = editForm();

        ConsoleHelper.writeMessage(String.format(person + " will be added to database"));
        CsvManipulator.addPerson(person);
    }

    private static void update() throws ImmediatlilyStopOperationException {

        int id = getPersonToManipulate();
        Person person = editForm();
        CsvManipulator.updatePerson(id, person);
        ConsoleHelper.writeMessage(String.format("Person with ID: %d was updated", id));

    }
    private static void delete() throws ImmediatlilyStopOperationException {

        int id = getPersonToManipulate();
        CsvManipulator.deletePerson(id);
        ConsoleHelper.writeMessage(String.format("Person with ID: %d was deleted", id));
    }

    private static void exit() {

        CsvManipulator.save();
    }

    private static Person editForm() throws ImmediatlilyStopOperationException {

        String firstName = ConsoleHelper.readFisrtName();
        String lastName = ConsoleHelper.readLastName();
        Sex sex = ConsoleHelper.readSex();
        Date date = ConsoleHelper.readBirthDay();
        String address = ConsoleHelper.readAddress();
        Person person = new Person(firstName, lastName, sex, date, address);
        return person;
    }

    private static int getPersonToManipulate() throws ImmediatlilyStopOperationException {

        int id;
        Person existPerson = null;
        do {
            id = ConsoleHelper.readId();

            try {
                existPerson = CsvManipulator.getPerson(id);
            } catch (Exception e) {
                e.printStackTrace();
            }

        } while (existPerson == null);

        ConsoleHelper.writeMessage(String.format("%s will be updated", existPerson));
        return id;
    }
}
