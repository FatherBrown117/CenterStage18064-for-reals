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

package org.firstinspires.ftc.teamcode;

import static android.os.SystemClock.sleep;

import com.qualcomm.hardware.rev.RevBlinkinLedDriver;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.internal.system.Deadline;

import java.util.concurrent.TimeUnit;

/*
 * This file contains an example of an iterative (Non-Linear) "OpMode".
 * An OpMode is a 'program' that runs in either the autonomous or the teleop period of an FTC match.
 * The names of OpModes appear on the menu of the FTC Driver Station.
 * When a selection is made from the menu, the corresponding OpMode
 * class is instantiated on the Robot Controller and executed.
 *
 * This particular OpMode just executes a basic Tank Drive Teleop for a two wheeled robot
 * It includes all the skeletal structure that all iterative OpModes contain.
 *
 * Use Android Studio to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this OpMode to the Driver Station OpMode list
 */

@TeleOp(name="CoolTeleop", group="Iterative OpMode")
public class CoolTeleop extends OpMode {
    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();

    private DcMotor leftFront = null;
    private DcMotor leftFrontb = null;
    private DcMotor rightFront = null;
    private DcMotor rightFrontb = null;
    private DcMotor leftRear = null;
    private DcMotor leftRearb = null;
    private DcMotor rightRear = null;
    private DcMotor rightRearb = null;
    private DcMotor rLift = null;
    private DcMotor lLift = null;
    //private DcMotor vector = null;
    private CRServo leftIntake = null;
    private CRServo rightIntake = null;
    private CRServo dread = null;
    private Servo leftPull = null;
    private Servo rightPull = null;
    private Servo drone = null;

    /*
     * Change the pattern every 10 seconds in AUTO mode.
     */
    private final static int LED_PERIOD = 10;

    /*
     * Rate limit gamepad button presses to every 500ms.
     */
    private final static int GAMEPAD_LOCKOUT = 500;


    RevBlinkinLedDriver blinkinLedDriver;
    RevBlinkinLedDriver.BlinkinPattern pattern;

    Telemetry.Item patternName;
    Telemetry.Item display;
    Blink.DisplayKind displayKind;
    Deadline ledCycleDeadline;
    Deadline gamepadRateLimit;

    protected enum DisplayKind {
        MANUAL,
        AUTO

    }

    /*
     * Code to run ONCE when the driver hits INIT
     */
    @Override
    public void init() {
        telemetry.addData("Status", "Initialized");

        // Initialize the hardware variables. Note that the strings used here as parameters
        // to 'get' must correspond to the names assigned during the robot configuration
        // step (using the FTC Robot Controller app on the phone).
        leftFront = hardwareMap.get(DcMotor.class, "leftFront"); //frontleft, port 0
        leftFrontb = hardwareMap.get(DcMotor.class, "leftFront");
        rightFront = hardwareMap.get(DcMotor.class, "rightFront");  //frontright, port 1
        rightFrontb = hardwareMap.get(DcMotor.class, "rightFront");  //frontright, port 1
        leftRear = hardwareMap.get(DcMotor.class, "leftRear"); //backleft, port 3
        leftRearb = hardwareMap.get(DcMotor.class, "leftRear");
        rightRear = hardwareMap.get(DcMotor.class, "rightRear");  //backright, port 2
        rightRearb = hardwareMap.get(DcMotor.class, "rightRear");
        rLift = hardwareMap.get(DcMotor.class, "rLift");
        lLift = hardwareMap.get(DcMotor.class, "lLift");
        leftIntake = hardwareMap.get(CRServo.class, "leftIntake");
        rightIntake = hardwareMap.get(CRServo.class, "rightIntake");
        dread = hardwareMap.get(CRServo.class, "dread");
        //vector = hardwareMap.get(DcMotor.class,"vector");
        rightPull = hardwareMap.get(Servo.class, "rightPull");
        leftPull = hardwareMap.get(Servo.class, "leftPull");
        drone = hardwareMap.get(Servo.class, "drone");

        leftFront.setDirection(DcMotor.Direction.REVERSE);
        leftFrontb.setDirection(DcMotor.Direction.REVERSE);
        rightFront.setDirection(DcMotor.Direction.FORWARD);
        rightFrontb.setDirection(DcMotor.Direction.FORWARD);
        leftRear.setDirection(DcMotor.Direction.FORWARD);
        leftRearb.setDirection(DcMotor.Direction.FORWARD);
        rightRear.setDirection(DcMotor.Direction.REVERSE);
        rightRearb.setDirection(DcMotor.Direction.REVERSE);
        rLift.setDirection(DcMotor.Direction.FORWARD);
        lLift.setDirection(DcMotor.Direction.REVERSE);
        //vector.setDirection(DcMotor.Direction.FORWARD);
        displayKind = Blink.DisplayKind.AUTO;

        blinkinLedDriver = hardwareMap.get(RevBlinkinLedDriver.class, "blinkin");
        pattern = RevBlinkinLedDriver.BlinkinPattern.CP1_BREATH_FAST;
        blinkinLedDriver.setPattern(pattern);

        display = telemetry.addData("Display Kind: ", displayKind.toString());
        patternName = telemetry.addData("Pattern: ", pattern.toString());

        ledCycleDeadline = new Deadline(LED_PERIOD, TimeUnit.SECONDS);
        gamepadRateLimit = new Deadline(GAMEPAD_LOCKOUT, TimeUnit.MILLISECONDS);

    }

