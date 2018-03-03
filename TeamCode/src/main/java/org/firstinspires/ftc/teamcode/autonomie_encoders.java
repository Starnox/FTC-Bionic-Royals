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
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.OpticalDistanceSensor;
import com.qualcomm.robotcore.hardware.Servo;
import org.firstinspires.ftc.robotcontroller.external.samples.ConceptVuMarkIdentification;
import org.firstinspires.ftc.robotcontroller.external.samples.HardwarePushbot;
import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;

/**
 * Created by Alex on 1/10/2018.
 */
@Autonomous(name="autonomie_encders", group="Linear Opmode")
public class autonomie_encoders extends LinearOpMode {
    private HardwarePushbot robot;
    public static float mers=35.29411f,rotit=14.0005f ;
    public void runOpMode() {
        robot = new HardwarePushbot();
        robot.init(hardwareMap);
        robot.mfl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.mfr.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.mbl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.mbr.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        waitForStart();
        robot.mfl.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.mfr.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.mbl.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.mbr.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            while (opModeIsActive())
        {
            telemetry.addData("MFL:",robot.mfl.getCurrentPosition());
            telemetry.addData("MFR:",robot.mfr.getCurrentPosition());
            telemetry.addData("MBL:",robot.mbl.getCurrentPosition());
            telemetry.addData("MBR:",robot.mbr.getCurrentPosition());
            telemetry.update();
            rotate_right(90);
            sleep(2000);
            rotate_right(180);
            sleep(2000);
            rotate_right(270);
            sleep(2000);
            rotate_right(360);
            sleep(2000);
            rotate_right(720);
            sleep(2000);
            stop();
        }
        }

    public void move_front(float x)
    {
        robot.mbl.setTargetPosition((int)(robot.mbl.getCurrentPosition()-x*mers));
        robot.mbr.setTargetPosition((int)(robot.mbr.getCurrentPosition()-x*mers));
        robot.mfr.setTargetPosition((int)(robot.mfr.getCurrentPosition()-x*mers));
        robot.mfl.setTargetPosition((int)(robot.mfl.getCurrentPosition()-x*mers));
        robot.mbl.setPower(0.5f);
        robot.mbr.setPower(0.5f);
        robot.mfr.setPower(0.5f);
        robot.mfl.setPower(0.5f);
        while(robot.mbr.isBusy()||robot.mbl.isBusy()||robot.mfl.isBusy()||robot.mfr.isBusy())
        {
            telemetry.addData("MFL:",robot.mfl.getCurrentPosition());
            telemetry.addData("MFR:",robot.mfr.getCurrentPosition());
            telemetry.addData("MBL:",robot.mbl.getCurrentPosition());
            telemetry.addData("MBR:",robot.mbr.getCurrentPosition());
            telemetry.update();
        }
    }

    public void move_back(float x)
    {
        robot.mbl.setTargetPosition((int)(robot.mbl.getCurrentPosition()+x*mers));
        robot.mbr.setTargetPosition((int)(robot.mbr.getCurrentPosition()+x*mers));
        robot.mfr.setTargetPosition((int)(robot.mfr.getCurrentPosition()+x*mers));
        robot.mfl.setTargetPosition((int)(robot.mfl.getCurrentPosition()+x*mers));
        robot.mbl.setPower(0.5f);
        robot.mbr.setPower(0.5f);
        robot.mfr.setPower(0.5f);
        robot.mfl.setPower(0.5f);
        while(robot.mbr.isBusy()||robot.mbl.isBusy()||robot.mfl.isBusy()||robot.mfr.isBusy())
        {
            telemetry.addData("MFL:",robot.mfl.getCurrentPosition());
            telemetry.addData("MFR:",robot.mfr.getCurrentPosition());
            telemetry.addData("MBL:",robot.mbl.getCurrentPosition());
            telemetry.addData("MBR:",robot.mbr.getCurrentPosition());
            telemetry.update();
        }
    }

    public void rotate_right(float x)
    {
        robot.mbl.setTargetPosition((int)(robot.mbl.getCurrentPosition()+x*rotit));
        robot.mbr.setTargetPosition((int)(robot.mbr.getCurrentPosition()-x*rotit));
        robot.mfr.setTargetPosition((int)(robot.mfr.getCurrentPosition()-x*rotit));
        robot.mfl.setTargetPosition((int)(robot.mfl.getCurrentPosition()+x*rotit));
        robot.mbl.setPower(0.5f);
        robot.mbr.setPower(0.5f);
        robot.mfr.setPower(0.5f);
        robot.mfl.setPower(0.5f);
        while(robot.mbr.isBusy()||robot.mbl.isBusy()||robot.mfl.isBusy()||robot.mfr.isBusy())
        {
            telemetry.addData("MFL:",robot.mfl.getCurrentPosition());
            telemetry.addData("MFR:",robot.mfr.getCurrentPosition());
            telemetry.addData("MBL:",robot.mbl.getCurrentPosition());
            telemetry.addData("MBR:",robot.mbr.getCurrentPosition());
            telemetry.update();
        }
    }
    public void rotate_left(float x)
    {
        robot.mbl.setTargetPosition((int)(robot.mbl.getCurrentPosition()-x*rotit));
        robot.mbr.setTargetPosition((int)(robot.mbr.getCurrentPosition()+x*rotit));
        robot.mfr.setTargetPosition((int)(robot.mfr.getCurrentPosition()+x*rotit));
        robot.mfl.setTargetPosition((int)(robot.mfl.getCurrentPosition()-x*rotit));
        robot.mbl.setPower(0.5f);
        robot.mbr.setPower(0.5f);
        robot.mfr.setPower(0.5f);
        robot.mfl.setPower(0.5f);
        while(robot.mbr.isBusy()||robot.mbl.isBusy()||robot.mfl.isBusy()||robot.mfr.isBusy())
        {
            telemetry.addData("MFL:",robot.mfl.getCurrentPosition());
            telemetry.addData("MFR:",robot.mfr.getCurrentPosition());
            telemetry.addData("MBL:",robot.mbl.getCurrentPosition());
            telemetry.addData("MBR:",robot.mbr.getCurrentPosition());
            telemetry.update();
        }
    }

}

