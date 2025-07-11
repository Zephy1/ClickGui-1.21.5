package dev.debuggings.clickgui.listeners

import dev.debuggings.clickgui.Colors
import dev.debuggings.clickgui.PrintableKeys
import dev.debuggings.clickgui.elements.Element
import dev.debuggings.clickgui.elements.SubSection
import gg.essential.elementa.components.UIText
import gg.essential.elementa.constraints.CenterConstraint
import gg.essential.elementa.dsl.constrain
import gg.essential.elementa.dsl.pixel
import gg.essential.elementa.dsl.toConstraint
import gg.essential.elementa.events.UIClickEvent

open class KeyListener<T>(
    name: String,
    defaultValue: T,
    val allowBinding: Boolean,
    override var description: String? = null
) : Element<T>(name, defaultValue, description) {
    private var keyInputMode: Boolean = false

    var boundKey: Int = PrintableKeys.NONE.code
    var keyPressed: Boolean = false

    val boundKeyText: UIText = UIText("NONE").constrain {
        x = 5.pixel(true)
        y = CenterConstraint()
        textScale = 0.5.pixel()
        color = Colors.OPTION_TEXT.toConstraint()
    }

    fun listen(event: UIClickEvent) {
        if (event.mouseButton == 0) {
            keyInputMode = true
            boundKeyText.setText("Waiting...")
            return
        } else if (event.mouseButton == 1) {
            boundKey = PrintableKeys.NONE.code
            boundKeyText.setText(PrintableKeys.getKeyName(boundKey))
            saveKeybind()
        }
    }

    fun saveKeybind() {
        if (allowBinding) {
            clickGui!!.config.set<Int>(
                if (this is SubSection) {
                    "_keys_.$savePath._key_"
                } else {
                    "_keys_.$savePath"
                },
                boundKey
            )
            clickGui!!.config.save()
        }
    }

    fun captureKeyPress() {
        clickGui?.window?.onKeyType { _, keyCode ->
            if (!keyInputMode) return@onKeyType
            if (keyCode == PrintableKeys.LSHIFT.code) return@onKeyType
            if (keyCode != PrintableKeys.ESCAPE.code) {
                boundKey = keyCode
                saveKeybind()
            }
            keyInputMode = false
            boundKeyText.setText(PrintableKeys.getKeyName(boundKey))
        }
    }
}
