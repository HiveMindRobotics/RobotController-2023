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
        // val armLeft = hardwareMap.get(DcMotor::class.java, "leftBottomArm")
        // val armRight = hardwareMap.get(DcMotor::class.java, "rightBottomArm")
        // val armMiddle = hardwareMap.get(DcMotor::class.java, "middleArm")
        // val armWrist = hardwareMap.get(DcMotor::class.java, "wristArm")
        // val slide = hardwareMap.get(DcMotor::class.java, "slide")
        // val armServo = hardwareMap.get(Servo::class.java, "servo0")
        // val planeServo = hardwareMap.get(Servo::class.java, "servo1")
        
        waitForStart()

        var speed_modifier = 1.0
        
        while (opModeIsActive()) {
            val drive = gamepad1.left_stick_y.toDouble()
            val strafe = -gamepad1.left_stick_x.toDouble()
            val twist = -gamepad1.right_stick_x.toDouble()

            if (gamepad1.b && !gamepad1.x) 
                speed_modifier = .5
            if (gamepad1.x && !gamepad1.b)
                speed_modifier = 2

            leftFrontMotor.power  = (drive + strafe + twist) * speed_modifier
            rightFrontMotor.power = (drive - strafe - twist) * speed_modifier
            leftBackMotor.power   = (drive - strafe + twist) * speed_modifier * -1
            rightBackMotor.power  = (drive + strafe - twist) * speed_modifier
        }
    }
}
