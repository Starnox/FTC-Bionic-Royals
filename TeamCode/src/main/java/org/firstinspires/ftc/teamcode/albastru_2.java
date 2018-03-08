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
import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
import org.firstinspires.ftc.robotcore.external.matrices.VectorF;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackableDefaultListener;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;

/**
 * Created by Alex on 1/10/2018.
 */
@Autonomous(name="albastru_2", group="Linear Opmode")
public class albastru_2 extends LinearOpMode {
    private HardwarePushbot robot;
    public static float mers=35.29411f,rotit=14.0005f;
    float v1[]={0.64f,0.5f,0f},v2[]={0.36f,0.5f,1f},dist[]={93,103,122};
    int target;
    VuforiaLocalizer vuforia;
    OpenGLMatrix pose;
    int cameraMonitorViewId;
    VuforiaTrackables relicTrackables;
    VuforiaTrackable relicTemplate;
    VuforiaLocalizer.Parameters parameters;
    RelicRecoveryVuMark vuMark;
    public void runOpMode() {
        robot = new HardwarePushbot();
        robot.init(hardwareMap);
        robot.mfl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.mfr.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.mbl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.mbr.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.up1.setPosition(v1[1]);
        robot.up2.setPosition(v2[1]);
        initPose();
        waitForStart();
        robot.mfl.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.mfr.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.mbl.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.mbr.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        while (opModeIsActive())
        {
            getTarget();
            telemetry.addData("target:",target);
            push_red();
            move_back(15);
            rotate_right(90,0.5f);
            move_back(dist[target]);
            rotate_left(90,0.5f);
            setpoz(2);
            move_back(10);
            move_front(10);
            setpoz(1);
            move_back(6);
            stop();
        }
    }

    void push_blue()
    {
        robot.sb.setPosition(1f);
        sleep(2000);
        if(robot.sc.red()>robot.sc.blue())
        {
            push_front();
        }
        else
        {
            push_back();
        }
    }

    void push_red()
    {
        robot.sb.setPosition(1f);
        sleep(2000);
        if(robot.sc.red()<robot.sc.blue())
        {
            push_front();
        }
        else
        {
            push_back();
        }
        balance_back(1750);
    }

    void move_front(float x)
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
        }
    }

    void move_back(float x)
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
        }
    }

    void rotate_left(float x,float t)
    {
        robot.mbl.setTargetPosition((int)(robot.mbl.getCurrentPosition()+x*rotit));
        robot.mbr.setTargetPosition((int)(robot.mbr.getCurrentPosition()-x*rotit));
        robot.mfr.setTargetPosition((int)(robot.mfr.getCurrentPosition()-x*rotit));
        robot.mfl.setTargetPosition((int)(robot.mfl.getCurrentPosition()+x*rotit));
        robot.mbl.setPower(t);
        robot.mbr.setPower(t);
        robot.mfr.setPower(t);
        robot.mfl.setPower(t);
        while(robot.mbr.isBusy()||robot.mbl.isBusy()||robot.mfl.isBusy()||robot.mfr.isBusy())
        {
        }
    }
    void rotate_right(float x,float t)
    {
        robot.mbl.setTargetPosition((int)(robot.mbl.getCurrentPosition()-x*rotit));
        robot.mbr.setTargetPosition((int)(robot.mbr.getCurrentPosition()+x*rotit));
        robot.mfr.setTargetPosition((int)(robot.mfr.getCurrentPosition()+x*rotit));
        robot.mfl.setTargetPosition((int)(robot.mfl.getCurrentPosition()-x*rotit));
        robot.mbl.setPower(t);
        robot.mbr.setPower(t);
        robot.mfr.setPower(t);
        robot.mfl.setPower(t);
        while(robot.mbr.isBusy()||robot.mbl.isBusy()||robot.mfl.isBusy()||robot.mfr.isBusy())
        {
        }
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
    void push_front()
    {
        rotate_right(15,0.5f);
        rotate_left(15,0.5f);
        robot.sb.setPosition(0.1f);
        sleep(1000);
    }
    void push_back()
    {
        rotate_left(15,0.5f);
        rotate_right(15,0.5f);
        robot.sb.setPosition(0.1f);
        sleep(1000);
    }
    /* void push_front2()
     {
         robot.sb.setPosition(1f);
         sleep(2000);
         balance_back(1750);
         robot.sb.setPosition(0.1f);
         sleep(2000);
     }
     void push_back2()
     {
         robot.sb.setPosition(1f);
         sleep(2000);
         balance_front(800);
         robot.sb.setPosition(0.1f);
         sleep(2000);
         balance_back(2520);
     }*/
    void balance_front(float x)
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

    void balance_back(float x)
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
    void initPose()
    {
        cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        parameters = new VuforiaLocalizer.Parameters(cameraMonitorViewId);


        parameters.vuforiaLicenseKey = " AfskXVz/////AAAAmVUQUJaKPkpnoCuHva4WBHp7gHrMII3cdGcUGQd5dtWp2UD5UEJL3PxVqiF65V/8Il/lOcm2ce4l4GntFtvH53aaJXHIgNdwgyxSDdstWn2tGJMIn8SFkcKs+h/aGypSX/hUG9MmaUdAVCbrXA1W7+euYEhZjs1tAHButW4T7kZx8tObN4kvM14eX1mGdDAz2I3KhNfkjWP3X+vYibQlClN+i3k5IwpYJdZhYYydFyaWYir0QSsYKmriA/qSbw1RqGUGD7t38EbhmXGslGNVTgQxukKhcO1LlXEiZx/wx1O9MEfObcD1dSSjwI1zQPqRWBFO2tWVWg6HkK9j1IV9YA9LBMwDXsMiC5DztTB6/LZ7 ";

        parameters.cameraDirection = VuforiaLocalizer.CameraDirection.BACK;
        this.vuforia = ClassFactory.createVuforiaLocalizer(parameters);

        relicTrackables = this.vuforia.loadTrackablesFromAsset("RelicVuMark");
        relicTemplate = relicTrackables.get(0);
        relicTemplate.setName("relicVuMarkTemplate"); // can help in debugging; otherwise not necessary

        relicTrackables.activate();
    }


    void getPose() {

        vuMark = RelicRecoveryVuMark.from(relicTemplate);
    }

    void getTarget()
    {
        getPose();
        while (vuMark == RelicRecoveryVuMark.UNKNOWN)
        {
            telemetry.addData("relic:",vuMark);
            telemetry.update();
            getPose();
        }
        switch (vuMark)
        {
            case RIGHT:
            {
                target=0;
                break;
            }
            case CENTER:
            {
                target=1;
                break;
            }
            case LEFT:
            {
                target=2;
                break;
            }
            case UNKNOWN:
            {
                target=1;
                break;
            }
        }
    }

}

