package org.firstinspires.ftc.teamcode

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import com.qualcomm.robotcore.hardware.DcMotor
import kotlin.math.sqrt

@TeleOp(name = "Driver Control", group = "Linear Opmode")
class DriverControl : LinearOpMode() {
    private fun smooth(x: Float): Double {
        return if(x > 0) {
            sqrt(x).toDouble()
        } else {
            -sqrt(-x).toDouble()
        }
    }
    override fun runOpMode() {
        val leftMotor = hardwareMap.get(DcMotor::class.java, "leftMotor")
        val rightMotor = hardwareMap.get(DcMotor::class.java, "rightMotor")
        waitForStart()
        while (opModeIsActive()) {
            // robot is tank drive
            // left stick controls forward and backward power
            // left trigger and right
        }
    }
}
