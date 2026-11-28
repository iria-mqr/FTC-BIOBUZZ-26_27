package org.firstinspires.ftc.teamcode;


import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.YawPitchRollAngles;


@TeleOp
public class baseTeleOpMode extends OpMode {
    //aqui variables globales y atributos
    double velocity = 0;
    DcMotor frontRight,frontLeft,backRight,backLeft,intake;
    IMU imu;
    public YawPitchRollAngles orientation;



    @Override
    public void init() {
        frontRight = hardwareMap.get(DcMotor.class, "frontRight");
        frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        backRight = hardwareMap.get(DcMotor.class, "backRight");
        backLeft = hardwareMap.get(DcMotor.class, "backLeft");
        intake = hardwareMap.get(DcMotor.class, "intake");

        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        intake.setDirection(DcMotorSimple.Direction.REVERSE);

        //IMU
        imu = hardwareMap.get(IMU.class, "imu");
        IMU.Parameters parameters = new IMU.Parameters(new RevHubOrientationOnRobot(
                RevHubOrientationOnRobot.LogoFacingDirection.LEFT,
                RevHubOrientationOnRobot.UsbFacingDirection.UP
        ));
        imu.initialize(parameters);
    }

    @Override
    public void start(){

    }

    @Override
    public void loop() {
            orientation = imu.getRobotYawPitchRollAngles();
            double yawAngle = orientation.getYaw(AngleUnit.RADIANS);

            double drive = -gamepad1.left_stick_y * 1;
            double strafe = gamepad1.left_stick_x * 1;
            double rotate = gamepad1.right_stick_x * 1;

            double headingRad = Math.toRadians(yawAngle);

            double rotX = drive * Math.cos(yawAngle) - strafe * Math.sin(yawAngle);
            double rotY = drive * Math.sin(yawAngle) + strafe * Math.cos(yawAngle);

            double frontLeftPower  = rotX + rotY + rotate;
            double frontRightPower = rotX - rotY - rotate;
            double backLeftPower   = rotX - rotY + rotate;
            double backRightPower  = rotX + rotY - rotate;

            frontLeft.setPower(frontLeftPower);
            frontRight.setPower(frontRightPower);
            backLeft.setPower(backLeftPower);
            backRight.setPower(backRightPower);
            intake.setPower(0.5);
    }

}
