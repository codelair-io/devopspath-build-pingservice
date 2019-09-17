package io.codelair.ping.service;

import io.codelair.ping.model.Greeting;
import org.eclipse.microprofile.config.Config;
import org.eclipse.microprofile.config.ConfigProvider;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.Locale;

/**
 *
 * @author Hassan Nazar, hassenasse @ github (2019)
 */
@ExtendWith( MockitoExtension.class)
@DisplayName("Testing GreetingService")
public class GreetingServiceTest
{
    @Mock Config config;
    GreetingService greetingService = new GreetingService();

    @ParameterizedTest
    @ValueSource(strings = { "en", "sv", "es" })
    void getGreeting_shouldReturnCorrectLocale(String localeInput) {
        //GIVEN
        var locale = new Locale( localeInput );
        var returnMessage = "Hello from " + locale.getLanguage();
        when(config.getValue( Mockito.anyString(), Mockito.any() )).thenReturn( returnMessage );

        //WHEN
        greetingService.config = this.config;
        var result = greetingService.getGreeting( locale );

        //THEN
        System.out.println( "result.message = " + result.message );
        assertThat( result.message ).isEqualToIgnoringCase( returnMessage );
    }

    @Test
    void getGreeting_shouldThrowIfGreetingNotFound(){
        //GIVEN
        when(config.getValue( Mockito.anyString(), Mockito.any() )).thenReturn( null );

        //WHEN
        greetingService.config = this.config;

        //THEN
        assertThatExceptionOfType( IllegalArgumentException.class ).isThrownBy( () -> {
            greetingService.getGreeting( Locale.CHINESE );
        } );
    }
}
