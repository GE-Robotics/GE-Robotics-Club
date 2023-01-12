package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import java.lang.annotation.Target;
import com.qualcomm.robotcore.hardware.Blinker;
import com.qualcomm.robotcore.hardware.Gyroscope;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.hardware.AnalogInput;


@Autonomous

public class Auto extends LinearOpMode {
    private Blinker expansion_Hub_2;
    private Gyroscope imu;
    private DcMotor motor0;
    private DcMotor motor1;
    private DcMotor motor2;
    private DcMotor motor3;
    


    // todo: write your code here
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
        
        
        // run until the end of the match (driver presses STOP)
        E
        
        
        
        
        
        \
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        lapsedTime runtime = new ElapsedTime();
    
        while (opModeIsActive()) { 
            
            while (runtime.seconds() <= 3.0 || runtime.seconds() >= 8.0) {
                motor0.setPower(0);
                motor1.setPower(0);
            } 
            while (runtime.seconds() >= 3.0 && runtime.seconds() <= 8.0) {
                motor0.setPower(1);
                motor1.setPower(-1);
            }
        }   
    }
}

