package com.carload.vehiclejava21crud.controller;

import com.carload.vehiclejava21crud.models.CarLoadEntity;
import com.carload.vehiclejava21crud.models.dtos.CarLoadInputDto;
import com.carload.vehiclejava21crud.models.dtos.CarLoadOutPutDto;
import com.carload.vehiclejava21crud.services.CarLoadService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin
@RequestMapping("/carloads")
public class CarloadController {

    private  final CarLoadService carLoadService;

    public CarloadController(CarLoadService carLoadService) {
        this.carLoadService = carLoadService;
    }

    @GetMapping
    public List<CarLoadEntity> showAllCarLoads(){
        List<CarLoadEntity> carLoads= carLoadService.findAllCarLoads();
        return carLoads;
    }

    @PostMapping
    public CarLoadEntity createCarload(@RequestBody CarLoadOutPutDto carLoadEntity){
      CarLoadEntity newCarload= new CarLoadEntity();
      newCarload.setClientName(carLoadEntity.getClientName());
      newCarload.setClienNumber(carLoadEntity.getClienNumber());
      newCarload.setDestino(carLoadEntity.getDestino());
      newCarload.setStatus(carLoadEntity.getStatus());
      newCarload.setValorPago(carLoadEntity.getValorPago());

        carLoadService.saveCarload(newCarload);
        return newCarload;
    }

}
