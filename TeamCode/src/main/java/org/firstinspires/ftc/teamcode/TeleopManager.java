package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.Gamepad;

public class TeleopManager {
    public RobotActionTrigger robotActionTrigger;
    public ToggleTrigger robotToggleTrigger;
    public RobotAction robotAction, robotAction1, robotAction2;

    public TeleopManager(RobotActionTrigger trigger, RobotAction action){
        this.robotActionTrigger = trigger;
        this.robotAction = action;
    }

    public TeleopManager(ToggleTrigger trigger, RobotAction action1, RobotAction action2){
        this.robotToggleTrigger = trigger;
        this.robotAction1 = action1;
        this.robotAction2 = action2;
    }

    public void update(){
        if (robotToggleTrigger == null) {
            if (robotActionTrigger.isSatisfied()) {
                robotAction.run();
            }
        }
        else {
            robotToggleTrigger.update();
            if (robotToggleTrigger.state())
                robotAction1.run();
            else
                robotAction2.run();
        }
    }
}
