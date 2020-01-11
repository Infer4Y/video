package ui

import bvanseg.kotlincommons.graphics.Color
import bvanseg.openawt.Graphics
import java.util.*
import kotlin.random.Random.Default.nextDouble
import kotlin.random.Random.Default.nextFloat

class Bar (x: Float, y: Float, var width: Float, var height: Float) : Component(x, y) {
    var color: Color = Color.ALICE_BLUE
    var max = 1000f
    var speed = nextDouble(1.0, 15.0).toFloat()

    override fun render(graphics: Graphics){
        graphics.color = color
        graphics.fillRect(x, y, width, height)
    }
}