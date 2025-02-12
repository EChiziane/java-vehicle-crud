package com.carload.vehiclejava21crud.controller;

import com.carload.vehiclejava21crud.models.CarLoadEntity;
import com.carload.vehiclejava21crud.models.CarloadStatus;
import com.carload.vehiclejava21crud.models.dtos.CarLoadInputDto;
import com.carload.vehiclejava21crud.models.dtos.CarLoadOutPutDto;
import com.carload.vehiclejava21crud.services.CarLoadService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


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



    @GetMapping("/entreges")
    public List<CarLoadEntity> showEntreguesCarLoads(){
        List<CarLoadEntity> carLoads= carLoadService.findCarLoadsByStatus(CarloadStatus.ENTREGUE);
        return carLoads;
    }

    @GetMapping("/progress")
    public List<CarLoadEntity> showProgressCarLoads(){
        List<CarLoadEntity> carLoads= carLoadService.findCarLoadsByStatus(CarloadStatus.EM_TRANSPORTE);
        return carLoads;
    }


    @PostMapping
    public CarLoadEntity createCarload(@RequestBody CarLoadOutPutDto carLoadEntity){
      CarLoadEntity newCarload= new CarLoadEntity();
      newCarload.setClientName(carLoadEntity.getClientName());
      newCarload.setClientNumber(carLoadEntity.getClientNumber());
      newCarload.setDestino(carLoadEntity.getDestino());
      newCarload.setStatus(carLoadEntity.getStatus());
      newCarload.setValorPago(carLoadEntity.getValorPago());

        carLoadService.saveCarload(newCarload);
        return newCarload;
    }


    @DeleteMapping("/{id}")
    public String deleteCarload(@PathVariable UUID id){
        carLoadService.deleteCarload(id);
        return "Success Deleting";
    }

    @PutMapping("/{id}")
    public ResponseEntity<CarLoadEntity> updateCarload(@PathVariable UUID id, @RequestBody CarLoadEntity carLoad){
     carLoad.setId(id);
     CarLoadEntity carLoadUpdated= carLoadService.updateCarload(carLoad);
     return ResponseEntity.ok(carLoadUpdated);
    }
}
