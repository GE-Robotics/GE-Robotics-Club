/* Copyright (c) 2017 FIRST. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted (subject to the limitations in the disclaimer below) provided that
 * the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list
 * of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice, this
 * list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * Neither the name of FIRST nor the names of its contributors may be used to endorse or
 * promote products derived from this software without specific prior written permission.
 *
 * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
 * LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.Blinker;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;


/**
 * This file contains an minimal example of a Linear "OpMode". An OpMode is a 'program' that runs in either
 * the autonomous or the teleop period of an FTC match. The names of OpModes appear on the menu
 * of the FTC Driver Station. When an selection is made from the menu, the corresponding OpMode
 * class is instantiated on the Robot Controller and executed.
 *
 * This particular OpMode just executes a basic Tank Drive Teleop for a two wheeled robot
 * It includes all the skeletal structure that all linear OpModes contain.
 *
 * Use Android Studios to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this opmode to the Driver Station OpMode list
 */

@TeleOp(name="Basic: Linear OpMode", group="Linear Opmode")

public class ArmTest extends LinearOpMode {
    private Blinker expansionHub;
    private DcMotor motorArm1;
    private DcMotor motorArm2;
    //private Servo servo1;
    


    @Override
    public void runOpMode() {
        expansionHub = hardwareMap.get(Blinker.class, "Expansion Hub 2");
        motorArm1 = hardwareMap.get(DcMotor.class, "motorArm1");
        motorArm2 = hardwareMap.get(DcMotor.class, "motorArm2");
        //servo1 = hardwareMap.get(Servo.class, "servo1");
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        // reset motors
        motorArm1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorArm2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);


        waitForStart();

        telemetry.addData("Status", "Robot is moving");
        telemetry.update();

        
        
        // run until the end of the match (driver presses STOP)
        
       // Motor1
       
        while (opModeIsActive()) {
            
            if (gamepad1.dpad_up){
                motorArm1.setPower(-0.75);
            
            }
            else motorArm1.setPower(0);{
            
                if (gamepad1.dpad_down){
                    motorArm1.setPower(0.70);
                
                }
                else motorArm1.setPower(0);
            }
        }
        // Motor2
       
        while (opModeIsActive()) {
            
            if (gamepad1.dpad_up){
                motorArm2.setPower(0.75);
            
            }
            else motorArm2.setPower(0);{
            
                if (gamepad1.dpad_down){
                    motorArm2.setPower(-0.70);
                
                }
                else motorArm2.setPower(0);
            }
        }
        
        //Servo for the grabber
        
            //if (gamepad1.a) {
             // works from 0 to 1;
                //servo1.setPosition(0.35);

            }
            //if (gamepad1.b) {
            //works from -0 to -1;
                //servo1.setPosition(0);
    
                
                
                
            }
            
