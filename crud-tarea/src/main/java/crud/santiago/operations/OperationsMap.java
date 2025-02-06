package crud.santiago.operations;

import java.util.Map;

import crud.santiago.model.Empleado;

public interface  OperationsMap {
    
    boolean create(Empleado empleado);

    Empleado read(String identificador);

    Empleado read(Empleado empleado);

    boolean update(Empleado empleado);

    boolean delete(Empleado empleado);

    boolean delete(String identificador);

    Map<String,Empleado> empleadosPorPuestoTreeMap(String puesto);

    Map<String,Empleado> empleadosPorEdadTreeMap(String fechaInicio, String fechaFin);
}
