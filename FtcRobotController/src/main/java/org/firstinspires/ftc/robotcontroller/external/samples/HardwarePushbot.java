/* Copyright (c) 2017 FIRST. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted (subject to the limitations in the disclaimer below) provided that
 * the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list
 * of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice, this
 * list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * Neither the name of FIRST nor the names of its contributors may be used to endorse or
 * promote products derived from this software without specific prior written permission.
 *
 * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
 * LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.firstinspires.ftc.robotcontroller.external.samples;

import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.CompassSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gyroscope;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.ServoController;
import com.qualcomm.robotcore.hardware.ServoImpl;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * This is NOT an opmode.
 *
 * This class can be used to define all the specific hardware for a single robot.
 * In this case that robot is a Pushbot.
 * See PushbotTeleopTank_Iterative and others classes starting with "Pushbot" for usage examples.
 *
 * This hardware class assumes the following device names have been configured on the robot:
 * Note:  All names are lower case and some have single spaces between words.
 *
 * Motor channel:  Left  drive motor:        "left_drive"
 * Motor channel:  Right drive motor:        "right_drive"
 * Motor channel:  Manipulator drive motor:  "left_arm"
 * Servo channel:  Servo to open left claw:  "left_hand"
 * Servo channel:  Servo to open right claw: "right_hand"
 */
public class HardwarePushbot
{
    /* Public OpMode members. */
    public DcMotor  mfl = null;
    public DcMotor  mfr = null;
    public DcMotor  mbl = null;
    public DcMotor  mbr = null;
    public DcMotor mcr = null;
    public DcMotor mcl = null;
    public DcMotor mr = null;
    public Servo  sb = null;
    public Servo  sf = null;
    public Servo up1=null;
    public Servo up2=null;
    public ColorSensor sc=null;
    public CompassSensor cmp = null;
    public Gyroscope gyro = null;


    public static final double MID_SERVO       =  0.5 ;
    public static final double ARM_UP_POWER    =  0.45 ;
    public static final double ARM_DOWN_POWER  = -0.45 ;

    /* local OpMode members. */
    HardwareMap hwMap           =  null;
    private ElapsedTime period  = new ElapsedTime();

    /* Constructor */
    public HardwarePushbot(){

    }

    /* Initialize standard Hardware interfaces */
    public void init(HardwareMap ahwMap) {
        // Save reference to Hardware map
        hwMap = ahwMap;

        // Define and Initialize Motors
        mfl  = hwMap.get(DcMotor.class, "mfl");
        mfr = hwMap.get(DcMotor.class, "mfr");
        mbl = hwMap.get(DcMotor.class, "mbl");
        mbr = hwMap.get(DcMotor.class,  "mbr");
        mcr = hwMap.get(DcMotor.class,"mcr");
        mcl = hwMap.get(DcMotor.class,"mcl");
        mr = hwMap.get(DcMotor.class,"mr");

        mfl.setDirection(DcMotor.Direction.FORWARD);
        mfr.setDirection(DcMotor.Direction.REVERSE);
        mbl.setDirection(DcMotor.Direction.FORWARD);
        mbr.setDirection(DcMotor.Direction.REVERSE);
        mcl.setDirection(DcMotor.Direction.REVERSE);
        mcr.setDirection(DcMotor.Direction.FORWARD);
        mr.setDirection(DcMotor.Direction.FORWARD);

        // Set all motors to zero power
        mfl.setPower(0);
        mfr.setPower(0);
        mbl.setPower(0);
        mbr.setPower(0);
        mcl.setPower(0);
        mcr.setPower(0);
        mr.setPower(0);
        // Set all motors to run without encoders.
        // May want to use RUN_USING_ENCODERS if encoders are installed.
        mfl.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        mfr.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        mbl.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        mbr.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        mcr.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        mcl.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        mr.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        // Define and initialize ALL installed servos.
        sb  = hwMap.get(Servo.class, "sb");
        sb.setPosition(0.1);
        sf = hwMap.get(Servo.class,"sf");
        sf.setPosition(1);

        up1= hwMap.get(Servo.class,"up1");
       // up1.setPosition(0.63);
        up2= hwMap.get(Servo.class,"up2");
        //up2.setPosition(0.37);
        //Sensors
        sc= hwMap.get(ColorSensor.class,"sc");
        sc.enableLed(true);
        cmp = hwMap.get(CompassSensor.class,"cmp");
        cmp.setMode(CompassSensor.CompassMode.MEASUREMENT_MODE);
        gyro =  hwMap.get(Gyroscope.class,"gyro");

    }
 }

