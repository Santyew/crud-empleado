package crud.santiago.file;

import java.io.File;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import crud.santiago.model.Empleado;

/**
 *@author santiago
 *@version 1.0
 */
public class FileOperationsMap extends FileOperations {
    public FileOperationsMap(){
        super();
    }

    private Map<String, Empleado> readMap(File file){
        Map<String,Empleado> empleadosMap = new TreeMap();
        Set<Empleado> empleados = super.read(file);
        for (Empleado empleado : empleados) {
            empleadosMap.put(empleado.getIdentificador(), empleado);
        }
        return empleadosMap;
    }

    private Map<String, Empleado> empleadosPorPuestoMap(String puesto){
        Map<String,Empleado> empleadosPorPuestoMap = new TreeMap();
        Set<Empleado> empleados = super.empleadosPorPuesto(puesto);
        for (Empleado empleado : empleados) {
            empleadosPorPuestoMap.put(empleado.getIdentificador(), empleado);
        }
        return empleadosPorPuestoMap;
    }

    private Map<String, Empleado> empleadosPorEdadMap(String fechaInicio, String fechaFin){
        Map<String,Empleado> empleadosPorEdadMap = new TreeMap();
        Set<Empleado> empleados = super.empleadosPorEdad(fechaInicio,fechaFin);
        for (Empleado empleado : empleados) {
            empleadosPorEdadMap.put(empleado.getIdentificador(), empleado);
        }
        return empleadosPorEdadMap;
    }

}

