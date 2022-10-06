package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name = "BasicTeleOp")

public class BasicTeleOp extends LinearOpMode {

    private DcMotor frontLeft = null;
    private DcMotor frontRight = null;
    private DcMotor backLeft = null;
    private DcMotor backRight = null;
    //private DcMotor motor5 = null;
    //private DcMotor motor6 = null;
    //private Servo servo1 = null;
    //private Servo servo2 = null;

    @Override
    public void runOpMode() {

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        frontLeft = hardwareMap.get(DcMotor.class,"frontLeft"); //frontleft, port 0
        frontRight = hardwareMap.get(DcMotor.class,"frontRight");  //frontright, port 1
        backLeft = hardwareMap.get(DcMotor.class,"backLeft"); //backleft, port 3
        backRight = hardwareMap.get(DcMotor.class,"backRight");  //backright, port 2
        //motor5 = hardwareMap.get(DcMotor.class, "motor5"); //carousel spinner
        //motor6 = hardwareMap.get(DcMotor.class, "motor6"); //slide motor

        //servo1 = hardwareMap.get(Servo.class, "servo1"); //servo intake right
        //servo2 = hardwareMap.get(Servo.class, "servo2");

        frontLeft.setDirection(DcMotor.Direction.REVERSE);
        frontRight.setDirection(DcMotor.Direction.FORWARD);
        backLeft.setDirection(DcMotor.Direction.REVERSE);
        backRight.setDirection(DcMotor.Direction.FORWARD);

        //motor6.setDirection(DcMotor.Direction.REVERSE);


        waitForStart();

        while (opModeIsActive()) {

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


            double G1rightStickY = -gamepad1.right_stick_y/1.5;
            double G1leftStickY = -gamepad1.left_stick_y/1.5;
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

            if (G1A) {
                speed = false;
            }

            if (G1B) {
                speed = true;
            }

            if (G1leftStickY > 0 && speed == true) {  // Clockwise Fast
                frontLeft.setPower(.5);
                backLeft.setPower(.5);
                frontRight.setPower(-.5);
                backRight.setPower(-.5);
            }

            if (G1leftStickY < 0 && speed == true) { //Counterclockwise Fast
                frontLeft.setPower(-.5);
                backLeft.setPower(-.5);
                frontRight.setPower(.5);
                backRight.setPower(.5);
            }
            if (G1leftStickY > 0 && speed == false) {  // Clockwise Slow
                frontLeft.setPower(.25);
                backLeft.setPower(.25);
                frontRight.setPower(-.25);
                backRight.setPower(-.25);
            }

            if (G1leftStickY < 0 && speed == false) { // Counterclockwise Slow
                frontLeft.setPower(-.25);
                backLeft.setPower(-.25);
                frontRight.setPower(.25);
                backRight.setPower(.25);
            }

            if (G1rightStickY < 0) {
                frontLeft.setPower(.5);
                backLeft.setPower(.5);
                frontRight.setPower(.5);
                backRight.setPower(.5);
            }
            if (G1rightStickY > 0) {
                frontLeft.setPower(-.75);
                backLeft.setPower(-.75);
                frontRight.setPower(-.75);
                backRight.setPower(-.75);
            }
            if (G1rightStickY < 0) {
                frontLeft.setPower(.75);
                backLeft.setPower(.75);
                frontRight.setPower(.75);
                backRight.setPower(.75);
            }

            if (gamepad1.dpad_right) { //strafe right
                frontLeft.setPower(.75);
                frontRight.setPower(-.75);
                backLeft.setPower(-.75);
                backRight.setPower(.75);
            }

            else if (gamepad1.dpad_left) { //strafe left
                frontLeft.setPower(-.75);
                frontRight.setPower(.75);
                backLeft.setPower(.75);
                backRight.setPower(-.75);
            } else if (gamepad1.dpad_up) {
                frontLeft.setPower(.75);
                frontRight.setPower(.75);
                backLeft.setPower(.75);
                backRight.setPower(.75);

            } else if (gamepad1.dpad_down){
                frontLeft.setPower(-.75);
                frontRight.setPower(-.75);
                backLeft.setPower(-.75);
                backRight.setPower(-.75);

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
