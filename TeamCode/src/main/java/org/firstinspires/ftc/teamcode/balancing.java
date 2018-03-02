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
public class balancing extends LinearOpMode {
    private HardwarePushbot robot;
    private int dir,x,y;
    private boolean ok=false;
    public void runOpMode() {
        robot = new HardwarePushbot();
        robot.init(hardwareMap);
        x=0;
        y=0;
        robot.sb.setPosition(1f);
        waitForStart();
        while (opModeIsActive())
        {
            if (gamepad1.dpad_up)
                x++;
            else if (gamepad2.dpad_down)
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
                        go_1();
                    else
                        go_2();
                    ok = true;
                }
            } else ok = false;
            telemetry.addData("Fata:",x);
            telemetry.addData("Spate:",y);
            telemetry.addData(dir==0?"in spate":"in fata",0);
            telemetry.update();
        }
    }
    void go_1()
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
        robot.mbl.setPower(0.5f);
        robot.mbr.setPower(0.5f);
        robot.mfr.setPower(0.5f);
        robot.mfl.setPower(0.5f);
        sleep(y);
        robot.mbl.setPower(0);
        robot.mbr.setPower(0);
        robot.mfr.setPower(0);
        robot.mfl.setPower(0);
        sleep(500);
    }
    void go_2()
    {
        robot.mbl.setPower(0.5f);
        robot.mbr.setPower(0.5f);
        robot.mfr.setPower(0.5f);
        robot.mfl.setPower(0.5f);
        sleep(y);
        robot.mbl.setPower(0);
        robot.mbr.setPower(0);
        robot.mfr.setPower(0);
        robot.mfl.setPower(0);
        sleep(500);
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

}
