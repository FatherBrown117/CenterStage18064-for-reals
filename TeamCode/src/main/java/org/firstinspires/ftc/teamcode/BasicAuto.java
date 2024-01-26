package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@Autonomous(name ="BasicAuto", group="Linear Opmode")
public class BasicAuto extends LinearOpMode {

    private DcMotor leftFront = null;
    private DcMotor rightFront = null;
    private DcMotor leftRear = null;
    private DcMotor rightRear = null;
    private DcMotor rLift = null;
    private DcMotor lLift = null;
    //private DcMotor vector = null;
    private CRServo leftIntake = null;
    private CRServo rightIntake = null;
    private CRServo dread = null;
    private Servo leftPull = null;
    private Servo rightPull = null;

    @Override
    public void runOpMode() throws InterruptedException {

        //hardware mapping
        leftFront = hardwareMap.get(DcMotor.class,"leftFront"); //frontleft, port 0
        rightFront = hardwareMap.get(DcMotor.class,"rightFront");  //frontright, port 1
        leftRear = hardwareMap.get(DcMotor.class,"leftRear"); //backleft, port 3
        rightRear = hardwareMap.get(DcMotor.class,"rightRear");  //backright, port 2
        rLift = hardwareMap.get(DcMotor.class,"rLift");
        lLift = hardwareMap.get(DcMotor.class,"lLift");
        leftIntake = hardwareMap.get(CRServo.class,"leftIntake");
        rightIntake = hardwareMap.get(CRServo.class,"rightIntake");
        dread = hardwareMap.get(CRServo.class,"dread");
        //vector = hardwareMap.get(DcMotor.class,"vector");

        rightPull = hardwareMap.get(Servo.class, "rightPull");
        leftPull = hardwareMap.get(Servo.class, "leftPull");

        leftFront.setDirection(DcMotor.Direction.REVERSE);
        rightFront.setDirection(DcMotor.Direction.FORWARD);
        leftRear.setDirection(DcMotor.Direction.REVERSE);
        rightRear.setDirection(DcMotor.Direction.REVERSE);
        rLift.setDirection(DcMotor.Direction.FORWARD);
        lLift.setDirection(DcMotor.Direction.REVERSE);
        //vector.setDirection(DcMotor.Direction.FORWARD);

        waitForStart();
        while(opModeIsActive()) {
            driveForward(300);
            sleep(30000);

        }

    }

    public double arm_distance(float inches) {
        return inches * (537.6 / (1.5 * 3.141592));
    }

    public double distance(float inches) {
        //537.6 pulses per rotation
        return inches * (537.6 / (3.75 * 3.141592));
    }

    public void driveForward(double distance) {

        //Reset Encoders
        //leftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        //rightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        //leftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        //rightFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        //leftRear.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightRear.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightRear.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        //leftRear.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        //leftFront.setPower(0.5);
        //rightFront.setPower(0.5);
        //leftRear.setPower(0.5);
        rightRear.setPower(0.5);


        while (-rightRear.getCurrentPosition() < distance) {
            telemetry.addData("Encoder", rightRear.getCurrentPosition());
            telemetry.update();
        }

        //leftFront.setPower(0);
        //rightFront.setPower(0);
        //leftRear.setPower(0);
        rightRear.setPower(0);

        sleep(500);

    }

    public void driveBackward(double distance) {

        //Reset Encoders
        leftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftRear.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightRear.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightRear.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftRear.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        leftFront.setPower(-0.5);
        rightFront.setPower(-0.5);
        leftRear.setPower(-0.5);
        rightRear.setPower(-0.5);

        while (-rightFront.getCurrentPosition() < distance) {
            telemetry.addData("Left Encoder", rightFront.getCurrentPosition());
            telemetry.update();
        }

        leftFront.setPower(0);
        rightFront.setPower(0);
        leftRear.setPower(0);
        rightRear.setPower(0);

        sleep(500);

    }

    public void strafeRight(double distance) {

        //Reset Encoders
        leftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftRear.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightRear.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightRear.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftRear.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        leftFront.setPower(0.5);
        rightFront.setPower(-0.5);
        leftRear.setPower(-0.5);
        rightRear.setPower(0.5);

        while (-rightFront.getCurrentPosition() < distance) {
            telemetry.addData("Left Encoder", rightFront.getCurrentPosition());
            telemetry.update();
        }

        leftFront.setPower(0);
        rightFront.setPower(0);
        leftRear.setPower(0);
        rightRear.setPower(0);

        sleep(500);

    }

    public void strafeLeft(double distance) {

        //Reset Encoders
        leftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftRear.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightRear.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightRear.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftRear.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        leftFront.setPower(-0.5);
        rightFront.setPower(0.5);
        leftRear.setPower(0.5);
        rightRear.setPower(-0.5);

        while (rightFront.getCurrentPosition() < distance) {
            telemetry.addData("Left Encoder", rightFront.getCurrentPosition());
            telemetry.update();
        }

        leftFront.setPower(0);
        rightFront.setPower(0);
        leftRear.setPower(0);
        rightRear.setPower(0);

        sleep(500);

    }



    public void turnRight(double distance) {

        //Reset Encoders
        leftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftRear.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightRear.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightRear.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftRear.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        leftFront.setPower(0.5);
        rightFront.setPower(-0.5);
        leftRear.setPower(0.5);
        rightRear.setPower(-0.5);

        while (-rightFront.getCurrentPosition() < (distance - 10)) {
            telemetry.addData("Left Encoder", rightFront.getCurrentPosition());
            telemetry.update();
        }

        //Slowing down to reduce momentum
        leftFront.setPower(0.1);
        rightFront.setPower(-0.1);
        leftRear.setPower(0.1);
        rightRear.setPower(-0.1);

        while (-rightFront.getCurrentPosition() < distance) {
            telemetry.addData("Left Encoder", rightFront.getCurrentPosition());
            telemetry.update();
        }

        leftFront.setPower(0);
        rightFront.setPower(0);
        leftRear.setPower(0);
        rightRear.setPower(0);

        sleep(500);

    }

    public void turnLeft(double distance) {

        //Reset Encoders
        leftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftRear.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightRear.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightRear.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftRear.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        leftFront.setPower(-0.5);
        rightFront.setPower(0.5);
        leftRear.setPower(-0.5);
        rightRear.setPower(0.5);

        while (rightFront.getCurrentPosition() < (distance - 10)) {
            telemetry.addData("Left Encoder", rightFront.getCurrentPosition());
            telemetry.update();
        }

        //Slowing down to reduce momentum
        leftFront.setPower(-0.1);
        rightFront.setPower(0.1);
        leftRear.setPower(-0.1);
        rightRear.setPower(0.1);

        while (rightFront.getCurrentPosition() < distance) {
            telemetry.addData("Left Encoder", rightFront.getCurrentPosition());
            telemetry.update();
        }

        leftFront.setPower(0);
        rightFront.setPower(0);
        leftRear.setPower(0);
        rightRear.setPower(0);

        sleep(500);

    }
}
//Aidan was here