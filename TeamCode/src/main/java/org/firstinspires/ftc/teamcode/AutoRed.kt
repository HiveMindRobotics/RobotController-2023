package org.firstinspires.ftc.teamcode

import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import com.qualcomm.robotcore.hardware.*


@Autonomous(name = "AutoRed", group = "Linear Opmode")
class AutoRed : LinearOpMode() {
    override fun runOpMode() {
        // val leftMotor = hardwareMap.get(DcMotor::class.java, "leftMotor")
        // val rightMotor = hardwareMap.get(DcMotor::class.java, "rightMotor")
        // val armLeft = hardwareMap.get(DcMotor::class.java, "leftBottomArm")
        // val armRight = hardwareMap.get(DcMotor::class.java, "rightBottomArm")
        // val armMiddle = hardwareMap.get(DcMotor::class.java, "middleArm")
        // val armWrist = hardwareMap.get(DcMotor::class.java, "wristArm")
        // val slide = hardwareMap.get(DcMotor::class.java, "slide")
        // val armServo = hardwareMap.get(Servo::class.java, "servo0")
        // val planeServo = hardwareMap.get(Servo::class.java, "servo1")
        
        val leftFrontMotor = hardwareMap.get(DcMotor::class.java, "leftFrontMotor")
        val leftBackMotor = hardwareMap.get(DcMotor::class.java, "leftBackMotor")
        val rightFrontMotor = hardwareMap.get(DcMotor::class.java, "rightFrontMotor")
        val rightBackMotor = hardwareMap.get(DcMotor::class.java, "rightBackMotor")

        waitForStart()
        
        leftFrontMotor.power  = 10.0
        leftBackMotor.power   = -10.0
        rightFrontMotor.power = 10.0
        rightBackMotor.power  = 10.0
        
        Thread.sleep(1000)

        leftFrontMotor.power  = 0.0
        leftBackMotor.power   = 0.0
        rightFrontMotor.power = 0.0
        rightBackMotor.power  = 0.0
    }
}
