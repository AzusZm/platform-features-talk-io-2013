package com.example.iotalk;

/**
 * Created with IntelliJ IDEA. User: viki Date: 4/20/13 Time: 12:24 PM To change this template use
 * File | Settings | File Templates.
 */
public interface Controller {
    /** An increment operation */
    public final int INCREMENT = 1;
    /** A decrement operation */
    public final int DECREMENT = 2;

    // The input half of the controller.  The controller accepts input from the fragment.
    /** Accepts an input operation either {@link #INCREMENT} or {@link #DECREMENT} */
    void acceptOperation(int operation);

    // The output half of the controller.  The controller directs output to the fragment.
    /** Register an object that can take an action. */
    void registerOutput(NumberOutput outputDelegate);
    /** Unregister a previous register object. */
    void unregisterOutput();
}
