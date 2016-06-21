package com.client.utils;

import java.util.LinkedList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
 

/**
 * The Class TableBuilder.
 */
public class TableBuilder
{
    
    /** The rows. */
    List<String[]> rows = new LinkedList<String[]>();
 
    /**
     * Adds the row.
     *
     * @param cols the cols
     */
    public void addRow(String... cols)
    {
        rows.add(cols);
    }
 
    /**
     * Col widths.
     *
     * @return the int[]
     */
    private int[] colWidths()
    {
        int cols = -1;
 
        for(String[] row : rows)
            cols = Math.max(cols, row.length);
 
        int[] widths = new int[cols];
 
        for(String[] row : rows) {
            for(int colNum = 0; colNum < row.length; colNum++) {
                widths[colNum] =
                    Math.max(
                        widths[colNum],
                        StringUtils.length(row[colNum]));
            }
        }
 
        return widths;
    }
 
    /* 
     * ovveride of the method toString
     */
    @Override
    public String toString()
    {
        StringBuilder buf = new StringBuilder();
 
        int[] colWidths = colWidths();
 
        for(String[] row : rows) {
            for(int colNum = 0; colNum < row.length; colNum++) {
                buf.append(
                    StringUtils.rightPad(
                        StringUtils.defaultString(
                            row[colNum]), colWidths[colNum]));
                buf.append(' ');
            }
 
            buf.append('\n');
        }
 
        return buf.toString();
    }
 
}