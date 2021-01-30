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
        String[] dateElements = date.split("-");
        date = dateElements[2] + "." + dateElements[1]  + "." + dateElements[0];

        try {
            out.print(date);
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

}
