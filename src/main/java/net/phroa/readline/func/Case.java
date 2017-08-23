package net.phroa.readline.func;

import net.minecraft.client.gui.GuiTextField;

import java.util.function.Consumer;

public final class Case {

    public static final Consumer<GuiTextField> CAPITALIZE = field -> {
        int pos = field.getCursorPosition();
        String text = field.getText();
        if (text.length() < 1)
            return;
        if (text.length() == 1) {
            field.setText(text.toUpperCase());
            return;
        }

        int start = text.lastIndexOf(' ', pos) + 1;
        int end = text.indexOf(' ', pos);
        if (start == end)
            return;
        if (start < 0)
            start = 0;
        if (end >= text.length() || end < 0) {
            end = text.length() - 1;
        }

        char[] word = text.substring(start, end).toLowerCase().toCharArray();
        word[0] = ("" + text.charAt(start)).toUpperCase().charAt(0);

        char[] out = text.toCharArray();
        System.arraycopy(word, 0, out, start, word.length);
        field.setText(new String(out));
        field.setCursorPosition(end + 1);
    };

    public static final Consumer<GuiTextField> UPCASE = field -> {
        int pos = field.getCursorPosition();
        String text = field.getText();
        if (text.length() < 1)
            return;

        int start = Math.max(0, text.lastIndexOf(' ', pos));
        int i = text.indexOf(' ', pos);
        int end = Math.min(text.length(), i < 1 ? Integer.MAX_VALUE : i);
        char[] word = text.substring(start, end).toUpperCase().toCharArray();

        char[] out = text.toCharArray();
        System.arraycopy(word, 0, out, start, word.length);
        field.setText(new String(out));
        field.setCursorPosition(end);
    };

    public static final Consumer<GuiTextField> DOWNCASE = field -> {
        int pos = field.getCursorPosition();
        String text = field.getText();
        if (text.length() < 1)
            return;

        int start = Math.max(0, text.lastIndexOf(' ', pos));
        int i = text.indexOf(' ', pos);
        int end = Math.min(text.length(), i < 1 ? Integer.MAX_VALUE : i);
        char[] word = text.substring(start, end).toLowerCase().toCharArray();

        char[] out = text.toCharArray();
        System.arraycopy(word, 0, out, start, word.length);
        field.setText(new String(out));
        field.setCursorPosition(end);
    };
}
