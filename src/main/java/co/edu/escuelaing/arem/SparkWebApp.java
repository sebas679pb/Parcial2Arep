package co.edu.escuelaing.arem;

import static spark.Spark.*;

public class SparkWebApp {

    public static void main(String[] args) {
        port(getPort());
        staticFiles.location("/public");
        get("/collatzsequence",(req, res)->{
            res.type("application/json");
            return "{\"operation\":\"collatzsequence\",\"input\":"+ Integer.parseInt(req.queryParams("value")) +            
                ",\"output\":"+ getCollatzSequence(Integer.parseInt(req.queryParams("value"))) +"}";
        });
    }

    public static String getCollatzSequence(int value) {
        String sequence = String.valueOf(value);
        while(value > 1){
            if(value % 2 == 0)
                value = value / 2;
            else
                value = (3*value) + 1;
            sequence = sequence + "->" + String.valueOf(value);
        }
        return sequence;
    }
    
    static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 5000;
    }
}
