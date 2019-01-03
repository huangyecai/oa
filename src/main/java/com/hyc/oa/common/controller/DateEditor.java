package com.hyc.oa.common.controller;

import java.beans.PropertyEditorSupport;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.util.StringUtils;

public class DateEditor extends PropertyEditorSupport {

	private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
    private static final DateFormat DATE_FORMAT_NO_DD = new SimpleDateFormat("yyyy-MM");
    private static final DateFormat TIME_FORMAT_NO_SS = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    private static final DateFormat TIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    
    private DateFormat dateFormat;
    private boolean allowEmpty = true;
    
    public DateEditor() {
    }

    public DateEditor(DateFormat dateFormat) {
        this.dateFormat = dateFormat;
    }

    public DateEditor(DateFormat dateFormat, boolean allowEmpty) {
        this.dateFormat = dateFormat;
        this.allowEmpty = allowEmpty;
    }
    
	@Override
	public String getAsText() {

		Date value = (Date) getValue();
        DateFormat dateFormat = this.dateFormat;
        if (dateFormat == null)
            dateFormat = TIME_FORMAT_NO_SS;
        return (value != null ? dateFormat.format(value) : "");
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		 
		if (this.allowEmpty && !StringUtils.hasText(text)) {
	            setValue(null);
	        } else {
	            try {
	                if (this.dateFormat != null)
	                    setValue(this.dateFormat.parse(text));
	                else {
	                    Date date = getUtilDate(text);
	                    setValue(date);
	                }
	            } catch (Exception e) {
	                e.printStackTrace();  
	            }

	        }
	}

	private Date getUtilDate(String text) throws Exception {
        Date date  = null;
        String[] texts = text.split(":");
        if (texts.length == 1) {
            String[] pats = text.split("-");
            if(pats.length == 3) {
                date = DATE_FORMAT.parse(text);
            } else if(pats.length == 2) {
                date = DATE_FORMAT_NO_DD.parse(text);
            }
        } else if (texts.length == 2) {
            date = TIME_FORMAT_NO_SS.parse(text);
        } else if (texts.length == 3) {
            date = TIME_FORMAT.parse(text);
        }
        return date;
    }
}
