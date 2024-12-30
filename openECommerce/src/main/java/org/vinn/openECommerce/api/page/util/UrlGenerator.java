package org.vinn.openECommerce.api.page.util;

import org.springframework.stereotype.Component;

import java.text.Normalizer;
import java.util.Locale;
import java.util.regex.Pattern;

/**
 * Utility class for generating SEO-friendly URLs.
 */
@Component
public class UrlGenerator {

    private static final Pattern NON_LATIN = Pattern.compile("[^\\w-]");
    private static final Pattern WHITESPACE = Pattern.compile("[\\s]");

    /**
     * Generates a URL-friendly string from the given name and a unique identifier.
     *
     * @param name the base name for the URL.
     * @param uniqueId a unique identifier to ensure uniqueness.
     * @return a SEO-friendly URL.
     */
    public String generateUrl(String name, Long uniqueId) {
        String normalized = Normalizer.normalize(name, Normalizer.Form.NFD);
        String slug = NON_LATIN.matcher(WHITESPACE.matcher(normalized).replaceAll("-"))
                .replaceAll("")
                .toLowerCase(Locale.ENGLISH);

        return String.format("%s-%d", slug, uniqueId);
    }
}
