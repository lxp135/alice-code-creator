package alice.code.creator.service.generator.impl;

import org.apache.commons.collections.ExtendedProperties;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.apache.velocity.runtime.resource.Resource;
import org.apache.velocity.runtime.resource.loader.ResourceLoader;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class GeneratorResourceLoader extends ResourceLoader {
    @Override
    public void init(ExtendedProperties configuration) {

    }

    @Override
    public InputStream getResourceStream(String source) throws ResourceNotFoundException {
        InputStream result = null;

        if (source == null || source.length() == 0) {
            throw new ResourceNotFoundException(
                    "模板没有被定义~！");
        }
        result = new ByteArrayInputStream(source.getBytes());
        return result;
    }

    @Override
    public boolean isSourceModified(Resource resource) {
        return false;
    }

    @Override
    public long getLastModified(Resource resource) {
        return 0;
    }
}
