package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name = "BasicTeleOp")

public class BasicTeleOp extends LinearOpMode {

    BasicAuto obj = new BasicAuto();

    private DcMotor frontLeft = null;
    private DcMotor frontRight = null;
    private DcMotor backLeft = null;
    private DcMotor backRight = null;
    private DcMotor armMotor = null;
    private Servo clawServo = null;
    //private Servo servo2 = null;

    @Override
    public void runOpMode() {

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        frontLeft = hardwareMap.get(DcMotor.class,"frontLeft"); //frontleft, port 0
        frontRight = hardwareMap.get(DcMotor.class,"frontRight");  //frontright, port 1
        backLeft = hardwareMap.get(DcMotor.class,"backLeft"); //backleft, port 3
        backRight = hardwareMap.get(DcMotor.class,"backRight");  //backright, port 2
        armMotor = hardwareMap.get(DcMotor.class, "armMotor");

        clawServo = hardwareMap.get(Servo.class, "clawServo");

        frontLeft.setDirection(DcMotor.Direction.REVERSE);
        frontRight.setDirection(DcMotor.Direction.FORWARD);
        backLeft.setDirection(DcMotor.Direction.REVERSE);
        backRight.setDirection(DcMotor.Direction.FORWARD);

        //motor6.setDirection(DcMotor.Direction.REVERSE);


        waitForStart();

        while (opModeIsActive()) {

            /*
            double m1Power;
            double m2Power;
            double m3Power;
            double m4Power;
            //double m5Power;
            //double m6Power;

            m1Power = -gamepad1.left_stick_y/1.5;
            m2Power = -gamepad1.right_stick_y/1.5;
            m3Power = -gamepad1.left_stick_y/1.5;
            m4Power = -gamepad1.right_stick_y/1.5;
            // m5Power = gamepad2.left_stick_y;
            // m6Power = -gamepad1.right_trigger+gamepad1.left_trigger;

            frontLeft.setPower(m1Power);
            frontRight.setPower(m2Power);
            backLeft.setPower(m3Power);
            backRight.setPower(m4Power);
            //motor5.setPower(m5Power);
            //motor6.setPower(m6Power);
            */

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

            boolean speed;
            speed = false;

            if (gamepad1.a) {
                speed = false;
            } else if (gamepad1.b) {
                speed = true;
            }

            if (gamepad2.a){
                clawServo.setPosition(.25);
            } else if (gamepad2.b){
                clawServo.setPosition(0.5);
            }

            if (gamepad2.dpad_up) {
                armMotor.setPower(1);
                telemetry.addData("Arm Encoder", armMotor.getCurrentPosition());
                telemetry.update();
            } else if (gamepad2.dpad_down) {
                telemetry.addData("Arm Encoder", armMotor.getCurrentPosition());
                telemetry.update();
                armMotor.setPower(-1);
            } else if (gamepad2.dpad_right) {
                armMotor.setPower(0);
            } else if (gamepad2.dpad_left) {
                armMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                armMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

                obj.armUp(3126);
            }
            telemetry.update();
            if (G1rightStickX > 0 && speed == true) {  // Clockwise Fast
                frontLeft.setPower(1);
                backLeft.setPower(1);
                frontRight.setPower(-1);
                backRight.setPower(-1);
            } else if (G1rightStickX < 0 && speed == true) { //Counterclockwise Fast
                frontLeft.setPower(-1);
                backLeft.setPower(-1);
                frontRight.setPower(1);
                backRight.setPower(1);
            } else if (G1rightStickX > 0 && speed == false) {  // Clockwise Slow
                frontLeft.setPower(.75);
                backLeft.setPower(.75);
                frontRight.setPower(-.75);
                backRight.setPower(-.75);
            } else if (G1rightStickX < 0 && speed == false) { // Counterclockwise Slow
                frontLeft.setPower(-.75);
                backLeft.setPower(-.75);
                frontRight.setPower(.75);
                backRight.setPower(.75);
            } else if (G1leftStickY > 0 && speed == true) { //Driving forward
                frontLeft.setPower(-1);
                backLeft.setPower(-1);
                frontRight.setPower(-1);
                backRight.setPower(-1);
            } else if (G1leftStickY > 0 && speed == false) {
                frontLeft.setPower(-.75);
                backLeft.setPower(-.75);
                frontRight.setPower(-.75);
                backRight.setPower(-.75);
            } else if (G1leftStickY < 0 && speed == true) {//Driving backward
                frontLeft.setPower(.75);
                backLeft.setPower(.75);
                frontRight.setPower(.75);
                backRight.setPower(.75);
            } else if (G1leftStickY < 0 && speed == false) {
                frontLeft.setPower(.5);
                backLeft.setPower(.5);
                frontRight.setPower(.5);
                backRight.setPower(.5);
            } else if (gamepad1.dpad_right) { //strafe right
                frontLeft.setPower(.5);
                frontRight.setPower(-.5);
                backLeft.setPower(-.5);
                backRight.setPower(.5);
            } else if (gamepad1.dpad_left) { //strafe left
                frontLeft.setPower(-.5);
                frontRight.setPower(.5);
                backLeft.setPower(.5);
                backRight.setPower(-.5);
            } else if (gamepad1.dpad_up) {
                frontLeft.setPower(1);
                frontRight.setPower(1);
                backLeft.setPower(1);
                backRight.setPower(1);

            } else if (gamepad1.dpad_down) {
                frontLeft.setPower(-1);
                frontRight.setPower(-1);
                backLeft.setPower(-1);
                backRight.setPower(-1);
            } else if (G1rightBumper) {
                frontLeft.setPower(.5);
                frontRight.setPower(-.5);
                backLeft.setPower(-.5);
                backRight.setPower(.5);

            } else if (G1leftBumper) {
                frontLeft.setPower(-.5);
                frontRight.setPower(.5);
                backLeft.setPower(.5);
                backRight.setPower(-.5);

            } else {
                frontLeft.setPower(0);
                frontRight.setPower(0);
                backLeft.setPower(0);
                backRight.setPower(0);
            }
                /*
                if (G1B) {
                    servo1.setPosition(.25);
                    servo2.setPosition(.75); // continuously turn clockwise

                }else if (G1X) {
                    servo1.setPosition(.75);
                    servo2.setPosition(.25); // continuously turn counter clockwise

                } else if (G1A) {
                    servo1.setPosition(.5);
                    servo2.setPosition(.5);
                }
                */
            //servo2 on right
                /*
                if (gamepad1.dpad_up) {
                    motor5.setPower(.80);

                } else if (gamepad1.dpad_down) {
                    motor5.setPower(-.80);

                } else {
                    motor5.setPower(0);
                }

              if (gamepad1.right_bumper) {
                motor6.setPower(0.5);
            } else if (gamepad1.left_bumper) {
                motor6.setPower(-0.5);
            } else {
                motor6.setPower(0);
            }*/



            telemetry.addData("Status", "Running");
            telemetry.update();

        }
    }
}
