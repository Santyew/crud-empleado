package crud;

import crud.santiago.file.FileOperationsMap;
import crud.santiago.model.Empleado;

public class Main {
    public static void main(String[] args) {
        FileOperations operaciones = new FileOperations();
        Empleado empleado = new Empleado("3", "Sara", "Gerente", 15, "25/02/1995");
        Empleado empleado1 = new Empleado("8", "Sergio", "Conserje", 85, "04/10/2004");
        Empleado empleado2 = new Empleado("3", "Emmanuel", "Oficinista", 69, "06/08/1997");

        operaciones.create(empleado);
        operaciones.create(empleado1);
        operaciones.update(empleado2);
        System.out.println(operaciones.empleadosPorPuestoTreeMap("Gerente"));
        System.out.println(operaciones.empleadosPorEdadTreeMap("06/08/1997", "04/10/2005"));
    }
}
