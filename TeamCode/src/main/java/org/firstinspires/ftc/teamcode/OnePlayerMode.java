package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "OnePlayerMode")

public class OnePlayerMode extends LinearOpMode {

    BasicAuto obj = new BasicAuto();

    private DcMotor leftFront = null;
    private DcMotor rightFront = null;
    private DcMotor leftRear = null;
    private DcMotor rightRear = null;
    private DcMotor rightArm = null;
    private DcMotor leftArm = null;
    private Servo rightClaw = null;
    private Servo leftClaw = null;

    private Boolean clawClosed = true;

    @Override
    public void runOpMode() {

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        leftFront = hardwareMap.get(DcMotor.class,"leftFront"); //frontleft, port 0
        rightFront = hardwareMap.get(DcMotor.class,"rightFront");  //frontright, port 1
        leftRear = hardwareMap.get(DcMotor.class,"leftRear"); //backleft, port 3
        rightRear = hardwareMap.get(DcMotor.class,"rightRear");  //backright, port 2
        rightArm = hardwareMap.get(DcMotor.class, "rightArm");
        leftArm = hardwareMap.get(DcMotor.class, "leftArm");

        rightClaw = hardwareMap.get(Servo.class, "rightClaw");
        leftClaw = hardwareMap.get(Servo.class, "leftClaw");

        leftFront.setDirection(DcMotor.Direction.REVERSE);
        rightFront.setDirection(DcMotor.Direction.FORWARD);
        leftRear.setDirection(DcMotor.Direction.REVERSE);
        rightRear.setDirection(DcMotor.Direction.FORWARD);
        rightArm.setDirection(DcMotorSimple.Direction.REVERSE);

        leftArm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightArm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);


        waitForStart();

        while (opModeIsActive()) {

            double G1rightStickY = gamepad1.right_stick_y;
            double G1leftStickY = gamepad1.left_stick_y;
            double G1rightStickX = gamepad1.right_stick_x;
            double G1leftStickX = gamepad1.left_stick_x;
            boolean G1rightBumper = gamepad1.right_bumper;
            boolean G1leftBumper = gamepad1.left_bumper;
            boolean G1Y = gamepad1.y;
            boolean G1B = gamepad1.b;
            boolean G1X = gamepad1.x;
            boolean G1A = gamepad1.a;
            double G1RT = -gamepad1.right_trigger;
            double G1LT = gamepad1.left_trigger;

            //claw movements
            if (gamepad1.a){
                clawClosed = false;
            } else if (gamepad1.b) {
                clawClosed = true;
            }

            if (clawClosed){
                rightClaw.setPosition(0.35); //closes claw
                leftClaw.setPosition(0.67);
            } else if (clawClosed == false) {
                rightClaw.setPosition(0.1);// opens claw
                leftClaw.setPosition(0.88);
            }
            //Driving movements
            if (G1rightStickX > 0) {  // Clockwise
                leftFront.setPower(0.5);
                leftRear.setPower(0.5);
                rightFront.setPower(-0.5);
                rightRear.setPower(-0.5);
            } else if (G1rightStickX < 0) { // Counterclockwise
                leftFront.setPower(-0.5);
                leftRear.setPower(-0.5);
                rightFront.setPower(0.5);
                rightRear.setPower(0.5);
            } else if (G1leftStickY > 0) { // Backwards
                leftFront.setPower(-0.5);
                leftRear.setPower(-0.5);
                rightFront.setPower(-0.5);
                rightRear.setPower(-0.5);
            } else if (G1leftStickY < 0) { // Forwards
                leftFront.setPower(.5);
                leftRear.setPower(.5);
                rightFront.setPower(.5);
                rightRear.setPower(.5);
            } else if (gamepad1.dpad_right) { //strafe right
                leftFront.setPower(1);
                rightFront.setPower(-1);
                leftRear.setPower(-1);
                rightRear.setPower(1);
            } else if (gamepad1.dpad_left) { //strafe left
                leftFront.setPower(-1);
                rightFront.setPower(1);
                leftRear.setPower(1);
                rightRear.setPower(-1);
            } else if (gamepad1.dpad_up) { // forwards
                leftFront.setPower(0.65);
                rightFront.setPower(0.65);
                leftRear.setPower(0.65);
                rightRear.setPower(0.65);
            } else if (gamepad1.dpad_down) { // backwards
                leftFront.setPower(-0.65);
                rightFront.setPower(-0.65);
                leftRear.setPower(-0.65);
                rightRear.setPower(-0.65);
            } else if (G1rightBumper) { // arm up
                rightArm.setPower(0.75);
                leftArm.setPower(0.75);
            } else if (G1leftBumper) { // arm down
                rightArm.setPower(-0.45);
                leftArm.setPower(-0.45);
            } else if (gamepad1.x) { // arm power off
                rightArm.setPower(0);
                leftArm.setPower(0);
            } else {
                leftFront.setPower(0);
                rightFront.setPower(0);
                leftRear.setPower(0);
                rightRear.setPower(0);
            }

            telemetry.addData("Status", "Running");
            telemetry.update();

        }
    }

    public void armUp(double distance) {

        //Reset Encoders
        rightArm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightArm.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        rightArm.setPower(0.45);

        while (rightArm.getCurrentPosition() < distance) {
            telemetry.addData("Arm Encoder", rightArm.getCurrentPosition());
            telemetry.update();
        }

        rightArm.setPower(0);

        sleep(500);

    }

    public void armDown(double distance) {

        //Reset Encoders
        rightArm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightArm.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        rightArm.setPower(-0.45);

        while (-rightArm.getCurrentPosition() < distance) {
            telemetry.addData("Arm Encoder", rightArm.getCurrentPosition());
            telemetry.update();
        }

        rightArm.setPower(0);

        sleep(500);

    }

}
