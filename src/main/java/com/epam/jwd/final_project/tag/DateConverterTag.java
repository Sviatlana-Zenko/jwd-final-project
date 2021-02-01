package com.epam.jwd.final_project.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

public class DateConverterTag extends TagSupport {

    private String date;

    @Override
    public int doStartTag() throws JspException {
        JspWriter out = pageContext.getOut();

        try {
            out.print(formatString());
            out.flush();
        } catch (IOException e) {
            throw new JspException(e);
        }

        return SKIP_BODY;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    private String formatString(){
        String formatted;
        String[] dateElements = date.split("-");
        formatted = dateElements[2] + "." +
                dateElements[1]  + "." +
                dateElements[0];

        return formatted;
    }

}
