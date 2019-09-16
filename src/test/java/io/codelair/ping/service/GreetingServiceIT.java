package io.codelair.ping.service;

import io.codelair.ping.model.Greeting;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import java.util.Locale;

import static org.assertj.core.api.Assertions.assertThat;

/**
 *
 * @author Hassan Nazar, hassenasse @ github (2019)
 */
@RunWith( Arquillian.class )
public class GreetingServiceIT
{
    @Deployment
    public static WebArchive createDeployment()
    {
        return ShrinkWrap.create( WebArchive.class )
                .addClasses(
                        GreetingService.class,
                        Greeting.class
                )
                .addAsManifestResource( "test-microprofile-config.properties", "microprofile-config.properties" )
                .addAsManifestResource( EmptyAsset.INSTANCE, "beans.xml" );
    }

    @Inject GreetingService service;

    @Test
    public void getGreeting_shouldReturnCorrectGreetings(){
        assertThat(service.getGreeting( Locale.ENGLISH )).isNotNull();
    }
}
