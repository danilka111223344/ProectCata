package koschei.models;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Duck5 {
    private Egg6 egg6;


    public String toString() {
        return ", в утке яйцо " + egg6.toString();
    }

    @Autowired
    public void setEgg (Egg6 egg6) { this.egg6 = egg6; }
}

