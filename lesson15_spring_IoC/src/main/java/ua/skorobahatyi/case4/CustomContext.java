package ua.skorobahatyi.case4;

import lombok.SneakyThrows;
import org.reflections.Reflections;

import java.util.HashMap;
import java.util.Map;

public class CustomContext {
    private Map<String,Object> beanMap = new HashMap<>();

    public CustomContext() {
        init();
    }

    private void init(){
        var currentPackage = getClass().getPackage().getName();
        var reflections = new Reflections(currentPackage);
        reflections.getTypesAnnotatedWith(BeanNick.class)
                .stream()
                .forEach(this::registerBean);
        // scan current package;
        // find all classes that require bean creation
        // create object for each class
        // add object to the beanMap

    }

    @SneakyThrows
    private void registerBean(Class<?> type) {
        var beanAnotation =type.getAnnotation(BeanNick.class);
        var beanId = beanAnotation.value();
        var constructor = type.getConstructor();
        var beanInstance = constructor.newInstance();
        beanMap.put(beanId,beanInstance);

    }

    public <T> T getBean(Class<T> type ){
        return beanMap.values()
                .stream()
                .filter(type::isInstance)
                .findAny()
                .map(type::cast)
                .orElseThrow();
    }
}
