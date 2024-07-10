package com.birariro.highlight.setting;

import com.birariro.highlight.KeywordColor;
import com.birariro.highlight.support.Colors;

import javax.swing.*;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class SettingsComponent {
    private JTable excludeTable;
    private JButton addButton;
    private JButton removeButton;
    private JPanel mainPanel;

    private final Colors colors = new Colors();
    private final AppSettings settings = AppSettings.getInstance();
    private DefaultTableModel tableModel;
    private String[][] contents;

    public SettingsComponent() {
        initializeContents();
        initializeUIComponents();
        changeContentsListener();
        addRowButtonListener();
        removeRowButtonListener();
    }

    private void initializeContents() {
        List<KeywordColor> keywordColorList = settings.getKeywordColors();
        setContents(keywordColorList);
    }

    public List<KeywordColor> getContents() {
        List<KeywordColor> keywordColors = new ArrayList<>();
        for (String[] content : contents) {
            keywordColors.add(new KeywordColor(content[0], content[1]));
        }
        return keywordColors;
    }

    public void setContents(List<KeywordColor> keywordColors) {
        contents = new String[keywordColors.size()][2];
        for (int i = 0; i < keywordColors.size(); i++) {
            contents[i][0] = keywordColors.get(i).getKeyword();
            contents[i][1] = keywordColors.get(i).getColor();
        }
    }

    private void initializeUIComponents() {
        String[] columnNames = {"Keyword", "Color"};
        tableModel = new DefaultTableModel(contents, columnNames);
        excludeTable.setModel(tableModel);

        excludeTable.getTableHeader().getColumnModel().getColumn(1).setMaxWidth(100);
        TableColumn colorColumn = excludeTable.getColumnModel().getColumn(1);
        colorColumn.setCellRenderer(new ColorButtonRenderer());
        colorColumn.setCellEditor(new ColorButtonEditor(excludeTable));
    }

    private void changeContentsListener() {
        tableModel.addTableModelListener(e -> {
            int row = e.getFirstRow();
            int column = e.getColumn();
            if (column == 0 || column == 1) {
                String newValue = (String) tableModel.getValueAt(row, column);
                System.out.println("Value changed at row " + row + ", column " + column + ": " + newValue);
                contents[row][column] = newValue;
            }
        });
    }

    private void addRowButtonListener() {
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<KeywordColor> contents = getContents();
                contents.add(new KeywordColor("keyword", "#FFFFFF"));
                setContents(contents);
                tableModel.addRow(new Object[]{"keyword", "#FFFFFF"});
            }
        });
    }

    private void removeRowButtonListener() {
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = excludeTable.getSelectedRow();
                if (selectedRow != -1) {
                    List<KeywordColor> contents = getContents();
                    contents.remove(selectedRow);
                    setContents(contents);
                    tableModel.removeRow(selectedRow);
                }
            }
        });
    }

    public JPanel getPanel() {
        return mainPanel;
    }

    private class ColorButtonRenderer extends JButton implements TableCellRenderer {
        public ColorButtonRenderer() {
            setOpaque(true);
            setBorderPainted(false);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            if (value != null) {
                setIcon(DEFAULT_ICON);
                setBackground(Color.decode((String) value));
                setForeground(Color.decode((String) value));
                setText("");
            }
            return this;
        }
    }

    private class ColorButtonEditor extends AbstractCellEditor implements TableCellEditor {
        private final JButton button;
        private String currentColor;
        private final JTable table;
        public ColorButtonEditor(JTable table) {
            this.table = table;
            button = new JButton();
            button.setIcon(DEFAULT_ICON);
            button.setOpaque(true);
            button.setBorderPainted(false);
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Color initialColor = Color.decode(currentColor);
                    Color newColor = JColorChooser.showDialog(button, "Choose Color", initialColor);
                    if (newColor != null) {
                        currentColor = colors.colorToString(newColor);
                        button.setBackground(newColor);
                        button.setForeground(newColor);
                        updateTableRowColor();
                    }
                }
            });
        }
        private void updateTableRowColor() {
            int row = table.getEditingRow();
            int column = table.getEditingColumn();
            tableModel.setValueAt(currentColor, row, column);
        }

        @Override
        public Object getCellEditorValue() {
            return currentColor;
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            if (value != null) {
                currentColor = (String) value;
                button.setBackground(Color.decode(currentColor));
                button.setForeground(Color.decode(currentColor));
            }
            return button;
        }
    }

    public static final Icon DEFAULT_ICON = new Icon() {
        @Override
        public void paintIcon(Component c, Graphics g, int x, int y) {
            g.setColor(c.getBackground());
            g.fillRect(x, y, getIconWidth(), getIconHeight());
        }

        @Override
        public int getIconWidth() {
            return 100;
        }

        @Override
        public int getIconHeight() {
            return 13;
        }
    };
}
