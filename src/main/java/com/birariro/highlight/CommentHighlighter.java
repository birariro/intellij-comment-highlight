package com.birariro.highlight;

import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.lang.annotation.Annotator;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.editor.markup.TextAttributes;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiComment;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;

import java.awt.Font;

public class CommentHighlighter implements Annotator {
    private static final TextAttributesKey RED_COMMENT_KEYWORD =
            TextAttributesKey.createTextAttributesKey("RED_COMMENT_KEYWORD");
    private static final TextAttributesKey BLUE_COMMENT_KEYWORD =
            TextAttributesKey.createTextAttributesKey("BLUE_COMMENT_KEYWORD");
    private static final String RED_KEYWORD = "!";
    private static final String BLUE_KEYWORD = "?";


    static {
        TextAttributes redTextAttributes = new TextAttributes();
        redTextAttributes.setForegroundColor(Colors.RED);
        redTextAttributes.setFontType(Font.ITALIC);
        com.intellij.openapi.editor.colors.EditorColorsManager.getInstance().getGlobalScheme().setAttributes(RED_COMMENT_KEYWORD, redTextAttributes);

        TextAttributes blueTextAttributes = new TextAttributes();
        blueTextAttributes.setForegroundColor(Colors.BLUE);
        blueTextAttributes.setFontType(Font.ITALIC);
        com.intellij.openapi.editor.colors.EditorColorsManager.getInstance().getGlobalScheme().setAttributes(BLUE_COMMENT_KEYWORD, blueTextAttributes);
    }

    @Override
    public void annotate(@NotNull PsiElement element, @NotNull AnnotationHolder holder) {
        if (element instanceof PsiComment) {
            String text = element.getText();

            if (text.contains(RED_KEYWORD)) {
                TextRange range = element.getTextRange();
                holder.newSilentAnnotation(com.intellij.lang.annotation.HighlightSeverity.INFORMATION)
                        .range(range)
                        .textAttributes(RED_COMMENT_KEYWORD)
                        .create();
            } else if (text.contains(BLUE_KEYWORD)) {
                TextRange range = element.getTextRange();
                holder.newSilentAnnotation(com.intellij.lang.annotation.HighlightSeverity.INFORMATION)
                        .range(range)
                        .textAttributes(BLUE_COMMENT_KEYWORD)
                        .create();
            }
        }
    }
}