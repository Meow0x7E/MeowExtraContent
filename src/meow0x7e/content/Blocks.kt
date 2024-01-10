package meow0x7e.content

import arc.Events
import arc.math.Mathf
import mindustry.game.EventType.UnlockEvent
import mindustry.type.Category
import mindustry.type.ItemStack.with
import mindustry.world.Block
import mindustry.world.blocks.production.Drill
import mindustry.world.meta.Env
import kotlin.math.pow
import mindustry.content.Blocks as MindustryBlocks
import mindustry.content.Items as MindustryItems
import mindustry.content.Liquids as MindustryLiquids

class Blocks {
    companion object {
        lateinit var mechanicalDrillSmall: Block
        lateinit var mechanicalDrillLarge: Block
        lateinit var mechanicalDrillExtraLarge: Block

        lateinit var pneumaticDrillSmall: Block
        lateinit var pneumaticDrillLarge: Block
        lateinit var pneumaticDrillExtraLarge: Block

        fun load() {
            (fun(name: String, size: Int): Block {
                val scalingFactor = size.toFloat().pow(2) / MindustryBlocks.mechanicalDrill.size.toFloat().pow(2)
                return Drill(name).apply {
                    requirements(Category.production, with(MindustryItems.copper, Mathf.round(12f * scalingFactor)))
                    tier = 2
                    drillTime = 600f
                    this.size = size
                    //mechanical drill doesn't work in space
                    envEnabled = envEnabled xor Env.space

                    consumeLiquid(MindustryLiquids.water, 0.05f * scalingFactor).boost()

                    Events.on(UnlockEvent::class.java) {
                        if (it.content == MindustryBlocks.mechanicalDrill) {
                            unlock()
                        }
                    }
                }
            }).let {
                mechanicalDrillSmall = it("mechanical-drill-small", 1)
                mechanicalDrillLarge = it("mechanical-drill-large", 3)
                mechanicalDrillExtraLarge = it("mechanical-drill-extra-large", 4)
            }

            (fun(name: String, size: Int): Block {
                val scalingFactor = size.toFloat().pow(2) / MindustryBlocks.pneumaticDrill.size.toFloat().pow(2)
                return Drill(name).apply {
                    requirements(
                        Category.production,
                        with(MindustryItems.copper, Mathf.round(18f * scalingFactor), MindustryItems.graphite, Mathf.round(10f * scalingFactor))
                    )
                    tier = 3
                    drillTime = 400f
                    this.size = size

                    consumeLiquid(MindustryLiquids.water, 0.06f * scalingFactor).boost()

                    Events.on(UnlockEvent::class.java) {
                        if (it.content == MindustryBlocks.pneumaticDrill) {
                            unlock()
                        }
                    }
                }
            }).let {
                pneumaticDrillSmall = it("pneumatic-drill-small", 2)
                pneumaticDrillLarge = it("pneumatic-drill-large", 3)
                pneumaticDrillExtraLarge = it("pneumatic-drill-extra-large", 4)
            }
        }
    }
}