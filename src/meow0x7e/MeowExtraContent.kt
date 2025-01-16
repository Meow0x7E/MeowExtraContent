package meow0x7e

import arc.util.Log
import meow0x7e.content.Blocks
import meow0x7e.content.TechTree
import mindustry.mod.Mod

class MeowExtraContent : Mod() {
    init {
        //Log.level = Log.LogLevel.debug
        Log.info("喵喵喵")
    }

    override fun loadContent() {
        Log.debug("加载 Blocks...")
        Blocks.load()
        Log.debug("加载 TechTree...")
        TechTree.load()
    }
}
