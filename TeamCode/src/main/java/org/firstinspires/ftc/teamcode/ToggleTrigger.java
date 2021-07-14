package org.firstinspires.ftc.teamcode;

public class ToggleTrigger {
    public RobotActionTrigger robotActionTrigger;
    public boolean prevState;
    public boolean state;

    public ToggleTrigger(RobotActionTrigger robotActionTrigger) {
        this.robotActionTrigger = robotActionTrigger;
        prevState = false;
        state = false;
    }

    public boolean state(){
        return state;
    }

    public void update(){
        if(robotActionTrigger.isSatisfied()){
            if(!prevState)
                state = !state;
            prevState = true;
        }
        else{
            if(prevState)
                prevState = false;
        }
    }
}
