package data_layer;

import business_layer.DeliveryService;

import java.io.*;

public class Serializator {

    private final static String FILE_NAME = "file.ser";

    public static void serialize(DeliveryService deliveryService) {
        try {
            FileOutputStream file = new FileOutputStream(FILE_NAME);
            ObjectOutputStream out = new ObjectOutputStream(file);
            out.writeObject(deliveryService);
            out.close();
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static DeliveryService deserialize() {
        DeliveryService deliveryService = new DeliveryService();
        try {
            FileInputStream file = new FileInputStream(FILE_NAME);
            ObjectInputStream in = new ObjectInputStream(file);
            while (in.available() > 0) {
                deliveryService = (DeliveryService) in.readObject();
            }
            in.close();
            file.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return deliveryService;
    }

}
