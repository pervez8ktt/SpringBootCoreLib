package com.knitkota.core.config;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.stereotype.Service;

@Service
public class CustomThreadHandler {
	
	private ExecutorService executor;
	
	
	@PostConstruct
	public void init() {
		
		System.out.println("Start down");
		
		executor = Executors.newFixedThreadPool(5);
	}
	
	@PreDestroy
	public void destroy(){
	
		System.out.println("Shut down");
		executor.shutdown();
		
	}
	
	
	public void addThread(Runnable runnable) {
		
		Thread t = new Thread(runnable);
		
		
        executor.execute(t);
		
	}
}
