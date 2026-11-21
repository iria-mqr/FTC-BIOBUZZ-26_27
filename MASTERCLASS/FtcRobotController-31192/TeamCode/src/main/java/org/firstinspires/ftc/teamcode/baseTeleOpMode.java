package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;


@TeleOp
public class baseTeleOpMode extends OpMode {
    //aqui variables globales y atributos
   double contador;
    DcMotorEx dcMotorEx;
    Servo iria;
    CRServo aparicio;
    TouchSensor touchSensor;
    DistanceSensor distanceSensor;

    @Override
    public void init() {
        telemetry.addLine("31192");
        dcMotorEx = hardwareMap.get(DcMotorEx.class, "motor");
        iria = hardwareMap.get(Servo.class, "Servo plano");
        aparicio = hardwareMap.get(CRServo.class, "Servo circulo");
        touchSensor = hardwareMap.get(TouchSensor.class,"touchsensor");
        distanceSensor = hardwareMap.get(DistanceSensor.class, "Distancesensor");
   }

    @Override
    public void start(){

    }

    @Override
    public void loop() {
        //ej 1
        if (touchSensor.isPressed()){
            contador=contador +1;
        }

        if (contador ==25){
            gamepad1.rumble(123456789);
        }

        //ej 2
        dcMotorEx.setVelocity(contador);

        if (gamepad1.left_bumper){
            aparicio.setPower(-1);
        }else if (gamepad1.right_bumper) {
            aparicio.setPower(1);
        }else aparicio.setPower(0);
        if (gamepad1.cross){
            if (iria.getPosition() > 0.9){
                iria.setPosition(0);
            }else if (iria.getPosition() < 0.1){
                iria.setPosition(1);
            }

        }
        telemetry.addData("distancia",distanceSensor.getDistance(DistanceUnit.CM));

        telemetry.addData("valor contador",contador);
        telemetry.update();
    }

}
