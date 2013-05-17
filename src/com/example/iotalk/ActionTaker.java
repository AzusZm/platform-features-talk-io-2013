package com.example.iotalk;


/**
 * An object that can take an action.
 * Following the pattern at:
 * http://developer.android.com/training/basics/fragments/communicating.html
 */
public interface ActionTaker {
    /** Perform an action given some arbitrary information. */
    void performAction(int number);
}
