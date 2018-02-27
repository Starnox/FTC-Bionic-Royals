package org.firstinspires.ftc.robotcontroller.external.samples;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CompassSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.Gyroscope;
import com.qualcomm.robotcore.hardware.OpticalDistanceSensor;
import com.qualcomm.robotcore.util.Range;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
import org.firstinspires.ftc.robotcore.external.matrices.VectorF;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AngularVelocity;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.robotcore.external.navigation.VuMarkInstanceId;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackableDefaultListener;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;

import java.text.DecimalFormat;

/**
 * Created by Alex on 1/20/2018.
 */

@TeleOp(name="test2", group="Pushbot")
@Disabled
public class test2 extends OpMode {
    private ElapsedTime timp;
    private HardwarePushbot robot;
    private ColorSensor sc;
    private OpticalDistanceSensor ods;
    private Gyroscope gyro;
    private CompassSensor cmp;
    int poz=0;
    float v[]={0,0.4f,0.8f};
    VuforiaLocalizer vuforia;
    OpenGLMatrix pose;
    int cameraMonitorViewId;
    VuforiaTrackables relicTrackables;
    VuforiaTrackable relicTemplate;
    VuforiaLocalizer.Parameters parameters;
    RelicRecoveryVuMark vuMark;
    @Override
    public void init() {
        initPose();
        timp = new ElapsedTime();
        robot =  new HardwarePushbot();

        gyro = hardwareMap.get(Gyroscope.class,"gyro");


        cmp = hardwareMap.get(CompassSensor.class,"cmp");
        cmp.setMode(CompassSensor.CompassMode.MEASUREMENT_MODE);
        ods = hardwareMap.get(OpticalDistanceSensor.class, "ods");
        ods.enableLed(true);
        sc = hardwareMap.get(ColorSensor.class, "sc");
        sc.enableLed(true);
        robot.init(hardwareMap);
        telemetry.addData("Say", "test");
    }


    @Override
    public void init_loop() {
    }

    @Override
    public void start() {


    }


    @Override
    public void loop() {
        getPose();
        double fata = gamepad1.left_stick_x;
        double intoarce = gamepad1.left_stick_y;
        //stanga = fata – intoarce;
        //dreapta = fata + intoarce;
        if (fata != 0 || intoarce != 0) {
            //dreapta
            robot.mfr.setPower(gamepad1.left_stick_y + gamepad1.left_stick_x / 2);
            robot.mbr.setPower(gamepad1.left_stick_x + gamepad1.left_stick_y / 2);
            //stanga
            robot.mfl.setPower(gamepad1.left_stick_y - gamepad1.left_stick_x / 2);
           robot.mbl.setPower(gamepad1.left_stick_y - gamepad1.left_stick_x / 2);
        } if(fata == 0 && intoarce == 0){
            robot.mfl.setPower(0);
            robot.mfr.setPower(0);
            robot.mbl.setPower(0);
            robot.mbr.setPower(0);
        }
        if(gamepad1.x)
            robot.sb.setPosition(0.9);
        if(gamepad1.y)
            robot.sb.setPosition(0.1);
        if(gamepad1.right_trigger!=0)
        {
            robot.mcl.setPower(gamepad1.right_trigger);
            robot.mcr.setPower(gamepad1.right_trigger);
        }
        else if(gamepad1.left_trigger!=0)
        {
            robot.mcl.setPower(gamepad1.left_trigger);
            robot.mcr.setPower(-gamepad1.left_trigger);
        }
        else
        {
            robot.mcl.setPower(0);
            robot.mcr.setPower(0);
        }
        if(gamepad1.right_bumper)
            robot.sf.setPosition(Math.min(1,robot.sf.getPosition()+0.01));
        else if(gamepad1.left_bumper)
            robot.sf.setPosition(Math.max(0,robot.sf.getPosition()-0.01));

        if(gamepad1.dpad_up)
            robot.up1.setServoPosition(1,robot.up1.getServoPosition(1)+0.01);
        else if(gamepad1.dpad_down)
            robot.up1.setServoPosition(1,robot.up1.getServoPosition(1)-0.01);

        robot.mr.setPower(gamepad1.right_stick_y);
        if(sc.red() > 15 && sc.red() > sc.blue()){
            robot.sb.setPosition(0.4);
        }
        else if(sc.blue() > 15 && sc.blue() > sc.red())
            robot.sb.setPosition(1);
        telemetry.addData("Pose:",vuMark);
        OpticalDistanceSensor();
        CompassSensor();
        GyroSensor();

        telemetry.update();
    }

