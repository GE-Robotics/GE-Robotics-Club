/*
Copyright 2022

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and
associated documentation files (the "Software"), to deal in the Software without restriction,
including without limitation the rights to use, copy, modify, merge, publish, distribute,
sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial
portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT
NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
*/
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

public class DriveTrain extends LinearOpMode {
    private Blinker expansionHub;
    private DcMotor motorRearLeft;
    private DcMotor motorRearRight;
    private DcMotor motorFrontLeft;
    private DcMotor motorFrontRight;
    private Servo servo0;

    @Override
    public void runOpMode() {
        expansionHub = hardwareMap.get(Blinker.class, "Expansion Hub 2");
        motorRearLeft = hardwareMap.get(DcMotor.class, "motorRearLeft");
        motorRearRight = hardwareMap.get(DcMotor.class, "motorRearRight");
        motorFrontLeft = hardwareMap.get(DcMotor.class, "motorFrontLeft");
        motorFrontRight = hardwareMap.get(DcMotor.class, "motorFrontRight");
        servo0 = hardwareMap.get(Servo.class, "servo0");

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        telemetry.addData("Status", "Robot is moving");
        telemetry.update();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {
            double y; 
            double x;
            double rx;
            if (gamepad1.left_stick_y < -0.1 || gamepad1.left_stick_y> 0.1){
                y = -gamepad1.left_stick_y; // Reversed for a reason don't ask me what reason though
            } else {
                y = 0;
            }
            if (gamepad1.left_stick_x < -0.1 || gamepad1.left_stick_x> 0.1){
                x = gamepad1.left_stick_x * 1.1; // Reversed for a reason don't ask me what reason though
            } else {
                x = 0;
            }
            if (gamepad1.right_stick_x < -0.1 || gamepad1.right_stick_x > 0.1){
                rx = gamepad1.right_stick_x; // Reversed for a reason don't ask me what reason though
            } else {
                rx = 0;
            }
            

            double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);
            double frontLeftPower = (y + x + rx) / denominator;
            double backLeftPower = (y - x + rx) / denominator;
            double frontRightPower = (y - x - rx) / denominator;
            double backRightPower = (y + x - rx) / denominator;


            //Servo for the grabber
            if (gamepad2.b) {
                //works from 0 to 1
                servo0.setPosition(0.35);

            }
            if (gamepad2.y) {
                //works from -0 to -1
                servo0.setPosition(0);

            }

            telemetry.addData("Left Stick", gamepad1.left_stick_y);
            telemetry.addData("Right Stick", gamepad1.left_stick_x);
            //telemetry.addData("Movement motor 0", motorRearLeft.getPower());
            //telemetry.addData("Movement motor 1", motorRea.getPower());
            //telemetry.addData("Lifter motor", motor2.getPower());
            telemetry.addData("Servo", servo0.getPosition());
            //telemetry.addData("Backup motor", motor3.getPower());
            telemetry.update();
        }
    }
}
