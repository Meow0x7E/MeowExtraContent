package meow0x7e

import arc.Core
import arc.Events
import arc.util.Log
import arc.util.Time
import meow0x7e.content.Blocks
import meow0x7e.content.TechTree
import mindustry.game.EventType.ClientLoadEvent
import mindustry.mod.Mod
import mindustry.ui.dialogs.BaseDialog

class MeowExtraContent : Mod() {
    companion object {
        const val NAME = "meow-extra-content"
    }

    init {
        Log.info("喵喵喵")
        Events.on(ClientLoadEvent::class.java) {
            Time.runTask(10f, baseDialogLookMe()::show)
        }
    }

    fun baseDialogLookMe() = BaseDialog(Core.bundle.get("base-dialog.look-me.title")).apply BaseDialog@{
        cont.apply {
            button(Core.bundle.get("base-dialog.changelog.title")) {
                this@BaseDialog.hide()
                baseDialogChangeLog(this@BaseDialog::show).show()
            }.growX()
        }
        buttons.apply {
            button(Core.bundle.get("close"), this@BaseDialog::hide).fill(0.1f, 0f).growX()
        }
    }

    fun baseDialogChangeLog(backTo: () -> Unit): BaseDialog =
        BaseDialog(Core.bundle.get("base-dialog.changelog.title")).apply BaseDialog@{
            cont.apply {
                (fun(text: String) = add(text).left().row()).let {
                    it("v0.2.1")
                    it("添加 更新日志")
                    it("给那六个钻头添加了监听事件自动解锁，顺手用了一种更舒服的方式构造他们")
                    it("补充了那三个气动钻头的 bundle")
                    row()
                    it("v0.1 - v0.2")
                    it("添加 将方块插入到科技树的方法")
                    it("添加 气动钻头-小型，大小为1，其他参数和普通气动钻头一样")
                    it("添加 气动钻头-大型，大小为3，其他参数和普通气动钻头一样")
                    it("添加 气动钻头-超大型，大小为4，其他参数和普通气动钻头一样")
                    it("添加 机械钻头-小型，大小为1，其他参数和普通机械钻头一样")
                    it("添加 机械钻头-大型，大小为3，其他参数和普通机械钻头一样")
                    it("添加 机械钻头-超大型，大小为4，其他参数和普通机械钻头一样")
                }
            }
            buttons.apply {
                button(Core.bundle.get("back")) {
                    this@BaseDialog.hide()
                    backTo()
                }.growX()
            }
        }

    override fun loadContent() {
        Blocks.load()
        TechTree.load()
    }
}
