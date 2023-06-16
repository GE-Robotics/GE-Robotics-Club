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

//version 1.0 6/7/23
//currently set up for mecanum wheels

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
    private Blinker expansionHub; //Defines the different things
    private DcMotor motorRearLeft;
    private DcMotor motorRearRight;
    private DcMotor motorFrontLeft;
    private DcMotor motorFrontRight; 

    @Override
    
    public void runOpMode() {
        expansionHub = hardwareMap.get(Blinker.class, "Expansion Hub 2"); //this defines the actuall stuff again for some reason you gotta have both
        motorRearLeft = hardwareMap.get(DcMotor.class, "motorRearLeft");
        motorRearRight = hardwareMap.get(DcMotor.class, "motorRearRight");
        motorFrontLeft = hardwareMap.get(DcMotor.class, "motorFrontLeft");
        motorFrontRight = hardwareMap.get(DcMotor.class, "motorFrontRight");
        motorRearRight.setDirection(DcMotor.Direction.REVERSE); //Couple of the motors are reversed because of the orientaion on the robot, this basically just multiplies any command to the motors by -1
        motorFrontRight.setDirection(DcMotor.Direction.REVERSE);
    
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        telemetry.addData("Status", "Robot is moving");
        telemetry.update();
        
        boolean burnOutMode = false; //Causes the front wheels to lock
        
        while (opModeIsActive()) { //Loop that repeats until stop is pressed
            double y; 
            double x;
            double rx;
            double leftSticky;
            double leftStickx;
            
            if (gamepad1.b){ //B = burn out
                burnOutMode = true;
            } else {
                burnOutMode = false;
            }
            
            //These lines are so it's easier to make the robot go perfectly straight
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
            //Code that tells the wheels were to go
            if (leftSticky < -0.1 || leftSticky> 0.1){
                y = -leftSticky;
            } else {
                y = 0;
            }
            if (leftStickx < -0.1 || leftStickx> 0.1){
                x = leftStickx * 1.1;
            } else {
                x = 0;
            }
            if (gamepad1.right_stick_x < -0.1 || gamepad1.right_stick_x > 0.1){
                rx = gamepad1.right_stick_x /2; // Reversed for a reason don't ask me what reason though
            } else {
                rx = 0;
            }
            
            //Asigns power to variables
            double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);
        
            double frontLeftPower = (y + x + rx) / denominator;// * (speed * (1/3));
            double backLeftPower = (y - x + rx) / denominator;// * (speed * (1/3));
            double frontRightPower = (y - x - rx) / denominator;// * (speed * (1/3));
            double backRightPower = (y + x - rx) / denominator;// * (speed * (1/3));
            
            //Burn out code
            if (burnOutMode == true){
                
                frontLeftPower = 0;
                frontRightPower = 0;
                if (leftStickx == 0){
                    frontLeftPower = leftSticky * 0.15;
                    frontRightPower = leftSticky * 0.15;
                    //backRightPower = backRightPower * 0.25;
                }
            } 
            
            //Tells the motors what to do
            motorRearLeft.setPower(backLeftPower);
            motorRearRight.setPower(backRightPower);
            motorFrontLeft.setPower(frontLeftPower);
            motorFrontRight.setPower(frontRightPower);
            
            //Telementry for debug stuff
            telemetry.addData("Left Stick", gamepad1.left_stick_y);
            telemetry.addData("Right Stick", gamepad1.left_stick_x);
            telemetry.addData("Rear left", motorRearLeft.getPower());
            telemetry.addData("Rear right", motorRearRight.getPower());
            telemetry.addData("Front left", motorFrontLeft.getPower());
            telemetry.addData("Front right", motorFrontRight.getPower());
            telemetry.update();
        }
    }
}