    @Override
    public void stop() {
    }
    private void initPose()
    {
        cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        parameters = new VuforiaLocalizer.Parameters(cameraMonitorViewId);


        parameters.vuforiaLicenseKey = " AfskXVz/////AAAAmVUQUJaKPkpnoCuHva4WBHp7gHrMII3cdGcUGQd5dtWp2UD5UEJL3PxVqiF65V/8Il/lOcm2ce4l4GntFtvH53aaJXHIgNdwgyxSDdstWn2tGJMIn8SFkcKs+h/aGypSX/hUG9MmaUdAVCbrXA1W7+euYEhZjs1tAHButW4T7kZx8tObN4kvM14eX1mGdDAz2I3KhNfkjWP3X+vYibQlClN+i3k5IwpYJdZhYYydFyaWYir0QSsYKmriA/qSbw1RqGUGD7t38EbhmXGslGNVTgQxukKhcO1LlXEiZx/wx1O9MEfObcD1dSSjwI1zQPqRWBFO2tWVWg6HkK9j1IV9YA9LBMwDXsMiC5DztTB6/LZ7 ";

        parameters.cameraDirection = VuforiaLocalizer.CameraDirection.BACK;
        this.vuforia = ClassFactory.createVuforiaLocalizer(parameters);

         relicTrackables = this.vuforia.loadTrackablesFromAsset("RelicVuMark");
         relicTemplate = relicTrackables.get(0);
        relicTemplate.setName("relicVuMarkTemplate"); // can help in debugging; otherwise not necessary

        telemetry.addData(">", "Press Play to start");
        telemetry.update();

        relicTrackables.activate();
    }


    private void getPose() {

             vuMark = RelicRecoveryVuMark.from(relicTemplate);
            if (vuMark != RelicRecoveryVuMark.UNKNOWN) {

                /* Found an instance of the template. In the actual game, you will probably
                 * loop until this condition occurs, then move on to act accordingly depending
                 * on which VuMark was visible. */

                /* For fun, we also exhibit the navigational pose. In the Relic Recovery game,
                 * it is perhaps unlikely that you will actually need to act on this pose information, but
                 * we illustrate it nevertheless, for completeness. */
                 pose = ((VuforiaTrackableDefaultListener) relicTemplate.getListener()).getPose();

                /* We further illustrate how to decompose the pose into useful rotational and
                 * translational components */
                if (pose != null) {
                    VectorF trans = pose.getTranslation();
                    Orientation rot = Orientation.getOrientation(pose, AxesReference.EXTRINSIC, AxesOrder.XYZ, AngleUnit.DEGREES);

                    // Extract the X, Y, and Z components of the offset of the target relative to the robot
                    double tX = trans.get(0);
                    double tY = trans.get(1);
                    double tZ = trans.get(2);

                    // Extract the rotational components of the target relative to the robot
                    double rX = rot.firstAngle;
                    double rY = rot.secondAngle;
                    double rZ = rot.thirdAngle;
                }
            }
    }
    private void OpticalDistanceSensor()
    {
        telemetry.addData("Normal: ", ods.getLightDetected());
        telemetry.addData("Raw: ", ods.getRawLightDetected());
        telemetry.addData("RawMax: ", ods.getRawLightDetectedMax());


    }
    private void CompassSensor()
    {
        telemetry.addData("Direction",cmp.getDirection());
    }
    private void GyroSensor()
    {
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);

        AngularVelocity av = gyro.getAngularVelocity(AngleUnit.DEGREES);
        String toPrint = df.format(av.xRotationRate) + " " + df.format(av.yRotationRate) + " " + df.format(av.zRotationRate);
        telemetry.addData("Gyro:", toPrint);
    }
}