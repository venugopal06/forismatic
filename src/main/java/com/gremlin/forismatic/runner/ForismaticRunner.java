package com.gremlin.forismatic.runner;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.gremlin.forismatic.dto.ForismaticDTO;
import com.gremlin.forismatic.exception.ForismaticException;
import com.gremlin.forismatic.service.ForismaticRestClient;
import com.gremlin.forismatic.util.ForismaticLanguage;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
@Slf4j
@AllArgsConstructor
public class ForismaticRunner implements CommandLineRunner {

    private final ForismaticRestClient forismaticRestClient;

    /**
     * Callback used to run the bean.
     *
     * @param args incoming main method arguments
     */
    @Override
    public void run(String... args) {
        try {
            // Default the language to English
            String requestedLanguage = "english";
            if (args.length > 0) {
                requestedLanguage = args[0];
                log.info("The language requested by the user is " + requestedLanguage);
                if (ForismaticLanguage.valueOfLabel(requestedLanguage.toLowerCase()) == null) {
                    throw new ForismaticException("Invalid language is passed as an argument. Exiting the application...");
                }
            }
            String languageCode = ForismaticLanguage.valueOfLabel(requestedLanguage.toLowerCase()).name();

            ForismaticDTO forismaticDTO = forismaticRestClient.getForismaticQuote(languageCode);
            log.info("The quote is: " + forismaticDTO.getQuoteText());
            log.info("The author is: " + forismaticDTO.getQuoteAuthor());

            log.info("Finished processing. Exiting the application!");
        } catch (ForismaticException | JsonProcessingException e) {
            log.error(e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
    }
}
