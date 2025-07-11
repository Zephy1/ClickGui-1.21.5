package dev.debuggings.clickgui.elements

import dev.debuggings.clickgui.Colors
import gg.essential.elementa.components.UIText
import gg.essential.elementa.constraints.CenterConstraint
import gg.essential.elementa.dsl.childOf
import gg.essential.elementa.dsl.constrain
import gg.essential.elementa.dsl.minus
import gg.essential.elementa.dsl.pixel
import gg.essential.elementa.dsl.plus
import gg.essential.elementa.dsl.toConstraint

class SelectElement @JvmOverloads constructor(
    name: String,
    private val defaultValue: String,
    private val options: ArrayList<String>,
    override var description: String? = null,
) : Element<String>(name, defaultValue, description) {
    private var selectedOption: Int = 0

    override fun loadValue() {
        value = clickGui!!.config.get<String>(savePath) ?: defaultValue
        selectedOption = options.indexOf(defaultValue)
        valueText.setText(value)
    }

    override fun saveValue() {
        clickGui!!.config.set<String>(savePath, value)
        clickGui!!.config.save()
    }

    override var nameText: UIText? = UIText(name).constrain {
        x = 5.pixel()
        y = CenterConstraint()
        textScale = 0.5.pixel()
        color = Colors.OPTION_TEXT.toConstraint()
    } childOf this

    private val valueText = UIText(value).constrain {
        x = 5.pixel(true)
        y = CenterConstraint()
        textScale = 0.5.pixel()
        color = Colors.ON.toConstraint()
    } childOf this

    private val leftIconText = UIText("◀").constrain {
        x = 5.pixel()
        y = CenterConstraint()
        color = Colors.OPTION_TEXT.toConstraint()
    } childOf this

    private val rightIconText = UIText("▶").constrain {
        x = 5.pixel(true)
        y = CenterConstraint()
        color = Colors.OPTION_TEXT.toConstraint()
    } childOf this

    override fun init() {
        loadValue()

        leftIconText.hide()
        rightIconText.hide()

        onMouseEnter {
            leftIconText.unhide()
            rightIconText.unhide()

            nameText?.setX(CenterConstraint())
            nameText?.setY(CenterConstraint() - 4.pixel())
            valueText.setX(CenterConstraint())
            valueText.setY(CenterConstraint() + 4.pixel())
        }

        onMouseLeave {
            leftIconText.hide()
            rightIconText.hide()

            nameText?.setX(5.pixel())
            nameText?.setY(CenterConstraint())
            valueText.setX(5.pixel(true))
            valueText.setY(CenterConstraint())
        }

        leftIconText.onMouseClick {
            selectedOption--
            cycleText()
        }

        rightIconText.onMouseClick {
            selectedOption++
            cycleText()
        }
    }

    private fun cycleText() {
        if (selectedOption < 0) {
            selectedOption = options.size - 1
        }
        selectedOption %= options.size
        value = options[selectedOption]
        valueText.setText(value)
        saveValue()
    }
}
