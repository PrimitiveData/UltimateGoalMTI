package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.Hardware;

import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.subsystems.Intake;
import org.firstinspires.ftc.teamcode.subsystems.MecanumDrive;
import org.firstinspires.ftc.teamcode.subsystems.Shooter;
import org.firstinspires.ftc.teamcode.subsystems.Subsystem;
import org.firstinspires.ftc.teamcode.subsystems.Turret;
import org.firstinspires.ftc.teamcode.subsystems.WobbleGoal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Robot {
    public List<Subsystem> subsystems;
    public List<TeleopManager> teleopManagers;
    public HardwareMap hardwareMap;
    public Intake intake;
    public MecanumDrive mecanumDrive;
    public Shooter shooter;
    public Turret turret;
    public WobbleGoal wobbleGoal;
    public SampleMecanumDrive drive;

    public Gamepad gamepad1, gamepad2;

    public Robot(HardwareMap hardwareMap){
        this.hardwareMap = hardwareMap;
        drive = new SampleMecanumDrive(hardwareMap);
        intake = new Intake(this);
        mecanumDrive = new MecanumDrive(this);
        shooter = new Shooter(this);
        turret = new Turret(this);
        wobbleGoal = new WobbleGoal(this);

        subsystems = new ArrayList<>();
        Collections.addAll(
                subsystems,
                intake,
                mecanumDrive,
                shooter,
                turret,
                wobbleGoal
        );

        teleopManagers = new ArrayList<>();
    }

    public Robot(HardwareMap hardwareMap, Gamepad gamepad1, Gamepad gamepad2){
        this.hardwareMap = hardwareMap;
        drive = new SampleMecanumDrive(hardwareMap);
        intake = new Intake(this);
        mecanumDrive = new MecanumDrive(this);
        shooter = new Shooter(this);
        turret = new Turret(this);
        wobbleGoal = new WobbleGoal(this);

        subsystems = new ArrayList<>();
        Collections.addAll(
                subsystems,
                intake,
                mecanumDrive,
                shooter,
                turret,
                wobbleGoal
        );

        this.gamepad1 = gamepad1;
        this.gamepad2 = gamepad2;
    }

    public void update(){
        for (Subsystem s: subsystems)
            s.update();
        for (TeleopManager t: teleopManagers)
            t.update();
    }
}
