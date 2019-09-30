import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UsoExpresionesRegularesTest {
    @BeforeEach
    void setUp() {
    }

    @Test
    void ingresoPalabras() {
        assertEquals(false, UsoExpresionesRegulares.ingresoPalabras("asdsa"));

    }

    @Test
    void validarRut() {
        assertEquals(false, UsoExpresionesRegulares.validarRut("200809610"));

    }


}