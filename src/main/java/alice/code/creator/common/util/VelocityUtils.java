package alice.code.creator.common.util;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Properties;

/**
 * Velocity模板工具类
 * @author contact@liuxp.me
 */
public class VelocityUtils {

    /**
     * 生成文件
     * @param content 模板内容
     * @param map 参数集合
     * @param importFilePath 目标文件路径
     */
    public static void generatorCode(String content, HashMap<String, Object> map, String importFilePath){
        try {

            Properties p = new Properties();
            p.setProperty(Velocity.OUTPUT_ENCODING, "UTF-8");
            p.setProperty(Velocity.INPUT_ENCODING, "UTF-8");

            p.put("resource.loader","srl");
            p.put("srl.resource.loader.class","alice.code.creator.service.generator.impl.GeneratorResourceLoader");

            VelocityEngine velocityEngine = new VelocityEngine();
            velocityEngine.init(p);
            VelocityContext context = new VelocityContext();
            Iterator it = map.entrySet().iterator();
            while(it.hasNext()){
                Entry entry = (Entry)it.next();
                context.put(entry.getKey().toString(), entry.getValue());
            }
            Template template = velocityEngine.getTemplate(content);
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(importFilePath), "UTF-8"));
            template.merge(context, writer);
            writer.flush();
            writer.close();
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }

}