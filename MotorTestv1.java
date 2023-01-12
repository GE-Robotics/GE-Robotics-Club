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
import java.lang.annotation.Target;
import com.qualcomm.robotcore.hardware.Blinker;
import com.qualcomm.robotcore.hardware.Gyroscope;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * This file contains an minimal example of a Linear "OpMode". An OpMode is a 'program' that runs in either
 * the autonomous or the teleop period of an FTC match. The names of OpModes appear on the menu
 * of the FTC Driver Station. When an selection is made from the menu, the corresponding OpMode
 * class is instantiated on the Robot Controller and executed.
 *
 * This particular OpMode just executes a basic Tank Drive Teleop for a PushBot
 * It includes all the skeletal structure that all linear OpModes contain.
 *
 * Remove a @Disabled the on the next line or two (if present) to add this opmode to the Driver Station OpMode list,
 * or add a @Disabled annotation to prevent this OpMode from being added to the Driver Station
 */
 
 //version 0.5 12/2/22
 
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

public class MotorTest extends LinearOpMode {
    private Blinker expansion_Hub_2;
    private Gyroscope imu;
    private DcMotor motor0;
    private DcMotor motor1;
    private DcMotor motor2;
    private DcMotor motor3;

   
    
    @Override
    public void runOpMode() {
        expansion_Hub_2 = hardwareMap.get(Blinker.class, "Expansion Hub 2");
        imu = hardwareMap.get(Gyroscope.class, "imu");
        motor0 = hardwareMap.get(DcMotor.class, "motor0");
        motor1 = hardwareMap.get(DcMotor.class, "motor1");
        motor2 = hardwareMap.get(DcMotor.class, "motor2");
        motor3 = hardwareMap.get(DcMotor.class, "motor3");

        telemetry.addData("Status", "Initialized");
        telemetry.update();
        
        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        //Setting up the motor power
        double motor0Power = 0;
        double motor1Power = 0;
        
        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) { 
            //Acounting for stick drift and setting the motors to which way they go
            if (gamepad1.left_stick_y > 0.1 || gamepad1.left_stick_y < -0.1) {
                motor0.setPower(gamepad1.left_stick_y);
            } else {
                motor0.setPower(0);
            }
            if (gamepad1.right_stick_y > 0.1 || gamepad1.right_stick_y < -0.1) {
                motor1.setPower(gamepad1.right_stick_y * -1);
            } else {
                motor1.setPower(0);
            }  

            //Posible other motor            
            if (gamepad1.dpad_up) {
                motor2.setPower(1);
            } else if (gamepad1.dpad_down) {
                motor2.setPower(-1);
            } else {
                motor2.setPower(0);
            }
            
            //Backup motor 
            if (gamepad1.dpad_left) {
                motor3.setPower(1);
            } else if (gamepad1.dpad_right) {
                motor3.setPower(-1);
            } else {
                motor3.setPower(0);
            }
        }
    }
}
