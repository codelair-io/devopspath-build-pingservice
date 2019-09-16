package io.codelair.ping.service;

import io.codelair.ping.model.Greeting;
import org.eclipse.microprofile.config.Config;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.Locale;
import java.util.Optional;

/**
 * @author Hassan Nazar, hassenasse @ github (2019)
 */
@RequestScoped
public class GreetingService
{
    @Inject Config config;

    public Greeting getGreeting( Locale locale )
    {
        var greeting = Optional
                .ofNullable( config.getValue( "message_" + locale.getLanguage(), String.class  ) );

        return greeting
                .map( Greeting::new )
                .orElseThrow( IllegalArgumentException::new );
    }
}
