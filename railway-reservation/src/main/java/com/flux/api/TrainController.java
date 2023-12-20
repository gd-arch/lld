package com.flux.api;

import com.flux.model.train.Train;
import com.flux.model.train.TrainType;
import com.flux.services.TrainService;
import lombok.AllArgsConstructor;
import lombok.NonNull;

@AllArgsConstructor
public class TrainController {
    private final TrainService trainService;

    public String createTrain(@NonNull TrainType trainType,@NonNull String trainNo,@NonNull String routeNo){
        //we can also create custom train number
        try{
            String createdTrainNo = trainService.createTrain(trainType,trainNo,routeNo);
            return createdTrainNo;
        }catch (Exception exception){
            System.out.println("Exception occured:"+exception.getMessage());
            return null;
        }
    }
}
