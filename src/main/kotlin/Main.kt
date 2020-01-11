import bvanseg.openawt.OpenAWT
import bvanseg.openawt.window.WindowManager
import windows.VideoWindow


fun main(){
    OpenAWT.initialize()
    val window = WindowManager.createWindow<VideoWindow>("Video of Music", 1280, 720)
    window.frameRate = 120
    window.start()
}