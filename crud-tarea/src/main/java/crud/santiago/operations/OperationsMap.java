package crud.santiago.operations;

import java.util.Map;

import crud.santiago.model.Empleado;

public interface  OperationsMap {

    /**
    * Crea un empleado
    */
    boolean create(Empleado empleado);
    /**
    * Lee el identificador de empleado
    */
    Empleado read(String identificador);
    /**
    * Lee el empleado 
    */
    Empleado read(Empleado empleado);
    /**
    * Actualiza el empleado
    */
    boolean update(Empleado empleado);
    /**
    * Elimina el empleado 
    */
    boolean delete(Empleado empleado);
    /**
    * Elimina el identificador
    */
    boolean delete(String identificador);
    /**
    * Crea un puesto y lo recorre por Map
    */
    Map<String,Empleado> empleadosPorPuestoTreeMap(String puesto);
    /**
    * Crea una edad y lo recorre por Map
    */
    Map<String,Empleado> empleadosPorEdadTreeMap(String fechaInicio, String fechaFin);
}
