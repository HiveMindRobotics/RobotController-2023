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
        val armLeft = hardwareMap.get(DcMotor::class.java, "leftBottomArm")
        val armRight = hardwareMap.get(DcMotor::class.java, "rightBottomArm")
        val armMiddle = hardwareMap.get(DcMotor::class.java, "middleArm")
        val armWrist = hardwareMap.get(DcMotor::class.java, "wristArm")
        waitForStart()

        armLeft.targetPosition = armLeft.currentPosition
        armRight.targetPosition = armRight.currentPosition
        armMiddle.targetPosition = armMiddle.currentPosition
        armWrist.targetPosition = armWrist.currentPosition
        
        armLeft.mode = DcMotor.RunMode.RUN_TO_POSITION
        armRight.mode = DcMotor.RunMode.RUN_TO_POSITION
        armMiddle.mode = DcMotor.RunMode.RUN_TO_POSITION
        armWrist.mode = DcMotor.RunMode.RUN_TO_POSITION

        val p = 100.0f
        
        armLeft.power = p.toDouble()
        armRight.power = p.toDouble()
        armMiddle.power = p.toDouble()
        armWrist.power = p.toDouble()
        
        while (opModeIsActive()) {
            val z_translation = gamepad1.left_trigger - gamepad1.right_trigger
            val x_translation = -gamepad1.left_stick_x

            val leftPower = -smooth((z_translation + x_translation))
            val rightPower = smooth((z_translation - x_translation))

            leftMotor.power = leftPower
            rightMotor.power = rightPower

            // val leftBottomarmPower = gamepad2.left_stick_y.toDouble()
            // val rightBottomarmPower = -leftBottomarmPower
            // val middlearmPower = gamepad2.right_stick_y.toDouble()
            // val wristPower = gamepad2.right_trigger.toDouble() - gamepad2.left_trigger.toDouble()


            if (gamepad2.left_stick_y != 0.0f) {
                armLeft.targetPosition = armLeft.currentPosition + (gamepad2.left_stick_y * 10).toInt()
                armRight.targetPosition = armRight.currentPosition - (gamepad2.left_stick_y * 10).toInt()
            }
            else {
                armLeft.targetPosition = armLeft.currentPosition
                armRight.targetPosition = armRight.currentPosition
            }
            
            if (gamepad2.right_stick_y != 0.0f) {
                armMiddle.targetPosition = armMiddle.currentPosition + (gamepad2.right_stick_y * 10).toInt()
            }
            else {
                armMiddle.targetPosition = armMiddle.currentPosition
            }
            
            if (gamepad2.left_trigger != 0.0f || gamepad2.right_trigger != 0.0f) {
                armWrist.targetPosition = armWrist.currentPosition + ((gamepad2.right_trigger - gamepad2.left_trigger) * 10).toInt()
            }
            else {
                armWrist.targetPosition = armWrist.currentPosition
            }
            
            // leftBottomarm.power = smooth(leftBottomarmPower.toFloat())
            // rightBottomarm.power = smooth(rightBottomarmPower.toFloat())
            // middlearm.power = smooth(middlearmPower.toFloat())
            // wristarm.power = smooth(wristPower.toFloat())
        }
    }
}
