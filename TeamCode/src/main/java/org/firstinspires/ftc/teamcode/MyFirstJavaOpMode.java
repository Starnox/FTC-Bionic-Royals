package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.Gyroscope;
import com.qualcomm.robotcore.hardware.OpticalDistanceSensor;
import com.qualcomm.robotcore.hardware.Servo;
import org.firstinspires.ftc.robotcontroller.external.samples.ConceptVuMarkIdentification;
import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;

/**
 * Created by Alex on 1/10/2018.
 */
@Autonomous(name="MyFirstJavaOpMode", group="Linear Opmode")
    public class MyFirstJavaOpMode extends LinearOpMode {
    private Gyroscope gyro;
    private ColorSensor sc;
    private OpticalDistanceSensor ods;
    private Servo sb;
    private DcMotor mfl = null;
    private DcMotor mfr = null;
    private DcMotor mbl = null;
    private DcMotor mbr = null;
    @Override
    public void runOpMode() {
        gyro = hardwareMap.get(Gyroscope.class, "gyro");
        mfl = hardwareMap.get(DcMotor.class, "mfl");
        mfr = hardwareMap.get(DcMotor.class, "mfr");
        mbl = hardwareMap.get(DcMotor.class, "mbl");
        mbr = hardwareMap.get(DcMotor.class, "mbr");
        ods = hardwareMap.get(OpticalDistanceSensor.class, "ods");
        sb = hardwareMap.get(Servo.class, "sb");
        sc = hardwareMap.get(ColorSensor.class, "sc");
        mfr.setDirection(DcMotor.Direction.REVERSE);
        mbr.setDirection(DcMotor.Direction.REVERSE);

        sb.setPosition(0.9);
// Wait for the game to start (driver presses PLAY)
        waitForStart();
        while (opModeIsActive()) {
            if(sc.red() > sc.blue()){
                telemetry.addData("color:","red");
                telemetry.update();
                mfl.setPower(0.5);
                mfr.setPower(0.5);
                mbl.setPower(0.5);
                mbr.setPower(0.5);
                sleep(200);
                mfl.setPower(0);
                mfr.setPower(0);
                mbl.setPower(0);
                mbr.setPower(0);
                sleep(200);
                mfl.setPower(-0.5);
                mbl.setPower(-0.5);
                mfr.setPower(-0.5);
                mbr.setPower(-0.5);
                sleep(200);
                mfl.setPower(0);
                mfr.setPower(0);
                mbl.setPower(0);
                mbr.setPower(0);
                sb.setPosition(0.1);
            }
            else
            {
                telemetry.addData("color:","blue");
                telemetry.update();
                mfl.setPower(-0.5);
                mfr.setPower(-0.5);
                mbl.setPower(-0.5);
                mbr.setPower(-0.5);
                sleep(200);
                mfl.setPower(0);
                mfr.setPower(0);
                mbl.setPower(0);
                mbr.setPower(0);
                sleep(200);
                mfl.setPower(0.5);
                mfr.setPower(0.5);
                mbl.setPower(0.5);
                mbr.setPower(0.5);
                sleep(200);
                mfl.setPower(0);
                mfr.setPower(0);
                mbl.setPower(0);
                mbr.setPower(0);
                sb.setPosition(0.1);
            }
            stop();
        }

    }
}


