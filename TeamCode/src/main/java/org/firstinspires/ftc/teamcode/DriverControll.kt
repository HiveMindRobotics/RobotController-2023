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
        val wheelLeftFront = hardwareMap.get(DcMotor::class.java, "wheelLeftFront")
        val wheelLeftBack = hardwareMap.get(DcMotor::class.java, "wheelLeftBack")
        val wheelRightFront = hardwareMap.get(DcMotor::class.java, "wheelRightFront")
        val wheelRightBack = hardwareMap.get(DcMotor::class.java, "wheelRightBack")
        val hslideLeft = hardwareMap.get(DcMotor::class.java, "hslideLeft")
        val hslideRight = hardwareMap.get(DcMotor::class.java, "hslideRight")
        val vslideLeft = hardwareMap.get(DcMotor::class.java, "vslideLeft")
        val vslideRight = hardwareMap.get(DcMotor::class.java, "vslideRight")
        // val slide = hardwareMap.get(DcMotor::class.java, "slide")
        // val armServo = hardwareMap.get(Servo::class.java, "servo0")
        // val planeServo = hardwareMap.get(Servo::class.java, "servo1")
        
        waitForStart()

        var speed_modifier = 1.0
        var sprint = 0.5
        
        while (opModeIsActive()) {
            val drive = gamepad1.left_stick_y.toDouble()
            val strafe = -gamepad1.left_stick_x.toDouble()
            val twist = -gamepad1.right_stick_x.toDouble()

            if (gamepad1.b && !gamepad1.x) 
                speed_modifier *= .5
            if (gamepad1.x && !gamepad1.b)
                speed_modifier *= 2

            wheelLeftFront.power  = (drive + strafe + twist) * speed_modifier
            wheelRightFront.power = (drive - strafe - twist) * speed_modifier
            wheelLeftBack.power   = (drive - strafe + twist) * speed_modifier * -1
            wheelRightBack.power  = (drive + strafe - twist) * speed_modifier


            if(gamepad2.a)
                sprint = 1.0
            else
                sprint = 0.5
            
            if (gamepad2.left_trigger > 0.1 || gamepad2.right_trigger > 0.1) {
                hslideLeft.power  = -smooth(gamepad2.left_trigger - gamepad2.right_trigger) * sprint
                hslideRight.power = smooth(gamepad2.left_trigger - gamepad2.right_trigger) * sprint
            }
            else {
                hslideLeft.power = 0.0
                hslideRight.power = 0.0
            }
            if (abs(gamepad2.left_stick_y) > 0.1) {
                vslideLeft.power  = gamepad2.left_stick_y.toDouble() * sprint
                vslideRight.power = -gamepad2.left_stick_y.toDouble() * sprint
            }
            else {
                vslideLeft.power = 0.0
                vslideRight.power = 0.0
            }
        }
    }
}
