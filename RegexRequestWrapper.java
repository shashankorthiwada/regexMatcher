
package com.regexmatch.dto;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "Regex", "TextBody" })
public class RegexRequestWrapper
{
    @JsonProperty("Regex")
    private String regex;

    @JsonProperty("TextBody")
    private String textBody;

    public String getRegex()
    {
        return regex;
    }

    public void setRegex(String regex)
    {
        this.regex = regex;
    }

    public String getTextBody()
    {
        return textBody;
    }

    public void setTextBody(String textBody)
    {
        this.textBody = textBody;
    }

    @Override
    public String toString()
    {
        return ToStringBuilder.reflectionToString(this);
    }

}
