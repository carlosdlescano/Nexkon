/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.minegocio.util;

import com.minegocio.model.*;
//import com.minegocio.model.Familia;
//import com.minegocio.model.Estado;
import java.util.List;
/**
 *
 * @author miNegocio
 */
public interface DatosCompartidos {
    void setDatos(List<Departamento> departamentos, List<Familia> familias, List<Rubro> rubros, List<Marca> marcas);
                  //List<Estado> estados);
    
}


