package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.util.Debouncer;

public class Shooter {
    private DcMotorEx shooter;
    private double velocity = 500;
    private boolean shooting;
    private Debouncer shooterDebouncer;



    public Shooter(HardwareMap hardwareMap){
        shooter = hardwareMap.get(DcMotorEx.class, "shooter");
        shooter.setDirection(DcMotorSimple.Direction.REVERSE);

        intakeDebouncer = new Debouncer(200);
    }

    public void forwardMovement() {}

    public void strafingMovement(){}

    public void rotatingMovement(){}

    public void pruebaTeleOp(Gamepad gamepad, Telemetry telemetry){
        if(gamepad.cross && intakeDebouncer.isReady()){
            shooting = !shooting;
        }

        if(shooting){
            shooter.setVelocity(velocity);
        }else{
            shooter.setVelocity(0);
        }


        if(gamepad.dpad_up){
            velocity += 20;
        }else if(gamepad.dpad_down){
            velocity -= 20;
        }
    }
}
