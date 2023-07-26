package com.nocountry.movenow;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.xhtmlrenderer.pdf.ITextRenderer;


@SpringBootApplication
public class MovenowApplication {

	public static void main(String[] args) {
		SpringApplication.run(MovenowApplication.class, args);
	}

	@Bean
    public ITextRenderer iTextRenderer() {
        return new ITextRenderer();
    }

}
