package paul.sp.labs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import paul.sp.labs.examples.ClientComponent;
import paul.sp.labs.examples.SingletonComponent;
import paul.sp.labs.examples.TransientComponent;

@SpringBootApplication
public class SpringStart {
    public static void main(String[] args) {
        ApplicationContext context =
                SpringApplication.run(SpringStart.class, args);
        demonstrateDependencyInjection(context);
    }

    private static void demonstrateDependencyInjection(ApplicationContext context) {
        // Transient Component Example
        TransientComponent transientBean = context.getBean(TransientComponent.class);
        transientBean.operation();

        // Another instance of Transient Component
        transientBean = context.getBean(TransientComponent.class);
        transientBean.operation();

        // Singleton Component Example
        SingletonComponent singletonBean = context.getBean(SingletonComponent.class);
        singletonBean.operation();

        // Another instance of Singleton Component
        singletonBean = context.getBean(SingletonComponent.class);
        singletonBean.operation();

        // Client Component Example
        ClientComponent clientComponent = context.getBean(ClientComponent.class);
        clientComponent.operation();

        // Another way to get Client Component using bean name
        clientComponent = (ClientComponent) context.getBean("clientComponent");
        clientComponent.operation();
    }

}