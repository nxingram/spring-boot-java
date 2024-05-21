//package com.generation.fileupload.config;
//
//import java.nio.file.Path;
//import java.nio.file.Paths;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//@Configuration
//public class MvcConfig implements WebMvcConfigurer {
// 
// //N.B. NON SERVE se si usa la cartella static di spring per salvare le immagini
//    
//	// rende visibile dall'esterno la cartella delle fotografie, se il percorso è corretto
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        exposeDirectory(CustomProperties.basepath, registry);
//    }
//     
//    private void exposeDirectory(String dirName, ResourceHandlerRegistry registry) {
//        Path uploadDir = Paths.get(dirName);
//        String uploadPath = uploadDir.toFile().getAbsolutePath();
//         
//        if (dirName.startsWith("../")) dirName = dirName.replace("../", "");
//         
//        registry.addResourceHandler("/" + dirName + "/**").addResourceLocations("file:/"+ uploadPath + "/");
//    }
//}
