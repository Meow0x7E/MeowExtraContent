package meow0x7e

import arc.*
import arc.util.*
import mindustry.game.EventType.*
import mindustry.mod.*
import mindustry.ui.dialogs.*

class MeowExtraContent : Mod(){
    companion object {
        const val NAME = "meow-extra-content"
    }

    init{
        Log.info("喵喵喵")
/*
        //listen for game load event
        Events.on(ClientLoadEvent::class.java){
            //show dialog upon startup
            Time.runTask(10f){
                BaseDialog("frog").apply{
                    cont.apply{
                        add("behold").row()
                        //mod sprites are prefixed with the mod name (this mod is called 'example-kotlin-mod' in its config)
                        image(Core.atlas.find("${NAME}-frog")).pad(20f).row()
                        button("I see"){ hide() }.size(100f, 50f)
                    }
                    show()
                }
            }
        }
*/
    }

    override fun loadContent(){
        meow0x7e.content.Blocks.load()
        meow0x7e.content.TechTree.load()
    }
}
