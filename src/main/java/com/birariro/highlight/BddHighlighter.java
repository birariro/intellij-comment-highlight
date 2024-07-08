package com.birariro.highlight;

import com.birariro.highlight.setting.AppSettings;
import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.lang.annotation.Annotator;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.editor.markup.TextAttributes;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiComment;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

public class BddHighlighter implements Annotator {
    private static final TextAttributesKey BDD_KEYWORD =
            TextAttributesKey.createTextAttributesKey("BDD.KEYWORD");

    private static final String [] KEYWORDS = {"given","when","then"};

    @Override
    public void annotate(@NotNull PsiElement element, @NotNull AnnotationHolder holder) {
        AppSettings settings = AppSettings.getInstance();

        TextAttributes textAttributes = new TextAttributes();
        textAttributes.setForegroundColor(Color.WHITE);
        textAttributes.setBackgroundColor(settings.getBddColor());
        textAttributes.setFontType(Font.BOLD);
        com.intellij.openapi.editor.colors.EditorColorsManager.getInstance().getGlobalScheme().setAttributes(BDD_KEYWORD, textAttributes);

        if (element instanceof PsiComment) {
            String text = element.getText();
            for (String keyword : KEYWORDS) {
                if (text.contains(keyword)) {
                    TextRange range = element.getTextRange();
                    holder.newSilentAnnotation(com.intellij.lang.annotation.HighlightSeverity.INFORMATION)
                            .range(range)
                            .textAttributes(BDD_KEYWORD)
                            .create();

                }
            }
        }
    }
}