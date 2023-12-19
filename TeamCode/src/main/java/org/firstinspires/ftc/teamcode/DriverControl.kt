package org.firstinspires.ftc.teamcode

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import com.qualcomm.robotcore.hardware.DcMotor
import kotlin.math.*

@TeleOp(name = "abcdefghijklmnopqrstuvwxyz", group = "Linear Opmode")
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
        val leftBottomArm = hardwareMap.get(DcMotor::class.java, "leftBottomArm")
        val rightBottomArm = hardwareMap.get(DcMotor::class.java, "rightBottomArm")
        val middleArm = hardwareMap.get(DcMotor::class.java, "middleArm")
        val wristArm = hardwareMap.get(DcMotor::class.java, "wristArm")
        waitForStart()
        while (opModeIsActive()) {
            val z_translation = gamepad1.left_trigger - gamepad1.right_trigger
            val x_translation = -gamepad1.left_stick_x

            val leftPower = -smooth((z_translation + x_translation))
            val rightPower = smooth((z_translation - x_translation))

            leftMotor.power = leftPower
            rightMotor.power = rightPower

            val leftBottomArmPower = gamepad2.left_stick_y.toDouble()
            val rightBottomArmPower = -leftBottomArmPower
            val middleArmPower = gamepad2.right_stick_y.toDouble()
            val wristPower = gamepad2.right_trigger.toDouble() - gamepad2.left_trigger.toDouble()

            leftBottomArm.power = smooth(leftBottomArmPower.toFloat())
            rightBottomArm.power = smooth(rightBottomArmPower.toFloat())
            middleArm.power = smooth(middleArmPower.toFloat())
            wristArm.power = smooth(wristPower.toFloat())
        }
    }
}
