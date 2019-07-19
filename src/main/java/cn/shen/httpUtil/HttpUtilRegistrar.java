package cn.shen.httpUtil;

import cn.shen.httpUtil.annotation.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.util.ClassUtils;

import java.util.Set;

/**
 * @author huishen
 * @date 2019-07-19 17:13
 */

@Slf4j
public class HttpUtilRegistrar implements ImportBeanDefinitionRegistrar, ResourceLoaderAware,
    BeanFactoryAware, EnvironmentAware, BeanClassLoaderAware {

    private ResourceLoader resourceLoader;

    private BeanFactory beanFactory;

    private Environment environment;

    private ClassLoader classLoader;

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        doRegister();
    }

    private void doRegister() {
        ClassPathScanningCandidateComponentProvider classScanner = getClassScanner();
        // todo
        classScanner.setResourceLoader(resourceLoader);
        //指定只关注标注了@HTTPUtil注解的接口
        AnnotationTypeFilter annotationTypeFilter = new AnnotationTypeFilter(HttpUtil.class);
        classScanner.addIncludeFilter(annotationTypeFilter);

        // 写死了
        String basePack = "cn.shen";
        Set<BeanDefinition> beanDefinitions = classScanner.findCandidateComponents(basePack);
        for (BeanDefinition beanDefinition : beanDefinitions) {
            if (beanDefinition instanceof AnnotatedBeanDefinition) {
                registerBeans((AnnotatedBeanDefinition) beanDefinition);
            }
        }

    }

    private void registerBeans(AnnotatedBeanDefinition beanDefinition) {
        String className = beanDefinition.getBeanClassName();
        Object proxy = createProxy(beanDefinition);
        ((DefaultListableBeanFactory) beanFactory).registerSingleton(className, proxy);
    }

    private Object createProxy(AnnotatedBeanDefinition beanDefinition) {
        AnnotationMetadata annotationMetadata = beanDefinition.getMetadata();
        Class<?> target = null;
        try {
            target = Class.forName(annotationMetadata.getClassName());
        } catch (ClassNotFoundException e) {
            log.error(e.getMessage());
        }

        return HttpUtilRegistry.createProxy(target);
    }

    private ClassPathScanningCandidateComponentProvider getClassScanner() {
        return new ClassPathScanningCandidateComponentProvider(false, this.environment) {

            @Override
            protected boolean isCandidateComponent(
                AnnotatedBeanDefinition beanDefinition) {
                if (beanDefinition.getMetadata().isInterface()) {
                    try {
                        Class<?> target = ClassUtils.forName(
                            beanDefinition.getMetadata().getClassName(),
                            classLoader);
                        return !target.isAnnotation();
                    } catch (Exception ex) {
                        log.error("load class exception:", ex);
                    }
                }
                return false;
            }
        };
    }

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }
}
