package com.epam.kinorating.tag;

import org.apache.log4j.Logger;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.SkipPageException;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.text.DecimalFormat;

public class NumberFormatterTag extends SimpleTagSupport {
    private static final Logger log = Logger.getLogger(NumberFormatterTag.class);

    private String format;
    private String number;

    public void setFormat(String format) {
        this.format = format;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public  void doTag() throws JspException, IOException {
        try {
            double amount = Double.parseDouble(number);
            DecimalFormat formatter = new DecimalFormat(format);

            String formattedNumber = formatter.format(amount);

            getJspContext().getOut().write(formattedNumber);
        } catch (Exception e) {
            log.error("Got a problem with the custom tag", e);
            throw new SkipPageException("Exception in formatting " + number + " with format " + format);
        }
    }
}
