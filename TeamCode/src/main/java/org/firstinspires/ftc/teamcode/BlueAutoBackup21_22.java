
package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor.ZeroPowerBehavior;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name ="BlueAutoBackup21_22", group="Linear Opmode")
public class BlueAutoBackup21_22 extends LinearOpMode {

    private DcMotor FRightDrive;
    private DcMotor FLeftDrive;
    private DcMotor BRightDrive;
    private DcMotor BLeftDrive;
    private DcMotor Carousel;
    private DcMotor Arm;
    private Servo Intake;
    int rightTarget = 0;
    int leftTarget = 0;

    //Convert from the counts per revolution of the encoder to counts per inch

    @Override
    public void runOpMode() {

        FLeftDrive = hardwareMap.get(DcMotor.class,"motor2"); //frontleft, port 0
        FRightDrive = hardwareMap.get(DcMotor.class,"motor1");  //frontright, port 1
        BRightDrive = hardwareMap.get(DcMotor.class, "motor3");
        BLeftDrive = hardwareMap.get(DcMotor.class, "motor4");
        Carousel = hardwareMap.get(DcMotor.class, "motor5");
        Arm = hardwareMap.get(DcMotor.class, "motor6");
        Intake = hardwareMap.get(Servo.class, "servo1");

        // reverse left drive motor direciton
        FRightDrive.setDirection(DcMotorSimple.Direction.REVERSE);
        BRightDrive.setDirection(DcMotorSimple.Direction.REVERSE);
        FLeftDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        FRightDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        BLeftDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        BRightDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        FLeftDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        FRightDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        //Arm.setDirection(DcMotorSimple.Direction.REVERSE);
        //Arm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);



        waitForStart();
        if (opModeIsActive()) {

            //Reset Encoders
            BLeftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            BRightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            BLeftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            BRightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            FLeftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            FRightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            FLeftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            FRightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

            //Arm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            //Arm.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            Intake.setPosition(1);
            sleep(1000);

            Arm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            Arm.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

            Arm.setPower(0.25);
            while (Arm.getCurrentPosition() < 200) {
                telemetry.addData("Arm encoder", Arm.getCurrentPosition());
                telemetry.update();
            }
            Arm.setPower(0);
            sleep(250);

            //STEP 1: move forward small amount
            BLeftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            BRightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            BLeftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            BRightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            FLeftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            FRightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            FLeftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            FRightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

            BLeftDrive.setPower(1);
            BRightDrive.setPower(1);
            FLeftDrive.setPower(1);
            FRightDrive.setPower(1);
            while(BLeftDrive.getCurrentPosition() < 150){
                telemetry.addData("Left Encoder", BLeftDrive.getCurrentPosition());
                telemetry.addData("Right Encoder", BRightDrive.getCurrentPosition());
                telemetry.update();
            }

            // set motor power back to 0 
            BLeftDrive.setPower(0);
            BRightDrive.setPower(0);
            FLeftDrive.setPower(0);
            FRightDrive.setPower(0);
            sleep(250);

            //STEP 2: strafe to carousel
            BLeftDrive.setPower(-1);
            BRightDrive.setPower(1);
            FLeftDrive.setPower(1);
            FRightDrive.setPower(-1);
            while(-BLeftDrive.getCurrentPosition() < 1500){
                telemetry.addData("Left Encoder", BLeftDrive.getCurrentPosition());
                telemetry.addData("Right Encoder", BRightDrive.getCurrentPosition());
                telemetry.update();
            }

            // set motor power back to 0 
            BLeftDrive.setPower(0);
            BRightDrive.setPower(0);
            FLeftDrive.setPower(0);
            FRightDrive.setPower(0);
            sleep(750);

            //STEP 3: spin carousel
            Carousel.setPower(0.5);
            sleep(4000);
            Carousel.setPower(0);


            BLeftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            BRightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            BLeftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            BRightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            FLeftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            FRightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            FLeftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            FRightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);


            //Step 4: stafe away from carousel
            BLeftDrive.setPower(-1);
            BRightDrive.setPower(1);
            FLeftDrive.setPower(1);
            FRightDrive.setPower(-1);
            while(-BLeftDrive.getCurrentPosition() < 200){
                telemetry.addData("Left Encoder", BLeftDrive.getCurrentPosition());
                telemetry.addData("Right Encoder", BRightDrive.getCurrentPosition());
                telemetry.update();
            }

            // set motor power back to 0 
            BLeftDrive.setPower(0);
            BRightDrive.setPower(0);
            FLeftDrive.setPower(0);
            FRightDrive.setPower(0);
            sleep(250);


            BLeftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            BRightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            BLeftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            BRightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            FLeftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            FRightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            FLeftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            FRightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

            //Step 5: Turn towards blue shipping hub
            BLeftDrive.setPower(1);
            BRightDrive.setPower(-1);
            FLeftDrive.setPower(1);
            FRightDrive.setPower(-1);
            while(BLeftDrive.getCurrentPosition() < 310){
                telemetry.addData("Left Encoder", BLeftDrive.getCurrentPosition());
                telemetry.addData("Right Encoder", BRightDrive.getCurrentPosition());
                telemetry.update();
            }

            // set motor power back to 0 
            BLeftDrive.setPower(0);
            BRightDrive.setPower(0);
            FLeftDrive.setPower(0);
            FRightDrive.setPower(0);
            sleep(250);


            BLeftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            BRightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            BLeftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            BRightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            FLeftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            FRightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            FLeftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            FRightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

            //Step 6: Move to blue shipping hub
            BLeftDrive.setPower(1);
            BRightDrive.setPower(1);
            FLeftDrive.setPower(1);
            FRightDrive.setPower(1);
            while(BLeftDrive.getCurrentPosition() < 1200){
                telemetry.addData("Left Encoder", BLeftDrive.getCurrentPosition());
                telemetry.addData("Right Encoder", BRightDrive.getCurrentPosition());
                telemetry.update();
            }

            // set motor power back to 0 
            BLeftDrive.setPower(0);
            BRightDrive.setPower(0);
            FLeftDrive.setPower(0);
            FRightDrive.setPower(0);
            sleep(250);
            
            /*Step 7: Lift arm
            Arm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            Arm.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            Arm.setPower(0.25);
            while (Arm.getCurrentPosition() < 1175) {
                telemetry.addData("Arm encoder", Arm.getCurrentPosition());
                telemetry.update();
            }
            Arm.setPower(0);
            sleep(250);
            */

            BLeftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            BRightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            BLeftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            BRightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            FLeftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            FRightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            FLeftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            FRightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

            //Step 8: Move foward a lil
            BLeftDrive.setPower(1);
            BRightDrive.setPower(1);
            FLeftDrive.setPower(1);
            FRightDrive.setPower(1);
            while(BLeftDrive.getCurrentPosition() < 200){
                telemetry.addData("Left Encoder", BLeftDrive.getCurrentPosition());
                telemetry.addData("Right Encoder", BRightDrive.getCurrentPosition());
                telemetry.update();
            }

            // set motor power back to 0 
            BLeftDrive.setPower(0);
            BRightDrive.setPower(0);
            FLeftDrive.setPower(0);
            FRightDrive.setPower(0);
            sleep(250);


            BLeftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            BRightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            BLeftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            BRightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            FLeftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            FRightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            FLeftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            FRightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

            //Step 9: Drop it
            //Intake.setPosition(0.25);

            //Step 10: Move back a lil
            BLeftDrive.setPower(-1);
            BRightDrive.setPower(-1);
            FLeftDrive.setPower(-1);
            FRightDrive.setPower(-1);
            while(-BLeftDrive.getCurrentPosition() < 250){
                telemetry.addData("Left Encoder", BLeftDrive.getCurrentPosition());
                telemetry.addData("Right Encoder", BRightDrive.getCurrentPosition());
                telemetry.update();
            }

            // set motor power back to 0 
            BLeftDrive.setPower(0);
            BRightDrive.setPower(0);
            FLeftDrive.setPower(0);
            FRightDrive.setPower(0);
            sleep(250);


            BLeftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            BRightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            BLeftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            BRightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            FLeftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            FRightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            FLeftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            FRightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            
            /*Step 11: Lower arm
            Arm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            Arm.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            Arm.setPower(-0.25);
            while (-Arm.getCurrentPosition() < 950) {
                telemetry.addData("Arm encoder", Arm.getCurrentPosition());
                telemetry.update();
            }
            Arm.setPower(0);
            sleep(250);
            */

            BLeftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            BRightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            BLeftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            BRightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            FLeftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            FRightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            FLeftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            FRightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

            //Step 12: Turn towards cube
            BLeftDrive.setPower(1);
            BRightDrive.setPower(-1);
            FLeftDrive.setPower(1);
            FRightDrive.setPower(-1);
            while(BLeftDrive.getCurrentPosition() < 210){
                telemetry.addData("Left Encoder", BLeftDrive.getCurrentPosition());
                telemetry.addData("Right Encoder", BRightDrive.getCurrentPosition());
                telemetry.update();
            }

            // set motor power back to 0 
            BLeftDrive.setPower(0);
            BRightDrive.setPower(0);
            FLeftDrive.setPower(0);
            FRightDrive.setPower(0);
            sleep(250);


            BLeftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            BRightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            BLeftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            BRightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            FLeftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            FRightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            FLeftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            FRightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

            //Step 13: Back up to cube
            BLeftDrive.setPower(-1);
            BRightDrive.setPower(-1);
            FLeftDrive.setPower(-1);
            FRightDrive.setPower(-1);
            while(-BLeftDrive.getCurrentPosition() < 1000){
                telemetry.addData("Left Encoder", BLeftDrive.getCurrentPosition());
                telemetry.addData("Right Encoder", BRightDrive.getCurrentPosition());
                telemetry.update();
            }

            // set motor power back to 0 
            BLeftDrive.setPower(0);
            BRightDrive.setPower(0);
            FLeftDrive.setPower(0);
            FRightDrive.setPower(0);
            sleep(250);


            sleep(30000);

        }
    }
}