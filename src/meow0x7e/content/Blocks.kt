package meow0x7e.content

import arc.Events
import arc.math.Mathf
import arc.util.Log
import meow0x7e.content.TechTree.Companion.syncUnlocks
import mindustry.game.EventType
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
            { name: String, size: Int ->
                val scalingFactor = size.toFloat().pow(2) / MindustryBlocks.mechanicalDrill.size.toFloat().pow(2)
                Drill(name).apply {
                    requirements(
                        Category.production,
                        with(MindustryItems.copper, Mathf.round(12f * scalingFactor))
                    )
                    tier = 2
                    drillTime = 600f
                    this.size = size
                    //mechanical drill doesn't work in space
                    envEnabled = envEnabled xor Env.space

                    consumeLiquid(MindustryLiquids.water, 0.05f * scalingFactor).boost()
                }
            }.let {
                Log.debug("注册方块 mechanical-drill-small")
                mechanicalDrillSmall = it("mechanical-drill-small", 1)
                Log.debug("注册方块 mechanical-drill-large")
                mechanicalDrillLarge = it("mechanical-drill-large", 3)
                Log.debug("注册方块 mechanical-drill-extra-large")
                mechanicalDrillExtraLarge = it("mechanical-drill-extra-large", 4)
            }

            syncUnlocks(
                MindustryBlocks.mechanicalDrill,
                mechanicalDrillSmall,
                mechanicalDrillLarge,
                mechanicalDrillExtraLarge
            );

            { name: String, size: Int ->
                val scalingFactor =
                    size.toFloat().pow(2) / MindustryBlocks.pneumaticDrill.size.toFloat().pow(2)
                Drill(name).apply {
                    requirements(
                        Category.production,
                        with(
                            MindustryItems.copper,
                            Mathf.round(18f * scalingFactor),
                            MindustryItems.graphite,
                            Mathf.round(10f * scalingFactor)
                        )
                    )
                    tier = 3
                    drillTime = 400f
                    this.size = size

                    consumeLiquid(MindustryLiquids.water, 0.06f * scalingFactor).boost()

                    Events.on(EventType.UnlockEvent::class.java) {
                        if (it.content == MindustryBlocks.pneumaticDrill) {
                            unlock()
                        }
                    }
                }
            }.let {
                Log.debug("注册方块 pneumatic-drill-small")
                pneumaticDrillSmall = it("pneumatic-drill-small", 2)
                Log.debug("注册方块 pneumatic-drill-large")
                pneumaticDrillLarge = it("pneumatic-drill-large", 3)
                Log.debug("注册方块 pneumatic-drill-extra-large")
                pneumaticDrillExtraLarge = it("pneumatic-drill-extra-large", 4)
            }

            syncUnlocks(
                MindustryBlocks.pneumaticDrill,
                pneumaticDrillSmall,
                pneumaticDrillLarge,
                pneumaticDrillExtraLarge
            )
        }
    }
}