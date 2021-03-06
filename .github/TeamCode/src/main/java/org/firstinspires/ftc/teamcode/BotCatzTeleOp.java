package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

/**
 * Created by ITSA-GAMINGHP2 on 11/2/2017.
 */
@TeleOp
public class BotCatzTeleOp extends LinearOpMode {
    private DcMotor leftMotor;
    private DcMotor rightMotor;
    private DcMotor leftMotor2;
    private DcMotor rightMotor2;
    @Override
    public void runOpMode()
    {
        leftMotor = hardwareMap.dcMotor.get("leftMotor");
        rightMotor = hardwareMap.dcMotor.get("rightMotor");
        rightMotor2 = hardwareMap.dcMotor.get("rightMotor2");
        leftMotor2 = hardwareMap.dcMotor.get("leftMotor2");
        leftMotor.setDirection(DcMotor.Direction.REVERSE);
        leftMotor2.setDirection(DcMotor.Direction.REVERSE);
        waitForStart();
        while(opModeIsActive()){
            leftMotor.setPower(-gamepad1.left_stick_y);
            leftMotor2.setPower(-gamepad1.left_stick_y);
            rightMotor.setPower(-gamepad1.right_stick_y);
            rightMotor2.setPower(-gamepad1.right_stick_y);

            idle();
        }


    }
}
