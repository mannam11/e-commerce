//package com.store.ezbuy.controller;
//
//import com.store.ezbuy.service.ImageService;
//import lombok.AllArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.IOException;
//import java.io.InputStream;
//
//@RestController
//@RequestMapping("/file")
//@AllArgsConstructor
//public class FileController {
//
//    private ImageService fileService;
//
//    @PostMapping
//    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
//        String response = fileService.uploadFile(file);
//        return ResponseEntity.ok(response);
//    }
//
//    @GetMapping("/{imageName}")
//    public ResponseEntity<byte[]> getImage(@PathVariable String imageName) {
//        try {
//            byte[] imageBytes = fileService.getFileContent(imageName);
//            String contentType = fileService.getContentType(imageName);
//            return ResponseEntity.ok()
//                    .contentType(MediaType.parseMediaType(contentType))
//                    .body(imageBytes);
//        } catch (IOException e) {
//            return ResponseEntity.notFound().build();
//        }
//    }
//}
