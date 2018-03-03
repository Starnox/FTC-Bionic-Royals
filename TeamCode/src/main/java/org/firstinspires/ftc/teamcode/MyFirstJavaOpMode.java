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
import org.firstinspires.ftc.robotcontroller.external.samples.SensorMRIrSeeker;
import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;

/**
 * Created by Alex on 1/10/2018.
 */
@Autonomous(name="MyFirstJavaOpMode", group="Linear Opmode")
    public class MyFirstJavaOpMode extends LinearOpMode {
    private HardwarePushbot robot;
    private float const1=11.6033332f;
    private boolean ok1,ok2,ok3;
    @Override
    public void runOpMode() {
        robot = new HardwarePushbot();
        robot.init(hardwareMap);
        ok1=false;
        ok2=false;
        ok3=false;
// Wait for the game to start (driver presses PLAY)
        waitForStart();
        while (opModeIsActive()) {
            /*if (gamepad1.dpad_up)
                const1+=0.000001;
            else if (gamepad1.dpad_down)
                const1-=0.000001;
            if (gamepad1.dpad_right)
                const1+=0.0001;
            else if (gamepad1.dpad_left)
                const1-=0.0001;
            if (gamepad1.a)
            {
                if (!ok1) {
                    rotate_left(720f);
                    ok1 = true;
                }
            } else ok1 = false;
            if (gamepad1.b)
            {
                if (!ok2) {
                    rotate_left(270f);
                    ok2 = true;
                }
            } else ok2 = false;
            if (gamepad1.y)
            {
                if (!ok3) {
                    rotate_left(90f);
                    ok3 = true;
                }
            } else ok3 = false;
            telemetry.addData("Const:",const1);
            telemetry.update();
        */
        robot.sb.setPosition(1f);
        sleep(2000);
        if(robot.sc.red()>robot.sc.blue())
        {
            telemetry.addData("ROSU:",robot.sc.red() + " " + robot.sc.blue());
            telemetry.update();
            balance_fata(70);
            balance_spate(170);
            sleep(1000);
            robot.sb.setPosition(0.1f);
            sleep(1000);
        }
        else
        {
            telemetry.addData("ALBASTRU:",robot.sc.red() + " " + robot.sc.blue());
            telemetry.update();
            balance_spate(170);
            balance_fata(70);
            sleep(1000);
            robot.sb.setPosition(0.1f);
            sleep(1000);
        }
        move_front(85);
        rotate_left(90);
        move_back(10);
        stop();
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
        robot.mbl.setPower(-0.2f);
        robot.mfl.setPower(-0.2f);
        robot.mbr.setPower(0.2f);
        robot.mfr.setPower(0.2f);
        sleep((int)(x*const1));
        //sleep(10000);
        robot.mbl.setPower(0);
        robot.mfl.setPower(0);
        robot.mbr.setPower(0);
        robot.mfr.setPower(0);
        sleep(500);
    }
    public void rotate_left(float x)
    {
        robot.mbl.setPower(0.2f);
        robot.mfl.setPower(0.2f);
        robot.mbr.setPower(-0.2f);
        robot.mfr.setPower(-0.2f);
        sleep((int)(x*const1));
        robot.mbl.setPower(0);
        robot.mfl.setPower(0);
        robot.mbr.setPower(0);
        robot.mfr.setPower(0);
        sleep(500);
    }
    public void balance_fata(int x)
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
    public void balance_spate(int x)
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
}


