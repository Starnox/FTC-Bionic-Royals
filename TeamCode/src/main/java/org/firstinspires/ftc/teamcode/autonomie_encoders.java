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
    public static float mers=35.29411f,rotit=14.0005f;
    float v1[]={0.71f,0.56f,0f},v2[]={0.29f,0.44f,1f};
    public void runOpMode() {
        robot = new HardwarePushbot();
        robot.init(hardwareMap);
        robot.mfl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.mfr.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.mbl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.mbr.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.up1.setPosition(v1[1]);
        robot.up2.setPosition(v2[1]);
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
            push_red();
            move_front(79);
            rotate_left(90);
            //up();
            setpoz(2);
           // down();
            move_back(20);
            move_front(15);
            setpoz(1);
            stop();
        }
        }

    void push_blue()
    {
        robot.sb.setPosition(1f);
        sleep(2000);
        if(robot.sc.red()>robot.sc.blue())
        {
            telemetry.addData("ROSU:",robot.sc.red() + " " + robot.sc.blue());
            telemetry.update();
            move_front(4.5f);
            move_back(10f);
            sleep(1000);
            robot.sb.setPosition(0.1f);
            sleep(1000);
        }
        else
        {
            telemetry.addData("ALBASTRU:",robot.sc.red() + " " + robot.sc.blue());
            telemetry.update();
            move_back(10f);
            move_front(4.5f);
            sleep(1000);
            robot.sb.setPosition(0.1f);
            sleep(1000);
        }
    }

    void push_red()
    {
        robot.sb.setPosition(1f);
        sleep(2000);
        if(robot.sc.red()<robot.sc.blue())
        {
            telemetry.addData("ALBASTRU:",robot.sc.red() + " " + robot.sc.blue());
            telemetry.update();
            move_front(4.5f);
            move_back(7f);
            sleep(1000);
            robot.sb.setPosition(0.1f);
            sleep(1000);
        }
        else
        {
            telemetry.addData("ROSU:",robot.sc.red() + " " + robot.sc.blue());
            telemetry.update();
            move_back(10f);
            move_front(4.5f);
            sleep(1000);
            robot.sb.setPosition(0.1f);
            sleep(1000);
            move_front(5f);
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

    public void rotate_left(float x)
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
    public void rotate_right(float x)
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
    void balance_fata(int x)
    {
        robot.mbl.setPower(-0.5f);
        robot.mbr.setPower(-0.5f);
        robot.mfr.setPower(-0.5f);
        robot.mfl.setPower(-0.5f);
        sleep(x);
        robot.mbl.setPower(0);
        robot.mbr.setPower(0);
        robot.mfr.setPower(0);
        robot.mfl.setPower(0);
        sleep(500);
    }
    void balance_spate(int x)
    {
        robot.mbl.setPower(0.5f);
        robot.mbr.setPower(0.5f);
        robot.mfr.setPower(0.5f);
        robot.mfl.setPower(0.5f);
        sleep(x);
        robot.mbl.setPower(0);
        robot.mbr.setPower(0);
        robot.mfr.setPower(0);
        robot.mfl.setPower(0);
        sleep(500);
    }
    void up()
    {
        robot.mr.setTargetPosition(3200);
        robot.mr.setPower(0.3f);
        while(robot.mr.isBusy()) {
        }
    }
    void down()
    {
        robot.mr.setTargetPosition(0);
        robot.mr.setPower(0.3f);
        while(robot.mr.isBusy()) {
        }
    }
    void setpoz(int poz)
    {
        robot.up1.setPosition(v1[poz]);
        robot.up2.setPosition(v2[poz]);
        sleep(1000);
    }

}

