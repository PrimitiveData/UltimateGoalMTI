package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Robot;
import org.firstinspires.ftc.teamcode.TeleopManager;
import org.firstinspires.ftc.teamcode.ToggleTrigger;

@TeleOp
public class SampleShooterTest extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        Robot robot = new Robot(hardwareMap, gamepad1, gamepad2);

        ToggleTrigger shooterToggle = new ToggleTrigger(() -> robot.gamepad1.x);

        TeleopManager shooterManager = new TeleopManager(shooterToggle, ()-> robot.shooter.shooterOn(), ()-> robot.shooter.shooterOff());
        TeleopManager shooterOn = new TeleopManager(()-> robot.gamepad1.a, ()-> robot.shooter.shooterOn());
        TeleopManager shooterOff = new TeleopManager(()-> robot.gamepad1.b, () -> robot.shooter.shooterOff());

        robot.teleopManagers.add(shooterOn);
        robot.teleopManagers.add(shooterOff);
        robot.teleopManagers.add(shooterManager);

        waitForStart();
        robot.shooter.veloTimer.reset();

        while(opModeIsActive()){
            robot.update();
        }
    }
}
