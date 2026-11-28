package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.util.Debouncer;

public class Shooter {
    private DcMotorEx shooter;
    private double velocity = 1500;
    private boolean shooting;
    private Debouncer shooterDebouncer;



    public Shooter(HardwareMap hardwareMap){
        shooter = hardwareMap.get(DcMotorEx.class, "shooter");


        shooterDebouncer = new Debouncer(200);
    }

    public void pruebaTeleOp(Gamepad gamepad, Telemetry telemetry){


        if(gamepad.right_trigger > 0.9){
            shooter.setVelocity(velocity);
        }else{
            shooter.setVelocity(0);
        }


        if(gamepad.dpad_right){
            velocity += 20;
        }else if(gamepad.dpad_left){
            velocity -= 20;
        }

        telemetry.addData("shooter velocity", velocity);
    }
}
