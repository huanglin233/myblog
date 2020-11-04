package com.hl.myblog.common.utils;

/**
 * 空格排版
 * 
 * @author huanglin
 * @date   2020年7月4日
 */
public class PrefixString {
	public PrefixString()
    {
    }

    public static final String NoIndent()
    {
        return Indent(null);
    }

    public static final String Indent(String s)
    {
        int i;
        if(s == null)
            i = m_prefix.length();
        else
            i = m_prefix.length() - s.length() - 4;
        if(i < 0)
            i = 0;
        return m_prefix.substring(i);
    }

    public static final String Indent1()
    {
        return Indent(NoIndent());
    }

    public static final String Indent2()
    {
        return Indent(Indent1());
    }

    private static String m_prefix = "                                                 ";
}