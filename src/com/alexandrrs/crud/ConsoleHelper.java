package com.alexandrrs.crud;

import com.alexandrrs.crud.exeptions.ImmediatlilyStopOperationException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Alexandr on 14.08.2015.
 */
public class ConsoleHelper
{
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    public static final String NAME_REGULAR_EXPRESSION = "[a-z,A-Z,а-я,А-Я]{3,20}";
    public static final String SEX_REGULAR_EXPRESSION = "[wWmM]{1}";

    private ConsoleHelper() {
    }

    public static void writeMessage(String string) {

        System.out.println(string);
    }
    public static String readMessage() throws ImmediatlilyStopOperationException {

        String result = null;
        try {
            result = reader.readLine();
        } catch (IOException e) {
            writeMessage("console not available");
            System.exit(1);
        }

        if (result.equalsIgnoreCase("EXIT")) {
            throw new ImmediatlilyStopOperationException("User entered Exit");
        }

        return result;
    }

    public static Operation selectOperation()
    {
        Operation operation = null;
        Operation[] operations = Operation.values();
        writeMessage("1. List\n2. Create\n3. Update\n4. Delete\n0. Exit");

        do {
            try {
                operation = operations[Integer.parseInt(readMessage())];

            } catch (Exception e) {
                writeMessage("Enter correct choice");
            }
        } while (operation == null);
        return operation;
    }
    public static String readFisrtName() throws ImmediatlilyStopOperationException {

        String readString;
        do {
            writeMessage("Enter FirstName");
            readString = readMessage();
        } while (!readString.matches(NAME_REGULAR_EXPRESSION));

        return readString;
    }

    public static String readLastName() throws ImmediatlilyStopOperationException {

        String readString;
        do {
            writeMessage("Enter LastName");
            readString = readMessage();
        } while (!readString.matches(NAME_REGULAR_EXPRESSION));

        return readString;
    }
    public static Sex readSex() throws ImmediatlilyStopOperationException {

        String sex;
        do {

            ConsoleHelper.writeMessage("Enter Sex (M/W)");
            sex = ConsoleHelper.readMessage();
        } while (!sex.matches(SEX_REGULAR_EXPRESSION));

        return sex.equalsIgnoreCase("m") ? Sex.Male : Sex.Female;
    }

    public static Date readBirthDay() throws ImmediatlilyStopOperationException {

        SimpleDateFormat dateFormat = PropertiesHelper.getUserDateFormat();
        Date date = null;

        do {
            writeMessage(String.format("Enter birthday (now is %s)", dateFormat.format(new Date())));
            String age = readMessage();
            try {
                date = dateFormat.parse(age);
            } catch (ParseException e) {
                writeMessage("Entered date is not valid");
            }
        } while (date == null);

        return date;
    }

    public static String readAddress() throws ImmediatlilyStopOperationException {

        writeMessage("Enter Address");

        return readMessage();
    }

    public static int readId() throws ImmediatlilyStopOperationException {
        ConsoleHelper.writeMessage("Enter ID");

        int id = -1;

        do {
            try {
                 id = Integer.parseInt(readMessage());
            } catch (NumberFormatException e) {
                writeMessage("ID must be contains only digits");
            }
        } while (id < 0);

        return id;
    }
}
