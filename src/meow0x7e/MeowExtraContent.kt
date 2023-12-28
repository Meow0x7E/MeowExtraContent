package meow0x7e

import arc.*
import arc.util.*
import mindustry.game.EventType.*
import mindustry.mod.*
import mindustry.ui.dialogs.*

class MeowExtraContent : Mod(){

    init{
        //listen for game load event
/*
        Events.on(ClientLoadEvent::class.java){
            //show dialog upon startup
            Time.runTask(10f){
                BaseDialog("frog").apply{
                    cont.apply{
                        add("behold").row()
                        //mod sprites are prefixed with the mod name (this mod is called 'example-kotlin-mod' in its config)
                        image(Core.atlas.find("meow-extra-content-frog")).pad(20f).row()
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
