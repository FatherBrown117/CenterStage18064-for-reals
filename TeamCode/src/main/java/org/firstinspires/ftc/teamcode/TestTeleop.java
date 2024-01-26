package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.rev.RevBlinkinLedDriver;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.internal.system.Deadline;

import java.util.concurrent.TimeUnit;

@TeleOp(name = "TestTeleop", group = "LinearOpMode")

public class TestTeleop extends LinearOpMode {


    private DcMotor leftFront = null;
    private DcMotor rightFront = null;
    private DcMotor leftRear = null;
    private DcMotor rightRear = null;
    private DcMotor rLift = null;
    private DcMotor lLift = null;
    private CRServo intakein = null;
    private CRServo leftIntake = null;
    private CRServo rightIntake = null;
    private CRServo dread = null;
    private Servo leftPull = null;
    private Servo rightPull = null;
    private Servo drone = null;
    private Servo outtake = null;



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
    @Override
    public void runOpMode() {

        telemetry.addData("Status", "Initialized");
        //telemetry.addData("getRuntime","watermelon");
        telemetry.update();

        leftFront = hardwareMap.get(DcMotor.class,"leftFront"); //frontleft, port 0
        rightFront = hardwareMap.get(DcMotor.class,"rightFront");  //frontright, port 1
        leftRear = hardwareMap.get(DcMotor.class,"leftRear"); //backleft, port 3
        rightRear = hardwareMap.get(DcMotor.class,"rightRear");  //backright, port 2
        rLift = hardwareMap.get(DcMotor.class,"rLift");
        lLift = hardwareMap.get(DcMotor.class,"lLift");
        leftIntake = hardwareMap.get(CRServo.class,"leftIntake");
        rightIntake = hardwareMap.get(CRServo.class,"rightIntake");
        dread = hardwareMap.get(CRServo.class,"dread");
        intakein = hardwareMap.get(CRServo.class,"intakein");
        rightPull = hardwareMap.get(Servo.class, "rightPull");
        leftPull = hardwareMap.get(Servo.class, "leftPull");
        drone = hardwareMap.get(Servo.class,"drone");
        outtake = hardwareMap.get(Servo.class,"outtake");

        leftFront.setDirection(DcMotor.Direction.FORWARD);
        rightFront.setDirection(DcMotor.Direction.REVERSE);
        leftRear.setDirection(DcMotor.Direction.FORWARD);
        rightRear.setDirection(DcMotor.Direction.REVERSE);
        rLift.setDirection(DcMotor.Direction.FORWARD);
        lLift.setDirection(DcMotor.Direction.REVERSE);
        displayKind = Blink.DisplayKind.AUTO;

        rightFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftRear.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightRear.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        blinkinLedDriver = hardwareMap.get(RevBlinkinLedDriver.class, "blinkin");
        pattern = RevBlinkinLedDriver.BlinkinPattern.CP1_BREATH_FAST;
        blinkinLedDriver.setPattern(pattern);

        display = telemetry.addData("Display Kind: ", displayKind.toString());
        patternName = telemetry.addData("Pattern: ", pattern.toString());

        ledCycleDeadline = new Deadline(LED_PERIOD, TimeUnit.SECONDS);
        gamepadRateLimit = new Deadline(GAMEPAD_LOCKOUT, TimeUnit.MILLISECONDS);




        waitForStart();

        while (opModeIsActive()) {
            double leftPower;
            double rightPower;
            double leftPowerx;


            double G1rightStickY = gamepad1.right_stick_y;
            double G1leftStickY = gamepad1.left_stick_y;
            double G1rightStickX = gamepad1.right_stick_x;
            double G1leftStickX = gamepad1.left_stick_x;
            //leftPower = Range.clip(G1leftStickY +G1rightStickX, -1.0, 1.0);
            //rightPower = Range.clip(G1leftStickY -G1rightStickX, -1.0, 1.0);
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
            boolean G2X = gamepad2.x;
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
//aaaaa

            if (G1leftStickY > 0) {
                leftFront.setPower(1);
                leftRear.setPower(1);
                rightFront.setPower(1);
                rightRear.setPower(1);
            }if (G1leftStickY < 0) { // Backwards
                leftFront.setPower(-1);
                leftRear.setPower(-1);
                rightFront.setPower(-1);
                rightRear.setPower(-1);
            }if (G1leftStickX < 0) { // Move right
                leftFront.setPower(1);
                rightFront.setPower(-1);
                leftRear.setPower(-1);
                rightRear.setPower(1);
            }if (G1leftStickX > 0) { // Move left
                leftFront.setPower(-1);
                rightFront.setPower(1);
                leftRear.setPower(1);
                rightRear.setPower(-1);
            }if (G1rightStickX > 0) { // Move left
                leftFront.setPower(1);
                rightFront.setPower(-1);
                leftRear.setPower(1);
                rightRear.setPower(-1);
            }if (G1rightStickX < 0) { // Move left
                leftFront.setPower(-1);
                rightFront.setPower(1);
                leftRear.setPower(-1);
                rightRear.setPower(1);
            }if (G1UD) {
                leftFront.setPower(.5);
                leftRear.setPower(.5);
                rightFront.setPower(.5);
                rightRear.setPower(.5);
            }if (G1DD) { // Backwards
                leftFront.setPower(-.5);
                leftRear.setPower(-.5);
                rightFront.setPower(-.5);
                rightRear.setPower(-.5);
            }if (G1RD) { // Move right
                leftFront.setPower(.5);
                rightFront.setPower(-.5);
                leftRear.setPower(-.5);
                rightRear.setPower(.5);
            }if (G1LD) { // Move left
                leftFront.setPower(-.5);
                rightFront.setPower(.5);
                leftRear.setPower(.5);
                rightRear.setPower(-.5);
            }if (G2A) { // Intake + treadmill going up
                leftIntake.setPower(1);
                rightIntake.setPower(1);
                intakein.setPower(1);
                //dread.setPower(1);
                pattern = RevBlinkinLedDriver.BlinkinPattern.SHOT_BLUE;
                displayPattern();
                gamepadRateLimit.reset();
                blinkinLedDriver.setPattern(pattern);
            }if (G2B) { // Outtake the Intake (reverse intake)
                pattern = RevBlinkinLedDriver.BlinkinPattern.SHOT_RED;
                displayPattern();
                gamepadRateLimit.reset();
                blinkinLedDriver.setPattern(pattern);
                leftIntake.setPower(-1);
                rightIntake.setPower(-1);
                intakein.setPower(-1);
                //dread.setPower(-1); //in ca
            }if (G1B) { // Diagonal: Lower Right (First controller)
                leftFront.setPower(0);
                rightFront.setPower(-1);
                leftRear.setPower(-1);
                rightRear.setPower(0);
            }if (G1X) { // Diagonal: Upper Right (First controller)
                leftFront.setPower(0);
                rightFront.setPower(1);
                leftRear.setPower(1);
                rightRear.setPower(0);
            }if (G1A) {
                leftFront.setPower(-1);
                rightFront.setPower(0);
                leftRear.setPower(0);
                rightRear.setPower(-1);
            }if (G1Y) {
                leftFront.setPower(1);
                rightFront.setPower(0);
                leftRear.setPower(0);
                rightRear.setPower(1);
            }if (G2back) {
                drone.setPosition(0);
                sleep(3000);
                drone.setPosition(1);
                //moving into claw and linear slides (second controller)
            }if (G2LT == 1) { // Linear pillars move up (second controller)
                rLift.setPower(1.01);
                lLift.setPower(-1);
            }if (G2RT == 1) { // Linear pillars move down (second controller)
                rLift.setPower(-1);
                lLift.setPower(1);
            }if (G2UD) { //linear SLIDE moves up (second controller)
                dread.setPower(1);
            }if (G2DD) { //linear SLIDE moves down (second controller)
                dread.setPower(-1);
            }if (G2leftBumper) { //outtake moves inward (second controller)
                leftPull.setPosition(1);
                rightPull.setPosition(0);
            }if (G2rightBumper) { //outtake moves outward (second controller)
                leftPull.setPosition(0);
                rightPull.setPosition(1);
            }if (G2X) { //outtake outtake
                outtake.setPosition(1);
            }if (G2Y) {
                outtake.setPosition(0);
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
                intakein.setPower(0);
            }

            telemetry.addData("Status", "Running");
            telemetry.update();

        }
    }
    protected void setDisplayKind(Blink.DisplayKind displayKind)
    {
        this.displayKind = displayKind;
        display.setValue(displayKind.toString());
    }

    protected void doAutoDisplay()
    {
        if (ledCycleDeadline.hasExpired()) {
            //pattern = pattern.next();
            displayPattern();
            ledCycleDeadline.reset();
        }
    }
    protected void displayPattern()
    {
        //blinkinLedDriver.setPattern(pattern);
        //patternName.setValue(pattern.toString());
    }

}
//Aidan was here :P