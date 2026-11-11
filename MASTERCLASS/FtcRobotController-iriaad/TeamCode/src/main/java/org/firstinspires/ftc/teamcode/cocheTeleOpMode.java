package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.util.Debouncer;


@TeleOp
public class cocheTeleOpMode extends OpMode {
    //atributos
    public int marcha;
    public double[] vMarchas;
    public boolean r;
    public boolean warning;

    //componentes
    DcMotorEx motor1;
    DcMotorEx motor2;
    Servo lParabrisas;
    TouchSensor botonLP;
    DistanceSensor warningSensor;


    //debouncers
    Debouncer debouncerMarchas;
    Debouncer debouncerR;



    @Override
    public void init() {
        //inicio variables
        marcha = 0;
        vMarchas = new double[]{0, 500, 750, 1000, 1250, 1500, 1700};
        r = false;
        warning = false;

        //hardwareMap
        motor1 = hardwareMap.get(DcMotorEx.class, "motor1");
        motor2 = hardwareMap.get(DcMotorEx.class, "motor2");
        lParabrisas = hardwareMap.get(Servo.class, "lParabrisas");
        botonLP = hardwareMap.get(TouchSensor.class, "botonLP");
        warningSensor = hardwareMap.get(DistanceSensor.class, "warningSensor");

        //debouncers
        debouncerMarchas = new Debouncer(200);
        debouncerR = new Debouncer(200);
    }

    @Override
    public void start(){

    }

    @Override
    public void loop() {
        ///MARCHAS
        //subir/bajar
        if (marcha<6 && gamepad1.dpad_up && debouncerMarchas.isReady()){
            marcha++;
        }else if(marcha>1 && gamepad1.dpad_down && debouncerMarchas.isReady()){
            marcha--;
        }

        //retroceso
        if(gamepad1.options && debouncerR.isReady()){
            r = !r;
        }

        ///ACELERADOR/FRENO
        if(gamepad1.right_trigger_pressed && !warning){
            //velocidad
            if(r){
                setVelocityMotors(-500);
            }else{
                setVelocityMotors(vMarchas[marcha]);
            }
        } else if (gamepad1.left_trigger_pressed || warning) {
            setVelocityMotors(0);
        }


        ///WARNING
        warning = (warningSensor.getDistance(DistanceUnit.CM)<5);

        ///LIMPIAPARABRISAS
        if(botonLP.isPressed()){
            if(lParabrisas.getPosition()>0.9){
                lParabrisas.setPosition(0);
            }else if(lParabrisas.getPosition()<0.1){
                lParabrisas.setPosition(1);
            }
        }


        ///DATOS POR TELEMETRIA
        telemetry.addLine("Coche en marcha...");
        telemetry.addData("marcha",marcha);
        telemetry.addData("velocidad",motor1.getVelocity());
        telemetry.addData("marcha",marcha);

        if(warning)
            telemetry.addLine("warning");


        telemetry.update();
    }


    void setVelocityMotors(double v){
        motor1.setVelocity(v);
        motor2.setVelocity(v);
    }

}
