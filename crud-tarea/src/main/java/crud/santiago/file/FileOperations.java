package crud.santiago.file;

import crud.santiago.model.Empleado;
import crud.santiago.operations.Operations;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

/**
 * @author santiago
 * @version 1.0
 */

public class FileOperations implements Operations {
    File fichero;
    String path = "/Users/Santiago/Desktop/crud-tarea/src/main/java/crud/santiago/resources/empleados.txt"; 

    public FileOperations() {
        fichero = new File(path);
        if (!fichero.exists() || !fichero.isFile()) {
            throw new IllegalArgumentException("El recurso no es de tipo fichero " + path);
        }
    }

    /**
     * Añade un empleado al archivo
     */

    @Override
    public boolean create(Empleado empleado) {
        if (empleado == null || empleado.getIdentificador() == null || empleado.getIdentificador().isEmpty()) {
            return false;
        }
        Set<Empleado> empleados = read(fichero);
        if (empleados.contains(empleado)) {
            return false;
        }
        return create(empleado.toString(), fichero);
    }

    private boolean create(String data, File file) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            writer.write(data);
            writer.newLine(); 
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    /**
     * Lee la informacion del archivo
     * @param file lugar de la informacion
     * @return Set<Empleado>
     */

    private Set<Empleado> read(File file) {
        if (file == null) {
            return new HashSet<>();
        }
        Set<Empleado> empleados = new HashSet<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] arrayLine = line.trim().split(",");
                Empleado empleado = new Empleado(arrayLine[0], arrayLine[1], arrayLine[2], Double.valueOf(arrayLine[3]), arrayLine[4]);
                empleados.add(empleado);
            }
        } catch (IOException e) {
            return new HashSet<>();
        }
        return empleados;
    }

    /**
     * Metodo que actualiza la informacion de un empleado
     * @param empleado que van actualizar
     * @return true/false
     */

    @Override
    public boolean update(Empleado empleado) {
        if (empleado == null || empleado.getIdentificador() == null || empleado.getIdentificador().isEmpty()) {
            return false;
        }
        Set<Empleado> empleados = read(fichero);
        if (!empleados.contains(empleado)) {
            return false;
        }
        empleados.remove(empleado); // Remover el empleado existente
        empleados.add(empleado); // Agregar el empleado actualizado
        return updateFile(empleados, fichero);
    }

    /**
     * Actualiza la informacion del fichero
     * @param empleados de la empresa
     * @param file lugar de la informacion
     * @return true/false
     */

    private boolean updateFile(Set<Empleado> empleados, File file) {
        try {
            file.delete();
            file.createNewFile();
        } catch (IOException e) {
            return false;
        }
        for (Empleado empleado : empleados) {
            create(empleado);
        }
        return true;
    }

    /**
     * Elimina a un empleado
     */

    @Override
    public boolean delete(Empleado empleado) {
        if (empleado == null || empleado.getIdentificador() == null || empleado.getIdentificador().isEmpty()) {
            return false;
        }
        Set<Empleado> empleados = read(fichero);
        if (!empleados.contains(empleado)) {
            return false;
        }
        empleados.remove(empleado); // Remover el empleado
        return updateFile(empleados, fichero);
    }

    /**
     * Lee a un empleado por su identificador
     */

    @Override
    public Empleado read(String identificador) {
        return read(new Empleado(identificador));
    }

    /**
     * Lee a un empleado por su tipo objeto empleado
     */

    @Override
    public Empleado read(Empleado empleado) {
        Set<Empleado> empleados = read(fichero);
        for (Empleado empleado2 : empleados) {
            if (empleado2.equals(empleado)) {
                return empleado2;
            }
        }
        return null;
    }

    /**
     * retorna una lista de los empleados de un puesto indicado
     */

    @Override
    public Set<Empleado> empleadosPorPuesto(String puesto) {
        if (puesto == null || puesto.isEmpty()) {
            return new HashSet<>();
        }
        Set<Empleado> empleados = read(fichero);
        Set<Empleado> porPuesto = new HashSet<>();
        for (Empleado empleado : empleados) {
            if (empleado.getPuesto().trim().equals(puesto.trim())) {
                porPuesto.add(empleado);
            }
        }
        return porPuesto;
    }

    /**
     * Lista los empleados por su edad
     */
    
    @Override
    public Set<Empleado> empleadosPorEdad(String fechaInicio, String fechaFin) {
        if (fechaInicio == null || fechaInicio.isEmpty() || fechaFin == null || fechaFin.isEmpty()) {
            return new HashSet<>();
        }
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("d/MM/yyyy");
        LocalDate inicio = LocalDate.parse(fechaInicio, formato);
        LocalDate fin = LocalDate.parse(fechaFin, formato);
        Set<Empleado> empleados = read(fichero);
        Set<Empleado> porEdad = new HashSet<>();
        for (Empleado empleado : empleados) {
            LocalDate cumpleanio = LocalDate.parse(empleado.getFechaNacimiento(), formato);
            if (cumpleanio.isAfter(inicio) && cumpleanio.isBefore(fin)) {
                porEdad.add(empleado);
            }
        }
        return porEdad;
    }
}