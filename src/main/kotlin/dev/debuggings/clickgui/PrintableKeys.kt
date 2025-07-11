package dev.debuggings.clickgui

import gg.essential.universal.UKeyboard

// This exists because in 1.21.5 using UKeyboard.getKeyName() errors when not run in the main thread.
enum class PrintableKeys(val code: Int) {
    NONE(UKeyboard.KEY_NONE),
    ESCAPE(UKeyboard.KEY_ESCAPE),
    LMETA(UKeyboard.KEY_LMETA),
    RMETA(UKeyboard.KEY_RMETA),
    LCONTROL(UKeyboard.KEY_LCONTROL),
    RCONTROL(UKeyboard.KEY_RCONTROL),
    LSHIFT(UKeyboard.KEY_LSHIFT),
    RSHIFT(UKeyboard.KEY_RSHIFT),
    LMENU(UKeyboard.KEY_LMENU),
    RMENU(UKeyboard.KEY_RMENU),
    MINUS(UKeyboard.KEY_MINUS),
    EQUALS(UKeyboard.KEY_EQUALS),
    BACKSPACE(UKeyboard.KEY_BACKSPACE),
    ENTER(UKeyboard.KEY_ENTER),
    TAB(UKeyboard.KEY_TAB),
    LEFT_BRACKET(UKeyboard.KEY_LBRACKET),
    RIGHT_BRACKET(UKeyboard.KEY_RBRACKET),
    SEMICOLON(UKeyboard.KEY_SEMICOLON),
    APOSTROPHE(UKeyboard.KEY_APOSTROPHE),
    GRAVE(UKeyboard.KEY_GRAVE),
    BACKSLASH(UKeyboard.KEY_BACKSLASH),
    COMMA(UKeyboard.KEY_COMMA),
    PERIOD(UKeyboard.KEY_PERIOD),
    SLASH(UKeyboard.KEY_SLASH),
    MULTIPLY(UKeyboard.KEY_MULTIPLY),
    SPACE(UKeyboard.KEY_SPACE),
    CAPITAL(UKeyboard.KEY_CAPITAL),
    LEFT(UKeyboard.KEY_LEFT),
    UP(UKeyboard.KEY_UP),
    RIGHT(UKeyboard.KEY_RIGHT),
    DOWN(UKeyboard.KEY_DOWN),
    NUMLOCK(UKeyboard.KEY_NUMLOCK),
    SCROLL(UKeyboard.KEY_SCROLL),
    SUBTRACT(UKeyboard.KEY_SUBTRACT),
    ADD(UKeyboard.KEY_ADD),
    DIVIDE(UKeyboard.KEY_DIVIDE),
    DECIMAL(UKeyboard.KEY_DECIMAL),

    NUMPAD0(UKeyboard.KEY_NUMPAD0),
    NUMPAD1(UKeyboard.KEY_NUMPAD1),
    NUMPAD2(UKeyboard.KEY_NUMPAD2),
    NUMPAD3(UKeyboard.KEY_NUMPAD3),
    NUMPAD4(UKeyboard.KEY_NUMPAD4),
    NUMPAD5(UKeyboard.KEY_NUMPAD5),
    NUMPAD6(UKeyboard.KEY_NUMPAD6),
    NUMPAD7(UKeyboard.KEY_NUMPAD7),
    NUMPAD8(UKeyboard.KEY_NUMPAD8),
    NUMPAD9(UKeyboard.KEY_NUMPAD9),

    A(UKeyboard.KEY_A),
    B(UKeyboard.KEY_B),
    C(UKeyboard.KEY_C),
    D(UKeyboard.KEY_D),
    E(UKeyboard.KEY_E),
    F(UKeyboard.KEY_F),
    G(UKeyboard.KEY_G),
    H(UKeyboard.KEY_H),
    I(UKeyboard.KEY_I),
    J(UKeyboard.KEY_J),
    K(UKeyboard.KEY_K),
    L(UKeyboard.KEY_L),
    M(UKeyboard.KEY_M),
    N(UKeyboard.KEY_N),
    O(UKeyboard.KEY_O),
    P(UKeyboard.KEY_P),
    Q(UKeyboard.KEY_Q),
    R(UKeyboard.KEY_R),
    S(UKeyboard.KEY_S),
    T(UKeyboard.KEY_T),
    U(UKeyboard.KEY_U),
    V(UKeyboard.KEY_V),
    W(UKeyboard.KEY_W),
    X(UKeyboard.KEY_X),
    Y(UKeyboard.KEY_Y),
    Z(UKeyboard.KEY_Z),

    KEY_0(UKeyboard.KEY_0),
    KEY_1(UKeyboard.KEY_1),
    KEY_2(UKeyboard.KEY_2),
    KEY_3(UKeyboard.KEY_3),
    KEY_4(UKeyboard.KEY_4),
    KEY_5(UKeyboard.KEY_5),
    KEY_6(UKeyboard.KEY_6),
    KEY_7(UKeyboard.KEY_7),
    KEY_8(UKeyboard.KEY_8),
    KEY_9(UKeyboard.KEY_9),

    F1(UKeyboard.KEY_F1),
    F2(UKeyboard.KEY_F2),
    F3(UKeyboard.KEY_F3),
    F4(UKeyboard.KEY_F4),
    F5(UKeyboard.KEY_F5),
    F6(UKeyboard.KEY_F6),
    F7(UKeyboard.KEY_F7),
    F8(UKeyboard.KEY_F8),
    F9(UKeyboard.KEY_F9),
    F10(UKeyboard.KEY_F10),
    F11(UKeyboard.KEY_F11),
    F12(UKeyboard.KEY_F12),
    F13(UKeyboard.KEY_F13),
    F14(UKeyboard.KEY_F14),
    F15(UKeyboard.KEY_F15),
    F16(UKeyboard.KEY_F16),
    F17(UKeyboard.KEY_F17),
    F18(UKeyboard.KEY_F18),
    F19(UKeyboard.KEY_F19),

    DELETE(UKeyboard.KEY_DELETE),
    HOME(UKeyboard.KEY_HOME),
    END(UKeyboard.KEY_END);

    companion object {
        private val codeToName = PrintableKeys.entries.associate { it.code to it.name }

        @JvmStatic
        operator fun invoke(code: Int): String? = codeToName[code]

        @JvmStatic
        fun getKeyName(code: Int): String {
            //#if MC<12105
            //$$ return UKeyboard.getKeyName(code) ?: "UNKNOWN"
            //#else
            return codeToName[code] ?: "UNKNOWN"
            //#endif
        }
    }
}
