package com.birariro.highlight;

import com.birariro.highlight.setting.AppSettings;
import com.birariro.highlight.support.Colors;
import com.birariro.highlight.support.KeywordMatch;
import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.lang.annotation.Annotator;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.editor.markup.TextAttributes;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiComment;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;

import java.awt.Font;
import java.util.List;

public class CommentHighlighter implements Annotator {

    private static KeywordMatch keywordMatch = new KeywordMatch();
    private static Colors colors = new Colors();

    @Override
    public void annotate(@NotNull PsiElement element, @NotNull AnnotationHolder holder) {

        AppSettings settings = AppSettings.getInstance();
        List<KeywordColor> keywordColors = settings.getKeywordColors();


        if (element instanceof PsiComment) {
            String text = element.getText();
            for (KeywordColor keywordColor : keywordColors) {
                if (keywordMatch.matches(text, keywordColor.getKeyword())) {

                    TextAttributesKey COMMENT_KEYWORD =
                            TextAttributesKey.createTextAttributesKey(keywordColor.getKeyword());

                    TextAttributes redTextAttributes = new TextAttributes();
                    redTextAttributes.setForegroundColor(colors.stringToColor(keywordColor.getColor()));
                    redTextAttributes.setFontType(Font.ITALIC);
                    com.intellij.openapi.editor.colors.EditorColorsManager.getInstance().getGlobalScheme().setAttributes(COMMENT_KEYWORD, redTextAttributes);

                    TextRange range = element.getTextRange();

                    holder.newSilentAnnotation(com.intellij.lang.annotation.HighlightSeverity.INFORMATION)
                            .range(range)
                            .textAttributes(COMMENT_KEYWORD)
                            .create();

                    break;
                }
            }


        }
    }
}