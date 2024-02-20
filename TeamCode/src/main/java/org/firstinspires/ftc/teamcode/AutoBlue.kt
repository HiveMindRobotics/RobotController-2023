package org.firstinspires.ftc.teamcode

import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import com.qualcomm.robotcore.hardware.*


@Autonomous(name = "AutoBlue", group = "Linear Opmode")
class AutoBlue : LinearOpMode() {
    override fun runOpMode() {
        val leftMotor = hardwareMap.get(DcMotor::class.java, "leftMotor")
        val rightMotor = hardwareMap.get(DcMotor::class.java, "rightMotor")
        val armLeft = hardwareMap.get(DcMotor::class.java, "leftBottomArm")
        val armRight = hardwareMap.get(DcMotor::class.java, "rightBottomArm")
        val armMiddle = hardwareMap.get(DcMotor::class.java, "middleArm")
        val armWrist = hardwareMap.get(DcMotor::class.java, "wristArm")
        val slide = hardwareMap.get(DcMotor::class.java, "slide")
        val armServo = hardwareMap.get(Servo::class.java, "servo0")
        val planeServo = hardwareMap.get(Servo::class.java, "servo1")

        waitForStart()
        leftMotor.power = 10.0
        rightMotor.power = -10.0
        
        Thread.sleep(1000)

        leftMotor.power = 0.0
        rightMotor.power = 0.0
    }
}