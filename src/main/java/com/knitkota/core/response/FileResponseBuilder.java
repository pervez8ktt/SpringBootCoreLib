package com.knitkota.core.response;

import java.io.ByteArrayInputStream;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.knitkota.core.exception.power.CustomResponseException;

public class FileResponseBuilder {

	private MediaType mediaType; 
	private byte[] resource; 
	private String resourceName; 
	private boolean needDownload;
	
	public FileResponseBuilder setMediaType(MediaType mediaType) {
		this.mediaType = mediaType;
		return this;
	}
	public FileResponseBuilder setResource(byte[] resource) {
		this.resource = resource;
		return this;
	}
	public FileResponseBuilder setResourceName(String resourceName) {
		this.resourceName = resourceName;
		return this;
	}
	public FileResponseBuilder setNeedDownload(boolean needDownload) {
		this.needDownload = needDownload;
		return this;
	}
	
	public ResponseEntity<InputStreamResource> build() {
		if(resource==null) {
			throw new CustomResponseException();
		}
		
		if(mediaType==null) {
			mediaType = MediaType.IMAGE_JPEG;
		}
		
		if(resourceName==null) {
			this.resourceName = "image.jpg";
		}
		
		ByteArrayInputStream in = new ByteArrayInputStream(resource);

		HttpHeaders headers = new HttpHeaders();

		headers.setContentType(mediaType);
		
		if(needDownload) {
			headers.add("Content-Disposition", "attachment; filename="+resourceName);
		}
		
		

		return ResponseEntity.ok().headers(headers).body(new InputStreamResource(in));
	}
	
}
