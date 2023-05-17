package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import java.lang.annotation.Target;
import com.qualcomm.robotcore.hardware.Blinker;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

//version 0.6 12/9/22

//List of controller inputs
//gamepad1.left_stick_x or y
//gamepad1.right_stick_x or y 
//gamepad1.x 
//gamepad1.y 
//gamepad1.b 
//gamepad1.a 
//gamepad1.left_bumper
//gamepad1.right_bumper

@TeleOp

public class IWantToDie extends LinearOpMode {
    private Blinker expansionHub;
    private DcMotor steve;
    //private Servo servo0;

    //DcMotor.setDirection(DcMotor.Direction.REVERSE)



    @Override
    public void runOpMode() {
        expansionHub = hardwareMap.get(Blinker.class, "Expansion Hub 2");
        steve = hardwareMap.get(DcMotor.class, "steve");
        
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        telemetry.addData("Status", "Robot is moving");
        telemetry.update();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {
            if (gamepad1.a){
                steve.setPower(1);
            } else {
                steve.setPower(0);
            }
            
            telemetry.addData("steve moving", steve.getPower());
            telemetry.update();
        }
    }
}
