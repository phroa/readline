package net.phroa.readline.func;

import net.minecraft.client.gui.GuiTextField;

import java.util.function.Consumer;

public final class Transpose {
    public static final Consumer<GuiTextField> CHARS = field -> {
        int pos = field.getCursorPosition();
        String text = field.getText();

        if (pos < 1)
            return;
        if (pos >= text.length())
            pos = text.length() - 1;

        char[] chars = text.toCharArray();
        char tmp = chars[pos];
        chars[pos] = chars[pos - 1];
        chars[pos - 1] = tmp;
        field.setText(new String(chars));
        field.setCursorPosition(pos + 1);
    };

    public static final Consumer<GuiTextField> WORDS = field -> {
        int pos = field.getCursorPosition();
        String text = field.getText();
        int firstSpace = text.indexOf(' ');
        String[] words = text.split("\\s+"); // yes, this'll eat duplicated spaces anywhere...

        if (words.length < 2 || pos < firstSpace)
            return;

        int wordUnderCursor = -1;
        int chars = 0;
        for (String word : words) {
            wordUnderCursor++;
            chars += word.length() + 1;
            if (pos < chars)
                break;
        }

        if (wordUnderCursor < 1)
            return;

        String tmp = words[wordUnderCursor];
        words[wordUnderCursor] = words[wordUnderCursor - 1];
        words[wordUnderCursor - 1] = tmp;

        field.setText(String.join(" ", words));
        field.setCursorPosition(text.indexOf(' ', pos) + 1);
    };
}
