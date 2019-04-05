/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Edham
 */

public class ParametroNodo {
    
    private List<parametro> parametros;
	
	public ParametroNodo() {
		parametros = new ArrayList<parametro>();
	}

	public List<parametro> getParametros() {
		return parametros;
	}

	public void setParametros(ArrayList<parametro> parametros) {
		this.parametros = parametros;
	}
	
	public void adicionar(String campo, Object valor)
	{
            getParametros().add(new parametro(campo,valor));
	}
        
        public class parametro{
            private String campo;
            private Object valor;
            
            public parametro(String campo, Object valor) {
                this.campo = campo;
                this.valor = valor;
            }
           
            public String getCampo() {
                return campo;
            }

            public void setCampo(String campo) {
                this.campo = campo;
            }

            public Object getValor() {
                return valor;
            }

            public void setValor(Object valor) {
                this.valor = valor;
            }
        }
}
