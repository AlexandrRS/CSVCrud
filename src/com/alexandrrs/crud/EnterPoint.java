package com.alexandrrs.crud;

import com.alexandrrs.crud.exeptions.ImmediatlilyStopOperationException;

/**
 * Created by Alexandr on 14.08.2015.
 */
public class EnterPoint {

    public static void main(String[] args) {

        Operation operation;
        do {
            operation = ConsoleHelper.selectOperation();
            try {
                Controller.operate(operation);
            } catch (ImmediatlilyStopOperationException e) {
                ConsoleHelper.writeMessage("Operation interrupted");
            }

        } while (operation != Operation.EXIT);
    }
}
