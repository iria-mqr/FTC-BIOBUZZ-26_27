package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.util.Debouncer;

public class Intake {
    //variables
    private DcMotorEx intake;
    private double velocity = 500;
    private boolean intaking;
    private Debouncer intakeDebouncer;



    public Intake(HardwareMap hardwareMap){
        intake = hardwareMap.get(DcMotorEx.class, "intake");
        intake.setDirection(DcMotorSimple.Direction.REVERSE);

        intakeDebouncer = new Debouncer(200);
    }


    public void pruebaTeleOp(Gamepad gamepad, Telemetry telemetry){
        if(gamepad.cross && intakeDebouncer.isReady()){
            intaking = !intaking;
        }

        if(intaking){
            intake.setVelocity(velocity);
        }else{
            intake.setVelocity(0);
        }


        if(gamepad.dpad_up){
            velocity += 20;
        }else if(gamepad.dpad_down){
            velocity -= 20;
        }
    }

}