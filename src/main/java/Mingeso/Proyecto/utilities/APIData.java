package Mingeso.Proyecto.utilities;

import lombok.Data;

@Data
public class APIData {

    private String contentTypeData;
    private String apiTokenData;

    public APIData(String contentTypeData, String apiTokenData)
    {
        this.contentTypeData = contentTypeData;
        this.apiTokenData = apiTokenData;
    }
}