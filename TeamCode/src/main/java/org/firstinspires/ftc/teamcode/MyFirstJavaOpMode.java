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
@Autonomous(name="MyFirstJavaOpMode", group="Linear Opmode")
    public class MyFirstJavaOpMode extends LinearOpMode {
    private HardwarePushbot robot;
    /*private Gyroscope gyro;
    private ColorSensor sc;
    private OpticalDistanceSensor ods;
    private Servo sb;
    private DcMotor mfl = null;
    private DcMotor mfr = null;
    private DcMotor mbl = null;
    private DcMotor mbr = null;*/
    @Override
    public void runOpMode() {
        robot = new HardwarePushbot();
        robot.init(hardwareMap);
       /* gyro = hardwareMap.get(Gyroscope.class, "gyro");
        mfl = hardwareMap.get(DcMotor.class, "mfl");
        mfr = hardwareMap.get(DcMotor.class, "mfr");
        mbl = hardwareMap.get(DcMotor.class, "mbl");
        mbr = hardwareMap.get(DcMotor.class, "mbr");
        ods = hardwareMap.get(OpticalDistanceSensor.class, "ods");
        sb = hardwareMap.get(Servo.class, "sb");
        sc = hardwareMap.get(ColorSensor.class, "sc");
        mfr.setDirection(DcMotor.Direction.REVERSE);
        mbr.setDirection(DcMotor.Direction.REVERSE);*/
        //robot.sb.setPosition(1f);
// Wait for the game to start (driver presses PLAY)
        waitForStart();
        while (opModeIsActive()) {
        telemetry.addData("Direction:",robot.cmp.getDirection());
        telemetry.update();
        rotate_left(720);
        stop();
        /*sleep(500);
        if(robot.sc.red()>robot.sc.blue())
        {
            move_front(8);
            sleep(2000);
            move_back(8);
            sleep(2000);
            robot.sb.setPosition(0.8f);
        }
        else
        {
            move_back(8);
            sleep(2000);
            move_front(8);
            sleep(2000);
            robot.sb.setPosition(0.8f);
        }*/
        //stop();

            /*if(sc.red() > sc.blue()){
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
            stop();*/
        }

    }
    public void move_front(float x)
    {
        robot.mbl.setPower(-0.5f);
        robot.mbr.setPower(-0.5f);
        robot.mfr.setPower(-0.5f);
        robot.mfl.setPower(-0.5f);
        sleep((int)(x*15.625f));
        robot.mbl.setPower(0);
        robot.mbr.setPower(0);
        robot.mfr.setPower(0);
        robot.mfl.setPower(0);
        sleep(200);
    }
    public void move_back(float x)
    {
        robot.mbl.setPower(0.5f);
        robot.mbr.setPower(0.5f);
        robot.mfr.setPower(0.5f);
        robot.mfl.setPower(0.5f);
        sleep((int)(x*15.625f));
        robot.mbl.setPower(0);
        robot.mbr.setPower(0);
        robot.mfr.setPower(0);
        robot.mfl.setPower(0);
        sleep(200);
    }

    public void rotate_right(float x)
    {
        robot.mbl.setPower(-0.5f);
        robot.mfl.setPower(-0.5f);
        robot.mbr.setPower(0.5f);
        robot.mfr.setPower(0.5f);
        sleep((int)(x*6.45f));
        robot.mbl.setPower(0);
        robot.mfl.setPower(0);
        robot.mbr.setPower(0);
        robot.mfr.setPower(0);
        sleep(200);
    }
    public void rotate_left(float x)
    {
        robot.mbl.setPower(0.5f);
        robot.mfl.setPower(0.5f);
        robot.mbr.setPower(-0.5f);
        robot.mfr.setPower(-0.5f);
        sleep((int)(x*6.45f));
        robot.mbl.setPower(0);
        robot.mfl.setPower(0);
        robot.mbr.setPower(0);
        robot.mfr.setPower(0);
        sleep(200);
    }
    /*public void rotate_left(float x)
    {
        float direction = (float)robot.cmp.getDirection();
        robot.mbl.setPower(0.5f);
        robot.mfl.setPower(0.5f);
        robot.mbr.setPower(-0.5f);
        robot.mfr.setPower(-0.5f);
        while((direction-x)<(float)robot.cmp.getDirection())
            sleep(1);
        robot.mbl.setPower(0);
        robot.mfl.setPower(0);
        robot.mbr.setPower(0);
        robot.mfr.setPower(0);
    }*/
}


