package meow0x7e.content

import arc.struct.Seq
import mindustry.content.Blocks as MindustryBlocks
import mindustry.content.Planets
import mindustry.content.TechTree.*
import mindustry.ctype.UnlockableContent

class TechTree {
    companion object {
        @JvmStatic
        fun Seq<TechNode>.findNode(content: UnlockableContent,vararg nodes: TechNode) {
            this.forEach {
                when {
                    it.content.name == content.name -> {
                        nodes.forEach(it.children::add)
                    }
                    it.children.any() -> {
                        it.children.findNode(content, *nodes)
                    }
                }
            }
        }

        fun load() {
            with(Planets.serpulo.techTree.children) {
                findNode(
                    MindustryBlocks.mechanicalDrill,
                    node(Blocks.mechanicalDrillSmall),
                    node(Blocks.mechanicalDrillLarge),
                    node(Blocks.mechanicalDrillExtraLarge)
                )
            }
        }
    }
}