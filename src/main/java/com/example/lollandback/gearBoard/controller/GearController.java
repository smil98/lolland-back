package com.example.lollandback.gearBoard.controller;

import com.example.lollandback.gearBoard.domain.GearBoard;
import com.example.lollandback.gearBoard.dto.GearBoardDto;
import com.example.lollandback.gearBoard.service.GearService;
import com.example.lollandback.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/gearboard")
public class GearController {
    private final GearService service;

    // free
    @GetMapping("best")
    public List<GearBoard> listss(){
        return service.listss();
    }

    //today
    @GetMapping("today")
    public List<GearBoard> listto(){
        return service.listto();
    }


    @PostMapping("saves")
    public ResponseEntity saves(  GearBoard gearBoard,
                                  @RequestParam(value = "uploadFiles[]", required = false) MultipartFile[] files,
                                  @SessionAttribute(value = "login", required = false) Member login) throws IOException {
        if (!service.validate(gearBoard)) {
            return ResponseEntity.badRequest().build();
        }
        if (service.saves(gearBoard,files,login)){
            return ResponseEntity.ok().build();
        } else {
            return  ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("list")
    public List<GearBoard> list(@RequestParam String category){
        return service.list(category);
    }

    @GetMapping("listAll")
    public Map<String, Object> list(@RequestParam(value = "p",defaultValue = "1")Integer page){
    return service.listAll(page);
    }

    @GetMapping("gear_id/{gear_id}")
    public GearBoardDto getId(@PathVariable Integer gear_id){
        return service.getId(gear_id);
    }

    @DeleteMapping("remove/{gear_id}")
    public void remove(@PathVariable Integer gear_id){
        service.remove(gear_id);
    }

    @PutMapping("saveup")
    public  void saveup( GearBoard gearBoard,
                         @RequestParam(value = "removeFileIds[]",required = false) List<Integer> removeFilesIds,
                         @RequestParam(value = "uploadFiles[]",required = false) MultipartFile[] uploadFiles,
                         @SessionAttribute(value = "login" ,required = false)Member login) throws IOException {
        service.saveup(gearBoard,removeFilesIds,uploadFiles);
    }
}
