package hello.itemservice.message;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;

import java.util.Locale;

import static org.assertj.core.api.Assertions.*;
@SpringBootTest
public class MessageSourceTest {
    @Autowired
    MessageSource ms;
    @Test
    void helloMessage() {
        String result = ms.getMessage("hello", null, Locale.KOREA);
        assertThat(result).isEqualTo("안녕");
    }


//    nosuchmessageexception should there be no mapping of the message

    @Test
    void notFoundMessageCode() {
        assertThatThrownBy(() -> ms.getMessage("no_code", null, null))
                .isInstanceOf(NoSuchMessageException.class);
    }

//    defaultmessage settings exist
    @Test
    void notFoundMessageCodeDefaultMessage() {
        String result = ms.getMessage("no_code", null, "기본 메시지", null);
        assertThat(result).isEqualTo("기본 메시지");
    }

//    able to change {0} field in message.properties via parameters, parameter declared beside code
    @Test
    void argumentMessage() {
        String result = ms.getMessage("hello.name", new Object[]{"Spring"}, Locale.KOREA);
        assertThat(result).isEqualTo("안녕 Spring");
    }
    @Test
    void defaultLang() {
        assertThat(ms.getMessage("hello", null, Locale.KOREA)).isEqualTo("안녕");
        assertThat(ms.getMessage("hello", null, null)).isEqualTo("안녕");
    }
}