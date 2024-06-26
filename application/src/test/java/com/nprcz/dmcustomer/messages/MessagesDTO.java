package com.nprcz.dmcustomer.messages;

import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.Locale;
import java.util.ResourceBundle;

@Component
@Getter
 class MessagesDTO {

    private final String notNullMessage;
    private final String notBlankMessage;
    private final String invalidTypeMessage;
    private final String invalidFormatMessage;
    private final String wrongEmailPatternMessage;
    private final String wrongPasswordMessage;
    private final String notEqualMessage;
    private final String validationPositiveOrZero;
    private final String validationDecimalMin;
    private final String validationDecimalMax;

    public MessagesDTO() {
        this.validationPositiveOrZero = getMessageFromProperties("validation.positive.or.zero");
        this.validationDecimalMin = getMessageFromProperties("validation.decimal.min");
        this.validationDecimalMax = getMessageFromProperties("validation.decimal.max");
        this.notNullMessage = getMessageFromProperties("not.null");
        this.notBlankMessage = getMessageFromProperties("not.blank");
        this.invalidTypeMessage = getMessageFromProperties("invalid.type");
        this.invalidFormatMessage = getMessageFromProperties("invalid.format");
        this.wrongEmailPatternMessage = getMessageFromProperties("wrong.email");
        this.wrongPasswordMessage = getMessageFromProperties("wrong.password");
        this.notEqualMessage = getMessageFromProperties("not.equal");
    }

    private String getMessageFromProperties(String key) {
        ResourceBundle bundle = ResourceBundle.getBundle(
                "messages/messages", Locale.forLanguageTag("en")
        );
        return bundle.getString(key);
    }
}
