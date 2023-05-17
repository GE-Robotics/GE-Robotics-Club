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
    //private Servo servo0;

    //DcMotor.setDirection(DcMotor.Direction.REVERSE)



    @Override
    public void runOpMode() {
        expansionHub = hardwareMap.get(Blinker.class, "Expansion Hub 2");
        motorRearLeft = hardwareMap.get(DcMotor.class, "motorRearLeft");
        motorRearRight = hardwareMap.get(DcMotor.class, "motorRearRight");
        motorFrontLeft = hardwareMap.get(DcMotor.class, "motorFrontLeft");
        motorFrontRight = hardwareMap.get(DcMotor.class, "motorFrontRight");
        //servo0 = hardwareMap.get(Servo.class, "servo0");
        motorRearRight.setDirection(DcMotor.Direction.REVERSE);
        motorFrontRight.setDirection(DcMotor.Direction.REVERSE);
        
        
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        telemetry.addData("Status", "Robot is moving");
        telemetry.update();
        boolean burnOutMode = false;
        double speed = 3; //1 slow 2 medium 3 fast
        // run until the end of the match 
        boolean speed_on = false;
        while (opModeIsActive()) {
            double y; 
            double x;
            double rx;
            double leftSticky;
            double leftStickx;
            
            if (gamepad1.b){
                burnOutMode = true;
            } else {
                burnOutMode = false;
            }
            if (gamepad1.left_bumper == true && speed > 1){
                speed -= 1;
            }
            if (gamepad1.right_bumper == true && speed < 3){
                speed += 1;
            }
            if (gamepad1.left_stick_y > -0.2 && gamepad1.left_stick_y < 0.2){
                leftSticky = 0;
            } else {
                leftSticky = gamepad1.left_stick_y;
            }
            if (gamepad1.left_stick_x > -0.2 && gamepad1.left_stick_x < 0.2){
                leftStickx = 0;
            } else {
                leftStickx = gamepad1.left_stick_x;
            }
            if (leftSticky < -0.1 || leftSticky> 0.1){
                y = -leftSticky; // Reversed for a reason don't ask me what reason though
            } else {
                y = 0;
            }
            if (leftStickx < -0.1 || leftStickx> 0.1){
                x = leftStickx * 1.1; // Reversed for a reason don't ask me what reason though
            } else {
                x = 0;
            }
            if (gamepad1.right_stick_x < -0.1 || gamepad1.right_stick_x > 0.1){
                rx = gamepad1.right_stick_x /2; // Reversed for a reason don't ask me what reason though
            } else {
                rx = 0;
            }
            

            double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);
        
            double frontLeftPower = (y + x + rx) / denominator;// * (speed * (1/3));
            double backLeftPower = (y - x + rx) / denominator;// * (speed * (1/3));
            double frontRightPower = (y - x - rx) / denominator;// * (speed * (1/3));
            double backRightPower = (y + x - rx) / denominator;// * (speed * (1/3));
            if (burnOutMode == true){
                
                frontLeftPower = 0;
                frontRightPower = 0;
                if (leftStickx == 0){
                    frontLeftPower = leftSticky * 0.15;
                    frontRightPower = leftSticky * 0.15;
                    //backRightPower = backRightPower * 0.25;
                }
            } else {
                if (speed_on = true) {
                    frontLeftPower = frontLeftPower * (speed * (1/3));
                    frontRightPower = frontLeftPower * (speed * (1/3));
                    backLeftPower = frontLeftPower * (speed * (1/3));
                    backRightPower = frontLeftPower * (speed * (1/3));
                }
            }
            //Servo for the grabber
            //if (gamepad2.b) {
                //works from 0 to 1
                //servo0.setPosition(0.35);

            //}
            //if (gamepad2.y) {
                //works from -0 to -1
                //servo0.setPosition(0);

            //}
            motorRearLeft.setPower(backLeftPower);
            motorRearRight.setPower(backRightPower);
            motorFrontLeft.setPower(frontLeftPower);
            motorFrontRight.setPower(frontRightPower);
            
            
            
            telemetry.addData("Left Stick", gamepad1.left_stick_y);
            telemetry.addData("Right Stick", gamepad1.left_stick_x);
            telemetry.addData("Rear left", motorRearLeft.getPower());
            telemetry.addData("Movement motor 1", motorRearRight.getPower());
            telemetry.addData("Speed", speed);
            //telemetry.addData("Lifter motor", motor2.getPower());
            //telemetry.addData("Servo", servo0.getPosition());
            //telemetry.addData("Backup motor", motor3.getPower());
            telemetry.update();
        }
    }
}
