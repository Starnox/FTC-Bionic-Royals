
package org.firstinspires.ftc.robotcontroller.external.samples;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.Range;

/**
 * This file provides basic Telop driving for a Pushbot robot.
 * The code is structured as an Iterative OpMode
 *
 * This OpMode uses the common Pushbot hardware class to define the devices on the robot.
 * All device access is managed through the HardwarePushbot class.
 *
 * This particular OpMode executes a basic Tank Drive Teleop for a PushBot
 * It raises and lowers the claw using the Gampad Y and A buttons respectively.
 * It also opens and closes the claws slowly using the left and right Bumper buttons.
 *
 * Use Android Studios to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this opmode to the Driver Station OpMode list
 */

@TeleOp(name="Pushbot: Teleop Tank", group="Pushbot")
@Disabled
public class test extends OpMode{
    HardwarePushbot robot       = new HardwarePushbot();

    @Override
    public void init() {
        robot.init(hardwareMap);
        telemetry.addData("Say", "CMB");
    }


    @Override
    public void init_loop() {
    }

    @Override
    public void start() {
    }


    @Override
    public void loop() {





        // Move both servos to new position.  Assume servos are mirror image of each other.


        // Use gamepad buttons to move the arm up  and down (A)
        /*
        if (gamepad1.left_stick_y != 0 || gamepad1.left_stick_x != 0) {
            robot.mr.setPower(gamepad1.left_stick_y + gamepad1.left_stick_x/2);
            robot.ml.setPower(gamepad1.left_stick_y - gamepad1.left_stick_x/2);
        }
        else if(gamepad1.right_bumper){
            robot.msr.setPower(1);
            robot.msl.setPower(1);
        }
        else if(gamepad1.y){
            robot.srli.setPosition(-1);
        }
        else if(gamepad1.right_trigger != 0){
            robot.mg.setPower(1);
        }
        else if(gamepad1.b){
            robot.mb.setPower(1);
        }
        else if(gamepad1.left_trigger != 0){
            robot.mli.setDirection(DcMotor.Direction.FORWARD);
            robot.mli.setPower(gamepad1.left_trigger);

        }
        else if(gamepad1.left_bumper){
            robot.mli.setDirection(DcMotor.Direction.REVERSE);
            robot.mli.setPower(gamepad1.left_trigger);

        }
        else{
            robot.mr.setPower(0.0);
            robot.ml.setPower(0.0);
            robot.mli.setPower(0.0);
            robot.msr.setPower(0.0);
            robot.msl.setPower(0.0);
            robot.mg.setPower(0.0);
            robot.srli.setPosition(1);
            robot.mb.setPower(0);
        }

        // Send telemetry message to signify robot running;
        telemetry.addData("Robot CNMB:",  "Ready");
*/


        if (gamepad1.left_stick_y != 0 || gamepad1.left_stick_x != 0) {
            robot.mfr.setPower(gamepad1.left_stick_x + gamepad1.left_stick_x/2);
            robot.mbr.setPower(gamepad1.left_stick_y + gamepad1.left_stick_x/2);

            robot.mfl.setPower(gamepad1.left_stick_y - gamepad1.left_stick_x/2);
            robot.mbl.setPower(gamepad1.left_stick_y - gamepad1.left_stick_x/2);
        }
        else{
            robot.mfl.setPower(0.0);
            robot.mfr.setPower(0.0);
            robot.mbl.setPower(0.0);
            robot.mbr.setPower(0.0);
        }

        // Send telemetry message to signify robot running;
        telemetry.addData("Robot CNMB:",  "Ready");
    }

    /*
     * Code to run ONCE after the driver hits STOP
     */
    @Override
    public void stop() {
    }
}
