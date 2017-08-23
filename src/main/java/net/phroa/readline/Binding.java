package net.phroa.readline;

import net.minecraft.client.gui.GuiTextField;
import net.phroa.readline.func.Case;
import net.phroa.readline.func.Transpose;
import org.lwjgl.input.Keyboard;

import java.util.function.Consumer;

public enum Binding {
    BEGINNING_OF_LINE("beginning-of-line", Keyboard.KEY_A, true, false, GuiTextField::setCursorPositionZero),
    END_OF_LINE("end-of-line", Keyboard.KEY_E, true, false, GuiTextField::setCursorPositionEnd),

    BACKWARD_CHAR("backward-char", Keyboard.KEY_B, true, false, field -> field.moveCursorBy(-1)),
    FORWARD_CHAR("forward-char", Keyboard.KEY_F, true, false, field -> field.moveCursorBy(1)),

    BACKWARD_WORD("backward-word", Keyboard.KEY_B, false, true, field -> field.setCursorPosition(field.getNthWordFromCursor(-1))),
    FORWARD_WORD("forward-word", Keyboard.KEY_F, false, true, field -> field.setCursorPosition(field.getNthWordFromCursor(1))),

    BACKWARD_DELETE_CHAR("backward-delete-char", Keyboard.KEY_H, true, false, field -> field.deleteFromCursor(-1)),

    BACKWARD_DELETE_WORD("backward-delete-word", Keyboard.KEY_W, true, false, field -> field.deleteWords(-1)),

    BACKWARD_DELETE_LINE("backward-delete-line", Keyboard.KEY_U, true, false, field -> field.deleteFromCursor(-field.getCursorPosition())),
    FORWARD_DELETE_LINE("forward-delete-line", Keyboard.KEY_K, true, false, field -> field.deleteFromCursor(field.getMaxStringLength() - field.getCursorPosition())),

    TRANSPOSE_CHARS("transpose-chars", Keyboard.KEY_T, true, false, Transpose.CHARS),
    TRANSPOSE_WORDS("transpose-words", Keyboard.KEY_T, false, true, Transpose.WORDS),

    CAPITALIZE_WORD("capitalize-word", Keyboard.KEY_C, false, true, Case.CAPITALIZE),
    UPCASE_WORD("upcase-word", Keyboard.KEY_U, false, true, Case.UPCASE),
    DOWNCASE_WORD("downcase-word", Keyboard.KEY_L, false, true, Case.DOWNCASE);

    public final String name;
    public final int key;
    public final boolean control;
    public final boolean meta;
    public final Consumer<GuiTextField> action;

    Binding(String name, int key, boolean control, boolean meta, Consumer<GuiTextField> action) {
        this.name = name;
        this.key = key;
        this.control = control;
        this.meta = meta;
        this.action = action;
    }
}
