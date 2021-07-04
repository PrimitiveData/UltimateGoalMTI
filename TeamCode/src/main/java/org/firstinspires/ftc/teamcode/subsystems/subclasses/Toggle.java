package org.firstinspires.ftc.teamcode.subsystems.subclasses;

public class Toggle {
    public boolean lastState;
    public boolean toggle;

    public boolean getState(boolean state) {
        if (state && !lastState)
            toggle = !toggle;
        lastState = state;
        return toggle;
    }
}