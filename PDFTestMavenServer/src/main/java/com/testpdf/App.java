package com.testpdf;

import com.testpdf.Utils.PathPropertiesUtils;
import java.util.logging.*;

public class App 
{
    public static void main( String[] args )
    {
        PathPropertiesUtils pathProperties = new PathPropertiesUtils();
        Logger.getLogger("ReadWithFileReader").log(Level.INFO, pathProperties.readProperty("VIAFIRMA_PATH"));
    }
}