    /*
     * Code to run REPEATEDLY after the driver hits INIT, but before they hit PLAY
     */
    @Override
    public void init_loop() {
    }

    /*
     * Code to run ONCE when the driver hits PLAY
     */
    @Override
    public void start() {
        runtime.reset();
    }

    /*
     * Code to run REPEATEDLY after the driver hits PLAY but before they hit STOP
     */
    @Override
    public void loop() {
        double leftFrontx;
        double rightFrontx;
        double leftRearx;
        double rightRearx;
        double leftFronty;
        double rightFronty;
        double leftReary;
        double rightReary;


        double G1rightStickY = gamepad1.right_stick_y;
        double G1leftStickY = gamepad1.left_stick_y;
        double G1rightStickX = gamepad1.right_stick_x;
        double G1leftStickX = gamepad1.left_stick_x;
        boolean G1rightBumper = gamepad1.right_bumper;
        boolean G1leftBumper = gamepad1.left_bumper;
        boolean G1UD = gamepad1.dpad_up;   // up dpad
        boolean G1DD = gamepad1.dpad_down; //Down dpad
        boolean G1RD = gamepad1.dpad_right;// right dpad
        boolean G1LD = gamepad1.dpad_left; //left dpad
        boolean G1Y = gamepad1.y;
        boolean G1B = gamepad1.b;
        boolean G1X = gamepad1.x;
        boolean G1A = gamepad1.a;
        double G1RT = gamepad1.right_trigger;
        double G1LT = gamepad1.left_trigger;
        //Second controller (Intake/linear slide)
        double G2leftStickY = gamepad2.left_stick_y;
        boolean G2B = gamepad2.b;
        boolean G2Y = gamepad2.y;
        boolean G2A = gamepad2.a;
        boolean G2UD = gamepad2.dpad_up; // up dpad
        boolean G2DD = gamepad2.dpad_down; // down dpad
        boolean G2RD = gamepad2.dpad_right;// right dpad
        boolean G2LD = gamepad2.dpad_left; //left dpad
        double G2LT = gamepad2.left_trigger;
        double G2RT = gamepad2.right_trigger;
        boolean G2rightBumper = gamepad2.right_bumper;
        boolean G2leftBumper = gamepad2.left_bumper;
        boolean G2back = gamepad2.back;


        //Driving movements (First controller)
        // Forward and Backward (Left Stick)

        if (G1rightStickX > 0) {  // Clockwise
            leftFront.setPower(.5);
            leftRear.setPower(.5);
            rightFront.setPower(-.5);
            rightRear.setPower(-.5);
        }if (G1rightStickX < 0) { // Counterclockwise
            leftFront.setPower(-.5);
            leftRear.setPower(-.5);
            rightFront.setPower(.5);
            rightRear.setPower(.5);
        }if (G1leftStickY > 0) { // Backwards
            leftFront.setPower(-.6);
            leftRear.setPower(-.6);
            rightFront.setPower(-.6);
            rightRear.setPower(-.6);
        }if (G1leftStickY < 0) { // Forwards
            leftFront.setPower(.6);
            leftRear.setPower(.6);
            rightFront.setPower(.6);
            rightRear.setPower(.6);
        }if (G1leftStickX > 0) { // Move right
            leftFront.setPower(.6);
            rightFront.setPower(-.6);
            leftRear.setPower(-.6);
            rightRear.setPower(.6);
        } if (G1leftStickX < 0) { // Move left
            leftFront.setPower(-.6);
            rightFront.setPower(.6);
            leftRear.setPower(.6);
            rightRear.setPower(-.6);
        } if (G1UD) {
                leftFront.setPower(.25);
                leftRear.setPower(.25);
                rightFront.setPower(.25);
                rightRear.setPower(.25);
        }if (G1DD) { // Backwards
                leftFront.setPower(-.25);
                leftRear.setPower(-.25);
                rightFront.setPower(-.25);
                rightRear.setPower(-.25);
        }if (G1RD) { // Move right
                leftFront.setPower(.25);
                rightFront.setPower(-.25);
                leftRear.setPower(-.25);
                rightRear.setPower(.25);
        }if (G1LD) { // Move left
                leftFront.setPower(-.25);
                rightFront.setPower(.25);
                leftRear.setPower(.25);
                rightRear.setPower(-.25);
        }if (G2A) { // Intake + treadmill going up
                leftIntake.setPower(1);
                rightIntake.setPower(1);
                dread.setPower(1);
                pattern = RevBlinkinLedDriver.BlinkinPattern.SHOT_BLUE;
                displayPattern();
                gamepadRateLimit.reset();
                blinkinLedDriver.setPattern(pattern);
        } if (G2B) { // Outtake the Intake (reverse intake)
                pattern = RevBlinkinLedDriver.BlinkinPattern.SHOT_RED;
                displayPattern();
                gamepadRateLimit.reset();
                blinkinLedDriver.setPattern(pattern);
                leftIntake.setPower(-1);
                rightIntake.setPower(-1);
                dread.setPower(-1); //in case
        } if (G2Y) { //Outtake 2 (backdrop
        } if (G1B) { // Diagonal: Lower Right (First controller)
                pattern = RevBlinkinLedDriver.BlinkinPattern.HOT_PINK;
                displayPattern();
                gamepadRateLimit.reset();
                blinkinLedDriver.setPattern(pattern);
        } if (G1X) { // Diagonal: Upper Right (First controller)
                pattern = RevBlinkinLedDriver.BlinkinPattern.WHITE;
                displayPattern();
                gamepadRateLimit.reset();
                blinkinLedDriver.setPattern(pattern);
        }if (G1A) {
                pattern = RevBlinkinLedDriver.BlinkinPattern.GREEN;
                displayPattern();
                gamepadRateLimit.reset();
                blinkinLedDriver.setPattern(pattern);
        }if (G1Y) {
                pattern = RevBlinkinLedDriver.BlinkinPattern.YELLOW;
                displayPattern();
                gamepadRateLimit.reset();
                blinkinLedDriver.setPattern(pattern);
        }if (G2back) {
                drone.setPosition(0);
                sleep(3000);
                drone.setPosition(1);
                //moving into claw and linear slides (second controller)
        }if (G2LT == 1) { // Linear pillars move up (second controller)
                rLift.setPower(1);
                lLift.setPower(-1);
        }if (G2RT == 1) { // Linear pillars move down (second controller)
                rLift.setPower(-1);
                lLift.setPower(1);
        }if (G2leftStickY > 0) { //linear SLIDE moves up (second controller)
                //vector.setPower(.5);
        }if (G2leftStickY < 0) { //linear SLIDE moves down (second controller)
                //vector.setPower(-.5);
        }if (G2leftBumper) { //outtake moves inward (second controller)
                leftPull.setPosition(1);
                rightPull.setPosition(0);
        }if (G2rightBumper) { //outtake moves outward (second controller)
                leftPull.setPosition(0);
                rightPull.setPosition(1);
        } else { //STOP IN THE NAME OF THE LAW!
                leftFront.setPower(0);
                rightFront.setPower(0);
                leftRear.setPower(0);
                rightRear.setPower(0);
                leftIntake.setPower(0);
                rightIntake.setPower(0);
                dread.setPower(0);
                pattern = RevBlinkinLedDriver.BlinkinPattern.BLACK;
                blinkinLedDriver.setPattern(pattern);
                displayPattern();
                gamepadRateLimit.reset();
                rLift.setPower(0);
                lLift.setPower(0);
                //vector.setPower(0);
            }

            // Show the elapsed game time and wheel power.
            telemetry.addData("Status", "Run Time: " + runtime.toString());
        }

        /*
         * Code to run ONCE after the driver hits STOP
         */
        @Override
        public void stop () {
        }
        protected void setDisplayKind (Blink.DisplayKind displayKind)
        {
            this.displayKind = displayKind;
            display.setValue(displayKind.toString());
        }

        protected void doAutoDisplay ()
        {
            if (ledCycleDeadline.hasExpired()) {
                //pattern = pattern.next();
                displayPattern();
                ledCycleDeadline.reset();
            }
        }
        protected void displayPattern ()
        {
            //blinkinLedDriver.setPattern(pattern);
            //patternName.setValue(pattern.toString());
        }
    }
//Aidan was Here