package org.firstinspires.ftc.teamcode.subsystems;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.hardware.lynx.LynxModule;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Robot;
import org.firstinspires.ftc.teamcode.subsystems.subclasses.LinkedMotorTuner;
import org.firstinspires.ftc.teamcode.subsystems.subclasses.Toggle;
import org.firstinspires.ftc.teamcode.subsystems.subclasses.VelocityPIDFController;

@Config
public class Shooter implements Subsystem {
    Robot robot;
    DcMotorEx myMotor1, myMotor2;
    public double targetVelo;
    VelocityPIDFController veloController;
    private double lastTargetVelo;
    public ElapsedTime veloTimer;
    public static double myVelo;
    Toggle toggleShooter;

    public Shooter(Robot robot) {
        this.robot = robot;
        myMotor1 = robot.hardwareMap.get(DcMotorEx.class, "shooterMotor1");
        myMotor2 = robot.hardwareMap.get(DcMotorEx.class, "shooterMotor2");

        myMotor1.setDirection(DcMotorSimple.Direction.REVERSE);
        //myMotor2.setDirection(DcMotorSimple.Direction.REVERSE);

        myMotor1.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        myMotor2.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        for (
                LynxModule module : robot.hardwareMap.getAll(LynxModule.class)) {
            module.setBulkCachingMode(LynxModule.BulkCachingMode.AUTO);
        }

        veloController = new VelocityPIDFController(
                LinkedMotorTuner.MOTOR_VELO_PID,
                LinkedMotorTuner.kV,
                LinkedMotorTuner.kA,
                LinkedMotorTuner.kStatic
        );

        veloTimer = new ElapsedTime();
        toggleShooter = new Toggle();
    }

    @Override
    public void update() {
        if(toggleShooter.getState(robot.gamepad1.left_bumper))
            myVelo = 1350;
        else
            myVelo = 0;
        double targetVelo = myVelo;

        veloController.setTargetVelocity(targetVelo);
        veloController.setTargetAcceleration((targetVelo - lastTargetVelo) / veloTimer.seconds());
        veloTimer.reset();

        lastTargetVelo = targetVelo;

        double motorPos = -myMotor1.getCurrentPosition();
        double motorVelo = -myMotor1.getVelocity();

        double power = veloController.update(motorPos, motorVelo);
        myMotor1.setPower(power);
        myMotor2.setPower(power);
    }
}