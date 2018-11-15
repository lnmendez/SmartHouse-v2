package com.example.luis.smarthouse.modelo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by luis on 10-12-17.
 */

public class CrudDesarrolladores {

    public static List<Desarrolladores> desarrolladoresList;

    public CrudDesarrolladores(){
        desarrolladoresList = new ArrayList<>();
        desarrolladoresList.add(new Desarrolladores("Luis Méndez", "Programador", "luis.mendez18@inacapmail.cl", "+56967684506"));
        desarrolladoresList.add(new Desarrolladores("Caterin Morales", "Diseñadora", "caterin.morales@inacapmail.cl", "+56962765086"));
        desarrolladoresList.add(new Desarrolladores("Jose Yañez", "Integracion con Arduino", "jose.yanez27@inacapmail.cl", "+56973524955"));
    }

}
