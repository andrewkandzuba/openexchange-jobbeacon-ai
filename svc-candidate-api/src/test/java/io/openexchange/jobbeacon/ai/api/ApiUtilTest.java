package io.openexchange.jobbeacon.ai.api;

import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.context.request.NativeWebRequest;

import java.io.IOException;
import java.io.PrintWriter;

import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ApiUtilTest {

    @Mock
    public NativeWebRequest req;
    @Mock
    public HttpServletResponse resp;
    @Mock
    public PrintWriter writer;

    @BeforeEach
    void setUp() {
        when(req.getNativeResponse(HttpServletResponse.class)).thenReturn(resp);
    }

    @Test
    void setExampleResponse() throws IOException {
        when(resp.getWriter()).thenReturn(writer);
        ApiUtil.setExampleResponse(req, "text/html", "example");
    }

    @Test
    void setExampleResponseWithIOException() {
        assertThrowsExactly(RuntimeException.class, () -> {
            when(resp.getWriter()).thenThrow(new IOException());
            ApiUtil.setExampleResponse(req, "text/html", "example");
        });
    }
}