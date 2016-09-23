package edu.pucmm.pwa.ambientes;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

/**
 * Created by vacax on 20/09/16.
 */
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
@Component
public class AmbienteSesion {

    private int contador = 0;

    public int getContador(){
        return ++contador;
    }

}
