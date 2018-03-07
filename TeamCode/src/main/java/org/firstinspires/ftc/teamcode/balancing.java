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
@Autonomous(name="balancing", group="Linear Opmode")
public class balancing extends LinearOpMode {
    private HardwarePushbot robot;
    private int dir,x,y;
    public static float rotit=14.0005f;
    private boolean ok=false;
    public void runOpMode() {
        robot = new HardwarePushbot();
        robot.init(hardwareMap);
        x=0;
        y=0;
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
            if (gamepad1.dpad_up)
            x++;
        else if (gamepad1.dpad_down)
            x--;
            if (gamepad1.dpad_right)
                y++;
            else if (gamepad1.dpad_left)
                y--;
            if (gamepad1.y)
                dir = 0;
            if (gamepad1.b)
                dir = 1;
            if (gamepad1.a)
            {
                if (!ok) {
                    if (dir == 1)
                        push_front();
                    else
                        push_back();
                    ok = true;
                }
            } else ok = false;
            telemetry.addData("Fata:",x/1000);
            telemetry.addData("Spate:",y/1000);
            telemetry.addData(dir==0?"in spate":"in fata",0);
            telemetry.update();
        }
    }
    void push_front()
    {
        /*robot.sb.setPosition(1f);
        sleep(2000);
        balance_front((int)x/1000);
        robot.sb.setPosition(0.1f);
        sleep(2000);*/
        robot.sb.setPosition(1f);
        sleep(1000);
        rotate_right((int)x/1000);
        rotate_left((int)x/1000);
        robot.sb.setPosition(0.1f);
        sleep(1000);
    }
    void push_back()
    {
        /*robot.sb.setPosition(1f);
        sleep(2000);
        balance_back((int)y/1000);
        robot.sb.setPosition(0.1f);
        sleep(2000);
        balance_front((int)x/1000);*/
        robot.sb.setPosition(1f);
        sleep(1000);
        rotate_left((int)x/1000);
        rotate_right((int)x/1000);
        robot.sb.setPosition(0.1f);
        sleep(1000);
    }
    public void balance_front(float x)
    {
        robot.mbl.setTargetPosition((int)(robot.mbl.getCurrentPosition()-x));
        robot.mbr.setTargetPosition((int)(robot.mbr.getCurrentPosition()-x));
        robot.mfr.setTargetPosition((int)(robot.mfr.getCurrentPosition()-x));
        robot.mfl.setTargetPosition((int)(robot.mfl.getCurrentPosition()-x));
        robot.mbl.setPower(0.5f);
        robot.mbr.setPower(0.5f);
        robot.mfr.setPower(0.5f);
        robot.mfl.setPower(0.5f);
        while(robot.mbr.isBusy()||robot.mbl.isBusy()||robot.mfl.isBusy()||robot.mfr.isBusy())
        {
        }
    }

    public void balance_back(float x)
    {
        robot.mbl.setTargetPosition((int)(robot.mbl.getCurrentPosition()+x));
        robot.mbr.setTargetPosition((int)(robot.mbr.getCurrentPosition()+x));
        robot.mfr.setTargetPosition((int)(robot.mfr.getCurrentPosition()+x));
        robot.mfl.setTargetPosition((int)(robot.mfl.getCurrentPosition()+x));
        robot.mbl.setPower(0.5f);
        robot.mbr.setPower(0.5f);
        robot.mfr.setPower(0.5f);
        robot.mfl.setPower(0.5f);
        while(robot.mbr.isBusy()||robot.mbl.isBusy()||robot.mfl.isBusy()||robot.mfr.isBusy())
        {

        }
    }
    void rotate_left(float x)
    {
        robot.mbl.setTargetPosition((int)(robot.mbl.getCurrentPosition()+x*rotit));
        robot.mbr.setTargetPosition((int)(robot.mbr.getCurrentPosition()-x*rotit));
        robot.mfr.setTargetPosition((int)(robot.mfr.getCurrentPosition()-x*rotit));
        robot.mfl.setTargetPosition((int)(robot.mfl.getCurrentPosition()+x*rotit));
        robot.mbl.setPower(0.2f);
        robot.mbr.setPower(0.2f);
        robot.mfr.setPower(0.2f);
        robot.mfl.setPower(0.2f);
        while(robot.mbr.isBusy()||robot.mbl.isBusy()||robot.mfl.isBusy()||robot.mfr.isBusy())
        {
        }
    }
    void rotate_right(float x)
    {
        robot.mbl.setTargetPosition((int)(robot.mbl.getCurrentPosition()-x*rotit));
        robot.mbr.setTargetPosition((int)(robot.mbr.getCurrentPosition()+x*rotit));
        robot.mfr.setTargetPosition((int)(robot.mfr.getCurrentPosition()+x*rotit));
        robot.mfl.setTargetPosition((int)(robot.mfl.getCurrentPosition()-x*rotit));
        robot.mbl.setPower(0.2f);
        robot.mbr.setPower(0.2f);
        robot.mfr.setPower(0.2f);
        robot.mfl.setPower(0.2f);
        while(robot.mbr.isBusy()||robot.mbl.isBusy()||robot.mfl.isBusy()||robot.mfr.isBusy())
        {
        }
    }
}
