package Mingeso.Proyecto.utilities;

import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import org.springframework.beans.factory.annotation.Autowired;
import springfox.documentation.swagger2.mappers.ModelMapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class CsvUtils {

    @Autowired
    private ModelMapper modelmapper;

    public static <T> List<T> read(Class<T> clazz, InputStream stream) throws IOException {
        CsvSchema schema = modelmapper.schemaFor(clazz).withHeader().withColumnReordering(true);
        ObjectReader reader = modelmapper.readerFor(clazz).with(schema);
        return reader.<T>readValues(stream).readAll();
    }
}
