package controller;

import business_layer.DeliveryService;
import data_layer.Serializator;
import presentation_layer.*;

public class Controller {


    public static void main(String[] args) {
        DeliveryService deliveryService = Serializator.deserialize();
        EmployeeView employeeView = new EmployeeView(deliveryService);
        deliveryService.addObserver(employeeView);
        employeeView.show();
        LogInView view = new LogInView(deliveryService);
    }

}
