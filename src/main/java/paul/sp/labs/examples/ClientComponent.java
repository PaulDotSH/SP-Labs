package paul.sp.labs.examples;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ClientComponent {
    private final TransientComponent t;
    private final SingletonComponent s;

    @Autowired
    public ClientComponent(TransientComponent t, SingletonComponent s) {
        this.t = t;
        this.s = s;
        System.out.println("ClientComponent = " + this);
        System.out.println("\t- SingletonComponent = " + s);
        System.out.println("\t- TransientComponent = " + t);
    }

    public void operation() {
        System.out.println("Invoked ClientComponent::operation() on " +
                this);
        System.out.println("\t- SingletonComponent = " + s);
        System.out.println("\t- TransientComponent = " + t);
    }
}