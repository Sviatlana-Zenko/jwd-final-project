package com.epam.jwd.final_project.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

public class NumberSplitterTag extends TagSupport {

    private String amount;

    @Override
    public int doStartTag() throws JspException {
        JspWriter out = pageContext.getOut();

        try {
            out.print(splitString());
            out.flush();
        } catch (IOException e) {
            throw new JspException(e);
        }

        return SKIP_BODY;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    private String splitString() {
        StringBuilder builder = new StringBuilder(amount);
        int length = amount.length();
        int index = length % 3 == 0 ? 3 : length % 3;

        while (index < length) {
            builder.insert(index, " ");
            index += 4;
        }

        return builder.toString();
    }

}
