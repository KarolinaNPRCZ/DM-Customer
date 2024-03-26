package com.nprcz.dmcustomer.messages;

import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import org.springframework.test.context.ContextConfiguration;

import java.util.Locale;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
@ContextConfiguration(classes = {
        MessageConfig.class,
        MessagesDTO.class
})
class MessageTest {

    @Autowired
    private MessageSource messageSource;
    @Autowired
    private MessagesDTO messagesDTO;

    @ParameterizedTest
    @MethodSource("getMessages")
    void should_return_message_in_specific_language(
            Locale local,
            String notNullErrorMessage,
            String notBlankErrorMessage,
            String invalidTypeErrorMessage,
            String invalidFormatErrorMessage,
            String notEqualErrorMessage,
            String wrongEmailPatternErrorMessage,
            String wrongPasswordErrorMessage,
            String validationPositiveOrZero,
            String validationDecimalMin,
            String validationDecimalMax


    ) {
        // GIVEN && WHEN
        String notNullResolvedMessage = messageSource.getMessage(
                "not.null", null, local
        );
        String notBlankResolvedMessage = messageSource.getMessage(
                "not.blank", null, local
        );
        String invalidTypeResolvedMessage = messageSource.getMessage(
                "invalid.type", new Object[]{"abc", "type"}, local
        );
        String invalidFormatResolvedMessage = messageSource.getMessage(
                "invalid.format", null, local
        );
        String wrongEmailPatternResolvedMessage = messageSource.getMessage(
                "wrong.email", null, local
        );
        String wrongPasswordResolvedMessage = messageSource.getMessage(
                "wrong.password", null, local
        );
        String notEqualResolvedMessage = messageSource.getMessage(
                "not.equal", null, local
        );
        String validationPositiveOrZeroResolvedMessage = messageSource.getMessage(
                "validation.positive.or.zero", null, local
        );
        String validationDecimalMinResolvedMessage = messageSource.getMessage(
                "validation.decimal.min", null, local
        );
        String validationDecimalMaxResolvedMessage = messageSource.getMessage(
                "validation.decimal.max", null, local
        );


        // THEN
        assertThat(notNullResolvedMessage)
                .isEqualTo(notNullErrorMessage);
        assertThat(notBlankResolvedMessage)
                .isEqualTo(notBlankErrorMessage);
        assertThat(invalidTypeResolvedMessage)
                .isEqualTo(invalidTypeErrorMessage
                        .replace("{0}", "abc")
                        .replace("{1}", "type")
                );
        assertThat(invalidFormatResolvedMessage)
                .isEqualTo(invalidFormatErrorMessage);
        assertThat(wrongEmailPatternResolvedMessage)
                .isEqualTo(wrongEmailPatternErrorMessage);
        assertThat(wrongPasswordResolvedMessage)
                .isEqualTo(wrongPasswordErrorMessage
                );
        assertThat(notEqualErrorMessage)
                .isEqualTo(notEqualResolvedMessage
                );

        assertThat(validationPositiveOrZero)
                .isEqualTo(validationPositiveOrZeroResolvedMessage
                );

        assertThat(validationDecimalMin)
                .isEqualTo(validationDecimalMinResolvedMessage
                );

        assertThat(validationDecimalMax)
                .isEqualTo(validationDecimalMaxResolvedMessage
                );

    }

    private Stream<Arguments> getMessages() {
        return Stream.of(
                Arguments.of(
                        Locale.ENGLISH,
                        messagesDTO.getNotNullMessage(),
                        messagesDTO.getNotBlankMessage(),
                        messagesDTO.getInvalidTypeMessage(),
                        messagesDTO.getInvalidFormatMessage(),
                        messagesDTO.getNotEqualMessage(),
                        messagesDTO.getWrongEmailPatternMessage(),
                        messagesDTO.getWrongPasswordMessage(),
                        messagesDTO.getValidationPositiveOrZero(),
                        messagesDTO.getValidationDecimalMin(),
                        messagesDTO.getValidationDecimalMax()


                )
        );
    }
}