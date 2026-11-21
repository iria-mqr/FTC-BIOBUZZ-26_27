package org.firstinspires.ftc.teamcode;



import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;


import org.firstinspires.ftc.teamcode.subsystems.Intake;
import org.firstinspires.ftc.teamcode.subsystems.SimpleDriveTrain;

@TeleOp
public class testTeleOpMode extends OpMode {
    SimpleDriveTrain driveTrain;
    Intake intake;

    @Override
    public void init() {
        driveTrain = new SimpleDriveTrain(hardwareMap);
        intake = new Intake(hardwareMap);
    }

    @Override
    public void start(){
        gamepad1.rumble(20);
        gamepad2.rumble(20);
    }


    @Override
    public void loop() {
        driveTrain.TeleOp(gamepad1, telemetry);
        intake.pruebaTeleOp(gamepad1, telemetry);

        telemetry.update();
    }

}
