package org.firstinspires.ftc.teamcode

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import com.qualcomm.robotcore.hardware.*
import kotlin.math.*

@TeleOp(name = "Ben Shapiro", group = "Linear Opmode")
class DriverControll : LinearOpMode() {
    private fun smooth(x: Float): Double {
        return if(x > 0) {
            sqrt(x).toDouble()
        } else {
            -sqrt(-x).toDouble()
        }
    }

    override fun runOpMode() {
        val leftFrontMotor = hardwareMap.get(DcMotor::class.java, "leftFrontMotor")
        val leftBackMotor = hardwareMap.get(DcMotor::class.java, "leftBackMotor")
        val rightFrontMotor = hardwareMap.get(DcMotor::class.java, "rightFrontMotor")
        val rightBackMotor = hardwareMap.get(DcMotor::class.java, "rightBackMotor")
        val armLeft = hardwareMap.get(DcMotor::class.java, "leftBottomArm")
        val armRight = hardwareMap.get(DcMotor::class.java, "rightBottomArm")
        val armMiddle = hardwareMap.get(DcMotor::class.java, "middleArm")
        val armWrist = hardwareMap.get(DcMotor::class.java, "wristArm")
        val slide = hardwareMap.get(DcMotor::class.java, "slide")
        val armServo = hardwareMap.get(Servo::class.java, "servo0")
        val planeServo = hardwareMap.get(Servo::class.java, "servo1")

        val drive = gamepad1.left_stick_y.toDouble()
        val strafe = gamepad1.left_stick_x.toDouble()
        val twist = gamepad1.right_stick_x.toDouble()
        leftFrontMotor.power = drive + strafe + twist
        rightFrontMotor.power = drive - strafe - twist
        leftBackMotor.power = drive - strafe + twist
        rightBackMotor.power = drive + strafe - twist
    }
}
