package windows

import bvanseg.kotlincommons.graphics.Color
import bvanseg.openawt.Graphics
import bvanseg.openawt.window.Window
import org.lwjgl.opengl.GL11.*
import ui.Bar
import kotlin.math.floor
import kotlin.math.pow
import kotlin.math.roundToInt
import kotlin.random.Random
import kotlin.random.Random.Default.nextInt

class VideoWindow(id: Long) : Window(id) {
    var bars : Array<Bar?> = arrayOfNulls<Bar?>(30000)
    var barColors : Array<Color?> = arrayOfNulls<Color?>(0xFFFFFF)

    override fun init() {
        super.init()

        for (i in 0 until 0xFFFFFF){
            barColors[i] = colorOut(i)
        }

        for (i in bars.indices){
            bars[i] = Bar(0f, 0f,10f,nextInt(600).toFloat())
            bars[i]?.color = barColors[nextInt(0xFFFFFF)]!!
        }
    }

    override fun render(graphics: Graphics) {
        super.render(graphics)
        graphics.color = Color.DARK_KHAKI
        graphics.fillRect(0f, 0f, getWidth().toFloat(), getHeight().toFloat())

        for(bar in bars) {
            glPushMatrix()
            this.rotateCenterd(bar!!.height, getWidth()/2f,getHeight()/2f,1f)
            bar.render(graphics)
            this.rotateCenterd(-bar.height, -getWidth()/2f,-getHeight()/2f,1f)
            glPopMatrix()
        }
        handleBars()
    }

    fun handleBars(){
        for (bar in bars) {
            if (bar?.height!! > bar.max) {
                bar.height = 0f
                bar.speed = Random.nextDouble(1.0, 10.0).toFloat()
                bar.color = barColors[nextInt(0xFFFFFF)]!!
            } else {
                bar.height += bar.speed
            }
        }
    }

    fun colorOut(n : Int) : Color {
        Random(n)
        return Color(nextInt(256), nextInt(256), nextInt(256))
    }

    fun rotateCenterd(amount: Float, x : Float, y : Float, z : Float){
        glTranslatef(x, y, 0f)
        glRotatef(amount, 0f, 0f, z)
    }


    override fun dispose() {
        super.dispose()
    }
}