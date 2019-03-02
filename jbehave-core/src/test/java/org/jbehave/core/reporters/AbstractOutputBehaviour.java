package org.jbehave.core.reporters;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import org.custommonkey.xmlunit.XMLUnit;
import org.jbehave.core.io.IOUtils;
import org.junit.Assert;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;


public abstract class AbstractOutputBehaviour {

    protected void assertThatOutputIs(String out, String pathToExpected) throws IOException {
        String expected = IOUtils.toString(getClass().getResourceAsStream(pathToExpected), true);
        Assert.assertEquals(dos2unix(expected), dos2unix(out));
    }

    protected String dos2unix(String string) {
        return string.replace("\r\n", "\n");
    }

    protected void assertFileOutputIsSameAs(File file, String name) throws IOException {
        String out = fileContent(file);
        assertThatOutputIs(out, "/" + name);
    }

    protected String fileContent(File file) throws IOException {
        return IOUtils.toString(new FileReader(file), true);
    }

    protected void validateFileOutput(File file) throws IOException, SAXException {
        String out = fileContent(file);
        if ( file.getName().endsWith(".xml") ){
            // will throw SAXException if the xml file is not well-formed
            XMLUnit.buildTestDocument(out);
        } else if ( file.getName().endsWith(".json") ){
            // will not throw JsonSyntaxException if the json file is not valid
            Gson gson = new Gson();
            JsonReader jsonReader = new JsonReader(new StringReader(out));
            jsonReader.setLenient(false);
            gson.fromJson(jsonReader, Object.class);
        }
    }

    protected File newFile(String path) {
        File file = new File(path);
        file.delete();
        return file;
    }
}
