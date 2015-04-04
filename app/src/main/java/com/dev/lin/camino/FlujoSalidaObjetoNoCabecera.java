package com.dev.lin.camino;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

/**
 * Escritura de objetos sin almacenar cabecera para evitar la corrupción al añadir más objetos 
 *
 * @author German Martínez Maldonado
 * @author Pablo Sánchez Robles
 */
public class FlujoSalidaObjetoNoCabecera extends ObjectOutputStream{

    public FlujoSalidaObjetoNoCabecera(OutputStream out) throws IOException{
        super(out);
    }

    @Override
    protected void writeStreamHeader() throws IOException {
        reset();
    }

}
