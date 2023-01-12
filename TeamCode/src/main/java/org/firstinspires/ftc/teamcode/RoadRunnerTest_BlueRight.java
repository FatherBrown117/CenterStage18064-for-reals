package org.firstinspires.ftc.teamcode;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;

@Autonomous(name ="RoadRunnerTest_BlueRight", group="Linear Opmode")
public class RoadRunnerTest_BlueRight extends LinearOpMode {

    private DcMotor frontLeft = null;
    private DcMotor frontRight = null;
    private DcMotor backRight = null;
    private DcMotor backLeft = null;
    private DcMotor armMotor = null;
    private Servo clawServo = null;

    @Override
    public void runOpMode() throws InterruptedException {

        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap); // Declaring the hardware maping for roadrunner

        frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        frontRight = hardwareMap.get(DcMotor.class, "frontRight");
        backLeft = hardwareMap.get(DcMotor.class, "backLeft");
        backRight = hardwareMap.get(DcMotor.class, "backRight");
        armMotor = hardwareMap.get(DcMotor.class, "armMotor");

        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        armMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);

        clawServo = hardwareMap.get(Servo.class, "clawServo");

        Trajectory miniDriveForwardTraj = drive.trajectoryBuilder(new Pose2d())
                .forward(2)
                .strafeLeft(5)
                .build();

        waitForStart();
        while(opModeIsActive()) {
            clawServo.setPosition(.9);
            driveForward(distance(2));
            strafeLeft(distance(37));
            driveForward(distance(30));
            armUp(distance(100));
            clawServo.setPosition(0.6);
            armDown(distance(70));
            driveBackward(distance(7));
            strafeRight(distance(10));
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

    public void servoOpen() {
        clawServo.setPosition(.5);
    }

    public void servoClose() {
        clawServo.setPosition(0);
    }

    public void driveForward(double distance) {

        //Reset Encoders
        frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        frontLeft.setPower(0.5);
        frontRight.setPower(0.5);
        backLeft.setPower(0.5);
        backRight.setPower(0.5);

        while (frontRight.getCurrentPosition() < (distance - 10)) {
            telemetry.addData("Left Encoder", frontRight.getCurrentPosition());
            telemetry.update();
        }

        //Slowing down to reduce momentum
        frontLeft.setPower(0.1);
        frontRight.setPower(0.1);
        backLeft.setPower(0.1);
        backRight.setPower(0.1);

        while (frontRight.getCurrentPosition() < distance) {
            telemetry.addData("Left Encoder", frontRight.getCurrentPosition());
            telemetry.update();
        }

        frontLeft.setPower(0);
        frontRight.setPower(0);
        backLeft.setPower(0);
        backRight.setPower(0);

        sleep(500);

    }

    public void driveBackward(double distance) {

        //Reset Encoders
        frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        frontLeft.setPower(-0.5);
        frontRight.setPower(-0.5);
        backLeft.setPower(-0.5);
        backRight.setPower(-0.5);

        while (-frontRight.getCurrentPosition() < distance) {
            telemetry.addData("Left Encoder", frontRight.getCurrentPosition());
            telemetry.update();
        }

        frontLeft.setPower(0);
        frontRight.setPower(0);
        backLeft.setPower(0);
        backRight.setPower(0);

        sleep(500);

    }

    public void strafeRight(double distance) {

        //Reset Encoders
        frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        frontLeft.setPower(0.5);
        frontRight.setPower(-0.5);
        backLeft.setPower(-0.5);
        backRight.setPower(0.5);

        while (-frontRight.getCurrentPosition() < distance) {
            telemetry.addData("Left Encoder", frontRight.getCurrentPosition());
            telemetry.update();
        }

        frontLeft.setPower(0);
        frontRight.setPower(0);
        backLeft.setPower(0);
        backRight.setPower(0);

        sleep(500);

    }

    public void strafeLeft(double distance) {

        //Reset Encoders
        frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        frontLeft.setPower(-0.5);
        frontRight.setPower(0.5);
        backLeft.setPower(0.5);
        backRight.setPower(-0.5);

        while (frontRight.getCurrentPosition() < distance) {
            telemetry.addData("Left Encoder", frontRight.getCurrentPosition());
            telemetry.update();
        }

        frontLeft.setPower(0);
        frontRight.setPower(0);
        backLeft.setPower(0);
        backRight.setPower(0);

        sleep(500);

    }

    public void armUp(double distance) {

        //Reset Encoders
        armMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        armMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        armMotor.setPower(0.5);

        while (armMotor.getCurrentPosition() < distance) {
            telemetry.addData("Arm Encoder", armMotor.getCurrentPosition());
            telemetry.update();
        }

        armMotor.setPower(0);

        sleep(500);

    }

    public void armDown(double distance) {

        //Reset Encoders
        armMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        armMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        armMotor.setPower(-0.5);

        while (-armMotor.getCurrentPosition() < distance) {
            telemetry.addData("Arm Encoder", armMotor.getCurrentPosition());
            telemetry.update();
        }

        armMotor.setPower(0);

        sleep(500);

    }
}
