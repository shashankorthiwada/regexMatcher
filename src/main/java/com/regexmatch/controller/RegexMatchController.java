package com.regexmatch.controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.regexmatch.dto.RegexRequestWrapper;
import com.regexmatch.dto.RegexResponseWrapper;

@RestController
@RequestMapping("/api/regexmatch")
public class RegexMatchController
{

    @PostMapping(value = "/fetch")
    @ResponseBody
    public RegexResponseWrapper regexMatcher(@RequestBody RegexRequestWrapper reqWrapper)
    {
        RegexResponseWrapper response = new RegexResponseWrapper();
        try
        {
            if (reqWrapper != null && reqWrapper.getRegex() != null && reqWrapper.getTextBody() != null
                    && !reqWrapper.getRegex().isEmpty() && !reqWrapper.getTextBody().isEmpty())
            {
                String inputPattern = reqWrapper.getRegex();
                Pattern pattern = Pattern.compile(inputPattern);
                Matcher matcher = pattern.matcher(reqWrapper.getTextBody());
                boolean isAlreadyFound = false;
                while (matcher.find())
                {
                    // If Match is Found setting the First Matched Value in the Match and Setting Error as False
                    response.setMatch(matcher.group(0));
                    response.setError(false);
                    isAlreadyFound = true;
                    matcher.reset();

                }
                if (!isAlreadyFound)
                {
                    // If Match Not Found then Setting Match as Null and Error as False
                    response.setMatch(null);
                    response.setError(false);
                    matcher.reset();
                }

            }
        }
        catch (PatternSyntaxException exception)
        {
            // If given regex is invalid or bad then setting Match as Null and Error as True
            response.setError(true);
            response.setMatch(null);
        }
        return response;
    }

}
